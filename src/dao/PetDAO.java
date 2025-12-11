package dao;

import model.Pet;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    private static final String ARQUIVO = "pets.csv";

    public static List<Pet> getPets() {
        List<String> linhas = CSVUtils.read(ARQUIVO);
        List<Pet> pets = new ArrayList<>();

        for (String l : linhas) {
            String[] p = l.split(";");
            if (p.length == 6) {
                String nome = p[0];
                String especie = p[1];
                String raca = p[2];
                int idade = Integer.parseInt(p[3]);
                double peso = Double.parseDouble(p[4]);
                String nomeCliente = p[5];

                pets.add(new Pet(nome, especie, raca, idade, peso, nomeCliente));
            }
        }
        return pets;
    }

    private static void salvarTudo(List<Pet> lista) {
        List<String> linhas = new ArrayList<>();

        for (Pet p : lista) {
            linhas.add(p.toString());
        }

        CSVUtils.write(ARQUIVO, linhas);
    }

    public static void adicionar(Pet p) {
        List<Pet> lista = getPets();
        lista.add(p);
        salvarTudo(lista);
    }

    public static void remover(String nome) {
        List<Pet> lista = getPets();
        lista.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
        salvarTudo(lista);
    }

    public static Pet buscar(String nome) {
        for (Pet p : getPets()) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public static List<Pet> getPetsDoCliente(String nomeCliente) {
        List<Pet> resultado = new ArrayList<>();
        
        for (Pet p : getPets()) {
            if (p.getNomeCliente().equalsIgnoreCase(nomeCliente)) {
                resultado.add(p);
            }
        }
        
        return resultado;
    }
}
