package com.example.demo.common;

public class JsonMessage<T> {
	private int status;
	private T data;
	public JsonMessage(int status, T data) {
		this.status = status;
		this.data = data;
	}
	
	
	public JsonMessage(int status) {
		this.status = status;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
