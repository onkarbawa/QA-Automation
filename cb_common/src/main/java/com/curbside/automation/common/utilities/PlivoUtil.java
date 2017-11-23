package com.curbside.automation.common.utilities;

import com.cucumber.listener.Reporter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.message.Message;
import com.plivo.helper.api.response.message.MessageFactory;
import com.plivo.helper.api.response.message.MessageMeta;
import com.plivo.helper.exception.PlivoException;

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


    public String getLatestInboundMessage(String authId, String authToken) {
        String latestSms = null;
        RestAPI api = new RestAPI(authId, authToken, "v1");
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "1");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");

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

    public static String getLatestMsgToNumber(String authId, String authToken) {
        String msgToNumber = null;
        MessageFactory msgFactory;
        RestAPI api = new RestAPI(authId, authToken, "v1");
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "1");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");
            parameters.put("message_time__gte", getDateAndTime());

            // Setting filter
            try {
                msgFactory = api.getMessages(parameters);
                msgToNumber = msgFactory.messageList.get(0).toNumber;
            } catch (Exception e) {
                parameters.remove("message_time__gte");
                parameters.put("message_time_gte", getDateAndTime());
                msgFactory = api.getMessages(parameters);
                msgToNumber = msgFactory.messageList.get(0).toNumber;
            }

        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return msgToNumber;
    }


    public static int getInboundMsgCount(String authId, String authToken) {
        int total_count = 0;
        String response;
        JsonObject convertedObject;
        RestAPI api = new RestAPI(authId, authToken, "v1");

        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "20");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");
            parameters.put("message_time__gte", getDateAndTime());

            try {
                response = api.request("GET", "/Message/", parameters);
                convertedObject = new Gson().fromJson(response, JsonObject.class);
                total_count = Integer.parseInt(convertedObject.get("meta")
                        .getAsJsonObject()
                        .get("total_count"
                        ).toString());
            } catch (Exception e) {
                parameters.remove("message_time__gte");
                parameters.put("message_time_gte", getDateAndTime());
                response = api.request("GET", "/Message/", parameters);
                convertedObject = new Gson().fromJson(response, JsonObject.class);
                total_count = Integer.parseInt(convertedObject.get("meta")
                        .getAsJsonObject()
                        .get("total_count"
                        ).toString());
            }

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
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateAndTime = sdf.format(currentTime);
        return DateAndTime;
    }

}
