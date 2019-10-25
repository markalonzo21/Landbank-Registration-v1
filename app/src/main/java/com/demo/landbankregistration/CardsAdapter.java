package com.demo.landbankregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder>{

    Context context;
    ArrayList<String> items;
    private OnItemClickListener onItemClickListener;

    public CardsAdapter(Context c, ArrayList<String> data, OnItemClickListener listener){
        context = c;
        items = data;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new ViewHolder(layoutInflater, parent, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.documentName.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView documentName;
        ImageView imageView;
        OnItemClickListener onItemClickListener;

        public ViewHolder(LayoutInflater inflater, ViewGroup viewGroup, OnItemClickListener listener){
            super(inflater.inflate(R.layout.cards, viewGroup, false));
            this.documentName = itemView.findViewById(R.id.documentName);
            this.imageView = itemView.findViewById(R.id.checkImage);
            onItemClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(context, this.documentName + "", Toast.LENGTH_LONG).show();
            onItemClickListener.onItemClick(getAdapterPosition(),view);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int i, View view);
    }
}

