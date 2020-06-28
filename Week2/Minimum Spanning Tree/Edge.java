public class Edge implements Comparable<Edge>
{
    private final int v;                //Edge from v to w
    private final int w;
    private final double weight;        //Weight of the edge
    public Edge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public int vertex()   //Returns either of the two vertices
    {
        return v;
    }
    public int other(int vertex)  //Returns the other end of vertex
    {
        if (vertex==v) return w;
        else if (vertex==w) return v;
        else throw new IllegalArgumentException();
    }
    public int compareTo (Edge e)
    {
        if (e.weight() < this.weight()) return 1;
        else if (e.weight() > this.weight() ) return -1;
        else return 0;
    }
    public double weight()
    {
        return weight;
    }
    public String toString()
    {
        return "This is a weighted edge from "+this.v+" to "+this.w+" with weight "+this.weight;
    }
}