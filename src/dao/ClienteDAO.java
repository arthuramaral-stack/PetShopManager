package dao;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String ARQUIVO = "clientes.csv";

    
    public static List<Cliente> getClientes() {
        List<String> linhas = CSVUtils.read(ARQUIVO);
        List<Cliente> clientes = new ArrayList<>();

        for (String l : linhas) {
            String[] partes = l.split(";");
            if (partes.length == 3) {
                clientes.add(new Cliente(partes[0], partes[1], partes[2]));
            }
        }
        return clientes;
    }

    
    private static void salvarTudo(List<Cliente> lista) {
        List<String> linhas = new ArrayList<>();

        for (Cliente c : lista) {
            linhas.add(c.toString());
        }

        CSVUtils.write(ARQUIVO, linhas);
    }

    
    public static void adicionar(Cliente c) {
        List<Cliente> lista = getClientes();
        lista.add(c);
        salvarTudo(lista);
    }

    
    public static void remover(String nome) {
        List<Cliente> lista = getClientes();
        lista.removeIf(c -> c.getNome().equalsIgnoreCase(nome));
        salvarTudo(lista);
    }

    
    public static Cliente buscar(String nome) {
        for (Cliente c : getClientes()) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }
}
