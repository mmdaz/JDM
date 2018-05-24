import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ToolBarPanel extends JPanel {


    private JButton newDownload;
    private JButton pauseDownload;
    private JButton continueDownload;
    private JButton cancelDownload;
    private JButton addToQueue ;
    public static JButton setting;
    private JButton remove;
    private JMenuItem newDownloadItem ;
    private JMenuItem pauseDownloadItem ;
    private JMenuItem continueDownloadItem ;
    private JMenuItem cancelDownloadItem ;
    private JMenuItem settingItem ;
    private JMenuItem removreItem ;
    private JMenuItem deleteAll ;
    private JMenuItem exitItem ;
    private JMenuItem helpAndAbout;
    private JMenuItem export ;
  //  public static final SettingsFrame settingsFrame = new SettingsFrame() ;



    public ToolBarPanel() {
        JLabel nameLabel = new JLabel("mmdni Download Manager ..." , SwingConstants.CENTER) ;
        nameLabel.setBorder( BorderFactory.createLineBorder(Color.GREEN));
        nameLabel.setPreferredSize( new Dimension(300,45));
        nameLabel.setFont(new Font("serif", Font.PLAIN, 14));
        JPanel eastPanel = new JPanel( new BorderLayout()) ;
        eastPanel.setPreferredSize( new Dimension(320,100));


        // create and add tool buttons to a panel and add this panel to the maoin panel

        JPanel bottunsPanel = new JPanel( new GridLayout(1,6,5,5)) ;
        setLayout( new BorderLayout());
        setPreferredSize(new Dimension(430, 100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newDownload = new JButton();
        newDownload.setSize(45, 45);
        newDownload.setIcon(getGoodSizeImage("Icons/newDownload.png", newDownload));
        bottunsPanel.add(newDownload);
        pauseDownload = new JButton();
        pauseDownload.setSize(45, 45);
        pauseDownload.setIcon(getGoodSizeImage("Icons/pause.png", pauseDownload));
        bottunsPanel.add(pauseDownload);
        continueDownload = new JButton();
        continueDownload.setSize(45, 45);
        continueDownload.setIcon(getGoodSizeImage("Icons/continue.png", continueDownload));
        bottunsPanel.add(continueDownload);
        cancelDownload = new JButton();
        cancelDownload.setSize(45, 45);
        cancelDownload.setIcon(getGoodSizeImage("Icons/cancel.png", cancelDownload));
        bottunsPanel.add(cancelDownload);
        setting = new JButton() ;
        setting.setSize(45,45);
        setting.setIcon(getGoodSizeImage("Icons/settings.png",setting));
        bottunsPanel.add(setting);
        remove = new JButton() ;
        remove.setSize(45,45);
        remove.setIcon(getGoodSizeImage("Icons/remove.png",remove));
        bottunsPanel.add(remove) ;
        addToQueue = new JButton() ;
        addToQueue.setSize(45,45);
        addToQueue.setIcon(getGoodSizeImage("Icons/addToQueue.png",addToQueue));
        addToQueue.setToolTipText("Add To Queue");
        bottunsPanel.add(addToQueue) ;


        // create a menubar and add it to this panel

        JMenuBar menuBar = new JMenuBar() ;
        JMenu toolsMenu = new JMenu("Tools") ;
        JMenu aboutMenu = new JMenu("About") ;
        toolsMenu.setMnemonic(KeyEvent.VK_T);
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        newDownloadItem = new JMenuItem("New Download ") ;
        newDownload.setToolTipText("New Download");
        continueDownloadItem = new JMenuItem("Continue Download ") ;
        continueDownload.setToolTipText("Continue Download");
        cancelDownloadItem = new JMenuItem("Cancel Download ") ;
        cancelDownload.setToolTipText("Cancel Download ");
        pauseDownloadItem = new JMenuItem("Pause Download ") ;
        pauseDownload.setToolTipText("Pause Download ");
        settingItem = new JMenuItem("Settings") ;
        setting.setToolTipText("Settings");
        removreItem = new JMenuItem("Remove") ;
        remove.setToolTipText("Remove");
        deleteAll = new JMenuItem("Remove All") ;
        exitItem = new JMenuItem("Exit") ;
        helpAndAbout = new JMenuItem("Help & About") ;
        export = new JMenuItem("Export") ;
        toolsMenu.add(newDownloadItem) ;
        toolsMenu.add(pauseDownloadItem) ;
        toolsMenu.add(cancelDownloadItem);
        toolsMenu.add(continueDownloadItem);
        toolsMenu.add(settingItem);
        toolsMenu.add(removreItem) ;
        toolsMenu.add(deleteAll) ;
        toolsMenu.add(export) ;
        toolsMenu.add(exitItem) ;
        aboutMenu.add(helpAndAbout) ;
        menuBar.add(toolsMenu) ;
        menuBar.add(aboutMenu) ;
        eastPanel.add(menuBar , BorderLayout.NORTH) ;
        add(nameLabel , BorderLayout.NORTH);
        add(bottunsPanel , BorderLayout.CENTER);
        add(eastPanel ,BorderLayout.WEST);

        // add buttons actionlistener

        newDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                NewDownloadFrame newDownloadFrame = new NewDownloadFrame() ;
            }
        });

        setting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                SettingsFrame settingsFrame = new SettingsFrame() ;
                setting.setEnabled(false);
            }
        });

        pauseDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                DownloadPanel.pauseDownloads();
                MainFrame.updateDownloadPanel(1);
                System.out.println("pause download ...");
            }
        });

        continueDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

               DownloadPanel.continueDownloads();
               MainFrame.updateDownloadPanel(1);
                System.out.println("continue download ...");
            }
        });

        cancelDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                DownloadPanel.cancelDownload();
                MainFrame.updateDownloadPanel(1);
                System.out.println("cancel download ... ");
            }
        });

        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("remove download ...");
                DownloadPanel.deleteFromDownloadList();
                QueuePanel.deleteFromDownloadList();
                MainFrame.updateDownloadPanel(1);
                DownloadsQueueFrame.updateDownloadPanel();
              //  MainFrame.updateDownloadPanel(2);
            }
        });

        addToQueue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                DownloadPanel.addSelectedDownloadsToQueue();
            }
        });


        // add Items Actionlisteners :

        newDownloadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewDownloadFrame newDownloadFrame = new NewDownloadFrame() ;
            }
        });

        settingItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SettingsFrame settingsFrame = new SettingsFrame() ;
            }
        });

        continueDownloadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DownloadPanel.continueDownloads();
                MainFrame.updateDownloadPanel(1);
                System.out.println("continue download ...");
            }
        });

        pauseDownloadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DownloadPanel.pauseDownloads();
                MainFrame.updateDownloadPanel(1);
                System.out.println("pause download ...");
            }
        });

        removreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DownloadPanel.deleteFromDownloadList();
                MainFrame.updateDownloadPanel(1);
                System.out.println("remove download ...");
            }
        });

        cancelDownloadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("cancel download ...");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        helpAndAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HelpFrame helpFrame = new HelpFrame() ;
            }
        });


        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DownloadPanel.downloadsList.clear();
                QueuePanel.downloadsList.clear();
                MainFrame.updateDownloadPanel(1);
                DownloadsQueueFrame.updateDownloadPanel();
            }
        });

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getZip();
            }
        });

        // add accelactor to items :

        newDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N , KeyEvent.CTRL_MASK));
        pauseDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P , KeyEvent.CTRL_MASK));
        continueDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K , KeyEvent.CTRL_MASK));
        removreItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R , KeyEvent.CTRL_MASK));
        cancelDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C , KeyEvent.CTRL_MASK));
        settingItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , KeyEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E , KeyEvent.CTRL_MASK));
        helpAndAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H , KeyEvent.CTRL_MASK));
        deleteAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A , KeyEvent.CTRL_MASK));
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X , KeyEvent.CTRL_MASK));

    }





    public ImageIcon getGoodSizeImage(String address, JButton button) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(address));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image icon = img.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(icon);

        return imageIcon;
    }

    private void getZip () {

        String[] saveFiles = {"queue.jdm" , "list.jdm"} ;
        String zipFile = "archive.zip" ;
        try {
            byte[] buffer = new byte[2048] ;
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile) ;
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream) ;
            for (int i = 0 ; i < saveFiles.length ; i++ ) {
                File saveFile = new File(saveFiles[i]) ;
                FileInputStream fileInputStream = new FileInputStream(saveFile) ;
                zipOutputStream.putNextEntry( new ZipEntry(saveFile.getName()));

                int length ;
                while ((length = fileInputStream.read(buffer)) > 0) {

                    zipOutputStream.write(buffer , 0 , length);
                }

                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
            zipOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
