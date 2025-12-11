package view;

import javax.swing.*;
import java.awt.*;
import dao.ClienteDAO;
import model.Cliente;

public class TelaClientes extends JFrame {

    private JTextField txtNome, txtTelefone, txtEmail;
    private JTextArea txtLista;

    public TelaClientes() {

        setTitle("Cadastro de Clientes");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(painel);

        JLabel titulo = new JLabel("Clientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        form.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        form.add(txtTelefone);

        form.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        form.add(txtEmail);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnExcluir = new JButton("Excluir");

        form.add(btnCadastrar);
        form.add(btnExcluir);

        painel.add(form, BorderLayout.CENTER);

        txtLista = new JTextArea();
        txtLista.setEditable(false);
        painel.add(new JScrollPane(txtLista), BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> cadastrar());
        btnExcluir.addActionListener(e -> excluir());

        listar();
    }

    private void cadastrar() {

        String nome = txtNome.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            JOptionPane.showMessageDialog(this, "O nome não pode conter números ou símbolos.");
            return;
        }

        if (telefone.length() < 8) {
            JOptionPane.showMessageDialog(this, "Telefone muito curto.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Email inválido.");
            return;
        }

        ClienteDAO.adicionar(new Cliente(nome, telefone, email));
        listar();
        limpar();
    }

    private void excluir() {
        ClienteDAO.remover(txtNome.getText());
        listar();
        limpar();
    }

    private void listar() {
        txtLista.setText("");
        for (Cliente c : ClienteDAO.getClientes()) {
            txtLista.append(c.getNome() + " - " + c.getTelefone() + " - " + c.getEmail() + "\n");
        }
    }

    private void limpar() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
    }
}

