import java.util.ArrayList;
import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class WeightedDigraph
{
    //We're Using MultiSets are parallel edges are allowed
    private final int V;                         //Total vertices
    private ArrayList<Multiset<Edge>> a;         //Adjacency List  
    private Multiset<Edge> allEdges;             //All edges
    private int E;
    public WeightedDigraph(int V)
    {
        this.V=V;
        a=new ArrayList<>();
        for (int i=0;i<V;i++)
        a.add(HashMultiset.create());
        E=0;
        allEdges=HashMultiset.create();
    }
    public WeightedDigraph(String filename) throws FileNotFoundException
    {
        Scanner sc=new Scanner(new File(filename));
        V=sc.nextInt();
        a=new ArrayList<>();
        for (int i=0;i<V;i++)
        a.add(HashMultiset.create());
        E=0;
        allEdges=HashMultiset.create();


        while (sc.hasNextInt())
        {
            int u=sc.nextInt();
            int v=sc.nextInt();
            double w=sc.nextDouble();
            Edge e=new Edge(u,v,w);
            addEdge(e);
        }
    }
    public void addEdge(Edge e)
    {
        a.get(e.from()).add(e);
        E++;
        allEdges.add(e);
    }
    public Iterable<Edge> adjacent(int v)
    {
        return a.get(v);
    }
    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }
    public Iterable<Edge> allEdges()
    {
        return allEdges;
    }



    public static void main(String[] args) throws FileNotFoundException
    {
        WeightedDigraph obj=new WeightedDigraph(args[0]);
        System.out.println(obj.V()+"    "+obj.E());
        for (Edge e:obj.allEdges())
        {
            System.out.println(e.toString());
        }
    }
}