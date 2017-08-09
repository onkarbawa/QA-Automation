package com.curbside.automation.common.utilities;

import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.message.Message;
import com.plivo.helper.api.response.message.MessageFactory;
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

    public static int getInboundMsgCount(String phoneNumber){
        int count = 0 ;
        RestAPI api = new RestAPI("MAMZQ1YWQWZDGYY2E5YT", "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3", "v1");

        final Calendar cal = Calendar.getInstance();
        final Date currentTime = cal.getTime();
//        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String GMTDate = sdf.format(currentTime);
        System.out.println("GMT time: " + GMTDate);

        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "20");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");
            parameters.put("message_time__gte", GMTDate);
            // Get count off all the messages according to phone number
            MessageFactory msgFactory = api.getMessages(parameters);

            for (Message msg : msgFactory.messageList) {

                if(msg.toNumber.equalsIgnoreCase(phoneNumber)){
                    ++count;
                }
            }
        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return count;
    }
    public static String getInboundMessageTimeStamp(String phoneNumber) {
        String latestSms = null;
        List<String> smsList = new ArrayList<String>();
        RestAPI api = new RestAPI("MAMZQ1YWQWZDGYY2E5YT", "YjQ3NjY5ZWFjZWJiM2EwNzBmYjQzNzE2YTNlM2Q3", "v1");
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "1");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");

            // Setting filter
            MessageFactory msgFactory = api.getMessages(parameters);
            for (Message msg : msgFactory.messageList) {
            if(msg.toNumber.equalsIgnoreCase(phoneNumber)){
                latestSms=    msgFactory.messageList.get(0).messageTime;
                //smsList.add(msg.messageTime);
            }

        }} catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return latestSms;
    }
}
