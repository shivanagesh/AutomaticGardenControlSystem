/**
 * 
 */
package gardenControl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

/**
 * <h1> Panel Background </h2>
 * This Class will  draw given image as background in given Jframe
 * 
 *  
 * @author Shivanagesh Chandra
 * Feb 16, 2016 2016
 * Background.java
 */
public class PanelBackground extends JComponent{
	private Image background;
	public PanelBackground(Image background){
		this.background = background;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}



