package id.leo.ternaku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_kandang")
public class TabelKandang {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_kandang")
    String namaKandang;
    @ColumnInfo (name = "lokasi_kandang")
    String lokasiKandang;
    @ColumnInfo (name = "luas_kandang")
    int luasKandang;
    @ColumnInfo (name = "kapasitas_kandang")
    int kapasitasKandang;

    public TabelKandang(String namaKandang, String lokasiKandang, int luasKandang, int kapasitasKandang) {
        this.namaKandang = namaKandang;
        this.lokasiKandang = lokasiKandang;
        this.luasKandang = luasKandang;
        this.kapasitasKandang = kapasitasKandang;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNamaKandang() {
        return namaKandang;
    }

    public String getLokasiKandang() {
        return lokasiKandang;
    }

    public int getLuasKandang() {
        return luasKandang;
    }

    public int getKapasitasKandang() {
        return kapasitasKandang;
    }
}
