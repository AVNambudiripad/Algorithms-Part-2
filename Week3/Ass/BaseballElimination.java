import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowEdge;
public class BaseballElimination
{
    private final ArrayList<String> teams;                         //Match team names to their index numbers
    private final int num;                                         //Number of teams
    private final int[] wins;                                      //Wins of any team 'i'
    private final int[] losses;                                    //Losses of any team 'i'
    private final int[] remaining;                                 //Remaining games for any team 'i'
    private final int[][] games;                                   //Number of games left between 'i' and 'j'
    public BaseballElimination(String filename)                    // create a baseball division from given filename in format specified below
    {
        if (filename==null)
        throw new IllegalArgumentException();
        In in=new In(filename);
        num=Integer.parseInt(in.readLine().trim());
        teams=new ArrayList<>();
        wins=new int[num];
        losses=new int[num];
        remaining=new int[num];
        games=new int[num][num];
        for (int i=0;i<num && in.hasNextLine();i++)
        {
            String[] line=in.readLine().trim().split("\\s+");
            teams.add(line[0].trim());
            wins[i]=Integer.parseInt(line[1].trim());
            losses[i]=Integer.parseInt(line[2].trim());
            remaining[i]=Integer.parseInt(line[3].trim());
            for (int j=4;j<num+4;j++)
            {
                games[i][j-4]=Integer.parseInt(line[j].trim());
            }
        }
    }
    public int numberOfTeams()                        // number of teams
    {
        return num;
    }
    public Iterable<String> teams()                                // all teams
    {
        return teams;
    }
    public int wins(String team)                      // number of wins for given team
    {
        int i=teams.indexOf(team);
        if (i==-1)
        throw new IllegalArgumentException();
        return wins[i];
    }
    public int losses(String team)                    // number of losses for given team
    {
        int i=teams.indexOf(team);
        if (i==-1)
        throw new IllegalArgumentException();
        return losses[i];
    }
    public int remaining(String team)                 // number of remaining games for given team
    {
        int i=teams.indexOf(team);
        if (i==-1)
        throw new IllegalArgumentException();
        return remaining[i];
    }
    public int against(String team1, String team2)    // number of remaining games between team1 and team2
    {
        int i=teams.indexOf(team1);
        int j=teams.indexOf(team2);
        if (i==-1 || j==-1)
        throw new IllegalArgumentException();
        return games[i][j];
    }
    public boolean isEliminated(String team)              // is given team eliminated?
    {
        return certificateOfElimination(team)!=null;
    }
    private int convert1(int i,int C,int team)
    {
        if (team>=i ) team--;
        return team+C+1;
    }
    private int convert2(int i, int C, int vertex)
    {
        int x=vertex-C-1;
        if (x>=i) return (x+1);
        return x;
    }
    public Iterable<String> certificateOfElimination(String team)  // subset R of teams that eliminates given team; null if not eliminated
    {
        int i=teams.indexOf(team);
        if (i==-1)
        throw new IllegalArgumentException();
        int maxWin=wins[i]+remaining[i];
        
        //Check Trivial Case
        for (int j=0;j<num;j++)
        if (maxWin < wins[j])
        {
            Queue<String> q=new LinkedList<>();
            q.add(teams.get(j));
            return q;
        }
        
        int C=(num-1)*(num-2)/2;
        FlowNetwork g=new FlowNetwork(1 + C + (num-1) + 1);
        //In g, 0 is source;
        // [1 , C] are the pairs
        // [C+1 , C + num - 1] are the teams
        // [C+num] is the sink
        
        int count=1;  //The following loop initialises all the edges of the middle game pair vertices
        int temp=0;
        for (int j=0;j<num;j++)
        {
            if (j!=i)
            {
                for (int k=j+1;k<num;k++)
                {
                    if (k!=i)
                    {
                        FlowEdge e1=new FlowEdge(0,count,games[j][k]);
                        temp+=games[j][k];
                        FlowEdge e2=new FlowEdge(count,convert1(i,C,j),Double.POSITIVE_INFINITY);
                        FlowEdge e3=new FlowEdge(count,convert1(i,C,k),Double.POSITIVE_INFINITY);
                        g.addEdge(e1);
                        g.addEdge(e2);
                        g.addEdge(e3);
                        //System.out.println("count is "+count+" j:"+j+" and k:"+k+" converted into "+convert1(i,C,j)+" & "+convert1(i,C,k));
                        count++;
                    }
                }
            }
        }
        
        //The following loop initialisees the edges to sink
        for (int j=C+1;j<C+num;j++)
        {
            FlowEdge e=new FlowEdge(j,C+num,maxWin-wins[convert2(i,C,j)]);
            g.addEdge(e);
        }
        
        FordFulkerson ford=new FordFulkerson(g,0,C+num);
        if (temp == ford.value())
        return null;
        Queue<String> q=new LinkedList<>();
        for (int j=C+1;j<C+num;j++)
        {
            if (ford.inCut(j))
            q.add(teams.get(convert2(i,C,j)));
        }
        return q;        
    }
    
    public static void main(String[] args)
    {
        BaseballElimination obj=new BaseballElimination(args[0]);
        System.out.println("The number of teams is "+obj.numberOfTeams());
        int i=0;
        for (String s:obj.teams())
        {
            System.out.print(i+" "+s+" "+obj.wins(s)+" "+obj.losses(s)+" "+obj.remaining(s)+" ");
            for (int j=0;j<obj.numberOfTeams();j++)
            {
                System.out.print(obj.against(s,obj.teams.get(j))+" ");
            }
            System.out.println();
            i++;
        }
        
        System.out.println("\n");
        for (String s : obj.certificateOfElimination("Detroit"))
        System.out.println(s);
    }
}