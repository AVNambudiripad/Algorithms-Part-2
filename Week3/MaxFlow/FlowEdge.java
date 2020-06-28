public class FlowEdge
{
    private final int u;
    private final int v;
    private final double capacity;
    private double flow;
    public FlowEdge(int u,int v,double c)
    {
        this.u=u;
        this.v=v;
        capacity=c;
        flow=0;
    }
    public int from()
    {
        return u;
    }
    public int to()
    {
        return v;
    }
    public int other(int vertex)
    {
        if (vertex!=u && vertex!=v)
        {
            System.out.println(vertex+"  "+toString());
            throw new IllegalArgumentException();
        }
        else if (vertex==u) return v;
        return u;
    }
    public double flow()
    {
        return flow;
    }
    public double capacity()
    {
        return capacity;
    }
    public double residualCapacity(int vertex)     //Residual Capacity towards vertex
    {
        if (vertex!=u && vertex!=v)
        throw new IllegalArgumentException();
        else if (v==vertex)
        {
            return capacity-flow;
        }
        return flow;
    }
    public void addFlow(int w,double delta)                  //Add flow towards vertex w
    {
        if (w==u) flow-=delta;
        else if (w==v) flow+=delta;
        else throw new IllegalArgumentException();
    }
    public String toString()
    {
        return "This is an edge from "+u+" to "+v+" with capacity "+capacity+" and flow "+flow;
    }
}