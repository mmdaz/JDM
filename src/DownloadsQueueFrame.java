import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DownloadsQueueFrame extends JFrame {

    public static JButton delete ;
    public static JButton sortByDate ;
    public static JButton sortByName ;
    public static JButton sortBySize ;
    public static JButton startQueue ;
    public static JButton puaseQueue ;
    public static JPanel contentPain ;
    public static JButton addNewDownload ;
    public static QueuePanel queuePanel ;
    public static JPanel mainPanel ;
    public static DownloadsQueueFrame downloadsQueueFrame ;


    private DownloadsQueueFrame () {


    }


    public static DownloadsQueueFrame getInstance () {

        if (downloadsQueueFrame == null) {


            downloadsQueueFrame = new DownloadsQueueFrame() ;
            downloadsQueueFrame.setSize(750 , 400);
            downloadsQueueFrame.setTitle("Queue");
            queuePanel = new QueuePanel();
            contentPain = new JPanel(new BorderLayout());

            // create and add buttons panel :


            JPanel buttonsPanel = new JPanel(new GridLayout(2, 4, 5, 5));
            delete = new JButton("Delete");
            sortByDate = new JButton("Sort By Date ");
            sortByName = new JButton("Sort By Name ");
            sortBySize = new JButton("Sort By Size ");
            startQueue = new JButton("Start");
            puaseQueue = new JButton("Puase");
            addNewDownload = new JButton("New Download") ;
            buttonsPanel.add(delete);
            buttonsPanel.add(sortByDate);
            buttonsPanel.add(sortByName);
            buttonsPanel.add(sortBySize);
            buttonsPanel.add(startQueue) ;
            buttonsPanel.add(puaseQueue) ;
            buttonsPanel.add(addNewDownload) ;


            mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(queuePanel, BorderLayout.CENTER);
            mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
            contentPain.add(mainPanel);
            downloadsQueueFrame.setContentPane(contentPain);

            downloadsQueueFrame.setVisible(true);


            // add  buttons action listener  :

            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    QueuePanel.deleteFromDownloadList();
                    downloadsQueueFrame.updateDownloadPanel();
                   // System.out.println(DownloadPanel.downloadsList.size());
                }
            });

            addNewDownload.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    NewDownloadFrame newDownloadFrame = new NewDownloadFrame() ;
                }
            });

        }
        return downloadsQueueFrame ;
    }


    public void updateDownloadPanel ( ) {
        mainPanel.remove(queuePanel);
        queuePanel = new QueuePanel() ;
        mainPanel.add(queuePanel, BorderLayout.CENTER) ;
        contentPain.revalidate();
        contentPain.repaint();
        revalidate();
        repaint();

    }

}
