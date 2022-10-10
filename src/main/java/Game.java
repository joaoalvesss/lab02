import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
public class Game {
    private Screen screen;
    private Arena arena;
    public Game() {
        try {
            arena = new Arena(40,20);
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        this.screen.clear();
        arena.draw(screen.newTextGraphics());
        this.screen.refresh();
    }
    public void run() throws IOException {
        while(true){
            draw();
            if(arena.verifyCoins()){
                screen.close();
                break;
            }
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }
            KeyStroke key = this.screen.readInput();
            if(key.getKeyType() == KeyType.EOF) break;
            processKey(key);
            }
    }
    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
    }
}
