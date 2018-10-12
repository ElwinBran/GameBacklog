package com.github.elwinbran.gamebacklog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BacklogAdapter extends RecyclerView.Adapter<EntryViewHolder>
{
    private List<BacklogEntry> gameEntries;
    private final List<View.OnClickListener> actions;

    public BacklogAdapter(List<BacklogEntry> entries, List<View.OnClickListener> actions)
    {
        this.gameEntries = entries;
        this.actions = actions;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View card = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.backlog_entry_card, viewGroup, false);
        return new EntryViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int i)
    {
        entryViewHolder.setDisplayedEntry(this.gameEntries.get(i), actions.get(i));
    }

    @Override
    public int getItemCount() {
        return this.gameEntries.size();
    }
}
