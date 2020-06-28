import edu.princeton.cs.algs4.In;
import java.util.Queue;
import java.util.LinkedList;
public class TernarySearchTrie<Value>
{
    private class Node
    {
        Value value;
        char c;
        Node left;
        Node right;
        Node middle;
        Node(char ch)
        {
            c=ch;
            value=null;
            left=null;
            middle=null;
            right=null;
        }
    }
    private Node root;
    private int size;
    public TernarySearchTrie()
    {
        root=null;
        size=0;
    }
    public void put(String s, Value v)
    {
        root=put(root,s,v,0);
    }
    private Node put(Node n,String s,Value v,int d)
    {
       char c=s.charAt(d);
       if (n==null)
       n=new Node(c);
       
       
       if (c < n.c)
       n.left=put(n.left,s,v,d);
       else if (c > n.c)
       n.right=put(n.right,s,v,d);
       else 
       {
           if (d == s.length() - 1)
           {
               if (n.value == null)
               size++;
               n.value=v;
               return n;
            }
           n.middle=put(n.middle,s,v,d+1);
       }
       
       return n;
    }
    public boolean contains(String s)
    {
        Node t=root;
        for (int i=0;i<s.length();)
        {
            if (t==null) return false;
            char c=s.charAt(i);
            if (t.c == c)
            {
                i++;
                if (i!=s.length())
                t=t.middle;
            }
            else if (c < t.c)
            t=t.left;
            else
            t=t.right;
        }
        return !(t.value == null);
    }
    public Value get(String s)
    {
        Node t=root;
        for (int i=0;i<s.length();)
        {
            if (t==null) return null;
            char c=s.charAt(i);
            if (t.c == c)
            {
                i++;
                if (i!=s.length())
                t=t.middle;
            }
            else if (c < t.c)
            t=t.left;
            else
            t=t.right;
        }
        return t.value;
    }
    public int size()
    {
        return size;
    }
    public Value remove(String s)
    {
        Value v=get(s);
        boolean[] b=new boolean[1];                //Implemented as Java doesn't have pass-by-reference
        root=remove(root,s,0,b);
        return v;
    }
    private boolean hasChildren(Node n)
    {
        if (n.left!=null || n.middle!=null || n.right!=null)
        return true;
        return false;
    }
    private Node remove(Node n,String s,int d,boolean[] b)
    {
        if (n==null) return n;
        char c=s.charAt(d);
        if (c < n.c)
        n.left=remove(n.left,s,d,b);
        else if (c > n.c)
        n.right=remove(n.right,s,d,b);
        else
        {
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
            else
            n.middle=remove(n.middle,s,d+1,b);
        }
        
        if (b[0])
        {
            if (hasChildren(n))
            b[0]=false;
            else
            return null;
        }
        return n;
    }
    public Iterable<String> getKeys()  //Returns keys in sorted order
    {
        if (root==null) return null;
        Queue<String> q=new LinkedList<>();
        String s="";
        helper(root,s,q);
        return q;
    }
    private void helper(Node n,String s,Queue<String> q)
    {
        if (n==null) return;
        String s1=s+n.c;
        if (n.value !=null) q.add(s1);
        if (n.left !=null)
        helper(n.left,s,q);
        if (n.middle !=null)
        helper(n.middle,s1,q);
        if (n.right !=null)
        helper(n.right,s,q);
    }
    public Iterable<String> prefixMatch(String s)
    {
        Node t=root;
        for (int i=0;i<s.length();)
        {
            if (t==null) return null;
            char c=s.charAt(i);
            if (t.c == c)
            {
                i++;
                if (i!=s.length())
                t=t.middle;
            }
            else if (c < t.c)
            t=t.left;
            else
            t=t.right;
        }
        Queue<String> q=new LinkedList<>();
        helper(t.middle,s,q);
        return q;
    }
    public String longestPrefix(String s)
    {
        Node t=root;
        String st="";
        for (int i=0;i<s.length();)
        {
            if (t==null) return st;
            char c=s.charAt(i);
            if (t.c == c)
            {
                st=st+c;
                i++;
                if (i!=s.length())
                t=t.middle;
            }
            else if (c < t.c)
            t=t.left;
            else
            t=t.right;
        }
        return st;
    }
    
    public static void main(String[] args)
    {
        //Simple Dictionary
        In in=new In(args[0]);
        TernarySearchTrie<Integer> t=new TernarySearchTrie<>();
        while (in.hasNextLine())
        {
            String[] s=in.readLine().trim().split("\\s+");
            t.put(s[0].trim(),Integer.parseInt(s[1].trim()));
        }
        
        for (String s:t.getKeys())
        {
            System.out.print(s+"  ");
        }
        System.out.println();
        
        for (String s:t.prefixMatch("sh"))
        System.out.print(s+" ");
        System.out.println();
        
        System.out.println(t.longestPrefix("shellsort"));
    }
}