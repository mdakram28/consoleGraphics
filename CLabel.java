import java.util.*;
public class CLabel extends Cobject{
Font font;
	public CLabel(String s,Font f){
		super();
		font = f;
		Bitmap bmp = f.getBitmap(s);
		tg = new TextGraphics(bmp.getHeight(),bmp.getWidth());
		tg.drawBitmap(bmp,0,0,true);
		tg.paint();
	}
	public CLabel(String s){
		super();
		font = new Font();
		Bitmap bmp = font.getBitmap(s);
		tg = new TextGraphics(bmp.getHeight(),bmp.getWidth());
		tg.drawBitmap(bmp,0,0,true);
		tg.paint();
	}
	public void setText(String s){
		Bitmap bmp = font.getBitmap(s);
		tg.setPaintable(true);
		tg.clearScreen();
		tg = new TextGraphics(bmp.getHeight(),bmp.getWidth());
		tg.drawBitmap(bmp,0,0,true);
		tg.setPaintable(false);
		changed = true;
	}
}