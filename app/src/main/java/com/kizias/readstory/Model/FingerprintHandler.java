package com.kizias.readstory.Model;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.kizias.readstory.R;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;

    public FingerprintHandler(Context context){
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("Xảy ra lỗi xác thực " + errString, false);

    }

    @Override
    public void onAuthenticationFailed() {

        this.update("Mở khoá thất bại. ", false);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Lỗi: " + helpString, false);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        this.update("Mở khoá thành công", true);

    }

    private void update(String s, boolean b) {

        TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.paraLabel);
        paraLabel.setText(s);

        if(b == false){

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {
            //Thanh cong
//            paraLabel.setTextColor(Integer.parseInt("#FF4081"));
//            imageView.setImageResource(R.mipmap.action_done);

        }

    }
}
