import java.util.Arrays;

public class k {
    public static void print(char[] a)
    {
        for (char i:a)
        System.out.print(i);
        System.out.println();
    }
    public static void main(String[] args) {
        char[] t= {'a','d','b','c','f'};
        print(t);
        char[] s=Arrays.copyOf(t, t.length);
        Arrays.sort(s);
        print(t);
        print(s);
    }
}