import java.util.ArrayList;
public class k {
    public static void main(String[] args) {
        ArrayList<Pair> al1=new ArrayList<>();
        al1.add(new Pair(0,2));
        al1.add(new Pair(2,3));
        ArrayList<Pair> al2=new ArrayList<>(al1);
        al2.add(new Pair(4,3));
        for (Pair p:al1)
        {
            System.out.print(p.x+","+p.y+"     ");
        }
        System.out.println();
        for (Pair p:al2)
        {
            System.out.print(p.x+","+p.y+"     ");
        }
        System.out.println();
    }
}