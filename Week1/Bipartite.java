public class Bipartite<Key>
{
    //To determine whether a given graph is bipartite or not
    private int[] color;  //color=0 means graph not visited yet, color=1 or 2 denotes the color of the graph node
    private boolean bipartite;
    public Bipartite(Graph<Key> g)
    {
        bipartite=true;
        color=new int[g.V()];
        int colour=1;
        for (Key i:g.allNodes())
        {
            if (color[g.keyToInt(i)]==0)   //Examining each of the connected components to see if they're bipartite
            {
                bipartite = bipartite && dfs(g,color,i,colour);
            }
            if (!bipartite)      //If at least one connected component is not bipartite, graph is not bipartite
            break;
        }
    }
    private boolean dfs(Graph<Key> g,int[] color,Key k,int colour)
    {
        color[g.keyToInt(k)]=colour;
        if (colour==1) colour=2;
        else colour=1;
        boolean b=true;
        for (Key i:g.adjacent(k))
        {
            if (color[g.keyToInt(i)]==colour) ;
            else if (color[g.keyToInt(i)]==0)
            {
                b=b && dfs(g,color,i,colour);                
            }
            else
            {
                return false;
            }
        }
        return b;
    }
    public boolean bipartiteOrNot()
    {
        return bipartite;
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
        g.addEdge('B', 'A');
        g.addEdge('H', 'I');
        g.addEdge('J', 'H');
        g.addEdge('H', 'K');
        g.addEdge('J', 'K');
        g.addEdge('L', 'M');

        g.print();
        System.out.println("\n");

        Bipartite<Character> obj=new Bipartite<>(g);
        System.out.println(obj.bipartiteOrNot());

        Integer[] a2={1,2,3,4,5,6,7,8,9};
        Graph<Integer> g2=new Graph<>(a2);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(9, 2);
        g2.addEdge(3, 4);
        g2.addEdge(4, 7);
        g2.addEdge(5, 6);
        g2.addEdge(5, 8);
        g2.addEdge(8, 9);

        g2.print();
        System.out.println("\n");

        Bipartite<Integer> obj2=new Bipartite<>(g2);
        System.out.println(obj2.bipartiteOrNot());


    }
}