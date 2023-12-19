import controller.GameController;
import model.Chessboard;
import view.ChessGameFrame;
import view.ModeSelectingFrame;

import javax.swing.*;

public class Main {
        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
//                        ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
//                        GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
//                        mainFrame.setGameController(gameController);
//                        gameController.setStatusLabel(mainFrame.getStatusLabel());
                        //the game board
                        ModeSelectingFrame selectingFrame = new ModeSelectingFrame(600, 500);
                        selectingFrame.setVisible(true);
//                        selectingFrame.setTraditionalModeCallback(difficulty -> {
//                                System.out.println("Received difficulty from Traditional Mode: " + difficulty);
//                        });
//                        ChessGameFrame gameFrame = new ChessGameFrame(801,600);
//                        gameFrame.setVisible(true);

                });
        }
//        public static void main(String[] args) {
//                SwingUtilities.invokeLater(() -> {
//                        ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
//                        GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
//                        mainFrame.setGameController(gameController);
//                        gameController.setStatusLabel(mainFrame.getStatusLabel());
//                        mainFrame.setVisible(true);
//                });
//        }
}


