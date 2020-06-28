//Brute Force Algorithm for Finding Substring
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Brute
{
    public static int indexOf(String pattern,String txt)
    {
        int N=txt.length();
        int M=pattern.length();
        int i=0,j=0;
        while(i<N)
        {
            if (txt.charAt(i) == pattern.charAt(j))
            j++;
            else
            {
                i-=j;                           //Backup
                j=0;
            }
            i++;
            if (j==M) break;
        }
        if (j==M) return i-M;
        return -1;
    }
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc1=new Scanner(new File(args[0]));
        Scanner sc2=new Scanner (System.in);
        StringBuilder txt=new StringBuilder();
        while (sc1.hasNext())
        txt.append(sc1.nextLine());
        System.out.println(txt);
        System.out.print("Enter a String to search for    ");
        String s=sc2.nextLine();
        System.out.println(indexOf(s.trim(),txt.toString()));
    }
}