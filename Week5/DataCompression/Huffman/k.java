import java.util.TreeSet;
import edu.princeton.cs.algs4.In;
public class k
{
    public static void main(String[] args)
    {
        TreeSet<Character> t=new TreeSet<>();
        In in=new In(args[0]);
        while(in.hasNextChar())
        {
            t.add(in.readChar());
        }

        for (char c:t)
        System.out.println(c);
        System.out.println(t.size());
    }
}