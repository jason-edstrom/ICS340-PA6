import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    //GUI Build
    JPanel untitled_5;
    Label lbl_Input;
    TextField tf_inpu;
    Button bt_Dra;
    Button bt_BuildBS;
    JScrollPane jsp;

    public BSTRenderGUI()
    {
        getContentPane().setLayout(null);
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void setupGUI()
    {
        untitled_5 = new JPanel();
        untitled_5.setLocation(13,84);
        untitled_5.setSize(538,451);
        //untitled_5.setBackground( new Color(-1) );
        //untitled_5.setText("");
        //untitled_5.setRows(5);
        //untitled_5.setColumns(5);
        jsp = new JScrollPane(untitled_5);
        getContentPane().add(jsp);



        lbl_Input = new Label();
        lbl_Input.setLocation(7,7);
        lbl_Input.setSize(40,32);
        lbl_Input.setText("Input:");
        getContentPane().add(lbl_Input);

        tf_inpu = new TextField();
        tf_inpu.setLocation(50,7);
        tf_inpu.setSize(501,34);
        tf_inpu.setBackground( new Color(-1) );
        tf_inpu.setText("");
        tf_inpu.setColumns(10);
        getContentPane().add(tf_inpu);

        bt_Dra = new Button();
        bt_Dra.setLocation(155,46);
        bt_Dra.setSize(90,32);
        bt_Dra.setLabel("Draw");
        getContentPane().add(bt_Dra);

        bt_BuildBS = new Button();
        bt_BuildBS.setLocation(280,47);
        bt_BuildBS.setSize(90,32);
        bt_BuildBS.setLabel("Build BST");
        getContentPane().add(bt_BuildBS);


        bt_BuildBS.addActionListener(this);
        bt_Dra.addActionListener(this);

        setTitle("TreePaintGUI");
        setSize(567,150);
        setVisible(true);
        setResizable(true);



    }
    public static void main( String args[] )
    {
        new BSTRenderGUI();
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        System.out.println(obj);
    }
}
