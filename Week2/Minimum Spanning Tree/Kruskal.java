import java.util.Queue;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class Kruskal
{
    private Queue<Edge> mst;
    private UnionFind uf;
    private PriorityQueue<Edge> pq;
    private double weight;
    public Kruskal(WeightedGraph g)
    {
        weight=0;
        mst=new LinkedList<>();
        uf=new UnionFind(g.V());
        pq=new PriorityQueue<>();
        for (Edge i:g.allEdges())
        pq.add(i);

        while (mst.size() < g.V() -1)
        {
            Edge e=pq.remove();
            int v=e.vertex();
            int w=e.other(v);
            if (!uf.find(v,w))
            {
                uf.union(v,w);
                mst.add(e);
                weight+=e.weight();
            }
        }
    }
    public Iterable<Edge> MST()
    {
        return mst;
    }
    public double weight()
    {
        return weight;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        WeightedGraph obj=new WeightedGraph(args[0]);
        System.out.println(obj.V()+"    "+obj.E());
        for (Edge e:obj.allEdges())
        {
            System.out.println(e.toString());
        }

        System.out.println();

        Kruskal k=new Kruskal(obj);
        for (Edge e:k.MST())
        {
            System.out.println(e.toString());
        }
        System.out.println("The total weight of the MST is "+k.weight());
    }
}