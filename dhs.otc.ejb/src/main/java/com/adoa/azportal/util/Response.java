package com.adoa.azportal.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import com.adoa.azportal.util.PooledMessage;

@RequestScoped
public class Response {
	
	
	private String redirectTo;
	
	
	private List<PooledMessage> messages=new ArrayList<>();
	
	private Object data;
	
	
	public void setMesages(List<PooledMessage> messages) {
		this.messages = messages;
	}
	
	public List<PooledMessage> getMessages() {
		return messages;
	}
	
	
	public void addMessage(Severity severity,String message){
		messages.add(new PooledMessage(severity,message));

	}
	
	
	
	public void clear(){
		messages.clear();
	}
	
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

	public String getRedirectTo() {
		return redirectTo;
	}

	public void setRedirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}

}
