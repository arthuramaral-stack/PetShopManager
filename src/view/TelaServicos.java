package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import dao.ClienteDAO;
import dao.PetDAO;
import dao.ServicoDAO;
import model.Cliente;
import model.Pet;
import model.Servico;

public class TelaServicos extends JFrame {

    private JComboBox<String> cbClientes, cbPets, cbTipo;
    private JTextField txtPreco, txtData;
    private JTextArea txtLista;

    public TelaServicos() {
        setTitle("Serviços");
        setSize(650, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(painel);

        JLabel titulo = new JLabel("Serviços", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));

        form.add(new JLabel("Cliente:"));
        cbClientes = new JComboBox<>();
        carregarClientes();
        form.add(cbClientes);

        form.add(new JLabel("Pet:"));
        cbPets = new JComboBox<>();
        carregarPets();
        form.add(cbPets);

        form.add(new JLabel("Serviço:"));
        cbTipo = new JComboBox<>(new String[]{
            "Banho e Tosa",
            "Consulta Veterinária",
            "Hospedagem",
            "Adestramento"
        });
        form.add(cbTipo);

        form.add(new JLabel("Preço (R$):"));
        txtPreco = new JTextField();
        form.add(txtPreco);

        form.add(new JLabel("Data (AAAA-MM-DD):"));
        txtData = new JTextField();
        form.add(txtData);

        JButton btnAgendar = new JButton("Agendar");
        JButton btnCancelar = new JButton("Cancelar");

        form.add(btnAgendar);
        form.add(btnCancelar);

        painel.add(form, BorderLayout.CENTER);

        txtLista = new JTextArea();
        txtLista.setEditable(false);
        painel.add(new JScrollPane(txtLista), BorderLayout.SOUTH);

        cbClientes.addActionListener(e -> carregarPets());
        btnAgendar.addActionListener(e -> agendar());
        btnCancelar.addActionListener(e -> cancelar());

        listar();
    }

    private void carregarClientes() {
        cbClientes.removeAllItems();
        for (Cliente c : ClienteDAO.getClientes()) {
            cbClientes.addItem(c.getNome());
        }
    }

    private void carregarPets() {
        cbPets.removeAllItems();
        String cliente = (String) cbClientes.getSelectedItem();
        if (cliente != null) {
            for (Pet p : PetDAO.getPetsDoCliente(cliente)) {
                cbPets.addItem(p.getNome());
            }
        }
    }

    private void agendar() {
        try {
            String cliente = (String) cbClientes.getSelectedItem();
            String pet = (String) cbPets.getSelectedItem();
            String tipo = (String) cbTipo.getSelectedItem();
            double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));
            LocalDate data = LocalDate.parse(txtData.getText());

            if (data.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "A data deve ser futura.");
                return;
            }

            ServicoDAO.adicionar(new Servico(tipo, preco, cliente, pet, data));

            listar();
            limpar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Verifique os campos! Preço e data precisam ser válidos.");
        }
    }

    private void cancelar() {
        String cliente = (String) cbClientes.getSelectedItem();
        String pet = (String) cbPets.getSelectedItem();
        String tipo = (String) cbTipo.getSelectedItem();

        ServicoDAO.remover(tipo, cliente, pet);
        listar();
    }

    private void listar() {
        txtLista.setText("");
        for (Servico s : ServicoDAO.getServicos()) {
            txtLista.append(
                s.getTipo() + " | " + s.getNomeCliente() + " | " +
                s.getNomePet() + " | R$" + s.getPreco() +
                " | " + s.getData() + "\n"
            );
        }
    }

    private void limpar() {
        txtPreco.setText("");
        txtData.setText("");
    }
}
