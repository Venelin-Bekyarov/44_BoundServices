package com.thenewboston.a44_boundservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.ServiceConnection;

import com.thenewboston.a44_boundservices.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyService vensService;
    boolean isBound = false;

    public void showTime(View view) {
        String currentTime = vensService.getCurrentTime();
        TextView vensText = (TextView) findViewById(R.id.vensText);
        vensText.setText(currentTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, MyService.class);
        bindService(i, vensConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection vensConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            vensService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

}