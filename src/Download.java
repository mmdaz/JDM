import javax.swing.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Download implements Serializable {
    private String name ;
    private String status ;
    private String size ;
    private String saveAdress ;
    private String createdTime ;
    private String finishedTime ;
    private String url ;
    private JProgressBar progressBar ;



    public Download ( String url ) {
        this.url = url ;

        this.name = "download" ;
        this.status = "downloading..." ;
        this.finishedTime = " finish time" ;
        this.size = "size" ;
        this.createdTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()); ;
        this.saveAdress = "s address" ;

        progressBar = new JProgressBar(0,100) ;
        progressBar.setBounds(40,40,160,30);
        progressBar.setValue(50);
        progressBar.setStringPainted(true);



    }

    public Download (Download anotherDownload) {

        this.name = anotherDownload.name ;
        this.createdTime = anotherDownload.createdTime ;
        this.finishedTime = anotherDownload.finishedTime ;
        this.progressBar = anotherDownload.progressBar ;
        this.saveAdress = anotherDownload.saveAdress ;
        this.size = anotherDownload.size ;
        this.status = anotherDownload.status ;
        this.url = anotherDownload.url ;

    }

    public Download () {

        this.name = "download" ;
        this.status = "status" ;
        this.finishedTime = " finish time" ;
        this.size = "size" ;
        this.createdTime = " c time" ;
        this.url = "url" ;
        this.saveAdress = "s address" ;

        progressBar = new JProgressBar(0,100) ;
        progressBar.setBounds(40,40,160,30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

    }

    public Download ( String name , String status , String size , String saveAdress , String createdTime , String finishedTime , String url ) {
        this.name = name;
        this.createdTime = createdTime ;
        this.finishedTime = finishedTime ;
        this.saveAdress = saveAdress ;
        this.size = size ;
        this.status = status ;
        this.url = url ;

        progressBar = new JProgressBar(0,100) ;
        progressBar.setBounds(40,40,160,30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);


    }

    public String getName() {
        return name;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public String getFinishedTime() {
        return finishedTime;
    }

    public String getSaveAdress() {
        return saveAdress;
    }

    public String getSize() {
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

    public void setSize(String size) {
        this.size = size;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


