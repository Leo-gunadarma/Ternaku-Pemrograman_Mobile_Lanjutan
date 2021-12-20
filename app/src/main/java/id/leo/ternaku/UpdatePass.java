package id.leo.ternaku;

import android.content.Context;
import android.widget.Toast;

import id.leo.ternaku.database.DatabaseHewan;
import id.leo.ternaku.model.UpdatePasswordModel;

public class UpdatePass {
    Context context;
    DatabaseHewan database;
    UpdatePasswordModel updatePasswordModel;
    int idPengguna;

    public UpdatePass(Context context, DatabaseHewan database, UpdatePasswordModel updatePasswordModel, int idPengguna) {
        this.context = context;
        this.database = database;
        this.updatePasswordModel = updatePasswordModel;
        this.idPengguna = idPengguna;
    }

    public int processUpdatePass(){
        String OldPassTy = updatePasswordModel.getOldPassTy();
        String OldPass = updatePasswordModel.getOldPass();
        String NewPass = updatePasswordModel.getNewPass();
        String ReNewPass = updatePasswordModel.getReNewPass();

        if(OldPassTy.isEmpty() || NewPass.isEmpty() || ReNewPass.isEmpty()){
            Toast.makeText(context, "Lengkapi Data Terlebih Dahulu!", Toast.LENGTH_SHORT).show();
            return 0;
        }else{
            if(OldPassTy.equals(OldPass)){
                if(NewPass.equals(ReNewPass)){
                    if(OldPass.equals(NewPass)){
                        Toast.makeText(context, "Password Lama dan Password Baru Sama!", Toast.LENGTH_SHORT).show();
                        return 0;
                    }else{
                        database.daoHewan().updatePass(idPengguna,NewPass);
                        Toast.makeText(context, "Password Berhasil Diubah!",Toast.LENGTH_SHORT).show();
                        return 1;
//                        finish();
                    }
                }else{
                    Toast.makeText(context, "Password Baru Tidak Valid!", Toast.LENGTH_SHORT).show();
                    return 0;
                }
            }else{
                Toast.makeText(context, "Password Lama Salah!"+ OldPass + OldPassTy, Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
    }
}
