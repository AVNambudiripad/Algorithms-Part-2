import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
public class BFS<Key>
{
    private boolean[] visited;
    private int[] distance;
    private int[] edgeTo;
    private Key start;
    public BFS(Graph<Key> g,Key start)
    {
        visited=new boolean[g.V()];
        distance=new int[g.V()];
        edgeTo=new int[g.V()];
        this.start=start;
        Queue<Key> q=new LinkedList<>();
        q.add(start);
        distance[g.keyToInt(start)]=0;
        edgeTo[g.keyToInt(start)]=g.keyToInt(start);


        while (!q.isEmpty())
        {
            Key temp=q.remove();
            visited[g.keyToInt(temp)]=true;
            for (Key i:g.adjacent(temp))
            {
                if (!visited[g.keyToInt(i)])
                {
                    q.add(i);
                    visited[g.keyToInt(i)]=true;
                    distance[g.keyToInt(i)]=distance[g.keyToInt(temp)]+1;
                    edgeTo[g.keyToInt(i)]=g.keyToInt(temp);
                }
            }
        }
    }

    public boolean hasPath(Graph<Key> g,Key end) //Is there a path from start to end?
    {
        return visited[g.keyToInt(end)];
    }
    public Iterable<Key> path(Graph<Key> g,Key end)
    {
        if (!hasPath(g, end)) return null;
        Deque<Key> s=new LinkedList<Key>();
        Key i=end;
        while (!i.equals(start))
        {
            s.push(i);
            int j=g.keyToInt(i);
            j=edgeTo[j];
            i=g.intToKey(j);
        }
        s.push(start);
        return s;
    }
    public Integer getDistance(Graph<Key> g,Key end)
    {
        if (!hasPath(g, end)) return null;
        return distance[g.keyToInt(end)];
    }

    public static void main(String[] args)
    {
        Character[] a={'A','B','C','D','E','F','G','Z'};
        Graph<Character> g=new Graph<>(a);
        g.addEdge('A', 'B');
        g.addEdge('C', 'A');
        g.addEdge('A', 'F');
        g.addEdge('A', 'G');
        g.addEdge('G', 'E');
        g.addEdge('E', 'F');
        g.addEdge('D', 'E');
        g.addEdge('F', 'D');
        g.addEdge('B', 'A');

        g.print();
        System.out.println("\n");

        BFS<Character> obj=new BFS<>(g,'B');
        for (Character i:g.allNodes())
        {
            if (obj.hasPath(g, i))
            {
                System.out.print("There exists a path to node " +i+" with distance "+obj.getDistance(g, i)+"  ");
                for (Character j:obj.path(g,i))
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}