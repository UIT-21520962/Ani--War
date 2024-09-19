package Frame;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {

    public MyFrame() throws MalformedURLException {
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        gamePanel.setupGame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Ani World");
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
        URL urllogo = MyFrame.class.getResource("/Image/logo.png");
        ImageIcon img = new ImageIcon(urllogo); //create an ImageIcon
        frame.setIconImage(img.getImage());

        //frame.setSize(1080, 960);
        //frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        gamePanel.startGameThread();

    }
}
