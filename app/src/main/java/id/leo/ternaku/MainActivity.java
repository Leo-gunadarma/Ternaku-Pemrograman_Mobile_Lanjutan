package id.leo.ternaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import id.leo.ternaku.helper.SessionManagement;

public class MainActivity extends AppCompatActivity {
    ImageView kelolaHewan,kelolaKandang,kelolaObat,pengaturanProfil;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SessionManagement session = new SessionManagement(MainActivity.this);

        kelolaHewan = findViewById(R.id.imageViewKelolaHewan);
        kelolaKandang = findViewById(R.id.imageViewKelolaKandang);
        kelolaObat = findViewById(R.id.imageViewMenuKelolaObat);
        pengaturanProfil = findViewById(R.id.imageViewMenuPengaturanProfil);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.removeSession();
                Toast toast = Toast.makeText(MainActivity.this, "Berhasil Logout", Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        });

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
        Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}