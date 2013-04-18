import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jason Edstrom
 * Class: ICS 340
 * Assignment: ICS340-PA6
 * Date: 4/15/13
 * Time: 7:29 PM
 * Java Class: PACKAGE_NAME
 */
public class BSTRenderGUI extends JFrame implements ActionListener {

    private JLabel lblInput = new JLabel("Input String: ");
    private JLabel lblMessage = new JLabel(" ");
    private JTextField jtfInput = new JTextField(40);
    private JButton jbtDraw = new JButton("Draw");
    private JButton jbtSearch = new JButton("Search");
    private JButton jbtDelete = new JButton("Delete");
    private JButton jbtString = new JButton("toString()");
    private JButton jbtClear = new JButton("Clear Tree");
    private JPanel inputPanel = new JPanel();
    private JPanel drawPanel = new JPanel();
    private JScrollPane jspDrawWindow;
    private BinarySearchTree tree = new BinarySearchTree();
    private int countX;

    public BSTRenderGUI(){
        initComponents();
    }

    /**
     * Build the UI
     */
    public void initComponents(){

        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints ip = new GridBagConstraints();
        ip.insets = new Insets(5,5,0,5);
        ip.gridx = 0;
        ip.gridy = 0;
        inputPanel.add(lblInput,ip);
        ip.gridx++;
        inputPanel.add(jtfInput,ip);
        ip.gridx = 0;
        ip.gridy++;
        ip.gridwidth = 2;
        ip.insets = new Insets(0,5,5,5);
        jbtClear.setEnabled(false);
        jbtDelete.setEnabled(false);
        jbtString.setEnabled(false);
        jbtSearch.setEnabled(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jbtDraw);
        buttonPanel.add(jbtSearch);
        buttonPanel.add(jbtDelete);
        buttonPanel.add(jbtString);
        buttonPanel.add(jbtClear);
        inputPanel.add(buttonPanel,ip);
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        jbtDraw.addActionListener(this);
        jbtDelete.addActionListener(this);
        jbtSearch.addActionListener(this);
        jbtString.addActionListener(this);
        jbtClear.addActionListener(this);


        jspDrawWindow = new JScrollPane(drawPanel);
        jspDrawWindow.setPreferredSize(new Dimension(600,300));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        c.gridx = 0;
        c.gridy = 0;
        add(inputPanel,c);
        c.gridy++;
        add(lblMessage,c);
        c.gridy++;
        add(jspDrawWindow,c);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main( String args[] )
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BSTRenderGUI().setVisible(true);
            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        System.out.println(obj);

        if (obj == jbtDraw){
            drawBST();
        }

        if (obj == jbtDelete){
              deleteTreeNode();
        }

        if (obj == jbtSearch){
            searchTree();
        }

        if (obj == jbtString){
            printToString();
        }

        if (obj == jbtClear){
             clear();
        }
    }



    private void deleteTreeNode() {
        if(!jtfInput.getText().isEmpty()){
            if (jtfInput.getText().length() > 1){
                lblMessage.setForeground(Color.red);
                lblMessage.setText("ERROR: Input needs to be a single character in order to delete the node.");
            } else{
                lblMessage.setText(" ");
                lblMessage.repaint();
                char[] input = jtfInput.getText().toLowerCase().toCharArray();
                //createBST(input);
                try{
                    tree.remove(input[0]);
                    BinaryNode root = tree.getRoot();
                    countX = -1;
                    setCoords(root, 0);
                    TreePanel treePanel = new TreePanel(root);
                    drawPanel.removeAll();
                    drawPanel.add(treePanel);
                    drawPanel.revalidate();
                    drawPanel.repaint();
                    lblMessage.setForeground(Color.green);
                    lblMessage.setText("");
                    tree.clearWasFound();
                } catch (ItemNotFoundException e){
                    lblMessage.setForeground(Color.red);
                    lblMessage.setText("ERROR: \"" + input[0] + "\" was not found");
                }



                }
            }
       // }
        else{
            lblMessage.setForeground(Color.red);
            lblMessage.setText("ERROR: Input is empty.");
        }
    }

    private void searchTree() {
        if(!jtfInput.getText().isEmpty()){
            if (jtfInput.getText().length() > 1){
                lblMessage.setForeground(Color.red);
                lblMessage.setText("ERROR: Input needs to be a single character in order to search.");
            } else{
            lblMessage.setText(" ");
            lblMessage.repaint();
            char[] input = jtfInput.getText().toLowerCase().toCharArray();

            tree.find(input[0]);
            if ((tree.find(input[0])) == null){
                lblMessage.setForeground(Color.red);
                lblMessage.setText("ERROR: \"" + input[0] + "\" was not found");
            }else{
            BinaryNode root = tree.getRoot();

            countX = -1;
            setCoords(root, 0);
            TreePanel treePanel = new TreePanel(root);
            drawPanel.removeAll();
            drawPanel.add(treePanel);
            drawPanel.revalidate();
            drawPanel.repaint();
            lblMessage.setForeground(Color.green);
            lblMessage.setText("");
            tree.clearWasFound();
            }
            }
        }
        else{
            lblMessage.setForeground(Color.red);
            lblMessage.setText("ERROR: Input is empty.");
        }
    }


    private void clear() {
        drawPanel.removeAll();
        jbtClear.setEnabled(false);
        jbtDelete.setEnabled(false);
        jbtString.setEnabled(false);
        jbtSearch.setEnabled(false);
        jbtDraw.setEnabled(true);
        drawPanel.revalidate();
        drawPanel.repaint();
        lblMessage.setForeground(Color.green);
        lblMessage.setText("Tree and Results cleared");
        tree.makeEmpty();
    }



    public <AnyType> int countDuplicates ( BinaryNode<AnyType> t){
        if( t == null )
            return 0;
        else
            return 1 + countDuplicates(t.duplicate);

    }


    /**
     * Adds coordinates to the nodes at the root provided and below. <br>
     * These coordinates are used by the drawXtree methods above to plot<br>
     * the nodes and edges on the draw window. Simply: the x-coordinate <br>
     * is set counting through an in-order traversal and the y-coordinate<br>
     * is set as the depth of the node.
     * @param node root node to begin at
     * @param depth depth to start from (typically 0)
     */
    public void setCoords(BinaryNode<Character> node, int depth){
        if(node.getLeft() != null){
            setCoords(node.getLeft(), depth+1);
        }
        node.setX(++countX);
        node.setY(depth);
        if(node.getRight() != null){
            setCoords(node.getRight(), depth+1);
        }
    }


    /**
     * Draw a binary-search tree. Duplicate characters are simply ignored.
     */
    public void drawBST(){
        if(!jtfInput.getText().isEmpty()){
            lblMessage.setText(" ");
            lblMessage.repaint();
            char[] input = jtfInput.getText().toLowerCase().toCharArray();
            createBST(input);
            BinaryNode root = tree.getRoot();
            countX = -1;
            setCoords(root, 0);
            TreePanel treePanel = new TreePanel(root);
            drawPanel.removeAll();
            drawPanel.add(treePanel);
            jbtClear.setEnabled(true);
            jbtDelete.setEnabled(true);
            jbtString.setEnabled(true);
            jbtSearch.setEnabled(true);
            jbtDraw.setEnabled(false);
            drawPanel.revalidate();
            drawPanel.repaint();
            lblMessage.setForeground(Color.green);
            lblMessage.setText("Binary Search Tree for " + jtfInput.getText() + " in place: Search, toString, Delete or Clear");
            jtfInput.setText("");
        }
        else{
            lblMessage.setForeground(Color.red);
            lblMessage.setText("ERROR: Input is empty.");
        }
    }

    private void printToString(){
             System.out.println(tree);
            String treeString = tree.toString();
            JFrame jf = new JFrame ("toString()");
            jf.setSize (450 , 450);
            jf.setResizable(false);
        jf.setLocationRelativeTo (null);
        //JScrollPane scroll = new JScrollPane(new TreeCanvas (root, this));
        //jf.add (scroll);
        //JPanel jp = new JPanel();
        JTextArea textAreal = new JTextArea(treeString, 5, 10);
        textAreal.setPreferredSize(new Dimension(450, 450));
        textAreal.setEditable(false);
        jf.add(textAreal);
        jf.setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
        jf.setVisible (true);


    }

    public void createBST(char[] a){
        for (char letter : a){
           // String ltr = Character.toString(letter);
            tree.insert(letter);
        }
        System.out.println("Tree built");
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

}
