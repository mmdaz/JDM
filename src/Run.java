import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.Set;

public class Run {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

     //   UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

//        System.out.println(SettingsFrame.loadSetiings().getLookAndFeelOption());

        if ( SettingsFrame.loadSetiings().getLookAndFeelOption() == 0 )
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        else if (SettingsFrame.loadSetiings().getLookAndFeelOption() == 1 )
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        else if (SettingsFrame.loadSetiings().getLookAndFeelOption() == 2 )
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");


       MainFrame mainFrame = MainFrame.getInstance() ;

    }
    }
