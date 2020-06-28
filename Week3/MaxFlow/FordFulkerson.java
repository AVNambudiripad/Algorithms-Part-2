
//Computes Both Mincut and Maxflow

import java.util.Queue;
import java.util.LinkedList;
import java.util.TreeSet;
public class FordFulkerson
{
    private boolean[] visited;
    private FlowEdge[] edgeTo;
    private double maxFlow;
    private TreeSet<Integer> MinCut;
    private final int V;
    public FordFulkerson(FlowGraph g,final int s,final int t)
    {
        maxFlow=0;
        V=g.V();
        while (hasAugmentingPaths(g,s,t))
        {
            double bottleNeck=Double.POSITIVE_INFINITY;
            for (int i=t;i!=s;i=edgeTo[i].other(i))
            bottleNeck=Math.min(bottleNeck,edgeTo[i].residualCapacity(i));
            for (int i=t;i!=s;i=edgeTo[i].other(i))
            edgeTo[i].addFlow(i,bottleNeck);
            maxFlow+=bottleNeck;
            
            //System.out.println("A new iteration with "+bottleNeck+" bottleNeck");
            //g.print();
        }
        
        //Computing MinCut - Make 2 groups: Vertices that have an sugmenting path from s, and those that don't
        MinCut=new TreeSet<>();
        MinCut.add(s);
        computeMinCut(MinCut,g,s);
        
    }
    private boolean hasAugmentingPaths(FlowGraph g,final int s,final int t)
    {
        visited=new boolean[g.V()];
        edgeTo=new FlowEdge[g.V()];
        Queue<Integer> q=new LinkedList<>();
        int v=s;
        visited[v]=true;
        q.add(v);
        do
        {
            v=q.remove();
            for (FlowEdge e:g.adjacent(v))
            {
                int w=e.other(v);
                if (e.residualCapacity(w) > 0 && !visited[w])
                {
                    visited[w]=true;
                    edgeTo[w]=e;
                    q.add(w);
                }
            }
        }while(!q.isEmpty());
        
        return visited[t];
    }
    private void computeMinCut(TreeSet<Integer> MinCut,FlowGraph g,int v)
    {
        //Standard DFS Algorithm
        for (FlowEdge e:g.adjacent(v))
        {
            int w=e.other(v);
            if (e.residualCapacity(w) > 0 && !MinCut.contains(w))
            {
                MinCut.add(w);
                computeMinCut(MinCut,g,w);
            }
        }
    }
    public double maxFlow()
    {
        return maxFlow;
    }
    public void printMinCut()
    {
        System.out.println("The Mincut ");
        String s1="";
        String s2="";
        for (int i=0;i<V;i++)
        {
            if (MinCut.contains(i))
            s1=s1+i+" ";
            else
            s2=s2+i+" ";
        }
        System.out.println("Set A: "+s1+"\nSet B: "+s2);
    }
    
    public static void main(String[] args)
    {
        FlowGraph g=new FlowGraph(args[0]);
        g.print();
        FordFulkerson obj=new FordFulkerson(g,0,5);
        System.out.println("The max flow is "+obj.maxFlow());
        g.print();
        
        obj.printMinCut();
    }
    
}