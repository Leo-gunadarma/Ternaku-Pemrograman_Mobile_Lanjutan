package id.leo.ternaku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_hewan")
public class TabelHewan {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_hewan")
    String namaHewan;
    @ColumnInfo (name = "ras_hewan")
    String rasHewan;
    @ColumnInfo (name = "jenis_hewan")
    String jenisHewan;
    @ColumnInfo (name = "jumlah_hewan")
    int jumlahHewan;
    @ColumnInfo (name = "jadwal_makan")
    String jadwalMakan;

    public TabelHewan(String namaHewan, String rasHewan, String jenisHewan, int jumlahHewan, String jadwalMakan) {
        this.namaHewan = namaHewan;
        this.rasHewan = rasHewan;
        this.jenisHewan = jenisHewan;
        this.jumlahHewan = jumlahHewan;
        this.jadwalMakan = jadwalMakan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public String getRasHewan() {
        return rasHewan;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public int getJumlahHewan() {
        return jumlahHewan;
    }

    public String getJadwalMakan() {
        return jadwalMakan;
    }
}
