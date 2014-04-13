
package com.iooly.android.topact;

import java.util.List;

import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManagerNative;
import android.content.ComponentName;
import android.os.SystemClock;

public class Main {

    public final static void main(String[] args) {

        int delay = 1000;
        if (args != null && args.length > 0) {
            try {
                delay = Integer.valueOf(args[0]);
            } catch (Exception ex) {
                delay = 1000;
            }
        }

        do {
            ComponentName topAct = getTopActivity();
            if (topAct == null) {
                System.out.println("No top activity found.");
            } else {
                System.out.println(topAct);
            }
            if (delay > 0) {
                SystemClock.sleep(delay);
            }
        } while (delay > 0);

    }

    public static ComponentName getTopActivity() {
        ComponentName pkgName = null;
        try {
            List<RunningTaskInfo> taskList = ActivityManagerNative.getDefault()
                    .getTasks(1, 0, null);

            if (!taskList.isEmpty() && taskList.size() >= 1) {
                pkgName = taskList.get(0).topActivity;
            }
        } catch (Exception e) {
        }
        return pkgName;
    }

}
