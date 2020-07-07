import java.util.HashSet;
public class k {
    public static void main(String[] args) {
        HashSet<String> h1=new HashSet<>();
        h1.add("dog");
        h1.add("cat");
        h1.add("mouse");
        HashSet<String> h2=(HashSet<String>)h1.clone();
        h2.add("rabbit");
        for (String s:h1)
        System.out.print(s+"   ");
        System.out.println();
        for (String s:h2)
        System.out.print(s+"   ");
        System.out.println();
    }
}