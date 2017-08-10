package com.curbside.automation.common.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.plivo.helper.api.client.RestAPI;
import com.plivo.helper.api.response.message.Message;
import com.plivo.helper.api.response.message.MessageFactory;
import com.plivo.helper.api.response.message.MessageMeta;
import com.plivo.helper.exception.PlivoException;

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
        RestAPI api = new RestAPI(authId, authToken, "v1");
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "1");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");

            // Setting filter
            MessageFactory msgFactory = api.getMessages(parameters);
            msgToNumber = msgFactory.messageList.get(0).toNumber;

        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return msgToNumber;
    }


    public static int getInboundMsgCount(String authId, String authToken){
        int total_count = 0 ;
        RestAPI api = new RestAPI(authId, authToken, "v1");

        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
            parameters.put("limit", "20");
            parameters.put("offset", "0");
            parameters.put("message_state", "received");
            parameters.put("message_direction", "inbound");

            String response = api.request("GET", "/Message/", parameters);
            JsonObject convertedObject = new Gson().fromJson(response, JsonObject.class);
            total_count = Integer.parseInt(convertedObject.get("meta")
                    .getAsJsonObject()
                    .get("total_count"
                    ).toString());

        } catch (PlivoException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return total_count;
    }

    public static boolean isSmsReceived(String authId, String authToken, String expectedPhoneNumber, int previousMsgCount){
        int total_count;
        String actualToNumber;
        boolean status;

        total_count = getInboundMsgCount(authId, authToken);
        actualToNumber = getLatestMsgToNumber(authId, authToken);
        if(!actualToNumber.equalsIgnoreCase(expectedPhoneNumber))
            total_count = -2;
        if(total_count == previousMsgCount+1)
            status = true;
        else
            status = false;


        return status;
    }

}
