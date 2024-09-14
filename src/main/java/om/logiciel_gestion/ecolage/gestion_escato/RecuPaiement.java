package om.logiciel_gestion.ecolage.gestion_escato;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.time.LocalDate;
import java.util.Objects;

public class RecuPaiement extends JFrame {

    public RecuPaiement(String nomEtudiant, String classeEtudiant, String methodePaiement, double montantPaye,String month) {
        setTitle("Reçu de Paiement");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        add(panel);

        // Titre du reçu
        JLabel titreLabel = new JLabel("Reçu de Paiement", SwingConstants.CENTER);
        titreLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titreLabel.setBounds(50, 20, 300, 30);
        panel.add(titreLabel);

        // Ligne de séparation
        JSeparator separator1 = new JSeparator();
        separator1.setBounds(50, 60, 300, 1);
        panel.add(separator1);

        // Informations sur l'étudiant
        JLabel nomLabel = new JLabel("Nom de l'étudiant : " + nomEtudiant);
        nomLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        nomLabel.setBounds(50, 80, 300, 30);
        panel.add(nomLabel);

        JLabel classeLabel = new JLabel("Classe : " + classeEtudiant);
        classeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        classeLabel.setBounds(50, 120, 300, 30);
        panel.add(classeLabel);

        // Ligne de séparation
        JSeparator separator2 = new JSeparator();
        separator2.setBounds(50, 160, 300, 1);
        panel.add(separator2);

        // Montant payé
        JLabel montantLabel = new JLabel("Montant payé : " + String.format("%.2f Ar", montantPaye));
        montantLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        montantLabel.setBounds(50, 180, 300, 30);
        panel.add(montantLabel);

        JLabel mois = new JLabel("Mois : " + month);
        mois.setFont(new Font("Serif", Font.PLAIN, 16));
        mois.setBounds(50, 220, 300, 30);
        panel.add(mois);

        // Méthode de paiement
        JLabel methodeLabel = new JLabel("Méthode de paiement : " + methodePaiement);
        methodeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        methodeLabel.setBounds(50, 260, 300, 30);
        panel.add(methodeLabel);

        // Ligne de séparation
        JSeparator separator3 = new JSeparator();
        separator3.setBounds(50, 300, 300, 1);
        panel.add(separator3);

        // Date de paiement
        JLabel dateLabel = new JLabel("Date : " + LocalDate.now());
        dateLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        dateLabel.setBounds(50, 310, 300, 30);
        panel.add(dateLabel);

        // Lieu de paiement
        JLabel lieuLabel = new JLabel("Lieu de paiement : Ecole Escato");
        lieuLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        lieuLabel.setBounds(50, 340, 300, 30);
        panel.add(lieuLabel);

        // Ligne de séparation
        JSeparator separator4 = new JSeparator();
        separator4.setBounds(50, 375, 300, 1);
        panel.add(separator4);

        // Message de remerciement
        JLabel merciLabel = new JLabel("Merci pour votre paiement !", SwingConstants.CENTER);
        merciLabel.setFont(new Font("Serif", Font.ITALIC, 16));
        merciLabel.setBounds(50, 380, 300, 30);
        panel.add(merciLabel);

        // Bouton d'impression
        JButton imprimerButton = new JButton("Imprimer");
        imprimerButton.setBounds(150, 419, 120, 30);
        imprimerButton.setBackground(new Color(22, 77, 180));
        imprimerButton.setForeground(Color.white);
        panel.add(imprimerButton);

        // Action listener pour le bouton Imprimer
        imprimerButton.addActionListener(e -> {
            imprimerButton.setVisible(false); // Masquer le bouton avant l'impression
            imprimerPanel(panel);  // Imprimer le panel
            imprimerButton.setVisible(true);  // Réafficher le bouton après l'impression
        });
    }

    // Méthode pour imprimer le panel
    private void imprimerPanel(JPanel panel) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Impression Reçu de Paiement");

        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            // Adapter l'échelle du panel pour qu'il s'ajuste à la page
            double scaleX = pageFormat.getImageableWidth() / panel.getWidth();
            double scaleY = pageFormat.getImageableHeight() / panel.getHeight();
            double scale = Math.min(scaleX, scaleY);
            g2d.scale(scale, scale);

            panel.printAll(g2d);

            return Printable.PAGE_EXISTS;
        });

        boolean doPrint = printerJob.printDialog();
        if (doPrint) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        // Exemple d'utilisation du reçu
//        RecuPaiement recu = new RecuPaiement("Jean Dupont", "6ème", "Espèces", 150000.00,"Juin");
//        recu.setVisible(true);
    }
}
