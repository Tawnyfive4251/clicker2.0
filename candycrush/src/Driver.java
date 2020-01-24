import java.util.Arrays;


public class Driver {
	public static void main(String[] arg) {
		int rand  = (int)(Math.random()*(3)+1);
		int[][] data3 = { {32,32,32,0,0,0,0,32,32,32},
				{32,32,0,0,0,0,0,0,32,32},
				{32,0,0,0,0,0,0,0,0,32},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,32,32,0,0,0,0},
				{0,0,0,0,0,32,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{32,0,0,0,0,0,0,32,0,32},
				{32,32,0,0,0,0,0,0,0,32},
				{32,32,32,0,0,0,0,32,32,32}
		};
		int[][] data2 = { {32,32,0,0,0,0,0,0,32,0},
				{32,0,0,0,0,0,0,0,0,0},
				{0,0,0,32,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{32,0,0,0,32,32,0,0,32,0},
				{0,0,0,0,32,32,0,0,32,0},
				{0,0,32,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,32,0,0,0,0,0,0,32,0},
				{0,32,0,0,0,0,0,0,0,32}
		};
		int[][] data1 = { {32,0,0,0,0,0,0,0,0,32},
				{0,32,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,32,0,0,32,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,32,0,0,32,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,32},
				{32,0,0,0,0,0,0,0,32,32}
		};
		if(rand == 1){
			BoardGUI b = new BoardGUI(data1);	//non populated board
		}else if(rand == 2){
			BoardGUI b = new BoardGUI(data2);
		}else{
			BoardGUI b = new BoardGUI(data3);
		}
		//BoardGUI b = new BoardGUI();	//populated board
		//Board c = new Board();
		//int[] myCol = c.getCol(data,0);
		//System.out.println(Arrays.toString(myCol));
		System.out.println("Welcome to Tyle");
		System.out.println("In order to play, click and drag tiles to connect three or more of the same colored tile to earn points");
		System.out.println("Tiles can only be swapped adjacently and not diagonally");
		System.out.println("Brown colored tiles are rocks and cannot be swapped/combined");
		System.out.println("Click an empty(non-brown) tile to begin");
	}

}