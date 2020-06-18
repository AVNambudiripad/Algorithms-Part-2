public class Outcast {
    private WordNet w;
    public Outcast(WordNet wordnet)         // constructor takes a WordNet object
    {
        w = wordnet;
    }
    public String outcast(String[] nouns)   // given an array of WordNet nouns, return an outcast
    {
        int max=Integer.MIN_VALUE;
        String s="";
        for (int i=0;i<nouns.length;i++)
        {
            int d=0;
            for (int j=0;j<nouns.length;j++)
            {
                if (i!=j)
                d = d + w.distance(nouns[i], nouns[j]);
            }
            if (d > max)
            {
                s=nouns[i];
                max=d;
            }
        }
        return s;
    }
    public static void main(String[] args)  // see test client below
    {

    }
 }