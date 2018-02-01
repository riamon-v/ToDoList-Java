package com.example.riamon_v.todolist.ListManagment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riamon_v.todolist.DatabaseManagment.TaskCard;
import com.example.riamon_v.todolist.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCard extends RecyclerView.Adapter<CardHolder> {

    private List<TaskCard> list = new ArrayList<>();
    private OnItemClickListener listener;

    public AdapterCard(List<TaskCard> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(TaskCard item);
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public CardHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view,viewGroup,false);
        return new CardHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(CardHolder cardHolder, int position) {
        TaskCard task = list.get(position);
        cardHolder.bind(task, listener);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(TaskCard item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }
    /*public void swap(List newList){
        if (list != null) {
            list.clear();
            list.addAll(newList);
        }
        else {
            list = newList;
        }
        notifyDataSetChanged();
    }*/
}
