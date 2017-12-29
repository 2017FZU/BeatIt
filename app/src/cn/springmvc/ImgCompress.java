package cn.springmvc;

import java.io.*;  
import java.util.Date;  
import java.awt.*;  
import java.awt.image.*;  
import javax.imageio.ImageIO;  
import com.sun.image.codec.jpeg.*;  
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/** 
 * ͼƬѹ������ 
 * @author ����ǿ 
 */  
public class ImgCompress {  

	    String openUrl; // ԭʼͼƬ��·��
	    String saveUrl; // ��ͼ����·��
	    String saveName; // ��ͼ����
	    String suffix; // ��ͼ���� ֻ֧��gif,jpg,png

	    public ImgCompress(String openUrl, String saveUrl, String saveName,String suffix) {
	        this.openUrl = openUrl;
	        this.saveName = saveName;
	        this.saveUrl = saveUrl;
	        this.suffix = suffix;
	    }

	    public void zoom(int width, int height) throws Exception {
	        double sx = 0.0;
	        double sy = 0.0;

	        File file = new File(openUrl);
	        if (!file.isFile()) {
	            throw new Exception("ImageDeal>>>" + file + " ����һ��ͼƬ�ļ�!");
	        }
	        BufferedImage bi = ImageIO.read(file); // ��ȡ��ͼƬ
	        // ����x��y�����ű���--����ȱ������ţ��ڵ���֮ǰȷ������width��height�ǵȱ����仯��
	        sx = (double) width / bi.getWidth();
	        sy = (double) height / bi.getHeight();

	        AffineTransformOp op = new AffineTransformOp(
	                AffineTransform.getScaleInstance(sx, sy), null);
	        File sf = new File(saveUrl, saveName + "." + suffix);
	        Image zoomImage = op.filter(bi, null);
	        try {
	            ImageIO.write((BufferedImage) zoomImage, suffix, sf); // ����ͼƬ
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    public void spin(int degree) throws Exception {
	        int swidth = 0; // ��ת��Ŀ��
	        int sheight = 0; // ��ת��ĸ߶�
	        int x; // ԭ�������
	        int y; // ԭ��������

	        File file = new File(openUrl);
	        if (!file.isFile()) {
	            throw new Exception("ImageDeal>>>" + file + " ����һ��ͼƬ�ļ�!");
	        }
	        BufferedImage bi = ImageIO.read(file); // ��ȡ��ͼƬ
	        // ����Ƕ�--ȷ����ת����
	        degree = degree % 360;
	        if (degree < 0)
	            degree = 360 + degree;// ���Ƕ�ת����0-360��֮��
	        double theta = Math.toRadians(degree);// ���Ƕ�תΪ����

	        // ȷ����ת��Ŀ�͸�
	        if (degree == 180 || degree == 0 || degree == 360) {
	            swidth = bi.getWidth();
	            sheight = bi.getHeight();
	        } else if (degree == 90 || degree == 270) {
	            sheight = bi.getWidth();
	            swidth = bi.getHeight();
	        } else {
	            swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
	                    + bi.getHeight() * bi.getHeight()));
	            sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth()
	                    + bi.getHeight() * bi.getHeight()));
	        }

	        x = (swidth / 2) - (bi.getWidth() / 2);// ȷ��ԭ������
	        y = (sheight / 2) - (bi.getHeight() / 2);

	        BufferedImage spinImage = new BufferedImage(swidth, sheight,
	                bi.getType());
	        // ����ͼƬ������ɫ
	        Graphics2D gs = (Graphics2D) spinImage.getGraphics();
	        gs.setColor(Color.white);
	        gs.fillRect(0, 0, swidth, sheight);// �Ը�����ɫ������ת��ͼƬ�ı���

	        AffineTransform at = new AffineTransform();
	        at.rotate(theta, swidth / 2, sheight / 2);// ��תͼ��
	        at.translate(x, y);
	        AffineTransformOp op = new AffineTransformOp(at,
	                AffineTransformOp.TYPE_BICUBIC);
	        spinImage = op.filter(bi, spinImage);
	        File sf = new File(saveUrl, saveName + "." + suffix);
	        ImageIO.write(spinImage, suffix, sf); // ����ͼƬ

	    }
//	    public static void main(String[] args) throws Exception {
//	    	ImgCompress imageDeal = new ImgCompress("e://1513499675030_20171213_185013.jpg", "e://", "c2", "jpg");
//	    	imageDeal.zoom(900, 600); 
//	    	//imageDeal.spin(90); 
//	    }

	}