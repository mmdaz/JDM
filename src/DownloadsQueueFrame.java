import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DownloadsQueueFrame extends JFrame {

    private List<Download> downloadsQueue ;

    public DownloadsQueueFrame () {

    }


    public void addTOQueue (Download download) {
        downloadsQueue.add(download) ;
    }

    public void sortByCreateTime () {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp);

    }

    private Download earlyDownload (Download download1 , Download download2) {

         return download1 ;

    }




}
