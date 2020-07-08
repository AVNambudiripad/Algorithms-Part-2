import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class MoveToFront {

    private static final int R = 256;        
    private static final int logR = 8;
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode()
    {
        char[] seq=new char[R];
        for (int i=0;i<R;i++)
        seq[i]=(char)i;
        while (!BinaryStdIn.isEmpty())
        {
            char c = BinaryStdIn.readChar(logR);
            int index =0;
            for (int j=0;j<R;j++)
            if (seq[j]==c)
            {
                index = j;
                break;
            }
            for (int j=index;j>0;j--)
            {
                seq[j]=seq[j-1];
            }
            seq[0] = c;
            BinaryStdOut.write(index, logR);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode()
    {
        char[] seq=new char[R];
        for (int i=0;i<R;i++)
        seq[i]=(char)i;
        while (!BinaryStdIn.isEmpty())
        {
            int index = BinaryStdIn.readInt(logR);
            char c = seq[index];
            BinaryStdOut.write(c, logR);
            for (int j=index;j>0;j--)
            seq[j]=seq[j-1];
            seq[0]=c;
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args)
    {
        char c = args[0].trim().charAt(0);
        if (c=='-')
        encode();
        else if (c=='+')
        decode();
    }

}