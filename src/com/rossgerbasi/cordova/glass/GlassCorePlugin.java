package com.rossgerbasi.cordova.glass;

//import android.util.Log;
import android.speech.RecognizerIntent;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class GlassCorePlugin extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
//        Log.d("GlassTouchPlugin", "Init Glass Core Plugin");
        super.initialize(cordova, webView);
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
