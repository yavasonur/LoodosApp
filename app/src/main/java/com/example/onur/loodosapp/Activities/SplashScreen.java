package com.example.onur.loodosapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onur.loodosapp.Config.MyClass;
import com.example.onur.loodosapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends Activity {

    private MyClass myClass;
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    @BindView(R.id.txtConfig) TextView txtConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        myClass = new MyClass(this);

        if(!myClass.InternetControl()){
            Intent i = new Intent(this,WarningActivity.class);
            startActivity(i);
            finish();
        }
        else{

            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

            long cacheExpiration = 0;

            mFirebaseRemoteConfig.fetch(cacheExpiration)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mFirebaseRemoteConfig.activateFetched();
                            } else {
                                Toast.makeText(SplashScreen.this, "Fetch Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                            displayWelcomeMessage();
                        }
                    });
        }
    }

    private void displayWelcomeMessage() {
        txtConfig.setText(mFirebaseRemoteConfig.getString("loodos"));

        Thread t1 = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);

                }catch (Exception e) {

                }finally {
                    finish();
                }
            }
        };
        t1.start();

    }
}
