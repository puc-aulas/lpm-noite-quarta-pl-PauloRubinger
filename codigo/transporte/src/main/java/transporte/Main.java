package transporte;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Grafo grafo = new Grafo();
        Cidade cidade1 = new Cidade("Cidade do Cabo");
        Cidade cidade2 = new Cidade("Joanesburgo");
        Cidade cidade3 = new Cidade("Nairobi");
        Cidade cidade4 = new Cidade("Paris");
        Estrada estrada1 = new Estrada(cidade1.getId(), cidade2.getId(), 1270);
        Estrada estrada2 = new Estrada(cidade1.getId(), cidade3.getId(), 3900);
        Estrada estrada3 = new Estrada(cidade1.getId(), cidade4.getId(), 8900);
        Estrada estrada4 = new Estrada(cidade2.getId(), cidade1.getId(), 1270);
        Estrada estrada5 = new Estrada(cidade2.getId(), cidade3.getId(), 4700);
        Estrada estrada6 = new Estrada(cidade3.getId(), cidade1.getId(), 3900);
        Estrada estrada7 = new Estrada(cidade3.getId(), cidade2.getId(), 4700);
        Estrada estrada8 = new Estrada(cidade4.getId(), cidade1.getId(), 8900);
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

        grafo.allCitieAllRoadsRecomendation();
    }
}