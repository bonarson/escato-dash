package om.logiciel_gestion.ecolage.gestion_escato;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Dash extends JFrame {

    public Dash() throws HeadlessException {
        setSize(1100, 700);
        setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        JMenuBar menuBar = new JMenuBar();
//
//        JMenu menuStudent = new JMenu("Student");
//
//        JMenu menuEcolage = new JMenu("Ecolage");
//
//        JMenuItem addStudent = new JMenuItem("Add Student");
//        JMenuItem addEcolage = new JMenuItem("Add Ecolage");
//        JMenuItem listEcolage = new JMenuItem("Liste ecolage");
//        JMenuItem listStudent = new JMenuItem("List student");
//
//        menuStudent.add(addStudent);
//        menuStudent.add(listStudent);
//        menuEcolage.add(addEcolage);
//        menuEcolage.add(listEcolage);
//
//        menuBar.add(menuStudent);
//        menuBar.add(menuEcolage);
//        setJMenuBar(menuBar);

        JPanel panelBody = new JPanel();
        panelBody.setLayout(null);
        panelBody.setBackground(Color.WHITE);
        panelBody.setBounds(0, 0, 1300, 700);


        //cree le panel pour la card
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(73, 73, 73, 116)));
        cardPanel.setBackground(new Color(22, 77, 180));
        cardPanel.setBounds(10, 5, 220, 100);

        JPanel cardPanel1 = new JPanel();
        cardPanel1.setLayout(null);
        cardPanel1.setBorder(BorderFactory.createLineBorder(new Color(73, 73, 73, 116)));
        cardPanel1.setBackground(Color.WHITE);
        cardPanel1.setBounds(255, 5, 220, 100);

        JPanel cardPanel2 = new JPanel();
        cardPanel2.setLayout(null);
        cardPanel2.setBorder(BorderFactory.createLineBorder(new Color(73, 73, 73, 116)));
        cardPanel2.setBackground(Color.WHITE);
        cardPanel2.setBounds(500, 5, 220, 100);

        JPanel cardPanel3 = new JPanel();
        cardPanel3.setLayout(null);
        cardPanel3.setBorder(BorderFactory.createLineBorder(new Color(73, 73, 73, 116)));
        cardPanel3.setBackground(Color.WHITE);
        cardPanel3.setBounds(750, 5, 320, 100);

        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(null);
        chartPanel.setBorder(BorderFactory.createLineBorder(new Color(73, 73, 73, 116)));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBounds(10, 107, 710, 530);


        //image student
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/student.png")));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(2, 0, 80, 100);
        cardPanel.add(imageLabel);

        //image girl
        ImageIcon imageIcon1 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/girls.png")));
        Image image1 = imageIcon1.getImage();
        Image scaledImage1 = image1.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        JLabel imageLabel1 = new JLabel(scaledIcon1);
        imageLabel1.setBounds(2, 0, 80, 100);
        cardPanel1.add(imageLabel1);

        //boys
        ImageIcon imageIcon2 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/boys.png")));
        Image image2 = imageIcon2.getImage();
        Image scaledImage2 = image2.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        JLabel imageLabel2 = new JLabel(scaledIcon2);
        imageLabel2.setBounds(2, 0, 80, 100);
        cardPanel2.add(imageLabel2);

        //Gain
        //boys
        ImageIcon imageIcon3 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/money1.png")));
        Image image3 = imageIcon3.getImage();
        Image scaledImage3 = image3.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        JLabel imageLabel3 = new JLabel(scaledIcon3);
        imageLabel3.setBounds(0, 0, 80, 100);
        cardPanel3.add(imageLabel3);

        JLabel titleLabel = new JLabel("Students");
        titleLabel.setBounds(105, -75, 200, 200);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 19));

        JLabel nbrStudent = new JLabel("1200");
        nbrStudent.setBounds(105, -30, 200, 200);
        nbrStudent.setForeground(Color.white);
        nbrStudent.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel titleLabel1 = new JLabel("Girls");
        titleLabel1.setBounds(105, -75, 200, 200);
        titleLabel1.setForeground(new Color(22, 77, 180));
        titleLabel1.setFont(new Font("Serif", Font.BOLD, 19));

        JLabel nbrGirl = new JLabel("62000");
        nbrGirl.setBounds(105, -30, 200, 200);
        nbrGirl.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel titleLabel2 = new JLabel("Boys");
        titleLabel2.setBounds(105, -75, 200, 200);
        titleLabel2.setForeground(new Color(22, 77, 180));
        titleLabel2.setFont(new Font("Serif", Font.BOLD, 19));

        JLabel nbrBoys = new JLabel("1200");
        nbrBoys.setBounds(105, -30, 200, 200);
        nbrBoys.setFont(new Font("Serif", Font.BOLD, 30));

        JLabel titleLabel3 = new JLabel("Total Earning");
        titleLabel3.setBounds(105, -75, 200, 200);
        titleLabel3.setForeground(new Color(22, 77, 180));
        titleLabel3.setFont(new Font("Serif", Font.BOLD, 19));

        JLabel nbrGain = new JLabel("Ar 60,000");
        nbrGain.setBounds(105, -30, 200, 200);
        nbrGain.setFont(new Font("Serif", Font.BOLD, 30));


        cardPanel.add(titleLabel);
        cardPanel.add(nbrStudent);

        cardPanel1.add(titleLabel1);
        cardPanel1.add(nbrGirl);

        cardPanel2.add(titleLabel2);
        cardPanel2.add(nbrBoys);

        cardPanel3.add(titleLabel3);
        cardPanel3.add(nbrGain);

        panelBody.add(cardPanel);
        panelBody.add(cardPanel1);
        panelBody.add(cardPanel2);
        panelBody.add(cardPanel3);
        //panelBody.add(chartPanel);
        add(panelBody);


        // Créer le dataset
        DefaultCategoryDataset dataset = createDataset();

        // Créer le chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Student Statistics",
                "Category",
                "Number",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);


        // Personnaliser l'apparence du chart
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        chart.setBackgroundPaint(Color.WHITE);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.PINK);
        renderer.setSeriesPaint(2, Color.GREEN);

        // Créer le panel de chart et l'ajouter au JFrame
        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createLineBorder(new Color(73, 73, 73, 116)));
        chartPanel.setBounds(10, 107, 465, 530);
        panelBody.add(chartPanel);


    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Ajouter les données
        dataset.addValue(4200, "Student boys", "Boys");
        dataset.addValue(4200, "Student girls", "Girls");
        dataset.addValue(50000, "Total revenue", "Revenue");

        return dataset;
    }


}
