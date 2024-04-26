import javax.swing.*;
import java.awt.*;

public class FullScreenFrame extends JFrame {
    public FullScreenFrame(String title){
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize= kit.getScreenSize();
        this.setSize(screenSize.width, screenSize.height);



    }

}
