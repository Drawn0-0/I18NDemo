package com.drawnfor.i18ndemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.drawnfor.i18ndemo.R;
import com.drawnfor.i18ndemo.base.BaseService;
import com.drawnfor.i18nlibrary.I18NUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyService extends BaseService {

    private static final String ChannelID = NotifyService.class.getName() + ".ChannelID";
    private NotificationManager mNoticeManager;
    private TimerTask task;
    private Timer timer;

    private MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    public void sendNotify(){
        task = new TimerTask() {
            int count;

            @Override
            public void run() {
                if (count <= 15) {
                    SimpleDateFormat format = new SimpleDateFormat("mm - ss ", I18NUtil.getInstance().getSysLocal());
                    setForeGroundNotify(format.format(new Date()));
                    count++;
                } else {
                    removeNotify();
                    stopSelf();
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 1000, 1000);
    }

    private void setForeGroundNotify(String message) {
        if (mNoticeManager == null) {
            mNoticeManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ChannelID, "Notify", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(null, null);
            mNoticeManager.createNotificationChannel(channel);
        }

        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle(getString(R.string.notify_title))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentText(getString(R.string.date_templet, message))
                .setWhen(System.currentTimeMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(ChannelID);
        }


        startForeground(2, builder.build());
    }

    public void removeNotify(){
        if (task!=null){
            task.cancel();
            task = null;
        }

        if (timer!=null){
            timer.cancel();
            timer = null;
        }
        stopForeground(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeNotify();
    }

    public class MyBinder extends Binder{
        public NotifyService getService(){
            return NotifyService.this;
        }
    }

}
