package id.ac.umn.hidayat.projectuas;

public class User {

    public String regPassword;
    public String regEmail;
    public String currentuser ;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String regPassword, String regEmail, String currentuser) {
        this.regPassword = regPassword;
        this.regEmail = regEmail;
        this.currentuser = currentuser;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }
}