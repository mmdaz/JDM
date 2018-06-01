import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A utility that downloads a file from a URL.
 *
 * @author Azhdari Muhammad
 *
 */
public class HTTPDownloadUtil {

    private HttpURLConnection httpConn;

    /**
     * hold input stream of HttpURLConnection
     */
    private InputStream inputStream;
    private String fileName;
    private int contentLength;

    /**
     * Downloads a file from a URL
     *
     * @param fileURL
     *            HTTP URL of the file to be downloaded
     * @throws IOException
     */
    public void downloadFile(String fileURL , String saveDirectory ) throws IOException {
        URL url = new URL(fileURL);
        httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        String saveFilePath = saveDirectory + File.separator + getFileName() ;

        File outPutFile = new File(saveFilePath) ;

        if (outPutFile.exists()) {
            System.out.println("salam exist");
            Long downloadedSize = outPutFile.length()  ;
            httpConn.setAllowUserInteraction(true);
            httpConn.setRequestProperty("Range" , "bytes" + downloadedSize);
            httpConn.connect();
        }
        else {
            httpConn.connect();
        }
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("ok");
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    System.out.println("filename from site");
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
            }

            // output for debugging purpose only
            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            inputStream = httpConn.getInputStream();

        } else {
            throw new IOException(
                    "No file to download. Server replied HTTP code: "
                            + responseCode);
        }
    }

    public void disconnect() throws IOException {
        inputStream.close();
        httpConn.disconnect();
    }

    public HttpURLConnection getHttpConn() {
        return httpConn;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }
}