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

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.fname);
        ed2 = findViewById(R.id.lname);
        ed3 = findViewById(R.id.mail);
        ed4 = findViewById(R.id.mobile);
        ed5 = findViewById(R.id.pan);
        ed6 = findViewById(R.id.adhar);
        ed7 = findViewById(R.id.birth);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                insert();
            }
        });
    }

    public void insert()
    {
        try {
            String fname = ed1.getText().toString();
            String lname = ed2.getText().toString();
            String mail = ed3.getText().toString();
            String mobile = ed4.getText().toString();
            String pan = ed5.getText().toString();
            String adhar = ed6.getText().toString();
            String birth = ed7.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("Slite", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,fname VARCHAR,lname VARCHAR,mail VARCHAR,mobile VARCHAR,pan VARCHAR,adhar VARCHAR,birth VARCHAR)");

            String sql = "insert into records(fname,lname,mail,mobile,pan,adhar,birth)values(?,?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,fname);
            statement.bindString(2,lname);
            statement.bindString(3,mail);
            statement.bindString(4,mobile);
            statement.bindString(5,pan);
            statement.bindString(6,adhar);
            statement.bindString(7,birth);
            statement.execute();
            Toast.makeText(this, "Record Added", Toast.LENGTH_LONG).show();

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