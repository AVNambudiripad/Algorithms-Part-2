import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
public class Trie<Value>
{
    private final int R;       //Radix of the Strings
    private Node root;
    private int size;
    private class Node
    {
        Value value;
        ArrayList<Node> a;
        Node()
        {
            value=null;
            a=new ArrayList<>();
            for (int i=0;i<R;i++)
            a.add(null);
        }
    }
    public Trie()
    {
        R=256;                  //Extended ASCII
        root=new Node();
        size=0;
    }
    public void put(String s, Value v)
    {
        char c=s.charAt(0);
        root.a.set(c,put(s,v,root.a.get(c),0));
    }
    private Node put(String s,Value v, Node n,int d)
    {
        if (n==null)
        n=new Node();
        if (d == s.length()-1)
        {
            if (n.value==null)
            size++;
            n.value=v;
            //System.out.println(n.value);
        }
        if (d+1 >= s.length())
        return n;
        else 
        {
            char c=s.charAt(d+1);
            n.a.set(c,put(s,v,n.a.get(c),d+1));
            return n;
        }
    }
    public boolean contains(String s)
    {
        Node t=root;
        for (int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            t=t.a.get(c);
            if (t==null)
            return false;
        }
        return !(t.value==null);    
    }
    public Value get(String s)
    {
        Node t=root;
        for (int i=0;i<s.length();i++)
        {
            char c=s.charAt(i);
            t=t.a.get(c);
            if (t==null)
            return null;
        }
        return t.value; 
    }
    public Value remove(String s)
    {
        Value v=get(s);
        char c=s.charAt(0);
        boolean[] b=new boolean[1];                       //Because Java doesn't have pass-by-reference
        root.a.set(c,remove(root.a.get(c),s,0,b));
        return v;
    }
    private boolean hasChildren(Node n)
    {
        for (int i=0;i<R;i++)
        if (n.a.get(i) !=null)
        return true;
        return false;
    }
    private Node remove(Node n,String s,int d,boolean[] b)
    {
        if (n==null) return n;
        if (d == s.length() -1)
        {
            size--;
            if (hasChildren(n))
            n.value=null;
            else
            {
                b[0]=true;
                return null;
            }
        }
        if (d+1 >= s.length())
        return n;
        char c=s.charAt(d+1);
        n.a.set(c,remove(n.a.get(c),s,d+1,b));
        if (b[0])
        {
            if (hasChildren(n))
            b[0]=false;
            else if (n!=root)
            return null;
        }
        return n;
    }
    public int size()
    {
        return size;
    }
    public Iterable<String> getKeys()
    {
        Queue<String> q=new LinkedList<>();
        for (int i=0;i<R;i++)
            if (root.a.get(i) !=null)
            {
                String s=""+(char)i;
                helper(q,s,root.a.get(i));
            }
        return q;    
    }
    private void helper(Queue<String> q,String s,Node n)
    {
        if (n.value != null)
        q.add(s.toString());
        for (int i=0;i<R;i++)
        {
            if (n.a.get(i) !=null)
            {
                String s1=s+(char)i;
                helper(q,s1,n.a.get(i));
            }
        }
    }
    public static void main(String[] args)
    {
        //Simple Dictionary Test
        In in=new In(args[0]);
        Trie<String> t=new Trie<>();
        while (in.hasNextLine())
        {
            String[] s=in.readLine().trim().split("\\s+");
            t.put(s[0].trim(),s[1].trim());
        }
        
        System.out.println(t.contains("india")+"  "+t.get("india"));
        System.out.println(t.contains("computer")+"  "+t.get("computer"));
        System.out.println(t.contains("teeth")+"  "+t.get("teeth"));
        System.out.println(t.contains("i")+"  "+t.get("i"));
        System.out.println(t.size()+"\n\n");
        
        for (String s:t.getKeys())
        {
            System.out.println(s);
        }
        
        System.out.println(t.remove("india"));
        System.out.println(t.contains("india")+"  "+t.get("india"));
        System.out.println(t.contains("indian")+"  "+t.get("indian"));
        System.out.println(t.size()+"\n\n");
        
        for (String s:t.getKeys())
        {
            System.out.println(s);
        }
    }
}