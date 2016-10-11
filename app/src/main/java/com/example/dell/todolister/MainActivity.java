package com.example.dell.todolister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static Button b1,b2;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SYNOPSIS");
        listner1();
        listner2();
        mydb=new DatabaseHelper(this);
    }

    public void listner1(){
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i1=new Intent("com.example.dell.todolister.ViewActivity");
                        startActivity(i1);
                    }
                }
        );
    }
    public void listner2(){
        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i2=new Intent("com.example.dell.todolister.MakeActivity");
                        startActivity(i2);
                    }
                }
        );
    }
}
