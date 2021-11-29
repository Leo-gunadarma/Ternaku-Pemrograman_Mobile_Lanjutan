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

        dataListKandang = database.daoHewan().getAllDataKandang();
        adapter = new KelolaKandangAdapter(dataListKandang);
        recyclerView.setAdapter(adapter);

        adapter.setListenerAdapterKandang(new KelolaKandangAdapter.onItemClickListenerAdapterKandang() {
            @Override
            public void onItemDetailClick(int position) {
                Intent detail = new Intent(KelolaKandangActivity.this,DetailKandangActivity.class);
                detail.putExtra("nama_kandang", dataListKandang.get(position).getNamaKandang());
                detail.putExtra("lokasi_kandang", dataListKandang.get(position).getLokasiKandang());
                detail.putExtra("luas_kandang", ""+ dataListKandang.get(position).getLuasKandang()+" Meter Persegi");
                detail.putExtra("kapasitas_kandang", ""+dataListKandang.get(position).getKapasitasKandang()+" Ekor");
                startActivity(detail);

            }

            @Override
            public void onItemUpdateClick(int position) {
                Intent edit = new Intent(KelolaKandangActivity.this,TambahKandangActivity.class);
                edit.putExtra("id_kandang",""+dataListKandang.get(position).getId());
                edit.putExtra("nama_kandang", dataListKandang.get(position).getNamaKandang());
                edit.putExtra("lokasi_kandang", dataListKandang.get(position).getLokasiKandang());
                edit.putExtra("luas_kandang", ""+ dataListKandang.get(position).getLuasKandang());
                edit.putExtra("kapasitas_kandang", ""+dataListKandang.get(position).getKapasitasKandang());
                startActivity(edit);
            }

            @Override
            public void onItemDeleteClick(int position) {
                AlertDialog.Builder deleteMsg = new AlertDialog.Builder(KelolaKandangActivity.this);
                deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                deleteMsg.setMessage("Anda akan menghapus data \""+dataListKandang.get(position).getNamaKandang()+ "\" \n"+
                        "Tekan tombol ok jika anda yakin");
                deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.daoHewan().deleteKandang(dataListKandang.get(position));
                        dataListKandang.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeRemoved(position, dataListKandang.size());
                        Toast.makeText(KelolaKandangActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
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
            AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaKandangActivity.this);
            succesMsg.setTitle("Inputan Berhasil");
            succesMsg.setMessage("Anda berhasil memasukan kandang \""+hasil.getStringExtra("nama_kandang")+ "\" dalam menu kelola kandang");
            succesMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            succesMsg.show();
        }


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
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(KelolaKandangActivity.this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}