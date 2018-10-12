package com.github.elwinbran.gamebacklog;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Locale;


public class EntryViewHolder extends RecyclerView.ViewHolder
{
    private final TextView titleDisplay;
    private final TextView platformDisplay;
    private final TextView statusDisplay;
    private final TextView dateDisplay;
    private final View content;

    public EntryViewHolder(View view)
    {
        super(view);
        this.content = view;
        this.titleDisplay = view.findViewById(R.id.gameTitleTextView);
        this.platformDisplay = view.findViewById(R.id.platformTextView);
        this.statusDisplay = view.findViewById(R.id.statusTextView);
        this.dateDisplay = view.findViewById(R.id.dateTextView);
    }

    public void setDisplayedEntry(BacklogEntry game, View.OnClickListener newAction)
    {
        titleDisplay.setText(game.getGameTitle());
        platformDisplay.setText(game.getPlatform());
        statusDisplay.setText(game.getStatus().getText());
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        dateDisplay.setText(dateFormatter.format(game.getAdded()));
        this.content.setOnClickListener(newAction);
    }
}
