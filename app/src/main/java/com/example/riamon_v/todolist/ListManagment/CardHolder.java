package com.example.riamon_v.todolist.ListManagment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.riamon_v.todolist.DatabaseManagment.TaskCard;
import com.example.riamon_v.todolist.R;

public class CardHolder extends RecyclerView.ViewHolder {
    private TextView textViewView;
    private View v;

    //itemView est la vue correspondante à 1 cellule
    public CardHolder(View itemView) {
        super(itemView);
        v = itemView;
        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.titleCard);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final TaskCard item, final AdapterCard.OnItemClickListener listener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(item);
            }
        });
       textViewView.setText(item.getTitle());
    }

}

