package graphs;

public class Estrada {
    private int _idOrigem;
    private int _idDestino;
    private int distancia;

    Estrada(int idOrigem, int idDestino, int distancia) {
        this._idOrigem = idOrigem;
        this._idDestino = idDestino;
        this.distancia = distancia;
    }

    public int get_idOrigem() {
        return _idOrigem;
    }

    public int get_idDestino() {
        return _idDestino;
    }

    public int getDistancia() {
        return distancia;
    }
}
