package com.github.elwinbran.gamebacklog;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(this);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.app_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView backlogView = findViewById(R.id.backlogView);
        //View muhSingleCard = LayoutInflater.from(this).inflate(R.layout.backlog_entry_card, backlogView, false);
        //applyGameToEntry(muhSingleCard, db.backlogEntryDAO().getAllEntries().get(0));
        List<BacklogEntry> entries = db.backlogEntryDAO().getAllEntries();
        List<View.OnClickListener> actions = new LinkedList<>();
        for(final BacklogEntry entry : entries)
        {
            actions.add(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startEditing(entry);
                }
            });
        }
        BacklogAdapter adapter = new BacklogAdapter(entries, actions);
        backlogView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        backlogView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addGameButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    static void applyGameToEntry(View entry, BacklogEntry game)
    {
        ((TextView)entry.findViewById(R.id.gameTitleTextView)).setText(game.getGameTitle());
        ((TextView)entry.findViewById(R.id.platformTextView)).setText(game.getPlatform());
        ((TextView)entry.findViewById(R.id.statusTextView)).setText(game.getStatus().getText());
        ((TextView)entry.findViewById(R.id.dateTextView)).setText(game.getAdded().toString());
    }

    void addEntry(List<BacklogEntry> entries, List<View.OnClickListener> actions, final BacklogEntry newEntry)
    {
        entries.add(newEntry);
        actions.add(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditing(newEntry);
            }
        });
    }

    void startEditing(BacklogEntry entry)
    {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("entry", entry);
        startActivity(intent);
    }
}
