package id.leo.ternaku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.database.TabelHewan;

public class TambahHewanActivity extends AppCompatActivity {
    private DatabaseHewan database;
    private EditText namaHewan, jumlahHewan, rasHewan;
    private Spinner jenisHewan;
    private Button tombolSimpan;
    private RadioButton selected;
    private RadioGroup radioGroup;
    private RadioButton radioPagiSiang , radioPagiSore, radioSiangSore;
    private TextView judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_hewan);
        database = DatabaseHewan.getDbInstance(this.getApplicationContext());
        namaHewan = findViewById(R.id.editTextNamaHewan);
        rasHewan = findViewById(R.id.editTextRasHewan);
        jenisHewan = findViewById(R.id.spinnerJenisHewan);
        jumlahHewan = findViewById(R.id.editTextJumlahHewan);
        radioGroup = findViewById(R.id.radioGroupJadwalMakan);
        radioPagiSiang = findViewById(R.id.radioButtonPagiSiang);
        radioPagiSore = findViewById(R.id.radioButtonPagiSore);
        radioSiangSore = findViewById(R.id.radioButtonSiangSore);
        tombolSimpan = findViewById(R.id.buttonSimpan);
        judul = findViewById(R.id.textViewJudulFormTambahHewan);

        if (getIntent().getExtras()!= null){
            Intent hasil = getIntent();
            int id_hewan= Integer.parseInt(hasil.getStringExtra("id_hewan"));
            int jenisHewanIdSpinner = 0;
            String jenisHewanEdit = hasil.getStringExtra("jenis_hewan");
            String bertelur= "Ovipar (Bertelur)";
            String melahirkan = "Vivipar (Malahirkan)";
            String bertelurMelahirkan = "Ovovivipar (Bertelur dan Melahirkan)";
            if (jenisHewanEdit.equals(bertelur)){
                jenisHewanIdSpinner = 1;
            }else if (jenisHewanEdit.equals(melahirkan)){
                jenisHewanIdSpinner = 2;
            }else if (jenisHewanEdit.equals(bertelurMelahirkan)){
                jenisHewanIdSpinner = 3;
            }

            String jadwalMakanEdit = hasil.getStringExtra("jadwal_makan");
            String PagiSiang = radioPagiSiang.getText().toString();
            String SiangSore = radioSiangSore.getText().toString();
            String PagiSore =  radioPagiSore.getText().toString();
            if (jadwalMakanEdit.equals(PagiSiang)){
                radioPagiSiang.setChecked(true);
            }else if (jadwalMakanEdit.equals(SiangSore)){
                radioSiangSore.setChecked(true);
            }else if (jadwalMakanEdit.equals(PagiSore)){
                radioPagiSore.setChecked(true);
            }

            namaHewan.setText(hasil.getStringExtra("nama_hewan"));
            rasHewan.setText(hasil.getStringExtra("ras_hewan"));
            jumlahHewan.setText(hasil.getStringExtra("jumlah_hewan"));
            jenisHewan.setSelection(jenisHewanIdSpinner);
            judul.setText("Form Perbarui Hewan Ternak");
            tombolSimpan.setText("Perbarui Data");
            tombolSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateData(id_hewan);
                }
            });
        }else{
            tombolSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    simpanData();
                }
            });
        }
    }

    public void updateData(int id_hewan){
        if (namaHewan.getText().toString().trim().length() == 0){
            //method trim digunakan untuk menghapus spasi
            Toast toast = Toast.makeText(this, "Nama Hewan Belum diisi", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if (rasHewan.getText().toString().trim().length() == 0){
            Toast toast = Toast.makeText(this, "Ras Hewan Belum diisi", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if (jenisHewan.getSelectedItemPosition() == 0){
            Toast toast = Toast.makeText(this, "Jenis Hewan Belum dirubah", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if (jumlahHewan.getText().toString().trim().length() == 0){
            Toast toast = Toast.makeText(this, "Banyak Hewan Belum diisi", Toast.LENGTH_LONG);
            toast.show();
            return;
        }


        if (radioGroup.getCheckedRadioButtonId() == -1){
            Toast toast = Toast.makeText(this, "Jadwal makan belum dipilih", Toast.LENGTH_LONG);
            toast.show();
            return;
        }else{
            int select = radioGroup.getCheckedRadioButtonId();
            selected = findViewById(select);
        }
        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahHewanActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin memperbarui data sebagai berikut?\n\n"+
                "Nama Hewan: "+ namaHewan.getText().toString()+ "\n"+
                "Ras Hewan: " + rasHewan.getText().toString() + "\n"+
                "Jenis Hewan: " + jenisHewan.getSelectedItem().toString() + "\n"+
                "Jumlah Hewan: " + jumlahHewan.getText().toString() + "\n" +
                "Jadwal Makan: "+ selected.getText().toString() + "\n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nmHwn = namaHewan.getText().toString();
                String rsHwn = rasHewan.getText().toString();
                String jnsHwn = jenisHewan.getSelectedItem().toString();
                String jmlhHwn = jumlahHewan.getText().toString();
                int finalJmlhHwn = Integer.parseInt(jmlhHwn);
                String jadwalMakan = selected.getText().toString();
                TabelHewan row = new TabelHewan(nmHwn,rsHwn,jnsHwn,finalJmlhHwn,jadwalMakan);
                row.setId(id_hewan);
                database.daoHewan().updateHewan(row);
//                Intent intent = new Intent(TambahHewanActivity.this, KelolaHewanActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        infoMsg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaHewan.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();

    }

    public void simpanData(){
        if (namaHewan.getText().toString().trim().length() == 0){
            //method trim digunakan untuk menghapus spasi
            Toast toast = Toast.makeText(this, "Nama Hewan Belum diisi", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if (rasHewan.getText().toString().trim().length() == 0){
            Toast toast = Toast.makeText(this, "Ras Hewan Belum diisi", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if (jenisHewan.getSelectedItemPosition() == 0){
            Toast toast = Toast.makeText(this, "Jenis Hewan Belum dirubah", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if (jumlahHewan.getText().toString().trim().length() == 0){
            Toast toast = Toast.makeText(this, "Banyak Hewan Belum diisi", Toast.LENGTH_LONG);
            toast.show();
            return;
        }


        if (radioGroup.getCheckedRadioButtonId() == -1){
            Toast toast = Toast.makeText(this, "Jadwal makan belum dipilih", Toast.LENGTH_LONG);
            toast.show();
            return;
        }else{
            int select = radioGroup.getCheckedRadioButtonId();
            selected = findViewById(select);
        }
        AlertDialog.Builder infoMsg = new AlertDialog.Builder(TambahHewanActivity.this);
        infoMsg.setTitle("Apakah Anda Yakin?");
        infoMsg.setMessage("Apakah anda yakin ingin menginputkan data sebagai berikut?\n\n"+
                "Nama Hewan: "+ namaHewan.getText().toString()+ "\n"+
                "Ras Hewan: " + rasHewan.getText().toString() + "\n"+
                "Jenis Hewan: " + jenisHewan.getSelectedItem().toString() + "\n"+
                "Jumlah Hewan: " + jumlahHewan.getText().toString() + "\n" +
                "Jadwal Makan: "+ selected.getText().toString() + "\n");
        infoMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nmHwn = namaHewan.getText().toString();
                String rsHwn = rasHewan.getText().toString();
                String jnsHwn = jenisHewan.getSelectedItem().toString();
                String jmlhHwn = jumlahHewan.getText().toString();
                int finalJmlhHwn = Integer.parseInt(jmlhHwn);
                String jadwalMakan = selected.getText().toString();
                TabelHewan row = new TabelHewan(nmHwn,rsHwn,jnsHwn,finalJmlhHwn,jadwalMakan);
                database.daoHewan().insertDataHewan(row);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nama_hewan", nmHwn);
                setResult(RESULT_OK, resultIntent);

//                Intent intent = new Intent(TambahHewanActivity.this, KelolaHewanActivity.class);
//                intent.putExtra("nama_hewan", nmHwn);
//                startActivity(intent);
                finish();
            }
        });
        infoMsg.setNegativeButton("Perbarui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                namaHewan.requestFocus();
                dialogInterface.dismiss();
            }
        });
        infoMsg.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCurrentFocus();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Kamu pindah kehalaman lain", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Selamat Datang Kembali", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Kembali ke menu kelola hewan", Toast.LENGTH_SHORT).show();
    }



}