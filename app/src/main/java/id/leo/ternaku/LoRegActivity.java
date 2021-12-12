package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.leo.ternaku.helper.SessionManagement;

public class LoRegActivity extends AppCompatActivity {
    Button tombolLogin, tombolRegister;

    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement session = new SessionManagement(this);
        if(session.statusLogin()){
            Intent mainIntent = new Intent(LoRegActivity.this, MainActivity.class);
            startActivity(mainIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_reg);
        tombolLogin = findViewById(R.id.buttonLogin);
        tombolRegister = findViewById(R.id.buttonRegister);
        tombolLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        tombolRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    public void login(){
        Intent intent = new Intent(LoRegActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void register(){
        Intent intent = new Intent(LoRegActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}