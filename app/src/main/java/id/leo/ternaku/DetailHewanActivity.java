package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailHewanActivity extends AppCompatActivity {
    private TextView namaHewan,jumlahHewan,rasHewan,jadwalMakan,jenisHewan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hewan);
        namaHewan = findViewById(R.id.textViewResultNamaHewan);
        jumlahHewan = findViewById(R.id.textViewResultJumlahHewan);
        rasHewan =  findViewById(R.id.textViewResultRasHewan);
        jadwalMakan = findViewById(R.id.textViewResultJadwalMakan);
        jenisHewan = findViewById(R.id.textViewResultJenisHewan);
        if (getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            namaHewan.setText(hasil.getStringExtra("nama_hewan"));
            jumlahHewan.setText(hasil.getStringExtra("jumlah_hewan"));
            rasHewan.setText(hasil.getStringExtra("ras_hewan"));
            jadwalMakan.setText(hasil.getStringExtra("jadwal_makan"));
            jenisHewan.setText(hasil.getStringExtra("jenis_hewan"));
        }
    }
}