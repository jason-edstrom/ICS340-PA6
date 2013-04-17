import java.util.ArrayList;

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
        left = right = null;
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

    public <AnyType> int size( BinaryNode<AnyType> t )
    {
        if( t == null )
            return 0;
        else
            return 1 + size( t.left ) + size( t.right );
    }

    /**
     * Return the height of the binary tree rooted at t.
     */
    public <AnyType> int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    // Print tree rooted at current node using preorder traversal.
    public void printPreOrder(ArrayList<BinaryNode> binaryNodeArrayList)
    {
        System.out.println( element );       // Node
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
        System.out.println( element );
        binaryNodeArrayList.add(this);// Node
    }

    // Print tree rooted at current node using inorder traversal.
    public void printInOrder(ArrayList<BinaryNode> binaryNodeArrayList )
    {
        if( left != null )
            left.printInOrder( binaryNodeArrayList);            // Left
        System.out.println( element );       // Node
        binaryNodeArrayList.add(this);
        if( right != null )
            right.printInOrder(binaryNodeArrayList );           // Right
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


    // Data; accessible by other package routines
    AnyType             element;  // The data in the node
    BinaryNode<AnyType> left;     // Left child
    BinaryNode<AnyType> right;    // Right child
}