package com.mfp.shoppingList;

import com.ibm.json.java.JSONObject;

public class Item {
	public String name;
	public int quantity;
	public boolean done;
	
	public Item(){
		 this("default", 1, false);
	}
	
	public Item(String name){
		this(name , 1, false);
	}

	public Item(String name, int quantity) {
		this(name, quantity, false);
	}
	
	public Item(String name, int quantity, boolean done){
		this.name = name;
		this.quantity = quantity;
		this.done = done;
	}
	
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\", \"quantity\" : " + quantity + ", \"done\":" + done + "}";
	}
	
	public JSONObject getItemAsJSONObject(){
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("name", name);
		jsonItem.put("quantity", quantity);
		jsonItem.put("done", done);
		return jsonItem;
	}

	/*
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	*/
}
