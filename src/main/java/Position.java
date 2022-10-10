public class Position {
    private int x;
    private int y;

    public Position(int x1, int y1){
        this.x = x1;
        this.y = y1;
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
    public void set_x(int x1){
        this.x = x1;
    }
    public void set_y(int y1){
        this.y = y1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.get_x() && y == p.get_y();
    }
}
