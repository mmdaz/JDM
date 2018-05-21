import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

public class ProccessingPanel extends JPanel {

    private JButton processingButton ;
    private JButton completedButton ;
    private JButton defaultButton ;
    private JButton queueButton ;
    private JButton searchButton ;
    private JTextField searchField ;
    private ArrayList<Download> searchDownloadList ;


    public ProccessingPanel () {

        setLayout( new GridLayout(2,1,5,5));
        setPreferredSize( new Dimension(130,370));
        processingButton = new JButton("Processing") ;
        completedButton = new JButton("Completed") ;
        queueButton = new JButton("Queue") ;
        defaultButton = new JButton("About") ;
        JPanel buttonsPanel = new JPanel( new GridLayout(4,1,5,5)) ;
        JPanel emptyPanel = new JPanel() ;
        emptyPanel.setBackground(Color.black);
        buttonsPanel.add(processingButton) ;
        buttonsPanel.add(completedButton) ;
        buttonsPanel.add(queueButton);
        buttonsPanel.add(defaultButton) ;
        

        add(buttonsPanel) ;
        add(emptyPanel) ;


        // add buttons actionlistener :

        defaultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                HelpFrame helpFrame = new HelpFrame() ;
            }
        });

        queueButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                DownloadsQueueFrame.downloadsQueueFrame = null ;
                DownloadsQueueFrame downloadsQueueFrame = DownloadsQueueFrame.getInstance() ;
            }
        });

        completedButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
               // DownloadPanel.setDownloadsList(DownloadPanel.completedDownloadsList);
                MainFrame.updateDownloadPanel(2);
            }
        });

        processingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
              //  DownloadPanel.setDownloadsList(DownloadPanel.progressDownloadlist);
                MainFrame.updateDownloadPanel(1);
            }
        });

        // add search bar :


        JPanel searchbarPanel = new JPanel( new BorderLayout()) ;
        searchbarPanel.setSize(80,100);

        searchButton = new JButton() ;
        searchButton.setSize(25,25);

        BufferedImage img = null ;
        try {
            img = ImageIO.read( new File("search1.png")) ;
        } catch (IOException e) {
            e.printStackTrace();
        }


        Image icon = img.getScaledInstance( searchButton.getWidth() , searchButton.getHeight() , Image.SCALE_SMOOTH ) ;
        ImageIcon icon1 = new ImageIcon(icon) ;
        searchButton.setIcon(icon1);

        searchbarPanel.add(searchButton , BorderLayout.EAST) ;
        searchField = new JTextField() ;
        searchField.setPreferredSize( new Dimension(80,20));
        searchbarPanel.add(searchField , BorderLayout.CENTER) ;
        emptyPanel.add(searchbarPanel) ;

        // add action listener to search button :

        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                search();
                System.out.println(searchDownloadList.get(0).getUrl());
                System.out.println(searchDownloadList.get(1).getUrl());
            }
        });

    }


    public void search () {

        String searchString = searchField.getText() ;
        searchDownloadList = new ArrayList<Download>() ;

        for (Download download : DownloadPanel.downloadsList ) {

            if (download.getName().matches(".*"+searchString+".*") || download.getUrl().matches(".*"+searchString+".*")) {
                searchDownloadList.add(download) ;
            }

        }


    }

    public ArrayList<Download> getSearchDownloadList() {
        return searchDownloadList;
    }


}
