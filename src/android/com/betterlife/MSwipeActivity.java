package com.betterlife;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.mswipe.wisepad.apkkit.WisePadController;
import com.mswipe.wisepad.apkkit.WisePadControllerListener;

/**
 * Created by Bitul on 16/02/17.
 */
public class MSwipeActivity extends Activity implements WisePadControllerListener {

    WisePadController mWisePadController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        String package_name = getApplication().getPackageName();
        mWisePadController = WisePadController.sharedInstance(this,this);

        WisePadController.ORIENTATION orientation = WisePadController.ORIENTATION.AUTO;
        String userName = getIntent().getStringExtra("userName");
        String password = getIntent().getStringExtra("password");
        String amount = getIntent().getStringExtra("amount");
        String requestID = getIntent().getStringExtra("requestID");
        String mobileNO = getIntent().getStringExtra("mobileNO");
        String notes = getIntent().getStringExtra("notes");
        Boolean production = getIntent().getBooleanExtra("production",false);
        mWisePadController.processCardSale(userName, password, amount, mobileNO, requestID, "", notes, "", "", "", false, production, orientation, false,false);
    }

    @Override
    public void onError(String error, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        setResult(resultCode,data);
        finish();
    }
}
