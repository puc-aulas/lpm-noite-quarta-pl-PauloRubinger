package graphs;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String stationsFilePath = "/home/jose/code/puc/grafo-lpm/codigo/rotas.txt";
        Extractor extractor = new Extractor(stationsFilePath);

        while (extractor.hasNextLine()) {
            System.out.println(extractor.readLine());
        }

        Grafo graph = new Grafo();

        Scanner input = new Scanner(System.in);

        // int[] highestAndLowestDegree;

        // boolean isSimple;
        // boolean isNull;
        // boolean isRegular;
        // boolean isComplete;

        try {
            while (graph.scanGraph(input) == true) {
                graph.print();
                // highestAndLowestDegree = graph.getHighestAndLowestDegree();
                // System.out.println(highestAndLowestDegree[0] + " " +
                // highestAndLowestDegree[1]);
                // isSimple = graph.isSimple();
                // if (isSimple == true) {
                // System.out.println("SIM");
                // } else {
                // System.out.println("NAO");
                // }
                // isNull = graph.isNull();
                // if (isNull == true) {
                // System.out.println("SIM");
                // } else {
                // System.out.println("NAO");
                // }

                // graph.getDegrees();
                // isRegular = graph.isRegular();
                // if (isRegular) {
                // System.out.println("SIM");
                // } else {
                // System.out.println("NAO");
                // }

                // isComplete = graph.isComplete();
                // System.out.println(isComplete ? "SIM" : "NAO");

                graph = new Grafo();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            input.close();
        }
    }
}
