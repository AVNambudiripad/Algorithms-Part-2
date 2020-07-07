import java.util.PriorityQueue;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
public class Huffman           //CREATE HUFFMAN CODING
{
    private class Node implements Comparable<Node>
    {
        Character ch;
        int freq;
        Node left,right;
        Node(int f)
        {
            freq=f;
            ch=null;
            left=null;
            right=null;
        }
        Node (int f,char c)
        {
            freq=f;
            ch=c;
            left=null;
            right=null;
        }
        public int compareTo(Node other)
        {
            if (freq < other.freq) return -1;
            else if (freq == other.freq) return 0;
            return 1;
        } 
    }



    private void prepend(Node n,char c,String[] encode,char[] chars)
    {
        //To all the letters in n, prepend c to their corresponding encodes
        if (n==null) throw new IllegalArgumentException();
        if (n.ch!=null)
        {
            encode[indexOf(chars, n.ch)] = c + encode[indexOf(chars, n.ch)];
            return;
        }
        prepend(n.left, c, encode, chars);
        prepend(n.right, c, encode, chars);
    }
    private int indexOf(char[] c,char ch)
    {
        for (int i=0;i<c.length;i++)
        if (c[i] == ch)
        return i;
        return -1;
    }
    private static char[] English()  //All chars in mobydick
    {
        ArrayList<Character> chars=new ArrayList<>();
        for (char c='a';c<='z';c++)
        chars.add(c);
        for (char c='A';c<='Z';c++)
        chars.add(c);
        for (char c='0';c<='9';c++)
        chars.add(c);

        chars.add('.');
        chars.add('?');
        chars.add(' ');
        chars.add('\t');
        chars.add('\n');
        chars.add('!');
        chars.add(',');
        chars.add('\'');
        chars.add('\"');
        chars.add('-');
        chars.add('$');
        chars.add('&');
        chars.add('(');
        chars.add(')');
        chars.add('*');
        chars.add(':');
        chars.add(';');
        chars.add('[');
        chars.add(']');

        assert chars.size() >= 80;
        char[] c=new char[chars.size()];
        for (int i=0;i<chars.size();i++)
        c[i]=chars.get(i);
        return c;
    }
    private static char[] ASCII()
    {
        char[] c=new char[256];
        for (char i=0;i<256;i++)
        c[i]=i;
        return c;
    }

    public Huffman(String txt)               //Assume ASCII
    {
        this(txt,ASCII());
    }
    public Huffman(String text,char[] chars) //chars[] is all possible letters
    {
        //Take the text and make 2 files - key.txt (containing key mappings)
        //And the encoded text as "encoded" (no extension)
        int[] freq = new int[chars.length];
        for (int i=0;i<text.length();i++)
        {
            char c = text.charAt(i);
            freq[indexOf(chars, c)]++;
        }

        PriorityQueue<Node> pq=new PriorityQueue<>();
        for (int i=0;i<chars.length;i++)
        {
            Node n=new Node(freq[i],chars[i]);
            pq.add(n);
        }

        String[] encode=new String[chars.length]; //The code corresponding to each letter
        for (int i=0;i<encode.length;i++)
        encode[i]="";
        while (pq.size() > 1)
        {
            Node l = pq.remove();
            Node r = pq.remove();
            prepend(l, '0', encode, chars);
            prepend(r, '1', encode, chars);
            Node newNode =new Node(l.freq+r.freq);
            newNode.left = l;
            newNode.right = r;
            pq.add(newNode);
        }

        //We have the Trie
        Node root = pq.remove();
        Out key = new Out("key.txt");   
        makeKey(root, key,encode,chars);
                
        BinaryOut bOut=new BinaryOut("encoded");
        for (int i=0;i<text.length();i++)
        {
            char c = text.charAt(i);
            int index = indexOf(chars, c);
            for (int j=0;j<encode[index].length();j++)
            {
                if (encode[index].charAt(j) == '0')
                bOut.write(false);
                else
                bOut.write(true);
            }
        }
        bOut.close();
    }
    private void makeKey(Node n,Out key,String[] encode,char[] chars)
    {
        assert n!=null;

        if (n.ch != null)
        {
            key.println(n.ch+"    "+encode[indexOf(chars, n.ch)]);
            return;
        }
        makeKey(n.left, key, encode, chars);
        makeKey(n.right, key, encode, chars);
    }


    public static void main(String[] args)
    {
        In in=new In(args[0]);
        Huffman obj=new Huffman(in.readAll(),English());
    }
}