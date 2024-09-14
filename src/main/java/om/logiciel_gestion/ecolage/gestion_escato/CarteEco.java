package om.logiciel_gestion.ecolage.gestion_escato;

import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;
import om.logiciel_gestion.ecolage.gestion_escato.model.Ecolage;
import om.logiciel_gestion.ecolage.gestion_escato.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.Objects;

public class CarteEco extends JFrame {

    private DefaultTableModel model;

    public CarteEco(String idStudent) throws HeadlessException {
        setSize(520, 670);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());

        JPanel panelBody = new JPanel();
        panelBody.setLayout(null);
        panelBody.setBackground(new Color(22, 77, 180));
        panelBody.setBounds(0, 0, 550, 730);
        add(panelBody);

        Student student = new Student(null, null, null, null, null, null, null, null, 0, null);
        DbConnection db = new DbConnection();

        List<Student> s = student.readStudentByIdStudent(db.connDb(), idStudent);


        JLabel nameValue = new JLabel(s.get(0).getLastName());
        nameValue.setForeground(Color.white);
        nameValue.setFont(new Font("Serif", Font.PLAIN, 17));
        nameValue.setBounds(250, -20, 700, 100);
        panelBody.add(nameValue);
        panelBody.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panelBody.getWidth() - 360) / 2);
                nameValue.setLocation(x3, -24);
            }
        });


        JLabel name = new JLabel("Name : ");
        name.setForeground(Color.white);
        name.setFont(new Font("Serif", Font.PLAIN, 17));
        name.setBounds(100, -20, 700, 100);
        panelBody.add(name);
        panelBody.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panelBody.getWidth() - 480) / 2);
                name.setLocation(x3, -24);
            }
        });

        JLabel firstNameValue = new JLabel(s.get(0).getFirstName());
        firstNameValue.setForeground(Color.white);
        firstNameValue.setFont(new Font("Serif", Font.PLAIN, 17));
        firstNameValue.setBounds(100, 0, 700, 100);
        panelBody.add(firstNameValue);
        panelBody.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panelBody.getWidth() - 300) / 2);
                firstNameValue.setLocation(x3, 2);
            }
        });

        JLabel firstName = new JLabel("First Name : ");
        firstName.setForeground(Color.white);
        firstName.setFont(new Font("Serif", Font.PLAIN, 17));
        firstName.setBounds(100, 0, 700, 100);
        panelBody.add(firstName);
        panelBody.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panelBody.getWidth() - 480) / 2);
                firstName.setLocation(x3, 2);
            }
        });

        JLabel classeValue = new JLabel(s.get(0).getGrade());
        classeValue.setForeground(Color.white);
        classeValue.setFont(new Font("Serif", Font.PLAIN, 17));
        classeValue.setBounds(100, 0, 700, 100);
        panelBody.add(classeValue);
        panelBody.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panelBody.getWidth() - 360) / 2);
                classeValue.setLocation(x3, 27);
            }
        });

        JLabel classe = new JLabel(" Grade : ");
        classe.setForeground(Color.white);
        classe.setFont(new Font("Serif", Font.PLAIN, 17));
        classe.setBounds(100, 0, 700, 100);
        panelBody.add(classe);
        panelBody.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panelBody.getWidth() - 490) / 2);
                classe.setLocation(x3, 27);
            }
        });

        String[] columnNames = {"Id", "Amount", "Payment Method", "Month", "Id Student"};
        model = new DefaultTableModel(columnNames, 0);

        JTable tableEcl = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Center align text in all columns
        DefaultTableCellRenderer centerRendererEcl = new DefaultTableCellRenderer();
        centerRendererEcl.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tableEcl.getColumnCount(); i++) {
            tableEcl.getColumnModel().getColumn(i).setCellRenderer(centerRendererEcl);
        }

        // Increase row height
        tableEcl.setRowHeight(40);

//        // Adjust column widths
        TableColumnModel columnModelEcl = tableEcl.getColumnModel();
        for (int i = 0; i < columnModelEcl.getColumnCount(); i++) {
            columnModelEcl.getColumn(i).setPreferredWidth(150);
        }

        JScrollPane scrollPaneEcl = new JScrollPane(tableEcl);
        scrollPaneEcl.setBounds(50, 80, 900, 200);
        scrollPaneEcl.setBackground(Color.WHITE);
        tableEcl.setBackground(Color.WHITE);
        panelBody.add(scrollPaneEcl);
        panelBody.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPaneEcl.setBounds(5, 130, panelBody.getWidth() - 10, panelBody.getHeight() - 135);
            }
        });

        Ecolage ecolage = new Ecolage(0, 0, null, null,null, null, null, null, null);


        List<Ecolage> ecolages = ecolage.readEcolageByIdStudent(db.connDb(), idStudent);
        updateTableEcl(ecolages);


        // Ajout du bouton Imprimer
        JButton printButton = new JButton("Print 🖨");
        printButton.setBounds(395, 45, 100, 40);
        printButton.setBackground(Color.WHITE);
        printButton.setForeground(new Color(22, 77, 180));
        printButton.setFont(new Font("Serif", Font.PLAIN, 20));
        panelBody.add(printButton);

// Action Listener pour le bouton Imprimer
        printButton.addActionListener(e -> {
            printButton.setVisible(false); // Masquer le bouton avant l'impression
            printPanel(panelBody); // Imprimer le panel sans le bouton
            printButton.setVisible(true); // Réafficher le bouton après l'impression
        });


    }

    private void updateTableEcl(List<Ecolage> ecolage) {
        model.setRowCount(0);
        for (Ecolage e : ecolage) {
            model.addRow(new Object[]{
                    e.getId(), e.getAmountEcolage() + " Ar", e.getPaymentMethod(),
                    e.getMonth(), e.getIdStudent()
            });
        }
    }

    // Méthode pour imprimer le JPanel
    private void printPanel(JPanel panel) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Impression Carte");

        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            // Obtenir la taille du JPanel
            int panelWidth = panel.getWidth();
            int panelHeight = panel.getHeight();

            // Obtenir les dimensions imprimables de la page
            double pageWidth = pageFormat.getImageableWidth();
            double pageHeight = pageFormat.getImageableHeight();

            // Calculer les marges pour centrer le JPanel
            double xMargin = (pageWidth - panelWidth) / 2;
            double yMargin = (pageHeight - panelHeight) / 2;

            // Convertir le JPanel en composant imprimable
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX() + xMargin, pageFormat.getImageableY() + yMargin);
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


}
