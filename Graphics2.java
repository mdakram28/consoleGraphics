
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graphics2 {
    public static void main(String[] args){
	
        ExtendedConsole cg = new ExtendedConsole(500,500,'#',1,0);
        
	Font f = null;
	try{f = new Font("Fonts\\chars_large.png",0,2);}catch(Exception e){e.printStackTrace(System.out);}
        
	CoordinatesLayout cl = new CoordinatesLayout();
        cg.setLayout(cl);
        
	cl.add(new CLabel("HELLO WORLD",f),0,0);
	cg.start();

            //System.out.println("startedcdfvdgfdgfdg................................");
while(true);
    }
}

