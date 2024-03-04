package ByteCoders.Model;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Paiement {
    private Double montant;
    private String date;
    private Facture facture;

}
