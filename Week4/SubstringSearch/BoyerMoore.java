import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class BoyerMoore
{
    public static int indexOf(String pattern, String txt)
    {
        final int N=txt.length();
        final int M=pattern.length();
        //right[] stores the rightmost index of a char in the pattern
        //eg: in NEEDLE, right['D']=3, right['E']=5
        int[] right=new int[256];     //256 chars in Extended ASCII
        for (int i=0;i<256;i++)
        {
            right[i]=-1;
        } 
        for (int i=0;i<M;i++)
        {
            right[pattern.charAt(i)]=i;
        }

        int skip=0,i=0,j=0;
        for (;i<=N-M;)
        {
            j=M-1;
            for (;j>=0;j--)
            {
                if (txt.charAt(j+i) != pattern.charAt(j))
                {
                    skip=Math.max(1,j-right[txt.charAt(i+j)]);
                    i+=skip;
                    break;
                }
            }
            if (j==-1)
            break;
        }
        if (j==-1)
        return i;
        return -1;
    }
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc=new Scanner(new File(args[0]));
        StringBuilder txt=new StringBuilder();
        while (sc.hasNext())
        txt.append(sc.nextLine());
        System.out.println(txt);
        sc=new Scanner(System.in);
        System.out.print("Enter a String to search for    ");
        String s=sc.nextLine();
        System.out.println("Computed index :"+indexOf(s.trim(),txt.toString()));
        System.out.println("Actual index :" +txt.indexOf(s.trim()));
    }
}