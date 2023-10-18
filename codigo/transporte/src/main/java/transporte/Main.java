package transporte;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Extractor extractor = new Extractor(System.getProperty("user.dir") + "/codigo/transporte/rotas.txt"); // Lê o arquivo
                                                                                                       // das estações

        Grafo grafo = new Grafo(); // Instancia o grafo vazio

        createGraph(extractor, grafo); // Cria o grafo

        grafo.showAdjacentList();

        // Imprime os vértices (cidades) com seus respectivos Id's e nomes
        ArrayList<Cidade> cidades = new ArrayList<>();
        cidades = grafo.getCidades();
        for (Cidade cidade : cidades) {
            System.out.println(cidade.getId() + " " + cidade.getNome());
        }

        grafo.bfs(cidades.get(0));
        
        if (grafo.isConnected()) {
            System.out.println("\nExiste estrada de qualquer cidade para qualquer cidade!");
        } else {
            ArrayList<Cidade> disconnectedCities = new ArrayList<>();
            disconnectedCities = grafo.disconnectedCitis();

            System.out.println("As cidades que não são possíveis de serem alcançadas por transporte terrestre são: ");

            for (Cidade cidade: disconnectedCities) {
                if (cidade != null) {
                    System.out.println(cidade.getNome());
                }
            }
        }

        grafo.solveTSP();

        extractor.closeFile();
    }

    static void createGraph(Extractor e, Grafo g) {
        ArrayList<String> cidadesOrigem = e.extractOriginCityNames(); // Extrai as cidades de origem

        // Cria todas as cidades e adiciona no grafo
        for (String nomeCidade : cidadesOrigem) {
            Cidade cidadeOrigem = new Cidade(nomeCidade);
            g.addCidade(cidadeOrigem);
        }

        int idOrigem = 0;

        // Cria todas estradas e adiciona no grafo
        while (e.hasNextLine()) {
            ArrayList<String> cidadesDestino = e.extractDestinationCityNames();

            for (String nomeDestino : cidadesDestino) {
                int distancia = e.extractDistance(nomeDestino);
                int idDestino = g.cityIdByName(nomeDestino);
                Estrada estrada = new Estrada(idOrigem, idDestino, distancia);

                g.addEstrada(estrada);
            }

            idOrigem++; // Próxima cidade de origem

            e.readLine(); // apenas para ir para próxima linha
        }
    }
}