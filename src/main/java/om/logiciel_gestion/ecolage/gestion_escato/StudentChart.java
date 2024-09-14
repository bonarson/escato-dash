package om.logiciel_gestion.ecolage.gestion_escato;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class StudentChart extends JFrame {

    public StudentChart(String title) {
        super(title);

        // Créer le dataset
        DefaultCategoryDataset dataset = createDataset();

        // Créer le chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Statistiques des étudiants",
                "Catégorie",
                "Nombre",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Personnaliser l'apparence du chart
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.PINK);
        renderer.setSeriesPaint(2, Color.GREEN);

        // Créer le panel de chart et l'ajouter au JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Ajouter les données
        dataset.addValue(150, "Garçons étudiants", "Garçons");
        dataset.addValue(130, "Filles étudiantes", "Filles");
        dataset.addValue(50000, "Revenus totaux", "Revenus");

        return dataset;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            StudentChart example = new StudentChart("Chart de Statistiques des étudiants");
//            example.setSize(800, 600);
//            example.setLocationRelativeTo(null);
//            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            example.setVisible(true);
//        });
//    }
}

