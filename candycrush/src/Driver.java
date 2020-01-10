import java.util.Arrays;


public class Driver {
	public static void main(String[] arg) {
	
		int[][] data = { {2,0,0,0,0,0,0,0,0,0},  
						 {0,0,0,0,0,0,0,0,0,0}, 
						 {0,0,0,0,0,0,0,0,0,0}, 
						 {0,0,0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,2,0,0,0,0},
						 {0,0,0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0,0,0,0}
						};
		//BoardGUI b = new BoardGUI(data);	//non populated board
		BoardGUI b = new BoardGUI();	//populated board
//		Board c = new Board();
//		int[] myCol = c.getCol(data,0);
//		System.out.println(Arrays.toString(myCol));
	}
}
