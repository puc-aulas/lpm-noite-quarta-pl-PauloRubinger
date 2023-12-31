package transporte;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo {
    private ArrayList<Cidade> cidades;
    private ArrayList<Estrada> estradas;

    Grafo() {
        this.cidades = new ArrayList<Cidade>();
        this.estradas = new ArrayList<Estrada>();
    }

    // Método que adiciona uma cidade na lista de cidades
    public void addCidade(Cidade cidade) {
        this.cidades.add(cidade);
    }

    // Método que adiciona uma estrada na lista de estradas
    public void addEstrada(Estrada estrada) {
        this.estradas.add(estrada);
    }

    // Método que mostra a lista de adjacência
    public void showAdjacentList() {
        System.out.println("Lista de adjacência:\n");
        for (Cidade cidade : this.cidades) {
            System.out.print(cidade.getNome());
            for (Estrada estrada : this.estradas) {
                if (estrada.get_idOrigem() == cidade.getId()) {
                    System.out.print(
                            " -> " + this.cidades.get(estrada.get_idDestino()).getNome()
                                    + " [" + estrada.getDistancia() + "]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showAdjacentMatrix() {
        // Mostra o header da matriz de adjacência e retorna o tamanho máximo de uma
        // célula
        int maxCityNameLength = showAdjacentMatrixHeader();

        // Imprime as linhas da matriz
        for (Cidade cidade : this.cidades) {
            //
            System.out.printf("%-" + maxCityNameLength + "s   |   ", cidade.getNome());
            for (Cidade cidade2 : this.cidades) {
                int distancia = findDistance(cidade, cidade2);
                String distanciaStr = distancia == -1 ? "" : String.valueOf(distancia);
                int cellWidth = maxCityNameLength;

                int metateTamanhoString = distanciaStr.length() / 2;
                int restCellWidth = cellWidth - metateTamanhoString;

                System.out.printf("%" + restCellWidth + "s%s%" + restCellWidth + "s   |   ",
                        "", distanciaStr, "");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Método para auxiliar na impressão da matriz de adjacência
    private int showAdjacentMatrixHeader() {
        int cellWidth = getMaxCityNameLength();

        System.out.println("Matriz de adjacência:\n");

        System.out.print(" ".repeat(cellWidth) + "   |   "); // mostra célula vazia

        for (Cidade cidade : this.cidades) {
            String cityName = cidade.getNome();
            int cityNameLength = cityName.length();
            int padding = (cellWidth - cityNameLength) / 2;
            String leftPadding = " ".repeat(padding);
            String rightPadding = " ".repeat(padding + cityNameLength % 2);
            System.out.printf("%s%s%s   |   ", leftPadding, cityName, rightPadding);
        }
        System.out.println();

        return cellWidth;
    }

    // Pega o tamanho do nome da cidade mais longo
    private int getMaxCityNameLength() {
        int maxLength = 0;
        for (Cidade cidade : this.cidades) {
            int length = cidade.getNome().length();
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }

    // Método que retorna a distância entre 2 cidades
    private int findDistance(Cidade cidade1, Cidade cidade2) {
        for (Estrada estrada : this.estradas) {
            if (estrada.get_idOrigem() == cidade1.getId() && estrada.get_idDestino() == cidade2.getId()) {
                return estrada.getDistancia();
            }
        }
        return -1; // Return -1 no distance
    }

    // Método que retorna o id da cidade por meio do seu nome
    public int cityIdByName(String searchName) {
        for (Cidade cidade : this.cidades) {
            if (cidade.getNome().equals(searchName)) {
                return cidade.getId();
            }
        }
        return -1;
    }

    // Método que retorna um array de cidades vizinhas a determinada cidade
    public ArrayList<Cidade> getCidadeVizinhas(Cidade atual) {
        ArrayList<Cidade> vizinhas = new ArrayList<>();
        for (Estrada estrada : this.estradas) {
            if (estrada.get_idOrigem() == atual.getId()) {
                vizinhas.add(this.cidades.get(estrada.get_idDestino()));
            }
        }
        return vizinhas;
    }

    public ArrayList<Estrada> getEstradas() {
        return estradas;
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }

    // Breadth First Search (Busca em Largura)
    public boolean[] bfs(Cidade startVertex) {

        // Nesse vetor de boolean, todos os elementos são iniciados com o valor "false"
        // por padrão
        // Isso indica que inicialmente nenhum vértice é marcado como visitado pelo
        // algoritmo
        boolean[] visited = new boolean[cidades.size()];
        // Cria estrutura de dados do tipo Fila para auxiliar na busca
        Queue<Cidade> queue = new LinkedList<>();
        queue.add(startVertex);

        System.out.println("\nOrdem da busca em largura:");

        while (!queue.isEmpty()) {
            Cidade current = queue.remove();
            if (visited[current.getId()] == false) {
                visited[current.getId()] = true;
                System.out.println(current.getNome());
                queue.addAll(getCidadeVizinhas(current));
            }
        }
        return visited;
    }

    // Método que verifica se o grafo é conexo
    public boolean isConnected() {
        boolean[] visited = new boolean[cidades.size()];
        // Vetor que indica, para cada vértice, se ele foi ou não visitado durante a
        // busca em largura
        visited = bfs(cidades.get(0));

        // Percorre o vetor de vértices que retornou da busca em largura
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false; // Se o vértice não foi visitado, o grafo não é conexo
            }
        }
        return true;
    }

    // Método para identificar em quais cidades não é possível chegar via transporte
    // terrestre
    public ArrayList<Cidade> disconnectedCitis() {
        // Vetor que indica quais são as cidades que não são possíveis de serem
        // alcançadas via transporte terrestre
        ArrayList<Cidade> disconnectedCities = new ArrayList<>();
        // Vetor que indica, para cada vértice, se ele foi ou não visitado durante a
        // busca em largura
        boolean[] visited = new boolean[cidades.size()];
        visited = bfs(cidades.get(0));

        // Variável auxiliar que será usada para indicar em qual índice do vetor a
        // cidade desconexa será colocada
        // Essa variável auxiliar fará com que o vetor só seja composto de cidades
        // desconexas
        int aux = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                disconnectedCities.add(aux, cidades.get(i));
                aux++;
            }
        }
        return disconnectedCities;
    }

    // Algoritmo que tenta resolver o problema do caixeiro viajante
    public ArrayList<Cidade> solveTSP() {
        ArrayList<Cidade> tour = new ArrayList<>();
        int totalDistance = 0;
    
        if (cidades.isEmpty()) {
            return tour;
        }
    
        int numCities = cidades.size();
        int[][] distanceMatrix = new int[numCities][numCities];
    
        // Preenche a matriz de distâncias com os valores fornecidos
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0; // Distância de uma cidade para ela mesma é 0
                } else {
                    distanceMatrix[i][j] = findDistance(cidades.get(i), cidades.get(j));
                }
            }
        }
    
        // Define a cidade de partida
        Cidade startCity = cidades.get(0);
        // Define a cidade atual
        Cidade currentCity = startCity;
        tour.add(startCity);
    
        // Cria um vetor de boolean para guardar se as cidades foram ou não visitadas
        boolean[] visited = new boolean[numCities];
        visited[startCity.getId()] = true;
    
        while (tour.size() < numCities) {
            Cidade nearestNeighbor = findNearestNeighbor(currentCity, visited, distanceMatrix);
    
            if (nearestNeighbor != null) {
                int distance = distanceMatrix[currentCity.getId()][nearestNeighbor.getId()];
                totalDistance += distance;
                tour.add(nearestNeighbor);
                visited[nearestNeighbor.getId()] = true;
                currentCity = nearestNeighbor;
            }
        }
    
        // Volta para a cidade de origem para completar o ciclo
        tour.add(startCity);
        totalDistance += distanceMatrix[currentCity.getId()][startCity.getId()];
    
        // Imprime a cidade de origem e a distância total
        System.out.println("Cidades Visitadas: ");
        for (Cidade cidade : tour) {
            System.out.println(cidade.getNome());
        }
        System.out.println("Distância Total: " + totalDistance);

        return tour;
    }
    
    // Método que retorna o vizinho mais próximo de determinada cidade
    private Cidade findNearestNeighbor(Cidade city, boolean[] visited, int[][] distanceMatrix) {
        Cidade nearestNeighbor = null;
        int minDistance = Integer.MAX_VALUE;
        int cityId = city.getId();
    
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (distanceMatrix[cityId][i] < minDistance) {
                    minDistance = distanceMatrix[cityId][i];
                    nearestNeighbor = cidades.get(i);
                }
            }
        }
    
        return nearestNeighbor;
    }
}
