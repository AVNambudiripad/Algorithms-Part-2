import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
public class BFS
{
    private boolean[] visited;
    private Integer[] distance;
    private int[] edgeTo;
    private int start;
    public BFS(Digraph g, int start)
    {
        Queue<Integer> q=new LinkedList<>();
        this.start=start;
        visited=new boolean[g.V()];
        visited[start]=true;
        distance=new Integer[g.V()];
        distance[start]=0;
        edgeTo=new int[g.V()];
        edgeTo[start]=start;
        q.add(start);
        bfs(g,q);
    }
    private void bfs(Digraph g, Queue<Integer> q)
    {
        int v=q.remove();
        for (int i:g.adjacent(v))
        {
            if (!visited[i])
            {
                edgeTo[i]=v;
                distance[i]=distance[v]+1;
                q.add(i);
                visited[i]=true;
            }
        }
        if (!q.isEmpty())
        bfs(g,q);
    }
    public boolean hasPath(int v)
    {
        return visited[v];
    }
    public Iterable<Integer> bfsPath(int v)
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
    public int distance(int v)
    {
        return distance[v];
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

        BFS obj=new BFS(dg,6);
        for (int i=0;i<dg.V();i++)
        if (obj.hasPath(i))
        {
            System.out.print("There is a path to "+i+", which has length "+obj.distance(i)+" :");
            for (int j:obj.bfsPath(i))
            System.out.print(" "+j);
            System.out.println();
        }
        else
        System.out.println("There is no path to "+i);
    }
}