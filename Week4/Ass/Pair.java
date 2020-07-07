public class Pair
{
    public final int x;
    public final int y;
    public Pair(int a,int b)
    {
        x=a;
        y=b;
    }
    public boolean equal(int a,int b)
    {
        return x==a && y==b;
    }
}