import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Run {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {


        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
       MainFrame mainFrame = MainFrame.getInstance() ;

    }
    }
