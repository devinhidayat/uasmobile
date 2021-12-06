package id.ac.umn.hidayat.projectuas;

public class UserHelperClass {

    String jam_checkin, jam_checkout;
    int total;

    public UserHelperClass() {}

    public UserHelperClass(String jam_checkin, String jam_checkout, int total) {
        this.jam_checkin = jam_checkin;
        this.jam_checkout = jam_checkout;
        this.total = total;
    }

    public String getJam_checkin() {
        return jam_checkin;
    }

    public String getJam_checkout() {
        return jam_checkout;
    }

    public int getTotal() {
        return total;
    }
}
