
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame implements Serializable {
    public static ToolBarPanel toolBarPanel ;
    public static DownloadPanel downloadPanel ;
    public static LogoPanel logoPanel ;
    public static ProccessingPanel proccessingPanel ;
    public static JPanel contentPain ;
    public static JPanel rightPanel ;
    public static JPanel leftPanel ;
    private static MainFrame mainFrame ;



    private MainFrame () {

    }

    public static  MainFrame getInstance () {


        if (mainFrame == null) {

            mainFrame = new MainFrame() ;
            mainFrame.setTitle("MMDNI Download Manager");
            contentPain = new JPanel(new BorderLayout());
            downloadPanel = new DownloadPanel(1);
          //  downloadPanel.setDownloadsList(readDownloadListFromFile());
            mainFrame.setContentPane(contentPain);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 500);
            mainFrame.setLocation(100, 100);
            // downloadPanel = new DownloadPanel() ;
            toolBarPanel = new ToolBarPanel();
            proccessingPanel = new ProccessingPanel();
            leftPanel = new JPanel(new BorderLayout());
            rightPanel = new JPanel(new BorderLayout());
            logoPanel = new LogoPanel();
            //     downloadsPanel.add(downloadPanel);
            leftPanel.add(logoPanel, BorderLayout.CENTER);
            leftPanel.add(proccessingPanel, BorderLayout.SOUTH);
            rightPanel.add(toolBarPanel, BorderLayout.NORTH);
            rightPanel.add(downloadPanel, BorderLayout.CENTER);
            contentPain.add(leftPanel, BorderLayout.WEST);
            contentPain.add(rightPanel, BorderLayout.CENTER);
            mainFrame.setVisible(true);

        }


        return mainFrame ;
    }


    public static void updateDownloadPanel (int conditionNumber ) {

        rightPanel.remove(downloadPanel);
        downloadPanel = new DownloadPanel(conditionNumber) ;
      //  saveDownloadList(downloadPanel);
        rightPanel.add(downloadPanel , BorderLayout.CENTER) ;
        System.out.println(downloadPanel.getDownloadsList().size());
        contentPain.validate();
        contentPain.repaint();

    }

//    public static void saveDownloadList (DownloadPanel downloadPanel ) {
//
//        try (FileOutputStream fs = new FileOutputStream("list.jdm")) {
//            ObjectOutputStream os = new ObjectOutputStream(fs) ;
//            ArrayList<Download> downloadList = downloadPanel.getDownloadsList() ;
//            os.writeObject(downloadList);
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//
//        }
//    }


//    public static ArrayList<Download> readDownloadListFromFile () {
//
//        ArrayList<Download> downloadList = new ArrayList<Download>() ;
//
//        try (FileInputStream fs = new FileInputStream("list.jdm")) {
//
//            ObjectInputStream os = new ObjectInputStream(fs);
//
//             downloadList = (ArrayList<Download>) os.readObject();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return downloadList ;
//
//    }

    }
