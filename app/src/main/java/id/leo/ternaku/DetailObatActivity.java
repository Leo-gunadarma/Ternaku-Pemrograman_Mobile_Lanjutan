package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailObatActivity extends AppCompatActivity {
    private TextView namaObat,jumlahObat,deskripsiObat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_obat);
        namaObat = findViewById(R.id.textViewResultNamaObat);
        jumlahObat = findViewById(R.id.textViewResultJumlahObat);
        deskripsiObat = findViewById(R.id.textViewResultDeskripsiObat);

        if (getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            namaObat.setText(hasil.getStringExtra("nama_obat"));
            jumlahObat.setText(hasil.getStringExtra("jumlah_obat"));
            deskripsiObat.setText(hasil.getStringExtra("deskripsi_obat"));
        }
    }
}