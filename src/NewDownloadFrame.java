import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.Serializable;

public class NewDownloadFrame extends JFrame implements Serializable {

    private JButton choosePath ;
    private JButton ok ;
    private JButton addToQueue ;
    private JButton cancel ;
   private JFileChooser pathChooser ;
   private String savePath ;
   private String downloadAddressString ;



    public NewDownloadFrame () {


        setSize(400,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel contentPain = new JPanel( new BorderLayout(20,20)) ;

        pathChooser = new JFileChooser() ;
        pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        JTextField downloadAddress = new JTextField() ;
        JLabel textLabel = new JLabel("Add Download Address : ") ;
        choosePath = new JButton("Choose Download Path ") ;
        choosePath.addMouseListener( new FileChooserMouseListener());
        JPanel addressBarPanel = new JPanel( new BorderLayout()) ;
        JPanel pathChoosePanel = new JPanel( new BorderLayout()) ;
        pathChoosePanel.setPreferredSize( new Dimension(200,50));
        JPanel okCancelPanel = new JPanel( new GridLayout(1,3,5,5)) ;
        okCancelPanel.setPreferredSize( new Dimension(400,50));
        ok = new JButton("Download Now") ;
        ok.setBackground(Color.gray);
        cancel = new JButton("Cancel") ;
        cancel.setBackground(Color.gray);
        addToQueue = new JButton("Add To Queue") ;
        addToQueue.setBackground(Color.LIGHT_GRAY);
        addressBarPanel.add(textLabel , BorderLayout.WEST) ;
        addressBarPanel.add(downloadAddress , BorderLayout.SOUTH) ;
        pathChoosePanel.add(choosePath , BorderLayout.CENTER) ;
        okCancelPanel.add(ok) ;
        okCancelPanel.add(addToQueue);
        okCancelPanel.add(cancel) ;

        contentPain.add(addressBarPanel , BorderLayout.NORTH) ;
        contentPain.add(pathChoosePanel , BorderLayout.CENTER) ;
        contentPain.add(okCancelPanel , BorderLayout.SOUTH) ;
        setContentPane(contentPain);


        setVisible(true);


        // add actopn listner to the buttons :
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                dispose();
            }
        });

        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                downloadAddressString = downloadAddress.getText() ;
//                System.out.println("download address : "+downloadAddressString);
//                System.out.println("save path : "+savePath);
                DownloadPanel.addDownload(new Download(downloadAddressString));
                DownloadPanel.setDownloadsList(DownloadPanel.progressDownloadlist);
                MainFrame.updateDownloadPanel(1);
                dispose();

            }
        });


        addToQueue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                downloadAddressString = downloadAddress.getText() ;
                Download download = new Download(downloadAddressString) ;
                QueuePanel.addDownload(download);
                DownloadPanel.addDownload(download);
                MainFrame.updateDownloadPanel(1);
                dispose();
            }
        });



    }

    class FileChooserMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            int result = pathChooser.showOpenDialog(null);
            if (result == JFileChooser.CANCEL_OPTION) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
            }
            else if (result == JFileChooser.APPROVE_OPTION) {
                pathChooser.showDialog(null , "choose") ;
                savePath = pathChooser.getSelectedFile().getAbsolutePath();
                System.out.println(" save path " + savePath);
            }
        }

    }
}
