import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    private Position position;
    public Coin(int x1, int y1){
        super(x1,y1);
        position = new Position(x1,y1);
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFC633"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), "0");
    }
    public Position getPosition(){
        return position;
    }
}
