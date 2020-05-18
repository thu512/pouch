package com.jinjoo.pouch.activity;

import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

public class Activity extends AppCompatActivity {
    ProgressDialog pd;
    public void showPd(){
        if(pd == null){
            pd = new ProgressDialog(this);
            pd.setCancelable(true);
            pd.setMessage("잠시만 기다려주세요...");
        }
        pd.show();
    }

    public void stopPd(){
        if(pd != null && pd.isShowing()){
            pd.dismiss();
        }
    }
}
