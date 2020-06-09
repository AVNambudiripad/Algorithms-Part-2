import java.util.HashSet;
import java.util.Set;
public class DetectCycle
{
    private boolean[] visited;
    private boolean cycle;
    public DetectCycle(Digraph g)
    {
        visited=new boolean[g.V()];
        cycle=false;
        for (int i=0;i<g.V() && !cycle;i++)
        {
            if (!visited[i])
            {
                Set<Integer> s=new HashSet<>();
                dfs(g,s,i);
            }
        }
    }
    private void dfs(Digraph g,Set<Integer> s,int v)
    {
        if (cycle == true) return;  //We already know that a cycle exists
        visited[v]=true;
        s.add(v);
        for (int i:g.adjacent(v)) //Examine a particular DFS path
        {
            if (!visited[i])
            {
                dfs(g,s,i);
            }
            else if (s.contains(i))  //Can you go to the same elements in the path again?
            {
                cycle=true;
            }
        }
        s.remove(v); //Remove in order to create a new DFS path
    }

    public boolean cycleOrNot ()
    {
        return cycle;
    }


    public static void main(String[] args)
    {
        Digraph g1=new Digraph(7);
        g1.addEdge(0, 5);
        g1.addEdge(0, 2);
        g1.addEdge(0, 1);
        g1.addEdge(3, 6);
        g1.addEdge(3, 5);
        g1.addEdge(3, 4);
        g1.addEdge(5, 2);
        g1.addEdge(6, 4);
        g1.addEdge(6, 0);
        g1.addEdge(3, 2);
        g1.addEdge(1, 4);

        System.out.println(g1.toString());

        DetectCycle obj=new DetectCycle(g1);
        System.out.println(obj.cycleOrNot());


        Digraph dg=new Digraph(13);
        dg.addEdge(0,5);
        dg.addEdge(0,1);
        //dg.addEdge(2,0);
        dg.addEdge(2,3);
        //dg.addEdge(3,2);
        //dg.addEdge(3,5);
        dg.addEdge(4,2);
        dg.addEdge(4,3);
        dg.addEdge(5,4);
        dg.addEdge(6,0);
        dg.addEdge(6,4);
        //dg.addEdge(6,8);
        dg.addEdge(6,9);
        dg.addEdge(7,6);
        dg.addEdge(7,9);
        dg.addEdge(8,6);
        dg.addEdge(9,10);
        dg.addEdge(9,11);
        dg.addEdge(10,12);
        dg.addEdge(11,4);
        dg.addEdge(11,12);
        //dg.addEdge(12,9);

        System.out.println(dg.toString());


        DetectCycle obj2=new DetectCycle(dg);
        System.out.println(obj2.cycleOrNot());

    }
}