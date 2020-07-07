import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
public class LZW_Expand
{
    public static void expand()
    {
        //Read from "compressed" and extract to "extracted.txt"
        
        final int W = 12;                //Max bit size of a word
        final int L = 4096;              //L = 2^W
        final int R = 128;              //Radix  (128 = ASCII)
        String[] t = new String[L];
        for (int i=0;i<=R;i++)
        t[i]=""+(char)i;
        
        BinaryIn bin=new BinaryIn("compressed");
        BinaryOut bOut=new BinaryOut("extracted.txt");
        String prev="";
        int i = R+1;
        while (!bin.isEmpty() && (i+1)<L)
        {
            int code = bin.readInt(W);
            System.out.println(Integer.toHexString(code));
            if (code == (char)R)  break;    //End-of-file
            if (code < i)
            {
                String s = t[code];
                if (prev!="")
                {
                    t[i++] = prev+s.charAt(0);
                }
                prev = s;
            }
            else        //Special Case
            {
                t[i++] = prev + prev.charAt(0);
                prev = prev + prev.charAt(0);
            }

            bOut.write(prev);
        }

        bOut.close();
    }

    public static void main(String[] args)
    {
        expand();
    }
}