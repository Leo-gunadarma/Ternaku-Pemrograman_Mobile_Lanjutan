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
import id.leo.ternaku.database.TabelHewan;

public class KelolaHewanAdapter extends RecyclerView.Adapter<KelolaHewanAdapter.viewHolder>{
    private DatabaseHewan database;
    private Context context;
    private List<TabelHewan> dataHewan= new ArrayList<>();

    public KelolaHewanAdapter(Context context, List<TabelHewan> dataHewan) {
        this.context = context;
        this.dataHewan = dataHewan;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_hewan_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.namaHewan.setText(dataHewan.get(position).getNamaHewan());
        holder.jumlahHewan.setText("Jumlah Hewan: "+dataHewan.get(position).getJumlahHewan());
        database = DatabaseHewan.getDbInstance(context);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(context,TambahHewanActivity.class);
                edit.putExtra("id_hewan",""+dataHewan.get(position).getId());
                edit.putExtra("nama_hewan", dataHewan.get(position).getNamaHewan());
                edit.putExtra("jumlah_hewan", ""+ dataHewan.get(position).getJumlahHewan());
                edit.putExtra("ras_hewan", dataHewan.get(position).getRasHewan());
                edit.putExtra("jenis_hewan", dataHewan.get(position).getJenisHewan());
                edit.putExtra("jadwal_makan", dataHewan.get(position).getJadwalMakan());
                context.startActivity(edit);
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context,DetailHewanActivity.class);
                detail.putExtra("nama_hewan", dataHewan.get(position).getNamaHewan());
                detail.putExtra("jumlah_hewan", ""+ dataHewan.get(position).getJumlahHewan());
                detail.putExtra("ras_hewan", dataHewan.get(position).getRasHewan());
                detail.putExtra("jenis_hewan", dataHewan.get(position).getJenisHewan());
                detail.putExtra("jadwal_makan", dataHewan.get(position).getJadwalMakan());
                context.startActivity(detail);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteMsg = new AlertDialog.Builder(context);
                deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                deleteMsg.setMessage("Anda akan menghapus data \""+dataHewan.get(position).getNamaHewan()+ "\" \n"+
                        "Tekan tombol ok jika anda yakin");
                deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.daoHewan().deleteHewan(dataHewan.get(position));
                        dataHewan.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, dataHewan.size());
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

    }

    @Override
    public int getItemCount() {
        return dataHewan.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView namaHewan,jumlahHewan;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaHewan = itemView.findViewById(R.id.textViewNamaHewan);
            jumlahHewan = itemView.findViewById(R.id.textViewJumlahHewan);
            edit = itemView.findViewById(R.id.imageViewEditHewan);
            delete = itemView.findViewById(R.id.imageViewDeleteHewan);
            detail = itemView.findViewById(R.id.imageViewDetailHewan);

        }
    }
}
