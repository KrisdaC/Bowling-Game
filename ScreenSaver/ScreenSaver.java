import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ScreenSaver extends JApplet implements Runnable {
	Thread draw;
	int delay;
	int speed = 1;
	private Dialog dlg = new Dialog(null);
	final int MAXOBJECTS = 20; // maximum number of objects to be drawn
	final int XLEFTMOST = 0; // left most x-coordinate of the screen
	final int XRIGHTMOST = 700; // right most x-coordinate of the screen
	final int YTOPMOST = 0; // top most y-coordinate of the screen
	final int YBOTTOMMOST = 500; // bottom most y-coordinate of the screen
	final int MAXWIDTH = 100; // maximum width of an object
	Shape[] shapes = new Shape[MAXOBJECTS]; // store objects to draw
	int[] upOrDowns = new int[MAXOBJECTS]; // store direction of each object
	Random ranDomShapeID = new Random(); // object used for creating random
											// integer
	int[] speeds = new int[MAXOBJECTS];
	int num;
	boolean run = false;
	Color[] colors = new Color[5];

	@Override
	public void init() {
		draw = new Thread(this);
		delay = 300;
		createShapes();
		setSize(700, 500);
		//draw.start();
		dlg.setVisible(true);
		for(int i=0;i<shapes.length;i++){
			if(i<num){
				while(i != 0 && isInVacantSpace(i)){
					shapes[i] = getEllipse();
				}
			}
			else{
				while(i != 0 && isInVacantSpace(i)){
					shapes[i] = getSquare();
				}
			}
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(g.getColor().WHITE);
		g.fillRect(0, 0, 700, 500);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < MAXOBJECTS; i++) {
			g2.setColor(initColors(g2,i));
			moveShapes();
			g2.fill(shapes[i]);
		}
	}
	
	public Color initColors(Graphics g2, int i){
		i = i/4;
		colors[0] = g2.getColor().ORANGE;
		colors[1] = g2.getColor().MAGENTA;
		colors[2] = g2.getColor().CYAN;
		colors[3] = g2.getColor().RED;
		colors[4] = g2.getColor().GREEN;
		return colors[i];
	}

	public void createShapes() {
		num = ranDomShapeID.nextInt(MAXOBJECTS);
		for (int i = 0; i < shapes.length; i++) {
			if (i < num) {
				shapes[i] = getEllipse();
			} else {
				shapes[i] = getSquare();
			}
			speeds[i] = speed;
		}
	}

	public void moveShapes() {
		for (int i = 0; i < shapes.length; i++) {
			if (i < num) {
				int sp = (int) ((RectangularShape) shapes[i]).getY();
				sp = sp + speeds[i];
				shapes[i] = new Ellipse2D.Double(
						((RectangularShape) shapes[i]).getX(), sp,
						((RectangularShape) shapes[i]).getWidth(),
						((RectangularShape) shapes[i]).getHeight());
				if (sp + ((RectangularShape) shapes[i]).getWidth() >= YBOTTOMMOST
						|| sp <= YTOPMOST) {
					speeds[i] = -1 * speeds[i];
				}
			} else {
				int sp = (int) ((RectangularShape) shapes[i]).getY();
				sp = sp + speeds[i];
				shapes[i] = new Rectangle2D.Double(
						((RectangularShape) shapes[i]).getX(), sp,
						((RectangularShape) shapes[i]).getWidth(),
						((RectangularShape) shapes[i]).getHeight());
				if (sp + ((RectangularShape) shapes[i]).getWidth() >= YBOTTOMMOST
						|| sp <= YTOPMOST) {
					speeds[i] = -1 * speeds[i];
				}
			}
		}
	}

	public Ellipse2D.Double getEllipse() {
		int width = ranDomShapeID.nextInt(MAXWIDTH);
		Ellipse2D.Double el = new Ellipse2D.Double(
				ranDomShapeID.nextInt((XRIGHTMOST - width) - (width) + 1)
						+ width, ranDomShapeID.nextInt((YBOTTOMMOST - width)
						- (width) + 1)
						+ width, width, width);
		return el;
	}

	public Rectangle2D.Double getSquare() {
		int width = ranDomShapeID.nextInt(MAXWIDTH);
		Rectangle2D.Double re = new Rectangle2D.Double(
				ranDomShapeID.nextInt((XRIGHTMOST - width) - (width) + 1)
						+ width, ranDomShapeID.nextInt((YBOTTOMMOST - width)
						- (width) + 1)
						+ width, width, width);
		return re;
	}
	private boolean isInVacantSpace(int i) {
		boolean temp = false;
		for(int j = 0; j < i; j++){
			temp = temp || shapes[i].getBounds2D().intersects(shapes[j].getBounds2D());
		}
		return temp;
	}

	@Override
	public void run() {
		while (run != false) {
			try {
				Thread.sleep(100);
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Dialog extends JDialog {
		public Dialog(JFrame parent) {
			super(parent);
			this.setLayout(new BorderLayout());
			JButton ok = new JButton("OK");
			JLabel text = new JLabel("Press OK to move");
			text.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(text, BorderLayout.CENTER);
			this.add(ok, BorderLayout.SOUTH);
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					run = true;
					draw.start();
				}
			});
			setSize(300, 150);
		}
	}

}
