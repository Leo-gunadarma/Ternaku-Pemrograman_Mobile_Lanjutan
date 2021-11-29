package id.leo.ternaku.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoHewan {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDataHewan (TabelHewan ... tabelHewans);

    @Update
    void  updateHewan(TabelHewan tabelHewan);

    @Delete
    void deleteHewan(TabelHewan tabelHewan);

    @Query("SELECT * FROM tb_hewan")
    List<TabelHewan> getAllDataHewan();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertDataKandang (TabelKandang ... tabelKandangs);

    @Update
    void  updateKandang (TabelKandang tabelKandang);

    @Delete
    void deleteKandang(TabelKandang tabelKandang);

    @Query("SELECT * FROM tb_kandang ")
    List<TabelKandang> getAllDataKandang();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertDataObat (TabelObat ... tabelObats);

    @Update
    void updateObat (TabelObat tabelObat);

    @Delete
    void deleteObat(TabelObat tabelObat);

    @Query("SELECT * FROM tb_obat ")
    List<TabelObat> getAllDataObat();
}
