package com.rossgerbasi.cordova.glass;

//import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.speech.RecognizerIntent;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class GlassCorePlugin extends CordovaPlugin implements View.OnGenericMotionListener {
    private ArrayList<View.OnGenericMotionListener> listeners;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
//        Log.d("GlassCorePlugin", "Init Glass Core Plugin");
        super.initialize(cordova, webView);

        //Test for Keep Awake Preference
        String keepAwake = webView.getProperty("com.rossgerbasi.cordova.glass.core.keepAwake", "false");
        if(keepAwake.equals("true")) {
            cordova.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        this.listeners = new ArrayList<View.OnGenericMotionListener>();
        this.webView.setOnGenericMotionListener(this);
    }

    @Override
    public Object onMessage(String id, Object data) {
        if(id.equals("setMotionListener") && data instanceof View.OnGenericMotionListener) {
            listeners.add((View.OnGenericMotionListener) data);
        }

        return super.onMessage(id, data);
    }

    @Override
    public boolean onGenericMotion(View v, MotionEvent event) {
        for(View.OnGenericMotionListener l : listeners){
            l.onGenericMotion(v, event);
        }
        return true;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("get_launch_params")) {
            ArrayList<String> voiceResults = this.cordova.getActivity().getIntent().getExtras().getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
            JSONArray jsArray;
            if (voiceResults != null) {
                jsArray = new JSONArray(voiceResults);
            } else {
                jsArray = new JSONArray();
            }
            PluginResult result = new PluginResult(PluginResult.Status.OK, jsArray);
            callbackContext.sendPluginResult(result);
        }

        return super.execute(action, args, callbackContext);
    }
}
