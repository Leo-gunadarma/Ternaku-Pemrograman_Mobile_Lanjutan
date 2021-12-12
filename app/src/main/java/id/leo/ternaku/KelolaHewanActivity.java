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
import id.leo.ternaku.database.TabelHewan;

public class KelolaHewanActivity extends AppCompatActivity {
    private FloatingActionButton addButton;
    private DatabaseHewan database;
    private RecyclerView recyclerView;
    private KelolaHewanAdapter adapter;
    List<TabelHewan> dataListHewan =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_hewan);

        database = DatabaseHewan.getDbInstance(this);
        recyclerView = findViewById(R.id.recyclerViewKelolaHewan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showData();


        addButton = findViewById(R.id.addButtonHewan);
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
        dataListHewan = database.daoHewan().getAllDataHewan();
        adapter = new KelolaHewanAdapter(KelolaHewanActivity.this, dataListHewan);
        recyclerView.setAdapter(adapter);
    }

    public void tambahData(){
        Intent intent= new Intent(KelolaHewanActivity.this , TambahHewanActivity.class);
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
        String hasil = data.getStringExtra("nama_hewan");
        AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaHewanActivity.this);
        succesMsg.setTitle("Inputan Berhasil");
        succesMsg.setMessage("Anda berhasil memasukan "+hasil+ " dalam menu kelola hewan");
        succesMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        succesMsg.show();
    }
}