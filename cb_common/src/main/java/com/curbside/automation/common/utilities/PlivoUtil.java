package com.curbside.automation.common.utilities;

import com.cucumber.listener.Reporter;
import com.curbside.automation.uifactory.MobileDevice;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.message.Message;
import com.plivo.helper.api.response.message.MessageFactory;
import com.plivo.helper.api.response.message.MessageMeta;
import com.plivo.helper.exception.PlivoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlivoUtil {

    public List<String> getAllMessage(String authId, String authToken) {
        List<String> smsList = new ArrayList<String>();
        RestAPI api = new RestAPI(authId, authToken, "v1");

        try {
            // Get details off all the messages
            MessageFactory msgFactory = api.getMessages();

            for (Message msg : msgFactory.messageList) {

                smsList.add(msg.messageTime + ":" + msg.toNumber + ":"
                        + msg.fromNumber + ":" + msg.messageState + ":"
                        + msg.messageDirection + ":" + msg.resourceUri);
            }
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return smsList;
    }

    public String getLatestOutboundMessage(String authId, String authToken) {
        String latestSms = null;
        RestAPI api = new RestAPI(authId, authToken, "v1");
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "1");
            parameters.put("offset", "0");
            parameters.put("message_state", "sent");
            parameters.put("message_direction", "outbound");

            // Setting filter
            MessageFactory msgFactory = api.getMessages(parameters);
            latestSms = msgFactory.messageList.get(0).toNumber + ":"
                    + msgFactory.messageList.get(0).fromNumber + ":"
                    + msgFactory.messageList.get(0).resourceUri;

        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return latestSms;
    }


    public static MessageFactory getLatestInboundMessage(String authId, String authToken) {
        MessageFactory msgFactory = null;
        RestAPI api = new RestAPI(authId, authToken, "v1");
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "1");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");

            // Setting filter
            msgFactory = api.getMessages(parameters);
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return msgFactory;
    }

    public static String getLatestMsgToNumber(String authId, String authToken) {
        String msgToNumber = null;
        MessageFactory msgFactory;
        RestAPI api;
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "1");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");
//            parameters.put("message_time__gte", getDateAndTime());

            // Setting filter
            api = new RestAPI(authId, authToken, "v1");
            msgFactory = api.getMessages(parameters);
            msgToNumber = msgFactory.messageList.get(0).toNumber;

        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return msgToNumber;
    }


    public static int getInboundMsgCount(String authId, String authToken) {
        int total_count = 0;
        String response;
        JsonObject convertedObject;
        RestAPI api;

        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "20");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");
//            parameters.put("message_time__gte", getDateAndTime());


            api = new RestAPI(authId, authToken, "v1");
            response = api.request("GET", "/Message/", parameters);
            convertedObject = new Gson().fromJson(response, JsonObject.class);
            total_count = Integer.parseInt(convertedObject.get("meta")
                    .getAsJsonObject()
                    .get("total_count"
                    ).toString());

        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return total_count;
    }

    public static boolean isSmsReceived(String authId, String authToken, String expectedPhoneNumber, int previousMsgCount) {
        int expectedMsgCount;
        String actualToNumber;
        boolean status;

        expectedMsgCount = getInboundMsgCount(authId, authToken);
        Reporter.addStepLog("Previous message count : " + previousMsgCount + " | Latest message count : " + expectedMsgCount);
        actualToNumber = getLatestMsgToNumber(authId, authToken);
        Reporter.addStepLog("Expected latest toNumber : " + expectedPhoneNumber + " | Actual latest toNumber : " + actualToNumber);
        if (!actualToNumber.equalsIgnoreCase(expectedPhoneNumber))
            expectedMsgCount = -2;
        if (expectedMsgCount == previousMsgCount + 1)
            status = true;
        else
            status = false;

        return status;
    }

    public static String getDateAndTime() {
        String DateAndTime;
        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateAndTime = sdf.format(currentTime);
        return DateAndTime;
    }

    public static boolean iSearchForSMS(String expectedPhoneNumber, String fromDateTime) throws Throwable {
        String authId = "MAMZQ1YWQWZDGYY2E5YT";
        String authToken = "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3";
        String actualToNumber;
        String msgReceivedTime;
        boolean status = false;

        MessageFactory msgFactory = getLatestInboundMessage(authId, authToken);

        actualToNumber = msgFactory.messageList.get(0).toNumber;
        msgReceivedTime = msgFactory.messageList.get(0).messageTime;
        msgReceivedTime = msgReceivedTime.substring(0, 19);

        long msgTimeDiffFromStart = getTimeDiff(fromDateTime, msgReceivedTime);
        long msgTimeDiffFromEnd = getTimeDiff(msgReceivedTime, getDateAndTime());

        Reporter.addStepLog("Expected latest toNumber : " + expectedPhoneNumber + " | Actual latest toNumber : " + actualToNumber);
        Reporter.addStepLog("Looking for messages after :" + fromDateTime + " time stamp");
        Reporter.addStepLog("Latest Message received at :" + msgReceivedTime);

        if (!(expectedPhoneNumber.equalsIgnoreCase(actualToNumber))) {
            Reporter.addStepLog("Test case failed because of ToNumber mismatch");
            status = false;
        } else if (msgTimeDiffFromStart < 0 || msgTimeDiffFromEnd < 0) {
            Reporter.addStepLog("No messages received after " + fromDateTime + " date and time");
            status = false;
        } else
            status = true;

        return status;
    }


    public static long getTimeDiff(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startDate);
            d2 = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);

        return diff;
    }
}
