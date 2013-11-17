package democonsole;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	// Container firstCon=null;
	public final static int RESIZE_WIDTH = 5;
	public final static int MIN_WIDTH = 20;
	public final static int MIN_HEIGHT = 20;
	boolean isTop = false;
	boolean isTopRight = false;
	boolean isRight = false;
	boolean isBottomRight = false;
	boolean isBottom = false;
	boolean isBottomLeft = false;
	boolean isLeft = false;
	boolean isTopLeft = false;

	public LoginFrame() {
		// this.firstCon=firstCon;
		this.setDragable();
		this.setVisible(false);
	}

	private void setDragable() {
		this.addMouseListener(new MouseLis());
		this.addMouseMotionListener(new MouseMotion());
	}

	class MouseLis extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			isDragged = false;
			LoginFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int width = LoginFrame.this.getWidth();
			int height = LoginFrame.this.getHeight();
			int RESIZE_WIDTH = LoginFrame.RESIZE_WIDTH;
			tmp = new Point(e.getX(), e.getY());
			if (!(x <= RESIZE_WIDTH || x > (width - RESIZE_WIDTH)
					|| y <= RESIZE_WIDTH || y > (height - RESIZE_WIDTH))) {
				isDragged = true;
				LoginFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		}

	}

	class MouseMotion extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {

			if (isDragged) {
				loc = new Point(
						LoginFrame.this.getLocation().x + e.getX() - tmp.x,
						LoginFrame.this.getLocation().y + e.getY() - tmp.y);
				LoginFrame.this.setLocation(loc);
			} else {
				// if((MyFrame.this.getWidth()>MyFrame.MIN_WIDTH||MyFrame.this.getHeight()>MyFrame.MIN_HEIGHT)){
				/*
				 * if(isRight&&((MyFrame.this.getWidth()>MyFrame.MIN_WIDTH)||(e.getX
				 * ()-tmp.x)>0)){ //
				 * MyFrame.this.setSize(e.getX(),MyFrame.this.getHeight());
				 * MyFrame.this.setBounds(MyFrame.this.getX(),
				 * MyFrame.this.getY(), e.getX(),MyFrame.this.getHeight());
				 * }else
				 * if(isBottom&&((MyFrame.this.getHeight()>MyFrame.MIN_HEIGHT
				 * )||(e.getY()-tmp.y)>0)){
				 * MyFrame.this.setSize(MyFrame.this.getWidth(),e.getY()); }else
				 * if(isBottomRight){ MyFrame.this.setSize(e.getX(), e.getY());
				 * }
				 */
				int x = e.getX();
				int y = e.getY();
				int width = LoginFrame.this.getWidth();
				int height = LoginFrame.this.getHeight();
				int nextX = LoginFrame.this.getX();
				int nextY = LoginFrame.this.getY();
				int nextWidth = LoginFrame.this.getWidth();
				int nextHeight = LoginFrame.this.getHeight();
				if (isTopLeft || isLeft || isBottomLeft) {
					nextX += x;
					nextWidth -= x;
				}
				if (isTopLeft | isTop || isTopRight) {
					nextY += y;
					nextHeight -= y;
				}
				if (isTopRight || isRight || isBottomRight) {
					nextWidth = x;
				}
				if (isBottomLeft || isBottom || isBottomRight) {
					nextHeight = y;
				}
				if (nextWidth <= LoginFrame.MIN_WIDTH) {
					nextWidth = LoginFrame.MIN_WIDTH;

					if (isTopLeft || isLeft || isBottomLeft) {
						nextX = LoginFrame.this.getX() + width - nextWidth;
					}

				}
				if (nextHeight <= LoginFrame.MIN_HEIGHT) {
					nextHeight = LoginFrame.MIN_HEIGHT;
					if (isTop || isTopLeft || isTopRight) {
						nextY = LoginFrame.this.getY() + height - nextHeight;
					}
				}
				LoginFrame.this.setBounds(nextX, nextY, nextWidth, nextHeight);
				// }
				System.out.println("nX:" + nextX + " nY:" + nextY);
			}

		}

		public void mouseMoved(MouseEvent e) {
			// System.out.println(e.getX());
			int x = e.getX();
			int y = e.getY();
			int width = LoginFrame.this.getWidth();
			int height = LoginFrame.this.getHeight();
			int cursorType = Cursor.DEFAULT_CURSOR;
			int RESIZE_WIDTH = LoginFrame.RESIZE_WIDTH;
			isTop = false;
			isTopRight = false;
			isRight = false;
			isBottomRight = false;
			isBottom = false;
			isBottomLeft = false;
			isLeft = false;
			isTopLeft = false;

			if (x > RESIZE_WIDTH && x < (width - RESIZE_WIDTH)
					&& y <= RESIZE_WIDTH) {
				isTop = true;
				cursorType = Cursor.N_RESIZE_CURSOR;
			} else if (x > (width - RESIZE_WIDTH) && y <= RESIZE_WIDTH) {
				isTopRight = true;
				cursorType = Cursor.NE_RESIZE_CURSOR;
			} else if (x > (width - RESIZE_WIDTH) && y > RESIZE_WIDTH
					&& y < (height - RESIZE_WIDTH)) {
				isRight = true;
				cursorType = Cursor.E_RESIZE_CURSOR;
			} else if (x > (width - RESIZE_WIDTH)
					&& y > (height - RESIZE_WIDTH)) {
				isBottomRight = true;
				cursorType = Cursor.SE_RESIZE_CURSOR;
			} else if (x > RESIZE_WIDTH && x < (width - RESIZE_WIDTH)
					&& y > (height - RESIZE_WIDTH)) {
				isBottom = true;
				cursorType = Cursor.S_RESIZE_CURSOR;
			} else if (x <= RESIZE_WIDTH && y > (height - RESIZE_WIDTH)) {
				isBottomLeft = true;
				cursorType = Cursor.SW_RESIZE_CURSOR;
			} else if (x <= RESIZE_WIDTH && y > RESIZE_WIDTH
					&& y < (height - RESIZE_WIDTH)) {
				isLeft = true;
				cursorType = Cursor.W_RESIZE_CURSOR;

			} else if (x <= RESIZE_WIDTH && y <= RESIZE_WIDTH) {
				isTopLeft = true;
				cursorType = Cursor.NW_RESIZE_CURSOR;
			}
			LoginFrame.this.setCursor(new Cursor(cursorType));
		}
	}
}