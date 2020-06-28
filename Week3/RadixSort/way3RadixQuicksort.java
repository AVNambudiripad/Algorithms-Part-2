import edu.princeton.cs.algs4.In;
public class way3RadixQuicksort
{
    private static int charAt(String s,int index)
    {
        if (index < s.length()) return s.charAt(index);
        return -1;
    }
    private static int compare(int a,int b)
    {
        if (a == b) return 0;
        else if (a > b) return 1;
        return -1;
    }
    private static void swap(String[] a,int i,int j)
    {
        String temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void sort(String[] a)
    {
        sort(a,0,a.length-1,0);
    }
    private static void sort(String[] a,int low,int high,int d)
    {
        if (low >= high)
        return;
        int pivot=charAt(a[low],d);
        int i1=low+1,i2=high,i=low+1;
        while( i <= high && i1<=i2)
        {
            int temp=compare(charAt(a[i],d),pivot);
            if (temp==1)
            {
                swap(a,i,i2);
                i2--;
            }
            else if (temp == -1)
            {
                swap(a,i,i1);
                i1++;
                i++;
            }
            else i++;
        }
        swap(a,low,i1-1);
        
        sort(a,low,i1-2,d);
        if (pivot>=0) sort(a,i1-1,i2,d+1);
        sort(a,i2+1,high,d);
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