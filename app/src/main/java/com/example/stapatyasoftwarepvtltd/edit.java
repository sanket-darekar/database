package com.example.stapatyasoftwarepvtltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ed1 = findViewById(R.id.fname);
        ed2 = findViewById(R.id.lname);
        ed3 = findViewById(R.id.mail);
        ed4 = findViewById(R.id.mobile);
        ed5 = findViewById(R.id.pan);
        ed6 = findViewById(R.id.adhar);
        ed7 = findViewById(R.id.birth);
        ed8 = findViewById(R.id.id);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        b3 = findViewById(R.id.bt3);

        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("fname").toString();
        String t3 = i.getStringExtra("lname").toString();
        String t4 = i.getStringExtra("mail").toString();
        String t5 = i.getStringExtra("mobile").toString();
        String t6 = i.getStringExtra("pan").toString();
        String t7 = i.getStringExtra("adhar").toString();
        String t8 = i.getStringExtra("birth").toString();

        ed8.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);
        ed3.setText(t4);
        ed4.setText(t5);
        ed5.setText(t6);
        ed6.setText(t7);
        ed7.setText(t8);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit();
            }
        });

    }


    public void Delete()
    {
        try {

            String id = ed8.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("Slite", Context.MODE_PRIVATE,null);

            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this, "Record Deleted...", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed7.setText("");

            ed1.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }

    public void Edit()
    {
        try {
            String fname = ed1.getText().toString();
            String lname = ed2.getText().toString();
            String mail = ed3.getText().toString();
            String mobile = ed4.getText().toString();
            String pan = ed5.getText().toString();
            String adhar = ed6.getText().toString();
            String birth = ed7.getText().toString();
            String id = ed8.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("Slite", Context.MODE_PRIVATE,null);

            String sql = "update records set fname = ?,lname=?,mail=?,mobile=?,pan=?,adhar=?,birth=? where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,fname);
            statement.bindString(2,lname);
            statement.bindString(3,mail);
            statement.bindString(4,mobile);
            statement.bindString(5,pan);
            statement.bindString(6,adhar);
            statement.bindString(7,birth);
            statement.bindString(7,id);
            statement.execute();
            Toast.makeText(this, "Record Updateddd", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed7.setText("");

            ed1.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }
}