package transporte;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExtractorTest {
    static String projectRoot = null;

    @BeforeClass
    public static void setup() {
        projectRoot = System.getProperty("user.dir");
        System.out.println("Project root: " + projectRoot);
    }

    @Test
    public void verificaSeALeituraDoArquivoEstaCorreta() {
        String filePath = projectRoot + "/rotas.txt";
        Extractor extractor = new Extractor(filePath);

        assertEquals("Verifica a quantidade de linhas", 11, extractor.getLinesSize());
        assertEquals("Cidade do Cabo: Joanesburgo (1270), Nairobi (3900), Paris (8900)", extractor.readLine());
        assertEquals("Joanesburgo: Cidade do Cabo (1270), Nairobi (4700), Mumbai (6500)", extractor.readLine());
        assertEquals("Nairobi: Cidade do Cabo (3900), Joanesburgo (4700), Mumbai (4300)", extractor.readLine());
        assertEquals("Paris: Londres (340), Berlim (1050), Cidade do Cabo (8900)", extractor.readLine());
    }

    @Test
    public void verificaSeAExtracaoDasListasDeNomesDasLinhasEstaCorreta() {
        String filePath = projectRoot + "/rotas.txt";
        Extractor extractor = new Extractor(filePath);

        ArrayList<String> originCityNames = extractor.extractOriginCityNames();
        ArrayList<String> firstLineDestinations = extractor.extractDestinationCityNames();
        assertEquals("Verifica a quantidade de cidades de origem", 11, originCityNames.size());
        assertEquals("Verifica o nome da primeira cidade de origem", "Cidade do Cabo", originCityNames.get(0));
        assertEquals("Verifica a quantidade de cidades de destino", 3, extractor.extractDestinationCityNames().size());
        assertEquals("Verifica o nome da primeira cidade de destino", "Joanesburgo", firstLineDestinations.get(0));
        assertEquals("Verifica o nome da segunda cidade de destino", "Nairobi", firstLineDestinations.get(1));
        assertEquals("Verifica o nome da terceira cidade de destino", "Paris", firstLineDestinations.get(2));
    }

    // verificar extração da distancia de uma cidade da linha
    @Test
    public void verificaSeAExtracaoDaDistanciaDeUmaCidadeEstaCorreta() {
        String filePath = projectRoot + "/rotas.txt";
        Extractor extractor = new Extractor(filePath);

        assertEquals("Verifica a distância da Cidade do Cabo até Joanesburgo", 1270, extractor.extractDistance("Joanesburgo"));
        assertEquals("Verifica a distância da Cidade do Cabo até Nairobi", 3900, extractor.extractDistance("Nairobi"));
        assertEquals("Verifica a distância da Cidade do Cabo até Paris", 8900, extractor.extractDistance("Paris"));
    }
}
