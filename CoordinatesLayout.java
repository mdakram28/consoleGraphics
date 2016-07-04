import java.util.*;
public class CoordinatesLayout extends Layout{
	public CoordinatesLayout(int h1,int w1){
		super();
            border = true;
		h = h1;w = w1;
		if(h>=0 && w>=0)
		{
			tg = new TextGraphics(h1,w1);
		}
		else
		{
			if(h<0 && w>=0)
			{
				tg = new TextGraphics(0,w);
			}
			else if(w<0 && h>=0)
			{
				tg = new TextGraphics(h,0);
			}
			else
			{
				tg = new TextGraphics(0,0);
			}
		}
	}
	public CoordinatesLayout(){
		super();
	}
	public void add(Cobject o,int x,int y){
		elements.add(o);
		o.setParent(this);
		int[] c = new int[2];
		c[0] = x;
		c[1] = y;
		coord.add(c);
		changed = true;
	}
	public void setCoordinates(Cobject c,int nx,int ny)
	{
		int i=0;
		for(i=0;i<elements.size();i++)
		{
			if(elements.get(i).equals(c))
			{
				break;
			}
		}
		if(i!=elements.size())
		{
			coord.get(i)[0] = nx;
			coord.get(i)[1] = ny;
		}
		changed = true;
	}
	@Override
	public void refreshView(){
		for(int i=0;i<elements.size();i++)
		{
			int x = elements.get(i).tg.getWidth()+coord.get(i)[0];
			if(x>maxx)
			{
				maxx = x;
			}
			int y = elements.get(i).tg.getHeight()+coord.get(i)[1];
			if(y>maxy)
			{
				maxy = y;
			}
		}
	}
}