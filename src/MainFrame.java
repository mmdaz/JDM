
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

/**
 * The MainFrame program is an application that simply
 * is main frame of the JDM .
 */
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

    /**
     * This class is singleton and this method get a instance of the class .
     * @return the instance of the main frame .
     */
    public static  MainFrame getInstance () {


        if (mainFrame == null) {

            mainFrame = new MainFrame() ;
            mainFrame.setTitle("MMDNI Download Manager");
            contentPain = new JPanel(new BorderLayout());
            downloadPanel = new DownloadPanel(1);
          //  downloadPanel.setDownloadsList(readDownloadListFromFile());
            mainFrame.setContentPane(contentPain);
            mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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

            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                   systemTray();
                   e.getWindow().dispose();
                }
            });

        }


        return mainFrame ;
    }

    /**
     * The Method that update main frame for add and remove and so an .
     * @param conditionNumber
     */
    public static void updateDownloadPanel (int conditionNumber ) {

        rightPanel.remove(downloadPanel);
        downloadPanel = new DownloadPanel(conditionNumber) ;
        saveDownloadList(downloadPanel);
        rightPanel.add(downloadPanel , BorderLayout.CENTER) ;
        System.out.println(downloadPanel.getDownloadsList().size());
        SwingUtilities.invokeLater(()->{
            downloadPanel.revalidate();
            downloadPanel.repaint();
        });

    }

    /**
     * The Method that save download list in a file .
     * @param downloadPanel
     */
    public static void saveDownloadList (DownloadPanel downloadPanel ) {

        try (FileOutputStream fs = new FileOutputStream("Saves/list.jdm")) {
            ObjectOutputStream os = new ObjectOutputStream(fs) ;
            Vector<Download> downloadList = downloadPanel.getDownloadsList() ;
            os.writeObject(downloadList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The Method that load the download list in from the file .
     * @return
     */
    public static Vector<Download> loadDownloadList () {

        Vector<Download> downloadList = new Vector<Download>() ;

        try (FileInputStream fs = new FileInputStream("Saves/list.jdm")) {

            ObjectInputStream os = new ObjectInputStream(fs);

             downloadList = (Vector<Download>) os.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return downloadList ;

    }

    /**
     * The Method that update the progress bar in downloading time .
     */
    public static void refresh () {

        downloadPanel.refreshProgressBar();
        downloadPanel.revalidate();
        downloadPanel.repaint();

    }

    /**
     * The method that handle the sysytem tray but does not work :((
     */
    public static void systemTray () {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            PopupMenu jdm = new PopupMenu();
            MenuItem item = new MenuItem("A MenuItem");
            jdm.add(item);
            Image image = Toolkit.getDefaultToolkit().getImage("Icons/icon3.png");
            TrayIcon trayIcon = new TrayIcon(image, "Java Download Manager", jdm);
            trayIcon.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tray.remove(trayIcon);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    tray.remove(trayIcon);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    tray.remove(trayIcon);
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }



    }
