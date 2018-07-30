package com.example.onur.loodosapp.FirebaseService;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.onur.loodosapp.Model.FDetail;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MyFirebaseAnalytics {

    private FirebaseAnalytics mFirebaseAnalytics;
    private Context context;

    public MyFirebaseAnalytics(Context c){
        this.context = c;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void LogEventsSave(FDetail fDetail){
        Bundle bundle = new Bundle();
        bundle.putString("title",fDetail.getTitle());
        bundle.putString("genre",fDetail.getGenre());
        bundle.putString("runtime",fDetail.getRunTime());
        bundle.putString("plot",fDetail.getPlot());
        bundle.putString("rating",fDetail.getImdbRating());
        bundle.putString("poster",fDetail.getPoster());
        mFirebaseAnalytics.logEvent("FilmDetail",bundle);
        Log.i("FirebaseAnalytics","LogEvent Save!");
    }
}
