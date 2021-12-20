package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelPengguna;
import id.leo.ternaku.helper.SessionManagement;

public class ProfilActivity extends AppCompatActivity {

    TextView tvNama, tvEmail, tvAlamat, tvTelp;
    private DatabaseHewan database;
    List<TabelPengguna> dataPengguna = new ArrayList<>();
    Button btnEdit, btnChgPass;
    int idPengguna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tvNama      = findViewById(R.id.textViewNama);
        tvEmail     = findViewById(R.id.textViewEmail);
        tvAlamat    = findViewById(R.id.textViewAlamat);
        tvTelp      = findViewById(R.id.textViewTelp);
        btnEdit     = findViewById(R.id.buttonEdit);
        btnChgPass  = findViewById(R.id.buttonResetPass);

        SessionManagement session = new SessionManagement(this);
        idPengguna = session.getId();

        showData();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProf = new Intent(ProfilActivity.this, EditProfilActivity.class);
                startActivity(intentProf);
            }
        });

        btnChgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPass = new Intent(ProfilActivity.this, EditPassActivity.class);
                startActivity(intentPass);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    public void showData(){
        database     = DatabaseHewan.getDbInstance(this);
        dataPengguna = database.daoHewan().selectPengguna(idPengguna);

        String nama   = dataPengguna.get(0).getNamaPengguna();
        String email  = dataPengguna.get(0).getEmailPengguna();
        String alamat = dataPengguna.get(0).getAlamatPengguna();
        String telp   = dataPengguna.get(0).getNoTelpPengguna();

        tvNama.setText(nama);
        tvEmail.setText(email);
        tvAlamat.setText(alamat);
        tvTelp.setText(telp);
    }
}