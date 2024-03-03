package ByteCoders.ViewController;

import java.time.ZonedDateTime;

public class CalendarActivity {
    private ZonedDateTime date;
    private String ramassage;


    public CalendarActivity(ZonedDateTime date, String ramassage) {
        this.date = date;
        this.ramassage = ramassage;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getRamassage() {
        return ramassage;
    }

    public void setRamassage(String ramassage) {
        this.ramassage = ramassage;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", ramassage='" + ramassage + '\'' +
                '}';
    }
}
