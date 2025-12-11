package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    // Lê todas as linhas de um arquivo CSV
    public static List<String> read(String caminhoArquivo) {
        List<String> linhas = new ArrayList<>();

        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            return linhas; // Retorna lista vazia caso o arquivo não exista
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return linhas;
    }

    // Escreve todas as linhas no CSV
    public static void write(String caminhoArquivo, List<String> linhas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
