public class CC<Key>
{
    //Connected Components Queries. Linear Time for initialisation
    private int[] cc;
    private int count;
    public CC(Graph<Key> g)
    {
        boolean[] visited=new boolean[g.V()];
        cc=new int[g.V()];
        count=0;
        for (Key i:g.allNodes())
        {
            if (!visited[g.keyToInt(i)])
            {
                dfs(cc,visited,g,i);
                count++;
            }
        }
    }
    private void dfs(int[] cc,boolean[] visited,Graph<Key> g,Key i)
    {
        int x=g.keyToInt(i);
        if (visited[x]) return;
        visited[x]=true;
        cc[x]=count;
        for (Key j:g.adjacent(i))
        {
            dfs(cc,visited,g,j);
        }
    }    
    public int count()   //Number of connected components
    {
        return count;
    }
    public int count(Graph<Key> g)
    {
        return count;
    }
    public boolean connected(Graph<Key> g,Key k1,Key k2)
    {
        return cc[g.keyToInt(k1)]==cc[g.keyToInt(k2)];
    }
    public int id(Graph<Key> g,Key k1)
    {
        return cc[g.keyToInt(k1)];
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

        CC<Character> obj=new CC<>(g);
        System.out.println(obj.count());
        for (int i=0;i<g.V();i++)
        {
            System.out.println("For the key "+g.intToKey(i)+"  its id is "+obj.id(g, g.intToKey(i)));
        }
    }
}