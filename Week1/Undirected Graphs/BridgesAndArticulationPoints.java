import java.util.Queue;
import java.util.LinkedList;


import com.google.common.collect.HashMultiset; 
import com.google.common.collect.Multiset; 

public class BridgesAndArticulationPoints<Key>  //SEE DFS TREE (From Codeforces)
{
    private class Edge
    {
        int key1;
        int key2;
        Edge(int k1,int k2) 
        {
            key1=k1;
            key2=k2;
        }
        Integer otherEnd(int v)
        {
            if (v==key1) return key2;
            else if (v==key2) return key1;
            else return null;
        }
    }

    private Queue<Edge> bridges;
    private Queue<Key> AP; //Articulation Points
    private boolean[] visited;
    private int count;
    private int[] dfs;  //Order of insertion in DFS Tree 
    private int[] parent;   //Parent element in DFS Tree
    private int[] low;  //The minimum node in DFS Tree connected to the given node or any of its children
    public BridgesAndArticulationPoints(Graph<Key> g)
    {
        bridges=new LinkedList<>();
        AP=new LinkedList<>();
        visited=new boolean[g.V()];
        dfs=new int[g.V()];
        low=new int[g.V()];
        parent=new int[g.V()];
        //First, initialise parent,visited,dfs and low for all Connected Components
        for (Key i:g.allNodes())
        {
            int x=g.keyToInt(i);
            if (!visited[x])
            {
                count=0;
                parent[x]=x; //Parent of root element of DFS Tree
                dfs(g,x);
            }
        }

        //debugPrint(g);

        /* First, find all the articulation points
        In DFS tree, a vertex u is articulation point if one of the following two conditions is true.
        1) u is root of DFS tree and it has at least two children.
        2) u is not root of DFS tree and it has a child v such that no vertex in subtree rooted 
        with v has a back edge to one of the ancestors (in DFS tree) of u.
        */
        for (Key i:g.allNodes())
        {
            int x=g.keyToInt(i);
            if (parent[x]==x) //First Condition
            {
                int children=0;
                for (Key j:g.adjacent(i))
                {
                    if (parent[g.keyToInt(j)] == x && g.keyToInt(j)!=x)
                    children++;
                    if (children==2)
                    {
                        AP.add(i);
                        break;
                    }
                }
            }

            else if (low[x] > dfs[parent[x]]) //Second Condition
            {
                if (!AP.contains(g.intToKey(parent[x]))  && parent[parent[x]]!=parent[x] )
                AP.add(g.intToKey(parent[x]));
            }
        }

        //Now, Find the Bridges 
        for (Key i:g.allNodes())
        {
            Multiset<Key> m=g.adjacentPowerful(i);
            for (Key j:m)
            {
                int x=g.keyToInt(i),y=g.keyToInt(j);
                if (x==y) 
                {
                    //Ignore - Self Loop cannot be a bridge
                }
                else if (parent[y] == x) //Only parent-child Span edges can be bridges
                {
                    if (m.count(j) == 1) //If two nodes have parallel edges, there is no bridge
                    {
                        if (low[y] > dfs[x])
                        {
                            Edge e=new Edge(x,y);
                            bridges.add(e);
                        }
                    }
                }
            }
        }
    }
    private void dfs(Graph<Key> g,int vertice)
    {
        visited[vertice]=true;
        dfs[vertice]=count;
        low[vertice]=count;
        count++;
        for (Key i:g.adjacent(g.intToKey(vertice)))
        {
            int x=g.keyToInt(i);
            if (x==vertice)  //Self Loop
            {} //Ignore
            else if (!visited[x])  //Span Edges
            {
                parent[x]=vertice;
                dfs(g,x);
            }
            else if (x!=parent[vertice])             //Back Edges
            {
                if (dfs[x] < low[vertice])
                low[vertice]=dfs[x];
            }
        }

        if (low[vertice] < low[parent[vertice]]) 
        low[parent[vertice]]=low[vertice];
    }


    private void print(int[] ar,Graph<Key> g)
    {
        for (int j=0;j<ar.length;j++)
        {
            int i=ar[j];
            System.out.print(i);
            System.out.print(g.intToKey(j)+" ");
        }
        System.out.println();
    }
    private void debugPrint(Graph<Key> g)
    {
        System.out.println("The dfs array is ");
        print(dfs,g);
        System.out.println("The low array is ");
        print(low,g);
        System.out.println("The parent array is ");
        print(parent,g);
    }


    public void printArticulationPoints()
    {
        System.out.println("The Articulation Points Are : ");
        for (Key i:AP)
        {
            System.out.print(i+"  ");
        }
        System.out.println();
    }
    public void printBridges(Graph<Key> g)
    {
        System.out.println("The Bridges Are  : ");
        for (Edge i:bridges)
        {
            System.out.println(g.intToKey(i.key1)+"  "+g.intToKey(i.key2));
        }
        System.out.println();
    }



    public static void main(String[] args)
    {
        Character[] a={'A','B','C','D','E','F','G','H','I','J','K','L','M','N'};
        Graph<Character> g=new Graph<>(a);
        
        g.addEdge('G', 'E');
        g.addEdge('E', 'F');
        g.addEdge('D', 'E');
        g.addEdge('F', 'D');
        g.addEdge('F', 'E');
        g.addEdge('A', 'B');
        g.addEdge('C', 'A');
        g.addEdge('A', 'F');
        g.addEdge('A', 'G');

        g.addEdge('H', 'I');
        g.addEdge('J', 'H');
        g.addEdge('H', 'K');
        g.addEdge('J', 'K');
        
        g.addEdge('L', 'M');
        g.addEdge('L', 'L');
        g.addEdge('M', 'N');

        g.print();

        BridgesAndArticulationPoints<Character> obj=new BridgesAndArticulationPoints<>(g);
        obj.printArticulationPoints();
        obj.printBridges(g);
    }
}