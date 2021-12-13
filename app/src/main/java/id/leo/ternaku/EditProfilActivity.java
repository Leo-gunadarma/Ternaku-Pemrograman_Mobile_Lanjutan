package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

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


public class EditProfilActivity extends AppCompatActivity {

    EditText etNama, etEmail, etAlamat, etTelp;
    Button btnUpdate;
    private DatabaseHewan database;
    List<TabelPengguna> dataPengguna = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        etNama    = findViewById(R.id.editTextNama);
        etEmail   = findViewById(R.id.editTextEmail);
        etAlamat  = findViewById(R.id.editTextAlamat);
        etTelp    = findViewById(R.id.editTextNoTelp);
        btnUpdate = findViewById(R.id.buttonUpdate);

        SessionManagement session = new SessionManagement(this);
        int idPengguna = session.getId();

        database     = DatabaseHewan.getDbInstance(this);
        dataPengguna = database.daoHewan().selectPengguna(idPengguna);

        String nama   = dataPengguna.get(0).getNamaPengguna();
        String email  = dataPengguna.get(0).getEmailPengguna();
        String alamat = dataPengguna.get(0).getAlamatPengguna();
        String telp   = dataPengguna.get(0).getNoTelpPengguna();

        etNama.setText(nama);
        etEmail.setText(email);
        etAlamat.setText(alamat);
        etTelp.setText(telp);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNama = etNama.getText().toString().trim();
                String newEmail = etEmail.getText().toString().trim();
                String newAlamat = etAlamat.getText().toString().trim();
                String newTelp = etTelp.getText().toString().trim();
                database.daoHewan().updatePengguna(idPengguna,newNama,newEmail,newAlamat,newTelp);
                Toast.makeText(EditProfilActivity.this, "Profil Berhasil Diubah!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}