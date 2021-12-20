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
import id.leo.ternaku.model.UpdatePasswordModel;

public class EditPassActivity extends AppCompatActivity {

    EditText etOldPass, etNewPass, etReNewPass;
    Button btnUp;
    DatabaseHewan database;
    List<TabelPengguna> dataPengguna = new ArrayList<>();
    int idPengguna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);

        etOldPass   = findViewById(R.id.editTextOldPass);
        etNewPass   = findViewById(R.id.editTextNewPass);
        etReNewPass = findViewById(R.id.editTextReNewPass);
        btnUp       = findViewById(R.id.buttonUpdate);

        SessionManagement session = new SessionManagement(this);
        idPengguna = session.getId();

        database     = DatabaseHewan.getDbInstance(this);
        dataPengguna = database.daoHewan().selectPengguna(idPengguna);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OldPass   = dataPengguna.get(0).getPasswordPengguna().trim();
                String OldPassTy = etOldPass.getText().toString().trim();
                String NewPass   = etNewPass.getText().toString().trim();
                String ReNewPass = etReNewPass.getText().toString().trim();

                UpdatePasswordModel updateModel = new UpdatePasswordModel(OldPassTy, OldPass, NewPass, ReNewPass);


                UpdatePass updatePass = new UpdatePass(EditPassActivity.this, database, updateModel, idPengguna);
                int resultProcess = updatePass.processUpdatePass();

                if(resultProcess == 1){
                    finish();
                }
            }
        });

    }

}