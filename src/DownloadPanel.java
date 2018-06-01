
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

/**
 *  The DownloadPanel program is an application that simply
 *  get a download object and show it information in the main frame .
 *
 * @author Azhdari Muhammad
 * @version 1.0
 */

public class DownloadPanel extends JPanel implements Serializable {

    public static JProgressBar progressBar;
    public static Vector<Download> downloadsList = new Vector<Download>();
    public static ArrayList<JPanel> downloadPanels;
    private MainFrame mainFrame = MainFrame.getInstance();
    public static ArrayList<Download> completedDownloadsList = new ArrayList<Download>();
    public static ArrayList<Download> progressDownloadlist = new ArrayList<Download>() ;


    public DownloadPanel( int coditionNumber ) {

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
                downloadInformationLabel.setPreferredSize(new Dimension(200, 65));
                downloadInformationLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                downloadInformationLabel.setFont(new Font("Aria", Font.ITALIC, 14));
                JPanel panel = new JPanel(new BorderLayout());
                progressBar = new JProgressBar() ;
                progressBar.setBounds(40, 40, 160, 30);
                progressBar.setStringPainted(true);
                int progressValue = download.getProgressValue();
//                System.out.println("prog"+progressValue);
                progressBar.setValue(progressValue);
                panel.add(downloadInformationLabel, BorderLayout.CENTER);
                panel.add(progressBar, BorderLayout.SOUTH);
                panel.setBorder(BorderFactory.createCompoundBorder());
                downloadPanels.add(panel);
            }
        }

        else if (coditionNumber == 2) {

            for (Download download : completedDownloadsList) {

                JLabel downloadInformationLabel = new JLabel("<html>file name : " + download.getName() + "<br/> url : " + download.getUrl() + "<br/>" + download.getStatus() + "</html>");
                downloadInformationLabel.setPreferredSize(new Dimension(200, 65));
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
                downloadInformationLabel.setPreferredSize(new Dimension(200, 65));
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
                downloadInformationLabel.setPreferredSize(new Dimension(200, 65));
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
                            mainFrame.revalidate();
                            mainFrame.repaint();
                        } else {
                            System.out.println("salam");
                            selectPanel(panel);

                            mainFrame.revalidate();
                            mainFrame.repaint();
                        }
                    }
                    if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed() && downloadsList.get(downloadPanels.indexOf(panel)).getStatus().equals("completed")) {
                        mouseEvent.consume();
                        System.out.println("2  clicked");

                        File file = new File(downloadsList.get(downloadPanels.indexOf(panel)).getSaveAdress()) ;
                        try {
                            Desktop.getDesktop().open(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }


    }

    public void refreshProgressBar () {
        for (Download download : downloadsList) {

            progressBar.setValue(download.getProgressValue());
        }

    }

    public static void setDownloadsList(Vector<Download> downloadsList) {
        DownloadPanel.downloadsList = downloadsList;
    }

    public Vector<Download> getDownloadsList() {
        return downloadsList;
    }


    /**
     * This method get a download and add it to the downloads list .
     * @param download
     */
    public static void addDownload(Download download) {
        downloadsList.add(download);
    }

    /**
     * This method select the download panel by changing it color .
     * @param panel
     */

    public void selectPanel(JPanel panel) {
        panel.setBackground(Color.DARK_GRAY);
    }

    /**
     * This method check that a download panel is selected or not .
     * @param panel
     * @return true if panel is selected and false if is not .
     */

    public static boolean isSelected(JPanel panel) {
        if (panel.getBackground().equals(Color.DARK_GRAY)) {
            return true;
        } else
            return false;
    }

    /**
     * This method unselect the download panel by changing its color to main color .
     * @param panel
     */

    public void unselectPanel(JPanel panel) {
        panel.setBackground(new Color(214, 217, 223));
    }



@Deprecated
    public void deleteDownload(JPanel panel) {

        downloadsList.remove(downloadPanels.indexOf(panel));
        downloadPanels.remove(panel);
        MainFrame.updateDownloadPanel(1);
        mainFrame.revalidate();
        mainFrame.repaint();
    }


    /**
     * This method iterates download lists ann delete selected downloads
     */
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

    /**
     * This method has a foreach to iterate download list and id a download is downloading add
     * it to the progress downloads list .
     */

    public static void addProgresssDownload () {


        progressDownloadlist.clear();
        for (Download download : downloadsList) {
            if (download.getStatus().equals("downloading...")) {
                progressDownloadlist.add(download);
            }
        }
    }

    /**
     * This method has a foreach to iterate download list and id a download is completed add
     *  it to the completed downloads list .
     */
    public static void addCompletedDownload() {
        completedDownloadsList.clear();
        for (Download download : downloadsList) {
            if (download.getStatus().equals("completed")) {
                completedDownloadsList.add(download);
            }
        }
    }

    /**
     * This method add selected downloads to the queue .
     */
    public static void addSelectedDownloadsToQueue () {

        for (Download download : downloadsList) {

            if (isSelected(downloadPanels.get(downloadsList.indexOf(download)))) {
                download.setStatus("Paused");
                QueuePanel.addDownload(download);
            }

        }

    }

    /**
     * This method pause selected downloads .
     */

    public static void pauseDownloads() {

      for (JPanel panel : downloadPanels ) {
          if (isSelected(panel)) {

              downloadsList.get(downloadPanels.indexOf(panel)).setStatus("Paused");
          }

      }

    }

    /**
     * This method resume downloading of selected and  Paused downloads .
     */
    public static void continueDownloads () {
        for (JPanel panel : downloadPanels ) {
            if (downloadingFilesNumber() == SettingsFrame.sameDownloadNumbers ) {
                JOptionPane.showMessageDialog(MainFrame.getInstance() , "Out of range Same Time Download ." , "Error" , JOptionPane.ERROR_MESSAGE);
                break;
            }
            if (isSelected(panel)) {
                downloadsList.get(downloadPanels.indexOf(panel)).setStatus("downloading...");
                downloadsList.get(downloadPanels.indexOf(panel)).startToDownload();
            }


        }


    }

    /**
     * This method cancel selected downloads .
     */
    public static void cancelDownload () {

        for (JPanel panel : downloadPanels ) {
            if (isSelected(panel)) {

                downloadsList.get(downloadPanels.indexOf(panel)).setStatus("canceled");
            }

        }


    }

    /**
     * This method sort downloads list by the name of downloads .
     * @return an ArrayList os sorted downloads .
     */
    public static Vector<Download> sortByName () {

        Vector<String> tempName = new Vector<String>() ;

        for (Download download : downloadsList) {
            tempName.add(download.getName()) ;
        }
        Collections.sort(tempName , String.CASE_INSENSITIVE_ORDER);

        Vector<Download> sortedByNameList = new Vector<Download>() ;

        for (String s : tempName) {
            if (downloadsList.size() != 0) {
                for (Download download : downloadsList) {
                    if (download.getName().equals(s)) {
                        sortedByNameList.add(download);
                    }
                }
            }
        }
        return sortedByNameList ;
    }

    /**
     * This method sort downloads list by the size of downloads .
     *@return an ArrayList os sorted downloads .
     */
    public static Vector<Download> sortBySize () {

        ArrayList<Integer> tempSize = new ArrayList<Integer>() ;

        for (Download download : downloadsList) {
            tempSize.add(download.getSize()) ;
        }
        Collections.sort(tempSize);

        Vector<Download> sortedByNameList = new Vector<Download>() ;

        for (Integer integer : tempSize) {
            for (Download download : downloadsList) {
                if (download.getSize() == integer) {
                    sortedByNameList.add(download) ;
                }
            }
        }
        return sortedByNameList ;
    }

    /**
     * This method count the in progress downloads .
     * @return the number of in progress downloads .
     */
    public static int downloadingFilesNumber () {

        int counter = 0 ;

        for (Download download : downloadsList ) {
            if (download.getStatus().equals("downloading...")) {
                counter ++ ;
            }
        }

        return counter ;

    }
}
