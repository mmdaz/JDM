import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class QueuePanel extends JPanel {

    private JProgressBar progressBar;
    public static ArrayList<Download> downloadsList = new ArrayList<Download>();
    public static ArrayList<JPanel> downloadPanels;
    private DownloadsQueueFrame downloadsQueueFrame = DownloadsQueueFrame.getInstance() ;


    public QueuePanel() {

        downloadPanels = new ArrayList<JPanel>();
        // create the progressbar :

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(40, 40, 160, 30);
        progressBar.setValue(50);
        progressBar.setStringPainted(true);


        // all download panels added to this panel and this panel addedP to the scrollpane
        // and scroll pane added to the panel of this class :

        JPanel mainPanel = new JPanel();
        int n = 1;
        GridLayout gridLayout = new GridLayout(n, 1, 5, 5);
        mainPanel.setLayout(gridLayout);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // create jlabel of each download panel for show  information of download :


        for (Download download : downloadsList) {

            JLabel downloadInformationLabel = new JLabel("<html>file name : " + download.getName() + "<br/> url : " + download.getUrl() + "</html>");
            downloadInformationLabel.setPreferredSize(new Dimension(200, 50));
            downloadInformationLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            downloadInformationLabel.setFont(new Font("Aria", Font.ITALIC, 14));
            JPanel panel = new JPanel(new BorderLayout());
            progressBar = new JProgressBar() ;
            progressBar.setBounds(40, 40, 160, 30);
            progressBar.setStringPainted(true);
            progressBar.setValue(download.getProgressValue());
            panel.add(downloadInformationLabel, BorderLayout.CENTER);
            panel.add(progressBar, BorderLayout.SOUTH);
            panel.setBorder(BorderFactory.createCompoundBorder());
            downloadPanels.add(panel);
        }

        // add panels to the mainPanel :

        for (JPanel panel : downloadPanels) {
            n++;
            mainPanel.add(panel);
            gridLayout.setRows(n);
        }


        // create and add scrollbar :


        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.add(mainPanel, BorderLayout.NORTH);
        JScrollPane downloadsScroll = new JScrollPane(borderPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        downloadsScroll.setPreferredSize(new Dimension(300, 300));


        //System.out.println(downloadPanels.get(0).getBackground().toString());


        setPreferredSize(new Dimension(350, 300));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        add(downloadsScroll, BorderLayout.CENTER);


        // set actionlistener for rightclick on downloadPanels and open a  download frame :


        for (JPanel panel : downloadPanels) {

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    if (mouseEvent.getModifiers() == MouseEvent.BUTTON3_MASK) {
                        DownloadFrame downloadFrame = new DownloadFrame(downloadsList.get(downloadPanels.indexOf(panel)));
                    }

                    if (mouseEvent.getModifiers() == MouseEvent.BUTTON1_MASK) {
                        if (isSelected(panel)) {
                            System.out.println("salam");
                            unselectPanel(panel);
                            downloadsQueueFrame.revalidate();
                            downloadsQueueFrame.repaint();
                        } else {
                            System.out.println("salam");
                            selectPanel(panel);

                            downloadsQueueFrame.revalidate();
                            downloadsQueueFrame.repaint();
                        }
                    }

                }
            });
        }


    }


    public static ArrayList<Download> getDownloadsList() {
        return downloadsList;
    }



    public static void setDownloadsList (ArrayList<Download> downloadsList) {
        QueuePanel.downloadsList = downloadsList ;
    }

    public static void addDownload(Download download) {
        downloadsList.add(download);
    }



    public void selectPanel(JPanel panel) {
        panel.setBackground(Color.DARK_GRAY);
    }



    public static boolean isSelected(JPanel panel) {
        if (panel.getBackground().equals(Color.DARK_GRAY)) {
            return true;
        } else
            return false;
    }



    public void unselectPanel(JPanel panel) {
        panel.setBackground(new Color(214, 217, 223));
    }

    public static int selectedDownloadsNumber () {

        int counter = 0 ;

        for (JPanel panel : downloadPanels) {

            if (isSelected(panel))
                counter ++ ;
        }

        return counter ;
    }

@Deprecated
    public void deleteDownload(JPanel panel) {

        downloadsList.remove(downloadPanels.indexOf(panel));
        downloadPanels.remove(panel);
        MainFrame.updateDownloadPanel(1);
        downloadsQueueFrame.revalidate();
        downloadsQueueFrame.repaint();
    }



    public static void deleteFromDownloadList() {

        Iterator iterator = downloadPanels.iterator();

        while (iterator.hasNext()) {
            JPanel panel = (JPanel) iterator.next();
            if (isSelected(panel)) {
                downloadsList.remove(downloadPanels.indexOf(panel));
                iterator.remove();
            }
        }

    }



    public static void swapTwoDownloadInQueue () {

        int i1 = 0  , i2 = 0  ;

        if (selectedDownloadsNumber()==2) {
            for (int i = 0 ; i < downloadPanels.size() ; i ++ ) {
                if ( isSelected( downloadPanels.get(i))) {
                    i1 = i ;
                    break;
                }
            }
            for (int i = i1 + 1 ; i < downloadPanels.size() ; i++ ) {
                if (isSelected(downloadPanels.get(i))) {
                    i2 = i ;
                    break;
                }
            }
            swapDownloadInArrayList(i1,i2);
        }

        else {

            JOptionPane.showMessageDialog(DownloadsQueueFrame.getInstance(),"Select two downloads for swap .","Alert",JOptionPane.WARNING_MESSAGE);
        }

    }


    public static void swapDownloadInArrayList(int i1, int i2) {

        Download temp = downloadsList.get(i1);
        downloadsList.set(i1, downloadsList.get(i2));
        downloadsList.set(i2, temp);
    }



    public static void startQueue () {

        for (Download download : downloadsList) {
            if (download.getStatus().equals("Paused")){
                download.setStatus("downloading...");
                download.startToDownload();
                break;
            }
        }

    }

    public static void stopQueue () {

        for (Download download : downloadsList) {

            if (download.getStatus().equals("downloading...")) {
                download.setStatus("Paused");
                break;
            }

        }

    }

    public static boolean inQueue (Download download) {

        for (Download download1 : downloadsList) {
            if (download1.getUrl().equals(download.getUrl()))
                return true ;

        }

        return false;

    }



}
