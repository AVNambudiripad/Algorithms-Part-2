import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

public class Prim   //Eager version of Prim's Algorithm
{
    private Queue<Edge> mst;
    private double weight;
    public Prim(WeightedGraph g)
    {
        mst=new LinkedList<>();
        weight=0;
        boolean[] inserted=new boolean[g.V()];    //Has vertex 'v' been added to mst yet

        int curr=0;           //The vertex being currently considered
        IndexPriorityQueue ipq=new IndexPriorityQueue(g.V());
        while (mst.size() < g.V()-1 )
        {
            inserted[curr]=true;
            for (Edge e:g.adjacent(curr))
            {
                int v=e.other(curr);
                if (!inserted[v])
                {
                    ipq.insert(v, e);
                }
            }

            BindVertexAndEdgeTogether obj=ipq.remove();
            mst.add(obj.edge);
            weight+=obj.edge.weight();
            curr=obj.vertex;
        }

    }
    public Iterable<Edge> MST()
    {
        return mst;
    }
    public double mstWeight()
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

        Prim k=new Prim(obj);
        for (Edge e:k.MST())
        {
            System.out.println(e.toString());
        }
        System.out.println("The total weight of the MST is "+k.mstWeight());
    }
}