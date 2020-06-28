import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class KnuthMorrisPratt
{
    private static void print(int[][] a)
    {
        for (int[] i:a)
        {
            for (int j:i)
            System.out.print(j+" ");
            System.out.println();
        }
        System.out.println();
    }
    private static int[][] DFSM(String pattern) //Deterministic Finite State Machine
    {
        int M=pattern.length();
        int[][] dfsm=new int[M][26];    //26 refers to 26 letters in the alphabet
        int[] state=new int[M];         //Helper array
        //First, fill the 0th state
        for (int i=0;i<26;i++)
        {
            if (i== (pattern.charAt(0)-'a'))
            dfsm[0][i]=1;
            else
            dfsm[0][i]=0;
        }
        //print(dfsm);

        //Creating state j after creating j-1 states
        for (int j=1;j<M;j++)
        {
            for (int i=0;i<26;i++)
            {
                if (i== (pattern.charAt(j)-'a'))
                dfsm[j][i]=j+1;
                else
                {
                    dfsm[j][i]=dfsm[state[j-1]][i];
                }
            }
            //print(dfsm);
            state[j]=dfsm[state[j-1]][pattern.charAt(j)-'a'];
        }
        return dfsm;
    }

    public static int indexOf(String pattern,String txt)   
    {
        //ASSUMPTION - txt consists of ONLY English letters (no other chars)
        //If we need all ASCII chars, then dfsm[][] will have 256 columns
        pattern=pattern.toLowerCase();
        txt=txt.toLowerCase();
        int M=pattern.length();
        int[][] dfsm=DFSM(pattern);
        int state=0,i=0;
        for (;i<txt.length();i++)
        {
            state=dfsm[state][txt.charAt(i)-'a'];
            if (state == M)
            break;
        }
        if (state == M)
        return i-M+1;
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