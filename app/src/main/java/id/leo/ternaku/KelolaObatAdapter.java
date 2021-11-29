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

import id.leo.ternaku.database.TabelObat;

public class KelolaObatAdapter extends RecyclerView.Adapter<KelolaObatAdapter.viewHolder> {

    List<TabelObat> dataObat = new ArrayList<>();
    private onItemClickListenerAdapterObat listenerAdapterObat;
    public KelolaObatAdapter(List<TabelObat> dataObat) {
        this.dataObat = dataObat;
    }


    public void setListenerAdapterObat(onItemClickListenerAdapterObat listenerAdapterObat) {
        this.listenerAdapterObat = listenerAdapterObat;
    }

    public interface onItemClickListenerAdapterObat{
        void onItemDetailClick(int position);
        void onItemUpdateClick(int position);
        void onItemDeleteClick(int position);
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_obat_view,parent,false);
        return new viewHolder(view, listenerAdapterObat);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.namaObat.setText(dataObat.get(position).getNamaObat());
        holder.jumlahObat.setText("Jumlah Obat: "+dataObat.get(position).getJumlahObat());
    }

    @Override
    public int getItemCount() {
        return dataObat.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView namaObat,jumlahObat;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView, onItemClickListenerAdapterObat listener) {
            super(itemView);
            namaObat = itemView.findViewById(R.id.textViewNamaObatAdapter);
            jumlahObat = itemView.findViewById(R.id.textViewJumlahObatAdapter);

            edit = itemView.findViewById(R.id.imageViewEditObat);
            delete = itemView.findViewById(R.id.imageViewDeleteObat);
            detail = itemView.findViewById(R.id.imageViewDetailObat);

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
