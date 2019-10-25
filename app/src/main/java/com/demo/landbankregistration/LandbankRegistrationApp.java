package com.demo.landbankregistration;

import android.app.Application;

import com.microblink.MicroblinkSDK;
import com.microblink.intent.IntentDataTransferMode;

public class LandbankRegistrationApp extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        MicroblinkSDK.setLicenseFile("MB_com.demo.landbankregistration_BlinkID_Android_2019-11-18.mblic", this);
        MicroblinkSDK.setIntentDataTransferMode(IntentDataTransferMode.PERSISTED_OPTIMISED);
    }
}
