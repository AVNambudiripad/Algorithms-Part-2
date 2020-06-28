import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class WeightedGraph
{
    private final int V;
    private ArrayList<TreeSet<Edge>> a;         //Adjacency List of each vertex
    private int E=0;
    public WeightedGraph(int V)      //Make a new empty graph
    {
        this.V=V;
        a=new ArrayList<>();
        for (int i=0;i<V;i++)
        {
            TreeSet<Edge> t=new TreeSet<>();
            a.add(t);
        }
    }
    public WeightedGraph(String filename) throws FileNotFoundException
    {
        Scanner sc=new Scanner(new File(filename));
        V=sc.nextInt();  //First Line is V
        a=new ArrayList<>();
        for (int i=0;i<V;i++)
        {
            TreeSet<Edge> t=new TreeSet<>();
            a.add(t);
        }

        // Rest are edges of the graph
        while (sc.hasNextDouble())
        {
            int v=sc.nextInt();
            int w=sc.nextInt();
            double weight=sc.nextDouble();
            Edge e=new Edge(v,w,weight);
            addEdge(e);
        }
    }
    public void addEdge(Edge e)
    {
        int v=e.vertex();
        int w=e.other(v);
        if (v<0 || v>=V || w<0 || w>=V)
        throw new IllegalArgumentException();
        E++;
        //Add the same Edge object to both v and w
        a.get(v).add(e);
        a.get(w).add(e);
    }

    public Iterable<Edge> adjacent(int v)
    {
        return a.get(v);
    }
    public Iterable<Edge> allEdges()
    {
        TreeSet<Edge> t=new TreeSet<>();
        for (TreeSet<Edge> i:a)
        t.addAll(i);
        return t;
    }
    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }


    public static void main(String[] args) throws FileNotFoundException
    {
        WeightedGraph obj=new WeightedGraph(args[0]);
        System.out.println(obj.V()+"    "+obj.E());
        for (Edge e:obj.allEdges())
        {
            System.out.println(e.toString());
        }
    }
}