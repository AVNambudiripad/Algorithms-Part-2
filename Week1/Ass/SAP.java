import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
public class SAP {

    final private Digraph dg;
    final private ArrayList<Integer> listOfNodes;
    

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        if (G==null) throw new IllegalArgumentException();
        dg=new Digraph(G);
        listOfNodes=new ArrayList<>();
        for (int i=0;i<dg.V();i++)
        listOfNodes.add(i);
    }
 
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        if (v<0 || v>=dg.V() || w<0 || w>=dg.V()) throw new IllegalArgumentException();
        BreadthFirstDirectedPaths obj1=new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths obj2=new BreadthFirstDirectedPaths(dg, w);

        int min=Integer.MAX_VALUE; //Minimum length
        for (int i:listOfNodes)
        {
            if (obj1.hasPathTo(i) && obj2.hasPathTo(i))
            {
                if (obj1.distTo(i) + obj2.distTo(i) < min)
                min=obj1.distTo(i) + obj2.distTo(i);
            }
        }
        if (min==Integer.MAX_VALUE) return -1;
        return min;
    }
 
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        if (v<0 || v>=dg.V() || w<0 || w>=dg.V()) throw new IllegalArgumentException();
        BreadthFirstDirectedPaths obj1=new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths obj2=new BreadthFirstDirectedPaths(dg, w);

        int min=Integer.MAX_VALUE; //Minimum length
        int minA=-1;               //Ancestor corresponding to minimum length
        for (int i:listOfNodes)
        {
            if (obj1.hasPathTo(i) && obj2.hasPathTo(i))
            {
                if (obj1.distTo(i) + obj2.distTo(i) < min)
                {
                    min=obj1.distTo(i)+obj2.distTo(i);
                    minA=i;
                }
            }
        }
        return minA;
    }
 
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        if (v==null || w==null) throw new IllegalArgumentException();
        BreadthFirstDirectedPaths obj1=new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths obj2=new BreadthFirstDirectedPaths(dg, w);

        int min=Integer.MAX_VALUE; //Minimum length
        for (int i:listOfNodes)
        {
            if (obj1.hasPathTo(i) && obj2.hasPathTo(i))
            {
                if (obj1.distTo(i) + obj2.distTo(i) < min)
                min=obj1.distTo(i) + obj2.distTo(i);
            }
        }
        if (min==Integer.MAX_VALUE) return -1;
        return min;
    }
 
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        if (v==null || w==null) throw new IllegalArgumentException();
        BreadthFirstDirectedPaths obj1=new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths obj2=new BreadthFirstDirectedPaths(dg, w);

        int min=Integer.MAX_VALUE; //Minimum length
        int minA=-1;               //Ancestor corresponding to minimum length
        for (int i:listOfNodes)
        {
            if (obj1.hasPathTo(i) && obj2.hasPathTo(i))
            {
                if (obj1.distTo(i) + obj2.distTo(i) < min)
                {
                    min=obj1.distTo(i)+obj2.distTo(i);
                    minA=i;
                }
            }
        }
        return minA;
    }
 
    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
 }
 