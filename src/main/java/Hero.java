import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{
    private Position position;
    public Hero(int x1, int y1){
        super(x1,y1);
        position = new Position(x1,y1);
    }
    public Position moveUp() {return new Position(position.get_x(), position.get_y() - 1);}
    public Position moveDown() {return new Position(position.get_x(), position.get_y() + 1);}
    public Position moveLeft() {return new Position(position.get_x() - 1, position.get_y());}
    public Position moveRight() {return new Position(position.get_x() + 1, position.get_y());}
    public void setPosition(Position position){
        this.position.set_x(position.get_x());
        this.position.set_y(position.get_y());
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#000037"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), "#");
    }

    public int heroGetX(){
        return position.get_x();
    }
    public int heroGetY(){
        return position.get_y();
    }

    public Position getPosition(){
        return position;
    }
}
