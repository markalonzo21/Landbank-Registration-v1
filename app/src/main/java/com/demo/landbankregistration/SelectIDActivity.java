package com.demo.landbankregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class SelectIDActivity extends AppCompatActivity {

    ListView listView;
    String[] items;
    DocumentAdapter documentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_id);

        Resources res = getResources();
        listView = (ListView) findViewById(R.id.listView);
        items = res.getStringArray(R.array.cb_items);
        listView.setSelection(AbsListView.CHOICE_MODE_MULTIPLE);

        documentAdapter = new DocumentAdapter(this, items);
        listView.setAdapter(documentAdapter);
    }

    public void onClickNext(View view) {
        int count = documentAdapter.getAllSelectedItems().size();
        if(count == documentAdapter.MAX_SIZE){
            Intent intent = new Intent(getApplicationContext(), ScanDocumentsActivity.class);
            intent.putExtra("com.demo.landbankregistration.IDS", documentAdapter.getAllSelectedItems());
            startActivity(intent);
        }else{
            Toast.makeText(this, "Select 3 IDs to Scan", Toast.LENGTH_LONG).show();
        }
    }
}
