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

    public int cityIdByName(String searchName) {
        for (Cidade cidade : this.cidades) {
            if (cidade.getNome().equals(searchName)) {
                return cidade.getId();
            }
        }
        return -1;
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

    public ArrayList<Estrada> getEstradas() {
        return estradas;
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }
}
