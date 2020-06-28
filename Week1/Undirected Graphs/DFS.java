import java.util.Deque;
import java.util.LinkedList;
public class DFS<Key> 
{
    private int[] edgeTo;
    private boolean[] visited;
    private Key start;
    public DFS(Graph<Key> g,Key start)
    {
        this.start=start;
        visited=new boolean[g.V()];
        edgeTo=new int[g.V()];
        dfs(g,start);
    }    
    private void dfs(Graph<Key> g,Key k)
    {
        visited[g.keyToInt(k)]=true;
        for (Key i:g.adjacent(k))
        {
            if (!visited[g.keyToInt(i)])  //if i has not been visited
            {
                edgeTo[g.keyToInt(i)]=g.keyToInt(k);
                dfs(g,i);
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

    public static void main(String[] args)
    {
        Character[] a={'A','B','C','D','E','F','G'};
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

        DFS<Character> obj=new DFS<>(g,'B');
        for (Character i:g.allNodes())
        {
            if (obj.hasPath(g, i))
            {
                System.out.print("There exists a path to node " +i+"    ");
                for (Character j:obj.path(g,i))
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}