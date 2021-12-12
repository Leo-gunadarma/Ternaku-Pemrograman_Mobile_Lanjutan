package id.leo.ternaku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelObat;

public class KelolaObatActivity extends AppCompatActivity {
    private DatabaseHewan database;
    private RecyclerView recyclerView;
    private KelolaObatAdapter adapter;
    private FloatingActionButton addButton;
    List<TabelObat> dataListObat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_obat);
        recyclerView = findViewById(R.id.recyclerViewKelolaObat);

        database = DatabaseHewan.getDbInstance(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showData();

        addButton = findViewById(R.id.addButtonObat);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }

    public void showData(){
        dataListObat = database.daoHewan().getAllDataObat();
        adapter = new KelolaObatAdapter(KelolaObatActivity.this, dataListObat);
        recyclerView.setAdapter(adapter);
    }

    public void tambahData(){
        Intent intent= new Intent(KelolaObatActivity.this , TambahObatActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                String hasil = data.getStringExtra("nama_obat");
                AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaObatActivity.this);
                succesMsg.setTitle("Inputan Berhasil");
                succesMsg.setMessage("Anda berhasil memasukan Obat \""+hasil+ "\" dalam menu kelola obat");
                succesMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                succesMsg.show();
            }
        }
    }
}