package view;

import javax.swing.*;
import java.awt.*;

import dao.PetDAO;
import dao.ClienteDAO;
import model.Pet;
import model.Cliente;

public class TelaPets extends JFrame {

    private JTextField txtNome, txtEspecie, txtRaca, txtIdade, txtPeso;
    private JComboBox<String> cbClientes;
    private JTextArea txtLista;

    public TelaPets() {

        setTitle("Cadastro de Pets");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(painel);

        JLabel titulo = new JLabel("Pets", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));

        form.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Espécie:"));
        txtEspecie = new JTextField();
        form.add(txtEspecie);

        form.add(new JLabel("Raça:"));
        txtRaca = new JTextField();
        form.add(txtRaca);

        form.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        form.add(txtIdade);

        form.add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField();
        form.add(txtPeso);

        form.add(new JLabel("Dono:"));
        cbClientes = new JComboBox<>();
        carregarClientes();
        form.add(cbClientes);

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

    private void carregarClientes() {
        cbClientes.removeAllItems();
        for (Cliente c : ClienteDAO.getClientes()) {
            cbClientes.addItem(c.getNome());
        }
    }

    private void cadastrar() {
        try {
            String nome = txtNome.getText();
            String especie = txtEspecie.getText();
            String raca = txtRaca.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));
            String cliente = (String) cbClientes.getSelectedItem();

            if (nome.isEmpty() || especie.isEmpty() || raca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            if (idade <= 0 || peso <= 0) {
                JOptionPane.showMessageDialog(this, "Idade e peso devem ser maiores que zero.");
                return;
            }

            PetDAO.adicionar(new Pet(nome, especie, raca, idade, peso, cliente));

            listar();
            limpar();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Verifique idade e peso.");
        }
    }

    private void excluir() {
        PetDAO.remover(txtNome.getText());
        listar();
        limpar();
    }

    private void listar() {
        txtLista.setText("");
        for (Pet p : PetDAO.getPets()) {
            txtLista.append(
                p.getNome() + " - " + p.getEspecie() + " - " + p.getRaca() +
                " - " + p.getIdade() + " anos - " + p.getPeso() +
                "kg - Dono: " + p.getNomeCliente() + "\n"
            );
        }
    }

    private void limpar() {
        txtNome.setText("");
        txtEspecie.setText("");
        txtRaca.setText("");
        txtIdade.setText("");
        txtPeso.setText("");
    }
}