package com.example.ashutosh.googletrails;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
EditText t1,t2;

DatabaseHelper dabba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        t1 = (EditText)findViewById(R.id.editText);
        t2 = (EditText)findViewById(R.id.editText2);

        dabba = new DatabaseHelper(this);


    }

    public void saveraja(View view) {

        try {
            boolean isInserted = dabba.insertData(t1.getText().toString(), t2.getText().toString());
            if (isInserted == true) {
                Toast.makeText(MainActivity.this, "Data inserted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Insert Full details!", Toast.LENGTH_SHORT).show();
        }


    }


    public void shower(View view) {

        Cursor res = dabba.getALLData();
        Log.i("chickening","Bickening");
        if(res.getCount() == 0){
            //show message
            Log.i("inside if","khaaliwala");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            Log.i("inside","cursor");
            buffer.append("Id : "+res.getString(0)+ "\n");
            buffer.append("Name : "+res.getString(1)+ "\n");
            buffer.append("Surname : "+res.getString(2)+ "\n");

            Log.i("inside","cursorEnd");



        }
        Log.i("outside","whileloop");
        //show data

        Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
        //showMessage("Data",buffer.toString());


    }

    public void rester(View view) {

        Intent intent = new Intent(MainActivity.this, SecondMainActivity.class);
        startActivity(intent);
    }
}
