##CordovaGlassCore##
---

This plugin modifies the Android platform in your Cordova based application with Google Glass Compatibility. At its most basic usage it will allow you to launch your application via a voice trigger. Advanced usage allows for voice prompts to be captured during the launch process and sent into the application.


###Basic Usage###

Add plugin via Node

`cordova plugin add https://github.com/aphex/cordova-glass-core`

Modify Voice Trigger located in **{app}/platforms/android/res/values/strings.xml**

`<string name="app_launch_voice_trigger">hello cordova</string>`


###Voice Trigger Prompt###

A Voice Trigger prompt allows your application to ask the user for voice input before the application launches.  An example of what this might sound like could be 

- 'Ok Glass' (wake up glass for voice input)
- 'Open movie search' (launch your movie search application)
- 'Goodfellas' (**capture a voice trigger prompt from the user**)

To use first uncomment the following line from **{app}/platforms/android/res/xml/app_launch_voice_trigger.xml**

` <input prompt="@string/app_launch_voice_prompt"/>`

Then modify Voice Trigger Prompt located in **{app}/platforms/android/res/values/strings.xml**

`<string name="app_launch_voice_prompt">prompt question</string>`

After your application has received a 'deviceready' event you will be able to retrieve the input from the user as shown in the follow where 'results' is a array.

```
com.rossgerbasi.cordova.glass.core.getLaunchParams(
	function(results) {
		console.log(results);
	},
	function () {
		console.log("Error getting launch Params");
	}
);
```