import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {

    protected Position position;

    public Element(int x1, int y1){
        position = new Position(x1, y1);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics graphics);
}
