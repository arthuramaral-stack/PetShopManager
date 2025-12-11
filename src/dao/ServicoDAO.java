package dao;

import model.Servico;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    private static final String ARQUIVO = "servicos.csv";

    public static List<Servico> getServicos() {
        List<String> linhas = CSVUtils.read(ARQUIVO);
        List<Servico> servicos = new ArrayList<>();

        for (String l : linhas) {
            String[] p = l.split(";");
            if (p.length == 5) {
                String tipo = p[0];
                double preco = Double.parseDouble(p[1]);
                String cliente = p[2];
                String pet = p[3];
                LocalDate data = LocalDate.parse(p[4]);

                servicos.add(new Servico(tipo, preco, cliente, pet, data));
            }
        }

        return servicos;
    }

    private static void salvarTudo(List<Servico> lista) {
        List<String> linhas = new ArrayList<>();

        for (Servico s : lista) {
            linhas.add(s.toString());
        }

        CSVUtils.write(ARQUIVO, linhas);
    }

    public static void adicionar(Servico s) {
        List<Servico> lista = getServicos();
        lista.add(s);
        salvarTudo(lista);
    }

    public static void remover(String tipo, String cliente, String pet) {
        List<Servico> lista = getServicos();
        lista.removeIf(s ->
            s.getTipo().equalsIgnoreCase(tipo) &&
            s.getNomeCliente().equalsIgnoreCase(cliente) &&
            s.getNomePet().equalsIgnoreCase(pet)
        );
        salvarTudo(lista);
    }

    public static Servico buscar(String tipo, String cliente, String pet) {
        for (Servico s : getServicos()) {
            if (s.getTipo().equalsIgnoreCase(tipo) &&
                s.getNomeCliente().equalsIgnoreCase(cliente) &&
                s.getNomePet().equalsIgnoreCase(pet)) {
                return s;
            }
        }
        return null;
    }
}
