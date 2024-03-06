package ByteCoders.Model;

public class Consommation {
    private int id;
    private String date;
    private String type;
    private int quantite;
    private float montant;
    private int idUser;


    public Consommation() {
    }

    public Consommation(int id, String date, String type, int quantite, float montant, int idUser) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.quantite = quantite;
        this.montant = montant;
        this.idUser = idUser;
    }

    public Consommation(String date, String type, int quantite, float montant, int idUser) {
        this.date = date;
        this.type = type;
        this.quantite = quantite;
        this.montant = montant;
        this.idUser = idUser;
    }

    // sql to create table consommation

    public String toSQL(){
        return "CREATE TABLE `consommation` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `date` date NOT NULL,\n" +
                "  `type` varchar(255) NOT NULL,\n" +
                "  `quantite` int(11) NOT NULL,\n" +
                "  `montant` float NOT NULL,\n" +
                "  `idUser` int(11) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `idUser` (`idUser`),\n" +
                "  CONSTRAINT `consommation_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
    }
}
