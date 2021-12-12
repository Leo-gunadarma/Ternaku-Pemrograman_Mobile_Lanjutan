package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelPengguna;
import id.leo.ternaku.helper.SessionManagement;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHewan database;
    EditText editTextEmail, editTextPassword;
    Button tombolLogin;
    List<TabelPengguna> dataLogin = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement session = new SessionManagement(this);
        if(session.statusLogin()){
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SessionManagement session = new SessionManagement(LoginActivity.this);

        database = DatabaseHewan.getDbInstance(this);

        editTextEmail = findViewById(R.id.editTextEmailL);
        editTextPassword = findViewById(R.id.editTextPasswordL);
        tombolLogin = findViewById(R.id.buttonLogin);

        tombolLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(email.length() == 0){
                    editTextEmail.setError("Isikan email terlebih dahulu !");
                    editTextEmail.requestFocus();
                }else if(password.length() == 0){
                    editTextEmail.setError("Isikan password terlebih dahulu !");
                    editTextEmail.requestFocus();
                }else{
                    dataLogin = database.daoHewan().loginPengguna(email, password);
                    int isLogin =  dataLogin.size();
                    if(isLogin <= 0){
                        Toast toast = Toast.makeText(LoginActivity.this, "Gagal Login, Username atau Password Salah", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        session.saveSession(dataLogin.get(0).getId(), dataLogin.get(0).getNamaPengguna());
                        Toast toast = Toast.makeText(LoginActivity.this, "Berhasil Login, Hai "+dataLogin.get(0).getNamaPengguna(), Toast.LENGTH_LONG);
                        toast.show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
            }
        });
    }
}