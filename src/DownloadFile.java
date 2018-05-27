import org.omg.CORBA.portable.InputStream;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadFile extends SwingWorker < Void , Void > {

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
    protected Void doInBackground() {

        try {
            HTTPDownloadUtil util = new HTTPDownloadUtil() ;
            util.downloadFile(downloadUrl);
            download.setSize(util.getContentLength());
            download.setName(util.getFileName());

            System.out.println("f" + util.getFileName());
            String saveFilePath = saveDirectory + File.separator + util.getFileName() ;

            InputStream inputStream = (InputStream) util.getInputStream();
            System.out.println("getinputstream");
         //   FileInputStream inputStream = new FileInputStream(String.valueOf(util.getInputStream())) ;

            // file operations :

            FileOutputStream outputStream = new FileOutputStream(saveFilePath) ;
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            long totalBytesRead = 0;
            int percentCompleted = 0;
            long fileSize = util.getContentLength();
            System.out.println(fileSize);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                percentCompleted = (int) (totalBytesRead * 100 / fileSize);

                setProgress(percentCompleted);
                download.setProgressValue(percentCompleted);
                System.out.println("g"+percentCompleted);
            }

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
        return null;


    }

    protected void done() {
        if (!isCancelled()) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "File has been downloaded successfully!", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
