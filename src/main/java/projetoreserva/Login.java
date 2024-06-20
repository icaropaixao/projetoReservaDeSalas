package projetoreserva;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexao.ConnectionDB;

public class Login extends JFrame {

    private Connection connection;

    /**
     * Cria o formulário de Login
     */
    public Login() {
        initComponents();
        // Inicializa a conexão com o banco de dados
        try {
            connection = ConnectionDB.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        labelLoginEntrar = new JLabel();
        txtLoginEntrar = new JTextField();
        labelSenhaEntrar = new JLabel();
        txtSenhaEntrar = new JPasswordField();
        botaoEntrar = new JButton();
        botaoCadastrar = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(232, 224, 224));
        jPanel1.setToolTipText("");

        labelLoginEntrar.setText("Login");
        labelSenhaEntrar.setText("Senha");
        txtSenhaEntrar.setMinimumSize(new java.awt.Dimension(64, 30));

        botaoEntrar.setText("Entrar");
        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoEntrarActionPerformed(evt);
            }
        });

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        // Layout do painel
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(149, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(145, 145, 145))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(botaoCadastrar)
                                                .addGap(41, 41, 41)
                                                .addComponent(botaoEntrar)
                                                .addGap(38, 38, 38))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(labelLoginEntrar)
                                                .addComponent(labelSenhaEntrar)
                                                .addComponent(txtLoginEntrar)
                                                .addComponent(txtSenhaEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(21, 21, 21)
                                .addComponent(labelLoginEntrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLoginEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelSenhaEntrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSenhaEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoEntrar)
                                        .addComponent(botaoCadastrar))
                                .addGap(26, 26, 26))
        );

        // Layout do frame
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void botaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {
        // Obtém os dados do formulário
        String email = txtLoginEntrar.getText();
        String senha = new String(txtSenhaEntrar.getPassword());

        // Verifica as credenciais no banco de dados
        try {
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Exibe mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

                // Abre a tela de gerenciamento de reservas
                Reserva reservaSalas = new Reserva();
                reservaSalas.setVisible(true);

                // Fecha a janela atual de login
                this.dispose();
            } else {
                // Exibe mensagem de erro
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao realizar login.");
        }
    }

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        // Abre a tela de cadastro
        Cadastro cadastroSalas = new Cadastro();
        cadastroSalas.setVisible(true);

        // Fecha a janela atual de login
        this.dispose();
    }

    public static void main(String args[]) {
        // Configura o look and feel do sistema
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Cria e exibe o formulário
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Declaração de variáveis - não modificar
    private JButton botaoCadastrar;
    private JButton botaoEntrar;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JLabel labelLoginEntrar;
    private JLabel labelSenhaEntrar;
    private JTextField txtLoginEntrar;
    private JPasswordField txtSenhaEntrar;
    // Fim da declaração de variáveis
}
