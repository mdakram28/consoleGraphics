
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graphics {
    public static void main(String[] args){

	Font f = null;
	try{f = new Font("Fonts\\chars.png",0,2);}catch(Exception e){e.printStackTrace(System.out);}
	Bitmap txt = f.getBitmap("AK");
	
        CommandGraphics cg = new CommandGraphics(txt.getHeight()+2,txt.getWidth()+50,'#',5,0);
        cg.setPaintable(true);
	cg.start();
	int di=2;
	for(int i=0;;i+=di)
	{
                cg.clearScreen();
		cg.drawBitmap(txt,i,1,true);
                cg.drawRectangle(0,0,cg.getWidth()-1,cg.getHeight()-1,'*');
                cg.paint();
		if(i==cg.getWidth()-txt.getWidth())di=-2;
		if(i==0)di=2;
		try{Thread.sleep(50);}catch(Exception e){}
	}
    }
}