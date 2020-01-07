package com.example.admin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.admin.Classes.AnswerItem;
import com.example.admin.R;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    private ArrayList<AnswerItem> mAnswerList;

    public AnswerAdapter(ArrayList<AnswerItem> answerList) {

        this.mAnswerList=answerList;
    }

    public void AddNewItem(AnswerItem newAnswer) {
        if (newAnswer != null) {
            this.mAnswerList.add(newAnswer);
        }
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //itt koti ossze egy recycleview elemet hogy hogyan nezzen ki
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.anwerresult_item, parent, false);
        AnswerViewHolder qvh = new AnswerViewHolder(v);
        return qvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        AnswerItem currentItem = mAnswerList.get(position);
        holder.nametext.setText(currentItem.name);
        holder.value.setText(currentItem.answer);

    }


    @Override
    public int getItemCount() {
        return mAnswerList.size();
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder {


        TextView nametext;
        Button value;


        private AnswerViewHolder(@NonNull View itemView) {
            super(itemView);


            nametext = itemView.findViewById(R.id.myNames);
            value=itemView.findViewById(R.id.AnswerButton);

        }
    }
}