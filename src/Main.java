import view.View;
import model.World;

class Main {
    public static void main(String[] args) {
        System.out.printf("Hello FiOnaOpPai!\n");
        View view = new View();
        Game game = new Game(view);
        game.start();
    }
}