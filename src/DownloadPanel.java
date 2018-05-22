import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class DownloadPanel extends JPanel implements Serializable {

    private JProgressBar progressBar;
    public static ArrayList<Download> downloadsList = new ArrayList<Download>();
    public static ArrayList<JPanel> downloadPanels;
    private MainFrame mainFrame = MainFrame.getInstance();
    public static ArrayList<Download> completedDownloadsList = new ArrayList<Download>();
    public static ArrayList<Download> progressDownloadlist = new ArrayList<Download>() ;


    public DownloadPanel( int coditionNumber ) {

//        downloadsList = new ArrayList<Download>() ;
        downloadPanels = new ArrayList<JPanel>();


        // **
        addCompletedDownload();
        addProgresssDownload();

        // all download panels added to this panel and this panel addedP to the scrollpane
        // and scroll pane added to the panel of this class :

        JPanel mainPanel = new JPanel();
        int n = 1;
        GridLayout gridLayout = new GridLayout(n, 1, 5, 5);
        mainPanel.setLayout(gridLayout);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // create jlabel of each download panel for show  information of download :



        if ( coditionNumber == 1) {


            for (Download download : progressDownloadlist) {

                JLabel downloadInformationLabel = new JLabel("<html>file name : " + download.getName() + "<br/> url : " + download.getUrl() + "<br/>" + download.getStatus() + "</html>");
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
        }
        else if (coditionNumber == 2) {

            for (Download download : completedDownloadsList) {

                JLabel downloadInformationLabel = new JLabel("<html>file name : " + download.getName() + "<br/> url : " + download.getUrl() + "<br/>" + download.getStatus() + "</html>");
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
        }


        else if ( coditionNumber == 3 ) {

            for (Download download : downloadsList) {

                JLabel downloadInformationLabel = new JLabel("<html>file name : " + download.getName() + "<br/> url : " + download.getUrl() + "<br/>" + download.getStatus() + "</html>");
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

        }

        else if (coditionNumber == 4) {

            for (Download download : ProccessingPanel.getSearchDownloadList()) {

                JLabel downloadInformationLabel = new JLabel("<html>file name : " + download.getName() + "<br/> url : " + download.getUrl() + "<br/>" + download.getStatus() + "</html>");
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

        }

        // add panels to the mainPanel :

        // System.out.println("p"+downloadsList.size() +"d"+downloadsList.size());
        //  int i = 0 ;
        for (JPanel panel : downloadPanels) {
            n++;
            mainPanel.add(panel);
            gridLayout.setRows(n);
            //  System.out.println(downloadsList.get(i).getName());
            // i ++ ;
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
                            mainFrame.revalidate();
                            mainFrame.repaint();
                        } else {
                            System.out.println("salam");
                            selectPanel(panel);

                            mainFrame.revalidate();
                            mainFrame.repaint();
                        }
                    }

                }
            });
        }


    }

    public static void setDownloadsList(ArrayList<Download> downloadsList) {
        DownloadPanel.downloadsList = downloadsList;
    }

    public ArrayList<Download> getDownloadsList() {
        return downloadsList;
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




    public void deleteDownload(JPanel panel) {

        downloadsList.remove(downloadPanels.indexOf(panel));
        downloadPanels.remove(panel);
        MainFrame.updateDownloadPanel(1);
        mainFrame.revalidate();
        mainFrame.repaint();
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



    public static void addProgresssDownload () {


        progressDownloadlist.clear();
        for (Download download : downloadsList) {
            if (download.getStatus().equals("downloading...")) {
                progressDownloadlist.add(download);
            }
        }
    }


    public static void addCompletedDownload() {
        completedDownloadsList.clear();
        for (Download download : downloadsList) {
            if (download.getStatus().equals("completed")) {
                completedDownloadsList.add(download);
            }
        }
    }

    public static void addSelectedDownloadsToQueue () {

        for (Download download : downloadsList) {

            if (isSelected(downloadPanels.get(downloadsList.indexOf(download)))) {
                QueuePanel.addDownload(download);
            }

        }

    }



    public static void pauseDownloads() {

      for (JPanel panel : downloadPanels ) {
          if (isSelected(panel)) {

              downloadsList.get(downloadPanels.indexOf(panel)).setStatus("Paused");
          }

      }

    }

    public static void continueDownloads () {
        for (JPanel panel : downloadPanels ) {
            if (isSelected(panel)) {

                downloadsList.get(downloadPanels.indexOf(panel)).setStatus("downloading...");
            }

        }


    }

    public static void cancelDownload () {

        for (JPanel panel : downloadPanels ) {
            if (isSelected(panel)) {

                downloadsList.get(downloadPanels.indexOf(panel)).setStatus("canceled");
            }

        }


    }
}
