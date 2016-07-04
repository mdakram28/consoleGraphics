import java.util.*;
public class CTextArea extends Cobject{
Font font;
String s="";
int lineSpacing=0;
int padding=0;
	public CTextArea(String st,Font f){
		super();
		font = f;
                s = st;
	}
	public CTextArea(String st){
		super();
		font = new Font();
                s = st;
                w = fillParent;
                h = wrapContent;
	}
        @Override
        public void paint(){
            //System.out.println(parent.dynw+" : "+parent.w);
		ArrayList<Bitmap> bmp = font.getBitmap(s,parent.w-padding-padding-(border?2:0));
		tg = new TextGraphics((bmp.get(0).getHeight()+lineSpacing)*bmp.size(),dynw);
                tg.setPaintable(true);
                int nx = padding+(border?1:0) , ny = padding+(border?1:0);
                for(int i=0;i<bmp.size();i++)
                {
                    tg.drawBitmap(bmp.get(i),nx,ny,true);
                    ny+=bmp.get(i).getHeight()+lineSpacing;
                }
                changed = false;
                tg.setPaintable(false);
        }
	public void setText(String s){
//		Bitmap bmp = font.getBitmap(s);
//		tg.setPaintable(true);
//		tg.clearScreen();
//		tg = new TextGraphics(bmp.getHeight(),bmp.getWidth());
//		tg.drawBitmap(bmp,0,0,true);
//		tg.setPaintable(false);
            this.s = s;
		changed = true;
	}
}