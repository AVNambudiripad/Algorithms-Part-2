import edu.princeton.cs.algs4.BinaryStdIn;

public class CircularSuffixArray {

    private final int length;
    private final int[] index;
    private final String s;

    private class helper implements Comparable<helper>
    {
        private int pointer;      //Between 0 and length-1
        public helper(int i)
        {
            pointer=i;
            //The string is s[i..length-1]+s[0..i]
        }
        public int compareTo(helper other)
        {
            int i=0,i1=this.pointer,i2=other.pointer;
            for (;i<length;i++,i1++,i2++)
            {
                if (i1==length)    i1=0;
                if (i2==length)    i2=0;
                if (s.charAt(i1) < s.charAt(i2))
                return -1;
                else if (s.charAt(i1) > s.charAt(i2))
                return 1;
            }
            return 0;
        }
        public String toString()
        {
            int count=0;
            StringBuilder sb=new StringBuilder();
            for (int i=pointer;count<length;count++,i++)
            {
                if (i==length)
                i=0;
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }

    // circular suffix array of s
    public CircularSuffixArray(String s)
    {
        if (s==null) throw new IllegalArgumentException();
        length=s.length();
        this.s=s;
        index=new int[length];
        helper[] suffixes=new helper[length];
        for (int i=0;i<length;i++)
        {
            index[i]=i;
            suffixes[i]=new helper(i);
        }

        quicksort(suffixes, index, 0, length-1);
    }

    // length of s
    public int length()
    {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i)
    {
        if (i<0 || i>=length) throw new IllegalArgumentException();
        return index[i];
    }

    private void exch(helper[] s,int[] i,int p,int q)
    {
        helper temp1=s[p];
        s[p]=s[q];
        s[q]=temp1;
        int temp2=i[p];
        i[p]=i[q];
        i[q]=temp2;
    }
    private void quicksort(helper[] s,int[] i,int low,int high)
    {
        if (low>=high) return;
        //Pivot is the first element
        int k=low+1;
        helper pivot = s[low];
        for (int j=low+1;j<=high;j++)
        {
            if (s[j].compareTo(pivot) < 0)
            {
                exch(s,i,j,k);
                k++;
            }
        }
        k=k-1;
        exch(s, i, low, k);
        quicksort(s, i, low, k-1);
        quicksort(s, i, k+1, high);
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        String s=BinaryStdIn.readString();
        System.out.println(s);
        CircularSuffixArray csa=new CircularSuffixArray(s);
        System.out.println(csa.length());
        System.out.println(csa.index(6));
    }

}