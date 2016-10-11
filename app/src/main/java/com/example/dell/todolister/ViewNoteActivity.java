package com.example.dell.todolister;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.R.id.text1;

public class ViewNoteActivity extends AppCompatActivity {
    private TextView t2;
    private EditText e1;
    private Button b4,b5;
    DatabaseHelper db;
    String id,text1,text2,text;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        setTitle("SYNOPSIS");
        db = new DatabaseHelper(this);
        b4 = (Button) findViewById(R.id.button4);
        listner();
        ButtonListen();
        ButtonDelete();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void listner() {
        Intent ii = getIntent();
        text1 = ii.getStringExtra("TextBox1");
        Cursor res = db.onView();
        while (res.moveToNext()) {
            if (res.getString(0).equals(text1)) {
                id = res.getString(0);
                text=res.getString(1);
                text2 = res.getString(2);
                t2 = (TextView) findViewById(R.id.textView2);
                e1 = (EditText) findViewById(R.id.editText2);
                t2.setText(text);
                e1.setText(text2);
                break;
            }
        }
    }
    public void ButtonListen(){
        b4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e1 = (EditText) findViewById(R.id.editText2);
                        t2 = (TextView) findViewById(R.id.textView2);
                        text2=e1.getText().toString();
                        t2.setText(text);
                        db.onUpdated(text2,id);
                        listner();
                    }
                }
        );
    }
    public void ButtonDelete(){
            b5=(Button)findViewById(R.id.button5);
            b5.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          db.onDelete(id);
                          t2.setText("");
                          e1.setText("");
                          Intent i=new Intent("com.example.dell.todolister.ViewActivity");
                          startActivity(i);
                        }
                    }
            );
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ViewNote Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
