package graphs;

import java.util.Scanner;

public class Grafo {
    public static final int NO_EDGE = -1;
    public static final int MAX_VERTICES = 500;

    private int numberOfVertices;
    private int numberOfEdges;
    private int[][] adjacencyMatrix;

    public Grafo() {
        this.numberOfVertices = 0;
        adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        deleteAllEdges();
    }

    public void setNumberOfVertices(int numberOfVertices) throws Exception {
        if (numberOfVertices > MAX_VERTICES) {
            throw new Exception("Erro: Número de vértices maior do que o permitido!");
        }
        this.numberOfVertices = numberOfVertices;
    }

    // Insert a new edge to the graph
    public void addEdge(int v1, int v2, int weigth) throws Exception {
        if (v1 > MAX_VERTICES || v2 > MAX_VERTICES) {
            throw new Exception("Erro: vértice " + v1 + " ou " + v2 + " não existe no gráfico");
        }
        if (adjacencyMatrix[v1][v2] == NO_EDGE) {
            adjacencyMatrix[v1][v2] = weigth;
            numberOfEdges++;
        }
    }

    public boolean scanGraph(Scanner input) throws Exception {
        boolean response;
        int temp;

        deleteAllEdges();

        temp = input.nextInt();
        setNumberOfVertices(temp);

        response = (numberOfVertices > 0) ? true : false;

        // Assigns NO_EDGE (-1) to the main diagonal and the edge weight to the other
        // elements of the adjacencyMatrix
        // In this case, the graph is undirected, so the matrix is symmetric
        for (int i = 0; i < numberOfVertices; i++) {
            addEdge(i, i, NO_EDGE);
            for (int j = i + 1; j < numberOfVertices; j++) {
                temp = input.nextInt();
                addEdge(i, j, temp);
                addEdge(j, i, temp);
            }
        }

        return response;
    }

    // Verify if there is an edge between two vertices
    public boolean isEdge(int v1, int v2) {
        return (adjacencyMatrix[v1][v2] != NO_EDGE);
    }

    // Returns the weigth of the edge
    public int getWeigthOfTheEdge(int v1, int v2) {
        return adjacencyMatrix[v1][v2];
    }

    // Delete a single edge by setting NO_EDGE to the edge
    public void deleteEdge(int v1, int v2) throws Exception {
        if (v1 > MAX_VERTICES || v2 > MAX_VERTICES) {
            throw new Exception("Erro: vértice " + v1 + " ou " + v2 + " não existe no gráfico");
        }
        if (adjacencyMatrix[v1][v2] != NO_EDGE) {
            adjacencyMatrix[v1][v2] = NO_EDGE;
            numberOfEdges--;
        }
    }

    // Set NO_EDGE to all edges
    public void deleteAllEdges() {
        for (int i = 0; i < MAX_VERTICES; i++) {
            adjacencyMatrix[i][i] = NO_EDGE;
            for (int j = i + 1; j < MAX_VERTICES; j++) {
                adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = NO_EDGE;
            }
        }
        numberOfEdges = 0;
    }

    public void print() {
        System.out.println("\nN = (" + numberOfVertices + ")");
        System.out.print("\t");
        for (int i = 0; i < numberOfVertices; i++) {
            if (i >= 100) {
                System.out.print("\t(" + i + ") ");
            } else if (i >= 10) {
                System.out.print("\t(0" + i + ") ");
            } else {
                System.out.print("\t(00" + i + ") ");
            }
        }

        for (int i = 0; i < numberOfVertices; i++) {
            if (i >= 100) {
                System.out.println("\n(" + i + ") - ");
            } else if (i >= 10) {
                System.out.println("\n(0" + i + ") - ");
            } else {
                System.out.println("\n(00" + i + ") - ");
            }

            for (int j = 0; j < numberOfVertices; j++) {
                if (adjacencyMatrix[i][j] == NO_EDGE) {
                    System.out.print("\t\t. ");
                } else {
                    System.out.print("\t\t" + adjacencyMatrix[i][j] + " ");
                }
            }
        }

        System.out.println();
    }

    public void printVerticesEdges() {
        System.out.println(numberOfVertices + numberOfEdges);
    }

    public int[] getHighestAndLowestDegree() {
        int degree = 0;
        int highestDegree = 0;
        int lowestDegree = numberOfVertices;
        int[] response = new int[2];

        int[][] matrixAux = new int[numberOfVertices][numberOfVertices];

        // Set the NO_EDGE values to 0 and the edge weights that are >= 1 to 1 to help
        // calculate the sum of the row/column
        for (int i = 0; i < numberOfVertices; i++) {
            matrixAux[i][i] = 0;
            for (int j = i + 1; j < numberOfVertices; j++) {
                if (adjacencyMatrix[i][j] == NO_EDGE) {
                    matrixAux[i][j] = matrixAux[j][i] = 0;
                }
                if (adjacencyMatrix[i][j] >= 1) {
                    matrixAux[i][j] = matrixAux[j][i] = 1;
                }
            }
        }

        System.out.println("\nN = (" + numberOfVertices + ")");
        System.out.print("\t");
        for (int i = 0; i < numberOfVertices; i++) {
            if (i >= 100) {
                System.out.print("\t(" + i + ") ");
            } else if (i >= 10) {
                System.out.print("\t(0" + i + ") ");
            } else {
                System.out.print("\t(00" + i + ") ");
            }
        }

        for (int i = 0; i < numberOfVertices; i++) {
            if (i >= 100) {
                System.out.println("\n(" + i + ") - ");
            } else if (i >= 10) {
                System.out.println("\n(0" + i + ") - ");
            } else {
                System.out.println("\n(00" + i + ") - ");
            }

            for (int j = 0; j < numberOfVertices; j++) {
                if (matrixAux[i][j] == NO_EDGE) {
                    System.out.print("\t\t. ");
                } else {
                    System.out.print("\t\t" + matrixAux[i][j] + " ");
                }
            }
        }

        System.out.println();

        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                degree += matrixAux[i][j];
            }
            if (degree > highestDegree) {
                highestDegree = degree;
            }
            if (degree < lowestDegree) {
                lowestDegree = degree;
            }
            degree = 0;
        }

        response[0] = highestDegree;
        response[1] = lowestDegree;

        return response;
    }

    public boolean isSimple() {

        boolean response = true;

        for (int i = 0; i < numberOfVertices; i++) {
            if (adjacencyMatrix[i][i] != NO_EDGE) {
                response = false;
                break;
            }
            for (int j = i + 1; j < numberOfVertices; j++) {
                if (adjacencyMatrix[i][j] != adjacencyMatrix[j][i]) {
                    response = false;
                    break;
                }
            }
        }
        return response;
    }

    public boolean isNull() {

        boolean response = true;

        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                if (adjacencyMatrix[i][j] != 0 && adjacencyMatrix[i][j] != NO_EDGE) {
                    response = false;
                    break;
                }
            }
        }
        return response;
    }

    public int[] getDegrees() {

        int degree = 0;
        int[] arrOfDegrees = new int[numberOfVertices];
        int[][] matrixAux = new int[numberOfVertices][numberOfVertices];

        // Set the NO_EDGE values to 0 and the edge weights that are >= 1 to 1 to help
        // calculate the sum of the row/column
        for (int i = 0; i < numberOfVertices; i++) {
            matrixAux[i][i] = 0;
            for (int j = i + 1; j < numberOfVertices; j++) {
                if (adjacencyMatrix[i][j] == NO_EDGE) {
                    matrixAux[i][j] = matrixAux[j][i] = 0;
                }
                if (adjacencyMatrix[i][j] >= 1) {
                    matrixAux[i][j] = matrixAux[j][i] = 1;
                }
            }
        }

        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                degree += matrixAux[i][j];
            }
            arrOfDegrees[i] = degree;
            degree = 0;
        }

        // for (int i = 0; i < arrOfDegrees.length; i++) {
        // System.out.println(arrOfDegrees[i]);
        // }

        return arrOfDegrees;
    }

    public boolean isRegular() {

        // Null graph is a regular graph of degree zero
        if (isNull()) {
            return true;
        }

        int[] arrOfDegrees = new int[numberOfVertices];
        arrOfDegrees = getDegrees();
        int expectedDegree = arrOfDegrees[0];

        for (int degree : arrOfDegrees) {
            if (degree != expectedDegree) {
                return false;
            }
        }

        return true;
    }

    public boolean isComplete() {
        if (isSimple() && isRegular()) {
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
                    if (i != j && adjacencyMatrix[i][j] != 0 && adjacencyMatrix[i][j] != NO_EDGE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
