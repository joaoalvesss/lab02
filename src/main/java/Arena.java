import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;


    public Arena(int width1, int height1){
        hero = new Hero(10, 10);
        this.width = width1;
        this.height = height1;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void processKey(KeyStroke key){
        if (key.getKeyType() == KeyType.ArrowUp) moveHero(hero.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        moveMonsters();
    }
    public boolean canHeroMove(Position pos){
        for (Wall wall : walls) {
            if (wall.getPosition().equals(pos)) {
                return false;
            }
        }
        return true;
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    public void draw(TextGraphics graphics) {
        retrieveCoins();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2F8AE3"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.heroGetX(), hero.heroGetY()), "H");
        for (Wall wall : walls)
            wall.draw(graphics);
        for(Coin coin : coins)
            coin.draw(graphics);
        for(Monster monster: monsters)
            monster.draw(graphics);
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            coins.add(new Coin(x,y));
        }
        return coins;
    }
    private void retrieveCoins() {
        for (Coin coin : coins)
            if (hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            monsters.add(new Monster(x, y));
        }
        return monsters;
    }

    public void moveMonsters(){
        for(Monster monster : monsters)
                monster.move();
    }
    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters)
            if(hero.getPosition().equals(monster.getPosition())){
                System.out.println("***********************************");
                System.out.println("You touched a monster, so you lost!");
                System.out.println("***********************************");
                return true;
            }
        return false;
    }
    public boolean verifyCoins(){
        if(coins.size() == 0) {
            System.out.println("**************************************");
            System.out.println("You collect all the coins, so you won!");
            System.out.println("**************************************");
            return true;
        }
        return false;
    }
}
