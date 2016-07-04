import java.util.*;
public class FlowLayout extends Layout{
	int padding = 0;
	boolean changed = false;
	public FlowLayout(int h1,int w1){
		super();
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
	public FlowLayout(){
		super();
	}
	public void add(Cobject o){
		elements.add(o);
		o.setParent(this);
		changed = true;
	}
	@Override
	public void refreshView(){
		coord = new ArrayList<int[]>();
			int[] tc = {padding+(border?1:0),padding+(border?1:0)};
			coord.add(tc);
		int maxx = tc[0]+elements.get(0).tg.getWidth();
		int maxy = tc[1]+elements.get(0).tg.getHeight();
		for(int i=1;i<elements.size();i++)
		{
			Cobject o = elements.get(i);
			Cobject prev = elements.get(i-1);
			int prevx = coord.get(i-1)[0];
			int prevy = coord.get(i-1)[1];
			int endx = prevx+prev.tg.getWidth()+padding+o.tg.getWidth();
			int nx = 0 , ny=0;
			if(o.h<0)
			{
				o.dynh = tg.getHeight()-(prevy);
				o.changed = true;
                                //o.paint();
				//int endy = h-1;
                                //System.out.println("dynh : "+o.dynh);
			}
			if(o.w<0)
			{
				o.dynw = tg.getWidth()-(prevx+padding+prev.tg.getWidth()+padding);
				o.changed = true;
                                o.paint();
                                endx = prevx+prev.tg.getWidth()+padding+o.tg.getWidth()+padding;
				
                                //System.out.println("dynh : "+o.dynw);
			}
			if(endx>=tg.getWidth()-(border?1:0) && prevy+prev.tg.getHeight()+padding+o.tg.getHeight()<=tg.getHeight()-(border?1:0) && o.w>0)
			{
				nx = padding;
				ny = prevy+padding+prev.tg.getHeight();
			}
			else
			{
				nx = prevx+prev.tg.getWidth()+padding;
				ny = prevy;
			}
			tc = new int[2];
			tc[0] = nx;
			tc[1] = ny;
			if((tc[0]+o.getWidth())>maxx)
			{
				maxx = tc[0]+o.getWidth();
			}
			if((tc[1]+o.getHeight())>maxy)
			{
				maxy = tc[1]+o.getHeight();
			}
			coord.add(tc);
		}
	}
}