/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA6
 * Date: 4/15/13
 * Time: 6:56 PM
 * Java Class: PACKAGE_NAME
 */

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// void removeMin( )      --> Remove minimum item
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Exceptions are thrown by insert, remove, and removeMin if warranted

import java.util.ArrayList;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree.
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree..
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Remove minimum item from the tree.
     * @throws ItemNotFoundException if tree is empty.
     */
    public void removeMin( )
    {
        root = removeMin( root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        return elementAt( findMin( root ) );
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public AnyType findMax( )
    {
        return elementAt( findMax( root ) );
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public AnyType find( AnyType x )
    {
        return elementAt( find( x, root ) );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private AnyType elementAt( BinaryNode<AnyType> t )
    {
        return t == null ? null : t.element;
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            t = new BinaryNode<AnyType>( x );
        else if( x.compareTo( t.element ) < 0 )
            t.left = insert( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = insert( x, t.right );
        else{
            t.increaseDuplicateCount();
            t.hasDuplicate = true;
            t.duplicate = duplicate(x , t.duplicate);
        }
        return t;
    }
    //throw new DuplicateItemException( x.toString( ) );  // Duplicate

    /**
     * Internal method to handle duplicates into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected  BinaryNode<AnyType> duplicate(AnyType x, BinaryNode<AnyType> t){

        if (t == null){
            t = new BinaryNode<AnyType>(x);
        } else{
          t.duplicate = duplicate(x, t.duplicate);
        }

        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            throw new ItemNotFoundException( x.toString( ) );
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = removeMin( t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if t is empty.
     */
    protected BinaryNode<AnyType> removeMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            throw new ItemNotFoundException( );
        else if( t.left != null )
        {
            t.left = removeMin( t.left );
            return t;
        }
        else
            return t.right;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.left != null )
                t = t.left;

        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode<AnyType> find( AnyType x, BinaryNode<AnyType> t )
    {

        while( t != null )
        {

            if( x.compareTo( t.element ) < 0 )
                t = t.left;
            else if( x.compareTo( t.element ) > 0 )
                t = t.right;
            else {
                t.wasFound = true;
                return t;    // Match
            }
        }

        return null;         // Not found
    }

    public <AnyType> int getTreeSize (){
        return root.size(root);
    }

    public <AnyType> int getTreeHeight(){
        return root.height(root);
    }

    public void clearWasFound(){
        root.clearFound();
    }

    public ArrayList<BinaryNode> printTreePreOrder(){
        ArrayList<BinaryNode> binaryNodeArrayList = new ArrayList<BinaryNode>() ;
        root.printPreOrder(binaryNodeArrayList);
        return binaryNodeArrayList;
    }

    public ArrayList<BinaryNode> printTreeInOrder(){
        ArrayList<BinaryNode> binaryNodeArrayList = new ArrayList<BinaryNode>() ;
        root.printInOrder(binaryNodeArrayList);
        return binaryNodeArrayList;
    }

    public ArrayList<BinaryNode> printTreePostOrder(){
        ArrayList<BinaryNode> binaryNodeArrayList = new ArrayList<BinaryNode>() ;
        root.printPostOrder(binaryNodeArrayList);
        /*
        String postOrder =null;
        for (BinaryNode node : binaryNodeArrayList){
            if (postOrder == null){
                 postOrder = node.getElement();
            }else{
            postOrder = postOrder + ", " + node.getElement();
            }
        }*/
       return binaryNodeArrayList;
    }

    public ArrayList<BinaryNode> printTreeLevelOrder(){
        ArrayList<BinaryNode> binaryNodeArrayList = new ArrayList<BinaryNode>() ;
        root.printLevelOrder(root, binaryNodeArrayList);
        return binaryNodeArrayList;
    }

    public String buildAString (ArrayList<BinaryNode> nodes){
        StringBuilder temp = new StringBuilder();

        for (BinaryNode t : nodes){
            temp.append(" " + t.getElement());
            if(t.duplicate != null){
                temp.append("*");
            }
        }

        return temp.toString();

    }

    public BinaryNode <AnyType> getRoot (){
        return root;
    }

    public String toString(){
        StringBuilder temp = new StringBuilder("");
        temp.append("1. Number of Nodes: " + getTreeSize() + " nodes \n" );
        temp.append("\n2. Number of Elements: " + getElementsCount() + " elements \n" );
        temp.append("\n3. Tree Height: " + getTreeHeight() + " \n");
        temp.append("\n4. Number of Internal Nodes: " + getInternalNodeCount() + " \n");
        temp.append("\n5. Number of External Nodes: " + getExternalNodeCount() + " \n");
        temp.append("\n6. Internal Path Length: " + getInternalPathLength() + " \n");
        temp.append("\n7. Average Depth of the Nodes: " + getAverageDepth() + " \n");
        temp.append("\n8. In-Order Traversal Path:" + buildAString(printTreeInOrder()) + " \n");
        temp.append("\n9. Post-Order Traversal Path:" + buildAString(printTreePostOrder()) + " \n");
        temp.append("\n10. Pre-Order Traversal Path:" + buildAString(printTreePreOrder()) + " \n");
        temp.append("\n11. Level-Order Traversal Path:" + buildAString(printTreeLevelOrder()) + " \n");
        temp.append("\n\n       Asterisks ( * ) denote nodes with duplicates\n");
     return temp.toString();
    }

    public int getExternalNodeCount() {

    return externalNodeCount(root);
    }

     private int externalNodeCount(BinaryNode root){

        // BinaryNode left = root.getLeft();


         //BinaryNode right = root.getRight();

         if (root == null){
             return 0;
         } else if ((root.getLeft() == null) && (root.getRight() == null) ){
             return 1;
         } else{
         return externalNodeCount(root.getRight()) + externalNodeCount(root.getLeft());
         }
     }


    public int getInternalNodeCount() {

        return internalNodeCount(root);
    }

    private int internalNodeCount(BinaryNode root){
        return getTreeSize() - getExternalNodeCount();
    }



    public int getElementsCount(){
         return countElements(root);
    }
    private int countElements(BinaryNode root) {

        BinaryNode duplicate = root.getDuplicate();
        BinaryNode right = root.getRight();
        BinaryNode left = root.getLeft();
        int c = 1;
        if (duplicate != null){
            c += countElements(duplicate);
        }
        if (right != null){
            c += countElements(right);
        }
        if (left != null){
            c += countElements(left);

        }

        return c;
    }
    public int getInternalPathLength(){

    return internalPathLength(root, 0);
}
    private int internalPathLength (BinaryNode<AnyType> root, int curLevel )
    {
      if (root == null){
          return 0;
      }
     if ((root.getLeft() == null) &&  (root.getRight() == null)) {
        return 0;
    }
        return curLevel + internalPathLength(root.getLeft(), curLevel+1) + internalPathLength(root.getRight(), curLevel +1);

    }

    public double getAverageDepth(){
        return round(averageDepth(),2);
    }

    private double averageDepth() {
        return (double) getInternalPathLength() / getTreeSize();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /** The tree root. */
    protected BinaryNode<AnyType> root;




}