package com.example.boxyz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Util {

    public static Properties getProperties() {
        Context context = MainActivity.getInstence().getApplicationContext();
        System.out.println(context);
        Resources resources = context.getResources();
        AssetManager assetManager = resources.getAssets();

        // Read from the /assets directory
        try {/*from  ww  w  .  j  a v a 2  s.  c o m*/
            InputStream inputStream = assetManager.open("config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            Log.i("Boxyz", "no config.properties available");
            return null;
        }
    }

    public static String findSSIDForWifiInfo(WifiManager manager, WifiInfo wifiInfo) {

        @SuppressLint("MissingPermission") List<WifiConfiguration> listOfConfigurations = manager.getConfiguredNetworks();

        for (int index = 0; index < listOfConfigurations.size(); index++) {
            WifiConfiguration configuration = listOfConfigurations.get(index);
            if (configuration.networkId == wifiInfo.getNetworkId()) {
                return configuration.SSID;
            }
        }

        return null;
    }
}
