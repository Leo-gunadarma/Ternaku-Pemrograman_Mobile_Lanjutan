package id.leo.ternaku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_obat")
public class TabelObat {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_obat")
    String namaObat;
    @ColumnInfo (name = "jumlah_obat")
    int jumlahObat;
    @ColumnInfo (name = "deskripsi_obat")
    String deskripsiObat;
    @ColumnInfo (name = "peraturan")
    String peraturan;

    public TabelObat(String namaObat, int jumlahObat, String deskripsiObat, String peraturan) {
        this.namaObat = namaObat;
        this.jumlahObat = jumlahObat;
        this.deskripsiObat = deskripsiObat;
        this.peraturan = peraturan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public int getJumlahObat() {
        return jumlahObat;
    }

    public String getDeskripsiObat() {
        return deskripsiObat;
    }

    public String getPeraturan() {
        return peraturan;
    }
}
