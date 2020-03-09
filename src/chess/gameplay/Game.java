package chess.gameplay;

import chess.UI.Block;
import chess.UI.Main;
import chess.piece.King;
import chess.piece.Piece;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import static chess.gameplay.Turn.isWhiteTurn;
import static chess.piece.Piece.validMove;
import static chess.util.Util.*;

public class Game {
    public static boolean gameOn;
    public static Block start;
    public static Block end;
    public static Block[] lastMove;

    public static void trackMove(Block selected) {
        if (start == null) {
            start = selected;
            for (Node node : Blocks.getChildren()) {
                Block block = (Block) node;
                if (start.getPiece().canMove(block) && validMove(start, block)) {
                    colorize(block, Color.LIGHTGREEN);
                }
            }
            colorize(start, Color.LIGHTBLUE);
        } else if (end == null) {
            end = selected;
            Piece.movePiece(start, end);
            decolorize();
            checkKingAlive();
        }
    }

    public static void startGame() {
        gameOn = true;
        isWhiteTurn = true;
        Scene scene = new Scene(Main.setup());
        mainScene = scene;
        window.setScene(scene);
    }

    public static void checkKingAlive() {
        King king = (King) (isWhiteTurn ? whiteKing : blackKing);
        if (!king.haveMove()) {
            king.getPosition().setFill(Color.RED);
            gameOn = false;
            // adding a restart button
            Button restart = new Button("Restart");
            mainPage.getChildren().add(restart);
            restart.setPrefHeight(40);
            restart.setPrefWidth(80);
            centering(restart, mainPage);
            restart.setOnMouseClicked(e -> startGame());
        }
    }
}
