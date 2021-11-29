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

import id.leo.ternaku.database.TabelHewan;

public class KelolaHewanAdapter extends RecyclerView.Adapter<KelolaHewanAdapter.viewHolder>{
    private List<TabelHewan> dataHewan= new ArrayList<>();
    private onItemClickListenerAdapterHewan listenerAdapterHewan;

    public KelolaHewanAdapter(List<TabelHewan> dataHewan) {
        this.dataHewan = dataHewan;
    }

    public interface onItemClickListenerAdapterHewan{
        void onItemDetailClick(int position);
        void onItemUpdateClick(int position);
        void onItemDeleteClick(int position);
    }

    public void setListenerAdapterHewan(onItemClickListenerAdapterHewan listenerAdapterHewan) {
        this.listenerAdapterHewan = listenerAdapterHewan;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hewan_view,parent,false);
        return new viewHolder(view,listenerAdapterHewan);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.namaHewan.setText(dataHewan.get(position).getNamaHewan());
        holder.jumlahHewan.setText("Jumlah Hewan: "+dataHewan.get(position).getJumlahHewan());
    }

    @Override
    public int getItemCount() {
        return dataHewan.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView namaHewan,jumlahHewan;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView, onItemClickListenerAdapterHewan listener) {
            super(itemView);
            namaHewan = itemView.findViewById(R.id.textViewNamaHewan);
            jumlahHewan = itemView.findViewById(R.id.textViewJumlahHewan);
            edit = itemView.findViewById(R.id.imageViewEditHewan);
            delete = itemView.findViewById(R.id.imageViewDeleteHewan);
            detail = itemView.findViewById(R.id.imageViewDetailHewan);

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
