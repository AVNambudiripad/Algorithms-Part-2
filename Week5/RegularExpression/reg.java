import java.util.Stack;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
//Finding Regular Expressions
public class reg
{
    private static String removeWhiteSpace(String s)
    {
        StringBuilder s1=new StringBuilder();
        for (int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);
            if (!(c==' ' || c=='\t' || c=='\n'))
            s1.append(c);
        }
        return s1.toString();
    }
    public static int indexOf(String regEx1,String txt)
    {
        //NOTE : Supported chars in RegEx = '('  ')'  '*'   '|' ONLY
        //All regular expressions must Start and End with ()
        final String regEx=removeWhiteSpace(regEx1);
        int M = regEx.length();
        int N = txt.length();
        Digraph g=new Digraph(M+1);
        int[] transition=new int[M+1];
        Stack<Character> s=new Stack<>();
        int lp = -1;             //The most recent left parenthesis found so far
        int or = -1;             //The most recent '|' found so far
        for (int i=0;i<M;i++)
        {
            transition[i]=i;
            char c = regEx.charAt(i);
            if (c == '(')
            {
                s.add(c);
                g.addEdge(i, i+1);
                lp=i;
            }
            else if (c == '*')
            {
                if (regEx.charAt(i-1) == ')')
                {
                    g.addEdge(i, lp);
                    g.addEdge(lp, i);
                }
                else
                {
                    g.addEdge(i, i-1);
                    g.addEdge(i-1, i);
                }
                g.addEdge(i, i+1);
            }
            else if (c == '|')
            {
                or=i;
                s.add(c);
                g.addEdge(lp,i+1);
            }
            else if (c == ')')
            {
                g.addEdge(i, i+1);
                char ch = s.pop();
                if (ch == '|')
                {
                    g.addEdge(or, i);
                    s.pop();
                }
            }
            else 
            transition[i]=i+1;
        }

        g.print();
        //Initialisation of g OVER


        HashSet<Integer> h=new HashSet<>();
        int index=0;

        for (int i=0;i<N;i++)
        {
            System.out.print(h.toString()+"        ");
            char c=txt.charAt(i);
            h.removeIf(state -> (regEx.charAt(state) != c));
            if (h.isEmpty())
            {
                reset(h, g);
                index=i+1;
            }
            else
            {
                for (int j:new HashSet<>(h))
                h.add(transition[j]);
                h.removeIf(state -> (regEx.charAt(state) == c));
                for (int j:g.reachable(h))
                h.add(j);
                if (h.contains(M))
                return index;
            }

            System.out.println(h.toString());
        }
        return -1;
    }
    private static void reset(HashSet<Integer> h,Digraph g)
    {
        h.add(0);
        for (int i:g.reachable(h))
        h.add(i);
    }

    public static void main(String[] args)
    {
        String reg = "( ( A * B | A C ) D )";
        String s="GREUAXDBSKJBDWKHDKL";
        System.out.println(s);
        System.out.println("\n\n"+indexOf(reg,s));
    }
}