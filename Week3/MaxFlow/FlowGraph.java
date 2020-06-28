import java.util.HashSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
public class FlowGraph
{
    private ArrayList<HashSet<FlowEdge>> ar;
    private final int V;
    public FlowGraph(int V)
    {
        this.V=V;
        ar=new ArrayList<>(V);
        for (int i=0;i<V;i++)
        ar.add(new HashSet<FlowEdge>());
    }
    public FlowGraph(String filename)
    {
        In in=new In(filename);
        V=in.readInt();
        ar=new ArrayList<>();
        for (int i=0;i<V;i++)
        ar.add(new HashSet<FlowEdge>());
        
        while(in.hasNextLine())
        {
            int u=in.readInt();
            int v=in.readInt();
            double capacity=in.readDouble();
            FlowEdge e=new FlowEdge(u,v,capacity);
            addEdge(e);
        }
    }
    public void addEdge(FlowEdge e)
    {
        ar.get(e.from()).add(e);
        ar.get(e.to()).add(e);
    }
    public Iterable<FlowEdge> adjacent(int v)
    {
        return ar.get(v);
    }
    public int V()
    {
        return V;
    }
    public void print()
    {
        System.out.println("The graph has "+V+" vertices");
        for (int i=0;i<V;i++)
        {
            System.out.println("\nVertex "+i+" has the following adjacent edges");
            for (FlowEdge e:adjacent(i))
            System.out.println(e.toString());
        }
        System.out.println("\n");
    }
    
    
    public static void main(String[] args)
    {
        FlowGraph g=new FlowGraph(args[0]);
        for (FlowEdge e:g.adjacent(4))
        e.addFlow(e.other(4),0.5);
        g.print();
    }
}