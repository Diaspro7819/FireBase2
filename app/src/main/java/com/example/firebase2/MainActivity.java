package com.example.firebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText edName, edSecondName, edEmail;
    private DatabaseReference myDataBase; // ссылка на базу данных
    private String USER_KEY = "User"; // группа

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        edName = findViewById(R.id.edName);
        edSecondName = findViewById(R.id.edSecName);
        edEmail = findViewById(R.id.edEmail);
        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
    public void onClickSave(View view){
        String id = myDataBase.getKey();
        String name = edName.getText().toString();
        String sec_name = edSecondName.getText().toString();
        String email = edEmail.getText().toString();
        User newUser = new User(id, name, sec_name, email);
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sec_name) && !TextUtils.isEmpty(email)){
            myDataBase.push().setValue(newUser); //push - добавить
        }
        else{
            Toast.makeText(this, "Пустое поле!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickRead(View view){
        Intent intent = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(intent);
    }
}