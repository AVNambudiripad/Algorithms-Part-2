import edu.princeton.cs.algs4.In;
import java.util.Arrays;
public class SuffixSort
{
    private static StringBuilder LCP(StringBuilder s1,StringBuilder s2)           //Finds the longest common prefix of the 2 strings
    {
        StringBuilder s=new StringBuilder();
        for (int i=0;i<Math.min(s1.length(),s2.length());i++)
        {
            if (s1.charAt(i) == s2.charAt(i))
            s.append(s1.charAt(i));
            else 
            break;
        }
        return s;
    }
    public static String LRS(String s)           //Return the longest repeating substring in the string
    {
        StringBuilder[] suffix=new StringBuilder[s.length()];
        for (int i=0;i<s.length();i++)
        suffix[i]=new StringBuilder(""+s.charAt(i));
        for (int i=s.length()-2;i>=0;i--)
        suffix[i].append(suffix[i+1]);
        
        Arrays.sort(suffix);
        StringBuilder lrs=new StringBuilder("");
        int length=0;
        for (int i=0;i<s.length()-1;i++)
        {
            StringBuilder lcp=LCP(suffix[i],suffix[i+1]);
            if (lcp.length() > length)
            {
                lrs=lcp;
                length=lcp.length();
            }
        }
        return lrs.toString();
    }
    public static void main(String[] args)
    {
        In in=new In(args[0]);
        String s=in.readAll();
        System.out.println("Input : "+s);
        System.out.println("The longest repeated substring is "+LRS(s));
    }
}