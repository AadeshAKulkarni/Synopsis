package com.example.dell.todolister;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dell.todolister.R.id.activity_view;
import static com.example.dell.todolister.R.id.text2;
public class ViewActivity extends AppCompatActivity {
    DatabaseHelper db;
    int len,index=1;
    public LinearLayout relative;
    private EditText e1;
    private Button b1;
    private TextView textView;
    private TextView t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        setTitle("SYNOPSIS");
        db=new DatabaseHelper(this);
        dataview();
    }
    public void dataview(){
        Cursor res=db.onView();
        len=res.getCount();
        if(res.getCount()==0)
        {
            Toast.makeText(ViewActivity.this,"No Notes.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext()) {
            relative = (LinearLayout) findViewById(R.id.activity_view);
            relative.addView(createNewTextView(res.getString(1).toString()));
        }
    }
    public TextView createNewTextView(String text){
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(0,10,0,0);

        //creating TextView and assigning properties here
        textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        index++;
        textView.setTextSize(18);
        textView.setBackgroundColor(Color.parseColor("#ff0099cc"));

        // onClick Action here
        final String title=textView.getText().toString();
        textView.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Cursor res1=db.onView();
                                while(res1.moveToNext()){
                                    if(res1.getString(1).toString().equals(title))
                                    {
                                        final String text1=res1.getString(0);
                                        Intent i=new Intent("com.example.dell.todolister.ViewNoteActivity");
                                        i.putExtra("TextBox1",text1);
                                        startActivity(i);

                                    }
                                }
                            }
                        }
                );
        return textView;
    }
}

