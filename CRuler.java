import java.util.*;
public class CRuler extends Cobject{
int orientation = horizontal;

	static int horizontal = 1;
	static int vertical = 0;

	public CRuler(int or){
		super();
                orientation=or;
                if(orientation==horizontal)
                {
                    w = fillParent;
                    h=1;
                }
                if(orientation==vertical)
                {
                    w=1;
                    h = fillParent;
                }
                changed = true;
	}
        @Override
	public void paint(){
            tg.setPaintable(true);
            if(orientation==horizontal)
            {
                tg.screen = new char[1][dynw];
                Arrays.fill(tg.screen[0],'#');
            }
            else
            {
                tg.screen = new char[dynh][1];
                for(int i=0;i<dynh;i++)
                {
                    tg.setPixel(0, i);
                }
            }
            changed = false;
            tg.setPaintable(false);
        }
}