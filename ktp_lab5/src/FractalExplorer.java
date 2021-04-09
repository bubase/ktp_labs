import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer 
{
    public static void main(String[] args)
    {
        Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 800, 800);
        FractalExplorer fr = new FractalExplorer(rect);
        fr.start();
    }

    /** Size of display */
    private int displaySize;

    /** Actual display for Mandelbrot */
    private JImageDisplay imageDisplay;

    /** Abstract fractal generator */
    private FractalGenerator fractalGenerator;

    /** Range of fractal */
    private Rectangle2D.Double range;

    private class AppListener implements ActionListener
    {
        private String action;

        AppListener(String action) { this.action = action; }

        public void actionPerformed(ActionEvent e)
        {
            if(action == "choose")
            {
                fractalGenerator = (FractalGenerator)((JComboBox)e.getSource()).getSelectedItem();
                drawFractal();
            }
            else if(action == "reset")
            {
                imageDisplay.clearImage();
                fractalGenerator.getInitialRange(range);
                drawFractal();
            }
            else if(action == "save")
            {
                File choosedFile = null;

                JFileChooser fileChooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);

                Component parentComponent = ((Component)e.getSource()).getParent().getParent(); // dreadful, there's MUST be something better
                int result = fileChooser.showSaveDialog(parentComponent); 
                if(result != JFileChooser.APPROVE_OPTION)
                {
                    return;
                }
                else
                {
                    choosedFile = fileChooser.getSelectedFile();
                    try 
                    {
                        ImageIO.write(imageDisplay.image, "png", choosedFile);
                    } catch (Exception exception) 
                    {
                        JOptionPane.showMessageDialog(parentComponent, "Exception occured while saving image: " + exception.getMessage(), "Cannot save image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    /** Class for handle mouse actions */
    private class ClickHandler implements MouseListener
    {
        public void mousePressed(MouseEvent e)
        {
            /** Get the click position and transform them into fractal position */
            Point click = e.getPoint();
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, click.x);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, click.y);

            /** When click on left button - zoom in, right - zoom out */
            if(e.getButton() == e.BUTTON1)
            {
                fractalGenerator.recenterAndZoomRange(range, xCoord,  yCoord, 0.5);
                System.out.println(String.format("Center spot: x=%f, y=%f", xCoord, yCoord));
                drawFractal();
            }
            else if(e.getButton() == e.BUTTON3)
            {
                fractalGenerator.recenterAndZoomRange(range, xCoord,  yCoord, 2);
                System.out.println(String.format("Center spot: x=%f, y=%f", xCoord, yCoord));
                drawFractal();
            }
        }

        /** These functions not need in implementation */
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    }

    /** Default constructor */
    FractalExplorer(Rectangle2D.Double rect)
    {
        this.range = rect;
        displaySize = (int)rect.width;

        imageDisplay = new JImageDisplay(displaySize, displaySize);
        fractalGenerator = new Mandelbrot();

        fractalGenerator.getInitialRange(range);
    }

    /** App launch function */
    public void start()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                drawFractal();
            }
        });
    }

    /** Gui construction function */
    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Mandelbrot Set");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());
        JLabel label = new JLabel("Fractal: ");
        
        JComboBox<FractalGenerator> fractals = new JComboBox<>();
        fractals.addItem(new Mandelbrot());
        fractals.addItem(new BurningShip());
        fractals.addItem(new Tricorn());

        fractals.addActionListener(new AppListener("choose"));

        topPanel.add(label);
        topPanel.add(fractals);

        JPanel botPanel = new JPanel();
        botPanel.setLayout(new GridLayout());

        JButton saveImage = new JButton("Save image");
        saveImage.addActionListener(new AppListener("save"));
        JButton resetButton = new JButton("Reset display");
        resetButton.addActionListener(new AppListener("reset"));

        botPanel.add(saveImage);
        botPanel.add(resetButton);

        contentPane.setLayout(new BorderLayout());

        imageDisplay.addMouseListener(new ClickHandler());

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(imageDisplay, BorderLayout.CENTER);
        contentPane.add(botPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /** Fractal draw function */
    public void drawFractal()
    {
        for(int y = 0; y < displaySize; ++y)
        {
            for(int x = 0; x < displaySize; ++x)
            {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);

                int iterations = fractalGenerator.numIterations(xCoord, yCoord);

                if(iterations == -1)
                {
                    imageDisplay.drawPixel(x, y, 0);
                }
                else
                {
                    float hue = 7.0f + (float)iterations/200.f;
                    int rgbColor = Color.HSBtoRGB(hue, 1.f, 1.f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }
}
