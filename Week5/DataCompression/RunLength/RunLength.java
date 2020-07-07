import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
public class RunLength
{
    //Compress to compressed.txt
    public static void compress(BinaryIn in)  //Read text from BinaryStdIn
    {
        int R = 256;
        int logR = 8;       //counts are represented by an 8-bit integer
        BinaryOut bOut=new BinaryOut("compressed.txt");
        boolean prev=false,curr=false;
        char count=0;
        while (!in.isEmpty())
        {
            curr = in.readBoolean();
            if (curr == prev)
            {
                count++;
                if (count == R)
                {
                    bOut.write(count, logR);
                    count=0;
                    prev=!prev;
                }
            }
            else
            {
                bOut.write(count, logR);
                prev=!prev;
                count=1;
            }
        }
        bOut.close();
    }
    public static void expand(BinaryIn in)
    {
        //Expand to expanded.txt
        boolean bit = false;
        char count =0;
        BinaryOut bOut=new BinaryOut("expanded.txt");
        while (!in.isEmpty())
        {
            count = in.readChar();
            for (int i=0;i<count;i++)
            bOut.write(bit);
            bit=!bit;
        }
        bOut.close();
    }

    public static void main(String[] args)
    {
        //Compress and then expand "data.txt"

        //First compress
        BinaryIn in1=new BinaryIn("data.txt");
        compress(in1);

        BinaryIn in2=new BinaryIn("compressed.txt");
        expand(in2);
    }
}