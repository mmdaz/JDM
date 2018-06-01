
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.File;

public class Download implements Serializable , PropertyChangeListener {
    private String name ;
    private String status ;
    private int size ;
    private String saveAdress ;
    private String createdTime ;
    private String finishedTime ;
    private String url ;
    private int progressValue ;
    private int speed ;



    public Download ( String url ) {
        this.url = url ;
        this.name = url ;
        if (DownloadPanel.downloadingFilesNumber() > SettingsFrame.sameDownloadNumbers ) {
            this.status = "Paused" ;
        }
        else {
            this.status = "downloading...";
        }
        this.finishedTime = " finish time" ;
        this.size = 0  ;
        this.createdTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        this.saveAdress = "s address" ;
        this.progressValue = 0;
        this.speed = 0 ;


    }

    public Download (Download anotherDownload) {

        this.name = anotherDownload.name ;
        this.createdTime = anotherDownload.createdTime ;
        this.finishedTime = anotherDownload.finishedTime ;
        this.saveAdress = anotherDownload.saveAdress ;
        this.size = anotherDownload.size ;
        this.status = anotherDownload.status ;
        this.url = anotherDownload.url ;

    }

    public Download () {

        this.name = "download" ;
        this.status = "status" ;
        this.finishedTime = " finish time" ;
        this.size = 500 ;
        this.createdTime = " c time" ;
        this.url = "url" ;
        this.saveAdress = "s address" ;
    }

    public Download ( String name , String status , int size , String saveAdress , String createdTime , String finishedTime , String url ) {
        this.name = name;
        this.createdTime = createdTime ;
        this.finishedTime = finishedTime ;
        this.saveAdress = saveAdress ;
        this.size = size ;
        this.status = status ;
        this.url = url ;

    }

    public int getProgressValue () {

        return progressValue ;

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public String getCreatedTime() {
        return createdTime;
    }


    public String getFinishedTime() {
        return finishedTime;
    }

    public String getSaveAdress() {
        return saveAdress;
    }

    public int getSize() {
        return size;
    }

    public String getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public void setSaveAdress(String saveAdress) {
        this.saveAdress = saveAdress;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue = progressValue;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            progressValue =progress ;
        }
    }

    public void startToDownload () {

        System.out.println(url);
        System.out.println(isFilter());

        File file = new File(saveAdress) ;

        if (!file.exists()) {

            if (isFilter()) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "This site is filter .", "Filter!!!", JOptionPane.ERROR_MESSAGE);
            } else {
                if (status.equals("downloading...")) {

                    if (url.equals("")) {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Please enter download URL!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (SettingsFrame.savePath.equals("")) {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(),
                                "Please choose a directory save file!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {

                        DownloadFile downloadFile = new DownloadFile(url, SettingsFrame.savePath, this);
                        downloadFile.addPropertyChangeListener(this::propertyChange);
                        downloadFile.execute();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(),
                                "Error executing upload task: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }


                }
            }
        }


    }

    private boolean isFilter () {

        String[] filterSites = SettingsFrame.getFilterSites() ;

        for (String filterSite : filterSites) {
//            System.out.println(filterSite);
//            System.out.println(url);
            if (url.matches(".*" + filterSite + ".*")) {
                System.out.println("salam true");
                return true;
            }
        }

        return false ;

    }

}


