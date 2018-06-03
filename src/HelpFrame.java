import javax.swing.*;
import java.awt.*;

/**
 * The HelpFrame program is an application that simply
 *  is a frame that have about and help of the JDM .
 *
 */
public class HelpFrame extends JFrame {


    public HelpFrame () {

        setSize(300,400);
        setTitle("Help & About");
        JTabbedPane tabs = new JTabbedPane() ;
        JPanel helpPanel = new JPanel( new BorderLayout()) ;
        JPanel aboutPanel = new JPanel( new BorderLayout()) ;
        JLabel helpLabel = new JLabel("This is a java download manager ...") ;
        JLabel aboutLabel = new JLabel("This is writed by Muhammad Azhdari ...") ;
        helpPanel.add(helpLabel , BorderLayout.CENTER) ;
        aboutPanel.add(aboutLabel , BorderLayout.CENTER) ;
        tabs.add("Help",helpPanel) ;
        tabs.add("About",aboutPanel) ;

        add(tabs) ;

        setVisible(true);
    }
}
