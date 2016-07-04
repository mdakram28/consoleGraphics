import java.util.*;
public class CRectangle extends Cobject{
	public CRectangle(int h,int w,char ch){
		super();
		tg = new TextGraphics(h,w);
		tg.setPaintable(true);
		tg.drawRectangle(0,0,w-1,h-1,ch);
                changed = true;
		tg.paint();
	}
}