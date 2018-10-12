package com.github.elwinbran.gamebacklog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;

public class EditActivity extends AppCompatActivity
{
    private Spinner statusSelection;

    private boolean editMode;

    private Date addedDate = null;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.statusSelection = (Spinner) findViewById(R.id.statusSpinner);
        statusSelection.setAdapter(new ArrayAdapter<GameStatus>(this,
                R.layout.support_simple_spinner_dropdown_item, GameStatus.values()));
        Parcelable possibleEntry = getIntent().getParcelableExtra("entry");
        editMode = false;
        if(possibleEntry != null)
        {
            BacklogEntry entry = (BacklogEntry) possibleEntry;
            editMode = true;
            statusSelection.setSelection(entry.getStatus().ordinal());
            ((TextView)findViewById(R.id.nameEditText)).setText(entry.getGameTitle());
            (findViewById(R.id.nameEditText)).setEnabled(false);
            ((TextView)findViewById(R.id.platformEditText)).setText(entry.getPlatform());
            ((TextView)findViewById(R.id.notesEditText)).setText(entry.getNotes());
            addedDate = entry.getAdded();
        }
        //TODO add FAB onClick that adds stuff to DB?
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveEditbutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSave(view);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
        return true;
    }

    private void handleSave(View view)
    {
        String title = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        String platform = ((EditText)findViewById(R.id.platformEditText)).getText().toString();
        String notes = ((EditText)findViewById(R.id.notesEditText)).getText().toString();
        GameStatus status = (GameStatus)this.statusSelection.getSelectedItem();
        if(title == null || title.isEmpty())
        {
            Toast.makeText(this, "Cannot save without title!", Toast.LENGTH_SHORT).show();
            ((EditText)findViewById(R.id.nameEditText)).requestFocus();
        }
        else {
            if (platform == null || platform.isEmpty()) {
                Toast.makeText(this, "Cannot save without platform!", Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.platformEditText)).requestFocus();
            }
            else {
                if (editMode) {
                    MainActivity.db.backlogEntryDAO().updateEntry(
                            new BacklogEntry(title, platform, notes, this.addedDate, status));
                } else {
                    Date addedDate = new Date(System.currentTimeMillis());
                    BacklogEntry newEntry = new BacklogEntry(title, platform, notes, addedDate, status);
                    MainActivity.db.backlogEntryDAO().insertEntry(newEntry);
                }
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        }
    }
}
