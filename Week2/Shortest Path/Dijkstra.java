import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.util.Deque;

public class Dijkstra     //Kinda like BFS
{
    private double[] distance;
    private Edge[] edgeTo;
    private int source;
    public Dijkstra(WeightedDigraph g, int source)
    {
        this.source=source;
        distance=new double[g.V()];
        edgeTo=new Edge[g.V()];
        for (int i=0;i<g.V();i++)
        distance[i]=Double.POSITIVE_INFINITY;

        distance[source]=0;
        edgeTo[source]=null;
        int curr=source;

        IndexMinPriorityQueue q=new IndexMinPriorityQueue(g.V());
        do
        {
            for (Edge e:g.adjacent(curr))
            {
                int v=e.to();
                if (distance[curr] + e.weight() < distance[v])
                {
                    distance[v]=distance[curr] + e.weight();
                    edgeTo[v]=e;
                    q.insert(v, distance[v]);
                }
            }
            curr=q.remove().vertex;
        }while(!q.isEmpty());
    }

    public double distTo(int v)   //distance from source to v
    {
        return distance[v];
    }
    public Iterable<Edge> pathTo(int v)
    {
        Edge e=edgeTo[v];
        if (e==null) return null;
        Deque<Edge> q=new LinkedList<>();
        while (e!=null)
        {
            q.addFirst(e);
            e=edgeTo[e.from()];
        }
        return q;
    }
    public int source()
    {
        return source;
    }



    public static void main(String[] args) throws FileNotFoundException
    {
        WeightedDigraph obj=new WeightedDigraph(args[0]);
        System.out.println(obj.V()+"    "+obj.E());
        for (Edge e:obj.allEdges())
        {
            System.out.println(e.toString());
        }


        Dijkstra d=new Dijkstra(obj, 0);
        for (int i=0;i<obj.V();i++)
        {
            System.out.println("The path from "+d.source()+" to "+i+" has distance "+d.distTo(i));
            Iterable<Edge> iter=d.pathTo(i);
            if (iter==null) System.out.println("There is no path");
            else
            for (Edge e:iter)
            System.out.println(e.toString());
            System.out.println("\n");
        }
    }
}