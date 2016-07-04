import java.util.ArrayList;
public class Layout extends Cobject{
	ArrayList<Cobject> elements = new ArrayList<Cobject>();
	ArrayList<int[]> coord = new ArrayList<int[]>();
	int maxx=0,maxy=0;
	public Layout(){
		super();
	}
	public void init(TextGraphics ttg){
		if(tg==null)
		{
			h = ttg.getHeight();
			w = ttg.getWidth();
			tg=ttg;
		}
	}
	@Override
	public void paint(){
                //System.out.println(tg.getHeight()+" : "+tg.getWidth());
		tg.screen = new char[h<0?dynh:h][w<0?dynw:w];
                //System.out.println(dynw+" : "+w+" : "+tg.screen[0].length);
		refreshView();
		tg.setPaintable(true);
                //System.out.println(coord.size()+" :-------- "+elements.size());
		for(int i=0;i<elements.size();i++)
		{
                    //System.out.println(i+elements.get(i).getTextGraphics().getWidth()+" : "+coord.get(i)[0]);
			tg.drawTextGraphics(elements.get(i).getTextGraphics(),coord.get(i)[0],coord.get(i)[1]);
		}
		if(h==Cobject.wrapContent)
		{
			tg.trimHeight(maxy);
		}
		if(w==Cobject.wrapContent)
		{
                    //System.out.println(coord.get(0)[0]+" : "+coord.get(0)[1]);
			tg.trimWidth(maxx);
		}
		changed = false;
                //System.out.println(tg.getString());
		tg.setPaintable(false);
	}
	public void refreshView(){}
	@Override
	public boolean isChanged(){
		changed = changed || checkChangedElements();
		return changed;
	}
	public boolean checkChangedElements(){
		boolean ret = false;
		for(int i=0;i<elements.size();i++)
		{
			if(elements.get(i).isChanged())
			{
				ret = true;
			}
			if(ret)break;
		}
		return ret;
	}
}