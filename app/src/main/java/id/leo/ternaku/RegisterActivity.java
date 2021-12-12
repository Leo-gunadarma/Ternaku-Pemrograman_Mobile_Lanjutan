package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelObat;
import id.leo.ternaku.database.TabelPengguna;
import id.leo.ternaku.helper.SessionManagement;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHewan database;
    EditText editTextNama, editTextEmail, editTextPassword, editTextAlamat, editTextNoTelp;
    Button tombolRegister;

    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement session = new SessionManagement(this);
        if(session.statusLogin()){
            Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextNama = findViewById(R.id.editTextNama);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextNoTelp = findViewById(R.id.editTextNoTelp);

        tombolRegister = findViewById(R.id.buttonDaftar);

        database = DatabaseHewan.getDbInstance(this.getApplicationContext());

        tombolRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNama.getText().toString().trim().length()==0){
                    editTextNama.setError("Nama harus diisi !");
                    editTextNama.requestFocus();
                }else if(editTextEmail.getText().toString().trim().length()==0){
                    editTextEmail.setError("Email harus diisi !");
                    editTextEmail.requestFocus();
                }else if(editTextPassword.getText().toString().trim().length()==0){
                    editTextPassword.setError("Password harus diisi !");
                    editTextPassword.requestFocus();
                }else if(editTextAlamat.getText().toString().trim().length()==0){
                    editTextAlamat.setError("Alamat harus diisi !");
                    editTextAlamat.requestFocus();
                }else if(editTextNoTelp.getText().toString().trim().length()==0){
                    editTextNoTelp.setError("No Telp harus diisi !");
                    editTextNoTelp.requestFocus();
                }else{
                    String nama = editTextNama.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();
                    String alamat = editTextAlamat.getText().toString().trim();
                    String notelp = editTextNoTelp.getText().toString().trim();

                    TabelPengguna row = new TabelPengguna(nama, email, password, alamat, notelp);
                    database.daoHewan().insertDataPengguna(row);

                    Toast toast = Toast.makeText(RegisterActivity.this, "Register Berhasil, Silahkan Login", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
            }
        });
    }
}