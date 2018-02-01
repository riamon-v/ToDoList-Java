package com.example.riamon_v.todolist.ListManagment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riamon_v.todolist.DatabaseManagment.TaskCard;
import com.example.riamon_v.todolist.ListManagment.CardHolder;
import com.example.riamon_v.todolist.R;

import java.util.List;

/**
 * Created by riamon_v on 30/01/2018.
 */

public class AdapterCard extends RecyclerView.Adapter<CardHolder> {

    public interface OnItemClickListener {
        void onItemClick(TaskCard item);
    }

    private List<TaskCard> list;
    private OnItemClickListener listener;


    //ajouter un constructeur prenant en entrée une liste
    public AdapterCard(List<TaskCard> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
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
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(TaskCard item, int position) {
        list.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
