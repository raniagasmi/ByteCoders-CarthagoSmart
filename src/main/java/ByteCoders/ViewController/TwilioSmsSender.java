package ByteCoders.ViewController;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;
public class TwilioSmsSender {

    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACcd4ff1c9cfa58bf3526dc66edd6a4e6c";
    public static final String AUTH_TOKEN = "33645e7909249631e8b885ac82f0e05d";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    public static void sendSms(int toPhoneNumber, String messageBody){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+21654331435"),
                new PhoneNumber("+12517325441"),
                "Il y'a avait une connexion au compte CarthagoSmart à l'instant"
        ) .create();

        System.out.println("SMS Sent with SID: " + message.getSid());
    }
}
