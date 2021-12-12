package id.leo.ternaku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelKandang;

public class KelolaKandangAdapter  extends RecyclerView.Adapter<KelolaKandangAdapter.viewHolder>{
    private DatabaseHewan database;
    private Context context;
    private List<TabelKandang> dataKandang = new ArrayList<>();

    public KelolaKandangAdapter(Context context, List<TabelKandang> dataKandang) {
        this.context = context;
        this.dataKandang = dataKandang;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kandang_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.namaKandang.setText(dataKandang.get(position).getNamaKandang());
        holder.lokasiKandang.setText("Lokasi: "+ dataKandang.get(position).getLokasiKandang());
        database = DatabaseHewan.getDbInstance(context);
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(context,TambahKandangActivity.class);
                edit.putExtra("id_kandang",""+dataKandang.get(position).getId());
                edit.putExtra("nama_kandang", dataKandang.get(position).getNamaKandang());
                edit.putExtra("lokasi_kandang", dataKandang.get(position).getLokasiKandang());
                edit.putExtra("luas_kandang", ""+ dataKandang.get(position).getLuasKandang());
                edit.putExtra("kapasitas_kandang", ""+dataKandang.get(position).getKapasitasKandang());
                context.startActivity(edit);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteMsg = new AlertDialog.Builder(context);
                deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                deleteMsg.setMessage("Anda akan menghapus data \""+dataKandang.get(position).getNamaKandang()+ "\" \n"+
                        "Tekan tombol ok jika anda yakin");
                deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.daoHewan().deleteKandang(dataKandang.get(position));
                        dataKandang.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, dataKandang.size());
                        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
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

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context,DetailKandangActivity.class);
                detail.putExtra("nama_kandang", dataKandang.get(position).getNamaKandang());
                detail.putExtra("lokasi_kandang", dataKandang.get(position).getLokasiKandang());
                detail.putExtra("luas_kandang", ""+ dataKandang.get(position).getLuasKandang()+" Meter Persegi");
                detail.putExtra("kapasitas_kandang", ""+dataKandang.get(position).getKapasitasKandang()+" Ekor");
                context.startActivity(detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataKandang.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView namaKandang, lokasiKandang;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaKandang = itemView.findViewById(R.id.textViewNamaKandangAdapter);
            lokasiKandang = itemView.findViewById(R.id.textViewLokasiKandangAdapter);
            edit = itemView.findViewById(R.id.imageViewEditKandang);
            delete = itemView.findViewById(R.id.imageViewDeleteKandang);
            detail = itemView.findViewById(R.id.imageViewDetailKandang);
        }
    }
}
