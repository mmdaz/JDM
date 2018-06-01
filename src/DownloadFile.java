
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class DownloadFile extends SwingWorker < String , Integer > {

    public static final int BUFFER_SIZE = 4096 ;
    private String downloadUrl ;
    private String saveDirectory ;
    private Download download ;
    private Long startTime ;
    private Long finishTime ;
    private int bytesRead ;


    public DownloadFile ( String downloadUrl , String saveDirectory , Download download ) {

        this.download = download ;
        this.downloadUrl = downloadUrl ;
        this.saveDirectory = saveDirectory ;

    }


    @Override
    protected void process(List<Integer> chunks) {

        double time = ( finishTime - startTime ) / Math.pow(10,9) ;
        double speed = bytesRead / Math.pow(10,3) / time ;
        download.setSpeed((int) speed);
        System.out.println("processed");

        MainFrame.refresh();
    }

    @Override
    protected String doInBackground() {
        System.out.println(download.getStatus());
        try {

            HTTPDownloadUtil util = new HTTPDownloadUtil() ;
            util.downloadFile(downloadUrl , saveDirectory);
            download.setSize(util.getContentLength());
            if (util.getFileName() != null && util.getFileName().contains("=")) {
                download.setName(util.getFileName().split("=")[1].replaceAll("\"", ""));
            }
            else
            download.setName(util.getFileName());

            String saveFilePath = saveDirectory + File.separator + util.getFileName() ;

            BufferedInputStream inputStream = new BufferedInputStream(util.getInputStream()) ;
            download.setSaveAdress(saveFilePath);

            // file operations :

            FileOutputStream outputStream = new FileOutputStream(saveFilePath) ;


            download.setSaveAdress(saveFilePath) ;
            byte[] buffer = new byte[BUFFER_SIZE] ;
            bytesRead = -1 ;
            long totalBytesRead = 0 ;
            int percentCompleted = 0 ;
            long fileSize = util.getContentLength();
            System.out.println(fileSize);
            while ((bytesRead = inputStream.read(buffer)) > 0 ) {
                if (Thread.interrupted())
                    break ;
                if (download.getStatus().equals("downloading...")) {
                    startTime = System.nanoTime() ;
                    outputStream.write(buffer, 0, bytesRead) ;
                    totalBytesRead += bytesRead ;
                    finishTime = System.nanoTime() ;
                    percentCompleted = (int) (((totalBytesRead + 0.0) / fileSize) * 100);
                    download.setProgressValue(percentCompleted);
                    publish(getProgress());
                }
                else if (download.getStatus().equals("Paused")) {
                   Thread.sleep(1000);
                }
                else if (download.getStatus().equals("canceled")) {
                    File fileForDelete = new File(saveFilePath) ;
                    if (fileForDelete.delete())
                        System.out.println("file deleted");
                    else
                        System.out.println("not deleted");
                    return "download canceled" ;
                }

            }

            outputStream.flush();
            outputStream.close();

            util.disconnect();

        }

        catch (IOException ex) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Error downloading file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            setProgress(0);
            cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Ok";


    }

    protected void done() {
        try {
            get() ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CancellationException e) {
            System.out.println();
        }
        if (download.getStatus().equals("Paused")) {
            System.out.println("download is paused");
        }
        else if (download.getStatus().equals("canceled")) {
            System.out.println("download is canceled");
        }
        else {
            download.setStatus("completed");
            download.setProgressValue(100);
            download.setFinishedTime(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
        }

        if (QueuePanel.inQueue(download)) {
            System.out.println("in Queue");
            QueuePanel.startQueue();
            MainFrame.updateDownloadPanel(1);
        }

        System.out.println("done");

        if (download.getStatus().equals("completed")) {
            if (!isCancelled()) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(),
                        "File has been downloaded successfully!", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        cancel(true) ;
    }
}
