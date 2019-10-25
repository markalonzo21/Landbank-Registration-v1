package com.demo.landbankregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.microblink.entities.recognizers.RecognizerBundle;
import com.microblink.entities.recognizers.blinkid.generic.BlinkIdRecognizer;
import com.microblink.uisettings.ActivityRunner;
import com.microblink.uisettings.BlinkIdUISettings;

import java.util.ArrayList;

public class ScanDocumentsActivity extends AppCompatActivity implements CardsAdapter.OnItemClickListener {

    public static final int MY_BLINKID_REQUEST_CODE = 123;
    private static final String TAG = "scandocu";
    private ArrayList<String> items;
    private BlinkIdRecognizer recognizer;
    private RecognizerBundle recognizerBundle;
    private CardsAdapter cardsAdapter;
    ImageView iv;
    Intent data;
    Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_documents);

        //initialize recognizer
        recognizer = new BlinkIdRecognizer();
        recognizer.setReturnFaceImage(true);
        recognizerBundle = new RecognizerBundle(recognizer);

        data = getIntent();
        items = data.getStringArrayListExtra("com.demo.landbankregistration.IDS");
        RecyclerView mrecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardsAdapter = new CardsAdapter(this, items, this);

        mrecyclerView.setAdapter(cardsAdapter);

        scanBtn = (Button) findViewById(R.id.scanBtn);
    }

    @Override
    public void onItemClick(int i, View view) {
        iv = (ImageView) view.findViewById(R.id.checkImage);
        openScanner();
    }

    public void onStartScanBtnClick(View view){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, FaceRecognition.class));
//        finish();
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_BLINKID_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                onScanSuccess(data);
            }else{
                Toast.makeText(this, "Scan cancelled!", Toast.LENGTH_SHORT).show();
//            finish();
            }
        }
        else if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                scanBtn.setVisibility(View.VISIBLE);
            }
            else if(resultCode == Activity.RESULT_CANCELED){
                openScanner();
            }
        }


    }

    public void openScanner(){
        BlinkIdUISettings uiSettings = new BlinkIdUISettings(recognizerBundle);
        ActivityRunner.startActivityForResult(this, MY_BLINKID_REQUEST_CODE, uiSettings);
    }

    private void onScanSuccess(Intent data){
        data.setComponent(new ComponentName(this, ScanResult.class));
        startActivityForResult(data, 1);
    }
}
