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
    private JLabel informationLabel ;
    private JPanel contentPain ;


    public  DownloadFrame ( Download download ) {

        setSize(500,200);
        contentPain = new JPanel( new BorderLayout()) ;

        // download information label :

        this.download = download ;
        informationLabel = new JLabel("<html>file name : " + download.getName() + "<br>status : "+download.getStatus() +
                "\nsize : "+download.getSize() + "<br>created Time : "+download.getCreatedTime() + "<br>finished time : " +
                download.getFinishedTime() + "<br>url : " +download.getUrl() +"</html>") ;


        // progressBar :

        progressBar = new JProgressBar() ;
        progressBar.setBounds(40,40,160,30);
        progressBar.setStringPainted(true);
        progressBar.setValue(download.getProgressValue()) ;



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
                if (!download.getStatus().equals("completed")) {
                    download.setStatus("canceled");
                    download.setProgressValue(0);
                }
                MainFrame.updateDownloadPanel(3);
                dispose();
            }
        });

        resume.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
               download.setStatus("downloading...");
               updateFrame();
               MainFrame.updateDownloadPanel(1);
                System.out.println("continue");
            }
        });

        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                download.setStatus("Paused");
                updateFrame();
                MainFrame.updateDownloadPanel(3);
                System.out.println("paused clicked ");
            }
        });


    }


    private void updateFrame () {

        contentPain.remove(informationLabel);
        informationLabel = new JLabel("<html>file name : " + download.getName() + "<br>status : "+download.getStatus() +
                "\n<p>size</p> : "+download.getSize() + "<br>created Time : "+download.getCreatedTime() + "<br>finished time : " +
                download.getFinishedTime() + "<br>url : " +download.getUrl() +"</html>") ;

        contentPain.add(informationLabel , BorderLayout.NORTH) ;
        contentPain.add(progressBar , BorderLayout.CENTER) ;
        contentPain.revalidate();
        contentPain.repaint();




    }


}