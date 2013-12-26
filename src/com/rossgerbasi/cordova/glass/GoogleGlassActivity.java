package com.rossgerbasi.cordova.glass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.apache.cordova.*;

public class GoogleGlassActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = this.getPackageManager().getLaunchIntentForPackage(this.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(intent);
    }
}