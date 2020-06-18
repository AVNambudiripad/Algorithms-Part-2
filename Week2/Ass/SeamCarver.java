import edu.princeton.cs.algs4.Picture;
public class SeamCarver {

    private Picture picture;
    private Picture transpose;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture)
    {
        if (picture==null) throw new IllegalArgumentException();
        this.picture=new Picture(picture);
        transpose=null;
    }
 
    // current picture
    public Picture picture()
    {
        return picture;
    }
 
    // width of current picture
    public int width()
    {
        return picture.width();
    }
 
    // height of current picture
    public int height()
    {
        return picture.height();
    }
 

    private int red(int rgb)                          
    {
        return ((rgb >> 16) & 0xFF);
    }
    private int green(int rgb)
    {
        return ((rgb >>  8) & 0xFF);
    }
    private int blue(int rgb)
    {
        return (rgb  & 0xFF);
    }
    private int sq(int x)                    //Square
    {
        return x*x;
    }
    private Picture transpose(Picture p)
    {
        Picture obj=new Picture(p.height(),p.width());
        for (int i=0;i<p.height();i++)
        {
            for (int j=0;j<p.width();j++)
            {
                obj.setRGB(i, j, p.getRGB(j, i));
            }
        }
        return obj;
    }


    // energy of pixel at column x and row y
    public double energy(int x, int y)
    {
        return energy(picture, x, y);
    }
    private double energy(Picture p,int x,int y)
    {
        if (x<0 || x>=p.width() || y<0 || y>=p.height())
        throw new IllegalArgumentException();
        else if (x==0 || x==p.width()-1 || y==0 || y==p.height()-1)
        return 1000;
        int c1=p.getRGB(x+1, y);
        int c2=p.getRGB(x-1, y);
        int c3=p.getRGB(x, y+1);
        int c4=p.getRGB(x, y-1);
        double X = sq(red(c1)-red(c2)) + sq(green(c1)-green(c2)) + sq(blue(c1)-blue(c2));
        double Y = sq(red(c3)-red(c4)) + sq(green(c3)-green(c4)) + sq(blue(c3)-blue(c4));
        return Math.sqrt(X+Y);
    }
 
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam()
    {
        if (transpose==null)
        transpose=transpose(picture);

        return findVerticalSeam(transpose);
    }
 
    // sequence of indices for vertical seam
    public int[] findVerticalSeam()
    {
        return findVerticalSeam(picture);
    }
    private int[] findVerticalSeam(Picture p)
    {
        int w=p.width(),h=p.height();

        double[][] en=new double [h][w];
        int[][] edgeTo=new int[h][w];
        double[][] distance=new double[h][w];

        for (int i=0;i<h;i++)
        {
            for (int j=0;j<w;j++)
            {
                en[i][j]=energy(p, j, i);
                distance[i][j]=Double.POSITIVE_INFINITY;
            }
        }

        //edgeTo[][] {-1 if parent is at (x-1,y-1); 0 if parent (x,y-1); 1 if parent(x+1,y-1)}

        for (int i=0;i<w;i++)
        {
            edgeTo[0][i]= 2;              //Special notation for top pixels
            distance[0][i]=en[0][i];
        }

        //INITIALISATION COMPLETE

        //COMPUTE DISTANCE AND EDGETO
        for (int i=0;i<h-1;i++)    //Last row does not have any children
        {
            for (int j=0;j<w;j++)     //0 and width are edges and so are not removed
            {
                if (j-1>=0)
                {
                    if (distance[i+1][j-1] > distance[i][j] + en[i+1][j-1])
                    {
                        distance[i+1][j-1]=distance[i][j] + en[i+1][j-1];
                        edgeTo[i+1][j-1]=1;
                    }
                }

                if (distance[i+1][j] > distance[i][j] + en[i+1][j])
                {
                    distance[i+1][j]=distance[i][j] + en[i+1][j];
                    edgeTo[i+1][j]=0;
                }

                if (j+1 < w)
                {
                    if (distance[i+1][j+1] > distance[i][j] + en[i+1][j+1])
                    {
                        distance[i+1][j+1]=distance[i][j] + en[i+1][j+1];
                        edgeTo[i+1][j+1]=-1;
                    }
                }
            }
        }


        double min=Double.POSITIVE_INFINITY;
        int minIndex=0;
        for (int i=0;i<w;i++)
        if (min > distance[h-1][i])
        {
            min=distance[h-1][i];
            minIndex=i;
        }

        int[] seam=new int[h];
        int col=minIndex;
        for (int i=h-1;i>=0;i--)
        {
            seam[i]=col;
            col=col+edgeTo[i][col];
        }
        return seam;
    }



    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam)
    {
        if (seam==null)                  throw new IllegalArgumentException();
        if (seam.length != width())     throw new IllegalArgumentException();
        if (height() <= 1)               throw new IllegalArgumentException();
        
        if (transpose==null)
        transpose=transpose(picture);

        transpose=removeVerticalSeam(seam, transpose);
        
        picture=transpose(transpose);
    }
 
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam)
    {
        if (seam==null)                  throw new IllegalArgumentException();
        if (seam.length != height())      throw new IllegalArgumentException();
        if (width() <= 1)                throw new IllegalArgumentException();
        
        picture=removeVerticalSeam(seam, picture);
        transpose=null;
    }
    private Picture removeVerticalSeam(int[] seam, Picture p)
    {
        Picture obj=new Picture(p.width()-1,p.height());

        int prev=seam[0];
        for (int i=0;i<p.height();i++)
        {
            for (int j=0;j<p.width()-1;j++)
            {
                if (j<seam[i])
                obj.setRGB(j, i, p.getRGB(j, i));
                else
                obj.setRGB(j, i, p.getRGB(j+1, i));
            }
            prev=seam[i];
        }

        return obj;
    }
 
    //  unit testing (optional)
    public static void main(String[] args)
    {

    }
 
 }