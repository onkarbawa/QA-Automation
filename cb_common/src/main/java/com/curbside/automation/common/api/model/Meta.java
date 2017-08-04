package com.curbside.automation.common.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hitesh.grover on 04/08/17.
 */
public class Meta{

	@SerializedName("next")
	private Object next;

	@SerializedName("offset")
	private int offset;

	@SerializedName("previous")
	private Object previous;

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("limit")
	private int limit;

	public void setNext(Object next){
		this.next = next;
	}

	public Object getNext(){
		return next;
	}

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setPrevious(Object previous){
		this.previous = previous;
	}

	public Object getPrevious(){
		return previous;
	}

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"next = '" + next + '\'' + 
			",offset = '" + offset + '\'' + 
			",previous = '" + previous + '\'' + 
			",total_count = '" + totalCount + '\'' + 
			",limit = '" + limit + '\'' + 
			"}";
		}
}