import java.util.Arrays;
import java.util.Random;

public class Board {

    private int[][] board; // holds state of game
    private Random rnd = new Random(0); // setup random # generator
    private int openTiles = 100;    //var for checking if game is over
    private boolean game_over = false;    //if true it resets the game
    private int num = 0;    //debounce variable
    private int[][] marked = new int[10][10];
    private int succeed;

    /* default constructor for board */
    // constructors must match exactly the name
    // of the class.
    public Board() {    //basic constructor

        // instantiate the board
        board = new int[10][10];

        game_over = false;
        populateOne();    //populates the blank board twice automatically
        populateOne();
    }

    /*
     * return a String representation of the 2D array board
     *
     * each row should be in its own line
     *
     * Example:
     *
     * { {1, 2, 3}, {4, 5, 6}} -> 1 2 3
     *
     * 4 5 6
     */

    //overriding a method is when a 'child'
    //class implements the exact same method
    //that its parent class has
    public String toString() {
        String str = "";
        /*
         * Use the String formatter to pad the numbers with leading 0s
         * so that the print out does not become jagged
         * An example is shown below.
         * String str = String.format("%04d", 9);  // 0009
         * int x = 30;
         * System.out.println(String.format("%04d",x));
         *
         */
        int count = 0;
        for (int i = 0; i < board.length / 2; i++) {
            for (int j = 0; j < board[0].length / 2; j++) {
                if (count == 0) {
                    str += String.format(" %04d", board[i][j]);    //first one so it doesn't have a space at the top
                } else {
                    str += String.format(" \n %04d", board[i][j]);    //the \n %04d causes it to move down a space and go in a certain order of 0000
                }
                count = 1;
            }
        }
        return str;
    }

    /*
     * set one of the empty spaces (at random)
     *
     * to a 2 or 4 (90/10 chance). an empty spot is defined to be a 0 element
     *
     * Must use the Random class object rnd.
     *
     * Example Use of rnd object.
     *
     * int randomNum = rnd.nextInt(10); //returns a number in range [0 10) (not
     * inclusive on the 10)
     */

    public void populateOne() {    //randomly populates a square
        if (openTiles <= 0) {    //checks if the game can no longer be continued
            openTiles = 100;
            game_over = true;
            return;
        }
        int num;
        int x;    //Variables for populate
        int y;
        boolean found = false;


        if (rnd.nextInt(100) <= 10) {    //random num generator (10 percent for 4) and (90 percent for a 2)
            num = 4;
        } else {
            num = 2;
        }

        while (!found) {    //if it doesn't find an open tile it will find another random tile
            x = (int) (rnd.nextInt(10));
            y = (int) (rnd.nextInt(10));
            if (board[x][y] == 0) {
                board[x][y] = num;
                found = true;
            }
        }
        openTiles--;    //removes one from openTile for game_over header
        //System.out.println(openTiles);
        // find an empty space on the board and randomly populate either a 2 or
        // 4 at that location

    }

    /*
     *
     * Given an array of integers, slide all non-zero elements to the right.
     *
     * zero elements are considered open spots.
     *
     * example:
     *
     * [0 2 0 2]->[0 0 2 2]
     *
     * [2 0 0 2]->[0 0 2 2]
     *
     * [4 0 0 0]->[0 0 0 4]
     */

    public void slideRight(int[] row) {
        for (int i = row.length - 1; i >= 0; i--) {
            if (row[i] == 0) {
                //open! find the next non-zero element
                for (int j = i - 1; j >= 0; j--) {
                    if (row[j] != 0) {
                        //swap el j and i;
                        int temp = row[i];
                        row[i] = row[j];
                        row[j] = temp;
                        break;    //breaks out once it finds the empty space, if there is one.
                    }
                }
            }
        }
    }

    /*
     *
     * Move the numbers as far to the right as they can go
     *
     * aka the numbers are trying to move to the right-most
     *
     * empty spaces. This method must utilize the slideRight(int[] row) method
     *
     * must utilize the helper method above for full credit.
     *
     * param: a valid row of 2048 where 0s are "empty" spots
     *
     * effect: row is modified so all numbers are to the right side
     *
     * return: none
     */

    public void slideRight() {
        for (int i = 0; i < board[0].length; i++) {
            slideRight(board[i]);    //calls slideRight four times for each diff column
        }
    }

    /**
     * Given an array of integers, slide all non-zero elements to the left.
     * <p>
     * zero elements are considered open spots.
     * <p>
     * example:
     * <p>
     * [0 2 0 2] -> [2 2 0 0]
     * <p>
     * [2 0 0 2] -> [2 2 0 0]
     */

    public void slideLeft(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                //open! find the next non-zero element
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] != 0) {
                        //swap el j and i;
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        break;    //breaks out once it finds and swaps the vars
                    }
                }
            }
        }
    }

    /*
     * Slide all the numbers to the left so that
     *
     * all of the empty spaces are on the right side
     */

    public void slideLeft() {
        for (int i = 0; i < board[0].length; i++) {
            slideLeft(board[i]);    //calls slideLeft for each of the 4 columns
        }
    }

    /**
     * Given a 2D array and a column number, return a 1D array representing the
     * elements in the given column number.
     */

    public int[] getCol(int[][] data, int c) {
        int[] newArray = new int[data[0].length];
        for (int i = 0; i < data.length; i++) {
            newArray[i] = data[i][c];
        }
        return newArray;    //returns the column as a 1d array
    }

    /**
     * Given an array of integers, slide all non-zero elements to the top.
     * <p>
     * zero elements are considered open spots.
     */

    public void slideDown(int[] row) {
        for (int i = row.length - 1; i >= 0; i--) {
            if (row[i] == 0) {
                //open! find the next non-zero element
                for (int j = i - 1; j >= 0; j--) {
                    if (row[j] != 0) {
                        //swap el j and i;
                        int temp = row[i];
                        row[i] = row[j];
                        row[j] = temp;
                        for (int r = 0; r < board.length; r++) {
                            board[r][num] = row[r];
                        }
                        break;//breaks out so it doesn't break the code
                    }
                }
            }
        }
    }

    /*
     *
     * Slide all elements in the board towards the top.
     *
     * You must use slideUp and getCol for full credit.
     */

    public void slideDown() {
        num = 0;
        for (int i = 0; i < board.length; i++) {
            slideDown(getCol(board, i));//calls slidedown for each col and row
            num++;
        }
    }

    public void slideUp(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                //open! find the next non-zero element
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] != 0) {
                        //swap el j and i;
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        for (int c = 0; c < board.length; c++) {
                            board[c][num] = arr[c];
                        }
                        break; //breaks out so it doesn't break the game/code
                    }
                }
            }
        }
    }

    /*
     * slide all the numbers down so that any
     *
     * empty space is at the top
     *
     * You must use slideDown and getCol for full credit.
     */

    public void slideUp() {
        num = 0;
        for (int i = 0; i < board.length; i++) {
            slideUp(getCol(board, i));    //uses getCol to grab the column
            num++;
        }
    }

    /*
     * Given the 2D array, board, combineRight will take adjacent numbers that
     * are the same and combine them (add them).
     *
     * After adding them together, one of the numbers is zeroed out. For
     * example, if row 0 contained [0 0 4 4],
     *
     * a call to combineRight will produce [0 0 0 8]. If row 1 contained [2 2 2
     * 2], a call to combineRight will
     *
     * produce [0 4 0 4].
     *
     * Notice that the left element is zeroed out.
     */

    public void combineRight() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 9; c > board.length - 1; c--) {
                if ((board[r][c] == board[r][c - 1]) && (board[r][c] != 0)) {
                    board[r][c] = board[r][c] + board[r][c - 1];
                    board[r][c - 1] = 0;
                    openTiles++;    //adds one to openTiles when two combine because it gets rid of one tiles
                }
            }
        }
    }

    /*
     * same behavior as combineRight but the right element is zeroed out when
     * two elements are combined
     */

    public void combineLeft() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length - 1; c++) {
                if ((board[r][c] == board[r][c + 1]) && (board[r][c] != 0)) {
                    board[r][c] = board[r][c] + board[r][c + 1];
                    board[r][c + 1] = 0;
                    openTiles++;    //adds one to openTiles when two combine because it gets rid of one tiles
                }
            }
        }
    }

    /*
     * same behavior as combineRight but the bottom element is zeroed out when
     * two elements are combined
     */

    public void combineUp() {
        for (int r = board[0].length - 1; r > 0; r--) {
            for (int c = 0; c < board[0].length; c++) {
                if ((board[r][c] == board[r - 1][c]) && (board[r][c] != 0)) {
                    board[r - 1][c] = board[r][c] + board[r - 1][c];
                    board[r][c] = 0;
                    openTiles++; //adds one to openTiles when two combine because it gets rid of one tiles
                }
            }
        }
    }

    /*
     * same behavior as combineRight but the top element is zeroed out when two
     * elements are combined
     */

    public void combineDown() {
        for (int r = 0; r < board[0].length - 1; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if ((board[r][c] == board[r + 1][c]) && (board[r][c] != 0)) {
                    board[r + 1][c] = board[r][c] + board[r + 1][c];
                    board[r][c] = 0;
                    openTiles++; //adds one to openTiles when two combine because it gets rid of one tiles
                }
            }
        }
    }


    //all of these are simplified versions of three methods
    public void left() {
        //1)numbers slide to the left
        //2)combine
        //3)slide again
        slideLeft();    //simplified version of two methods
//        combineLeft(); //checks if you can combine any
//        slideLeft(); //once it combines them, it slides them over like in the real game.
//        markLeft(board.length - 1, board[0].length-1);
//        remove();
    }

    public void right() {
        slideRight();
//        combineRight(); //checks if you can combine any
//        slideRight(); //once it combines them, it slides them over like in the real game.
//        markRight(board.length - 1, board[0].length-1);
//        remove();

    }

    public void up() {
        slideUp();
//        combineUp(); //checks if you can combine any
//        slideUp(); //once it combines them, it slides them over like in the real game.
//        MarkAll(board.length - 1,board[0].length - 1);
        if (count() > 3){
            remove(0);
        }

        markedReset();

    }

    public void down() {
        slideDown();
//        combineDown(); //checks if you can combine any
        slideDown(); //once it combines them, it slides them over like in the real game.
//        markDown()/;
    }

    public boolean gameOver() {
        return game_over;    //boolean starts as false, and becomes true after all squares are full
    }                        //and you can no longer combine numbers

    public int[][] getBoard() {
        return board;    //returns the 2d array board
    }

    // populate with a given 2d array
    public void populate(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                board[r][c] = arr[r][c];    //uses double for(loop) in order to cover all the rows and columns

            }
        }
    }

    public void markLeft(int row, int col) {
        int reference = board[row][col];
        boolean go = true;
        int c = col;
        while (go && row >=0 && c >=0) {
            if ((board[row][c] == reference) && (marked[row][c] != 1) ) {
                marked[row][c] = 1;
                if (c > 0) {
                    c--;
                    succeed = 0;
                }
                go = true;
            } else {
                go = false;
                ++succeed;
            }
        }
    }

    public void markRight(int row, int col) {
        int reference = board[row][col];
        boolean go = true;
        int c = col;
        while (go && c < board.length ) {
            if ((board[row][c] == reference) && (marked[row][c] != 1)) {
                marked[row][c] = 1;
                if (c < board[0].length) {
                    c++;
                    succeed = 0;
                }
                ;
                go = true;
            } else {
                ++succeed;
                go = false;
            }
        }
    }

    public void markUp(int row, int col) {
        int reference = board[row][col];
        boolean go = true;
        int r = row;
        while (go && r >= 0) {
            if ((board[r][col] == reference) && (marked[r][col] != 1)) {
                marked[r][col] = 1;
                if (r < board.length) {
                    r--;
                    succeed = 0;
                }
                go = true;
            } else go = false;
        }
    }
    public void markDown(int row, int col) {
        int reference = board[row][col];
        boolean go = true;
        int r = row;
        while (go && r < 10) {
            if ((board[r][col] == reference) && (marked[r][col] != 1)) {
                marked[r][col] = 1;
                if (r < board.length) {
                    r++;
                    succeed = 0;
                }
                go = true;
            } else go = false;
        }
    }
    public  int  count(){
        int counter = 0;
        for (int i = 0; i <marked.length ; i++) {
            for (int j = 0; j < marked[0].length; j++) {
                if(marked[i][j] == 1) ++counter;
            }
        }
        return  counter;
        }
    public void remove(int cntr) {
        int counter = cntr;
        int[][] b = board;
              System.out.println(Arrays.deepToString(marked));
            System.out.println(Arrays.deepToString(board));
        for (int i = 0; i <marked.length ; i++) {
            for (int j = 0; j < marked[0].length; j++) {
                if (marked[i][j] == 1) {
                    marked[i][j] = 0;
                    board[i][j] = 0;
                }

            }

        }
//        else {board = b;}
    }
    public void MarkAll(int row, int col){
        succeed = 0;
        markUp(row, col);
        succeed = 0;
        marked[row][col] = 0;
        markLeft(row, col);
        succeed = 0;
        marked[row][col] = 0;
        markRight(row,col);
        marked[row][col] = 0;
        succeed = 0;
        markDown(row, col);
    }
    public void markedReset(){
        for (int i = 0; i < marked.length; i++) {
            for (int j = 0; j <marked[0].length ; j++) {
                marked[i][j] = 0;

            }
        }
    }
}

