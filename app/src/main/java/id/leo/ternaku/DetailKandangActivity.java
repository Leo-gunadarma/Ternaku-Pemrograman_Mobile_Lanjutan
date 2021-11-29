package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailKandangActivity extends AppCompatActivity {
    private TextView namaKandang,lokasiKandang,luasKandang,kapasitasKandang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kandang);
        namaKandang = findViewById(R.id.textViewResultNamaKandang);
        lokasiKandang = findViewById(R.id.textViewResultLokasiKandang);
        luasKandang = findViewById(R.id.textViewResultLuasKandang);
        kapasitasKandang = findViewById(R.id.textViewResultKapasitasKandang);

        if (getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            namaKandang.setText(hasil.getStringExtra("nama_kandang"));
            lokasiKandang.setText(hasil.getStringExtra("lokasi_kandang"));
            luasKandang.setText(hasil.getStringExtra("luas_kandang"));
            kapasitasKandang.setText(hasil.getStringExtra("kapasitas_kandang"));
        }
    }
}