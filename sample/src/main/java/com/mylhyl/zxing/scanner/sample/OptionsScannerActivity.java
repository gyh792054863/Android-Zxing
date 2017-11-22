package com.mylhyl.zxing.scanner.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerOptions;
import com.mylhyl.zxing.scanner.ScannerView;

public class OptionsScannerActivity extends Activity implements OnScannerCompletionListener {

    public static void gotoActivity(Activity activity) {
        activity.startActivity(new Intent(activity, OptionsScannerActivity.class));
    }

    private ScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scanner_options);

        mScannerView = (ScannerView) findViewById(R.id.scanner_view);

        mScannerView.setOnScannerCompletionListener(this);

        mScannerView.toggleLight(true);

        ScannerOptions.Builder builder = new ScannerOptions.Builder();
        builder.setFrameSize(256, 256)
                .setFrameCornerLength(22)
                .setFrameCornerWidth(2)
                .setFrameCornerColor(0xff06c1ae)
                .setFrameCornerInside(true)
                .setFrameHide(true)

                //.setLaserLineColor(0xff06c1ae)
//                .setLaserLineResId(R.mipmap.wx_scan_line)
//                .setLaserLineHeight(18)

                .setLaserGridLineResId(R.mipmap.zfb_grid_scan_line)//网格图
                .setFrameCornerColor(0xFF26CEFF)//支付宝颜色

                .setLaserLineMoveFullScreen(true)
                .setFrameHide(true)
                .setScanMode(BarcodeFormat.QR_CODE)
                .setTipText("请联系其它已添加该设备用户获取二维码")
                .setTipTextSize(19)
                .setTipTextColor(getResources().getColor(R.color.colorAccent));

        mScannerView.setScannerOptions(builder.build());
    }


    @Override
    protected void onResume() {
        mScannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.onPause();
        super.onPause();
    }

    @Override
    public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
        vibrate();
        mScannerView.restartPreviewAfterDelay(0);
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
