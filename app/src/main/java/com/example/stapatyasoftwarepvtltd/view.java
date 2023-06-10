package com.example.stapatyasoftwarepvtltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int fname = c.getColumnIndex("fname");
        int lname = c.getColumnIndex("lname");
        int mail = c.getColumnIndex("mail");
        int mobile = c.getColumnIndex("mobile");
        int pan = c.getColumnIndex("pan");
        int adhar = c.getColumnIndex("adhar");
        int birth = c.getColumnIndex("Birth");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<student> stud = new ArrayList<student>();

        if (c.moveToFirst())
        {
            do {
                student stu = new student();
                stu.id = c.getString(id);
                stu.fname = c.getString(fname);
                stu.lname = c.getString(lname);
                stu.mail = c.getString(mail);
                stu.mobile = c.getString(mobile);
                stu.pan = c.getString(pan);
                stu.adhar = c.getString(adhar);
                stu.birth = c.getString(birth);

                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(fname) + " \t " + c.getString(lname) + " \t " + c.getString(mail) + " \t " + c.getString(mobile) + " \t " + c.getString(pan) + " \t " + c.getString(adhar) + " \t " + c.getString(birth));

            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();

                student stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(),edit.class);

                i.putExtra("id",stu.id);
                i.putExtra("fname",stu.fname);
                i.putExtra("lname",stu.lname);
                i.putExtra("mail",stu.mail);
                i.putExtra("mobile",stu.mobile);
                i.putExtra("pan",stu.pan);
                i.putExtra("adhar",stu.adhar);
                i.putExtra("birth",stu.birth);
                startActivity(i);



            }
        });
    }
}