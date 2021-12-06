package id.ac.umn.hidayat.projectuas;

public class data {
    String checkIn;
    String checkOut;
    String currentUser;

    public data() {
    }
    public data(String checkIn, String checkOut, String currentUser) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.currentUser = currentUser;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
