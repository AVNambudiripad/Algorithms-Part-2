

//THIS HAS BEEN COPY PASTED FROM MY WORK ON PRIM'S ALGORITHM
//But with "Edge" replaced with "Double"

public class IndexMinPriorityQueue //Helper class for Prim's Algorithm
{
    private Double[] edge;               //For vertex 'v', what is the edge associated with it 
    private int[] vertexToHeap;        //For vertex 'v', what is the heap index
    private int[] heapToVertex;        //For heap index 'i', what is the associated vertex
    private final int capacity;        //Max capacity of the Priority Queue
    private int size;                  //How many elements are there in the PQ
    public IndexMinPriorityQueue(int N)
    {
        capacity=N;
        vertexToHeap=new int[capacity];
        heapToVertex=new int[capacity];
        size=0;
        edge=new Double[capacity];

        for (int i=0;i<capacity;i++)
        {
            vertexToHeap[i]=-1;
            heapToVertex[i]=-1;
        }
    }


    private int compare (int vertex1, int vertex2)
    {
        if (!contains(vertex2)) return -1;           //Just a special fix for swimDown
        return edge[vertex1].compareTo(edge[vertex2]);
    }
    private int parent(int i)
    {
        if (i==0) return 0;
        return (i-1)/2;
    }
    private int leftChild(int i)    { return (1+2*i); }
    private int rightChild(int i)   { return (2+2*i); }
    private void swap(int[] a,int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    private void swap(final int i1,final int i2)   //Swap elements at heap index i1 and i2
    {
        vertexToHeap[heapToVertex[i1]]=i2;
        vertexToHeap[heapToVertex[i2]]=i1;
        swap(heapToVertex,i1,i2);
    }


    private void swimUp(int i)
    {
        while (true)
        {
            if (compare(heapToVertex[i],heapToVertex[parent(i)])  < 0 )
            {
                swap(i,parent(i));
                i=parent(i);
            }
            else
            return;
        }
    }
    private void swimDown(int i)
    {
        if (!contains(heapToVertex[i])) return;
        while (true)
        {
            if (compare(heapToVertex[i],heapToVertex[leftChild(i)]) > 0)
            {
                swap(i,leftChild(i));
                swimDown(leftChild(i));
            }
            else if (compare(heapToVertex[i],heapToVertex[rightChild(i)]) > 0)
            {
                swap(i,rightChild(i));
                swimDown(rightChild(i));
            }
            else
            return;
        }
    }




    public boolean contains(int vertex)
    {
        if (vertex <0 || vertex >= capacity) return false;
        return vertexToHeap[vertex] != -1;
    }
    public void insert(int vertex, Double e)
    {
        if (contains(vertex))
        {
            if (e.compareTo(edge[vertex]) < 0 )
            {
                edge[vertex]=e;
                swimUp(vertexToHeap[vertex]);
            }
        }
        else
        {
            edge[vertex]=e;
            heapToVertex[size]=vertex;       //Add to next free slot
            vertexToHeap[vertex]=size;
            swimUp(size);
            size++;
        }
    }
    public BindVertexAndDoubleTogether remove()
    {
        if (size == 0) return null;
        size--;
        swap(0,size);       //Swap first and last element
        int vertex=heapToVertex[size];
        Double e=edge[vertex];
        heapToVertex[size]=-1;
        vertexToHeap[vertex]=-1;
        edge[vertex]=null;
        swimDown(0);

        BindVertexAndDoubleTogether obj=new BindVertexAndDoubleTogether(vertex, e);
        return obj;
    }
    public boolean isEmpty()
    {
        return size==0;
    }

    public void print()   //For debugging purposes
    {
        for (int i=0;i<size;i++)
        {
            System.out.println("Vertex : "+heapToVertex[i]+" "+edge[heapToVertex[i]].toString());
        }
    }
}