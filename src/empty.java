//public static int checkNearRows(Cell[][] grid, int i, int j, int count, boolean leftChecked) {
//        if (!leftChecked){
//        boolean exist=true;
//        boolean equal=false;
//        try {
//        equal=grid[i][j].getPiece().getName().equals(grid[i - 1][j].getPiece().getName());
//        }catch (NullPointerException e){
//        exist=false;
//        }
//
//        if (i > 0 &&exist&&equal) {
//        count = checkNearRows(grid, i - 1, j, count + 1, false);
//        }
//        else {
//        count= checkNearRows(grid, i +count, j, count , true);
//        }
//        } else  {
//        if (i < CHESSBOARD_ROW_SIZE.getNum() - 1 && grid[i][j].getPiece().getName().equals(grid[i + 1][j].getPiece().getName())) {
//        count = checkNearRows(grid, i + 1, j, count + 1, true);
//        }
//        }
//
//        return count;
//        }
//public static int checkNearColumns(Cell[][] grid, int i, int j, int count, boolean upChecked) {
//        if (!upChecked){
//        boolean exist=true;
//        boolean equal=false;
//        try {
//        grid[i][j].getPiece().getName().equals(grid[i][j - 1].getPiece().getName());
//        }catch (NullPointerException e){
//        exist=false;
//        }
//        if (j > 0 &&exist){
//
//        count = checkNearColumns(grid, i, j-1, count +1, false);
//        }
//        else {
//        count= checkNearColumns(grid, i , j+count, count ,true);
//        }
//        } else {
//        if (j < CHESSBOARD_COL_SIZE.getNum() - 1 && grid[i][j].getPiece().getName().equals(grid[i][j+1].getPiece().getName())) {
//        count = checkNearColumns(grid, i , j+1, count + 1, true);
//        }
//        }
//
//        return count;
//        }



/*
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
        count= checkNearRows(grid, i +count, j, count , true);
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
        grid[i][j].getPiece().getName().equals(grid[i][j - 1].getPiece().getName());
        }catch (NullPointerException |IndexOutOfBoundsException e){
        exist=false;
        }
        if (exist&&equal){

        count = checkNearColumns(grid, i, j-1, count +1, false);
        }
        else {
        count= checkNearColumns(grid, i , j+count, count ,true);
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
        }*/
