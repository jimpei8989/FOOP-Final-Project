import controller.Game;
import model.World;

class Main {
    public static void main(String[] args) {
        System.out.printf("Hello FiOnaOpPai!\n");

        World world = new World();
        Game game = new Game(world);
    }
}