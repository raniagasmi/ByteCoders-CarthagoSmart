package ByteCoders.Model;

import java.util.Objects;

public class TypeDechets {
    private int typeId;
    private String nomType;
    private Categorie categorie;

    public TypeDechets() {
    }

    public TypeDechets(int typeId ,String nomType , Categorie categorie) {
        this.typeId = typeId;
        this.nomType=nomType;
        this.categorie=categorie;
    }

    public TypeDechets(String nomType, Categorie categorie) {
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

    public void setNom(String nom) {
        this.nomType = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDechets that = (TypeDechets) o;
        return typeId == that.typeId && Objects.equals(nomType, that.nomType) && categorie == that.categorie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, nomType, categorie);
    }

    @Override
    public String toString() {
        return "TypeDechets{" +
                "typeId=" + typeId +
                ", nom='" + nomType + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
