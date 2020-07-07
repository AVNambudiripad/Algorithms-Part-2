import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.Out;
public class DecodeHuffman
{
    public DecodeHuffman(String filename,String key)
    {
        //This is a lazy algo. Tries are better than HashMap
        HashMap<String,Character> h=new HashMap<>();  //Map encoding to char
        In in =new In(key);
        while (in.hasNextLine())
        {
            char c=in.readChar();
            //4 spaces
            in.readChar();
            in.readChar();
            in.readChar();
            in.readChar();
            if (in.hasNextChar())
            {
                String s=in.readString();
                h.put(s,c);
                //Then one new line
                in.readChar();
            }
        }
        /*
        for (String s:h.keySet())
        {
            System.out.println(h.get(s)+"    "+s);
        }*/

        BinaryIn bin=new BinaryIn(filename);
        String s="";
        StringBuilder text=new StringBuilder();
        while (!bin.isEmpty())
        {
            if (bin.readBoolean() ==true)
            s=s+"1";
            else
            s=s+"0";
            if (h.containsKey(s))
            {
                //System.out.println("HERE");
                text.append(h.get(s));
                s="";
            }
        }
        
        //System.out.println(text);
        Out out=new Out("decoded.txt");
        out.print(text.toString());
        out.close();
    }   

    public static void main(String[] args)
    {
        DecodeHuffman obj=new DecodeHuffman("encoded", "key.txt");
    }
}