package com.poly.toba.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class MakeThumbnail {
   private static final int IMG_WIDTH = 1920;
   private static final int IMG_HEIGHT = 1080;
   private static final int IMG_WIDTH2 = 828;
   private static final int IMG_HEIGHT2 = 150;
   public static String makeThumbnail(String path,String newFileName, String thumbFileName ,String extension,String year,String month,String day,String hour,String noticeNo) throws Exception {

      BufferedImage originalImage  = ImageIO.read(new File(newFileName));
      int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
      BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
      File thumbFolder = new File(path+"/"+year+"/"+month+"/"+day+"/"+hour+"/"+noticeNo+"/thumbs/");
      if (!thumbFolder.isDirectory()) {
            thumbFolder.mkdirs();
         }
      String thumbName = "THUMB_" + thumbFileName+"."+extension; 
      File thumbFile = new File(path+"/"+year+"/"+month+"/"+day+"/"+hour+"/"+noticeNo+"/thumbs/"+thumbName); 
      ImageIO.write(resizeImageHintJpg, "jpg", thumbFile); 
      return path+year+"/"+month+"/"+day+"/"+hour+"/"+noticeNo+"/thumbs/"+thumbName;
      
   }
   private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
      
      BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
      Graphics2D g = resizedImage.createGraphics();
      g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
      g.dispose();   
      g.setComposite(AlphaComposite.Src);

      g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g.setRenderingHint(RenderingHints.KEY_RENDERING,
      RenderingHints.VALUE_RENDER_QUALITY);
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
      
      return resizedImage;
   }
   public static String makeThumbnailAd(String path,String newFileName, String thumbFileName ,String extension,String year,String month,String day,String hour,String adNo) throws Exception {

      BufferedImage originalImage  = ImageIO.read(new File(newFileName));
      int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
      BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
      File thumbFolder = new File(path+"/"+year+"/"+month+"/"+day+"/"+hour+"/"+adNo+"/thumbs/");
      if (!thumbFolder.isDirectory()) {
            thumbFolder.mkdirs();
         }
      String thumbName = "THUMB_" + thumbFileName+"."+extension; 
      File thumbFile = new File(path+"/"+year+"/"+month+"/"+day+"/"+hour+"/"+adNo+"/thumbs/"+thumbName); 
      ImageIO.write(resizeImageHintJpg, "jpg", thumbFile); 
      return path+year+"/"+month+"/"+day+"/"+hour+"/"+adNo+"/thumbs/"+thumbName;
      
   }
   private static BufferedImage resizeImageWithHintAd(BufferedImage originalImage, int type){
      
      BufferedImage resizedImage = new BufferedImage(IMG_WIDTH2, IMG_HEIGHT2, type);
      Graphics2D g = resizedImage.createGraphics();
      g.drawImage(originalImage, 0, 0, IMG_WIDTH2, IMG_HEIGHT2, null);
      g.dispose();   
      g.setComposite(AlphaComposite.Src);

      g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g.setRenderingHint(RenderingHints.KEY_RENDERING,
      RenderingHints.VALUE_RENDER_QUALITY);
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
      
      return resizedImage;
       }   
}