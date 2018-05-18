import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadPoolExecutor;

public class ProccessingPanel extends JPanel {

    private JButton processingButton ;
    private JButton completedButton ;
    private JButton defaultButton ;
    private JButton queueButton ;


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

    }

}
