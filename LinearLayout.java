import java.util.*;
public class LinearLayout extends Layout{
	ArrayList<Integer> weight = new ArrayList<Integer>();
	int padding = 0;
	boolean changed = true;
	int orientation=1; //0-vertical  1-horizontal
        
	static int horizontal = 1;
	static int vertical = 0;

	public LinearLayout(int h1,int w1,int or){
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
                orientation = or;
	}
        public LinearLayout(int h1,int y1){
            this(h1,y1,horizontal);
        }
        public LinearLayout(){
            this(horizontal);
        }
	public LinearLayout(int or){
		super();
                orientation = or;
	}
        
	public void add(Cobject o,int wt){
		elements.add(o);
		weight.add(wt);
		o.setParent(this);
		changed = true;
	}
	public void add(Cobject o){
		elements.add(o);
		weight.add(1);
		o.setParent(this);
		changed = true;
	}
	@Override
	public void refreshView(){
            if(elements.size()==0)return;
		if(orientation==horizontal)setWidths();else{ setHeights();}
		coord = new ArrayList<int[]>();
		for(int i=0;i<elements.size();i++)
		{
			Cobject o = elements.get(i);
			int nx = padding+(border?1:0) , ny=padding+(border?1:0);
			if(o.h<0 && orientation==horizontal)
			{
				o.dynh = tg.getHeight()-(padding+padding)-(border?2:0);
				o.changed = true;
			}
			if(o.w<0 && orientation==vertical)
			{
				o.dynw = tg.getWidth()-(padding+padding)-(border?2:0);
                                if(o.dynw<0)o.dynw=0;
                                //System.out.println(tg.getWidth()+" : "+o.dynw);
				o.changed = true;
			}
			nx = (orientation==horizontal?(i!=0?(coord.get(i-1)[0]+elements.get(i-1).getTextGraphics().getWidth()):padding+(border?1:0)):nx);
			ny = (orientation==vertical?(i!=0?(coord.get(i-1)[1]+elements.get(i-1).getTextGraphics().getHeight()):padding+(border?1:0)):ny);
			int[] tc = new int[2];
			tc[0] = nx;
			tc[1] = ny;
			
			//System.out.println(i+" : "+nx+" : "+ny+" : "+o.tg.getWidth());
			coord.add(tc);
                        //System.out.println(coord.get(i)[0]+" : "+nx);
		}
                //System.out.println(coord.size());
	}
	public void setWidths(){
		int s=0,reserved=0;
		for(int i=0;i<elements.size();i++)
		{
                    elements.get(i).dynh = getHeight();
			if(elements.get(i).w==Cobject.fillParent)
			s+=weight.get(i);
			else
			{
                                elements.get(i).dynw = getWidth();
                                elements.get(i).changed = true;
				elements.get(i).paint();
				reserved+=elements.get(i).tg.getWidth();
                                //System.out.println("reserving : "+elements.get(i).tg.getWidth()+" , s :"+elements.get(i).tg.getString());
			}
		}
                if(s==0)return;
		double total = (tg.getWidth()-(border?2.0:0.0)-padding-padding-reserved)/s;
                //System.out.println(reserved+" : "+total+" : "+s);
		for(int i=0;i<elements.size();i++)
		{
			if(elements.get(i).w!=Cobject.fillParent)continue;
			int wid = (int)(weight.get(i)*total);
			elements.get(i).dynw = wid;
                        elements.get(i).changed = true;
                        elements.get(i).paint();
                //System.out.println(elements.get(i).dynw+" : "+elements.get(i).tg.getWidth());
		}
	}
        public void setHeights(){
		int s=0,reserved=0;
		for(int i=0;i<elements.size();i++)
		{
                                elements.get(i).dynh = getHeight();
			if(elements.get(i).h==Cobject.fillParent)
			s+=weight.get(i);
			else
			{
                                elements.get(i).dynh = tg.getHeight();
				elements.get(i).paint();
				reserved+=elements.get(i).tg.getHeight();
			}
                //System.out.println(s);
		}
		double total = (tg.getHeight()-(border?2.0:0.0)-padding-padding-reserved)/s;
		for(int i=0;i<elements.size();i++)
		{
			if(elements.get(i).h!=Cobject.fillParent)continue;
			int hi = (int)(weight.get(i)*total);
			elements.get(i).dynh = hi;
                        elements.get(i).changed = true;
                        elements.get(i).paint();
                //System.out.println(elements.get(i).dynh+" : "+elements.get(i).tg.getHeight());
		}
        }
}