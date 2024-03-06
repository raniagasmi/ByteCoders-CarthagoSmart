package ByteCoders.Service;

import ByteCoders.Model.Facture;

public interface IPaiement extends ICrud<Facture> {
    public void payerFacture();
    public void annulerPaiement();
}
