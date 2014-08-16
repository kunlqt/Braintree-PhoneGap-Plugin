package com.pdg.plugins.braintree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.braintreepayments.api.dropin.BraintreePaymentActivity;

public class AndroidBraintreePhoneGapPlugin extends CordovaPlugin {
	private CallbackContext callbackContext;
	private Activity activity = null;
	protected static final int REQUEST_CODE = 100;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		boolean retValue = true;
		this.callbackContext = callbackContext;
		this.activity = this.cordova.getActivity();
		if (action.equals("initWithSettings")) {
            this.init(args);
        } else if (action.equals("getCreditCardInfo")) {
        	this.getCreditCardInfo(args);
		} else if (action.equals("dismiss")) {
			this.dismiss(args);
		}
		else {
			retValue = false;
        }
        return retValue;
	 
	}

	private void dismiss(JSONArray args) {
		// TODO Auto-generated method stub
		
	}

	private void getCreditCardInfo(JSONArray args) {
		// TODO Auto-generated method stub
		
	}
	
	private void startService(String clientToken) {
		Intent intent = new Intent(this.activity, BraintreePaymentActivity.class);
		
//		Customization customization = new CustomizationBuilder()
//	    .primaryDescription("Cart")
//	    .secondaryDescription("3 Items")
//	    .amount("$35")
//	    .submitButtonText("Purchase")
//	    .build();
//		intent.putExtra(BraintreePaymentActivity.EXTRA_CUSTOMIZATION, customization);
		
		intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, clientToken);
		this.cordova.startActivityForResult(this,intent, REQUEST_CODE);	    	
	}

	private void init(JSONArray args) {
		// TODO Auto-generated method stub
		Log.i("KUNLQT", "INIT:" + args);
		cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
            	HttpClient httpclient = new DefaultHttpClient();

        	    // Prepare a request object
        	    HttpGet httpget = new HttpGet("http://192.168.1.110/Anton/Braintree/client_token.php"); 

        	    // Execute the request
        	    HttpResponse response;
        	    try {
        	        response = httpclient.execute(httpget);
        	        // Examine the response status
        	        Log.i("Praeda",response.getStatusLine().toString());

        	        // Get hold of the response entity
        	        HttpEntity entity = response.getEntity();
        	        // If the response does not enclose an entity, there is no need
        	        // to worry about connection release

        	        if (entity != null) {

        	            // A Simple JSON Response Read
        	            InputStream instream = entity.getContent();
        	            String result= convertStreamToString(instream);
        	            Log.i("KUNLQT", "CLIENT TOKEN:" + result);
        	            startService(result);
        	            // now you have the string representation of the HTML request
        	            instream.close();
        	        }
        	        
        	    } catch (Exception e) {}

            }
        });
		
		
	}
	
	private static String convertStreamToString(InputStream is) {
	    /*
	     * To convert the InputStream to String we use the BufferedReader.readLine()
	     * method. We iterate until the BufferedReader return null which means
	     * there's no more data to read. Each line will appended to a StringBuilder
	     * and returned as String.
	     */
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_CODE && resultCode == BraintreePaymentActivity.RESULT_OK) {
	        String paymentMethodNonce = data.getStringExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);
	        Log.i("KUNLQT", paymentMethodNonce);
	        this.callbackContext.success(paymentMethodNonce);
	    }else{
	    	this.callbackContext
			.error("Authorization was ok but no code");
	    }
	}
}
