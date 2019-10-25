package com.demo.landbankregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DocumentAdapter extends BaseAdapter {

    public static final int MAX_SIZE = 3;
    String items[];
    LayoutInflater inflater;
    private ArrayList<String> selectedItems;
    private int checkedItems = 0;

    public DocumentAdapter(Context context, String[] data){
        items = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        selectedItems = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = inflater.inflate(R.layout.checkbox_items, null);
        final CheckBox checkBoxItems = (CheckBox) v.findViewById(R.id.cb_items);
        checkBoxItems.setTextSize(24);
        LinearLayout Listener = (LinearLayout) v.findViewById(R.id.checkboxLinear);

        String name = items[i];
        checkBoxItems.setText(name);

        Listener.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkBoxItems.performClick();
            }
        });

        checkBoxItems.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkedItems == MAX_SIZE && b){
                    compoundButton.setChecked(false);
                }
                else if(b){
                    checkedItems++;
                    selectedItems.add(items[i]);
                }
                else if(!b){
                    checkedItems--;
                    selectedItems.remove(items[i]);
                }
            }
        });
        checkBoxItems.setChecked(false);
        return v;
    }

    public ArrayList<String> getAllSelectedItems() {
        return selectedItems;
    }
}
