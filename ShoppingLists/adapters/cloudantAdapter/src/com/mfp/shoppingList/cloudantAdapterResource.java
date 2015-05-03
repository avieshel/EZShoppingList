/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
*/

package com.mfp.shoppingList;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.worklight.adapters.rest.api.AdaptersAPI;
import com.worklight.adapters.rest.api.MFPServerOAuthException;
import com.worklight.adapters.rest.api.WLServerAPI;
import com.worklight.adapters.rest.api.WLServerAPIProvider;

@Path("/shoppinglist")
public class cloudantAdapterResource {
	/*
	 * For more info on JAX-RS see https://jsr311.java.net/nonav/releases/1.1/index.html
	 */
		
	//Define logger (Standard java.util.Logger)
	static Logger logger = Logger.getLogger(cloudantAdapterResource.class.getName());

    //Define the server api to be able to perform server operations
    WLServerAPI api = WLServerAPIProvider.getWLServerAPI();

	
    
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public JSONObject updateList(ShoppingList lst) throws IOException{
    	JSONObject shoppingList = getShopppingList(lst.getUsername(), lst.getTitle());
    	JSONObject updatedDoc = new JSONObject();
    	
    	updatedDoc.put("_id", shoppingList.get("_id") );
    	updatedDoc.put("_rev", shoppingList.get("_rev") );
    	updatedDoc.put("created by", shoppingList.get("created by") );
    	updatedDoc.put("items", lst.getItemsAsJSONArray() ) ;
    	//shoppingList.put("items", "milk");
    	AdaptersAPI adaptersApi = api.getAdaptersAPI();
    	String[] args = {"newdb", updatedDoc.get("_id").toString(), updatedDoc.toString() };
    	HttpUriRequest req = adaptersApi.createJavascriptAdapterRequest("cloudant", "updateDoc", args);
    	HttpResponse res = null;
    	try{
    		res = adaptersApi.executeAdapterRequest(req);
    	}catch(MFPServerOAuthException | IOException e){
    		e.printStackTrace();
    	}
    	return adaptersApi.getResponseAsJSON(res);
    }
    
    @POST
    @Consumes("application/json")
	@Produces("application/json")
	public JSONObject createList(ShoppingList lst) throws IOException{
		JSONObject doc = new JSONObject();
		doc.put("_id", lst.getTitle() + lst.getUsername());
		doc.put("created by", lst.getUsername());
		doc.put("items", "[]");
		
		AdaptersAPI adaptersApi = api.getAdaptersAPI();
		String[] args = {"newdb" ,  doc.toString() };
		HttpUriRequest req = adaptersApi.createJavascriptAdapterRequest("cloudant", "createDoc", args );
		HttpResponse res = null;
		try {
			res = adaptersApi.executeAdapterRequest(req);
		} catch (MFPServerOAuthException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adaptersApi.getResponseAsJSON(res);
	}
	
	@GET
	@Produces("application/json")
	public JSONObject getShopppingList(@QueryParam("username")String username, @QueryParam("title") String title) throws IOException{
		AdaptersAPI adaptersApi = api.getAdaptersAPI();
		String [] args = {"newdb" , title+username };
		HttpUriRequest req = adaptersApi.createJavascriptAdapterRequest("cloudant", "getDoc", args);
		HttpResponse res = null;
		try{
			res = adaptersApi.executeAdapterRequest(req);
		}catch (MFPServerOAuthException | IOException e){
			e.printStackTrace();
		}
		return adaptersApi.getResponseAsJSON(res);
	}
	
}
