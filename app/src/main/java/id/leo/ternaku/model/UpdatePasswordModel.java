package id.leo.ternaku.model;

public class UpdatePasswordModel {
    String oldPassTy, oldPass, newPass, reNewPass;

    public UpdatePasswordModel(String oldPassTy, String oldPass, String newPass, String reNewPass) {
        this.oldPassTy = oldPassTy;
        this.oldPass = oldPass;
        this.newPass = newPass;
        this.reNewPass = reNewPass;
    }

    public String getOldPassTy() {
        return oldPassTy;
    }

    public void setOldPassTy(String oldPassTy) {
        this.oldPassTy = oldPassTy;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getReNewPass() {
        return reNewPass;
    }

    public void setReNewPass(String reNewPass) {
        this.reNewPass = reNewPass;
    }
}
