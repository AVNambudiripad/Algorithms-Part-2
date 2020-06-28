import java.math.BigInteger;
public class k
{
    public static int gcd(int a,int b)
    {
        if (a==0) return b;
        return gcd(b%a,a);
    }
    public static void main(String[] args)
    {
        BigInteger c=new BigInteger("91");
        int count=0;
        for (int i=2;i<1000;i++)
        {
            if (gcd(i,91) != 1)
            {
                continue;
            }
            BigInteger num=new BigInteger(""+i);
            num=num.pow(67);
            int k=Integer.parseInt(num.remainder(c).toString());
            //System.out.print(i+"^67 mod 91 = "+k);
            if (i==k)
            {//System.out.println("  They are Equal");count++;}
            count++;}
            //else
            //System.out.println();
        }
        System.out.println("The count of Equals (exculing gcd =! 1) is "+count);
    }
}