package om.logiciel_gestion.ecolage.gestion_escato;

import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;
import om.logiciel_gestion.ecolage.gestion_escato.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class StudentList extends JFrame {

    private JTextField inputNumber;
    private DefaultTableModel model;
    private DbConnection db;
    private Student student;

    public StudentList() {
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());
        // Create the panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(110, -60, 100, 100);
        panel.add(imageLabel);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 1240) / 2);
                imageLabel.setLocation(x3, -9);
            }
        });

        JLabel identificationNumber = new JLabel("Identification Number");
        identificationNumber.setForeground(new Color(42, 42, 42, 236));
        identificationNumber.setFont(new Font("Serif", Font.BOLD, 14));
        identificationNumber.setBounds(100, -10, 200, 100);
        panel.add(identificationNumber);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 800) / 2);
                identificationNumber.setLocation(x3, 0);
            }
        });

        inputNumber = new JTextField();
        inputNumber.setBounds(100, 50, 130, 25);
        panel.add(inputNumber);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 498) / 2);
                inputNumber.setLocation(x3, 38);
            }
        });

        JButton refreshing = new JButton("Refreshing");
        refreshing.setForeground(Color.WHITE);
        refreshing.setBounds(200, 53, 100, 25);
        refreshing.setBackground(new Color(22, 77, 180));
        panel.add(refreshing);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 220) / 2);
                refreshing.setLocation(x3, 38);
            }
        });

        JButton search = new JButton("Search");
        search.setForeground(Color.WHITE);
        search.setBounds(200, 53, 110, 25);
        search.setBackground(new Color(22, 77, 180));
        panel.add(search);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() + 7) / 2);
                search.setLocation(x3, 38);
            }
        });

        JButton delete = new JButton("Delete");
        delete.setForeground(Color.WHITE);
        delete.setBounds(200, 53, 88, 25);
        delete.setBackground(new Color(22, 77, 180));
        panel.add(delete);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() + 250) / 2);
                delete.setLocation(x3, 38);
            }
        });

        JButton update = new JButton("Update");
        update.setForeground(Color.WHITE);
        update.setBounds(200, 53, 88, 25);
        update.setBackground(new Color(22, 77, 180));
        panel.add(update);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() + 450) / 2);
                update.setLocation(x3, 38);
            }
        });


        JLabel classLabel = new JLabel("Class");
        classLabel.setForeground(new Color(24, 24, 24, 242));
        classLabel.setFont(new Font("Serif", Font.BOLD, 16));
        classLabel.setBounds(100, -10, 200, 100);
        panel.add(classLabel);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() + 920) / 2);
                classLabel.setLocation(x3, 0);
            }
        });

        JTextField classInput = new JTextField();
        classInput.setBounds(100, 130, 129, 25);
        panel.add(classInput);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() + 1012) / 2);
                classInput.setLocation(x3, 38);
            }
        });

        db = new DbConnection();
        student = new Student(null, null, null, null, null, null, null, null, 0, null);

        String[] columnNames = {"ID Number", "Last Name", "First Name", "Date of birth", "Gender", "Class", "Number", "Enrollment Date", "Ecolage"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return column == 8; // Only the "Ecolage" column is editable
            }
        };

        // Center align text in all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add custom renderer and editor for the "Ecolage" column
        table.getColumnModel().getColumn(8).setCellRenderer(new Dashv1.ButtonRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new Dashv1.ButtonEditor(new JTextField(), table));

        // Increase row height
        table.setRowHeight(40);

        // Adjust column widths
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 200, 900, 300);
        scrollPane.setBackground(Color.WHITE);
        table.setBackground(Color.WHITE);
        panel.add(scrollPane);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                scrollPane.setBounds(5, 105, panel.getWidth() - 10, panel.getHeight() - 109);
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

        classInput.addKeyListener(new KeyAdapter() {
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

        // Add action listener for the search button
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
    }

    private void updateTable(List<Student> students) {
        model.setRowCount(0);
        for (Student s : students) {
            model.addRow(new Object[]{
                    s.getIdStudent(), s.getLastName(), s.getFirstName(),
                    s.getDateOfBirth(), s.getGender(), s.getGrade(), s.getNumber(), s.getEnrollmentDate(), "Ecolage Info"
            });
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new StudentList().setVisible(true));
//    }
}

//// Custom renderer for the button
//class ButtonRenderer extends JButton implements TableCellRenderer {
//
//    public ButtonRenderer() {
//        setOpaque(true);
//        setBackground(new Color(22, 77, 180));
//        setForeground(Color.white);
//    }
//
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        setText((value == null) ? "Ecolage Info" : value.toString());
//        return this;
//    }
//}
//
//// Custom editor for the button
//class ButtonEditor extends DefaultCellEditor {
//    protected JButton button;
//    private String label;
//    private boolean isPushed;
//    private JTable table;
//
//    public ButtonEditor(JTextField textField, JTable table) {
//        super(textField);
//        this.table = table;
//        button = new JButton();
//        button.setOpaque(true);
//        button.setBackground(new Color(22, 77, 180));
//        button.setForeground(Color.white);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                fireEditingStopped();
//            }
//        });
//    }
//
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        label = (value == null) ? "Ecolage Info" : value.toString();
//        button.setText(label);
//        isPushed = true;
//        return button;
//    }
//
//    public Object getCellEditorValue() {
//        if (isPushed) {
//            int selectedRow = table.getSelectedRow();
//            if (selectedRow != -1) {
//                String studentId = table.getValueAt(selectedRow, 0).toString();
//                System.out.println("aoky");
//            }
//        }
//        isPushed = false;
//        return label;
//    }
//
//    public boolean stopCellEditing() {
//        isPushed = false;
//        return super.stopCellEditing();
//    }
//
//    protected void fireEditingStopped() {
//        super.fireEditingStopped();
//    }
//}
