package view;


import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    private final CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    private final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell = new HashSet<>();

    private GameController gameController;

    public ChessboardComponent(int chessSize) {
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 8;
        int height = CHESS_SIZE * 8;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);

        initiateGridComponents();
    }


    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
//    private int checkNearby(Cell[][] grid,int i,int j,int count,boolean leftchecked){
//        if (!leftchecked) {
//            if ((i > 0) & grid[i][j].equals(grid[i - 1][j])) {
//                count++;
//                checkNearby(grid, i - 1, j, count,false);
//            } else {
//                checkNearby(grid, i + count, j,count,true);
//            }
//        }
//        else {
//            if ((i<CHESSBOARD_ROW_SIZE.getNum()-1) & grid[i][j].equals(grid[i+1][j])) {
//                count++;
//                checkNearby(grid, i + 1, j, count,true);
//            }
//        }
//        return count;
//    }
    public static int checkNearRows(Cell[][] grid, int i, int j, int count, boolean leftChecked) {
        if (!leftChecked){
            boolean exist=true;
            boolean equal=false;
            try {
                equal=grid[i][j].getPiece().getName().equals(grid[i - 1][j].getPiece().getName());
            }catch (NullPointerException | IndexOutOfBoundsException e){
                exist=false;
            }

            if (exist&&equal) {
                count = checkNearRows(grid, i - 1, j, count + 1, false);
            }
            else {
                count= checkNearRows(grid, i +count-1, j, count , true);
            }
        } else  {
            boolean exist=true;
            boolean equal=false;
            try {
                equal=grid[i][j].getPiece().getName().equals(grid[i + 1][j].getPiece().getName());
            }catch (NullPointerException|IndexOutOfBoundsException e){
                exist=false;
            }
            if (exist&&equal) {
                count = checkNearRows(grid, i + 1, j, count + 1, true);
            }
        }

        return count;
    }
    public static int checkNearColumns(Cell[][] grid, int i, int j, int count, boolean upChecked) {
        if (!upChecked){
            boolean exist=true;
            boolean equal=false;
            try {
                equal=grid[i][j].getPiece().getName().equals(grid[i][j - 1].getPiece().getName());
            }catch (NullPointerException |IndexOutOfBoundsException e){
                exist=false;
            }
            if (exist&&equal){

                count = checkNearColumns(grid, i, j-1, count +1, false);
            }
            else {
                count= checkNearColumns(grid, i , j+count-1, count ,true);
            }
        } else {
            boolean exist=true;
            boolean equal=false;
            try {
                equal=grid[i][j].getPiece().getName().equals(grid[i][j+1].getPiece().getName());
            }catch (NullPointerException|IndexOutOfBoundsException e){
                exist=false;
            }
            if (exist&&equal) {
                count = checkNearColumns(grid, i , j+1, count + 1, true);
            }
        }

        return count;
    }
    public static int checkNearBy(Cell[][] grid,int i,int j){
        return Math.max(checkNearRows(grid,i,j,1,false),checkNearColumns(grid,i,j,1,false));
    }
    public void initiateChessComponent(Chessboard chessboard) {
//        chessboard.removeAllChessPiece();
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                // TODO: Implement the initialization checkerboard
                //COMPLETED move to initPiece
                /*int count=0;
                do {
                    grid[i][j].setPiece(new ChessPiece(Util.RandomPick(new String[]{"💎", "⚪", "▲", "🔶"})));
                    count=checkNearBy(grid,i,j);
                    System.out.printf("%d, %d:%s count: %d\n", i, j,grid[i][j].getPiece().getName() , count);
                }while (count>=3);*/
                if (grid[i][j].getPiece() != null) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    gridComponents[i][j].add(new ChessComponent(CHESS_SIZE, chessPiece));
                }
            }
        }

    }
    /*public void setGridComponents(ChessboardPoint chessboardPoint,ChessPiece chessPiece){
        int i=chessboardPoint.getRow();
        int j=chessboardPoint.getCol();
        gridComponents[i][j].add(new ChessComponent(CHESS_SIZE, chessPiece));
    }*/

    public void initiateGridComponents() {

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (riverCell.contains(temp)) {//TODO:check the usage
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                } else {
                    cell = new CellComponent(Color.LIGHT_GRAY, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                }
                gridComponents[i][j] = cell;
            }
        }
    }

    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChessComponentAtGrid(ChessboardPoint point, ChessComponent chess) {
        getGridComponentAt(point).add(chess);
    }

    public void removeAllChessComponentsAtGrids(){
        //todo:  complete the method
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (gridComponents[i][j].getComponents().length>0){
                    removeChessComponentAtGrid(new ChessboardPoint(i,j));
                }
            }
        }

    }

    public ChessComponent removeChessComponentAtGrid(ChessboardPoint point) {
        // Note re-validation is required after remove / removeAll.
        ChessComponent chess = (ChessComponent) getGridComponentAt(point).getComponents()[0];
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }

    public CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y/CHESS_SIZE +  ", " +point.x/CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y/CHESS_SIZE, point.x/CHESS_SIZE);
    }
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void swapChess(){
        gameController.onPlayerSwapChess();
    }

    public void nextStep(){
        gameController.onPlayerNextStep();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ChessComponent) clickedComponent.getComponents()[0]);
            }
        }
    }


    public int getCHESS_SIZE() {
        return CHESS_SIZE;
    }//added
}
