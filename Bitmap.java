import java.awt.Image.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class Bitmap
{
	File f;
	String FName , ext;
	char[][] img;

	public Bitmap(String st){
		ext = st.substring(st.lastIndexOf('.')+1,st.length());
		FName = st.substring(0,st.lastIndexOf('.'));

		f = new File(st);
		if(ext.equalsIgnoreCase("jbmp"))
		{
			Scanner r = null;
			try{r = new Scanner(f);}catch(Exception e){}
			int w = r.nextInt();
			int h = r.nextInt();
			img = new char[h][w];
			String l = r.nextLine();
			int i=0;
			while(r.hasNextLine())
			{
				l = r.nextLine();
				//System.out.println(i+l);
				for(int j=0;j<l.length();j++)
				{
					img[i][j] = l.charAt(j);
				}i++;
			}
		}
		else
		{
		}
	}
        
        public Bitmap(ArrayList<char[]> c){
            int l = c.size();
            for(int i=0;i<l;i++)
            {
                img[i] = (char[])c.get(i);
            }
        }
	public Bitmap(String[] c){
            int l = c.length;
	    img = new char[c.length][c[0].length()];
            for(int i=0;i<l;i++)
            {
		String s=c[i];
                img[i] = new char[s.length()];
		for(int j=0;j<img[i].length;j++)
		{
		    img[i][j] = s.charAt(j);
		}
		
            }
	}
	public Bitmap(char[][] c){
            int l = c.length;
	    img = new char[c.length][c[0].length];
            for(int i=0;i<l;i++)
            {
                img[i] = new char[c[i].length];
		for(int j=0;j<img[i].length;j++)
		{
		    img[i][j] = c[i][j];
		}
            }
	}
	public int getWidth(){return img[0].length;}
	public int getHeight(){return img.length;}
}