package transporte;

public class Cidade {
    private int _id;
    private static int nextId = 0;
    private String nome;

    Cidade(String name) {
        this._id = nextId++;
        this.nome = name;
    }

    public int getId() {
        return _id;
    }

    public String getNome() {
        return nome;
    }
}
