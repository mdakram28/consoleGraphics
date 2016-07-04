
import java.util.Arrays;


public class TextGraphics {
    double dx=1,dy=1;
    char[][] screen;
    char ch = '*';
    String s = "";
    boolean painted = false;
    boolean paintable=false;
    boolean transparent = false;
    public TextGraphics(int h,int w,char c){
        screen = new char[(int)(h*dy)][(int)(w*dx)];
        ch=c;
	clearScreen();
    }
    public TextGraphics(int h,int w){
        screen = new char[(int)(h)][(int)(w)];
	clearScreen();
    }
    public TextGraphics(int h,int w,char c,double ax,double ay){
        dx=ax;dy=ay;
        screen = new char[(int)(h*dy)][(int)(w*dx)];
        ch=c;
	clearScreen();
    }
    public void setPixel(int x,int y){
        try{
            screen[(int)(y*dy)][(int)(x*dx)] = ch;
        }
        catch(Exception e){}
    }
    public void setPixel(int x,int y,char c){
        try{
            screen[(int)(y*dy)][(int)(x*dx)] = c;
        }
        catch(Exception e){}
    }
    public void clearPixel(int x,int y){
        try{
            screen[(int)(y*dy)][(int)(x*dx)] = ' ';
            painted = false;
        }
        catch(Exception e){}
    }
    public String getString(){
        if(painted || paintable){return s;}
	paint();
        return s;
    }
    public void setPaintable(boolean b){
        paintable = b;
    }
    public String getString(int x,int y,int w,int h){
        h+=y;
        h = h>screen.length?screen.length:h;
        w = (x+w)>(screen[0].length)?(screen[0].length-x):x;
        String ret="";
        for(int i=y;i<=h;i++)
        {
            ret+=new String(screen[i],x,w)+"\n";
        }
        
        return ret;
    }
    public void drawCircle(int x,int y,double r,double a,char c){
        double i=0;
        for(;i<=90;i+=a)
        {
            double ar = i*Math.PI/180;
            int x1 = (int)(r*Math.cos(ar));
            int y1 = (int)(r*Math.sin(ar));
            setPixel(x+x1,y+y1,c);
            setPixel(x-x1,y+y1,c);
            setPixel(x+x1,y-y1,c);
            setPixel(x-x1,y-y1,c);
        }
            painted = false;
    }
    public void clearScreen(){
        for(int i=0;i<screen.length;i++)
        {
            Arrays.fill(screen[i], ' ');
        }
            painted = false;
    }
    public void paint(){
        s="";
        int w = screen[0].length;
        int h = screen.length;
        for(int i=0;i<h;i++)
        {
            s+=new String(screen[i])+"\n";
        }
        painted = true;
    }
    void drawRectangle(int x1, int y1, int x2, int y2,char c) {
        if(x1>x2){
            int temp = x1;
            x1 = x2;
            x2=temp;
        }
        if(y1>y2){
            int temp = y1;
            y1 = y2;
            y2=temp;
        }
        
        for(int i=x1;i<=x2;i++)
        {
            setPixel(i,y1,c);
        }
        for(int i=y1+1;i<y2;i++)
        {
            setPixel(x1,i,c);
            setPixel(x2,i,c);
        }
        for(int i=x1;i<=x2;i++)
        {
            setPixel(i,y2,c);
        }
            painted = false;
    }
    void drawFilledRectangle(int x1, int y1, int x2, int y2,char c) {
        if(x1>x2){
            int temp = x1;
            x1 = x2;
            x2=temp;
        }
        if(y1>y2){
            int temp = y1;
            y1 = y2;
            y2=temp;
        }
        for(int i=y1;i<=y2;i++)
   	{
	    for(int j=x1;j<=x2;j++)
	    {
        	setPixel(i,j,c);
	    }
        }
            painted = false;
    }
    void drawBitmap(Bitmap bm,int x,int y,boolean trans){
	for(int i=0;i<bm.img.length;i++)
	{
		for(int j=0;j<bm.img[0].length;j++)
		{
			if(trans && bm.img[i][j]!=' ')
			{
				setPixel(x+j,y+i,bm.img[i][j]);
			}
			else
			{
				setPixel(x+j,y+i,bm.img[i][j]);
			}
		}
	}
            painted = false;
    }
    
    public int getWidth(){int ret=0;try{ret = screen[0].length;}catch(Exception e){}return ret;}
    public int getHeight(){return screen.length;}
    public void drawTextGraphics(TextGraphics tg,int x,int y){

		//System.out.println("old : "+getString());
	for(int i=0;i<tg.getHeight();i++)
	{
		for(int j=0;j<tg.getWidth();j++)
		{
			if(tg.transparent && (tg.screen[i][j]==' ' || tg.screen[i][j]==0))
			{
				//System.out.println("\nwoila found a blank space....!!");
			}
			else
			{
				setPixel(x+j,y+i , tg.screen[i][j]) ;
			}
			//try{System.out.print((int)screen[y+i][x+j]);}catch(Exception e){e.printStackTrace(System.out);}
		}

		//System.out.println(getString());
	}
	
	painted = false;
		//System.out.println("new : "+getString());
    }
    public void trimHeight(int maxy){
	char[][] tempp = new char[maxy+1][screen[0].length];
	for(int i=0;i<tempp.length;i++)
	{
		tempp[i] = screen[i];
	}
	screen = tempp;
	painted = false;
    }
    public void trimWidth(int maxx){
	//System.out.println("*************");
	char[][] tempp = new char[screen.length][maxx+1];
	//System.out.println("*************"+tempp.length+" : "+tempp[0].length);
	//System.out.println(getString());
	for(int i=0;i<tempp.length;i++)
	{
		for(int j=0;j<tempp[0].length;j++)
		{
			tempp[i][j] = screen[i][j];
		}
		
	}
	screen = tempp;
	painted = false;
    }
}
