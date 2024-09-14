package om.logiciel_gestion.ecolage.gestion_escato;


import om.logiciel_gestion.ecolage.gestion_escato.dao.DbConnection;
import om.logiciel_gestion.ecolage.gestion_escato.function.FunctionUse;
import om.logiciel_gestion.ecolage.gestion_escato.model.Admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class LoginEscato extends JFrame {

    public LoginEscato() {
        setTitle("Register");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());
        // Crée le panneau
        JPanel panel = new JPanel();
        add(panel);
        class ImagePanel extends JPanel {
            private BufferedImage image;

            public ImagePanel(BufferedImage image) {
                this.image = image;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Redimensionner l'image pour remplir le JPanel
                Image resizedImage = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);

                // Dessiner l'image redimensionnée
                g.drawImage(resizedImage, 0, 0, panelWidth, panelHeight, this);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(image.getWidth(), image.getHeight());
            }
        }


        panel.setLayout(new BorderLayout());

        // Charger l'image depuis les ressources du projet
        BufferedImage img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(LoginEscato.class.getResource("/abstrait_bleu.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement de l'image : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Créer le premier panneau (panel1) avec l'arrière-plan d'image
        JPanel panel1 = new ImagePanel(img);
        panel1.setLayout(new GridBagLayout());


        // Ajouter les étiquettes de texte avec GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);

        JLabel titre1 = new JLabel("Dashboard");
        titre1.setFont(new Font("Serif", Font.BOLD, 44));
        titre1.setForeground(Color.WHITE);
        panel1.add(titre1, gbc);

        JLabel titre2 = new JLabel("Escato ❤");
        titre2.setFont(new Font("Serif", Font.BOLD, 44));
        titre2.setForeground(Color.WHITE);
        panel1.add(titre2, gbc);

        JLabel p1 = new JLabel("Create an account");
        p1.setFont(new Font("Serif", Font.ITALIC, 19));
        p1.setForeground(Color.WHITE);
        panel1.add(p1, gbc);

        JLabel p2 = new JLabel("to Join Our Community");
        p2.setFont(new Font("Serif", Font.ITALIC, 19));
        p2.setForeground(Color.WHITE);
        panel1.add(p2, gbc);

        //panel2
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(Color.WHITE);
        panel2.setBorder(new LineBorder(Color.WHITE, 1));

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(110, -60, 300, 300);
        panel2.add(imageLabel);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() - 110) / 2) - 85;
                imageLabel.setLocation(x3, -60);
            }
        });


        JLabel titre = new JLabel("Hello ! Welcome Escato Login");
        titre.setForeground(new Color(24, 24, 24, 242));
        titre.setSize(titre.getPreferredSize());
        titre.setSize(250, 30);
        titre.setFont(new Font("Serif", Font.BOLD, 19));

        // Centrer le label dans le panel2
        int x = (panel2.getWidth() - titre.getWidth()) / 2;
        int y = 145;
        titre.setLocation(x, y);

        panel2.add(titre);
        // Mettre à jour la position du label après que le panneau a été redimensionné
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = (panel2.getWidth() - titre.getWidth() + 28) / 2;
                titre.setLocation(x, y);
            }
        });

        JLabel email = new JLabel("Email");
        email.setForeground(new Color(24, 24, 24, 242));
        email.setSize(email.getPreferredSize());

        int x1 = ((panel2.getWidth() - email.getWidth()) / 2) - 150;
        int y1 = 190;
        email.setLocation(x1, y1);
        panel2.add(email);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x2 = ((panel2.getWidth() - email.getWidth()) / 2) - 150;
                email.setLocation(x2, y1);
            }
        });

        ImageIcon emailIcon = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/mail.png")));
        ImageIcon passIcon = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/password.png")));
        Image imageMail = emailIcon.getImage();
        Image imagePass = passIcon.getImage();
        Image scaledMail = imageMail.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image scalePass = imagePass.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scMail = new ImageIcon(scaledMail);
        ImageIcon scPass = new ImageIcon(scalePass);
        JLabel mailLabel = new JLabel(scMail);
        JLabel passLabel = new JLabel(scPass);
        mailLabel.setBounds(66, 194, 105, 105);
        passLabel.setBounds(66, 285, 105, 105);
        panel2.add(mailLabel);
        panel2.add(passLabel);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth()) / 2) - 201;
                mailLabel.setLocation(x3, 194);
                passLabel.setLocation(x3, 285);
            }
        });

        JTextField emailField = new JTextField();
        emailField.setBounds(x1, 230, 300, 30);
        panel2.add(emailField);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() - email.getWidth()) / 2) - 120;
                emailField.setLocation(x3, 230);
            }
        });

        JLabel password = new JLabel("Password");
        password.setForeground(new Color(24, 24, 24, 242));
        password.setBounds(125, 280, 320, 30);
        panel2.add(password);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() - 125) / 2) - 100;
                password.setLocation(x3, 280);
            }
        });
        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setBounds(125, 320, 303, 30);
        panel2.add(passwordInput);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() - 125) / 2) - 73;
                passwordInput.setLocation(x3, 320);
            }
        });

        JLabel resetPassword = new JLabel("Reset Password!");
        resetPassword.setForeground(new Color(71, 71, 245));
        resetPassword.setBounds(158, 360, 320, 30);
        panel2.add(resetPassword);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() - 100) / 2) - 113;
                resetPassword.setLocation(x3, 360);
            }
        });
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(128, 410, 332, 30);
        buttonLogin.setForeground(Color.white);
        buttonLogin.setBackground(new Color(22, 77, 180));
        panel2.add(buttonLogin);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() - 128) / 2) - 98;
                buttonLogin.setLocation(x3, 410);
            }
        });

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordInput.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Information incomplete. Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!email.contains("@gmail.com")) {
                    JOptionPane.showMessageDialog(null, "Email should contain '@gmail.com'.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    DbConnection db = new DbConnection();
                    FunctionUse functionUse = new FunctionUse();
                    if (functionUse.authenticateAccount(email, password, db.connDb())) {
                        JFrame dashboard = new Dashv1();
                        dashboard.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Bad information. Please check your email and password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        passwordInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String email = emailField.getText();
                    String password = new String(passwordInput.getPassword());

                    if (email.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Information incomplete. Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!email.contains("@gmail.com")) {
                        JOptionPane.showMessageDialog(null, "Email should contain '@gmail.com'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DbConnection db = new DbConnection();
                        FunctionUse functionUse = new FunctionUse();
                        if (functionUse.authenticateAccount(email, password, db.connDb())) {
                            JFrame dashboard = new Dashv1();
                            dashboard.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Bad information. Please check your email and password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String email = emailField.getText();
                    String password = new String(passwordInput.getPassword());

                    if (email.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Information incomplete. Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!email.contains("@gmail.com")) {
                        JOptionPane.showMessageDialog(null, "Email should contain '@gmail.com'.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DbConnection db = new DbConnection();
                        FunctionUse functionUse = new FunctionUse();
                        if (functionUse.authenticateAccount(email, password, db.connDb())) {
                            JFrame dashboard = new Dashv1();
                            dashboard.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Bad information. Please check your email and password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        JLabel p3 = new JLabel("Don't have an Account? ");
        p3.setForeground(new Color(24, 24, 24, 242));
        p3.setBounds(128, 470, 320, 30);
        panel2.add(p3);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() - 128) / 2) - 98;
                p3.setLocation(x3, 470);
            }
        });
        JButton createAccount = new JButton("Create");
        createAccount.setForeground(Color.WHITE);
        createAccount.setBounds(360, 470, 88, 23);
        createAccount.setBackground(new Color(22, 77, 180));
        panel2.add(createAccount);
        panel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel2.getWidth() + 165) / 2);
                createAccount.setLocation(x3, 470);
            }
        });
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame authPin = new AuthPin();
                authPin.setVisible(true);

            }

        });

        // Créer le JSplitPane pour séparer les deux panneaux
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
        splitPane.setDividerLocation(650);
        splitPane.setDividerSize((int) 0.1);
        splitPane.setEnabled(false);
        panel.add(splitPane, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        SpringApplication.run(LoginEscato.class, args);
        DbConnection dbConnection = new DbConnection();
        dbConnection.connDb();
        new LoginEscato().setVisible(true);

    }
}

class SignUp extends JFrame {

    public SignUp() {
        setTitle("Register");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());

        // Crée le panneau
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setLayout(null);
        JLabel welcomeLabel = new JLabel("Register");
        welcomeLabel.setBounds(180, -70, 200, 200);
        welcomeLabel.setForeground(new Color(9, 9, 9, 242));
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 39));

        panel.add(welcomeLabel);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 71;
                welcomeLabel.setLocation(x, -70);
            }
        });

        JLabel phrase = new JLabel("Kindly fill in this form to register.");
        phrase.setBounds(127, -10, 500, 200);
        welcomeLabel.setForeground(new Color(31, 31, 31, 242));
        phrase.setFont(new Font("Serif", Font.BOLD, 15));
        panel.add(phrase);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 109;
                phrase.setLocation(x, -10);
            }
        });

        JLabel userName = new JLabel("Username");
        userName.setFont(new Font("Serif", Font.BOLD, 16));
        userName.setForeground(new Color(26, 26, 26, 255));
        userName.setBounds(10, 30, 500, 200);
        panel.add(userName);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                userName.setLocation(x, 30);
            }
        });
        JTextField enterUserName = new JTextField();
        enterUserName.setBounds(10, 152, 320, 29);
        panel.add(enterUserName);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                enterUserName.setLocation(x, 152);
            }
        });


        JLabel email = new JLabel("Email");
        email.setFont(new Font("Serif", Font.BOLD, 16));
        email.setForeground(new Color(26, 26, 26, 255));
        email.setBounds(10, 98, 500, 200);
        panel.add(email);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                email.setLocation(x, 98);
            }
        });
        JTextField enterEmail = new JTextField();
        enterEmail.setBounds(10, 219, 320, 29);
        panel.add(enterEmail);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                enterEmail.setLocation(x, 219);
            }
        });

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Serif", Font.BOLD, 16));
        password.setForeground(new Color(26, 26, 26, 255));
        password.setBounds(10, 168, 500, 200);
        panel.add(password);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                password.setLocation(x, 168);
            }
        });

        JTextField enterPassword = new JTextField();
        enterPassword.setBounds(10, 290, 320, 29);
        panel.add(enterPassword);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                enterPassword.setLocation(x, 290);
            }
        });
        JLabel repeatPassword = new JLabel("Repeat Password");
        repeatPassword.setFont(new Font("Serif", Font.BOLD, 16));
        repeatPassword.setForeground(new Color(26, 26, 26, 255));
        repeatPassword.setBounds(10, 238, 500, 200);
        panel.add(repeatPassword);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                repeatPassword.setLocation(x, 238);
            }
        });

        JTextField enterRepeatPassword = new JTextField();
        enterRepeatPassword.setBounds(10, 362, 320, 29);
        panel.add(enterRepeatPassword);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 160;
                enterRepeatPassword.setLocation(x, 362);
            }
        });

        JButton register = new JButton("Register");
        register.setBounds(10, 410, 320, 30);
        register.setForeground(Color.white);
        register.setBackground(new Color(22, 77, 180));
        panel.add(register);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth()) / 2) - 160;
                register.setLocation(x3, 410);
            }
        });


        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = enterUserName.getText();
                String email = enterEmail.getText();
                String password = enterPassword.getText();
                String password2 = enterRepeatPassword.getText();
                if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty information", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Admin admin = new Admin(userName, email, password);
                    DbConnection db = new DbConnection();
                    if (password2.equals(password)) {
                        admin.addAdmin(admin, db.connDb());
                        JOptionPane.showMessageDialog(null, "Add successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        enterUserName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String userName = enterUserName.getText();
                    String email = enterEmail.getText();
                    String password = enterPassword.getText();
                    String password2 = enterRepeatPassword.getText();
                    if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty information", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Admin admin = new Admin(userName, email, password);
                        DbConnection db = new DbConnection();
                        if (password2.equals(password)) {
                            admin.addAdmin(admin, db.connDb());
                            JOptionPane.showMessageDialog(null, "Add successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        enterEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String userName = enterUserName.getText();
                    String email = enterEmail.getText();
                    String password = enterPassword.getText();
                    String password2 = enterRepeatPassword.getText();
                    if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty information", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Admin admin = new Admin(userName, email, password);
                        DbConnection db = new DbConnection();
                        if (password2.equals(password)) {
                            admin.addAdmin(admin, db.connDb());
                            JOptionPane.showMessageDialog(null, "Add successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        enterPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String userName = enterUserName.getText();
                    String email = enterEmail.getText();
                    String password = enterPassword.getText();
                    String password2 = enterRepeatPassword.getText();
                    if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty information", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Admin admin = new Admin(userName, email, password);
                        DbConnection db = new DbConnection();
                        if (password2.equals(password)) {
                            admin.addAdmin(admin, db.connDb());
                            JOptionPane.showMessageDialog(null, "Add successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        enterRepeatPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String userName = enterUserName.getText();
                    String email = enterEmail.getText();
                    String password = enterPassword.getText();
                    String password2 = enterRepeatPassword.getText();
                    if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Empty information", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Admin admin = new Admin(userName, email, password);
                        DbConnection db = new DbConnection();
                        if (password2.equals(password)) {
                            admin.addAdmin(admin, db.connDb());
                            JOptionPane.showMessageDialog(null, "Add successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });


        JButton back = new JButton("⬅");
        back.setBounds(4, 520, 50, 20);
        back.setForeground(Color.white);
        back.setBackground(new Color(22, 77, 180));
        panel.add(back);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x3 = ((panel.getWidth()) / 2) - 160;
                back.setLocation(x3, 520);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }
}

class AuthPin extends JFrame {
    public AuthPin() throws HeadlessException {
        setTitle("AuthPin");
        setSize(320, 170);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon m = new ImageIcon(Objects.requireNonNull(LoginEscato.class.getResource("/logoSacre-removebg.png")));
        setIconImage(m.getImage());

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
        panel.setLayout(null);

        JLabel pinCode = new JLabel("Enter Pin code ");
        pinCode.setBounds(103, -70, 400, 200);
        pinCode.setForeground(new Color(9, 9, 9, 242));
        pinCode.setFont(new Font("Serif", Font.BOLD, 16));
        panel.add(pinCode);

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 50;
                pinCode.setLocation(x, -70);
            }
        });
        JTextField pinCodeField = new JTextField();
        pinCodeField.setBounds(40, 50, 230, 30);
        panel.add(pinCodeField);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 110;
                pinCodeField.setLocation(x, 50);
            }
        });

        JButton checks = new JButton("Check");
        checks.setBounds(103, 90, 96, 30);
        checks.setForeground(Color.white);
        checks.setBackground(new Color(22, 77, 180));
        panel.add(checks);
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int x = ((panel.getWidth()) / 2) - 50;
                checks.setLocation(x, 90);
            }
        });

        pinCodeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String pinCode = pinCodeField.getText();
                    DbConnection db = new DbConnection();
                    FunctionUse functionUse = new FunctionUse();
                    if (functionUse.checkPin(db.connDb(), pinCode)) {
                        dispose();
                        JFrame signUp = new SignUp();
                        signUp.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Bad pin.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        checks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pinCode = pinCodeField.getText();
                DbConnection db = new DbConnection();
                FunctionUse functionUse = new FunctionUse();
                if (functionUse.checkPin(db.connDb(), pinCode)) {
                    dispose();
                    JFrame signUp = new SignUp();
                    signUp.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Bad pin.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }
}

