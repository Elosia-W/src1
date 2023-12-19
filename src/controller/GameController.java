package controller;

import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessComponent;
import view.ChessGameFrame;
import view.ChessboardComponent;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and
 * onPlayerClickChessPiece()]
 */
public class GameController implements GameListener {

    private Chessboard model;
    private ChessboardComponent view;


    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private ChessboardPoint selectedPoint2;

    private int score;
    private int step;
    private int targetscore;//TODO:create a counter if get the targetscore or max step;
    private int maxstep;
    private int difficulty;

    private JLabel statusLabel;
    public boolean isActive;
    private boolean isChangeable;

    public void setActive(boolean active) {
        isActive = active;
    }
    public void setChangeable(boolean isChangeable){
        this.isChangeable=isChangeable;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public GameController(ChessboardComponent view, Chessboard model,int difficulty) {
        this.difficulty=difficulty;
        this.view = view;
        this.model = model;
        isActive=false;
        isChangeable=true;


        view.registerController(this);
        view.initiateChessComponent(model);
//        statusLabel.setText("");
        view.repaint();
    }

    public int getScore() {
        return score;
    }

    /*public void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                //todo: complete it when restart game
                //todo:check the validity
                SwingUtilities.invokeLater(() -> {
                    ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
                    GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
                    mainFrame.setGameController(gameController);
                    gameController.setStatusLabel(mainFrame.getStatusLabel());
                    mainFrame.setVisible(true);
                });
            }
        }
    }*/

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
    }

    @Override
    public void onPlayerSwapChess() {
        // TODO: Init your swap function here.
//        System.out.println("Implement your swap here.");
        if (selectedPoint!=null&&selectedPoint2!=null) {
            if (isChangeable) {
//            ChessboardPoint temp=new ChessboardPoint(0,0);
//            var point2 = (ChessComponent) view.getGridComponentAt(selectedPoint2).getComponent(0);
                model.swapChessPiece(selectedPoint, selectedPoint2);
//
                var point1 = (ChessComponent) view.getGridComponentAt(selectedPoint).getComponent(0);
                var point2 = (ChessComponent) view.getGridComponentAt(selectedPoint2).getComponent(0);

                view.setChessComponentAtGrid(selectedPoint, point2);
                view.setChessComponentAtGrid(selectedPoint2, point1);
                point1.setSelected(false);
                point2.setSelected(false);
//            selectedPoint=null;
//            selectedPoint2=null;
                view.repaint();
                isActive = true;
                isChangeable=false;
                step++;
            /*model.setChessPiece(temp,model.getChessPieceAt(selectedPoint));
            view.removeChessComponentAtGrid(temp);
            view.setChessComponentAtGrid(temp,new ChessComponent(view.getCHESS_SIZE(),model.getChessPieceAt(selectedPoint)));


            model.setChessPiece(selectedPoint,model.getChessPieceAt(selectedPoint2));
            view.removeChessComponentAtGrid(selectedPoint);
            view.setChessComponentAtGrid(selectedPoint,new ChessComponent(view.getCHESS_SIZE(),model.getChessPieceAt(selectedPoint2)));
//            new ChessComponent(view.getCHESS_SIZE(),model.getChessPieceAt(selectedPoint2);


            model.setChessPiece(selectedPoint2,model.getChessPieceAt(temp));
            view.removeChessComponentAtGrid(selectedPoint2);
            view.setChessComponentAtGrid(selectedPoint2,new ChessComponent(view.getCHESS_SIZE(),model.getChessPieceAt(temp)));
            view.repaint();*/
            } else {
                JOptionPane.showMessageDialog(ChessGameFrame.getFrames()[0], "invalid!");
//            System.out.printf("You need to select two adjacent points");
//            statusLabel.setText("didn't completely select");
            }
        }
        else  {
            JOptionPane.showMessageDialog(ChessGameFrame.getFrames()[0], "You need to select two adjacent point");
//            statusLabel.setText("");
        }

    }


    @Override
    public void onPlayerNextStep() {
        if (isActive) {
            if (selectedPoint != null && selectedPoint2 != null) {
                elimination();
            }
            else if (!isChangeable)falling();
        }
        else {//add new piece
            adding();
        }
    }

    private void adding() {
        updateStatusLabel("adding");
        int count = 0;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint x = new ChessboardPoint(i, j);
                if (model.getChessPieceAt(x) == null) {
                    model.setChessPiece(x, new ChessPiece(Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                    count++;
                }
            }
            view.removeAllChessComponentsAtGrids();
            view.initiateChessComponent(model);
            view.repaint();
        }
        if (count > 0) {
            isActive = true;
            selectedPoint=(new ChessboardPoint(0,0));
            selectedPoint2=(new ChessboardPoint(8,8));
        }
//        else JOptionPane.showMessageDialog(null, "try next swap!");
        else {
            updateStatusLabel("Done!");
            isChangeable = true;
        }

    }
    private void falling() {

        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint x = new ChessboardPoint(i,j);
                if (model.getChessPieceAt(x)==null){
                    for (int k = i; k >0 ; k--) {
                        if(model.getChessPieceAt(new ChessboardPoint(k-1,j))!=null) {
                            ChessboardPoint t = new ChessboardPoint(k,j);
                            model.setChessPiece(t, model.removeChessPiece(new ChessboardPoint(k - 1, j)));
                            view.removeAllChessComponentsAtGrids();
                            view.initiateChessComponent(model);
                            view.repaint();
                        }//if is null,then it is above the top
                    }
                }
            }
        }
        isActive=false;
        isChangeable=false;
        updateStatusLabel("falling");
    }
    public void elimination(){
        // TODO: Init your next step function here.
        ArrayList<ChessboardPoint> x = new ArrayList<ChessboardPoint>();
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
//                if (ChessboardComponent.checkNearBy(model.getGrid(),i,j)>=3){
//                    System.out.printf("qualified %d,%d\n",i,j);
//                }
                if (ChessboardComponent.checkNearBy(model.getGrid(), i, j) >= 3) {
                    System.out.println(ChessboardComponent.checkNearBy(model.getGrid(), i, j));
                    System.out.printf("qualified %d,%d\n", i, j);

                    x.add(new ChessboardPoint(i, j));
                }

            }

        }
        if (!x.isEmpty()) {
//        System.out.println("Implement your next step here.");
            for (ChessboardPoint e : x) {
                model.removeChessPiece(e);
                view.removeChessComponentAtGrid(e);
                view.repaint();
                score++;
                //
            }
            isChangeable=false;
            updateStatusLabel("elimination");
        } else {
            try {
                isChangeable=true;
                onPlayerSwapChess();
                isChangeable=true;
                step-=2;
                updateStatusLabel("Done!");

            }catch (ArrayIndexOutOfBoundsException e){
                isActive=false;
                updateStatusLabel("Done!");

            }
        }
        selectedPoint = null;
        selectedPoint2 = null;
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if (isChangeable) {
            if (selectedPoint2 != null) {
                var distance2point1 = Math.abs(selectedPoint.getCol() - point.getCol()) + Math.abs(selectedPoint.getRow() - point.getRow());
                var distance2point2 = Math.abs(selectedPoint2.getCol() - point.getCol()) + Math.abs(selectedPoint2.getRow() - point.getRow());
                var point1 = (ChessComponent) view.getGridComponentAt(selectedPoint).getComponent(0);
                var point2 = (ChessComponent) view.getGridComponentAt(selectedPoint2).getComponent(0);
                if (distance2point1 == 0 && point1 != null) {

                    point1.setSelected(false);
                    point1.repaint();
                    selectedPoint = selectedPoint2;
                    selectedPoint2 = null;
                } else if (distance2point2 == 0 && point2 != null) {
                    point2.setSelected(false);
                    point2.repaint();
                    selectedPoint2 = null;
                } else if (distance2point1 == 1 && point2 != null) {
                    point2.setSelected(false);
                    point2.repaint();
                    selectedPoint2 = point;
                    component.setSelected(true);
                    component.repaint();
                } else if (distance2point2 == 1 && point1 != null) {
                    point1.setSelected(false);
                    point1.repaint();
                    selectedPoint = selectedPoint2;
                    selectedPoint2 = point;
                    component.setSelected(true);
                    component.repaint();
                }
                return;
            }


            if (selectedPoint == null) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
                return;
            }

            var distance2point1 = Math.abs(selectedPoint.getCol() - point.getCol()) + Math.abs(selectedPoint.getRow() - point.getRow());

            if (distance2point1 == 0) {
                selectedPoint = null;
                component.setSelected(false);
                component.repaint();
                return;
            }

            if (distance2point1 == 1) {
                selectedPoint2 = point;
                component.setSelected(true);
                component.repaint();
            } else {
                selectedPoint2 = null;

                var grid = (ChessComponent) view.getGridComponentAt(selectedPoint).getComponent(0);
                if (grid == null) return;
                grid.setSelected(false);
                grid.repaint();

                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
            updateStatusLabel("selecting");
        }
        else {
            JOptionPane.showMessageDialog(ChessGameFrame.getFrames()[0], "invalid!");
        }
    }

    public void restart() {
        score=0;
        step=0;


        model.removeAllChessPiece();
        model.initPieces();
        view.registerController(this);
        view.removeAllChessComponentsAtGrids();
        view.initiateChessComponent(model);
        view.repaint();
        updateStatusLabel("restarted");
        this.isActive=false;
        this.isChangeable=true;
    }
    public int[][] changeIntoNum(){
        int[][] num= new int[Constant.CHESSBOARD_ROW_SIZE.getNum()+1][Constant.CHESSBOARD_COL_SIZE.getNum()];
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessPiece x = model.getChessPieceAt(new ChessboardPoint(i,j));
                if (x==null)num[i][j]=0;
                else if (x.getColor().equals(Color.blue))num[i][j]=1;
                else if(x.getColor().equals(Color.white))num[i][j]=2;
                else if(x.getColor().equals(Color.green))num[i][j]=3;
                else if (x.getColor().equals(Color.orange))num[i][j]=4;
            }
        }
        num[Constant.CHESSBOARD_ROW_SIZE.getNum()][0]=score;
        return num;
    }
    public void changeIntoModel(int[][] num){
        model.removeAllChessPiece();
        view.removeAllChessComponentsAtGrids();
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
//                ChessPiece x = model.getChessPieceAt(new ChessboardPoint(i,j));
                switch (num[i][j]) {
                    case 1 -> model.setChessPiece(new ChessboardPoint(i, j), new ChessPiece("💎"));
                    case 2 -> model.setChessPiece(new ChessboardPoint(i, j), new ChessPiece("⚪"));
                    case 3 -> model.setChessPiece(new ChessboardPoint(i, j), new ChessPiece("▲"));
                    case 4 -> model.setChessPiece(new ChessboardPoint(i, j), new ChessPiece("🔶"));
                }
            }
        }
        score=num[Constant.CHESSBOARD_ROW_SIZE.getNum()][0];
        updateStatusLabel("Loaded");
        view.initiateChessComponent(model);
        view.repaint();
    }

    public int getSteps() {
        return step;
    }
    public void updateStatusLabel(String status) {
        // 更新文本
        statusLabel.setText("<html>"+status+"<br>Difficulty:"+difficulty+"<br>Scores:" + score+ "<br>Steps:"+step+"</html>");
    }

    public String getDifficulty() {
        return "default";
    }
    /*public void loadGameFromFile(String path) {
        try{
            List<String>
        }
    }*/
}
