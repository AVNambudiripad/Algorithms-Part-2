import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output 
    public static void transform()
    {
        String s = BinaryStdIn.readString();
        BinaryStdIn.close();
        csa obj=new csa(s);
        BinaryStdOut.write(obj.first());
        for (char c:obj.t())
        BinaryStdOut.write(c);
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform()
    {
        int first = BinaryStdIn.readInt();
        char[] t = BinaryStdIn.readString().toCharArray(); //Stores the last char
        final int length = t.length;
        HashSet<Character> h=new HashSet<>();
        char[] f = new char[length];                     //Stores the first char
        for (int i=0;i<length;i++)
        {
            h.add(t[i]);
            f[i]=t[i];
        }
        Arrays.sort(f);
        int[] next=new int[length];

        HashMap<Character,PriorityQueue<Integer>> h1=new HashMap<>(); //Maps chars to f[]
        HashMap<Character,PriorityQueue<Integer>> h2=new HashMap<>(); //Maps chars to t[]
        for (char c:h)
        {
            h1.put(c,new PriorityQueue<>());
            h2.put(c,new PriorityQueue<>());
        }
        for (int i=0;i<length;i++)
        {
            h1.get(f[i]).add(i);
            h2.get(t[i]).add(i);
        }

        for (char c:h)
        {
            PriorityQueue<Integer> pqf = h1.remove(c);
            PriorityQueue<Integer> pqt = h2.remove(c);
            while(!pqt.isEmpty())
            {
                next[pqf.remove()]=pqt.remove();
            }
        }

        /*
        System.out.println("first :"+first);
        for (int i=0;i<next.length;i++)
        System.out.print(next[i]+"   ");
        System.out.println();*/


        //We now know first, next[] and t[]
        StringBuilder sb=new StringBuilder();
        for (int i=next[first],count=0;count<length;i=next[i],count++)
        {
            sb.append(t[i]);
        }
        BinaryStdOut.write(sb.toString());
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args)
    {
        char c = args[0].trim().charAt(0);
        if (c=='-')
        transform();
        else if (c=='+')
        inverseTransform();
    }

}