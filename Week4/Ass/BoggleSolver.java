import java.util.HashSet;
import java.util.ArrayList;
public class BoggleSolver
{
    private final TrieBetter t;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary)
    {
        t=new TrieBetter();
        for (String s:dictionary)
        t.add(s);
    }

    private boolean isValid(int i,int j,int m,int n)
    {
        return (i>=0 && i<m && j>=0 && j<n);
    }
    private boolean contains(ArrayList<Pair> al,int i,int j)
    {
        for (Pair p:al)
        if (p.equal(i, j))
        return true;
        return false;
    }
    

    private void dfs(BoggleBoard board,HashSet<String> h, ArrayList<Pair> al1,String s,int i,int j,int m,int n)
    {
        if (board.getLetter(i, j)=='Q')
        s=s+"QU";
        else
        s=s+board.getLetter(i, j);
        if (s.length()>2 && t.contains(s))
        h.add(s);
        if (t.containPrefix(s))
        {
            ArrayList<Pair> al=new ArrayList<>(al1);
            al.add(new Pair(i,j));
            if (isValid(i+1, j, m, n) && !contains(al, i+1, j))
            {
                dfs(board,h,al,s,i+1,j,m,n);
            }
            if (isValid(i-1, j, m, n) && !contains(al, i-1, j))
            {
                dfs(board,h,al,s,i-1,j,m,n);
            }
            if (isValid(i, j+1, m, n) && !contains(al, i, j+1))
            {
                dfs(board,h,al,s,i,j+1,m,n);
            }
            if (isValid(i, j-1, m, n) && !contains(al, i, j-1))
            {
                dfs(board,h,al,s,i,j-1,m,n);
            }
            if (isValid(i+1, j+1, m, n) && !contains(al, i+1, j+1))
            {
                dfs(board,h,al,s,i+1,j+1,m,n);
            }
            if (isValid(i+1, j-1, m, n) && !contains(al, i+1, j-1))
            {
                dfs(board,h,al,s,i+1,j-1,m,n);
            }
            if (isValid(i-1, j+1, m, n) && !contains(al, i-1, j+1))
            {
                dfs(board,h,al,s,i-1,j+1,m,n);
            }
            if (isValid(i-1, j-1, m, n) && !contains(al, i-1, j-1))
            {
                dfs(board,h,al,s,i-1,j-1,m,n);
            }
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        HashSet<String> h=new HashSet<>();
        final int m=board.rows();
        final int n=board.cols();
        for (int i=0;i<m;i++)
        {
            for (int j=0;j<n;j++)
            {
                String s="";
                ArrayList<Pair> al=new ArrayList<>();
                dfs(board,h,al,s,i,j,m,n);
            }
        }
        return h;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
    {
        if (t.contains(word))
        {
            int length=word.length();
            switch(length)
            {
                case 0:
                case 1:
                case 2:
                return 0;
                case 3:
                case 4:
                return 1;
                case 5:
                return 2;
                case 6:
                return 3;
                case 7:
                return 5;
                default:
                return 11;
            }
        }
        return 0;
    }
}
