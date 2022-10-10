import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    private Position position;

    public Monster(int x1, int y1){
        super(x1,y1);
        position = new Position(x1,y1);
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#171E00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), "&");
    }
    public void move(){
        Random random = new Random();
        int num = random.nextInt(4);
            if ((num == 0) && ((position.get_x() - 1 > 0))) position.set_x(position.get_x() - 1);
            else if ((num == 1) && ((position.get_x() + 1 < 39))) position.set_x(position.get_x() + 1);
            else if ((num == 2) && ((position.get_y() - 1 > 0))) position.set_y(position.get_y() - 1);
            else if ((num == 3) && ((position.get_y() + 1 < 19))) position.set_y(position.get_y() + 1);
    }
}
