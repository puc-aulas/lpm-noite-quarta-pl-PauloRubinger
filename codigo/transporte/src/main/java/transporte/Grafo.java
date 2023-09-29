package transporte;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Cidade> cidades;
    private ArrayList<Estrada> estradas;

    Grafo() {
        this.cidades = new ArrayList<Cidade>();
        this.estradas = new ArrayList<Estrada>();
    }
}
