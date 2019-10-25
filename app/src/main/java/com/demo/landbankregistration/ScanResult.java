package com.demo.landbankregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.microblink.entities.recognizers.RecognizerBundle;
import com.microblink.entities.recognizers.blinkid.generic.BlinkIdRecognizer;
import com.microblink.image.Image;
import com.microblink.results.date.DateResult;

public class ScanResult extends AppCompatActivity {

    private BlinkIdRecognizer recognizer;
    private RecognizerBundle recognizerBundle;
    Intent passedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        passedData = getIntent();

        recognizer = new BlinkIdRecognizer();
        recognizerBundle = new RecognizerBundle(recognizer);

        recognizerBundle.loadFromIntent(passedData);

        BlinkIdRecognizer.Result result = recognizer.getResult();

        String CRN = result.getDocumentNumber();
        String fullname = result.getFirstName() +" "+ result.getLastName();
        String address = result.getAddress();
        String gender = result.getSex();
        DateResult bdate = result.getDateOfBirth();
        Image image = result.getFaceImage();
        ImageView imageView = findViewById(R.id.imageView);
        if(image != null)
        {
            imageView.setImageBitmap(image.convertToBitmap());
        }

        TextView crnTv = findViewById(R.id.crnTv);
        TextView fullnameTv = findViewById(R.id.fullnameTv);
        TextView addressTv = findViewById(R.id.addressTv);
        TextView genderTv = findViewById(R.id.genderTv);
        TextView bdateTv = findViewById(R.id.bdateTv);

        crnTv.setText(CRN);
        fullnameTv.setText(fullname.replaceAll("[\n\r]", " "));
        addressTv.setText(address.replaceAll("[\n\r]", " "));
        genderTv.setText(gender);
        bdateTv.setText(bdate.getOriginalDateString());
    }

    public void onRescanBtnClick(View view){
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    public void onNextBtnClick(View view){
        Intent intent = new Intent();
//        intent.putExtra("com.demo.landbankregistration.PASSED", "ok");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
