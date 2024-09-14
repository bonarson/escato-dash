package om.logiciel_gestion.ecolage.gestion_escato.model;

import om.logiciel_gestion.ecolage.gestion_escato.LoginEscato;
import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;
import om.logiciel_gestion.ecolage.gestion_escato.function.FunctionUse;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Pin extends JFrame {

    public Pin() throws HeadlessException {
        setTitle("");
        setSize(320, 230);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setLayout(null);

        JLabel pinCodeOld = new JLabel("Enter Old Pin  ");
        pinCodeOld.setBounds(103, -70, 400, 200);
        pinCodeOld.setForeground(new Color(9, 9, 9, 242));
        pinCodeOld.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(pinCodeOld);

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 50;
                pinCodeOld.setLocation(x, -80);
            }
        });

        JTextField pinCodeFieldOld = new JTextField();
        pinCodeFieldOld.setBounds(40, 50, 230, 30);
        panel.add(pinCodeFieldOld);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 110;
                pinCodeFieldOld.setLocation(x, 35);
            }
        });

        JLabel pinCode = new JLabel("Enter new Pin  ");
        pinCode.setBounds(103, -70, 400, 200);
        pinCode.setForeground(new Color(9, 9, 9, 242));
        pinCode.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(pinCode);

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 50;
                pinCode.setLocation(x, -17);
            }
        });

        JTextField pinCodeField = new JTextField();
        pinCodeField.setBounds(40, 50, 230, 30);
        panel.add(pinCodeField);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 110;
                pinCodeField.setLocation(x, 100);
            }
        });

        JButton checks = new JButton("Change");
        checks.setBounds(103, 90, 96, 30);
        checks.setForeground(Color.white);
        checks.setBackground(new Color(22, 77, 180));
        panel.add(checks);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 50;
                checks.setLocation(x, 140);
            }
        });

        checks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pinCodeOld = pinCodeFieldOld.getText();
                String pinCodeNew = pinCodeField.getText();
                DbConnection db = new DbConnection();
                FunctionUse functionUse = new FunctionUse();
                if (functionUse.changePinCode(db.connDb(), pinCodeOld, pinCodeNew)) {
                    JOptionPane.showMessageDialog(null, "Change Ok.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Change pin ok");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Bad Old pin.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pinCodeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String pinCodeOld = pinCodeFieldOld.getText();
                    String pinCodeNew = pinCodeField.getText();
                    DbConnection db = new DbConnection();
                    FunctionUse functionUse = new FunctionUse();
                    if (functionUse.changePinCode(db.connDb(), pinCodeOld, pinCodeNew)) {
                        JOptionPane.showMessageDialog(null, "Change Ok.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Change pin ok");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Bad Old pin.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


    }

    public static void main(String[] args) {


    }
}
