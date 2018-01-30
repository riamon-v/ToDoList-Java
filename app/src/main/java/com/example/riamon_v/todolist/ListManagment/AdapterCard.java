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

    List<TaskCard> list;

    //ajouter un constructeur prenant en entrée une liste
    public AdapterCard(List<TaskCard> list) {
        this.list = list;
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
        cardHolder.bind(task);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
