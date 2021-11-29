package id.leo.ternaku;

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

        dataListHewan = database.daoHewan().getAllDataHewan();
        adapter = new KelolaHewanAdapter(dataListHewan);
        recyclerView.setAdapter(adapter);
        adapter.setListenerAdapterHewan(new KelolaHewanAdapter.onItemClickListenerAdapterHewan() {
            @Override
            public void onItemDetailClick(int position) {
                Intent detail = new Intent(KelolaHewanActivity.this,DetailHewanActivity.class);
                detail.putExtra("nama_hewan", dataListHewan.get(position).getNamaHewan());
                detail.putExtra("jumlah_hewan", ""+ dataListHewan.get(position).getJumlahHewan());
                detail.putExtra("ras_hewan", dataListHewan.get(position).getRasHewan());
                detail.putExtra("jenis_hewan", dataListHewan.get(position).getJenisHewan());
                detail.putExtra("jadwal_makan", dataListHewan.get(position).getJadwalMakan());
                startActivity(detail);
            }

            @Override
            public void onItemUpdateClick(int position) {
                Intent edit = new Intent(KelolaHewanActivity.this,TambahHewanActivity.class);
                edit.putExtra("id_hewan",""+dataListHewan.get(position).getId());
                edit.putExtra("nama_hewan", dataListHewan.get(position).getNamaHewan());
                edit.putExtra("jumlah_hewan", ""+ dataListHewan.get(position).getJumlahHewan());
                edit.putExtra("ras_hewan", dataListHewan.get(position).getRasHewan());
                edit.putExtra("jenis_hewan", dataListHewan.get(position).getJenisHewan());
                edit.putExtra("jadwal_makan", dataListHewan.get(position).getJadwalMakan());
                startActivity(edit);
            }

            @Override
            public void onItemDeleteClick(int position) {
                AlertDialog.Builder deleteMsg = new AlertDialog.Builder(KelolaHewanActivity.this);
                deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                deleteMsg.setMessage("Anda akan menghapus data \""+dataListHewan.get(position).getNamaHewan()+ "\" \n"+
                        "Tekan tombol ok jika anda yakin");
                deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.daoHewan().deleteHewan(dataListHewan.get(position));
                        dataListHewan.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeRemoved(position, dataListHewan.size());
                        Toast.makeText(KelolaHewanActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    }
                });

                deleteMsg.setNeutralButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                deleteMsg.show();
            }
        });

        if (getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaHewanActivity.this);
            succesMsg.setTitle("Inputan Berhasil");
            succesMsg.setMessage("Anda berhasil memasukan "+hasil.getStringExtra("nama_hewan")+ " dalam menu kelola hewan");
            succesMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            succesMsg.show();
        }


        addButton = findViewById(R.id.addButtonHewan);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahData();
            }
        });
    }

    public void tambahData(){
        Intent intent= new Intent(KelolaHewanActivity.this , TambahHewanActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(KelolaHewanActivity.this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}