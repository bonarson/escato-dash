package om.logiciel_gestion.ecolage.gestion_escato;

import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;
import om.logiciel_gestion.ecolage.gestion_escato.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class UpdateFram extends JFrame {

    public UpdateFram(String champ, DefaultTableModel model, int row, int column,
                      String columnName, String columnValue, String idStudent) {

        setSize(300, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());
        // Create the panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setLayout(null);

        JLabel title = new JLabel("Make your change");
        title.setFont(new Font("Serif", Font.BOLD, 25));
        title.setBounds(10, -10, 300, 100);
        title.setForeground(new Color(24, 24, 24, 242));
        panel.add(title);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 190) / 2);
                title.setLocation(x3, -10);
            }
        });

        JLabel value = new JLabel("Value");
        value.setFont(new Font("Serif", Font.BOLD, 17));
        value.setBounds(10, -10, 300, 100);
        value.setForeground(new Color(24, 24, 24, 242));
        panel.add(value);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 47) / 2);
                value.setLocation(x3, 42);
            }
        });

        JTextField inputValue = new JTextField(champ);
        inputValue.setFont(new Font("Serif", Font.BOLD, 14));
        inputValue.setBounds(10, -10, 190, 30);
        panel.add(inputValue);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 190) / 2);
                inputValue.setLocation(x3, 127);
            }
        });

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10, -10, 190, 30);
        changeButton.setBackground(new Color(22, 77, 180));
        changeButton.setForeground(Color.white);
        panel.add(changeButton);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 190) / 2);
                changeButton.setLocation(x3, 185);
            }
        });

        inputValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Student student = new Student(null, null, null, null, null, null, null, null, 0, null);
                    DbConnection db = new DbConnection();
                    if (columnName.toLowerCase().equals("gender")) {
                        if (student.updateStudent(db.connDb(), columnName.toLowerCase(Locale.ROOT), inputValue.getText(), idStudent)) {
                            System.out.println("update mety");
                        } else {
                            System.out.println("update ts mety");
                        }
                    } else if (columnName.toLowerCase(Locale.ROOT).equals("class")) {
                        if (student.updateStudent(db.connDb(), "grade", inputValue.getText(), idStudent)) {
                            System.out.println("update mety");
                        } else {
                            System.out.println("update ts mety");
                        }
                    } else if (columnName.toLowerCase(Locale.ROOT).equals("number")) {

                        if (student.updateStudent(db.connDb(), "num", inputValue.getText(), idStudent)) {
                            System.out.println("update mety");
                        } else {
                            System.out.println("update ts mety");
                        }
                    } else if (columnName.toLowerCase(Locale.ROOT).equals("last name")) {
                        if (student.updateStudent(db.connDb(), "last_name", inputValue.getText(), idStudent)) {
                            System.out.println("update mety");
                        } else {
                            System.out.println("update ts mety");
                        }
                    } else if (columnName.toLowerCase(Locale.ROOT).equals("first name")) {
                        if (student.updateStudent(db.connDb(), "first_name", inputValue.getText(), idStudent)) {
                            System.out.println("update mety");
                        } else {
                            System.out.println("update ts mety");
                        }
                    } else if (columnName.toLowerCase(Locale.ROOT).equals("date of birth")) {
                        if (student.updateStudent(db.connDb(), "date_of_birth", inputValue.getText(), idStudent)) {
                            System.out.println("update mety");
                        } else {
                            System.out.println("update ts mety");
                        }

                    } else if (columnName.toLowerCase(Locale.ROOT).equals("enrollment date")) {

                        if (student.updateStudent(db.connDb(), "enrollment_date", inputValue.getText(), idStudent)) {
                            System.out.println("update mety");
                        } else {
                            System.out.println("update ts mety");
                        }
                    }

                    model.setValueAt(inputValue.getText(), row, column);
                    setVisible(false);
                }
            }

        });

        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student student = new Student(null, null, null, null, null, null, null, null, 0, null);
                DbConnection db = new DbConnection();
                if (columnName.toLowerCase().equals("gender")) {
                    if (student.updateStudent(db.connDb(), columnName.toLowerCase(Locale.ROOT), inputValue.getText(), idStudent)) {
                        System.out.println("update mety");
                    } else {
                        System.out.println("update ts mety");
                    }
                } else if (columnName.toLowerCase(Locale.ROOT).equals("class")) {
                    if (student.updateStudent(db.connDb(), "grade", inputValue.getText(), idStudent)) {
                        System.out.println("update mety");
                    } else {
                        System.out.println("update ts mety");
                    }
                } else if (columnName.toLowerCase(Locale.ROOT).equals("number")) {

                    if (student.updateStudent(db.connDb(), "num", inputValue.getText(), idStudent)) {
                        System.out.println("update mety");
                    } else {
                        System.out.println("update ts mety");
                    }
                } else if (columnName.toLowerCase(Locale.ROOT).equals("last name")) {
                    if (student.updateStudent(db.connDb(), "last_name", inputValue.getText(), idStudent)) {
                        System.out.println("update mety");
                    } else {
                        System.out.println("update ts mety");
                    }
                } else if (columnName.toLowerCase(Locale.ROOT).equals("first name")) {
                    if (student.updateStudent(db.connDb(), "first_name", inputValue.getText(), idStudent)) {
                        System.out.println("update mety");
                    } else {
                        System.out.println("update ts mety");
                    }
                } else if (columnName.toLowerCase(Locale.ROOT).equals("date of birth")) {
                    if (student.updateStudent(db.connDb(), "date_of_birth", inputValue.getText(), idStudent)) {
                        System.out.println("update mety");
                    } else {
                        System.out.println("update ts mety");
                    }

                } else if (columnName.toLowerCase(Locale.ROOT).equals("enrollment date")) {

                    if (student.updateStudent(db.connDb(), "enrollment_date", inputValue.getText(), idStudent)) {
                        System.out.println("update mety");
                    } else {
                        System.out.println("update ts mety");
                    }
                }

                model.setValueAt(inputValue.getText(), row, column);
                setVisible(false);
            }
        });

    }


//    public static void main(String[] args) {
//        new UpdateFram("", null, 0, 0, "", "", "");
//    }
}
