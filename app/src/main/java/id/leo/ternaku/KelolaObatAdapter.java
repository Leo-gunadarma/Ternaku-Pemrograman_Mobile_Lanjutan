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
import id.leo.ternaku.database.TabelObat;

public class KelolaObatAdapter extends RecyclerView.Adapter<KelolaObatAdapter.viewHolder> {

    private DatabaseHewan database;
    private Context context;
    List<TabelObat> dataObat = new ArrayList<>();

    public KelolaObatAdapter(Context context, List<TabelObat> dataObat) {
        this.context = context;
        this.dataObat = dataObat;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_obat_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.namaObat.setText(dataObat.get(position).getNamaObat());
        holder.jumlahObat.setText("Jumlah Obat: "+dataObat.get(position).getJumlahObat());
        database = DatabaseHewan.getDbInstance(context);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(position,DetailObatActivity.class);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(position,TambahObatActivity.class);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteMsg = new AlertDialog.Builder(context);
                deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                deleteMsg.setMessage("Anda akan menghapus data \""+dataObat.get(position).getNamaObat()+ "\" \n"+
                        "Tekan tombol ok jika anda yakin");
                deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.daoHewan().deleteObat(dataObat.get(position));
                        dataObat.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, dataObat.size());
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
        return dataObat.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView namaObat,jumlahObat;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaObat = itemView.findViewById(R.id.textViewNamaObatAdapter);
            jumlahObat = itemView.findViewById(R.id.textViewJumlahObatAdapter);

            edit = itemView.findViewById(R.id.imageViewEditObat);
            delete = itemView.findViewById(R.id.imageViewDeleteObat);
            detail = itemView.findViewById(R.id.imageViewDetailObat);
        }
    }

    public void passData (int position, Class loc){
        Intent detail = new Intent(context,loc);
        detail.putExtra("id_obat", ""+dataObat.get(position).getId());
        detail.putExtra("nama_obat", dataObat.get(position).getNamaObat());
        detail.putExtra("jumlah_obat", ""+ dataObat.get(position).getJumlahObat());
        detail.putExtra("deskripsi_obat", dataObat.get(position).getDeskripsiObat());
        context.startActivity(detail);
    }
}
