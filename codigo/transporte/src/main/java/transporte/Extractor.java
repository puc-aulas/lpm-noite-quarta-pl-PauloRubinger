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
        return currentLineIndex < fileLines.length;
    }

    public String readLine() {
        if (hasNextLine()) {
            return fileLines[currentLineIndex++];
        }
        return null;
    }
}
