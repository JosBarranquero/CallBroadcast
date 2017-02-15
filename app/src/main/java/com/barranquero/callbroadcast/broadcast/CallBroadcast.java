package com.barranquero.callbroadcast.broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.telephony.TelephonyManager;

import com.barranquero.callbroadcast.CallActivity;
import com.barranquero.callbroadcast.R;

/**
 * Created by usuario on 15/02/17
 * CallBroadcast
 */
public class CallBroadcast extends BroadcastReceiver {
    private static final int CALLNOTIFICATION = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Intent newIntent = new Intent(context, CallActivity.class);
                intent.putExtra("number", number);

                PendingIntent pendingIntent = PendingIntent.getActivity(context, CALLNOTIFICATION, newIntent, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle(context.getString(R.string.app_name));
                builder.setContentText("Call from: " + number);
                builder.setSmallIcon(android.R.drawable.ic_menu_call);

                // Extra parameters
                builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
                builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);
                builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                // Adding the Pending Intent to the notification
                builder.setContentIntent(pendingIntent);
                // Add notification to NotificationManager
                NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(CALLNOTIFICATION, builder.build());
            }
        }
    }
}