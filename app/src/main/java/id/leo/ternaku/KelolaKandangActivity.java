package id.leo.ternaku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelKandang;

public class KelolaKandangActivity extends AppCompatActivity {
    private FloatingActionButton addButton;
    private DatabaseHewan database;
    private RecyclerView recyclerView;
    private KelolaKandangAdapter adapter;
    List<TabelKandang> dataListKandang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_kandang);

        database = DatabaseHewan.getDbInstance(this);
        recyclerView = findViewById(R.id.recyclerViewKelolaKandang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showData();


        addButton = findViewById(R.id.addButtonKandang);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahData();
            }
        });
    }
    public void tambahData(){
        Intent intent= new Intent(KelolaKandangActivity.this , TambahKandangActivity.class);
        startActivityForResult(intent, 1);
    }

    public void showData(){
        dataListKandang = database.daoHewan().getAllDataKandang();
        adapter = new KelolaKandangAdapter(KelolaKandangActivity.this, dataListKandang);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
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
                String hasil = data.getStringExtra("nama_kandang");
                AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaKandangActivity.this);
                succesMsg.setTitle("Inputan Berhasil");
                succesMsg.setMessage("Anda berhasil memasukan kandang \""+hasil+ "\" dalam menu kelola kandang");
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