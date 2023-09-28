package graphs;

import java.util.ArrayList;

public class Transporte {
    private ArrayList<Cidade> cidades;
    private ArrayList<Estrada> estradas;

    Transporte() {
        this.cidades = new ArrayList<Cidade>();
        this.estradas = new ArrayList<Estrada>();
    }

    public static void main(String[] args) {
        String stationsFilePath = "/home/jose/code/puc/grafo-lpm/codigo/rotas.txt";
        Extractor extractor = new Extractor(stationsFilePath);

        while (extractor.hasNextLine()) {
            Cidade cidade = new Cidade(extractor.readLine()); // cria uma cidade sem estradas
            this.cidades.add(cidade);
        }
    }
}
