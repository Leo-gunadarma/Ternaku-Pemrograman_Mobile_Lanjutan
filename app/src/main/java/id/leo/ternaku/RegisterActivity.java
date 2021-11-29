package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {
    Button tombolRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tombolRegister = findViewById(R.id.buttonDaftar);
        tombolRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftar();
            }
        });
    }
    public void daftar(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}