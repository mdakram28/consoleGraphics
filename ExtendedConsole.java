import java.util.*;
public class ExtendedConsole extends TextGraphics implements Runnable{
    int w,h,delay;
    String clear="";
    boolean running = false;
    boolean alive = true;
    Layout l = new CoordinatesLayout(getHeight(),getWidth());

    public ExtendedConsole(int h1,int w1,char ch,int hertz,int refresh_height){
        super(h1,w1,ch,1,1);
        w=w1;
        h=h1;
        delay = 1000/hertz;
	
	String tcls="";
        for(int i=0;i<w;i++)
        {
            tcls+="\n";
        }
        for(int i=0;i<h;i++)
        {
            clear+=tcls;
        }
        new Thread(this).start();
    }
    public void setLayout(Layout tl){
	l=tl;
	l.init(this);
    }
    @Override
    public void run(){
        try {
	while(alive)
	{
        	while(running)
        	{
                	System.out.println(l.getTextGraphics().getString());
                	Thread.sleep(delay);
        	}
	}
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    public void pause(){
	running = false;
    }
    public void start(){
	running = true;
    }
    public void kill(){
	running = false;
	alive = false;
    }
}