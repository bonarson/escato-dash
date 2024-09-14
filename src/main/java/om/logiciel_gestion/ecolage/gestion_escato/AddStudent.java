package om.logiciel_gestion.ecolage.gestion_escato;

import javax.swing.*;
import java.awt.*;

public class AddStudent extends JFrame {
    public AddStudent() {
        setSize(700, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setLayout(null);

        JLabel title = new JLabel("Student registration form");
        title.setBounds(10, 0, 4000, 200);
        title.setBackground(new Color(22, 77, 180));
        title.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(title);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth() - 210) / 2);
                title.setLocation(x3, -50);
            }
        });

    }


}
