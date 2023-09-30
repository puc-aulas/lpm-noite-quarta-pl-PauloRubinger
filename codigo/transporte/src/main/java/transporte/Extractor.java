package transporte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Extractor {
    private String filePath = "";
    private BufferedReader fileReader = null;
    private String[] fileLines = null;
    private int currentLineIndex = 0;

    Extractor(String filePath) {
        this.filePath = filePath;
        System.out.println("Lendo arquivo [" + this.filePath + "]");

        try {
            this.fileReader = new BufferedReader(new FileReader(filePath));
            StringBuilder content = new StringBuilder();
            String line;
            List<String> lines = new ArrayList<>();

            while ((line = this.fileReader.readLine()) != null) {
                content.append(line).append("\n");
                lines.add(line);
            }

            this.fileLines = lines.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo [" + this.filePath + "]:\n" + e);
        }
    }

    public Boolean hasNextLine() {
        return this.currentLineIndex < this.fileLines.length;
    }

    public String readLine() {
        if (hasNextLine()) {
            return this.fileLines[this.currentLineIndex++];
        }
        return null;
    }

    public boolean goToFirstLine() {
        if (this.fileLines != null && this.fileLines.length > 0) {
            this.currentLineIndex = 0;
            return true;
        }

        return false;
    }

    public ArrayList<String> extractOriginCityNames() {
        if (this.fileLines == null || this.fileLines.length == 0) {
            return null;
        }

        ArrayList<String> originCityNames = new ArrayList<>();

        for (String line : this.fileLines) {
            String[] onlyOriginCity = line.split(":");
            String originCityName = onlyOriginCity[0].trim();
            originCityNames.add(originCityName);
        }

        return originCityNames;
    }

    public ArrayList<String> extractDestinationCityNames() {
        if (this.fileLines == null || this.fileLines.length == 0 || this.currentLineIndex == this.fileLines.length) {
            return null;
        }

        ArrayList<String> destinationCityNames = new ArrayList<>();

        // Separa a origem do resto.
        // Ex: [Cidade do Cabo], [Joanesburgo (1270), Nairobi (3900), Paris (8900)]
        this.fileLines[this.currentLineIndex].split(":");

        // Separa as cidades de destino.
        // Ex: [Joanesburgo (1270)], [Nairobi (3900)], [Paris (8900)
        String[] onlyDestinations = this.fileLines[this.currentLineIndex].split(":")[1].split(",");

        // Separa apenas os nomes das cidades. Ex: [Joanesburgo], [Nairobi], [Paris]
        for (String destination : onlyDestinations) {
            String destinationCityName = destination.split("\\(")[0].trim();
            destinationCityNames.add(destinationCityName);
        }

        return destinationCityNames;
    }

    public int extractDistance(String destinationCityName) {
        if (this.fileLines == null || this.fileLines.length == 0 || this.currentLineIndex == this.fileLines.length) {
            return -1;
        }

        // Separa as cidades de destino.
        // Ex: [Joanesburgo (1270)], [Nairobi (3900)], [Paris (8900)
        String[] onlyDestinations = this.fileLines[this.currentLineIndex].split(":")[1].split(",");

        // Separa apenas os nomes das cidades. Ex: [Joanesburgo], [Nairobi], [Paris]
        for (String destination : onlyDestinations) {
            String destinationCityNameFromLine = destination.split("\\(")[0].trim();

            if (destinationCityNameFromLine.equals(destinationCityName)) {
                String distanceString = destination
                        .split("\\(")[1]
                        .split("\\)")[0];

                int distanceValue = Integer.parseInt(distanceString);

                return distanceValue;
            }
        }

        return -1;
    }

    public int getLinesSize() {
        return fileLines.length;
    }
}
