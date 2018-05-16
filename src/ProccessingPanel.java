import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadPoolExecutor;

public class ProccessingPanel extends JPanel {

    private JButton processingButton ;
    private JButton completedButton ;
    private JButton defaultButton ;


    public ProccessingPanel () {

        setLayout( new GridLayout(2,1,5,5));
        setPreferredSize( new Dimension(130,380));
        processingButton = new JButton("Processing") ;
        completedButton = new JButton("Completed") ;
        defaultButton = new JButton("About") ;
        JPanel buttonsPanel = new JPanel( new GridLayout(3,1,5,5)) ;
        JPanel emptyPanel = new JPanel() ;
        emptyPanel.setBackground(Color.DARK_GRAY);
        buttonsPanel.add(processingButton) ;
        buttonsPanel.add(completedButton) ;
        buttonsPanel.add(defaultButton) ;
        add(buttonsPanel) ;
        add(emptyPanel) ;


        // add buttons actionlistener :

        defaultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                HelpFrame helpFrame = new HelpFrame() ;
            }
        });



    }

}
