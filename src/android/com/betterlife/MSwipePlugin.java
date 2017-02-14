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

    import android.app.AlertDialog;
    import android.content.Intent;
    import android.util.Log;

    import com.mswipe.wisepad.apkkit.WisePadController;
    import com.mswipe.wisepad.apkkit.WisePadControllerListener;

    import java.util.Date;

    import static android.app.Activity.RESULT_CANCELED;
    import static android.app.Activity.RESULT_OK;

    public class MSwipePlugin extends CordovaPlugin implements WisePadControllerListener {
        WisePadController mWisePadController;
        private static final String TAG = "MSwipePlugin";

      public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing MSwipePlugin");
          mWisePadController = WisePadController.sharedInstance(this.cordova.getActivity(),this);
      }

      public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if(action.equals("echo")) {
          String phrase = args.getString(0);
          // Echo back the first argument
          Log.d(TAG, phrase);
        } else if(action.equals("getDate")) {
          // An example of returning data back to the web layer
          final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
          callbackContext.sendPluginResult(result);
        } else if(action.equals("cardSale")){
            //
        }
        return true;
      }

        @Override
        public void onError(String error, int errorCode) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.cordova.getActivity());
            builder.setTitle("Mswipe APKKit");
            builder.setMessage(error);
            builder.setPositiveButton("ok", null);
            builder.create().show();
        }

        @Override
        public void onMswipeAppInstalled() {

        }

        @Override
        public void onMswipeAppUpdated() {

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

                if(data.hasExtra("signatureUpdateStatus")){
                    if(data.getExtras().getBoolean("signatureUpdateStatus")){

                    }else{

                    }
                }else{
                    String receiptDetails = data.getExtras().getString("receiptDetail");
                }
            }else if(resultCode == RESULT_CANCELED){

                if(data!= null && data.hasExtra("errMsg")){
                    String errorMsg = data.getExtras().getString("errMsg");
                }
            }
        }
    }
