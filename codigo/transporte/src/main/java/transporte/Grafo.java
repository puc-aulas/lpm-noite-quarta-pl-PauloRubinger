package transporte;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Cidade> cidades;
    private ArrayList<Estrada> estradas;

    Grafo() {
        this.cidades = new ArrayList<Cidade>();
        this.estradas = new ArrayList<Estrada>();
    }

    public void addCidade(Cidade cidade) {
        this.cidades.add(cidade);
    }

    public void addEstrada(Estrada estrada) {
        this.estradas.add(estrada);
    }

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
        // Print the matrix header
        int maxCityNameLength = showAdjacentMatrixHeader();

        // Print the matrix rows
        for (Cidade cidade : this.cidades) {
            System.out.printf("%-" + maxCityNameLength + "s   |   ", cidade.getNome());
            for (Cidade cidade2 : this.cidades) {
                int distancia = findDistance(cidade, cidade2);
                String distanciaStr = distancia == -1 ? "" : String.valueOf(distancia);
                int cellWidth = maxCityNameLength;
                System.out.printf(
                        "%" + (cellWidth - distanciaStr.length()) / 2 + "s%s%" + (cellWidth - distanciaStr.length()) / 2
                                + "s   |   ",
                        "", distanciaStr, "");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Print adjacency matrix header
    private int showAdjacentMatrixHeader() {
        int cellWidth = getMaxCityNameLength();

        System.out.println("Matriz de adjacência:\n");

        System.out.print(" ".repeat(cellWidth) + "   |   ");
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

    // Helper method to find the maximum length of city names
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

    // Helper method to find the distance between two cities
    private int findDistance(Cidade cidade1, Cidade cidade2) {
        for (Estrada estrada : this.estradas) {
            if (estrada.get_idOrigem() == cidade1.getId() && estrada.get_idDestino() == cidade2.getId()) {
                return estrada.getDistancia();
            }
        }
        return -1; // Return -1 if no distance is found
    }

    // Breadth First Search algorithm
    public void bfs() {
        ArrayList<Cidade> visited = new ArrayList<Cidade>();
        ArrayList<Cidade> queue = new ArrayList<Cidade>();
        Cidade actual = this.cidades.get(0);
        visited.add(actual);
        System.out.println(actual.getNome());
        queue.add(actual);

        while (queue.size() > 0) {
            Cidade explored = queue.get(0);

            for (int i = 0; i < estradas.size(); i++) {
                if (explored.getId() == estradas.get(i).get_idOrigem()) {
                    
                }
            }
        }
    }

}
