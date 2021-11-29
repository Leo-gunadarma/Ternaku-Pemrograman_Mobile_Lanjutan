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
        database = DatabaseHewan.getDbInstance(this);
        recyclerView = findViewById(R.id.recyclerViewKelolaObat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataListObat = database.daoHewan().getAllDataObat();
        adapter = new KelolaObatAdapter(dataListObat);
        recyclerView.setAdapter(adapter);
        addButton = findViewById(R.id.addButtonObat);

        adapter.setListenerAdapterObat(new KelolaObatAdapter.onItemClickListenerAdapterObat() {
            @Override
            public void onItemDetailClick(int position) {
                Intent detail = new Intent(KelolaObatActivity.this,DetailObatActivity.class);
                detail.putExtra("nama_obat", dataListObat.get(position).getNamaObat());
                detail.putExtra("jumlah_obat", ""+ dataListObat.get(position).getJumlahObat());
                detail.putExtra("deskripsi_obat", dataListObat.get(position).getDeskripsiObat());
                startActivity(detail);
            }

            @Override
            public void onItemUpdateClick(int position) {
                Intent edit = new Intent(KelolaObatActivity.this,TambahObatActivity.class);
                edit.putExtra("id_obat", ""+dataListObat.get(position).getId());
                edit.putExtra("nama_obat", dataListObat.get(position).getNamaObat());
                edit.putExtra("jumlah_obat", ""+ dataListObat.get(position).getJumlahObat());
                edit.putExtra("deskripsi_obat", dataListObat.get(position).getDeskripsiObat());
                startActivity(edit);

            }

            @Override
            public void onItemDeleteClick(int position) {
                AlertDialog.Builder deleteMsg = new AlertDialog.Builder(KelolaObatActivity.this);
                deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                deleteMsg.setMessage("Anda akan menghapus data \""+dataListObat.get(position).getNamaObat()+ "\" \n"+
                        "Tekan tombol ok jika anda yakin");
                deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.daoHewan().deleteObat(dataListObat.get(position));
                        dataListObat.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeRemoved(position, dataListObat.size());
                        Toast.makeText(KelolaObatActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
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
            AlertDialog.Builder succesMsg = new AlertDialog.Builder(KelolaObatActivity.this);
            succesMsg.setTitle("Inputan Berhasil");
            succesMsg.setMessage("Anda berhasil memasukan Obat \""+hasil.getStringExtra("nama_obat")+ "\" dalam menu kelola obat");
            succesMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            succesMsg.show();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahData();
            }
        });
    }

    public void tambahData(){
        Intent intent= new Intent(KelolaObatActivity.this , TambahObatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(KelolaObatActivity.this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}