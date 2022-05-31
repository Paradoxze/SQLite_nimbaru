package com.example.sqlite_nim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstname, lastname;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = (EditText) findViewById(R.id.editFirstname);
        lastname = (EditText) findViewById(R.id.editLastname);
        textView = (TextView) findViewById(R.id.TextView);

        controller = new DB_Controller(this, "", null,1);

    }

    public void btn_click(View view){
        switch (view.getId()){
            case R.id.btn_add:
                try {
                    controller.insert_student(firstname.getText().toString(),lastname.getText().toString());
                }catch(SQLiteException e){
                    Toast.makeText(MainActivity.this, "ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete:
                controller.delete_student(firstname.getText().toString());
                break;
            case R.id.listname:
                controller.list_all_students(textView);
                break;
            case R.id.update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Masukkan Nama");

                final EditText newFirstName = new EditText(this);
                dialog.setView(newFirstName);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        controller.update_student(firstname.getText().toString(), newFirstName.getText().toString());
                    }
                });
                dialog.show();
                break;

        }
    }
}