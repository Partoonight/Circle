package com.example.paul.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Events extends AppCompatActivity {

    EventDB eventDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventDB = new EventDB(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadEvents();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TO BE DONE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadEvents(){
        TableLayout table = (TableLayout) findViewById(R.id.tableForEvents);
        for (int row = 1; row < eventDB.numberOfRows(); row++ ){
            TableRow tableRow = new TableRow(this);
            LinearLayout left = new LinearLayout(this);
            LinearLayout right = new LinearLayout(this);
            left.setOrientation(LinearLayout.VERTICAL);
            right.setOrientation(LinearLayout.VERTICAL);
            left.setMinimumWidth(800);
            tableRow.setPadding(30,50,30,50);
            tableRow.setGravity(Gravity.CENTER_VERTICAL);

            TextView title = new TextView(this);
            TextView description = new TextView(this);
            TextView time = new TextView(this);
            TextView cost = new TextView(this);
            TextView host = new TextView(this);
            Button cancel = new Button(this);

            title.setText("Title: " + eventDB.getTitle(row));
            description.setText(""+ eventDB.getDescription(row));
            time.setText("Time: " + (eventDB.getTime(row)) +" - " + (eventDB.getTime(row)+eventDB.getDuration(row)));
            cost.setText("$" + eventDB.getCost(row) + "0");
            host.setText("Host: " + eventDB.getHost(row));
            cancel.setText("Cancel");

            left.addView(title);
            left.addView(host);
            left.addView(cost);
            left.addView(description);
            right.addView(time);
            right.addView(cancel);
            tableRow.addView(left);
            tableRow.addView(right);
            table.addView(tableRow);
        }
    }
}
