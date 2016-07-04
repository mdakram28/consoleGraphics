
import java.util.ArrayList;

public class alpha {
    String[] rows ;
    char c;
    public alpha(char ch,int h){
        c = ch;
        rows = new String[h];
        for(int i=0;i<h;i++)
        {
            rows[i] = "";
        }
    }
    public void addCol(char ch[]){
        for(int i=0;i<ch.length;i++)
        {
            rows[i] += ch[i];
        }
    }
    
    public String getString(){
        String ret="";
        for(int i=0;i<rows.length;i++)
        {
            ret+=rows[i];
            if(i!=rows.length-1)ret+="\n";
        }
        return ret;
    }
    
    public String getString(int h){
        if(h<=0){return getString();}
        String ret="";
        int l = rows[0].length()*h/rows.length;
        for(int i=0;i<h;i++)
        {
            String r = rows[rows.length*i/h];
            for(int j=0;j<l;j++)
            {
                ret+=r.charAt(r.length()*j/l);
            }
            if(i!=h-1)ret+="\n";
        }
        return ret;
    }
    
    public void resize(int h){
        String[] trows = new String[h];
        int l = rows[0].length()*h/rows.length;
        for(int i=0;i<h;i++)
        {
	    String ret="";
            String r = rows[rows.length*i/h];
            for(int j=0;j<l;j++)
            {
                ret+=r.charAt(r.length()*j/l);
            }
	    trows[i] = ret;
        }
	rows = trows;
    }
    public boolean isChar(char ch){
	return (c==ch);
    }
    public String getRow(int rowNum){
	return rows[rowNum];
    }
}






