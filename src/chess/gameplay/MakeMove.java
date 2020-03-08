package chess.gameplay;

import chess.UI.Block;

public class MakeMove {

    public static void script() {
        makeMove("d2", "d4");
        makeMove("e7", "e5");
        makeMove("d4", "e5");
        makeMove("a7", "a5");
        makeMove("e5", "e6");
        makeMove("a5", "a4");
        makeMove("e6", "e7");
        makeMove("a4", "a3");
    }

    public static void makeMove(String from, String to) {
        try {
            Block start = blockFinder(from);
            Block end = blockFinder(to);
            if (start.click()) {
                end.click();
            }
        } catch (BlockNotFoundException e) {
            e.printStackTrace();
        }
        // pass start and end to Game.trackMove();
    }

    private static Block blockFinder(String move) throws BlockNotFoundException {
        if (move == null || move.length() != 2) {
            throw new BlockNotFoundException(move);
        }
        move = move.toLowerCase();
        char firstLetter = move.charAt(0);
        char secondLetter = move.charAt(1);
        int x = firstLetter - 'a' + 1;
        int y = -1;
        if (Character.isDigit(secondLetter)) {
            y = Character.getNumericValue(secondLetter);
        }
        Block block = null;
        if (x > 0 && x < 9 && y > 0 && y < 9) {
            block = Block.findBlock(x, y);
        }
        if (block == null) {
            throw new BlockNotFoundException(move);
        }
        return block;
    }

    private static class BlockNotFoundException extends Exception {
        private String move;

        public BlockNotFoundException(String move) {
            this.move = move;
        }

        @Override
        public String toString() {
            return "Block " + move + " is not found";
        }
    }

}
