package transporte;

import org.junit.Test;

public class GrafoTest {
    // check it shows the adjacent list is properly working
    @Test
    public void verificaSeAMatrizDeAdjacenciaEstaCorreta() {
        Grafo grafo = new Grafo();
        Cidade cidade1 = new Cidade("Cidade do Cabo");
        Cidade cidade2 = new Cidade("Joanesburgo");
        Cidade cidade3 = new Cidade("Nairobi");
        Cidade cidade4 = new Cidade("Paris");
        Estrada estrada1 = new Estrada(0, 1, 1270);
        Estrada estrada2 = new Estrada(0, 2, 3900);
        Estrada estrada3 = new Estrada(0, 3, 8900);
        Estrada estrada4 = new Estrada(1, 0, 1270);
        Estrada estrada5 = new Estrada(1, 2, 4700);
        Estrada estrada6 = new Estrada(2, 0, 3900);
        Estrada estrada7 = new Estrada(2, 1, 4700);
        Estrada estrada8 = new Estrada(3, 0, 8900);
        grafo.addCidade(cidade1);
        grafo.addCidade(cidade2);
        grafo.addCidade(cidade3);
        grafo.addCidade(cidade4);
        grafo.addEstrada(estrada1);
        grafo.addEstrada(estrada2);
        grafo.addEstrada(estrada3);
        grafo.addEstrada(estrada4);
        grafo.addEstrada(estrada5);
        grafo.addEstrada(estrada6);
        grafo.addEstrada(estrada7);
        grafo.addEstrada(estrada8);

        grafo.showAdjacentMatrix();
        grafo.showAdjacentList();
    }
}
