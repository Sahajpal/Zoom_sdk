package com.ryms.zoom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.net.MalformedURLException;
import java.net.URL;

import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.StartMeetingOptions;
import us.zoom.sdk.ZoomApiError;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button join = findViewById(R.id.join_button);

        initializeSdk(this);
        join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                joinMeeting(MainActivity.this);
            }
        });
    }


    public void initializeSdk(Context context) {
        ZoomSDK sdk = ZoomSDK.getInstance();
        ZoomSDKInitParams params = new ZoomSDKInitParams();
        params.appKey = "";/* Your sdk key here */
        params.appSecret = "";/* Your secret key here */
        params.domain = "zoom.us";
        params.enableLog = true;
        ZoomSDKInitializeListener listener = new ZoomSDKInitializeListener() {
            @Override
            public void onZoomSDKInitializeResult(int errorCode, int internalErrorCode) { }

            @Override
            public void onZoomAuthIdentityExpired() { }
        };
        sdk.initialize(context, listener, params);
    }

    private void joinMeeting(Context context) {
        // TODO: Integrate with API(hardcode)
        URL url = null;
        try {
            url = new URL("");/* Meeting url here */
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String[] meetingNumber = url.getPath().split("/j/");
        String[] password = url.getQuery().split("pwd=");
        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
        JoinMeetingOptions options = new JoinMeetingOptions();
        JoinMeetingParams params = new JoinMeetingParams();
        params.displayName = "Sahaj"; // TODO: Enter your name
        params.meetingNo = meetingNumber[1];
        params.password = password[1];
        meetingService.joinMeetingWithParams(context, params, options);
    }
}