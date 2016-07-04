public class Cobject{
	TextGraphics tg = null;
	boolean border = false;
	boolean changed = true;
	int hWieght = 1;
	int yWieght = 1;
	int h=0 , w=0;
	Layout parent = null;
	int dynh=0,dynw=0;

	static int fillParent = -1;
	static int wrapContent = -2;

	public Cobject(){
	}
	public void setBordered(boolean b){border = b;}
	public TextGraphics getTextGraphics(){
		if(isChanged()){paint();}
		if(border)tg.drawRectangle(0,0,tg.getWidth()-1,tg.getHeight()-1,'#');
		return tg;
	}
	public void setTransparent(boolean b){tg.transparent = b;}
	public void paint(){
		changed = false;
	}
	public boolean isChanged(){
		return changed;
	}
	public void setParent(Layout p){
		parent = p;
	}
	public int getHeight(){return tg.getHeight()-(border?2:0);}
	public int getWidth(){return tg.getWidth()-(border?2:0);}
}