import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;


public class SettingsFrame extends JFrame  {

    private JButton chooseSavePath ;
    private JFileChooser pathChooser ;
    private String savePath ;
    private JButton saveChanges ;
    private JButton cancel ;
    private int sameDownloadNumbers ;
    private JRadioButton lookAndFill1 ;
    private JRadioButton lookAndFill2 ;
    private JRadioButton defaultLook ;
    private SettingInformation settingInformation ;
    private JTextArea filterTextArea ;
    private String[] filterSites ;



    public SettingsFrame () {

        JTabbedPane tabs = new JTabbedPane() ;
        JPanel tabsPanel = new JPanel() ;
        setLayout(new BorderLayout());
        settingInformation = loadSetiings() ;
        savePath = SettingInformation.getSavePath();
        sameDownloadNumbers = SettingInformation.getSameTimeDownloads() ;
        filterSites = SettingInformation.getFilterSites() ;
        System.out.println(savePath);
        System.out.println(sameDownloadNumbers);
        for (int i = 0 ; i < filterSites.length ; i ++ ) {
            filterTextArea.setText(filterTextArea.getText() + "/n" + filterSites[i]);
        }
        setSize(550,300);
        setTitle("Settings");
        // create and add JSlider for the numbers of downloads in same time
        JPanel downloadNumberPanel = new JPanel( new BorderLayout()) ;
        JLabel downloadNumberLabel = new JLabel("Set the numbers of downloads in same time ... ") ;
        JSlider numberSelector = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
        numberSelector.setPaintTicks(true);
        numberSelector.setPaintLabels(true);
        numberSelector.setMinorTickSpacing(1);
        numberSelector.setMajorTickSpacing(1);
        numberSelector.setValue(SettingInformation.getSameTimeDownloads());
        downloadNumberPanel.add(downloadNumberLabel , BorderLayout.WEST) ;
        downloadNumberPanel.add(numberSelector , BorderLayout.EAST) ;
        downloadNumberLabel.setPreferredSize( new Dimension(300,100));
        downloadNumberPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // set path chooser
        JLabel chooseFileEmpty = new JLabel() ;
        chooseSavePath = new JButton("Choose Download Save Path ") ;
        pathChooser = new JFileChooser() ;
        pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseSavePath.addMouseListener( new SettingsFrame.FileChooserMouseListener());


        // set saveChanges and cancel buttons :

        JPanel buttonsPanel = new JPanel( new BorderLayout()) ;
        saveChanges = new JButton("Save Changes") ;
        cancel = new JButton("Cancel") ;
        saveChanges.setBackground(Color.DARK_GRAY);
        cancel.setBackground(Color.DARK_GRAY);
        buttonsPanel.add(saveChanges , BorderLayout.WEST) ;
        buttonsPanel.add(cancel , BorderLayout.EAST) ;
        buttonsPanel.setPreferredSize( new Dimension(300,60));


        // set look and fill chooser :



        lookAndFill1 = new JRadioButton("Look And Feel 1 ") ;
        lookAndFill2 = new JRadioButton("Look And Feel 2 ") ;
        defaultLook = new JRadioButton("Default") ;
        JPanel radioButtonsPanel = new JPanel( new BorderLayout()) ;
        JLabel setLAndF = new JLabel("<html><p style=\"text-align:center;\"> Select Look and Feel ...</p></html>") ;
        radioButtonsPanel.add(setLAndF ,BorderLayout.NORTH );
        radioButtonsPanel.add(lookAndFill1 ,BorderLayout.WEST);
        radioButtonsPanel.add(lookAndFill2 , BorderLayout.CENTER);
        radioButtonsPanel.add(defaultLook, BorderLayout.EAST) ;
        radioButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));



        // set cacntentpain :

        JPanel contentPain = new JPanel( new BorderLayout()) ;
        contentPain.add(downloadNumberPanel , BorderLayout.NORTH) ;
        contentPain.add(chooseSavePath , BorderLayout.CENTER) ;
        contentPain.add(radioButtonsPanel ,BorderLayout.SOUTH);
       // contentPain.add(buttonsPanel , BorderLayout.SOUTH) ;
        tabs.add("Download" , contentPain) ;


        // create connection tab :

        JPanel connectionPanel = new JPanel(new BorderLayout()) ;
        filterTextArea = new JTextArea() ;
        JLabel writeFilterSites = new JLabel("Write Filter Sites ...") ;
        connectionPanel.add(writeFilterSites , BorderLayout.NORTH) ;
        connectionPanel.add(filterTextArea , BorderLayout.CENTER) ;
        tabs.add("Connection" , connectionPanel) ;





        tabsPanel.add(tabs) ;
        add(tabsPanel , BorderLayout.CENTER) ;
        add(buttonsPanel , BorderLayout.SOUTH) ;







        // set actionlistener to slider :

        saveChanges.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                sameDownloadNumbers = numberSelector.getValue() ;
                System.out.println("same download numbers : " + sameDownloadNumbers);
                ToolBarPanel.setting.setEnabled(true);
                SettingInformation.setSameTimeDownloads(sameDownloadNumbers);
               SettingInformation.setSavePath(savePath);
                saveSettings();
                dispose();
            }
        }) ;


        cancel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ToolBarPanel.setting.setEnabled(true);
                dispose();
            }
        });



        // set action listener to radio buttons :

        lookAndFill1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (lookAndFill1.isSelected()) {
                    lookAndFill2.setSelected(false);
                    defaultLook.setSelected(false);
                    MainFrame mainFrame = MainFrame.getInstance() ;
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    SwingUtilities.updateComponentTreeUI(mainFrame);
                    mainFrame.revalidate();
                    mainFrame.repaint();
               }
            }
        });

        lookAndFill2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (lookAndFill2.isSelected()) {
                    lookAndFill1.setSelected(false);
                    defaultLook.setSelected(false);
                    MainFrame mainFrame = MainFrame.getInstance() ;
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    SwingUtilities.updateComponentTreeUI(mainFrame);
                    mainFrame.revalidate();
                    mainFrame.repaint();
                }
            }
        });


        defaultLook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (defaultLook.isSelected()) {
                    lookAndFill1.setSelected(false);
                    lookAndFill2.setSelected(false);
                    MainFrame mainFrame = MainFrame.getInstance() ;

                    try {
                        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    SwingUtilities.updateComponentTreeUI(mainFrame);
                    mainFrame.revalidate();
                    mainFrame.repaint();
                }
            }
        });

        setVisible(true);

    }

    private class FileChooserMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            int result = pathChooser.showOpenDialog(null);
            if (result == JFileChooser.CANCEL_OPTION) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
            }
            else if (result == JFileChooser.APPROVE_OPTION) {
                pathChooser.showDialog(null , "choose") ;
                savePath = pathChooser.getSelectedFile().getAbsolutePath();
                System.out.println(" save path " + savePath);
            }
        }

    }


    private void saveSettings () {

       // System.out.println(SettingInformation.getSameTimeDownloads());
        filterSites = filterTextArea.getText().split("/n") ;
        SettingInformation.setFilterSites(filterSites);

        try (FileOutputStream fs = new FileOutputStream("Saves/setting.jdm")) {
            ObjectOutputStream os = new ObjectOutputStream(fs) ;
            os.writeObject(settingInformation);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private SettingInformation loadSetiings () {


        SettingInformation loadInformation = new SettingInformation() ;

        try (FileInputStream fs = new FileInputStream("Saves/setting.jdm")) {

            ObjectInputStream os = new ObjectInputStream(fs);

            loadInformation = (SettingInformation) os.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return loadInformation ;
    }

}
