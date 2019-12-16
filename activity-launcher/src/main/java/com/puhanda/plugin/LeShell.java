package com.puhanda.plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

import android.os.Handler;

public class LeShell {

    public static void Login() {
        Log.w("Unity", "乐插件开始调用Login!");
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    final ActivityLauncher launcher = ActivityLauncher.init(UnityPlayer.currentActivity);
                    final Intent mIntent = new Intent();
                    ComponentName comp = new ComponentName("com.lj.ljshell", "com.lj.ljshell.ljshell");
                    mIntent.setComponent(comp);
                    mIntent.putExtra("CmdLine", "ljshell://ljauth?action=login&appid=1248&ResCode=100");
                    Log.e("Unity", "Plugin Login, launcher.startActivityForResult");
                    launcher.startActivityForResult(mIntent, new ActivityLauncher.Callback() {
                        @Override
                        public void onActivityResult(int resultCode, Intent data) {
                            Log.w("Unity", "乐插件登录回调, resultCode: " + resultCode);
                            switch (resultCode) {
                                case -1:
                                    int nResCode = data.getIntExtra("Result", 0);//返回实际返回码
                                    String str = data.getStringExtra("Param");//返回参数
                                    UnityPlayer.UnitySendMessage("LeJiaoLeXueManager", "OnLoginCallback", str);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                } catch (Exception ex) {
                    Log.e("Unity", ex.getMessage() + "\n");
                }
            }
        });
    }

    public static void Order(final String orderID) {
        Log.w("Unity", "乐插件开始调用Order!");
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("Unity", "Plugin Order, launcher.startActivityForResult");
                    final ActivityLauncher launcher = ActivityLauncher.init(UnityPlayer.currentActivity);
                    final Intent mIntent = new Intent();
                    ComponentName comp = new ComponentName("com.lj.ljshell", "com.lj.ljshell.ljshell");
                    mIntent.setComponent(comp);
                    mIntent.putExtra("CmdLine", "ljshell://ljPay?orderno=" + orderID + "&action=payorder");
                    launcher.startActivityForResult(mIntent, new ActivityLauncher.Callback() {
                        @Override
                        public void onActivityResult(int resultCode, Intent data) {
                            Log.w("Unity", "乐插件登录回调, resultCode: " + resultCode);
                            switch (resultCode) {
                                case -1:
                                    int nResCode = data.getIntExtra("Result", 0);//返回实际返回码
                                    String str = data.getStringExtra("Param");//返回参数
                                    UnityPlayer.UnitySendMessage("LeJiaoLeXueManager", "OnOrderCallback", str);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                } catch (Exception ex) {
                    Log.e("Unity", ex.getMessage() + "\n");
                }
            }
        });
    }
}
