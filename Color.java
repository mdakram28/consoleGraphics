public class Color
{
	int red,green,blue;
	public Color(int clr)
	{
        	red   = (clr & 0x00ff0000) >> 16;
        	green = (clr & 0x0000ff00) >> 8;
        	blue  =  clr & 0x000000ff;
	}

	public Color(int rt , int gt , int bt)
	{
		red=rt;green=gt;blue=bt;
	}
	
	public int getRange(Color[] clr)
	{
		int[] xi = new int[clr.length];
		int smallest = 0;
		for(int i=0;i<clr.length;i++)
		{
			int r = (int)Math.abs(clr[i].red-red);
			int g = (int)Math.abs(clr[i].green-green);
			int b = (int)Math.abs(clr[i].blue-blue);
			int x = r+g+b;

			xi[i] = x;
			if(x<xi[smallest])
			{
				smallest = i;
			}
		}
		return smallest;
	}
}