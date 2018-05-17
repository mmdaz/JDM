import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SettingsFrame extends JFrame  {

    private JButton chooseSavePath ;
    private JFileChooser pathChooser ;
    private String savePath ;
    private JButton saveChanges ;
    private JButton cancel ;
    private int sameDownloadNumbers ;
    private JRadioButton lookAndFill1 ;
    private JRadioButton lookAndFill2 ;



    public SettingsFrame () {

        setSize(500,400);
        setTitle("Settings");
        // create and add JSlider for the numbers of downloads in same time
        JPanel downloadNumberPanel = new JPanel( new BorderLayout()) ;
        JLabel downloadNumberLabel = new JLabel("Set the numbers of downloads in same time ... ") ;
        JSlider numberSelector = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
        numberSelector.setPaintTicks(true);
        numberSelector.setPaintLabels(true);
        numberSelector.setMinorTickSpacing(1);
        numberSelector.setMajorTickSpacing(1);
        downloadNumberPanel.add(downloadNumberLabel , BorderLayout.NORTH) ;
        downloadNumberPanel.add(numberSelector , BorderLayout.SOUTH) ;
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
        buttonsPanel.setPreferredSize( new Dimension(300,100));


        // set look and fill chooser :



        lookAndFill1 = new JRadioButton("Look And Fill 1 ") ;
        lookAndFill2 = new JRadioButton("Look And Fill 2 ") ;
        JPanel radioButtonsPanel = new JPanel( new BorderLayout()) ;
        JLabel setLAndF = new JLabel("<html><p style=\"text-align:center;\"> Select Look and Feel ...</p></html>") ;
        radioButtonsPanel.add(setLAndF ,BorderLayout.NORTH );
        radioButtonsPanel.add(lookAndFill1 ,BorderLayout.WEST);
        radioButtonsPanel.add(lookAndFill2 , BorderLayout.EAST);
        radioButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));



        // set cacntentpain :

        JPanel contentPain = new JPanel( new BorderLayout()) ;
        contentPain.add(downloadNumberPanel , BorderLayout.NORTH) ;
        contentPain.add(chooseSavePath , BorderLayout.CENTER) ;
        contentPain.add(radioButtonsPanel ,BorderLayout.EAST);
        contentPain.add(buttonsPanel , BorderLayout.SOUTH) ;
        setContentPane(contentPain);







        // set actionlistener to slider :

        saveChanges.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                sameDownloadNumbers = numberSelector.getValue() ;
                System.out.println("same download numbers : " + sameDownloadNumbers);
                ToolBarPanel.setting.setEnabled(true);
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
                    MainFrame mainFrame = MainFrame.getInstance() ;
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
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
}