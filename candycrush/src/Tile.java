import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

public class Tile extends JLabel {
	public static  int tots = 0;
	private Color c;
	private Color cb;
	 int val=0;
	int cindex = 0;
	public Tile() {
		cb = new Color(0xb1a395);
		setOpaque(true);
		tots++;		
		MatteBorder matte = new MatteBorder(5,5,5,5,cb);
		this.setBorder(matte);
		if(val>=0) setText(Integer.toString(val));
//		NEW CODE
//		if (val>0) setIcon("rocc");
	}
	
	
	public void setValue(int v) {
		val = v;
		setText("");
	}
	public Tile(int n) {
		val = n;
		cb = new Color(0xb1a395);
		setOpaque(true);
		tots++;
		
		
		MatteBorder matte = new MatteBorder(5,5,5,5,cb);
		this.setBorder(matte);
		setText(Integer.toString(val));
	}
	
	
	
	
	public void setText(String str) {
		try {

			if(val>256) {
				Font bigFont = new Font("Serif", Font.BOLD, 20);
				setFont(bigFont);
			}else if(val>256){
				Font bigFont = new Font("Serif", Font.BOLD, 40);
				setFont(bigFont);
			}else {
			
				Font bigFont = new Font("Serif", Font.BOLD, 55);
				setFont(bigFont);
			}
			
			
			
			int val2 = Integer.valueOf(Integer.toString(val));
			val2 = (int)( (Math.log(val)/Math.log(2)));
			if(val>0) {
				cindex = val2;
//				super.setText(Integer.toString(val));
			}else {
				cindex = 0;
				super.setText("");
			}
		}catch(Exception e) {
			
		}
	}
	public int getVal(){
		return val;
	}
	public void setImage(){
		String urlimage = String.format("images/%d",val);
		ImageIcon icon = createImageIcon(urlimage, "bruh");
		setIcon( icon);
	}
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path,
										String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
