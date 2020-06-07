import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

public class EulerCycle<Key>
{
    boolean eulerCycle;
    private class Edge
    {
        int key1;
        int key2;
        boolean visited;
        Edge(int k1,int k2) 
        {
            assert k1<=k2;  //To ensure there are no duplicate edges
            key1=k1;
            key2=k2;
            visited=false;
        }
        Integer otherEnd(int v)
        {
            if (v==key1) return key2;
            else if (v==key2) return key1;
            else return null;
        }
    }

    Deque<Edge> s;
    public EulerCycle(Graph<Key> g)
    {
        eulerCycle=hasCycle(g);
        s=new LinkedList<>();
        if (eulerCycle)
        {
            //h given below is basically an array containing the list of edges for any node
            HashMap<Integer,Queue<Edge>> h=new HashMap<>();  //map from index to list of edges
            
            //Initialisation - Part 1
            for (Key i:g.allNodes())
            {
                Queue<Edge> q=new LinkedList<>();
                h.put(g.keyToInt(i),q);
            }

            //Initialisation - Part 2
            for (Key i:g.allNodes())
            {
                int selfLoops=0; //Each self loop will be displayed twice in the adjacency list
                for (Key j:g.adjacent(i))
                {
                    int x=g.keyToInt(i),y=g.keyToInt(j);
                    if (x==y)  //Self loop
                    {
                        if (selfLoops%2==0)
                        {
                            h.get(x).add(new Edge(x,y));
                        }
                        selfLoops++;
                    }
                    else if (x < y)
                    {
                        Edge e=new Edge(x,y);
                        h.get(x).add(e);
                        h.get(y).add(e);
                    }
                }
            }

            int curr=0,prev=-1;  //curr and prev are int representations of keys
            Edge pr=null; //Previous edge
            //Heirholzer's Algorithm
            while (true)
            {
                Edge e=null;
                //Find an unvisited edge
                for(Edge i:h.get(curr))
                {
                    if (!i.visited && i!=pr)
                    {
                        e=i;
                        e.visited=true;
                        break;
                    }
                }
                if (e==null)  //No edges found. So backtrack
                {
                    Edge e1=s.removeFirst();
                    e1.visited=false;
                    pr=e1;
                    prev=curr;
                    curr=e1.otherEnd(curr);
                }
                else
                {
                    s.addFirst(e);
                    if (s.size()==g.E())
                    break;
                    prev=curr;
                    curr=e.otherEnd(curr);
                    pr=e;
                }
            }

        }
    }



    private boolean hasCycle(Graph<Key> g)
    {
        for (Key i:g.allNodes())
        {
            if (g.degree(i)%2==1)
            return false;
        }
        return true;
    }
    public boolean hasCycle()
    {
        return eulerCycle;
    }
    private void print(Edge e,Graph<Key> g)
    {
        System.out.println(g.intToKey(e.key1)+"   "+g.intToKey(e.key2));
    }
    public void print(Graph<Key> g)
    {
        if (s.isEmpty())
        System.out.println("There is no Euler Cycle");
        else
        {
            System.out.println("There exists an Eulerian Cycle");
            while (!s.isEmpty())
            {
                print(s.removeFirst(),g);
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        Character[] a={'A','B','C','D','E','F','G'};
        Graph<Character> g=new Graph<>(a);
        g.addEdge('A', 'B');
        g.addEdge('C', 'A');
        g.addEdge('A', 'F');
        g.addEdge('A', 'G');
        g.addEdge('G', 'E');
        g.addEdge('E', 'F');
        g.addEdge('D', 'E');
        g.addEdge('F', 'D');
        g.addEdge('B', 'C');
        g.addEdge('F', 'D');
        g.addEdge('D', 'E');
        g.print();

        EulerCycle<Character> obj=new EulerCycle<>(g);

        obj.print(g);
    }
}