import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class BoardGUI extends JPanel implements KeyListener, ActionListener, MouseListener{
	/**
	 * 
	 */
	private final int size = 10;
	private static final long serialVersionUID = 1L;
	private Tile[][] b;
	private Board data;
	private Color[] colors;
	Timer t;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int selectX;
	private int selectY;
	private int selectX2;
	private int selectY2;

	public BoardGUI() {
		b = new Tile[size][size];
		colors = new Color[11];
		t = new Timer(1000,this);
		setup(new int[][]{});	
		t.start();
		data.populateFree();
	}	
	
	public BoardGUI(int[][] d) {
		b = new Tile[size][size];
		colors = new Color[11];
		t = new Timer(1000,this);
		setup(d);
		t.start();
	}		
	
	
//	/*
	public void prepopulate(int[][] d) {
		data.populate(d);
		update();
	}
//	*/
	
	public void setup(int[][] d) {
		JFrame frame = new JFrame("2048");
		frame.setSize(600, 600);
		frame.setMaximumSize(frame.getSize());
		frame.setMinimumSize(frame.getSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		addMouseListener(this);
//		colors[0] = new Color(0xc7b9ab);
//		colors[1] = new Color(0xeaded5);
//		colors[2] = new Color(0xe9dbc0);
//		colors[3] = new Color(0xefa76b);
//		colors[4] = new Color(0xf59563);
//		colors[5] = new Color(0xf67c5f);
//		colors[6] = new Color(0xf65e3b);
//		colors[7]= new Color(0xedcf72);
//		colors[8]= new Color(0xedcc61);
//		colors[9]= new Color(0xedcc61);
//		colors[size]= new Color(0xf3c92f);
		colors[0] = new Color(0xFFF1DD);
		colors[1] = new Color(0xFF0005);
		colors[2] = new Color(0x39ECEE);
		colors[3] = new Color(0xe5da1a);
		colors[4] = new Color(0x27d83d);
		colors[5] = new Color(0x836e4b);
		colors[6] = new Color(0xf65e3b);
		colors[7]= new Color(0xedcf72);
		colors[8]= new Color(0xedcc61);
		colors[9]= new Color(0xedcc61);
		colors[10]= new Color(0xf3c92f);

		Font bigFont = new Font("Serif", Font.BOLD, 55);
		GridLayout g = new GridLayout(size,size);
		frame.setLayout(g);
		
		for(int i =0; i < b.length;i++) {
			for(int j = 0; j < b[0].length;j++) {
				b[i][j] = new Tile();
				b[i][j].setSize(100,100); //				Tile.setHorizontalAlignment(JTextField.CENTER);
				b[i][j].setFont(bigFont);
				b[i][j].setHorizontalAlignment(JTextField.CENTER);
				b[i][j].setBackground(colors[b[i][j].cindex]);
				frame.add(b[i][j]);
			}
		}
		data = new Board();
		data.populate(d);
		update();		 		
		frame.setVisible(true);

	}
	
	public void update() {
		for(int r = 0; r < size;r++) {
			for(int c =0; c < size; c++) {
				b[r][c].setValue(data.getBoard()[r][c]);
				b[r][c].setBackground(colors[b[r][c].cindex]);
				// add logic here
//				switch (b[r][c].getVal()) {
//					case 0:
//						System.out.println("bruh");
//					case 1:
//						System.out.println("bruhmoment");
//					case 2:
//						System.out.println("");
//				}
			}
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		/* call the helper methods for the Board object data*/
		
		switch(arg0.getKeyCode()) {
			case 39:
				data.right();
				break;
			case 37: 
				data.left();
				break;
			case 38:
				data.up();
				break;
			case 40:
				data.down();
				break;
		}
		
		
		update();
		/** reset the game if all tiles are populated **/
//		if(data.gameOver()) {
//			data = new Board();
//			update();
		}
//	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//bot algorithm here
		//data.populateOne();
	}
	@Override
	public void mousePressed(MouseEvent e) {
	     x1=e.getX();
	     y1=e.getY();
//	    System.out.println(x1+","+y1);//these co-ords are relative to the component
		 selectX = (x1)/60;
		 selectY = (y1)/60;
//		 System.out.println(selectX +","+ selectY);
		data.markedReset();

	}
	@Override
	public void mouseReleased(MouseEvent e) {
	     x2=e.getX();
	     y2=e.getY();
//	    System.out.println(x2+","+y2);//these co-ords are relative to the component
		 selectX2 = (x2)/60;
		 selectY2 = (y2)/60;
//		System.out.println(selectX2 +","+ selectY2);
		data.markedReset();

//				System.out.println(Arrays.deepToString(data.getBoard()));
		if((Math.abs(selectX-selectX2) <= 1)&&((Math.abs(selectY-selectY2) <= 1))) {
			data.markedReset();

		if((data.getBoard()[selectX2][selectY2] != 32)&&(data.getBoard()[selectX][selectY] != 32)) data.swap(selectY, selectX, selectY2, selectX2);
//		System.out.println(Arrays.deepToString(data.getBoard()));
			data.markedReset();
			data.MarkAll(selectY, selectX);
			data.MarkAll(selectY2, selectX2);
			if (data.count() <3){
				data.swap(selectY, selectX, selectY2, selectX2);
			}

//			System.out.println(Arrays.deepToString(data.marked));


			data.remove();
			data.markedReset();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			data.slideDown();
		data.populateAll();
		}
		data.markedReset();
		update();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
}
