
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Shape;
//import java.awt.Stroke;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    float currentWidth = 1.0f;
    Color currentColor = Color.black;
    Color bgColor = Color.white;
    BufferedImage bufferImage = null;
    Graphics2D bufferGraphics = null;
    JTextField text;
    JButton button;

    private void createBuffer(int width, int height) {
        bufferImage = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_INT_BGR);
        bufferGraphics = bufferImage.createGraphics();
        bufferGraphics.setBackground(bgColor);
        bufferGraphics.clearRect(0, 0, width * 2, height * 2);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        if (null == bufferGraphics) {
            this.createBuffer(this.getWidth(), this.getHeight());
            // 初期化したいことはここに追加
        }
        // Graphics2D g = (Graphics2D) this.getGraphics();
        // BasicStroke bs = new BasicStroke(currentWidth);
        // g.setStroke(bs);
        bufferGraphics.setStroke(new BasicStroke(currentWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        bufferGraphics.setColor(currentColor);
        bufferGraphics.drawLine(x1, y1, x2, y2);
        repaint();
    }

    public void drawRect(int x1, int y1, int width, int height) {
        if (null == bufferGraphics) {
            this.createBuffer(this.getWidth(), this.getHeight());
            // 初期化したいことはここに追加
        }
        // Graphics2D g = (Graphics2D) this.getGraphics();
        // BasicStroke bs = new BasicStroke(currentWidth);
        // g.setStroke(bs);
        bufferGraphics.setStroke(new BasicStroke(currentWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        bufferGraphics.setColor(currentColor);
        bufferGraphics.drawRect(x1, y1, width, height);
        repaint();

    }

    public void drawOval(int x1, int y1, int width, int height) {
        if (null == bufferGraphics) {
            this.createBuffer(this.getWidth(), this.getHeight());
            // 初期化したいことはここに追加
        }
        // Graphics2D g = (Graphics2D) this.getGraphics();
        // BasicStroke bs = new BasicStroke(currentWidth);
        // g.setStroke(bs);
        bufferGraphics.setStroke(new BasicStroke(currentWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        bufferGraphics.setColor(currentColor);
        bufferGraphics.drawOval(x1, y1, width, height);
        repaint();

    }

    public void clearAll(int width, int height) {
        if (null == bufferGraphics) {
            this.createBuffer(this.getWidth(), this.getHeight());
            // 初期化したいことはここに追加
        }
        // Graphics2D g = (Graphics2D) this.getGraphics();
        // BasicStroke bs = new BasicStroke(currentWidth);
        // g.setStroke(bs);
        bufferGraphics.setStroke(new BasicStroke(currentWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        bufferGraphics.setColor(bgColor);
        bufferGraphics.fillRect(0, 0, width, height);
        repaint();
    }

    /*
     * public void drawText(int x, int y, String str) {
     * if (null == bufferGraphics) {
     * this.createBuffer(this.getWidth(), this.getHeight());
     * // 初期化したいことはここに追加
     * }
     * // Graphics2D g = (Graphics2D) this.getGraphics();
     * // BasicStroke bs = new BasicStroke(currentWidth);
     * // g.setStroke(bs);
     * bufferGraphics.setStroke(new BasicStroke(currentWidth, BasicStroke.CAP_ROUND,
     * BasicStroke.JOIN_MITER));
     * bufferGraphics.setColor(currentColor);
     * 
     * bufferGraphics.drawString(str, x, y);
     * repaint();
     * }
     */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (null != bufferImage) {
            g.drawImage(bufferImage, 0, 0, this);
        }
    }

    /*
     * public void drawHelloWorld(Graphics g) {
     * Graphics2D g2d = (Graphics2D) g;
     * Dimension d = getSize();
     * g2d.drawString("Hello world!!", d.width / 2, d.height / 2);
     * }
     */

    public void setPenWidth(float newWidth) {
        currentWidth = newWidth;
    }

    public void setPenColor(Color newColor) {
        currentColor = newColor;
    }

    public void openFile(File file2open) {
        BufferedImage pictureImage;
        try {
            pictureImage = ImageIO.read(file2open);
        } catch (Exception e) {
            System.out.println("Error: reading file =" + file2open.getName());
            return;
        }
        this.createBuffer(pictureImage.getWidth(), pictureImage.getHeight());
        bufferGraphics.drawImage(pictureImage, 0, 0, this);
        repaint();

    }

    public void saveFile(File file2save) {
        try {
            ImageIO.write(bufferImage, "jpg", file2save);
        } catch (Exception e) {
            System.out.println("Error: writing file = " + file2save.getName());
            return;
        }
    }

    /*
     * public void drawText() {
     * JTextField text = new JTextField(15);
     * JButton button = new JButton("Enter");
     * 
     * // panel.setLayout(new GridLayout(2, 2));
     * // panel.setLayout(new FlowLayout());
     * this.getContentPane().add(panel2);
     * this.getContentPane().add(BorderLayout.SOUTH, button);
     * this.add(text);
     * this.add(button);
     * setSize(300, 200);
     * setVisible(true);
     * 
     * }
     */

    /*
     * public void makeNewDrawPanel(String title) {
     * DrawPanel panel;
     * int width = 100;
     * int height = 200;
     * 
     * panel.setTitle(title);
     * panel.setSize(width, height);
     * 
     * 
     * 
     * }
     */

}