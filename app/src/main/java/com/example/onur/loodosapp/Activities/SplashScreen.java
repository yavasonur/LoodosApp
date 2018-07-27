package com.example.onur.loodosapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.onur.loodosapp.Config.MyClass;
import com.example.onur.loodosapp.R;

public class SplashScreen extends Activity {

    private MyClass myClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        myClass = new MyClass(this);

        if(!myClass.InternetControl()){
            Intent i = new Intent(this,WarningActivity.class);
            startActivity(i);
            finish();
        }
        else{

        }
    }
}
