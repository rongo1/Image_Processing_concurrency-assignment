import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;


public class Main {

    static BufferedImage img;
    static ImageIcon icon;
    static JFrame frame;
    static JLabel label;
    static int x_start = 0;
    static int y_start = 0;
    static int x_end = 0;
    static int y_end = 0;
            
    

	public static void change_to_colorful(int x, int y, int n, int x_end, int y_end) {
		icon = new ImageIcon(img);
        label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        for (int i = x; i < x_end; i = i + 1){ 
		    for (int j = y; j < y_end; j = j + 1) {   
                int pixel = img.getRGB(i, j);
				Color c = new Color(pixel, true);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
                int avg_color = (red + green + blue)/3;
                if (avg_color < 85){
                    red /=3;
                }
                else if(avg_color > 85 & avg_color < 170){
                    green/=3;
                }
                else{
                    blue /=3;
                }
				Color avg = new Color(red, green, blue);				
				img.setRGB(i, j, avg.getRGB());
            }
        frame.repaint();
        frame.revalidate();
        icon.setImage(img);
		icon.setImage(img);       
	    }
        frame.setVisible(true); 
			
   }
   public static void multi_thread(int cores, int sq_size){   
        int num_boxes_row = (img.getHeight()+sq_size - 1)/sq_size;
        int num_boxes_col = (img.getWidth()+sq_size - 1)/sq_size;
        int num_boxes = num_boxes_row*num_boxes_col;
        int num_thread_cycles = num_boxes/cores;
        boolean imageXend = false;
        y_end = sq_size;
        for (int i = 0; i <= num_thread_cycles; i++) {    
            ArrayList<Thread> threads = new ArrayList<Thread>();            
            for (int j = 0; j < cores; j++) {                    
                if(x_start + sq_size < img.getWidth()){
                    x_end = x_start + sq_size;
                }
                else{
                    x_end = img.getWidth();
                    imageXend = true;
                }
                
                if(y_start + sq_size > img.getHeight()){
                    y_end = img.getHeight();
                }
                
                int x_start1 = x_start;
                int y_start1 = y_start;
                int x_end1 = x_end;
                int y_end1 = y_end;
                threads.add(new Thread(() -> change_to_colorful(x_start1, y_start1, sq_size, x_end1, y_end1)));
                threads.get(j).start();    
                if(imageXend){                   
                    x_start = 0;
                    x_end = 0;
                    y_start = y_start + sq_size;
                    y_end = y_start + sq_size;
                    imageXend = false;                        
                } 
                else{
                    x_start = x_end;
                }           

            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

   }
   public static void single_thread(int cores, int sq_size){   
    int num_boxes_row = (img.getHeight()+sq_size - 1)/sq_size;
    int num_boxes_col = (img.getWidth()+sq_size - 1)/sq_size;
    int num_boxes = num_boxes_row*num_boxes_col;
    int num_thread_cycles = num_boxes/cores;
    boolean imageXend = false;
    y_end = sq_size;

    for (int i = 0; i <= num_thread_cycles; i++) {  
                    
        for (int j = 0; j < cores; j++) {                    
            if(x_start + sq_size < img.getWidth()){
                x_end = x_start + sq_size;
            }
            else{
                x_end = img.getWidth();
                imageXend = true;
            }
            
            if(y_start + sq_size > img.getHeight()){
                y_end = img.getHeight();
            }
            
            int x_start1 = x_start;
            int y_start1 = y_start;
            int x_end1 = x_end;
            int y_end1 = y_end;
            Thread thread = new Thread(() -> change_to_colorful(x_start1, y_start1, sq_size, x_end1, y_end1));
            thread.start();   
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            if(imageXend){                   
                x_start = 0;
                x_end = 0;
                y_start = y_start + sq_size;
                y_end = y_start + sq_size;
                imageXend = false;                        
            } 
            else{
                x_start = x_end;
            }           

        }
        
    }

}





    public static void main(String[] args) throws IOException   {
        int cores = Runtime.getRuntime().availableProcessors(); 
        frame = new JFrame();    
        String fileName = args[0];
        int sq_size = Integer.parseInt(args[1]);
        String processingMode = args[2];

        
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] dots = fileName.trim().split("\\.");
        
        if (dots.length!=2){
            throw new IOException("Inappropriate number of dots");
        }
        String ext = dots[1];

        
        if(sq_size > img.getWidth() || sq_size > img.getHeight() || sq_size == 0) {
            throw new IOException("Inappropriate size of square");
        }

        
        if (processingMode.equals("S")) {
            single_thread(cores, sq_size);
        }
  
        else if (processingMode.equals("M")) {
            multi_thread(cores, sq_size);
            
        }
        else{
            throw new IOException("Inappropriate size of processing mode");
        }
            
        
		
    
		
		try {            
			File resultImage = new File("result." + ext);
			ImageIO.write(img, ext, resultImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.exit(0);
	

 }
}
