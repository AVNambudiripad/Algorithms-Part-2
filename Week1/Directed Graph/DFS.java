import java.util.Deque;
import java.util.LinkedList;

public class DFS
{
    private boolean[] visited;
    private int[] edgeTo;
    private int start;
    public DFS(Digraph g, int start)
    {
        visited=new boolean[g.V()];
        edgeTo=new int[g.V()];
        this.start=start;
        edgeTo[start]=start;
        dfs(g,start);
    }
    private void dfs(Digraph g, int v)
    {
        visited[v]=true;

        for (int i:g.adjacent(v))
        {
            if (!visited[i])
            {
                edgeTo[i]=v;
                dfs(g,i);
            }
        }
    }
    public boolean hasPath(int v)  //Is there path from start to v
    {
        return visited[v];
    }
    public Iterable<Integer> dfsPath(int v) //Path to v from start
    {
        if (!hasPath(v)) return null;
        Deque<Integer> q=new LinkedList<>();
        int temp=v;
        while (temp!=start)
        {
            q.addFirst(temp);
            temp=edgeTo[temp];
        }
        q.addFirst(start);
        return q;
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

        DFS obj=new DFS(dg,2);
        for (int i=0;i<dg.V();i++)
        if (obj.hasPath(i))
        {
            System.out.print("There is a path to "+i+ " :");
            for (int j:obj.dfsPath(i))
            System.out.print(" "+j);
            System.out.println();
        }
        else
        System.out.println("There is no path to "+i);
    }
}