package view;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Sistema Pet Shop");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fundo
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("PetShop Manager", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        // Painel dos botões
        JPanel painel = new JPanel(new GridLayout(3, 1, 15, 15));
        painel.setBackground(new Color(245, 245, 245));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

        JButton btnClientes = criarBotao("Clientes");
        JButton btnPets = criarBotao("Pets");
        JButton btnServicos = criarBotao("Serviços");

        // Ações
        btnClientes.addActionListener(e -> new TelaClientes().setVisible(true));
        btnPets.addActionListener(e -> new TelaPets().setVisible(true));
        btnServicos.addActionListener(e -> new TelaServicos().setVisible(true));

        painel.add(btnClientes);
        painel.add(btnPets);
        painel.add(btnServicos);

        add(painel, BorderLayout.CENTER);
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(new Color(52, 152, 219)); // azul bonito
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}
