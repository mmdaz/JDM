
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


        if (SystemTray.isSupported()) {

            SystemTray systemTray = SystemTray.getSystemTray();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(getClass().getResource("icon3.png"));
            PopupMenu popupMenu = new PopupMenu();
            TrayIcon icon = new TrayIcon(image, "JDM Program", popupMenu);
            MenuItem openItem = new MenuItem("Open JDM");
            openItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(true);
                }
            });
            popupMenu.add(openItem);

            MenuItem closeItem = new MenuItem("Close JDM");
            closeItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SystemTray.getSystemTray().remove(icon);
                    System.exit(0);
                }
            });
            popupMenu.add(closeItem);
            icon.setImageAutoSize(true);
            try {
                systemTray.add(icon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
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
            if (loadDownloadList().size() != 0) {
                DownloadPanel.setDownloadsList(loadDownloadList());
            }
            updateDownloadPanel(1);
            mainFrame.setVisible(true);

//            System.out.printf(proccessingPanel.getSearchDownloadList().get(0).getUrl());
         DownloadPanel.sortByName();
        }


        return mainFrame ;
    }


    public static void updateDownloadPanel (int conditionNumber ) {

        rightPanel.remove(downloadPanel);
        downloadPanel = new DownloadPanel(conditionNumber) ;
        saveDownloadList(downloadPanel);
        rightPanel.add(downloadPanel , BorderLayout.CENTER) ;
        System.out.println(downloadPanel.getDownloadsList().size());
        contentPain.revalidate();
        contentPain.repaint();

    }

    public static void saveDownloadList (DownloadPanel downloadPanel ) {

        try (FileOutputStream fs = new FileOutputStream("Saves/list.jdm")) {
            ObjectOutputStream os = new ObjectOutputStream(fs) ;
            ArrayList<Download> downloadList = downloadPanel.getDownloadsList() ;
            os.writeObject(downloadList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Download> loadDownloadList () {

        ArrayList<Download> downloadList = new ArrayList<Download>() ;

        try (FileInputStream fs = new FileInputStream("Saves/list.jdm")) {

            ObjectInputStream os = new ObjectInputStream(fs);

             downloadList = (ArrayList<Download>) os.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return downloadList ;

    }

    }
