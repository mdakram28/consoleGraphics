import java.util.*;
public class CCircle extends Cobject{
	public CCircle(double r,char ch,double a){
		super();
		tg = new TextGraphics((int)Math.round(r*2)+1,(int)Math.round(r*2)+1);
		tg.setPaintable(true);
		tg.drawCircle((int)r,(int)r,r,a,ch);
		tg.paint();
	}
}