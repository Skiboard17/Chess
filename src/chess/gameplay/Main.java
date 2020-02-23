package chess.gameplay;

import chess.board.Block;
import chess.piece.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static chess.gameplay.MakeMove.script;

import static chess.util.Util.*;

public class Main extends Application {

    private Parent setup() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * BLOCK_SIZE + 1, HEIGHT * BLOCK_SIZE + 1);
        root.getChildren().add(Blocks);
        root.getChildren().add(WhitePieces);
        root.getChildren().add(BlackPieces);
        for (int y = 0; y < WIDTH; y++) {
            for (int x = 0; x < HEIGHT; x++) {
                // setting up boards
                Block block = new Block((x + y) % 2 == 0, x, y);
                Blocks.getChildren().add(block);
                // setting up pieces
                int[] coordinate = convert(x, y, true);
                Piece piece = pieceGenerator(coordinate[0], coordinate[1]);
                block.setPiece(piece);
            }
        }
        return root;
    }

    public Piece pieceGenerator(int x, int y) {
        Piece piece = null;
        if (y == 2 || y == 7) {
            piece = Pond.makePond(x, y);
        } else if (y == 1 || y == 8) {
            switch (x) {
                case 1:
                case 8:
                    piece = Castle.makeCastle(x, y);
                    break;
                case 2:
                case 7:
                    piece = Knight.makeKnight(x, y);
                    break;
                case 3:
                case 6:
                    piece = Bishop.makeBishop(x, y);
                    break;
                case 4:
                    piece = Queen.makeQueen(x, y);
                    break;
                case 5:
                    piece = King.makeKing(x, y);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + x);
            }
        }
        return piece;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(setup());
        primaryStage.setTitle("Chess!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        script();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
