package chess.main;

import chess.board.Block;
import chess.piece.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    public static final int BLOCK_SIZE = 80;
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;

    public static Group Blocks = new Group();
    public static Group Pieces = new Group();

    private Parent setup() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * BLOCK_SIZE, HEIGHT * BLOCK_SIZE);
        root.getChildren().add(Blocks);
        root.getChildren().add(Pieces);
        for (int y = 0; y < WIDTH; y++) {
            for (int x = 0; x < HEIGHT; x++) {
                // setting up boards
                Block block = new Block((x + y) % 2 == 0, x, y);
                Blocks.getChildren().add(block);
                // setting up pieces
                if (y == 1 || y == 6) {
                    Piece piece = new Pond(x, y);
                    Pieces.getChildren().add(piece);
                } else if (y == 0 || y == 7) {
                    Piece piece;
                    switch (x) {
                        case 0:
                        case 7:
                            piece = new Castle(x, y);
                            break;
                        case 1:
                        case 6:
                            piece = new Knight(x, y);
                            break;
                        case 2:
                        case 5:
                            piece = new Bishop(x, y);
                            break;
                        case 3:
                            piece = new Queen(x, y);
                            break;
                        case 4:
                            piece = new King(x, y);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + x);
                    }
                    Pieces.getChildren().add(piece);
                }
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(setup());
        primaryStage.setTitle("Chess!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
