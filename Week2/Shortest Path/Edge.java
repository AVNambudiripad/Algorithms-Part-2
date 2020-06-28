public class Edge
{
    private final int u;
    private final int v;
    private final double weight;
    public Edge(int u,int v,double w)
    {
        this.u=u;
        this.v=v;
        weight=w;
    }
    public int from()
    {
        return u;
    }
    public int to()
    {
        return v;
    }
    public double weight()
    {
        return weight;
    }
    @Override
    public String toString()
    {
        return "Weighted edge from "+from()+" to "+to()+" with weight "+weight();
    }
}