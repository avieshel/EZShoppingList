package com.mfp.shoppingList;

import java.util.Collection;

import com.ibm.json.java.JSONArray;

public class ShoppingList {
	private String title;
	private String username;
	private Collection<Item> items;	
	
	public ShoppingList(){
		this("_defualt", "_deamon", null);
	}
	
	
	public ShoppingList(String title, String username, Collection<Item> items ) {
		this.title = title;
		this.username = username;
		this.items = items;	
	}
	
	
	public Collection<Item> getItems() {
		return items;
	}
	
	public JSONArray getItemsAsJSONArray(){
		JSONArray jsonArray = new JSONArray();
		for (Item item : items) {
			jsonArray.add(item.getItemAsJSONObject());
		}
		return jsonArray;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	
	
	public String getTitle() {
		return title;
	}


	public String getUsername() {
		return username;
	}
	
	
	
}
