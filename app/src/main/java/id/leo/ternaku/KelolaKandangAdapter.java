package id.leo.ternaku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.leo.ternaku.database.TabelKandang;

public class KelolaKandangAdapter  extends RecyclerView.Adapter<KelolaKandangAdapter.viewHolder>{
    private List<TabelKandang> dataKandang = new ArrayList<>();
    private onItemClickListenerAdapterKandang listenerAdapterKandang;


    public KelolaKandangAdapter(List<TabelKandang> dataKandang) {
        this.dataKandang = dataKandang;
    }

    public interface onItemClickListenerAdapterKandang{
        void onItemDetailClick(int position);
        void onItemUpdateClick(int position);
        void onItemDeleteClick(int position);
    }

    public void setListenerAdapterKandang(onItemClickListenerAdapterKandang listenerAdapterKandang) {
        this.listenerAdapterKandang = listenerAdapterKandang;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kandang_view,parent,false);
        return new viewHolder(view,listenerAdapterKandang);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.namaKandang.setText(dataKandang.get(position).getNamaKandang());
        holder.lokasiKandang.setText("Lokasi: "+ dataKandang.get(position).getLokasiKandang());
    }

    @Override
    public int getItemCount() {
        return dataKandang.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView namaKandang, lokasiKandang;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView, onItemClickListenerAdapterKandang listener) {
            super(itemView);
            namaKandang = itemView.findViewById(R.id.textViewNamaKandangAdapter);
            lokasiKandang = itemView.findViewById(R.id.textViewLokasiKandangAdapter);
            edit = itemView.findViewById(R.id.imageViewEditKandang);
            delete = itemView.findViewById(R.id.imageViewDeleteKandang);
            detail = itemView.findViewById(R.id.imageViewDetailKandang);

            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemDetailClick(position);
                        }
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemDeleteClick(position);
                        }
                    }
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemUpdateClick(position);
                        }
                    }
                }
            });
        }
    }
}
