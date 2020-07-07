import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.LinkedList;
public class Digraph 
{
    private ArrayList<TreeSet<Integer>> al;
    private final int V;
    private boolean[] marked;
    public Digraph(int V)        //Initialises empty digraph
    {
        this.V=V;
        al=new ArrayList<>();
        for (int i=0;i<V;i++)
        al.add(new TreeSet<>());
    }
    public void addEdge(int u,int v)
    {
        al.get(u).add(v);
    }
    public Iterable<Integer> adjacent(int u)
    {
        return al.get(u);
    }
    public Collection<Integer> reachable(Iterable<Integer> sources)
    {
        marked=new boolean[V];
        Queue<Integer> q=new LinkedList<>();
        for (int i:sources)
        {
            if (!marked[i])
            {
                q.add(i);
                dfs(q,i);
            }
        }
        return q;
    }
    private void dfs(Queue<Integer> q, int v)
    {
        marked[v]=true;
        for (int i:adjacent(v))
        {
            if (!marked[i])
            {
                q.add(i);
                dfs(q,i);
            }
        }
    }
    public void print()
    {
        System.out.println("This graph has "+V+" vertices\nEdges:");
        for (int i=0;i<V;i++)
        {
            for (int j:adjacent(i))
            System.out.println(i+"->"+j);
        }
    }
}