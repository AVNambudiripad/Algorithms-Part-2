import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import java.util.TreeMap;
import java.util.ArrayList;
public class WordNet {


    
    private TreeMap<String,ArrayList<Integer>> h; //Map nouns to a set of synset ids
    private ArrayList<String> h2;
    private Digraph g;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        if (synsets==null || hypernyms==null) throw new IllegalArgumentException();
        h=new TreeMap<>();
        In i1=new In(synsets);
        int i=0;
        h2=new ArrayList<>();
        while(!i1.isEmpty())
        {
            String[] temp=i1.readLine().split(",");
            String[] temp2=temp[1].trim().split("\\s+");
            String t="";
            for (String s:temp2)
            {
                if (h.containsKey(s))
                {
                    h.get(s).add(i);
                }
                else
                {
                    ArrayList<Integer> a=new ArrayList<>();
                    a.add(i);
                    h.put(s,a);
                }

                t=t+s+" ";
            }
            h2.add(t);
            i++;
        }

        g=new Digraph(i);
        In i2=new In(hypernyms);
        while (!i2.isEmpty())
        {
            String[] temp=i2.readLine().split(",");
            int j=Integer.parseInt(temp[0].trim());
            for (int k=1;k<temp.length;k++)
            g.addEdge(j, Integer.parseInt(temp[k].trim()));
        }

        sap=new SAP(g);

        //All the below lines check for Illegal Arguments
        DirectedCycle obj=new DirectedCycle(g);
        boolean b=obj.hasCycle();
        if (b) throw new IllegalArgumentException();

        int numberOfRoots=0;
        for (int j=0;j<g.V() && numberOfRoots < 2;j++)
        if (g.outdegree(j)==0) numberOfRoots++;

        //System.out.println("The number of roots is "+numberOfRoots);
        if (numberOfRoots!=1) throw new IllegalArgumentException();
    }
 
    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return h.navigableKeySet();
    }
 
    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        if (word==null) throw new IllegalArgumentException();
        return h.containsKey(word);
    }
 
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        return sap.length(h.get(nounA), h.get(nounB));
    }
 
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        int i=sap.ancestor(h.get(nounA), h.get(nounB)); //i is synset ID

        return h2.get(i);
    }
 
    // do unit testing of this class
    public static void main(String[] args)
    {
        WordNet obj=new WordNet("synsets.txt", "hypernyms.txt");
        System.out.println(obj.g.V()+"    "+obj.g.E());
        System.out.println(obj.h2.size());
        System.out.println(obj.distance("American_water_spaniel", "histology"));
    }
 }