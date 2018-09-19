package client;

import java.util.ArrayList;

public class Request {
	private String requestType;
	private ArrayList<String> request;

	public Request(String requestType, ArrayList<String> request) {
		this.requestType = requestType;
		this.request = request;
	}
	
	public String toString() {
		String string  = "";
		string += requestType + "; ";
		for(int i = 0; i < request.size(); i++) {
			string += request.get(i);
			string += "; ";
		}
		return string;
	}
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public ArrayList<String> getRequest() {
		return request;
	}

	public void setArguments(ArrayList<String> request) {
		this.request = request;
	}
}
