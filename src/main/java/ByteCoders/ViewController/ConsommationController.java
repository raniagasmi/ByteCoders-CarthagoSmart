package ByteCoders.ViewController;


import ByteCoders.Model.Facture;
import ByteCoders.Model.Facture;
import ByteCoders.Service.FacturesService;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.List;

public class ConsommationController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label sumLabel, averageLabel, maxLabel, minLabel, numOfFacturesLabel;

    // Méthode pour initialiser les statistiques
    public void initStats(double sum, double average, double max, double min, int numOfFactures) {
        sumLabel.setText(String.valueOf(getSum()));
        averageLabel.setText(String.valueOf(getAverage()));
        maxLabel.setText(String.valueOf(max));
        minLabel.setText(String.valueOf(min));
        numOfFacturesLabel.setText(String.valueOf(numOfFactures));
    }

    // Méthode pour afficher les statistiques sous forme de graphique
    public void displayStatsChart(double sum, double average, double max, double min) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Sum", getSum()));
        series.getData().add(new XYChart.Data<>("Average", getAverage()));
        series.getData().add(new XYChart.Data<>("Max", getMax()));
        series.getData().add(new XYChart.Data<>("Min", getMin()));

        barChart.getData().add(series);
    }

    //create local variable factures
    private List<Facture> factures;

    public double getSum() {
        double sum = 0;
    for (Facture facture : factures) {
            sum += facture.getMontant();
        }
        return sum;
    }


    public double getAverage() {
        return getSum() / factures.size();
    }

    public double getMax() {
        double max = 0;
        for (Facture facture : factures) {
            if (facture.getMontant() > max) {
                max = facture.getMontant();
            }
        }
        return max;
    }

    public double getMin() {
        double min = factures.get(0).getMontant();
        for (Facture facture : factures) {
            if (facture.getMontant() < min) {
                min = facture.getMontant();
            }
        }
        return min;
    }

    public int getNumberOfFactures() {
        return factures.size();
    }

    public int getNumberOfFacturesPaid() {
        int count = 0;
        for (Facture facture : factures) {
            if (facture.isEstPayee()) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfFacturesNotPaid() {
        return getNumberOfFactures() - getNumberOfFacturesPaid();
    }

    public double getSumPaid() {
        double sum = 0;
        for (Facture facture : factures) {
            if (facture.isEstPayee()) {
                sum += facture.getMontant();
            }
        }
        return sum;
    }

    public double getSumNotPaid() {
        return getSum() - getSumPaid();
    }

    public double getAveragePaid() {
        return getSumPaid() / getNumberOfFacturesPaid();
    }

    public double getAverageNotPaid() {
        return getSumNotPaid() / getNumberOfFacturesNotPaid();
    }

    public double getMaxPaid() {
        double max = 0;
        for (Facture facture : factures) {
            if (facture.isEstPayee() && facture.getMontant() > max) {
                max = facture.getMontant();
            }
        }
        return max;
    }

    public double getMaxNotPaid() {
        double max = 0;
        for (Facture facture : factures) {
            if (!facture.isEstPayee() && facture.getMontant() > max) {
                max = facture.getMontant();
            }
        }
        return max;
    }

    public double getMinPaid() {
        double min = 0;
        for (Facture facture : factures) {
            if (facture.isEstPayee() && facture.getMontant() < min) {
                min = facture.getMontant();
            }
        }
        return min;
    }

    public double getMinNotPaid() {
        double min = 0;
        for (Facture facture : factures) {
            if (!facture.isEstPayee() && facture.getMontant() < min) {
                min = facture.getMontant();
            }
        }
        return min;
    }

    public double getSumEnergy() {
        double sum = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("ENERGY")) {
                sum += facture.getMontant();
            }
        }
        return sum;
    }

    public double getSumWater() {
        double sum = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("EAU")) {
                sum += facture.getMontant();
            }
        }
        return sum;
    }

    public int getNumberOfEnergy() {
        int count = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("ENERGY")) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfWater() {
        int count = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("EAU")) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfEnergyPaid() {
        int count = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("ENERGY") && facture.isEstPayee()) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfWaterPaid() {
        int count = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("EAU") && facture.isEstPayee()) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfEnergyNotPaid() {
        return getNumberOfEnergy() - getNumberOfEnergyPaid();
    }

    public int getNumberOfWaterNotPaid() {
        return getNumberOfWater() - getNumberOfWaterPaid();
    }

    public double getSumEnergyPaid() {
        double sum = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("ENERGY") && facture.isEstPayee()) {
                sum += facture.getMontant();
            }
        }
        return sum;
    }

    public double getSumWaterPaid() {
        double sum = 0;
        for (Facture facture : factures) {
            if (facture.getType().toString().equals("EAU") && facture.isEstPayee()) {
                sum += facture.getMontant();
            }
        }
        return sum;
    }

    public double getSumEnergyNotPaid() {
        return getSumEnergy() - getSumEnergyPaid();
    }

    public double getSumWaterNotPaid() {
        return getSumWater() - getSumWaterPaid();
    }

    public double getAverageEnergy() {
        return getSumEnergy() / getNumberOfEnergy();
    }

}

