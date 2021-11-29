package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView kelolaHewan,kelolaKandang,kelolaObat,pengaturanProfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kelolaHewan = findViewById(R.id.imageViewKelolaHewan);
        kelolaKandang = findViewById(R.id.imageViewKelolaKandang);
        kelolaObat = findViewById(R.id.imageViewMenuKelolaObat);
        pengaturanProfil = findViewById(R.id.imageViewMenuPengaturanProfil);
        kelolaHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKelolaHewan();
            }
        });
        kelolaKandang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKelolaKandang();
            }
        });
        kelolaObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuKelolaObat();
            }
        });
        pengaturanProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPengaturanProfil();
            }
        });
    }
    public void menuKelolaHewan(){
        Intent intent = new Intent(MainActivity.this, KelolaHewanActivity.class);
        startActivity(intent);
    }
    public void menuKelolaKandang(){
        Intent intent = new Intent(MainActivity.this, KelolaKandangActivity.class);
        startActivity(intent);
    }
    public void menuKelolaObat(){
        Intent intent = new Intent(MainActivity.this, KelolaObatActivity.class);
        startActivity(intent);
    }
    public void menuPengaturanProfil(){
        Toast toast = Toast.makeText(this, "Menu dalam pembuatan", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}