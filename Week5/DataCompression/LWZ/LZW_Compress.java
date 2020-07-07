//import edu.princeton.cs.algs4.TST;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.In;
public class LZW_Compress
{
    //Assumption - Alphabet is ASCII (7-bit)
    public static void compress(String text)
    {
        //Make a new file compressed (no file extension)

        final int W = 12;                //Max bit size of a word
        final int L = 4096;              //L = 2^W
        final int R = 128;              //Radix  (128 = ASCII)
        TST<Integer> t=new TST<>();
        for (int i=0;i<R;i++)
        t.put(""+(char)i, i);
        text = text + (char)R;                //(char)R is the stop char
        int count = R+1;

        BinaryOut bOut=new BinaryOut("compressed");
        for (int i=0;i<text.length()-1;)
        {
            String s = t.longestPrefixOf(text,i);
            int l = s.length();
            if (count < L)
            {
                t.put(s+text.charAt(i+l),count);
                count++;
            }
            i+=l;
            bOut.write(t.get(s),W);
        }
        bOut.write((char)R, W);
        bOut.close();
    }
    public static void main(String[] args)
    {
        In in=new In("data.txt");
        compress(in.readAll().trim());
    }
}