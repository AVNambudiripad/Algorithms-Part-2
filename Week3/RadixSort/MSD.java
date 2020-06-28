import edu.princeton.cs.algs4.In;
public class MSD
{
    private static int charAt(String s,int index)
    {
        if (index < s.length()) return s.charAt(index);
        return -1;
    }
    
    public static void sort(String[] a)                 //Assumes R = 256 (ie, Extended ASCII)
    {
        sort(a,256);
    }
    public static void sort(String[] a,int R)           //R is the radix
    {
        String[] aux=new String[a.length];
        sort(a,aux,0,a.length-1,0,R);
    }
    private static void sort(String[] a,String[] aux,int low,int high,int d,int R)  //d is the position of the letter/char to sort by
    {
        if (low >= high) return;
        int[] count=new int[R+2];                        //R+2 as '0' is reserved for end-of-file
        for (int i=low;i<=high;i++)
        count[charAt(a[i],d)+2]++;
        for (int i=1;i<R+2;i++)
        count[i]+=count[i-1];
        for (int i=low;i<=high;i++)
        aux[count[charAt(a[i],d)+1]++]=a[i];
        for (int i=low;i<=high;i++)
        a[i]=aux[i-low];
        
        for (int r=0;r<R+1;r++)
        sort(a,aux,low+count[r],low+count[r+1]-1,d+1,R);
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
        in.readLine();
        String[] a=new String[N];
        for (int i=0;i<N;i++)
        a[i]=in.readLine().trim();
        print(a);
        sort(a);
        print(a);
    }
}