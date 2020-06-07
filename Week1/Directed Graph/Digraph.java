import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
public class Digraph
{
    private ArrayList<Set<Integer>> arr;  //ArrayList containing the adjacency list of every vertice
    private int V;
    private int E;
    public Digraph(int V) //Vertices are numbered 0,1,2,3,..V-1
    {
        this.V=V;
        arr=new ArrayList<>();
        E=0;
        for (int i=0;i<V;i++)
        {
            Set<Integer> s=new HashSet<>();
            arr.add(s);
        }
    }
    public void addEdge(int u,int v)  //Edge from u to v
    {
        if (u>=V || v>=V || u<0 || v<0)
        return;
        E++;
        arr.get(u).add(v);
    }
    public Iterable<Integer> adjacent(int v)
    {
        return arr.get(v);
    }
    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }
    public String toString()
    {
        String s="";
        s=s+"The graph has "+V()+" vertices and "+E()+" edges\n";
        for (int i=0;i<V();i++)
        {
            s=s+"Vertex "+i+" has an edge to these vertices : ";
            for (int j:adjacent(i))
            s=s+j+" ";
            s=s+"\n";
        }
        return s;
    }


    public static void main(String[] args)
    {
        Digraph dg=new Digraph(13);
        dg.addEdge(0,5);
        dg.addEdge(0,1);
        dg.addEdge(2,0);
        dg.addEdge(2,3);
        dg.addEdge(3,2);
        dg.addEdge(3,5);
        dg.addEdge(4,2);
        dg.addEdge(4,3);
        dg.addEdge(5,4);
        dg.addEdge(6,0);
        dg.addEdge(6,4);
        dg.addEdge(6,8);
        dg.addEdge(6,9);
        dg.addEdge(7,6);
        dg.addEdge(7,9);
        dg.addEdge(8,6);
        dg.addEdge(9,10);
        dg.addEdge(9,11);
        dg.addEdge(10,12);
        dg.addEdge(11,4);
        dg.addEdge(11,12);
        dg.addEdge(12,9);

        System.out.println(dg.toString());
    }
}