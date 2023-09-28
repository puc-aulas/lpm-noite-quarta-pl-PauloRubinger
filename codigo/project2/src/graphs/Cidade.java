package graphs;

import java.util.ArrayList;

public class Cidade {
    private int _id;
    private static int nextId = 0;
    private String nome;
    private ArrayList<Estrada> estradas;

    Cidade(String extracted) {
        this._id = nextId++;
        this.nome = this.extractName(extracted);
    }

    // Expected: Cidade do Cabo: Joanesburgo (1270), Nairobi (3900), Paris (8900)
    private String extractName(String extracted) {
        try {
            String[] parts = extracted.split(":");
            return parts[0].trim();
        } catch (Exception e) {
            System.out.println("Erro ao extrair nome da cidade: " + e);
            return "";
        }
    }

    // Expected: Cidade do Cabo: Joanesburgo (1270), Nairobi (3900), Paris (8900)
    private ArrayList<Estrada> extractEstradas(String extracted) {
        try {
            String[] withoutFrom = extracted.split(":");
            String[] destines = withoutFrom[1].split(",");
            ArrayList<Estrada> estradasList = new ArrayList<>();

            for (String destine : destines) {
                // get destine name
                String destineName = destine.split("\\(")[0].trim();

                // get distance
                String distanceString = destine.split("\\(")[1]
                        .split("\\)")[0];
                int distanceValue = Integer.parseInt(distanceString);

                estradasList.add(new Estrada(this._id, 0, distanceValue));
            }

            return estradasList;
        } catch (Exception e) {
            System.out.println("Erro ao extrair estradas da cidade: " + e);
            return new ArrayList<>();
        }
    }

    public int getId() {
        return _id;
    }

    public String getNome() {
        return nome;
    }
}
