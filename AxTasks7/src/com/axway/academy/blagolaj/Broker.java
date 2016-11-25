package com.axway.academy.blagolaj;

import java.util.ArrayList;
import java.util.List;

public class Broker {

	private List<Request> requestList = new ArrayList<>();

	public void takeRequest(Request request) {
		requestList.add(request);
	}

	public void placeRequests() {
		for (Request request : requestList) {
			request.execute();
		}
		requestList.clear();
	}
}
