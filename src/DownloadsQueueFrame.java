import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DownloadsQueueFrame extends JFrame {

    private JButton delete ;
    private JButton sortByDate ;
    private JButton sortByName ;
    private JButton sortBySize ;
    private JButton startQueue ;
    private JButton puaseQueu ;
    private JPanel contentPain ;
    public static QueuePanel queuePanel ;


    public DownloadsQueueFrame () {

        setSize(600,400);
        QueuePanel queuePanel = new QueuePanel() ;
        contentPain = new JPanel( new BorderLayout()) ;

        // create and add buttons panel :


        JPanel buttonsPanel = new JPanel( new GridLayout(1,5,5,5)) ;
        delete = new JButton("Delete") ;
        sortByDate = new JButton("Sort By Date ") ;
        sortByName = new JButton("Sort By Name ") ;
        sortBySize = new JButton("Sort By Size ") ;
        startQueue = new JButton("Start") ;
        puaseQueu = new JButton("Puase") ;
        buttonsPanel.add(delete) ;
        buttonsPanel.add(sortByDate) ;
        buttonsPanel.add(sortByName) ;
        buttonsPanel.add(sortBySize) ;

        contentPain.add(queuePanel , BorderLayout.CENTER) ;
        contentPain.add(buttonsPanel , BorderLayout.SOUTH) ;
        add(contentPain);

        setVisible(true);


        // add  buttons action listener  :

        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                QueuePanel.deleteFromDownloadList();
                updateDownloadPanel();
            }
        });


    }


    private void updateDownloadPanel ( ) {
        contentPain.remove(queuePanel);
        queuePanel = new QueuePanel() ;
        contentPain.add(queuePanel , BorderLayout.CENTER) ;
        contentPain.revalidate();
        contentPain.repaint();

    }

}
