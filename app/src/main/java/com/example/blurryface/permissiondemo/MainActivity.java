package com.example.blurryface.permissiondemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String TAG = "PermissionDemo";
    private static final int RECORD_REQUEST_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checks for versions below android 6.0
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        if(permission== PackageManager.PERMISSION_DENIED)
            Log.i(TAG,"Permission to record has been denied");
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECORD_AUDIO))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("The application requires this permission").setTitle("Permission Title");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG,"clicked");
                    makeRequest();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else
            makeRequest();
    }
    //checkPermission for android
    public void makeRequest()
    {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RECORD_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case RECORD_REQUEST_CODE:
                if(grantResults.length==0||grantResults[0]==PackageManager.PERMISSION_DENIED)
                    Log.i(TAG,"Permission to record has been denied");
                else
                    Log.i(TAG,"Permission to record has been accepted");
                break;
        }
    }
}
