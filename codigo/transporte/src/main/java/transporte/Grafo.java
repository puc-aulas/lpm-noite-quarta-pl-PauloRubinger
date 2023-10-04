package transporte;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        // Mostra o header da matriz de adjacência e retorna o tamanho máximo de uma
        // célula
        int maxCityNameLength = showAdjacentMatrixHeader();

        // Print the matrix rows
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

    private int findDistance(Cidade cidade1, Cidade cidade2) {
        for (Estrada estrada : this.estradas) {
            if (estrada.get_idOrigem() == cidade1.getId() && estrada.get_idDestino() == cidade2.getId()) {
                return estrada.getDistancia();
            }
        }
        return -1; // Return -1 no distance
    }

    private Cidade getIndexByCityName(String searchName) {
        for (Cidade cidade : this.cidades) {
            if (cidade.getNome().equals(searchName)) {
                return cidade;
            }
        }
        return null;
    }

    public ArrayList<Cidade> getCidadeVizinhas(Cidade atual) {
        ArrayList<Cidade> vizinhas = new ArrayList<>();
        for (Estrada estrada : this.estradas) {
            if (estrada.get_idOrigem() == atual.getId()) {
                vizinhas.add(this.cidades.get(estrada.get_idDestino()));
            }
        }
        return vizinhas;
    }

    public void allCitieAllRoadsRecomendation() {
        List<Cidade> vertices = this.cidades;
        List<Integer> distances = new ArrayList<>();
        List<Visited> verticeState = new ArrayList<>();
        List<Cidade> ancestors = new ArrayList<>();

        Cidade start = this.cidades.get(0); // Inicia pela Cidade do Cabo
        int startIndex = 0;

        // Inicializa os arrays
        for (int i = 0; i < vertices.size(); i++) {
            distances.add(i, Integer.MAX_VALUE);
            verticeState.add(i, Visited.NOT_VISITED);
            ancestors.add(i, null);
        }

        distances.set(startIndex, 0);
        verticeState.set(startIndex, Visited.VISITING);

        Queue<Cidade> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Cidade u = queue.poll();
            int uIndex = vertices.indexOf(u);

            List<Cidade> adj = this.getAdjacent(u);

            for (Cidade v : adj) {
                int vIndex = vertices.indexOf(v);

                if (verticeState.get(vIndex) == Visited.NOT_VISITED) {
                    verticeState.set(vIndex, Visited.VISITING);
                    distances.set(vIndex, distances.get(uIndex) + 1);
                    ancestors.set(vIndex, u);
                    queue.add(v);
                }
            }

            verticeState.set(uIndex, Visited.VISITED);
        }

        System.out.println(verticeState);

        for (int i = 0; i < distances.size(); i++) {
            try {
                System.out.println(this.cidades.get(i).getNome() + ": " + distances.get(i));
            } catch (Exception e) {
                System.out
                        .println("Cidade: " + this.cidades.get(i).getNome() + " não tem caminho para a cidade do Cabo");
            }
        }

        for (int i = 0; i < ancestors.size(); i++) {
            try {
                System.out.println(this.cidades.get(i).getNome() + ": " + ancestors.get(i).getNome());
            } catch (Exception e) {
                System.out
                        .println("Cidade: " + this.cidades.get(i).getNome() + " não tem caminho para a cidade do Cabo");
            }
        }
    }

    private List<Cidade> getAdjacent(Cidade u) {
        List<Cidade> adjacent = new ArrayList<>();
        for (Estrada estrada : this.estradas) {
            if (estrada.get_idOrigem() == u.getId()) {
                adjacent.add(this.cidades.get(estrada.get_idDestino()));
            }
        }
        return adjacent;
    }
}
