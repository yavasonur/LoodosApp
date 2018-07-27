package com.example.onur.loodosapp.Config;

import android.content.Context;
import android.net.ConnectivityManager;

public class MyClass {

    private Context context;

    public MyClass(Context c){
        this.context = c;
    }

    public boolean InternetControl(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() &&
                connectivityManager.getActiveNetworkInfo().isConnected()){
            return true;
        }else{
            return  false;
        }
    }

}
