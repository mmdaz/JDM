import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
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
    public static JButton swapDownloads ;
    public static JPanel contentPain ;
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
            swapDownloads = new JButton("Swap") ;
            buttonsPanel.add(delete);
            buttonsPanel.add(sortByDate);
            buttonsPanel.add(sortByName);
            buttonsPanel.add(sortBySize);
            buttonsPanel.add(startQueue) ;
            buttonsPanel.add(puaseQueue) ;
            buttonsPanel.add(swapDownloads) ;


            mainPanel = new JPanel(new BorderLayout());
            if (loadQueue().size() != 0) {
                QueuePanel.setDownloadsList(loadQueue());
            }
            mainPanel.add(queuePanel, BorderLayout.CENTER);
            mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
            contentPain.add(mainPanel);
            downloadsQueueFrame.setContentPane(contentPain);
            updateDownloadPanel();
            downloadsQueueFrame.setVisible(true);


            // add  buttons action listener  :

            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    QueuePanel.deleteFromDownloadList();
                    downloadsQueueFrame.updateDownloadPanel();
                    MainFrame.updateDownloadPanel(1);
                   // System.out.println(DownloadPanel.downloadsList.size());
                }
            });

            swapDownloads.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    QueuePanel.swapTwoDownloadInQueue();
                    downloadsQueueFrame.updateDownloadPanel();
                }
            });


            sortByDate.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    QueuePanel.sortByCreateTime();
                    downloadsQueueFrame.updateDownloadPanel();
                }
            });

        }
        return downloadsQueueFrame ;
    }


    public static void updateDownloadPanel ( ) {
        mainPanel.remove(queuePanel);
        queuePanel = new QueuePanel() ;
        mainPanel.add(queuePanel, BorderLayout.CENTER) ;
        saveQueue(queuePanel);
        contentPain.revalidate();
        contentPain.repaint();
        downloadsQueueFrame.revalidate();
        downloadsQueueFrame.repaint();

    }


    public static void saveQueue (QueuePanel queuePanel ) {

        try (FileOutputStream fs = new FileOutputStream("queue.jdm")) {
            ObjectOutputStream os = new ObjectOutputStream(fs) ;
            ArrayList<Download> downloadList = QueuePanel.getDownloadsList() ;
            os.writeObject(downloadList);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Download> loadQueue () {

        ArrayList<Download> downloadList = new ArrayList<Download>() ;

        try (FileInputStream fs = new FileInputStream("queue.jdm")) {

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
