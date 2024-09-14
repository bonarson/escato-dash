package om.logiciel_gestion.ecolage.gestion_escato;

import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;
import om.logiciel_gestion.ecolage.gestion_escato.model.Ecolage;
import om.logiciel_gestion.ecolage.gestion_escato.model.Pin;
import om.logiciel_gestion.ecolage.gestion_escato.model.Student;

import javax.swing.*;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

public class Dashv1 extends JFrame {

    private boolean isExpanded = false;
    private DefaultTableModel model;
    private DefaultTableModel modelEcl;

    private JButton sumAmountRegistButton;
    private JButton sumAmountInscriButton;
    private JButton sumAmountReinscriButton;
    private DbConnection db;
    private Student student;


    public Dashv1() throws HeadlessException {
        setTitle("");
        setSize(1330, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //BufferedImage t = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());


        JPanel panelBody = new JPanel();
        panelBody.setLayout(null);
        panelBody.setBackground(Color.WHITE);
        panelBody.setBounds(0, 0, 1100, 700);

        // navigation bar

        JPanel navigationBar = new JPanel();
        navigationBar.setLayout(null);
        navigationBar.setBounds(0, 0, 90, 700);
        navigationBar.setBackground(new Color(22, 77, 180));
        panelBody.add(navigationBar);

        // panel Content
        JPanel panelContentElement = new JPanel();
        panelContentElement.setLayout(null);
        panelContentElement.setBounds(190, 0, 1070, 700);
        panelContentElement.setBackground(new Color(22, 77, 180));
        panelBody.add(panelContentElement);

        // panel addStudent
        JPanel panelAddStudent = new JPanel();
        panelAddStudent.setLayout(null);
        panelAddStudent.setBounds(190, 0, 1070, 650);
        panelAddStudent.setBackground(new Color(22, 77, 180));
        panelBody.add(panelAddStudent);

        // panel addEcl
        JPanel panelAddEcolage = new JPanel();
        panelAddEcolage.setLayout(null);
        panelAddEcolage.setBounds(190, 0, 1070, 650);
        panelAddEcolage.setBackground(new Color(22, 77, 180));
        panelBody.add(panelAddEcolage);

        //panel listECL
        JPanel panelEclList = new JPanel();
        panelEclList.setLayout(null);
        panelEclList.setBounds(190, 0, 1070, 700);
        panelEclList.setBackground(new Color(22, 77, 180));
        panelBody.add(panelEclList);

        //================================


        ImageIcon menuIcon = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/menu-removebg-preview.png")));
        Image icon = menuIcon.getImage();
        Image scaledImageIcon = icon.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIconMenu = new ImageIcon(scaledImageIcon);
        JLabel imageLabelIcon = new JLabel(scaledIconMenu);
        imageLabelIcon.setBounds(20, 30, 43, 43);

        JButton click = new JButton();
        click.setBounds(110, 0, 60, 30);
        click.setBackground(new Color(22, 77, 180));
        click.add(imageLabelIcon);
        panelBody.add(click);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuStudent = new JMenu("Student");
        menuStudent.setFont(new Font("Serif", Font.BOLD, 14));
        menuStudent.setForeground(new Color(22, 77, 180));
        menuBar.add(menuStudent);
        menuBar.setBackground(Color.WHITE);
        menuBar.setBounds(72, 40, 158, 30);
        menuBar.setVisible(false);
        navigationBar.add(menuBar);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/add-student-removebg-preview.png")));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(43, 43, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(20, 30, 43, 43);
        navigationBar.add(imageLabel);


        JMenuBar menuBar1 = new JMenuBar();
        JMenu menuStudent1 = new JMenu("List STD");
        menuStudent1.setSize(200, 30);
        menuStudent1.setFont(new Font("Serif", Font.BOLD, 13));
        menuStudent1.setForeground(new Color(22, 77, 180));
        menuBar1.add(menuStudent1);
        menuBar1.setBackground(Color.WHITE);
        menuBar1.setBounds(72, 100, 158, 30);
        menuBar1.setVisible(false);
        navigationBar.add(menuBar1);

        ImageIcon imageIcon1 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/liste-removebg-preview.png")));
        Image image1 = imageIcon1.getImage();
        Image scaledImage1 = image1.getScaledInstance(43, 43, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        JLabel imageLabel1 = new JLabel(scaledIcon1);
        imageLabel1.setBounds(20, 100, 43, 43);
        navigationBar.add(imageLabel1);

        JMenuBar menuBar2 = new JMenuBar();
        JMenu menuStudent2 = new JMenu("List ECL");
        menuStudent2.setSize(200, 30);
        menuStudent2.setFont(new Font("Serif", Font.BOLD, 13));
        menuStudent2.setForeground(new Color(22, 77, 180));
        menuBar2.add(menuStudent2);
        menuBar2.setBackground(Color.WHITE);
        menuBar2.setBounds(72, 160, 158, 30);
        menuBar2.setVisible(false);
        navigationBar.add(menuBar2);

        ImageIcon imageIcon2 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/listeEcolage-removebg-preview.png")));
        Image image2 = imageIcon2.getImage();
        Image scaledImage2 = image2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        JLabel imageLabel2 = new JLabel(scaledIcon2);
        imageLabel2.setBounds(20, 160, 45, 45);
        navigationBar.add(imageLabel2);


        JMenuBar menuBar3 = new JMenuBar();
        JMenu menuStudent3 = new JMenu("Ecolage");
        menuStudent3.setSize(200, 30);
        menuStudent3.setFont(new Font("Serif", Font.BOLD, 15));
        menuStudent3.setForeground(new Color(22, 77, 180));
        menuBar3.add(menuStudent3);
        menuBar3.setBackground(Color.WHITE);
        menuBar3.setBounds(72, 220, 158, 30);
        menuBar3.setVisible(false);
        navigationBar.add(menuBar3);

        ImageIcon imageIcon3 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/moneyADD-removebg-preview.png")));
        Image image3 = imageIcon3.getImage();
        Image scaledImage3 = image3.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        JLabel imageLabel3 = new JLabel(scaledIcon3);
        imageLabel3.setBounds(20, 220, 50, 50);
        navigationBar.add(imageLabel3);


//        JMenuBar menuBar5 = new JMenuBar();
//        JMenu menuStudent5 = new JMenu("Home");
//        menuStudent5.setSize(200, 30);
//        menuStudent5.setFont(new Font("Serif", Font.BOLD, 15));
//        menuStudent5.setForeground(new Color(22, 77, 180));
//        menuBar5.add(menuStudent5);
//        menuBar5.setBackground(Color.WHITE);
//        menuBar5.setBounds(72, 280, 158, 30);
//        menuBar5.setVisible(true);
//        navigationBar.add(menuBar5);
//
//        ImageIcon imageIcon5 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/add-student-removebg-preview.png")));
//        Image image5 = imageIcon5.getImage();
//        Image scaledImage5 = image5.getScaledInstance(42, 42, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon5 = new ImageIcon(scaledImage5);
//        JLabel imageLabel5 = new JLabel(scaledIcon5);
//        imageLabel5.setBounds(20, 285, 42, 42);
//        navigationBar.add(imageLabel5);


        JMenuBar menuBar4 = new JMenuBar();
        JMenu menuStudent4 = new JMenu("New PIN");
        menuStudent4.setSize(200, 30);
        menuStudent4.setFont(new Font("Serif", Font.BOLD, 14));
        menuStudent4.setForeground(new Color(22, 77, 180));
        menuBar4.add(menuStudent4);
        menuBar4.setBackground(Color.WHITE);
        menuBar4.setBounds(72, 550, 158, 30);
        menuBar4.setVisible(false);
        navigationBar.add(menuBar4);

        ImageIcon imageIcon4 = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/changePin-removebg-preview.png")));
        Image image4 = imageIcon4.getImage();
        Image scaledImage4 = image4.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon4 = new ImageIcon(scaledImage4);
        JLabel imageLabel4 = new JLabel(scaledIcon4);
        imageLabel4.setBounds(20, 545, 50, 50);
        navigationBar.add(imageLabel4);


        //NEW PIN
        menuStudent4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame p = new Pin();
                p.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //

        //StudentList=============

        JLabel identificationNumber = new JLabel("Identification Number");
        identificationNumber.setForeground(Color.white);
        identificationNumber.setFont(new Font("Serif", Font.BOLD, 15));
        identificationNumber.setBounds(15, -10, 200, 100);
        panelContentElement.add(identificationNumber);

        JTextField inputNumber = new JTextField();
        inputNumber.setForeground(new Color(22, 77, 180));
        inputNumber.setFont(new Font("Serif", Font.BOLD, 20));
        inputNumber.setBounds(180, 28, 130, 25);
        panelContentElement.add(inputNumber);

        JButton refreshing = new JButton("Refreshing");
        refreshing.setBackground(Color.WHITE);
        refreshing.setForeground(new Color(22, 77, 180));
        refreshing.setFont(new Font("Serif", Font.BOLD, 13));
        refreshing.setBounds(340, 28, 100, 26);
        panelContentElement.add(refreshing);

        JButton search = new JButton("Search");
        search.setBackground(Color.WHITE);
        search.setForeground(new Color(22, 77, 180));
        search.setFont(new Font("Serif", Font.BOLD, 13));
        search.setBounds(460, 28, 110, 26);
        panelContentElement.add(search);

        JButton delete = new JButton("Delete");
        delete.setBackground(Color.WHITE);
        delete.setForeground(new Color(22, 77, 180));
        delete.setFont(new Font("Serif", Font.BOLD, 13));
        delete.setBounds(590, 28, 110, 26);
        panelContentElement.add(delete);

        JButton update = new JButton("Update");
        update.setBackground(Color.WHITE);
        update.setForeground(new Color(22, 77, 180));
        update.setFont(new Font("Serif", Font.BOLD, 13));
        update.setBounds(720, 28, 110, 26);
        panelContentElement.add(update);

        JLabel classLabel = new JLabel("Class");
        classLabel.setForeground(Color.white);
        classLabel.setFont(new Font("Serif", Font.BOLD, 16));
        classLabel.setBounds(850, -10, 200, 100);
        panelContentElement.add(classLabel);

        JTextField classInput = new JTextField();
//        classInput.setForeground(new Color(22, 77, 180));
//        classInput.setFont(new Font("Serif", Font.BOLD, 20));
//        classInput.setBounds(900, 28, 125, 25);
//        panelContentElement.add(classInput);

        String[] itemsCl = {"6ème I", "6ème II", "6ème III", "6ème IV",
                "5ème I", "5ème II", "5ème III", "5ème IV",
                "4ème I", "4ème II", "4ème III", "4ème IV",
                "3ème I", "3ème II", "3ème III", "3ème IV",
                "2nd I", "2nd II", "2nd III", "2nd IV",
                "PS I", "PS II", "PS III", "PS IV",
                "PL I", "PL II", "PL III", "PL IV",
                "TS I", "TS II", "TS III", "TS IV",
                "TL I", "TL II", "TL III", "TL IV",};
        JComboBox<String> comboBoxItemsCla = new JComboBox<>(itemsCl);
        comboBoxItemsCla.setFont(new Font("Serif", Font.BOLD, 16));
        comboBoxItemsCla.setBackground(Color.WHITE);
        comboBoxItemsCla.setForeground(new Color(22, 77, 180));
        comboBoxItemsCla.setBounds(900, 28, 100, 25);
        panelContentElement.add(comboBoxItemsCla);


        JButton impr = new JButton("Print 🖨");
        impr.setBackground(Color.WHITE);
        impr.setForeground(new Color(22, 77, 180));
        impr.setFont(new Font("Serif", Font.BOLD, 13));
        impr.setBounds(900, 65, 100, 28);
        panelContentElement.add(impr);


        db = new DbConnection();
        student = new Student(null, null, null, null, null, null, null, null, 0, null);

        String[] columnNames = {"ID Number", "Last Name", "First Name", "Date of birth", "Gender", "Class", "Number", "Enrollment Date", "status", "Ecolage"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return column == 9; // Only the "Ecolage" column is editable
            }
        };

        impr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean complete = table.print(JTable.PrintMode.FIT_WIDTH,
                            new MessageFormat("LIST STD "),
                            new MessageFormat("Page {0}"));
                    if (complete) {
                        JOptionPane.showMessageDialog(null, "200", "Success", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "500", "Error", JOptionPane.INFORMATION_MESSAGE);

                    }

                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'impression: ");
                }
            }
        });

        // Center align text in all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add custom renderer and editor for the "Ecolage" column
        table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JTextField(), table));

        // Increase row height
        table.setRowHeight(40);

        // Adjust column widths
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, panelContentElement.getWidth() - 10, panelContentElement.getHeight() - 124);
        scrollPane.setBackground(Color.WHITE);
        table.setBackground(Color.WHITE);
        panelContentElement.add(scrollPane);
        panelContentElement.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPane.setBounds(0, 100, panelContentElement.getWidth() - 10, panelContentElement.getHeight() - 124);
            }
        });


        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String columnName = (String) table.getColumnModel().getColumn(table.getSelectedColumn()).getHeaderValue();
                String valueColumn = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
                String idStudent = (String) table.getValueAt(table.getSelectedRow(), 0);
                String mod = model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
                UpdateFram uf = new UpdateFram(mod, model, table.getSelectedRow(), table.getSelectedColumn(), columnName, valueColumn, idStudent);
                uf.setVisible(true);
            }
        });
        inputNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (classInput.getText().isEmpty()) {
                        String idStudent = inputNumber.getText();
                        List<Student> students = student.readStudentByIdStudent(db.connDb(), idStudent);
                        updateTable(students);
                    } else if (inputNumber.getText().isEmpty()) {
                        String grade = classInput.getText();
                        List<Student> students = student.readStudentByClassName(db.connDb(), grade);
                        updateTable(students);
                    } else if (!classInput.getText().isEmpty() && !inputNumber.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter your ID only or grade only.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });
        comboBoxItemsCla.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String grade = (String) comboBoxItemsCla.getSelectedItem();
                    List<Student> students = student.readStudentByClassName(db.connDb(), grade);
                    updateTable(students);
                }

            }
        });

        // Add action listener for the search button
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (inputNumber.getText().isEmpty()) {
                    String grade = (String) comboBoxItemsCla.getSelectedItem();
                    List<Student> students = student.readStudentByClassName(db.connDb(), grade);
                    updateTable(students);
                } else {
                    String idStudent = inputNumber.getText();
                    List<Student> students = student.readStudentByIdStudent(db.connDb(), idStudent);
                    updateTable(students);
                }

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String idStudent = (String) table.getValueAt(selectedRow, 0);
                    System.out.println("nombre " + idStudent);
                    DbConnection db = new DbConnection();
                    if (student.deleteStudentByIdStudent(db.connDb(), idStudent)) {
                        JOptionPane.showMessageDialog(null, "Delete successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        List<Student> students = student.readStudents(db.connDb());
                        updateTable(students);
                        Ecolage ecc = new Ecolage(0, 0, null, null, null,
                                null, null, null, null);
                        sumAmountRegistButton.setText(totalRegistration(ecc) + " Ar");
                        sumAmountInscriButton.setText(totalInscri(ecc) + " Ar");
                        sumAmountReinscriButton.setText(totalReinscri(ecc) + " Ar");
                    } else {
                        JOptionPane.showMessageDialog(null, "Delete failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Initial load of student data
        List<Student> students = student.readStudents(db.connDb());
        updateTable(students);

        refreshing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> students = student.readStudents(db.connDb());
                updateTable(students);
            }
        });

        //========== end ==============

        //RegisterStudent============
        JLabel H1 = new JLabel("📝  Student Registration Form  📝");
        H1.setForeground(Color.white);
        H1.setFont(new Font("Serif", Font.BOLD, 24));
        H1.setBounds(374, 5, 700, 100);
        panelAddStudent.add(H1);

        JLabel fullName = new JLabel("Full Name");
        fullName.setForeground(Color.white);
        fullName.setFont(new Font("Serif", Font.BOLD, 16));
        fullName.setBounds(220, 100, 700, 100);
        panelAddStudent.add(fullName);

        JTextField inputLastName = new JTextField();
        inputLastName.setForeground(new Color(22, 77, 180));
        inputLastName.setFont(new Font("Serif", Font.BOLD, 20));
        inputLastName.setBounds(220, 180, 170, 25);
        panelAddStudent.add(inputLastName);

        JLabel lastName = new JLabel("Last Name");
        lastName.setForeground(Color.white);
        lastName.setFont(new Font("Serif", Font.BOLD, 14));
        lastName.setBounds(220, 170, 700, 100);
        panelAddStudent.add(lastName);

        JTextField inputFirstName = new JTextField();
        inputFirstName.setForeground(new Color(22, 77, 180));
        inputFirstName.setFont(new Font("Serif", Font.BOLD, 20));
        inputFirstName.setBounds(426, 180, 170, 25);
        panelAddStudent.add(inputFirstName);

        JLabel firstName = new JLabel("First Name");
        firstName.setForeground(Color.white);
        firstName.setFont(new Font("Serif", Font.BOLD, 14));
        firstName.setBounds(426, 170, 700, 100);
        panelAddStudent.add(firstName);


        String[] itemsClass = {"6ème I", "6ème II", "6ème III", "6ème IV",
                "5ème I", "5ème II", "5ème III", "5ème IV",
                "4ème I", "4ème II", "4ème III", "4ème IV",
                "3ème I", "3ème II", "3ème III", "3ème IV",
                "2nd I", "2nd II", "2nd III", "2nd IV",
                "PS I", "PS II", "PS III", "PS IV",
                "PL I", "PL II", "PL III", "PL IV",
                "TS I", "TS II", "TS III", "TS IV",
                "TL I", "TL II", "TL III", "TL IV",};
        JComboBox<String> comboBoxClass = new JComboBox<>(itemsClass);
        comboBoxClass.setFont(new Font("Serif", Font.BOLD, 14));
        comboBoxClass.setBackground(Color.WHITE);
        comboBoxClass.setForeground(new Color(22, 77, 180));
        comboBoxClass.setBounds(630, 180, 170, 25);
        panelAddStudent.add(comboBoxClass);

        JLabel grade = new JLabel("Class");
        grade.setForeground(Color.white);
        grade.setFont(new Font("Serif", Font.BOLD, 15));
        grade.setBounds(630, 170, 700, 100);
        panelAddStudent.add(grade);

        JLabel gender = new JLabel("Gender");
        gender.setForeground(Color.white);
        gender.setFont(new Font("Serif", Font.BOLD, 14));
        gender.setBounds(220, 230, 700, 100);
        panelAddStudent.add(gender);

        String[] itemsGender = {"Feminine", "Masculine"};
        JComboBox<String> comboBoxGender = new JComboBox<>(itemsGender);
        comboBoxGender.setFont(new Font("Serif", Font.BOLD, 14));
        comboBoxGender.setBackground(Color.WHITE);
        comboBoxGender.setForeground(new Color(22, 77, 180));
        comboBoxGender.setBounds(220, 300, 170, 25);
        panelAddStudent.add(comboBoxGender);


        JLabel dateOfBirth = new JLabel("Date of Birth");
        dateOfBirth.setForeground(Color.white);
        dateOfBirth.setFont(new Font("Serif", Font.BOLD, 14));
        dateOfBirth.setBounds(426, 230, 700, 100);
        panelAddStudent.add(dateOfBirth);

        JTextField inputBirth = new JTextField();
        inputBirth.setForeground(new Color(22, 77, 180));
        inputBirth.setFont(new Font("Serif", Font.BOLD, 20));
        inputBirth.setBounds(426, 300, 170, 25);
        panelAddStudent.add(inputBirth);

        JLabel idStudent = new JLabel("Identification Number(n.Matricule)");
        idStudent.setForeground(Color.white);
        idStudent.setFont(new Font("Serif", Font.BOLD, 14));
        idStudent.setBounds(626, 230, 700, 100);
        panelAddStudent.add(idStudent);

        JTextField inputId = new JTextField();
        inputId.setForeground(new Color(22, 77, 180));
        inputId.setFont(new Font("Serif", Font.BOLD, 20));
        inputId.setBounds(626, 300, 170, 25);
        panelAddStudent.add(inputId);

        JLabel numberInClass = new JLabel("Number(Class)");
        numberInClass.setForeground(Color.white);
        numberInClass.setFont(new Font("Serif", Font.BOLD, 14));
        numberInClass.setBounds(220, 320, 700, 100);
        panelAddStudent.add(numberInClass);

        JTextField inputNumberClass = new JTextField();
        inputNumberClass.setForeground(new Color(22, 77, 180));
        inputNumberClass.setFont(new Font("Serif", Font.BOLD, 20));
        inputNumberClass.setBounds(220, 390, 170, 25);
        panelAddStudent.add(inputNumberClass);

        JLabel amountRegistration = new JLabel("Amount");
        amountRegistration.setForeground(Color.white);
        amountRegistration.setFont(new Font("Serif", Font.BOLD, 14));
        amountRegistration.setBounds(426, 320, 700, 100);
        panelAddStudent.add(amountRegistration);

        JTextField inputAmountRegistration = new JTextField();
        inputAmountRegistration.setForeground(new Color(22, 77, 180));
        inputAmountRegistration.setFont(new Font("Serif", Font.BOLD, 20));
        inputAmountRegistration.setBounds(426, 390, 170, 25);
        panelAddStudent.add(inputAmountRegistration);

        JLabel status = new JLabel("Status");
        status.setForeground(Color.white);
        status.setFont(new Font("Serif", Font.BOLD, 15));
        status.setBounds(626, 320, 700, 100);
        panelAddStudent.add(status);

        String[] items = {"Inscription", "Réinscription"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Serif", Font.BOLD, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(new Color(22, 77, 180));
        comboBox.setBounds(626, 390, 170, 25);
        panelAddStudent.add(comboBox);

        Ecolage ecc = new Ecolage(0, 0, null, null, null, null, null, null, null);

        JLabel sumAmountRegist = new JLabel("💲    Total Registration    💲");
        sumAmountRegist.setForeground(Color.white);
        sumAmountRegist.setFont(new Font("Serif", Font.BOLD, 14));
        sumAmountRegist.setBounds(220, 410, 700, 100);
        panelAddStudent.add(sumAmountRegist);

        sumAmountRegistButton = new JButton(totalRegistration(ecc) + " Ar");
        sumAmountRegistButton.setBackground(Color.WHITE);
        sumAmountRegistButton.setForeground(new Color(22, 77, 180));
        sumAmountRegistButton.setFont(new Font("Serif", Font.BOLD, 15));
        sumAmountRegistButton.setBounds(220, 483, 170, 25);
        panelAddStudent.add(sumAmountRegistButton);

        JLabel sumAmountInscri = new JLabel("💲          Inscription         💲");
        sumAmountInscri.setForeground(Color.white);
        sumAmountInscri.setFont(new Font("Serif", Font.BOLD, 14));
        sumAmountInscri.setBounds(426, 410, 700, 100);
        panelAddStudent.add(sumAmountInscri);

        sumAmountInscriButton = new JButton(totalInscri(ecc) + " Ar");
        sumAmountInscriButton.setBackground(Color.WHITE);
        sumAmountInscriButton.setForeground(new Color(22, 77, 180));
        sumAmountInscriButton.setFont(new Font("Serif", Font.BOLD, 15));
        sumAmountInscriButton.setBounds(426, 483, 170, 25);
        panelAddStudent.add(sumAmountInscriButton);

        JLabel sumAmountReinscri = new JLabel("💲       Réinscription       💲");
        sumAmountReinscri.setForeground(Color.white);
        sumAmountReinscri.setFont(new Font("Serif", Font.BOLD, 14));
        sumAmountReinscri.setBounds(626, 410, 700, 100);
        panelAddStudent.add(sumAmountReinscri);

        sumAmountReinscriButton = new JButton(totalReinscri(ecc) + " Ar");
        sumAmountReinscriButton.setBackground(Color.WHITE);
        sumAmountReinscriButton.setForeground(new Color(22, 77, 180));
        sumAmountReinscriButton.setFont(new Font("Serif", Font.BOLD, 15));
        sumAmountReinscriButton.setBounds(626, 483, 170, 25);
        panelAddStudent.add(sumAmountReinscriButton);

        JButton register = new JButton("Register");
        register.setBackground(Color.WHITE);
        register.setForeground(new Color(22, 77, 180));
        register.setFont(new Font("Serif", Font.BOLD, 16));
        register.setBounds(220, 550, 100, 28);
        panelAddStudent.add(register);

        panelAddStudent.setVisible(false);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputId.getText().trim().isEmpty() ||
                        inputLastName.getText().trim().isEmpty() ||
                        inputFirstName.getText().trim().isEmpty() ||
                        inputBirth.getText().trim().isEmpty() ||
                        inputAmountRegistration.getText().trim().isEmpty() ||
                        inputNumberClass.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                Student student = new Student(
                        inputId.getText(),
                        inputLastName.getText(),
                        inputFirstName.getText(),
                        inputBirth.getText(),
                        (String) comboBoxGender.getSelectedItem(),
                        (String) comboBoxClass.getSelectedItem(),
                        inputNumberClass.getText(),
                        null,
                        Double.parseDouble(inputAmountRegistration.getText()),
                        (String) comboBox.getSelectedItem()
                );

                if (student.addStudent(db.connDb(), student)) {
                    JOptionPane.showMessageDialog(null, "Add successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    var students = student.readStudents(db.connDb());
                    updateTable(students);
                    sumAmountRegistButton.setText(totalRegistration(ecc) + " Ar");
                    sumAmountInscriButton.setText(totalInscri(ecc) + " Ar");
                    sumAmountReinscriButton.setText(totalReinscri(ecc) + " Ar");

                } else {
                    JOptionPane.showMessageDialog(null, "error!", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("error");
                }
            }
        });

        //=========== end ================

        //========== addEcl==============

        JLabel addEclgTitle = new JLabel("💲 Add Ecolage 💲");
        addEclgTitle.setForeground(Color.white);
        addEclgTitle.setFont(new Font("Serif", Font.BOLD, 25));
        addEclgTitle.setBounds(406, -4, 700, 100);
        panelAddEcolage.add(addEclgTitle);

        Ecolage ec = new Ecolage(0, 0, null, null, null,
                null, null, null, null);


        JLabel sumAmountAll = new JLabel("💲Total Amount💲");
        sumAmountAll.setForeground(Color.white);
        sumAmountAll.setFont(new Font("Serif", Font.BOLD, 18));
        sumAmountAll.setBounds(10, -4, 700, 100);
        panelAddEcolage.add(sumAmountAll);

        JButton sumAmountAllButton = new JButton(totalEco(ec) + " Ar");
        sumAmountAllButton.setBackground(Color.WHITE);
        sumAmountAllButton.setForeground(new Color(22, 77, 180));
        sumAmountAllButton.setFont(new Font("Serif", Font.BOLD, 16));
        sumAmountAllButton.setBounds(17, 65, 135, 28);
        panelAddEcolage.add(sumAmountAllButton);

        JLabel sumAmountDay = new JLabel("💲      Today      💲");
        sumAmountDay.setForeground(Color.white);
        sumAmountDay.setFont(new Font("Serif", Font.BOLD, 18));
        sumAmountDay.setBounds(10, 70, 700, 100);
        panelAddEcolage.add(sumAmountDay);

        JButton sumAmountToday = new JButton(totalEcoToday(ec) + " Ar");
        sumAmountToday.setBackground(Color.WHITE);
        sumAmountToday.setForeground(new Color(22, 77, 180));
        sumAmountToday.setFont(new Font("Serif", Font.BOLD, 16));
        sumAmountToday.setBounds(17, 140, 135, 28);
        panelAddEcolage.add(sumAmountToday);


        JLabel amountEcl = new JLabel("Amount");
        amountEcl.setForeground(Color.white);
        amountEcl.setFont(new Font("Serif", Font.BOLD, 16));
        amountEcl.setBounds(470, 70, 700, 100);
        panelAddEcolage.add(amountEcl);

        JTextField amountInput = new JTextField();
        amountInput.setBackground(Color.WHITE);
        amountInput.setForeground(new Color(22, 77, 180));
        amountInput.setFont(new Font("Serif", Font.BOLD, 20));
        amountInput.setBounds(409, 155, 195, 25);
        panelAddEcolage.add(amountInput);

        JLabel paymentMethod = new JLabel("Payment Method");
        paymentMethod.setForeground(Color.white);
        paymentMethod.setFont(new Font("Serif", Font.BOLD, 16));
        paymentMethod.setBounds(445, 160, 700, 100);
        panelAddEcolage.add(paymentMethod);

        String[] itemsPayment = {"Cash", "Cheques"};
        JComboBox<String> comboBoxItemsPayment = new JComboBox<>(itemsPayment);
        comboBoxItemsPayment.setFont(new Font("Serif", Font.BOLD, 16));
        comboBoxItemsPayment.setBackground(Color.WHITE);
        comboBoxItemsPayment.setForeground(new Color(22, 77, 180));
        comboBoxItemsPayment.setBounds(409, 240, 195, 25);
        panelAddEcolage.add(comboBoxItemsPayment);

        JLabel contact = new JLabel("Contact(optional)");
        contact.setForeground(Color.white);
        contact.setFont(new Font("Serif", Font.BOLD, 16));
        contact.setBounds(445, 240, 700, 100);
        panelAddEcolage.add(contact);

        JTextField contactInput = new JTextField();
        contactInput.setBackground(Color.WHITE);
        contactInput.setForeground(new Color(22, 77, 180));
        contactInput.setFont(new Font("Serif", Font.BOLD, 20));
        contactInput.setBounds(409, 310, 195, 25);
        panelAddEcolage.add(contactInput);

        JLabel month = new JLabel("Month");
        month.setForeground(Color.white);
        month.setFont(new Font("Serif", Font.BOLD, 16));
        month.setBounds(470, 330, 700, 100);
        panelAddEcolage.add(month);

        String[] itemsMonth = {"Janvier", "Février", "Mars", "Avril", "Mais", "Juin", "Juillet", "Aout",
                "Septembre", "Octobre", "Novembre", "Decembre"};
        JComboBox<String> comboBoxItemsMonth = new JComboBox<>(itemsMonth);
        comboBoxItemsMonth.setFont(new Font("Serif", Font.BOLD, 16));
        comboBoxItemsMonth.setBackground(Color.WHITE);
        comboBoxItemsMonth.setForeground(new Color(22, 77, 180));
        comboBoxItemsMonth.setBounds(409, 410, 195, 25);
        panelAddEcolage.add(comboBoxItemsMonth);

        JLabel idStudentEcl = new JLabel("Id Student");
        idStudentEcl.setForeground(Color.white);
        idStudentEcl.setFont(new Font("Serif", Font.BOLD, 17));
        idStudentEcl.setBounds(465, 420, 700, 100);
        panelAddEcolage.add(idStudentEcl);

        JTextField idStudentInputEcl = new JTextField();
        idStudentInputEcl.setBackground(Color.WHITE);
        idStudentInputEcl.setForeground(new Color(22, 77, 180));
        idStudentInputEcl.setFont(new Font("Serif", Font.BOLD, 20));
        idStudentInputEcl.setBounds(409, 500, 195, 25);
        panelAddEcolage.add(idStudentInputEcl);

        JButton addButton = new JButton("Add Ecolage");
        addButton.setBackground(Color.WHITE);
        addButton.setForeground(new Color(22, 77, 180));
        addButton.setFont(new Font("Serif", Font.BOLD, 16));
        addButton.setBounds(409, 570, 195, 28);
//        sumAmountAllButton = new JButton(totalEco(ec) + " Ar");
//        sumAmountAllButton.setBackground(Color.WHITE);
//        sumAmountAllButton.setForeground(new Color(22, 77, 180));
//        sumAmountAllButton.setFont(new Font("Serif", Font.BOLD, 16));
//        sumAmountAllButton.setBounds(17, 65, 135, 28);
//        panelAddEcolage.add(sumAmountAllButton);
        panelAddEcolage.add(addButton);

        panelAddEcolage.setVisible(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Check for empty fields
                if (amountInput.getText().isEmpty() || idStudentInputEcl.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panelAddEcolage, "Please fill in all fields",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int amount;
                try {
                    amount = Integer.parseInt(amountInput.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panelAddEcolage, "Please enter a valid amount",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String paymentMethodSelected = (String) comboBoxItemsPayment.getSelectedItem();
                String monthSelected = (String) comboBoxItemsMonth.getSelectedItem();
                String idStudentInEcl = idStudentInputEcl.getText();
                String contact = contactInput.getText();

                // Create Ecolage object
                Ecolage ecolage = new Ecolage(0, amount, paymentMethodSelected, contact, monthSelected, null, idStudentInEcl, null, null);

                try {
                    // Add ecolage to the database
                    if (ecolage.addEcolage(db.connDb(), ecolage)) {
                        JOptionPane.showMessageDialog(panelAddEcolage, "Ecolage added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        List<Ecolage> ecolages = ecolage.readEcolages(db.connDb());
                        updateTableEcl(ecolages);
                        sumAmountAllButton.setText(totalEco(ec) + " Ar");
                        sumAmountToday.setText(totalEcoToday(ec) + " Ar");

                        Student s = new Student(null, null, null, null, null, null, null, null, 0, null);
                        List<Student> stList = s.readStudentByIdStudent(db.connDb(), idStudentInputEcl.getText());

                        JFrame recu = new RecuPaiement(stList.getLast().getLastName() + " " + stList.getFirst().getFirstName(), stList.getFirst().getGrade(), (String) comboBoxItemsPayment.getSelectedItem(), Double.parseDouble(amountInput.getText()),(String)comboBoxItemsMonth.getSelectedItem() );
                        recu.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(panelAddEcolage, "Error adding ecolage", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // Handle other exceptions
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panelAddEcolage, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        //========== endAddEcl===========

        //========== listECL=============

        JLabel idStudentInEcl = new JLabel("Identification Student");
        idStudentInEcl.setForeground(Color.white);
        idStudentInEcl.setFont(new Font("Serif", Font.BOLD, 15));
        idStudentInEcl.setBounds(15, -10, 200, 100);
        panelEclList.add(idStudentInEcl);

        JTextField inputIdStudentEcl = new JTextField();
        inputIdStudentEcl.setForeground(new Color(22, 77, 180));
        inputIdStudentEcl.setFont(new Font("Serif", Font.BOLD, 20));
        inputIdStudentEcl.setBounds(180, 28, 130, 25);
        panelEclList.add(inputIdStudentEcl);


        JButton refres = new JButton("Refreshing");
        refres.setBackground(Color.WHITE);
        refres.setForeground(new Color(22, 77, 180));
        refres.setFont(new Font("Serif", Font.BOLD, 13));
        refres.setBounds(340, 28, 100, 26);
        panelEclList.add(refres);

        JButton sear = new JButton("Search");
        sear.setBackground(Color.WHITE);
        sear.setForeground(new Color(22, 77, 180));
        sear.setFont(new Font("Serif", Font.BOLD, 13));
        sear.setBounds(460, 28, 110, 26);
        panelEclList.add(sear);

        JButton delet = new JButton("Delete");
        delet.setBackground(Color.WHITE);
        delet.setForeground(new Color(22, 77, 180));
        delet.setFont(new Font("Serif", Font.BOLD, 13));
        delet.setBounds(590, 28, 110, 26);
        panelEclList.add(delet);


        String[] itemsP = {"Cash", "Cheques"};
        JComboBox<String> comboBoxItemsP = new JComboBox<>(itemsP);
        comboBoxItemsP.setFont(new Font("Serif", Font.BOLD, 16));
        comboBoxItemsP.setBackground(Color.WHITE);
        comboBoxItemsP.setForeground(new Color(22, 77, 180));
        comboBoxItemsP.setBounds(720, 28, 100, 25);
        panelEclList.add(comboBoxItemsP);


        JButton imprimListeEco = new JButton("Print 🖨");
        imprimListeEco.setBackground(Color.WHITE);
        imprimListeEco.setForeground(new Color(22, 77, 180));
        imprimListeEco.setFont(new Font("Serif", Font.BOLD, 13));
        imprimListeEco.setBounds(840, 28, 110, 26);
        panelEclList.add(imprimListeEco);

//        imprimListeEco.addActionListener(e -> {
//            imprimListeEco.setVisible(false); // Masquer le bouton avant l'impression
//            idStudentInEcl.setVisible(false);
//            inputIdStudentEcl.setVisible(false);
//            refres.setVisible(false);
//            sear.setVisible(false);
//            delet.setVisible(false);
//            comboBoxItemsP.setVisible(false);
//            printPanel(panelEclList); // Imprimer le panel sans le bouton
//            imprimListeEco.setVisible(true); // Réafficher le bouton après l'impression
//            idStudentInEcl.setVisible(true);
//            inputIdStudentEcl.setVisible(true);
//            refres.setVisible(true);
//            sear.setVisible(true);
//            delet.setVisible(true);
//            comboBoxItemsP.setVisible(true);
//        });


        String[] columnNamesEcl = {"Id", "Amount", "Payment Method", "Contact", "Month", "Date and Time", "Id Student", "Last Name", "First Name"};
        modelEcl = new DefaultTableModel(columnNamesEcl, 0);

        JTable tableEcl = new JTable(modelEcl) {
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

//        // Increase row height
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
        panelEclList.add(scrollPaneEcl);
        panelEclList.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPaneEcl.setBounds(5, 80, panelEclList.getWidth() - 10, panelEclList.getHeight() - 125);
            }
        });

        Ecolage ecolage = new Ecolage(0, 0, null, null, null, null, null, null, null);

        imprimListeEco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean complete = tableEcl.print(JTable.PrintMode.FIT_WIDTH,
                            new MessageFormat("LIST ECL"),
                            new MessageFormat("Page {0}"));
                    if (complete) {
                        JOptionPane.showMessageDialog(null, "200", "Success", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "500", "Error", JOptionPane.INFORMATION_MESSAGE);

                    }

                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'impression: ");
                }
            }
        });

        List<Ecolage> ecolages = ecolage.readEcolages(db.connDb());
        updateTableEcl(ecolages);

        refres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Ecolage> ecolages1 = ecolage.readEcolages(db.connDb());
                updateTableEcl(ecolages1);
            }
        });

        inputIdStudentEcl.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!(inputIdStudentEcl.getText().isEmpty())) {
                        List<Ecolage> ecolag = ecolage.readEcolageByIdStudent(db.connDb(), inputIdStudentEcl.getText());
                        updateTableEcl(ecolag);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter #IdStudent.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        sear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(inputIdStudentEcl.getText().isEmpty())) {
                    List<Ecolage> ecolag = ecolage.readEcolageByIdStudent(db.connDb(), inputIdStudentEcl.getText());
                    updateTableEcl(ecolag);
                } else {
                    List<Ecolage> ecola = ecolage.readEcolageByPaymentMethode(db.connDb(), (String) comboBoxItemsP.getSelectedItem());
                    updateTableEcl(ecola);
                }

            }
        });
        delet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableEcl.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableEcl.getValueAt(selectedRow, 0);
                    System.out.println("nombre " + id);
                    DbConnection db = new DbConnection();
                    if (ecolage.deleteEcolageByIdStudent(db.connDb(), id)) {
                        JOptionPane.showMessageDialog(null, "Delete successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        List<Ecolage> ec = ecolage.readEcolages(db.connDb());
                        updateTableEcl(ec);
                        sumAmountAllButton.setText(totalEco(ecolage) + " Ar");
                    } else {
                        JOptionPane.showMessageDialog(null, "Delete failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        panelEclList.setVisible(false);
        //==========END LIST ECL=========


        //navigation event
        menuStudent.addMenuListener(new

                                            MenuListener() {
                                                @Override
                                                public void menuSelected(MenuEvent e) {
                                                    panelContentElement.setVisible(false);
                                                    panelAddStudent.setVisible(true);
                                                    panelAddEcolage.setVisible(false);
                                                    panelEclList.setVisible(false);
                                                }

                                                @Override
                                                public void menuDeselected(MenuEvent e) {

                                                }

                                                @Override
                                                public void menuCanceled(MenuEvent e) {

                                                }
                                            });
        menuStudent1.addMenuListener(new

                                             MenuListener() {
                                                 @Override
                                                 public void menuSelected(MenuEvent e) {
                                                     panelContentElement.setVisible(true);
                                                     panelAddStudent.setVisible(false);
                                                     panelAddEcolage.setVisible(false);
                                                     panelEclList.setVisible(false);
                                                 }

                                                 @Override
                                                 public void menuDeselected(MenuEvent e) {

                                                 }

                                                 @Override
                                                 public void menuCanceled(MenuEvent e) {

                                                 }
                                             });
        menuStudent2.addMenuListener(new

                                             MenuListener() {
                                                 @Override
                                                 public void menuSelected(MenuEvent e) {
                                                     panelContentElement.setVisible(false);
                                                     panelAddStudent.setVisible(false);
                                                     panelAddEcolage.setVisible(false);
                                                     panelEclList.setVisible(true);
                                                 }

                                                 @Override
                                                 public void menuDeselected(MenuEvent e) {

                                                 }

                                                 @Override
                                                 public void menuCanceled(MenuEvent e) {

                                                 }
                                             });
        menuStudent3.addMouseListener(new

                                              MouseListener() {
                                                  @Override
                                                  public void mouseClicked(MouseEvent e) {
                                                      panelAddEcolage.setVisible(true);
                                                      panelContentElement.setVisible(false);
                                                      panelAddStudent.setVisible(false);
                                                      panelEclList.setVisible(false);
                                                  }

                                                  @Override
                                                  public void mousePressed(MouseEvent e) {

                                                  }

                                                  @Override
                                                  public void mouseReleased(MouseEvent e) {

                                                  }

                                                  @Override
                                                  public void mouseEntered(MouseEvent e) {

                                                  }

                                                  @Override
                                                  public void mouseExited(MouseEvent e) {

                                                  }
                                              });


        click.addActionListener(new

                                        ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (isExpanded) {
                                                    navigationBar.setBounds(0, 0, 90, 700);
                                                    panelContentElement.setBounds(180, 0, 1070, 700);
                                                    panelAddStudent.setBounds(190, 0, 1070, 650);
                                                    panelAddEcolage.setBounds(190, 0, 1070, 650);
                                                    panelEclList.setBounds(230, 0, 1000, 700);
                                                    click.setBounds(100, 0, 60, 30);
                                                    menuBar.setVisible(false);
                                                    menuBar1.setVisible(false);
                                                    menuBar2.setVisible(false);
                                                    menuBar3.setVisible(false);
                                                    menuBar4.setVisible(false);
                                                    imageLabel.setBounds(20, 30, 42, 42);
                                                    imageLabel1.setBounds(20, 100, 43, 43);
                                                    imageLabel3.setBounds(20, 220, 50, 50);
                                                } else {
                                                    navigationBar.setBounds(0, 0, 140, 700);
                                                    panelContentElement.setBounds(250, 0, 1070, 700);
                                                    panelAddStudent.setBounds(250, 0, 1000, 650);
                                                    panelAddEcolage.setBounds(250, 0, 1000, 650);
                                                    panelEclList.setBounds(250, 0, 1000, 700);
                                                    click.setBounds(170, 0, 60, 30);
                                                    menuBar.setVisible(true);
                                                    menuBar1.setVisible(true);
                                                    menuBar2.setVisible(true);
                                                    menuBar3.setVisible(true);
                                                    menuBar4.setVisible(true);
                                                    imageLabel.setBounds(20, 30, 43, 43);
                                                    imageLabel3.setBounds(20, 220, 50, 50);
                                                }
                                                isExpanded = !isExpanded;
                                            }
                                        });


        add(panelBody);
    }

    private void updateTable(List<Student> students) {
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(new Object[]{
                    s.getIdStudent(), s.getLastName(), s.getFirstName(),
                    s.getDateOfBirth(), s.getGender(), s.getGrade(), s.getNumber(), s.getEnrollmentDate(), s.getStatus(), "Ecolage Info"
            });
        }
    }

    private void updateTableEcl(List<Ecolage> ecolage) {
        modelEcl.setRowCount(0);
        for (Ecolage e : ecolage) {
            modelEcl.addRow(new Object[]{
                    e.getId(), e.getAmountEcolage() + " Ar", e.getPaymentMethod(), e.getContact(),
                    e.getMonth(), e.getDateAndTime(), e.getIdStudent(), e.getLastName(), e.getFirstName()
            });
        }
    }

    private String totalEco(Ecolage ecolage) {
        return ecolage.sumEcolageTotal(db.connDb());
    }

    private String totalEcoToday(Ecolage ecolage) {
        return ecolage.sumEcolageTotalNow(db.connDb());
    }

    private String totalRegistration(Ecolage ecolage) {
        return ecolage.SumAmountRegister(db.connDb());
    }

    private String totalInscri(Ecolage ecolage) {
        return ecolage.SumAmountInscri(db.connDb());
    }

    private String totalReinscri(Ecolage ecolage) {
        return ecolage.SumAmountReinscri(db.connDb());
    }

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


    // Custom renderer for the button
    static class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(22, 77, 180));
            setForeground(Color.white);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Ecolage Info" : value.toString());
            return this;
        }
    }

    // Custom editor for the button
    static class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JTextField textField, JTable table) {
            super(textField);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(new Color(22, 77, 180));
            button.setForeground(Color.white);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }


        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Ecolage Info" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String studentId = table.getValueAt(selectedRow, 0).toString();

                    JFrame carte = new CarteEco(studentId);
                    carte.setVisible(true);
                    System.out.println("aok");
                }
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    public static void main(String[] args) {
        JFrame dash = new Dashv1();
        dash.setVisible(true);
    }
}
