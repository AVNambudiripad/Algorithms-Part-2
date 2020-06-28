public class KeyIndexedCounting
{
    public static void sort(int[] a, int R)
    {
        //All numbers in a[] are between 0 and R-1
        int[] aux=new int[a.length];
        int[] count=new int[R+1];
        
        for (int i=0;i<a.length;i++)
        count[a[i]+1]++;
        for (int i=1;i<count.length;i++)
        count[i]+=count[i-1];
        for (int i=0;i<a.length;i++)
        aux[count[a[i]]++] = a[i];
        for (int i=0;i<a.length;i++)
        a[i]=aux[i];
    }
    public static void print(int[] a)
    {
        for (int i=0;i<a.length;i++)
        System.out.print(a[i]+" ");
        System.out.println();
    }
    public static void main(String[] args)
    {
        int[] a1 = {7,6,7,4,2,1,0,9,5,2,6,2,1,6,8,9,1};
        print(a1);
        sort(a1,10);
        print(a1);
    }
}