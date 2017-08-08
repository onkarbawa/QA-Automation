package com.curbside.automation.common.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hitesh.grover on 04/08/17.
 */
public class ObjectsItem{

	@SerializedName("to_number")
	private String toNumber;

	@SerializedName("message_time")
	private String messageTime;

	@SerializedName("message_state")
	private String messageState;

	@SerializedName("total_amount")
	private String totalAmount;

	@SerializedName("from_number")
	private String fromNumber;

	@SerializedName("resource_uri")
	private String resourceUri;

	@SerializedName("total_rate")
	private String totalRate;

	@SerializedName("error_code")
	private Object errorCode;

	@SerializedName("message_type")
	private String messageType;

	@SerializedName("message_direction")
	private String messageDirection;

	@SerializedName("message_uuid")
	private String messageUuid;

	@SerializedName("units")
	private int units;

	public void setToNumber(String toNumber){
		this.toNumber = toNumber;
	}

	public String getToNumber(){
		return toNumber;
	}

	public void setMessageTime(String messageTime){
		this.messageTime = messageTime;
	}

	public String getMessageTime(){
		return messageTime;
	}

	public void setMessageState(String messageState){
		this.messageState = messageState;
	}

	public String getMessageState(){
		return messageState;
	}

	public void setTotalAmount(String totalAmount){
		this.totalAmount = totalAmount;
	}

	public String getTotalAmount(){
		return totalAmount;
	}

	public void setFromNumber(String fromNumber){
		this.fromNumber = fromNumber;
	}

	public String getFromNumber(){
		return fromNumber;
	}

	public void setResourceUri(String resourceUri){
		this.resourceUri = resourceUri;
	}

	public String getResourceUri(){
		return resourceUri;
	}

	public void setTotalRate(String totalRate){
		this.totalRate = totalRate;
	}

	public String getTotalRate(){
		return totalRate;
	}

	public void setErrorCode(Object errorCode){
		this.errorCode = errorCode;
	}

	public Object getErrorCode(){
		return errorCode;
	}

	public void setMessageType(String messageType){
		this.messageType = messageType;
	}

	public String getMessageType(){
		return messageType;
	}

	public void setMessageDirection(String messageDirection){
		this.messageDirection = messageDirection;
	}

	public String getMessageDirection(){
		return messageDirection;
	}

	public void setMessageUuid(String messageUuid){
		this.messageUuid = messageUuid;
	}

	public String getMessageUuid(){
		return messageUuid;
	}

	public void setUnits(int units){
		this.units = units;
	}

	public int getUnits(){
		return units;
	}

	@Override
 	public String toString(){
		return 
			"ObjectsItem{" + 
			"to_number = '" + toNumber + '\'' + 
			",message_time = '" + messageTime + '\'' + 
			",message_state = '" + messageState + '\'' + 
			",total_amount = '" + totalAmount + '\'' + 
			",from_number = '" + fromNumber + '\'' + 
			",resource_uri = '" + resourceUri + '\'' + 
			",total_rate = '" + totalRate + '\'' + 
			",error_code = '" + errorCode + '\'' + 
			",message_type = '" + messageType + '\'' + 
			",message_direction = '" + messageDirection + '\'' + 
			",message_uuid = '" + messageUuid + '\'' + 
			",units = '" + units + '\'' + 
			"}";
		}
}