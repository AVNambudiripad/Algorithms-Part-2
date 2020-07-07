import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class RabinKarp
{
    private static int hash(String s,int M,int R,int Q)
    {
        int hash=0;
        for (int j=0;j<M;j++)
        {
            hash=(hash*R+(s.charAt(j)-'a'))%Q;
        }
        return hash;
    }
    public static int indexOf(String pattern,String txt)
    {
        //pattern and txt are only lowercase english chars
        final int Q=10000877;           //Prime number
        final int M=pattern.length();
        int RM=1;
        for (int i=0;i<M-1;i++)
        {
            RM=(RM*26) % Q;
            //System.out.println("RM = "+RM);
        }
        final int patternHash=hash(pattern,M,26,Q);
        int hash=0;
        StringBuilder  s=new StringBuilder();
        for (int i=0;i<txt.length();i++)     //No backup Monte Carlo
        {
            if (i>M-1)
            {
                int temp=s.charAt(0)-'a';
                s.deleteCharAt(0);
                s.append(txt.charAt(i));
                hash=((hash +Q - (RM*temp)%Q)*26 +(txt.charAt(i)-'a'))%Q;
                if (hash==patternHash)
                return i-M+1;
            }
            else if (i<M-1)
            {
                s.append(txt.charAt(i));
            }
            else 
            {
                s.append(txt.charAt(i));
                hash=hash(s.toString(),M,26,Q);
                if (hash==patternHash) return 0;
            }
            
            //System.out.println(s+"   "+hash);
        }
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