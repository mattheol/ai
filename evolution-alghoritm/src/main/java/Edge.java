public class Edge {
    public int id;
    public int x;
    public int y;

    public Edge(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
