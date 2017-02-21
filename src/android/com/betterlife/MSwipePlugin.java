/**
 */
package com.betterlife;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mswipe.wisepad.apkkit.WisePadController;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MSwipePlugin extends CordovaPlugin {

    private static final String TAG = "MSwipePlugin";

    private Context context;
    private CallbackContext callbackContext;
    private CordovaInterface cordova;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.cordova = cordova;
        context = cordova.getActivity().getApplicationContext();
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing MSwipePlugin");
    }

    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {

        this.callbackContext = callbackContext;

        if (action.equals("cardPayment")) {

            JSONObject arg_object;
            try {
                arg_object = data.getJSONObject(0);
                String amount = arg_object.getString("amount");
                String requestID = arg_object.getString("orderid");
                String userName = arg_object.getString("username");
                String password = arg_object.getString("password");
                String mobileNO = arg_object.getString("mobilenumber");
                String notes = arg_object.getString("appuser");
                Boolean production = arg_object.getBoolean("productionCheck");
                openMswipeActivity(context, userName, password, amount, requestID, mobileNO, notes, production);
            } catch (JSONException e) {
                Log.d(TAG,"JSONException", e);
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
    public void openMswipeActivity(Context context,String userName,String password,String amount,String requestID,String mobileNO,String notes,Boolean production){
        Intent intent = new Intent(context,MSwipeActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);
        intent.putExtra("amount", amount);
        intent.putExtra("requestID", requestID);
        intent.putExtra("mobileNO", mobileNO);
        intent.putExtra("notes", notes);
        intent.putExtra("production",production);
        this.cordova.startActivityForResult(this,intent,WisePadController.MS_CARDSALE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        Log.d(TAG,">>>>>>>entered in Activity Result");

        if (resultCode == RESULT_OK && requestCode == WisePadController.MS_CARDSALE_ACTIVITY_REQUEST_CODE) {
            String RRno = data.getExtras().getString("RRNo");
            String authCode = data.getExtras().getString("AuthCode");
            String tvr = data.getExtras().getString("TVR");
            String tsi = data.getExtras().getString("TSI");
            String receiptDetail = data.getExtras().getString("receiptDetail");
            if(data.hasExtra("signatureUpdateStatus")){
                if(data.getExtras().getBoolean("signatureUpdateStatus")){

                }else{

                }
            }else{
                String receiptDetails = data.getExtras().getString("receiptDetail");
            }
            JSONObject resultObj = new JSONObject();
            try {
                resultObj.put("RRno", RRno);
                resultObj.put("authCode", authCode);
                resultObj.put("tvr", tvr);
                resultObj.put("tsi", tsi);
                resultObj.put("receiptDetail", receiptDetail);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d(TAG,"JSONException result", e);
            }

            this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, resultObj));

        }else if(resultCode == RESULT_CANCELED){

            if(data!= null && data.hasExtra("errMsg")){
                String errorMsg = data.getExtras().getString("errMsg");
            }
        }
    }
}
