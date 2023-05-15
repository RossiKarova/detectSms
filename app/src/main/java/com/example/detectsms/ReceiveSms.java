package com.example.detectsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class ReceiveSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] messages;
            String messages_from;
            if(bundle != null){
                try {
                    Object[] pdus = (Object[])  bundle.get("pdus");
                    messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < messages.length; i ++){
                        messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        messages_from = messages[i].getOriginatingAddress();
                        String messageBody = messages[i].getMessageBody();

                        Toast.makeText(context, "From: " + messages_from + ", Body: " + messageBody, Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
