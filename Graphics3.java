
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graphics3 {
    public static void main(String[] args){
	
        ExtendedConsole cg = new ExtendedConsole(50,50,'#',10,0);
        
	Font f = null;
	try{f = new Font("Fonts\\chars.png",0,2);}catch(Exception e){e.printStackTrace(System.out);}
        
        LinearLayout fl = new LinearLayout();
        LinearLayout ll1 = new LinearLayout(Cobject.fillParent,30,LinearLayout.vertical);
        LinearLayout ll2 = new LinearLayout(Cobject.fillParent,Cobject.fillParent,LinearLayout.vertical);
        
        cg.setLayout(fl);
        
        fl.setBordered(true);
        ll1.setBordered(true);
        ll2.setBordered(true);
        
        ll2.add(new CoordinatesLayout(Cobject.fillParent,Cobject.fillParent));
        ll2.add(new CoordinatesLayout(Cobject.fillParent,Cobject.fillParent));
        ll2.add(new CoordinatesLayout(Cobject.fillParent,Cobject.fillParent));
        ll2.add(new CoordinatesLayout(Cobject.fillParent,Cobject.fillParent));
        ll2.add(new CoordinatesLayout(Cobject.fillParent,Cobject.fillParent));
        ll2.add(new CoordinatesLayout(Cobject.fillParent,Cobject.fillParent));
        
        ll1.add(new CLabel("My name is Akram"));
        ll1.add(new CLabel("My name is Akram"));
        ll1.add(new CLabel("My name is Akram"));
        ll1.add(new CLabel("My name is Akram"));
       	f.spacing = 2;
        fl.add(ll1);
        fl.add(ll2);
        CTextArea ct = new CTextArea("MYNAMEISMOHDAKRAMASNARI");
        ct.font = f;
        ll1.add(ct);
	cg.start();

            //System.out.println("startedcdfvdgfdgfdg................................");
while(true)
	for(char i='A';i<='Z';i++)
	{
		//ct.setText(i+"");
		try{Thread.sleep(300);}catch(Exception e){}
	}
    }
}

