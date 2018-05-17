
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DownloadFrame extends JFrame {

    private JButton pause ;
    private JButton cancel ;
    private JButton resume ;
    private Download download ;
    private JProgressBar progressBar ;


    public  DownloadFrame ( Download download ) {

        setSize(500,200);
        JPanel contentPain = new JPanel( new BorderLayout()) ;

        // download information label :

//        download = new Download("name" , "status" , "size" , "save Address" ,
//                "createdtime" , "finished time" , "url" ) ;

        this.download = download ;

        JLabel informationLabel = new JLabel("<html>file name : " + download.getName() + "<br>status : "+download.getStatus() +
                "\nsize : "+download.getSize() + "<br>created Time : "+download.getCreatedTime() + "<br>finished time : " +
                download.getFinishedTime() + "<br>url : " +download.getUrl() +"</html>") ;


        // progressBar :

        progressBar = new JProgressBar(0,100) ;
        progressBar.setBounds(40,40,160,30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);



        // buttons :

        JPanel buttonsPanel = new JPanel( new BorderLayout()) ;
        buttonsPanel.setPreferredSize( new Dimension(200,50));
        JPanel emptyPanel = new JPanel() ;
        cancel = new JButton("Cancel") ;
        resume = new JButton("Continue") ;
        pause = new JButton("Pause") ;
        cancel.setBackground(Color.DARK_GRAY);
        resume.setBackground(Color.DARK_GRAY);
        pause.setBackground(Color.DARK_GRAY);
        buttonsPanel.add(pause , BorderLayout.WEST) ;
        buttonsPanel.add(resume , BorderLayout.CENTER) ;
        buttonsPanel.add(cancel , BorderLayout.EAST) ;
        buttonsPanel.add(emptyPanel , BorderLayout.SOUTH) ;


        contentPain.add(informationLabel , BorderLayout.NORTH) ;
        contentPain.add(progressBar , BorderLayout.CENTER) ;
        contentPain.add(buttonsPanel , BorderLayout.SOUTH) ;


        setTitle(download.getName());
        setContentPane(contentPain);
        setVisible(true);


        // set actionlistener to buttons :

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                dispose();
            }
        });

        resume.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("continue");
            }
        });

        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("paused clicked ");
            }
        });




    }


}