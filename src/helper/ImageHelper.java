/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ADMIN
 */
public class ImageHelper {
    
    //Phương thức điểu chỉnh kích thước hình ảnh
    public static Image resize(Image originaImage, int targetWith, int targetHeight){
        Image resultingImage = originaImage.getScaledInstance(targetWith, targetHeight, Image.SCALE_SMOOTH);
        return resultingImage;
   }
    //Phương thức cho phep chuyển hình ảnh thành mảng các byte
    public static byte[] toByteArray(Image img, String type) throws IOException {
        BufferedImage bimage = new BufferedImage(img.getWidth(null),img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimage.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bimage, type, baos);
        byte[] imageInByte = baos.toByteArray();
        
        return imageInByte;
    }
    
    //Chuyển mảng trên byte về các đối tượng image và cho phép hiển thị trên giao diện
    public static Image createImageFromByteArray(byte[] data, String type) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);
        
        Image img = bImage2.getScaledInstance(bImage2.getWidth(), bImage2.getHeight(), Image.SCALE_SMOOTH);
        return img;
    }
}
