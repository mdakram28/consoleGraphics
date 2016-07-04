import java.awt.Image.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Font {
    
    ArrayList<alpha> ch = new ArrayList<alpha>();
    alpha def = null;
    BufferedImage img;
    int spacing=0;
    public static void main(String[] args)throws Exception{
        Font txt = new Font(args[0],11,1);
    }
    public Font(String fName,int h,int spacing) throws Exception{
        charsScan(fName);
	if (h>0)
        for(int i=0;i<ch.size();i++)
        {
            ch.get(i).resize(h);
        }
	initDefaultChar();
    }
    public Font(){
	for(int i=1;i<200;i++)
	{
		alpha t = new alpha((char)i,1);
		char[] tc = new char[1];
		tc[0] = (char)i;
		t.addCol(tc);
		ch.add(t);
	}
	initDefaultChar();
	spacing = 0;
    }
    public void initDefaultChar(){
	char[] c1 = new char[ch.get(0).rows.length];
	char[] c2 = new char[ch.get(0).rows.length];
	c1[0] = '?';
	c2[0] = '?';
	for(int i=1;i<c1.length-1;i++)
	{
		c1[i] = '?';
		c2[i] = ' ';
	}
	c1[c1.length-1] = '?';
	c2[c2.length-1] = ' ';


	def = new alpha('?',c1.length);
	def.addCol(c1);
	for(int i=0;i<c1.length/2;i++)
	{
		def.addCol(c2);
	}
	def.addCol(c1);
    }
    final void charsScan(String fN) throws Exception{
        File f = new File(fN);
        img = ImageIO.read(f);
        int color=0;
        int h = getHeight();
        char[] col = new char[h];
        char current_ch='A';
        for(int c=0;;c++)
        {
            for(int r=0;r<h;r++)
            {
                color = col(c,r,false);
                col[r] = color==0?' ':'*';
            }
            if(!isEmpty(col))
            {
                ch.get(ch.size()-1).addCol(col);
            }
            else
            {
                int colCode = col(c,0,true);
                if(colCode==2)
                {
                    int add = getAdd(c,h);
                //System.out.print(":::-");
                //System.out.print(col);
                    if(add == 0)
                    {
                        current_ch++;
                        ch.add(new alpha(current_ch,h));
                    }
                    else
                    {
                        current_ch = (char)add;
                        ch.add(new alpha(current_ch,h));
                    }
                //System.out.println(current_ch+" : "+(int)current_ch);
                }
                else if(colCode==3)
                {
                    break;
                }
            }
        }
    }
    public int col(int x,int y,boolean mode)throws Exception{
        int clr=  img.getRGB(x,y); 
        int  red   = (clr & 0x00ff0000) >> 16;
        int  green = (clr & 0x0000ff00) >> 8;
        int  blue  =  clr & 0x000000ff;
//        System.out.println(red+" | "+green+" | "+blue);
        int ret=0;
        if(red==250 && green==250 && blue==250)
        {
            ret=0;
        }
        if(mode && red>200 && green<50 && blue<50)
        {
            ret=2;
        }
        if(mode && red<50 && green<50 && blue>200)
        {
            ret=3;
        }
        if(red<50 && green<50 && blue<50)
        {
            ret=1;
        }
        return ret;
    }
    public int getHeight(){
        int ret = 0;
        try{
            while(true)
            {
                img.getRGB(0,ret);
                ret++;
            }
        }
        catch(Exception e){}
        return ret;
    }
    private boolean isEmpty(char[] col) {
        boolean ret = true;
        for(int i=0;i<col.length;i++)
        {
            //System.out.println(i+" : "+col[i]);
            if(col[i]!= ' ')
            {
                ret = false;
                break;
            }
        }
        return ret;
    }
    private int getAdd(int c,int h) {
        int ret=0;
        for(int i=1;i<h;i++)
        {
            int clr=  img.getRGB(c,i); 
            int  red   = (clr & 0x00ff0000) >> 16;
            int  green = (clr & 0x0000ff00) >> 8;
            int  blue  =  clr & 0x000000ff;
            if(red<50 && green>200 && blue<50)
            {
                ret+=Math.pow(2, i-1);
                //System.out.println(i);
            }
        }
        return ret;
    }
    public Bitmap getBitmap(String s){
        alpha[] temp = new alpha[s.length()];
        for(int i=0;i<s.length();i++)
        {
            temp[i] = getAlpha(s.charAt(i));
        }
	String space="";
	for(int i=0;i<spacing;i++)
	{
		space+=" ";
	}
	String[] bmpRows = new String[temp[0].rows.length];
	for(int i=0;i<temp[0].rows.length;i++)
	{
		String row="";
		for(int j=0;j<temp.length;j++)
		{
			row+=temp[j].getRow(i);
			if(j!=temp.length)row+=space;
		}
		
		bmpRows[i] = row;
	}
        return new Bitmap(bmpRows);
    }
    
    public ArrayList<Bitmap> getBitmap(String s , int l){l--;
        alpha[] temp = new alpha[s.length()];
        for(int i=0;i<s.length();i++)
        {
            temp[i] = getAlpha(s.charAt(i));
        }
	String space="";
        ArrayList<Bitmap> list = new ArrayList<Bitmap>();
	for(int i=0;i<spacing;i++)
	{
		space+=" ";
	}
        String[] t;
        for(int i=0;i<s.length();)
        {
            t = new String[temp[0].rows.length];
            Arrays.fill(t, "");
            //System.out.println("total : "+t[0].length()+" : "+l);
            while((t[0].length()+spacing+(i<(s.length()-1)?(temp[i+1].getRow(0).length()):0))<l)
            {
                //System.out.println(i);
                for(int j=0;j<temp[i].rows.length;j++)
                {
                    t[j]+=temp[i].getRow(j)+space;
                }
                i++;
                if(i>=s.length())break;
            }
            list.add(new Bitmap(t));
        }
        return list;
    }
    
    
    public alpha getAlpha(char c){
	alpha ret = null;
	for(int i=0;i<ch.size();i++)
	{
	    if(ch.get(i).isChar(c))
	    {
		ret = ch.get(i);
		break;
	    }
	}
	return ret!=null?ret:def;
    }
}