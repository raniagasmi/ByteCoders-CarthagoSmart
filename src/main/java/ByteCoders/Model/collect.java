package ByteCoders.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class collect {
    private int typeId;
    private String nomType;
    private Categorie categorie;
    private String PointRamassage;
    private String DateRamassage;
    private String PointRecyclage;

    public collect(int typeId, String nomType, Categorie categorie, String PointRamassage, String DateRamassage, String PointRecyclage) {
        this.typeId = typeId;
        this.nomType = nomType;
        this.categorie = categorie;
        this.PointRamassage = PointRamassage;
        this.DateRamassage = DateRamassage;
        this.PointRecyclage = PointRecyclage;
    }


    public static List<collect> collectList = new ArrayList<>();
    public collect(int typeId, String nomType, Categorie categorie) {
        this.typeId = typeId;
        this.nomType = nomType;
        this.categorie = categorie;
    }

    public collect(String nomType, Categorie categorie) {
        this.nomType = nomType;
        this.categorie = categorie;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }


    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public collect(String PointRamassage, String DateRamassage, String PointRecyclage) {
        this.PointRamassage = PointRamassage;
        this.DateRamassage = DateRamassage;
        this.PointRecyclage = PointRecyclage;
    }

    public collect(String PointRamassage, String DateRamassage) {
        this.PointRamassage = PointRamassage;
        this.DateRamassage = DateRamassage;

    }


    public String getPointRamassage() {
        return PointRamassage;
    }

    public void setPointRamassage(String PointRamassage) {
        this.PointRamassage = PointRamassage;
    }

    public String getDateRamassage() {
        return DateRamassage;
    }

    public void setDateRamassage(String DateRamassage) {
        this.DateRamassage = DateRamassage;
    }

    public String getPointRecyclage() {
        return PointRecyclage;
    }

    public void setPointRecyclage(String PointRecyclage) {
        this.PointRecyclage = PointRecyclage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        collect collect = (collect) o;
        return typeId == collect.typeId && Objects.equals(nomType, collect.nomType) && categorie == collect.categorie && Objects.equals(PointRamassage, collect.PointRamassage) && Objects.equals(DateRamassage, collect.DateRamassage) && Objects.equals(PointRecyclage, collect.PointRecyclage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, nomType, categorie, PointRamassage, DateRamassage, PointRecyclage);
    }

    @Override
    public String toString() {
        return "collect{" +
                "typeId=" + typeId +
                ", nomType='" + nomType + '\'' +
                ", categorie=" + categorie +
                ", PointRamassage='" + PointRamassage + '\'' +
                ", DateRamassage='" + DateRamassage + '\'' +
                ", PointRecyclage='" + PointRecyclage + '\'' +
                '}';
    }

}




