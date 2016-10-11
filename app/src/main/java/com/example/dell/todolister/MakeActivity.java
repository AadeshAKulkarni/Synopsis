package com.example.dell.todolister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MakeActivity extends AppCompatActivity {
    private static EditText e1,e2;
    private static Button b1;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        setTitle("SYNOPSIS");
        db=new DatabaseHelper(this);
        listner();
    }
    public void listner(){
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText4);
        b1=(Button)findViewById(R.id.button3);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if((e1.getText().toString().equals(""))||(e2.getText().toString().equals(""))) {
                            Toast.makeText(MakeActivity.this, "Cannot save your note: All fields must be filled.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        boolean res = db.onInsert(e1.getText().toString(), e2.getText().toString());
                        if (res)
                            Toast.makeText(MakeActivity.this, "Note Saved.", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MakeActivity.this, "Couldnt save this note.", Toast.LENGTH_SHORT).show();
                    }
                    }
                }
        );
    }
}
