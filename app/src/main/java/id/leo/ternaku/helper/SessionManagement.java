package id.leo.ternaku.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;
    String SHARED_PREF_NAME = "session";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
        sharedEditor.apply();
    }

    public void saveSession(int id, String nama){
        sharedEditor.putInt("id", id);
        sharedEditor.putString("nama", nama);
        sharedEditor.putBoolean("login", true);
        sharedEditor.apply();
    }

    public void removeSession(){
        sharedEditor.clear();
        sharedEditor.apply();
    }

    public Boolean statusLogin(){
        return sharedPreferences.getBoolean("login", false);
    }

    public String getNama(){
        return sharedPreferences.getString("nama", "");
    }
    public int getId(){
        return sharedPreferences.getInt("id", 0);
    }

}
