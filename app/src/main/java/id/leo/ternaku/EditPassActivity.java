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

public class EditPassActivity extends AppCompatActivity {

    EditText etOldPass, etNewPass, etReNewPass;
    Button btnUp;
    private DatabaseHewan database;
    List<TabelPengguna> dataPengguna = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);

        etOldPass   = findViewById(R.id.editTextOldPass);
        etNewPass   = findViewById(R.id.editTextNewPass);
        etReNewPass = findViewById(R.id.editTextReNewPass);
        btnUp       = findViewById(R.id.buttonUpdate);

        SessionManagement session = new SessionManagement(this);
        int idPengguna = session.getId();

        database     = DatabaseHewan.getDbInstance(this);
        dataPengguna = database.daoHewan().selectPengguna(idPengguna);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String OldPass   = dataPengguna.get(0).getPasswordPengguna().trim();
                String OldPassTy = etOldPass.getText().toString().trim();
                String NewPass   = etNewPass.getText().toString().trim();
                String ReNewPass = etReNewPass.getText().toString().trim();

                if(OldPassTy.isEmpty() || NewPass.isEmpty() || ReNewPass.isEmpty()){
                    Toast.makeText(EditPassActivity.this, "Lengkapi Data Terlebih Dahulu!", Toast.LENGTH_SHORT).show();
                }else{
                    if(OldPassTy.equals(OldPass)){
                        if(NewPass.equals(ReNewPass)){
                            if(OldPass.equals(NewPass)){
                                Toast.makeText(EditPassActivity.this, "Password Lama dan Password Baru Sama!", Toast.LENGTH_SHORT).show();
                            }else{
                                database.daoHewan().updatePass(idPengguna,NewPass);
                                Toast.makeText(EditPassActivity.this, "Password Berhasil Diubah!",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }else{
                            Toast.makeText(EditPassActivity.this, "Password Baru Tidak Valid!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(EditPassActivity.this, "Password Lama Salah!"+ OldPass + OldPassTy, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}