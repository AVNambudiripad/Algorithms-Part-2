public class UnionFind
{
    private int[] parent; //Parent of element
    private int[] size;   //Number of children to a subtree rooted at a given vertex
    int count;  //Number of connected components
    public UnionFind(int N)
    {
        parent=new int[N];
        size=new int[N];
        count=N;
        for (int i=0;i<N;i++)
        parent[i]=i;
    }
    private int root(int x)
    {
        int temp=x;
        while (parent[temp]!=temp)
        {
            parent[temp]=parent[parent[temp]]; //Tree height reduction to get log* performance
            temp=parent[temp];
        }
        return temp;
    }
    public void union(int x,int y)
    {
        int r1=root(x);
        int r2=root(y);
        if (r1==r2) return;
        count--;
        if (size[r1] < size[r2])
        {
            parent[r1]=r2;
            size[r2]+=size[r1];
        }
        else
        {
            parent[r2]=r1;
            size[r1]+=size[r2];
        }
    }
    public boolean find(int x,int y)  //Are x and y connected
    {
        return root(x)==root(y);
    }
    public int count()
    {
        return count;
    }
}