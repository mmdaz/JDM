import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoPanel extends JPanel {

    public LogoPanel () {
        setBackground(Color.DARK_GRAY);
        setLayout( new FlowLayout());
        BufferedImage img = null ;
        try {
            img = ImageIO.read( new File("icon1.jpg")) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel jLabel = new JLabel() ;
        jLabel.setSize(130,88);
        Image icon = img.getScaledInstance( jLabel.getWidth() , jLabel.getHeight() , Image.SCALE_SMOOTH ) ;
        ImageIcon icon1 = new ImageIcon(icon) ;
        jLabel.setIcon(icon1);
        setPreferredSize( new Dimension(100,40));
        add(jLabel) ;
    }

}
