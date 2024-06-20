package projetoreserva;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reserva extends javax.swing.JFrame {

    // Map para armazenar os IDs das salas
    private Map<String, Integer> salaMap;

    public Reserva() {
        initComponents();
        carregarSalas();

        // Adicionando ActionListener para o botão "Reservar Sala"
        botaoReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoReservarActionPerformed(evt);
            }
        });

        // Adicionando ActionListener para o botão "Gerenciar Salas"
        botaoGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerenciarActionPerformed(evt);
            }
        });

        // Adicionando ActionListener para o botão "Sair"
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });

        // Adicionando FocusLost para validar o formato da data
        textData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textDataFocusLost(evt);
            }
        });

        // Adicionando FocusLost para validar o formato da hora
        textHoraInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textHoraFocusLost(evt);
            }
        });

        textHoraFim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textHoraFocusLost(evt);
            }
        });
    }

    private void carregarSalas() {
        salaMap = new HashMap<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoreserva", "root", "98380439");

            String sql = "SELECT id, nome FROM salas";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            selecSala.removeAllItems(); // Limpar o JComboBox antes de adicionar os itens

            while (rs.next()) {
                String nomeSala = rs.getString("nome");
                int idSala = rs.getInt("id");
                salaMap.put(nomeSala, idSala);
                selecSala.addItem(nomeSala); // Adicionar o nome da sala no JComboBox
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar salas: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelBemVindo = new javax.swing.JLabel();
        labelSelecioneSala = new javax.swing.JLabel();
        selecSala = new javax.swing.JComboBox<>();
        labelData = new javax.swing.JLabel();
        labelHoraInicio = new javax.swing.JLabel();
        labelHoraFim = new javax.swing.JLabel();
        textData = new javax.swing.JTextField();
        textHoraInicio = new javax.swing.JTextField();
        textHoraFim = new javax.swing.JTextField();
        botaoGerenciar = new javax.swing.JButton();
        botaoReservar = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        labelBemVindo.setText("Bem-vindo ao Sistema de Reserva de Salas!");

        labelSelecioneSala.setText("Selecione a sala");

        labelData.setText("Data");

        labelHoraInicio.setText("Hora Início");

        labelHoraFim.setText("Hora Fim");

        botaoGerenciar.setText("Gerenciar salas");

        botaoReservar.setText("Reservar Sala");

        botaoSair.setText("Sair");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(selecSala, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(labelData)
                                                                        .addComponent(textData, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(textHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(labelHoraInicio))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(labelHoraFim)
                                                                        .addComponent(textHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(botaoReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(botaoGerenciar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(156, 156, 156)
                                                                .addComponent(botaoSair))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(88, 88, 88)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(labelSelecioneSala)
                                                                        .addComponent(labelBemVindo))))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(labelBemVindo)
                                .addGap(18, 18, 18)
                                .addComponent(labelSelecioneSala)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selecSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelData)
                                        .addComponent(labelHoraInicio)
                                        .addComponent(labelHoraFim))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(botaoGerenciar)
                                        .addComponent(botaoReservar))
                                .addGap(28, 28, 28)
                                .addComponent(botaoSair)
                                .addContainerGap(28, Short.MAX_VALUE))
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
    }// </editor-fold>

    private void botaoReservarActionPerformed(java.awt.event.ActionEvent evt) {
        String salaNome = (String) selecSala.getSelectedItem();
        Integer salaId = salaMap.get(salaNome);

        if (salaId == null) {
            JOptionPane.showMessageDialog(this, "Erro: Sala selecionada não encontrada.");
            return;
        }

        String data = textData.getText();
        String horaInicio = textHoraInicio.getText();
        String horaFim = textHoraFim.getText();

        // Validar a data e hora aqui
        if (!validarData(data) || !validarHora(horaInicio) || !validarHora(horaFim)) {
            JOptionPane.showMessageDialog(this, "Erro: Data ou hora inválida.");
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoreserva", "root", "98380439");
            String sql = "INSERT INTO reservas (sala_id, data, hora_inicio, hora_fim) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, salaId);
            pst.setString(2, data);
            pst.setString(3, horaInicio);
            pst.setString(4, horaFim);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar reserva: " + ex.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }

    private boolean validarData(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validarHora(String hora) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setLenient(false);
            sdf.parse(hora);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void textDataFocusLost(java.awt.event.FocusEvent evt) {
        String data = textData.getText();
        if (!validarData(data)) {
            JOptionPane.showMessageDialog(this, "Erro: Data inválida. Formato correto: yyyy-MM-dd.");
        }
    }

    private void textHoraFocusLost(java.awt.event.FocusEvent evt) {
        String hora = evt.getComponent() == textHoraInicio ? textHoraInicio.getText() : textHoraFim.getText();
        if (!validarHora(hora)) {
            JOptionPane.showMessageDialog(this, "Erro: Hora inválida. Formato correto: HH:mm.");
        }
    }

    private void botaoGerenciarActionPerformed(java.awt.event.ActionEvent evt) {
        // Lógica para gerenciar salas
    }

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reserva().setVisible(true);
            }
        });
    }

    // Declaração de variáveis - não modifique
    private javax.swing.JButton botaoGerenciar;
    private javax.swing.JButton botaoReservar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelBemVindo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelHoraFim;
    private javax.swing.JLabel labelHoraInicio;
    private javax.swing.JLabel labelSelecioneSala;
    private javax.swing.JComboBox<String> selecSala;
    private javax.swing.JTextField textData;
    private javax.swing.JTextField textHoraFim;
    private javax.swing.JTextField textHoraInicio;
    // Fim da declaração de variáveis
}
