
import edu.princeton.cs.algs4.In;
public class LSD
{
    //Sort Strings by their least significant digit
    public static void sort(String[] a,int W,int R)
    {
        //All strings have fixed length W and Radix R
        int N=a.length;
        String[] aux=new String[N];
        for (int d=W-1;d>=0;d--)
        {
            int[] count=new int[R+1];
            for (int i=0;i<N;i++)
            count[a[i].charAt(d)+1]++;
            for (int i=1;i<=R;i++)
            count[i]+=count[i-1];
            for (int i=0;i<N;i++)
            aux[count[a[i].charAt(d)]++] = a[i];
            for (int i=0;i<N;i++)
            a[i]=aux[i];
        }
    }
    public static void print(String[] a)
    {
        for (int i=0;i<a.length-1;i++)
        {
            if (i%10==0 && i!=0) System.out.println();
            System.out.print(a[i]+",");
        }
        System.out.println(a[a.length-1]+"\n");
    }
    public static void main(String[] args)
    {
        In in=new In(args[0]);
        int N=in.readInt();
        int W=in.readInt();
        in.readLine();
        String[] a=new String[N];
        for (int i=0;i<N;i++)
        a[i]=in.readLine().trim();
        print(a);
        sort(a,W,256);
        print(a);
    }
}