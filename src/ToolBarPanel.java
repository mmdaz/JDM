import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ToolBarPanel extends JPanel {


    private JButton newDownload;
    private JButton pauseDownload;
    private JButton continueDownload;
    private JButton cancelDownload;
    public static JButton setting;
    private JButton remove;
    private JMenuItem newDownloadItem ;
    private JMenuItem pauseDownloadItem ;
    private JMenuItem continueDownloadItem ;
    private JMenuItem cancelDownloadItem ;
    private JMenuItem settingItem ;
    private JMenuItem removreItem ;
    private JMenuItem exitItem ;
    private JMenuItem helpAndAbout ;
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
        newDownload.setSize(50, 50);
        newDownload.setIcon(getGoodSizeImage("newDownload.png", newDownload));
        bottunsPanel.add(newDownload);
        pauseDownload = new JButton();
        pauseDownload.setSize(50, 50);
        pauseDownload.setIcon(getGoodSizeImage("pause.png", pauseDownload));
        bottunsPanel.add(pauseDownload);
        continueDownload = new JButton();
        continueDownload.setSize(50, 50);
        continueDownload.setIcon(getGoodSizeImage("continue.png", continueDownload));
        bottunsPanel.add(continueDownload);
        cancelDownload = new JButton();
        cancelDownload.setSize(50, 50);
        cancelDownload.setIcon(getGoodSizeImage("cancel.png", cancelDownload));
        bottunsPanel.add(cancelDownload);
        setting = new JButton() ;
        setting.setSize(50,50);
        setting.setIcon(getGoodSizeImage("settings.png",setting));
        bottunsPanel.add(setting);
        remove = new JButton() ;
        remove.setSize(50,50);
        remove.setIcon(getGoodSizeImage("remove.png",remove));
        bottunsPanel.add(remove) ;


        // create a menubar and add it to this panel

        JMenuBar menuBar = new JMenuBar() ;
        JMenu toolsMenu = new JMenu("Tools") ;
        JMenu aboutMenu = new JMenu("About") ;
        toolsMenu.setMnemonic(KeyEvent.VK_T);
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        newDownloadItem = new JMenuItem("New Download ") ;
        continueDownloadItem = new JMenuItem("Continue Download ") ;
        cancelDownloadItem = new JMenuItem("Cancel Download ") ;
        pauseDownloadItem = new JMenuItem("Pause Download ") ;
        settingItem = new JMenuItem("Settings") ;
        removreItem = new JMenuItem("Remove") ;
        exitItem = new JMenuItem("Exit") ;
        helpAndAbout = new JMenuItem("Help & About") ;
        toolsMenu.add(newDownloadItem) ;
        toolsMenu.add(pauseDownloadItem) ;
        toolsMenu.add(cancelDownloadItem);
        toolsMenu.add(continueDownloadItem);
        toolsMenu.add(settingItem);
        toolsMenu.add(removreItem) ;
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
                MainFrame.updateDownloadPanel();
                System.out.println("pause download ...");
            }
        });

        continueDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

               DownloadPanel.continueDownloads();
               MainFrame.updateDownloadPanel();
                System.out.println("continue download ...");
            }
        });

        cancelDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("cancel download ... ");
            }
        });

        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("remove download ...");
                DownloadPanel.deleteFromDownloadList();
                MainFrame.updateDownloadPanel();
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
                System.out.println("continue download ...");
            }
        });

        pauseDownloadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("pause download ...");
            }
        });

        removreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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


        // add accelactor to items :

        newDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N , KeyEvent.CTRL_MASK));
        pauseDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P , KeyEvent.CTRL_MASK));
        continueDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K , KeyEvent.CTRL_MASK));
        removreItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R , KeyEvent.CTRL_MASK));
        cancelDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C , KeyEvent.CTRL_MASK));
        settingItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , KeyEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E , KeyEvent.CTRL_MASK));
        helpAndAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H , KeyEvent.CTRL_MASK));

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

}