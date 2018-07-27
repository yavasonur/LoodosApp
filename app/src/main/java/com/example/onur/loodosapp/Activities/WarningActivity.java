package com.example.onur.loodosapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.onur.loodosapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WarningActivity extends AppCompatActivity {

    @BindView(R.id.btnOK) Button btnOk;
    @BindView(R.id.btnCancel) Button btnCancel;

    @OnClick(R.id.btnOK) void btnOkSubmit(){
        Intent i = new Intent(this,SplashScreen.class);
        startActivity(i);
    }
    @OnClick(R.id.btnCancel) void btnCancelSubmit(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        ButterKnife.bind(this);
    }
}
