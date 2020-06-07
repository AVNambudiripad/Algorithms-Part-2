import java.util.HashMap;
import java.util.ArrayList;

import com.google.common.collect.HashMultiset; 
import com.google.common.collect.Multiset; 

//Creates a Generic Graph of type Key. (Key need not be comparable)

public class Graph<Key>  
{
    private HashMap<Key,Multiset<Key>> adj; //HashMap of the Adjacent Lists of all the vertices
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
            adj.put(arr[i],HashMultiset.create());
            ar.add(arr[i]);
        }
    }
    public int E()   {  return E;  }
    public int V()   {  return V;  }

    public void addEdge(Key v1,Key v2)
    {
        Multiset<Key> h1=adj.get(v1);
        Multiset<Key> h2=adj.get(v2);
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
    public Multiset<Key> adjacentPowerful(Key vertice)
    {
        return adj.get(vertice);
    }
    public Integer keyToInt(Key k)  //Give the order in which the vertex was added
    {
        return ar.indexOf(k);
    }
    public Key intToKey(int i)
    {
        return ar.get(i);
    }
    public Iterable<Key> allNodes()  //Return all nodes of the graph in chronological order
    {
        return ar;
    }
    public int degree(Key k)
    {
        return adj.get(k).size();
    }


    public void print()
    {
        for (Key i:ar)
        {
            System.out.print("For the vertex  "+i+"  "+keyToInt(i)+" adjacent vertices are  ");
            for (Key j: adj.get(i))
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    

    public static void main(String[] args)
    {
        Character[] a={'A','B','C','D','E','F','G','H','I','J','K','L','M'};
        Graph<Character> g=new Graph<>(a);
        g.addEdge('A', 'B');
        g.addEdge('C', 'A');
        g.addEdge('A', 'F');
        g.addEdge('A', 'G');
        g.addEdge('G', 'E');
        g.addEdge('E', 'F');
        g.addEdge('D', 'E');
        g.addEdge('F', 'D');
        g.addEdge('B', 'C');
        g.addEdge('F', 'E');

        g.addEdge('H', 'I');
        g.addEdge('J', 'H');
        g.addEdge('H', 'K');
        g.addEdge('J', 'K');
        g.addEdge('L', 'M');
        g.addEdge('L', 'L');

        g.print();
    }
}