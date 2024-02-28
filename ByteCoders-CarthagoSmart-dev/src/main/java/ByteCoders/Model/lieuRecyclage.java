package ByteCoders.Model;

import ByteCoders.Model.TypeDechets;

import java.util.List;
import java.util.Objects;

public class lieuRecyclage {
    private String PointRecyclage;
    private List<TypeDechets> DechetsAcceptes;

    public lieuRecyclage(String PointRecyclage, List DechetsAcceptes) {
        this.PointRecyclage = PointRecyclage;
        this.DechetsAcceptes = DechetsAcceptes;
    }

    public String getPointRecyclage() {
        return PointRecyclage;
    }

    public void setPointRecyclage(String pointRecyclage) {
        PointRecyclage = pointRecyclage;
    }

    public List<TypeDechets> getDechetsAcceptes() {
        return DechetsAcceptes;
    }

    public List<TypeDechets> getTypesDechetsAcceptes() {
        return DechetsAcceptes;
    }

    public void setDechetsAcceptes(List<TypeDechets> DechetsAcceptes) {
        this.DechetsAcceptes = DechetsAcceptes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        lieuRecyclage that = (lieuRecyclage) o;
        return Objects.equals(PointRecyclage, that.PointRecyclage) && Objects.equals(DechetsAcceptes, that.DechetsAcceptes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PointRecyclage, DechetsAcceptes);
    }

    @Override
    public String toString() {
        return "lieuRecyclage{" +
                "PointRecyclage='" + PointRecyclage + '\'' +
                ", DechetsAcceptes=" + DechetsAcceptes +
                '}';
    }
}
