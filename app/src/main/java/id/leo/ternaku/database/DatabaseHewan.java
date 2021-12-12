package id.leo.ternaku.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TabelHewan.class , TabelKandang.class, TabelObat.class, TabelPengguna.class} ,version = 1)
public abstract class DatabaseHewan extends RoomDatabase {
    public abstract DaoHewan daoHewan();
    private static DatabaseHewan INSTANCE;
    public static DatabaseHewan getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseHewan.class,"db_hewan")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
