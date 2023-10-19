package transporte;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Extractor extractor = new Extractor(System.getProperty("user.dir") + "/codigo/transporte/D.txt"); // Lê o arquivo
                                                                                                       // das estações

        Grafo grafo = new Grafo(); // Instancia o grafo vazio

        createGraph(extractor, grafo); // Cria o grafo

        // Imprime a lista de adjacência
        grafo.showAdjacentList();

        // Imprime os vértices (cidades) com seus respectivos Id's e nomes
        ArrayList<Cidade> cidades = new ArrayList<>();
        cidades = grafo.getCidades();
        for (Cidade cidade : cidades) {
            System.out.println(cidade.getId() + " " + cidade.getNome());
        }

        // Chama a função da busca em largura
        grafo.bfs(cidades.get(0));
        
        // Condição que verifica se o grafo é conexo
        if (grafo.isConnected()) {
            System.out.println("\nExiste estrada de qualquer cidade para qualquer cidade!");
        } else {
            ArrayList<Cidade> disconnectedCities = new ArrayList<>();
            disconnectedCities = grafo.disconnectedCitis(); // Pega as cidades que não estão conectadas

            System.out.println("As cidades que não são possíveis de serem alcançadas por transporte terrestre são: ");

            // Imprime as cidades que não estão conectadas
            for (Cidade cidade: disconnectedCities) {
                if (cidade != null) {
                    System.out.println(cidade.getNome());
                }
            }
        }

        // Chama o método do caixeiro viajante
        grafo.solveTSP();

        // Fecha o arquivo
        extractor.closeFile();
    }

    // Método para criar o grafo a partir do arquivo de texto
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