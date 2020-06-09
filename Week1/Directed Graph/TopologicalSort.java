import java.util.Deque;
import java.util.LinkedList;
public class TopologicalSort
{
    private boolean[] visited;
    private Deque<Integer> d;
    TopologicalSort(Digraph g) //g MUST be acyclical
    {
        visited=new boolean[g.V()];
        d=new LinkedList<>();
        for (int i=0;i<g.V();i++)
        {
            if (!visited[i])
            dfs(g,i);
        }
    }
    private void dfs(Digraph g,int v)
    {
        visited[v]=true;
        for (int i:g.adjacent(v))
        {
            if (!visited[i])
            {
                dfs(g,i);
            }
        }
        d.addFirst(v);
    } 

    public Iterable<Integer> topological()
    {
        return d;
    }

    public static void main(String[] args)
    {

        Digraph g1=new Digraph(7);
        g1.addEdge(0, 5);
        g1.addEdge(0, 2);
        g1.addEdge(0, 1);
        g1.addEdge(3, 6);
        g1.addEdge(3, 5);
        g1.addEdge(3, 4);
        g1.addEdge(5, 2);
        g1.addEdge(6, 4);
        g1.addEdge(6, 0);
        g1.addEdge(3, 2);
        g1.addEdge(1, 4);

        System.out.println(g1.toString());

        TopologicalSort obj=new TopologicalSort(g1);
        for (int i:obj.topological())
        {
            System.out.print(i+"  ");
        }
        System.out.println();
    }
}