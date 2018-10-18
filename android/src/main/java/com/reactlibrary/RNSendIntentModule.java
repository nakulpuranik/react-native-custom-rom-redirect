
package com.reactlibrary;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class RNSendIntentModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Promise mPromise;

  public RNSendIntentModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNSendIntent";
  }

  /**
   * Return the intent according to the device manufacturer name
   * with valid CmponentName set to it.
   * @param manufacturerName string
   * @return  Intent 
   * @throws Exception Manufacturer not supported
   */
  private Intent getAndroidComponentName(String manufacturerName) throws Exception{
    manufacturerName = manufacturerName.toLowerCase();
    Intent intent = new Intent();
    switch(manufacturerName){
        case "xiaomi" :
          //TOOD need to handle the navigation if Device is Mi A1 ?
          intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
          break;
        case "oppo":
          intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
          break;
        case "vivo":
        intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
          break;
        case "letv":
        intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
          break;
        case "honor":
          intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
          break;
          default:
          throw new Exception("Either it is stock or manufacturer Not Supported");
    }
    return intent;
  }

  @ReactMethod
  public void openSettingsAutoStart(Promise promise){
    
    try{
      mPromise = promise;
      String manufacturer = android.os.Build.MANUFACTURER;
      Intent intent = getAndroidComponentName(manufacturer);
      
      if(intent != null){
        List<ResolveInfo> list = getReactApplicationContext().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if  (list.size() > 0) {
          mPromise.resolve(true);
          getReactApplicationContext().startActivity(intent);
        }
      }
  }
  catch(Exception ex){
    ex.printStackTrace();
    Log.e(this.getName() , String.valueOf(ex));
    mPromise.reject("Error:",ex);
  }

  }
}