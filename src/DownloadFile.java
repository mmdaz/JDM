

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class DownloadFile extends SwingWorker < String , Integer > {

    public static final int BUFFER_SIZE = 4096 ;
    private String downloadUrl ;
    private String saveDirectory ;
    private Download download ;

    public DownloadFile ( String downloadUrl , String saveDirectory , Download download ) {

        this.download = download ;
        this.downloadUrl = downloadUrl ;
        this.saveDirectory = saveDirectory ;

    }


    @Override
    protected void process(List<Integer> chunks) {

        System.out.println("processed");
        MainFrame.refresh();
    }

    @Override
    protected String doInBackground() {
        System.out.println(download.getStatus());
        try {
            HTTPDownloadUtil util = new HTTPDownloadUtil() ;
            util.downloadFile(downloadUrl);
            download.setSize(util.getContentLength());
            download.setName(util.getFileName());

            System.out.println("f" + util.getFileName());
            String saveFilePath = saveDirectory + File.separator + util.getFileName() ;

            BufferedInputStream inputStream = new BufferedInputStream(util.getInputStream()) ;

            // file operations :

            FileOutputStream outputStream = new FileOutputStream(saveFilePath) ;
            download.setSaveAdress(saveFilePath);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            long totalBytesRead = 0;
            int percentCompleted = 0;
            long fileSize = util.getContentLength();
            System.out.println(fileSize);
            while ((bytesRead = inputStream.read(buffer)) > 0 ) {
                if (Thread.interrupted())
                    break;
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                percentCompleted = (int) (((totalBytesRead+0.0) / fileSize)*100);
                download.setProgressValue(percentCompleted);
                publish(getProgress());
              //  setProgress(percentCompleted);

//                System.out.println("g"+percentCompleted);
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
        System.out.println("done");
        download.setStatus("completed");
        if (!isCancelled()) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "File has been downloaded successfully!", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        cancel(true) ;
    }
}
