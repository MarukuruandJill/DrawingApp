import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SizeControl extends JFrame {
    JSlider sizeSlider;
    JLabel label;

    public SizeControl(String str, DrawPanel panel) {
        setTitle(str + "Selecter");
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sizeSlider = new JSlider(0, 30, 5);
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float penSize = sizeSlider.getValue();
                label.setText("value: " + penSize);
                // メインウィンドウにペンの太さを送信
                panel.setPenWidth(penSize);
            }
        });
        label = new JLabel();
        label.setText("value: " + sizeSlider.getValue());
        this.getContentPane().add(BorderLayout.CENTER, sizeSlider);
        this.getContentPane().add(BorderLayout.SOUTH, label);
        pack();
        setVisible(true);
    }
}
