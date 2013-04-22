import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA6
 * Date: 4/15/13
 * Time: 6:55 PM
 * Java Class: PACKAGE_NAME
 */


// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// of this package.

class BinaryNode<AnyType>
{
    // Constructor
    BinaryNode( AnyType theElement )
    {
        element = theElement;
        duplicate = left = right = null;
        x = -1;
        y = -1;
        hasDuplicate = false;
        wasFound = false;
        duplicateCount = 0;
    }

    public AnyType getElement( )
    {
        return element;
    }

    public BinaryNode<AnyType> getLeft( )
    {
        return left;
    }

    public BinaryNode<AnyType> getRight( )
    {
        return right;
    }

    public static <AnyType> int size( BinaryNode<AnyType> t )
    {
        if( t == null )
            return 0;
        else
            return 1 + size( t.left ) + size( t.right );
    }

    /**
     * Return the height of the binary tree rooted at t.
     */
    public static <AnyType> int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    // Print tree rooted at current node using preorder traversal.
    public void printPreOrder(ArrayList<BinaryNode> binaryNodeArrayList)
    {
        //System.out.println( element );       // Node
        binaryNodeArrayList.add(this);
        if( left != null )
            left.printPreOrder(binaryNodeArrayList );           // Left
        if( right != null )
            right.printPreOrder(binaryNodeArrayList );          // Right
    }


    // Print tree rooted at current node using postorder traversal.
    public void printPostOrder(ArrayList<BinaryNode> binaryNodeArrayList )
    {
        if( left != null )
            left.printPostOrder(binaryNodeArrayList );          // Left
        if( right != null )
            right.printPostOrder(binaryNodeArrayList );         // Right
        //System.out.println( element );
        binaryNodeArrayList.add(this);// Node
    }

    // Print tree rooted at current node using inorder traversal.
    public void printInOrder(ArrayList<BinaryNode> binaryNodeArrayList )
    {
        if( left != null )
            left.printInOrder( binaryNodeArrayList);            // Left
        //System.out.println( element );       // Node
        binaryNodeArrayList.add(this);
        if( right != null )
            right.printInOrder(binaryNodeArrayList );           // Right
    }


    //Level Order printing
    public  void printLevelOrder(BinaryNode<AnyType> n, ArrayList<BinaryNode> binaryNodeArrayList)
    {
        Queue<BinaryNode<AnyType>> nodequeue = new LinkedList<BinaryNode<AnyType>>();
        if (n != null)
            nodequeue.add(n);
        while (!nodequeue.isEmpty())
        {
            BinaryNode<AnyType> next = nodequeue.remove();
            //System.out.print(next.data + " ");
            binaryNodeArrayList.add(next);
            if (next.getLeft() != null)
            {
                nodequeue.add(next.getLeft());
            }
            if (next.getRight() != null)
            {
                nodequeue.add(next.getRight());
            }
        }
    }

    public void clearFound(){
        if(left != null){
            left.clearFound();   //Left
        }
            if (wasFound){
                wasFound = false;    //clear wasFound
            }

        if(right != null){
            right.clearFound(); //Right
        }
    }

    public void setElement( AnyType x )
    {
        element = x;
    }

    public void setLeft( BinaryNode<AnyType> t )
    {
        left = t;
    }

    public void setRight( BinaryNode<AnyType> t )
    {
        right = t;
    }

    public void setDuplicateCount (int i){
        duplicateCount = i;
    }
    public void increaseDuplicateCount(){
        duplicateCount++;
    }

    public int getX(){
        return x;
    }

    public BinaryNode getDuplicate(){
        return duplicate;
    }

    public int getY(){
        return y;
    }

    public int getDepth(){
        return depth;
    }

    public void setX(int _x){
        x = _x;
    }

    public void setY(int _y){
        y = _y;
    }

    public void setDepth(int _d){
        depth = _d;
    }
    // Data; accessible by other package routines
    AnyType             element;  // The data in the node
    BinaryNode<AnyType> left;     // Left child
    BinaryNode<AnyType> right;    // Right child
    BinaryNode<AnyType> duplicate; //duplicate
    int                 duplicateCount;
    private int x;
    private int y;
    private int depth;
    boolean hasDuplicate;
    boolean wasFound;
}