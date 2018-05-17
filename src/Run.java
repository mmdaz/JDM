import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Run {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {



        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
       MainFrame mainFrame = MainFrame.getInstance() ;
//       NewDownloadFrame newDownloadFrame = new NewDownloadFrame() ;
//       HelpFrame helpFrame = new HelpFrame() ;
//       SettingsFrame settingsFrame = new SettingsFrame() ;
//       DownloadFrame downloadFrame = new DownloadFrame() ;

//        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
//        for (UIManager.LookAndFeelInfo look : looks) {
//            System.out.println(look.getClassName());
//        }

       // DownloadsQueueFrame downloadsQueueFrame = new DownloadsQueueFrame() ;


    }

    public static void changeLookAndFeel ( int option ) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        if (option == 1) {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }

        else if (option == 2) {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
    }

}
