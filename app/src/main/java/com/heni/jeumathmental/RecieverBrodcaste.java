package com.heni.jeumathmental;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RecieverBrodcaste extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notificationId")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(context.getString(R.string.Notif_name))
                    .setContentText(context.getString(R.string.Notif_description))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(200, builder.build());

    }
}
