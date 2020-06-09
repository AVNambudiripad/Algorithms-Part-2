public class StrongComponent
{
    private boolean[] visited;
    private int[] scc;
    private int count;
    public StrongComponent(Digraph g)
    {
        visited=new boolean[g.V()];
        scc=new int[g.V()];
        TopologicalSort obj=new TopologicalSort(g.reverse());
        Iterable<Integer> iter=obj.topological();
        int id=0;
        count=0;
        for (int i:iter)
        {
            if (!visited[i])
            {
                count++;
                scc[i]=id;
                dfs(g,i);
                id++;
            }
        }
    }
    private void dfs(Digraph g, int v)
    {
        visited[v]=true;
        for (int i:g.adjacent(v))
        {
            if (!visited[i])
            {
                scc[i]=scc[v];
                dfs(g,i);
            }
        }
    }

    public void print() //Print all strongly connected components together with their ID
    {
        for (int id=0;id<count;id++)
        {
            System.out.print("The following vertices have id "+id+": ");
            for (int i=0;i<scc.length;i++)
            if (scc[i]==id)
            System.out.print(" "+i);
            System.out.println();
        }
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

        

        StrongComponent obj=new StrongComponent(dg);
        obj.print();
    }
}