package id.leo.ternaku.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_pengguna")
public class TabelPengguna {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama_pengguna")
    String namaPengguna;
    @ColumnInfo (name = "email_pengguna")
    String emailPengguna;
    @ColumnInfo (name = "password_pengguna")
    String passwordPengguna;
    @ColumnInfo (name = "alamat_pengguna")
    String alamatPengguna;
    @ColumnInfo (name = "no_telp_pengguna")
    String noTelpPengguna;

    public TabelPengguna(String namaPengguna, String emailPengguna, String passwordPengguna, String alamatPengguna, String noTelpPengguna) {
        this.namaPengguna = namaPengguna;
        this.emailPengguna = emailPengguna;
        this.passwordPengguna = passwordPengguna;
        this.alamatPengguna = alamatPengguna;
        this.noTelpPengguna = noTelpPengguna;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPasswordPengguna() {
        return passwordPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getEmailPengguna() {
        return emailPengguna;
    }

    public String getAlamatPengguna() {
        return alamatPengguna;
    }

    public String getNoTelpPengguna() {
        return noTelpPengguna;
    }
}
