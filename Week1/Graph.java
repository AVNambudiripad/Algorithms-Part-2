import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
public class Graph<Key>
{
    private HashMap<Key,HashSet<Key>> adj; //Adjacent List
    private int V;
    private int E;
    private ArrayList<Key> ar;
    public Graph(Key[] arr)
    {
        V=arr.length;
        E=0;
        adj=new HashMap<>();
        ar=new ArrayList<Key>();
        for (int i=0;i<V;i++)
        {
            adj.put(arr[i],new HashSet<Key>());
            ar.add(arr[i]);
        }
    }
    public int E()   {  return E;  }
    public int V()   {  return V;  }

    public void addEdge(Key v1,Key v2)
    {
        HashSet<Key> h1=adj.get(v1);
        HashSet<Key> h2=adj.get(v2);
        if (h1==null || h2==null)
        return;
        E++;
        h1.add(v2);
        h2.add(v1);
    }

    public Iterable<Key> adjacent(Key vertice)
    {
        return adj.get(vertice);
    }

    public void print()
    {
        for (Key i:ar)
        {
            System.out.print("For the vertex  "+i+"  adjacent vertices are  ");
            for (Key j: adj.get(i))
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
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
    }
}