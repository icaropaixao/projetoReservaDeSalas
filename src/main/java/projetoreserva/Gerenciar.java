package projetoreserva;

import javax.swing.*;
import java.sql.*;

public class Gerenciar extends javax.swing.JFrame {

    public Gerenciar() {
        initComponents();

        // Adicionar ActionListener para o botão "Salvar"
        botaoSalvar.addActionListener(e -> salvarSala());

        // Adicionar ActionListener para o botão "Cancelar"
        botaoCancelar.addActionListener(e -> {
            // Cria instância da tela de reserva e torna visível
            Reserva reservaView = new Reserva();
            reservaView.setVisible(true);

            // Fecha a tela atual de gerenciamento
            this.dispose();
        });

        // Adicionar ActionListener para o botão "Visualizar Salas"
        botaoVisualizar.addActionListener(e -> visualizarSalas());

        // Adicionar ActionListener para o botão "Excluir Sala"
        botaoExcluir.addActionListener(e -> excluirSala());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        labelSelecioneSalaGerenciar = new javax.swing.JLabel();
        botaoSalvar = new javax.swing.JButton();
        textNome = new javax.swing.JTextField();
        labelNome = new javax.swing.JLabel();
        labelCapacidade = new javax.swing.JLabel();
        textCapacidade = new javax.swing.JTextField();
        labelInformacoe = new javax.swing.JLabel();
        textInformacoes = new javax.swing.JTextField();
        botaoCancelar = new javax.swing.JButton();
        botaoVisualizar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        labelSelecioneSalaGerenciar.setText("Gerenciar salas");

        botaoSalvar.setText("Salvar");

        labelNome.setText("Nome");

        labelCapacidade.setText("Capacidade");

        labelInformacoe.setText("Informações");

        botaoCancelar.setText("Cancelar");

        botaoVisualizar.setText("Visualizar Salas");

        botaoExcluir.setText("Excluir Sala");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(154, 154, 154)
                                                .addComponent(labelSelecioneSalaGerenciar))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(labelInformacoe)
                                                        .addComponent(textNome)
                                                        .addComponent(labelNome)
                                                        .addComponent(labelCapacidade)
                                                        .addComponent(textCapacidade)
                                                        .addComponent(textInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(94, 94, 94)
                                                .addComponent(botaoSalvar)
                                                .addGap(18, 18, 18)
                                                .addComponent(botaoVisualizar)
                                                .addGap(18, 18, 18)
                                                .addComponent(botaoExcluir)
                                                .addGap(18, 18, 18)
                                                .addComponent(botaoCancelar)))
                                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelSelecioneSalaGerenciar)
                                .addGap(25, 25, 25)
                                .addComponent(labelNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelCapacidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textCapacidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(labelInformacoe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textInformacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoSalvar)
                                        .addComponent(botaoVisualizar)
                                        .addComponent(botaoExcluir)
                                        .addComponent(botaoCancelar))
                                .addGap(25, 25, 25))
        );

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

    private void salvarSala() {
        // Método para salvar uma nova sala no banco de dados

        String nome = textNome.getText();
        int capacidade = Integer.parseInt(textCapacidade.getText());
        String informacoes = textInformacoes.getText();

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoreserva", "root", "98380439");

            String sql = "INSERT INTO salas (nome, capacidade, informacoes) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, capacidade);
            pst.setString(3, informacoes);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sala cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao cadastrar sala.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar sala: " + ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    private void visualizarSalas() {
        // Método para visualizar as salas cadastradas no banco de dados

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoreserva", "root", "98380439");
            st = con.createStatement();
            String sql = "SELECT s.id, s.nome, s.capacidade, s.recursos, " +
                    "(CASE WHEN r.id IS NOT NULL THEN 'Reservada' ELSE 'Livre' END) AS status " +
                    "FROM salas s " +
                    "LEFT JOIN reservas r ON s.id = r.sala_id";
            rs = st.executeQuery(sql);

            StringBuilder salasList = new StringBuilder("Salas Disponíveis:\n");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int capacidade = rs.getInt("capacidade");
                String recursos = rs.getString("recursos");
                String status = rs.getString("status");

                salasList.append("ID: ").append(id)
                        .append(", Nome: ").append(nome)
                        .append(", Capacidade: ").append(capacidade)
                        .append(", Recursos: ").append(recursos)
                        .append(", Status: ").append(status).append("\n");
            }

            JOptionPane.showMessageDialog(this, salasList.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar salas: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    private void excluirSala() {
        // Método para excluir uma sala e suas reservas associadas do banco de dados

        String salaIdStr = JOptionPane.showInputDialog(this, "Digite o ID da sala a ser excluída:");
        int salaId = Integer.parseInt(salaIdStr);

        Connection con = null;
        PreparedStatement pstDeleteReservas = null;
        PreparedStatement pstDeleteSala = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoreserva", "root", "98380439");

            // Excluir as reservas associadas à sala
            String sqlDeleteReservas = "DELETE FROM reservas WHERE sala_id = ?";
            pstDeleteReservas = con.prepareStatement(sqlDeleteReservas);
            pstDeleteReservas.setInt(1, salaId);

            int rowsAffectedReservas = pstDeleteReservas.executeUpdate();

            // Se as reservas foram excluídas com sucesso ou não existiam, então excluir a sala
            if (rowsAffectedReservas >= 0) {
                String sqlDeleteSala = "DELETE FROM salas WHERE id = ?";
                pstDeleteSala = con.prepareStatement(sqlDeleteSala);
                pstDeleteSala.setInt(1, salaId);

                int rowsAffectedSala = pstDeleteSala.executeUpdate();

                if (rowsAffectedSala > 0) {
                    JOptionPane.showMessageDialog(this, "Sala excluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao excluir sala.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao excluir reservas associadas à sala.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir sala: " + ex.getMessage());
        } finally {
            try {
                if (pstDeleteReservas != null) {
                    pstDeleteReservas.close();
                }
                if (pstDeleteSala != null) {
                    pstDeleteSala.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        /* Configura o look and feel Nimbus */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gerenciar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Cria e exibe a interface gráfica */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gerenciar().setVisible(true);
            }
        });
    }

    // Declaração de variáveis - não modifique
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoVisualizar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCapacidade;
    private javax.swing.JLabel labelInformacoe;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSelecioneSalaGerenciar;
    private javax.swing.JTextField textCapacidade;
    private javax.swing.JTextField textInformacoes;
    private javax.swing.JTextField textNome;
    // Fim da declaração de variáveis
}
