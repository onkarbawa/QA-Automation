package com.curbside.automation.common.api.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hitesh.grover on 04/08/17.
 */
public class Response{

	@SerializedName("api_id")
	private String apiId;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("objects")
	private List<ObjectsItem> objects;

	public void setApiId(String apiId){
		this.apiId = apiId;
	}

	public String getApiId(){
		return apiId;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setObjects(List<ObjectsItem> objects){
		this.objects = objects;
	}

	public List<ObjectsItem> getObjects(){
		return objects;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"api_id = '" + apiId + '\'' + 
			",meta = '" + meta + '\'' + 
			",objects = '" + objects + '\'' + 
			"}";
		}
}