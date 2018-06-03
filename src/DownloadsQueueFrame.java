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

/**
 * The DownloadsQueueFrame program is an application that simply
 *  get and show and handle them in a queue for download .
 *
 * @author Azhdari Muhammad
 * @version 1.0
 * @since 2018 spring
 */
public class DownloadsQueueFrame extends JFrame {

    public static JButton delete ;
    public static JButton startQueue ;
    public static JButton pauseQueue ;
    public static JButton resumeQueue ;
    public static JButton swapDownloads ;
    public static JPanel contentPain ;
    public static QueuePanel queuePanel ;
    public static JPanel mainPanel ;
    public static DownloadsQueueFrame downloadsQueueFrame ;


    private DownloadsQueueFrame() {

    }

    /**
     * This class is a singleton class and this method get a instance of this .
     * @return a instance of this class
     */

    public static DownloadsQueueFrame getInstance () {

        if (downloadsQueueFrame == null) {


            downloadsQueueFrame = new DownloadsQueueFrame() ;
            downloadsQueueFrame.setSize(750 , 400);
            downloadsQueueFrame.setTitle("Queue");
            queuePanel = new QueuePanel();
            contentPain = new JPanel(new BorderLayout());

            // create and add buttons panel :


            JPanel buttonsPanel = new JPanel(new GridLayout(2, 4, 5, 5));
            startQueue = new JButton("Start");
            pauseQueue = new JButton("Pause");
            delete = new JButton("Delete");
            swapDownloads = new JButton("Swap") ;
            resumeQueue = new JButton("Resume") ;
            buttonsPanel.add(delete);
            buttonsPanel.add(startQueue) ;
            buttonsPanel.add(pauseQueue) ;
            buttonsPanel.add(resumeQueue);
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
         //   downloadsQueueFrame.setVisible(true);


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

            startQueue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    QueuePanel.startQueue();
                }
            });

            pauseQueue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    QueuePanel.stopQueue();
                }
            });


            resumeQueue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    QueuePanel.startQueue();
                }
            });

        }
        return downloadsQueueFrame ;
    }

    /**
     * The method that updates queue frame .
     */
    public static void updateDownloadPanel ( ) {
        mainPanel.remove(queuePanel) ;
        queuePanel = new QueuePanel() ;
        mainPanel.add(queuePanel, BorderLayout.CENTER) ;
        saveQueue(queuePanel);
        contentPain.revalidate();
        contentPain.repaint();
        downloadsQueueFrame.revalidate();
        downloadsQueueFrame.repaint();

    }

    /**
     * The method that save queue downloads in a file .
     * @param queuePanel
     */
    public static void saveQueue (QueuePanel queuePanel ) {

        try (FileOutputStream fs = new FileOutputStream("Saves/queue.jdm")) {
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

    /**
     * The method that load queue list from file that they are saved in it .
     * @return a download list
     */
    public static ArrayList<Download> loadQueue () {

        ArrayList<Download> downloadList = new ArrayList<Download>() ;

        try (FileInputStream fs = new FileInputStream("Saves/queue.jdm")) {

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

    /**
     * The method that refresh the queue frame and progress bar .
     */

    public static void refresh () {

        queuePanel.refreshProgressBar();
        queuePanel.revalidate();
        queuePanel.repaint();
    }

}
