
//import java.awt.Graphics;
//import java.awt.event.MouseListener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;

//import javax.swing.event.ChangeListener;
//import javax.swing.event.ChangeEvent;

//import java.awt.Graphics;

public class Canvas extends JFrame implements MouseMotionListener, ActionListener, MouseListener {
    DrawPanel panel;
    int mousex = 0;
    int mousey = 0;
    int width = 800;
    int height = 600;
    JFileChooser fileChooser = new JFileChooser();
    int regularPen = 0;
    int rectPen = 1;
    int ovalPen = 2;
    int penNumber;
    JSlider slider;
    JLabel label;
    float val;

    // 追加部分
    ImageIO image;
    // 追加部分

    private void init() {
        penNumber = regularPen;
        this.setTitle("Canvas");
        this.setSize(width, height);

        panel = new DrawPanel();
        panel.addMouseMotionListener(this);
        panel.addMouseListener(this);
        this.getContentPane().add(panel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addMenuItem(JMenuItem targetMenu, String itemName, String actionName, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(itemName);
        menuItem.setActionCommand(actionName);
        menuItem.addActionListener(listener);
        targetMenu.add(menuItem);
    }

    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        // this.addMenuItem(menuFile, "New", "New", this);
        this.addMenuItem(menuFile, "Open...", "Open", this);
        this.addMenuItem(menuFile, "Save...", "Save", this);
        menubar.add(menuFile);
        JMenuItem menuPen = new JMenu("Pen");
        menuPen.setActionCommand("Pen");
        menuPen.addActionListener(this);
        this.addMenuItem(menuPen, "Color...", "Color", this);
        JMenu menuWidth = new JMenu("Width");
        this.addMenuItem(menuWidth, "width1", "width1", this);
        this.addMenuItem(menuWidth, "width5", "width5", this);
        this.addMenuItem(menuWidth, "width10", "width10", this);
        this.addMenuItem(menuWidth, "width20", "width20", this);
        this.addMenuItem(menuWidth, "widthChooser", "widthChooser", this);
        menuPen.add(menuWidth);
        menubar.add(menuPen);
        JMenuItem menuTypes = new JMenu("PenTypes");
        this.addMenuItem(menuTypes, "Regular", "Regular", this);
        this.addMenuItem(menuTypes, "Oval", "Oval", this);
        this.addMenuItem(menuTypes, "Rect", "Rect", this);
        // this.addMenuItem(menuTypes, "Text", "Text", this);
        menuPen.add(menuTypes);
        menuTypes.addActionListener(this);
        // this.setJMenuBar(menubar);
        // this.setVisible(true);
        JMenu menuEraser = new JMenu("Eraser");
        this.addMenuItem(menuEraser, "Ewidth1", "Ewidth1", this);
        this.addMenuItem(menuEraser, "Ewidth5", "Ewidth5", this);
        this.addMenuItem(menuEraser, "Ewidth10", "Ewidth10", this);
        this.addMenuItem(menuEraser, "Ewidth20", "Ewidth20", this);
        this.addMenuItem(menuEraser, "EraseSlider", "EraseSlider", this);
        this.addMenuItem(menuEraser, "Clear All", "Clear All", this);
        menubar.add(menuEraser);
        this.setJMenuBar(menubar);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        Canvas frame = new Canvas();
        frame.init();
        frame.initMenu();
    }

    public void mouseDragged(MouseEvent arg0) {
        if (penNumber == regularPen) {
            panel.drawLine(mousex, mousey, arg0.getX(), arg0.getY());
        }
        mousex = arg0.getX();
        mousey = arg0.getY();
    }

    public void mouseMoved(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(penNumber);
        System.out.println("mouse clicked");
        if (panel.currentColor == Color.white)
            panel.setPenColor(Color.black);
        if (penNumber == rectPen) {
            panel.drawRect(mousex, mousey, width / 4, height / 4);
        }
        if (penNumber == ovalPen) {
            panel.drawOval(mousex, mousey, width / 4, height / 4);
        }

    }

    public void mousePressed(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();

    }

    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent arg0) {
        String command = arg0.getActionCommand();

        if (command.equals("width1")) {
            panel.setPenWidth(1);
            if (panel.currentColor.equals(Color.white))
                panel.setPenColor(Color.black);
        } else if (command.equals("width5")) {
            panel.setPenWidth(5);
            if (panel.currentColor.equals(Color.white))
                panel.setPenColor(Color.black);
        } else if (command.equals("width10")) {
            panel.setPenWidth(10);
            if (panel.currentColor.equals(Color.white))
                panel.setPenColor(Color.black);
        } else if (command.equals("width20")) {
            panel.setPenWidth(20);
            if (panel.currentColor.equals(Color.white))
                panel.setPenColor(Color.black);
        } else if (command.equals("widthChooser")) {
            System.out.println("widthslider pushed");
            new SizeControl("Pen Size", panel);
        } else if (command.equals("Color")) {
            JColorChooser colorchooser = new JColorChooser();
            Color color = colorchooser.showDialog(this, "choose a color", Color.blue);
            panel.setPenColor(color);
        } else if (command.equals("Ewidth1")) {
            penNumber = regularPen;
            panel.setPenColor(Color.white);
            panel.setPenWidth(1);
        } else if (command.equals("Ewidth5")) {
            penNumber = regularPen;
            panel.setPenColor(Color.white);
            panel.setPenWidth(5);
        } else if (command.equals("Ewidth10")) {
            penNumber = regularPen;
            panel.setPenColor(Color.white);
            panel.setPenWidth(10);
        } else if (command.equals("Ewidth20")) {
            penNumber = regularPen;
            panel.setPenColor(Color.white);
            panel.setPenWidth(20);
        } else if (command.equals("EraseSlider")) {
            penNumber = regularPen;
            panel.setPenColor(Color.white);
            new SizeControl("Eraser Size", panel);
        } else if (command.equals("Open")) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                panel.openFile(fileChooser.getSelectedFile());
            }
        } else if (command.equals("Save")) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                panel.saveFile(fileChooser.getSelectedFile());
            }
        } else if (command.equals("Oval")) {
            penNumber = ovalPen;
            System.out.println("Oval pushed");
        } else if (command.equals("Rect")) {
            penNumber = rectPen;
            System.out.println("Rect pushed");
        } else if (command.equals("Regular")) {
            penNumber = regularPen;
            if (panel.currentColor == Color.white)
                panel.setPenColor(Color.black);
        } else if (command.equals("Clear All")) {
            System.out.println("clear pushed");
            panel.clearAll(width * 2, height * 2);
            panel.setPenColor(Color.black);
            penNumber = regularPen;
        }
    }

}
