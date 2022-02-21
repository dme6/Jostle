package io.github.dme6.jostle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Jostle
{
    public static void main( String[] args ) throws IOException {
        File[] in = new File("./frames_in").listFiles();

        int imgCount = 0;

        for(File f : in) {
            BufferedImage inImg = ImageIO.read(f);
            BufferedImage[] outImgs = new BufferedImage[4];
            for(int i = 0; i < outImgs.length; i++) {
                outImgs[i] = new BufferedImage(inImg.getWidth() / 2, inImg.getHeight() / 2, BufferedImage.TYPE_INT_RGB);
            }

            int wNew = 0;
            int hNew = 0;

            for(int h = 1; h < inImg.getHeight(); h += 2) {
                for(int w = 0; w < inImg.getWidth(); w += 2) {
                    outImgs[1].setRGB(wNew, hNew, inImg.getRGB(w, h));
                    wNew++;
                }
                hNew++;
                wNew = 0;
            }
            wNew = 0; hNew = 0;
            for(int h = 1; h < inImg.getHeight(); h += 2) {
                for(int w = 1; w < inImg.getWidth(); w += 2) {
                    outImgs[3].setRGB(wNew, hNew, inImg.getRGB(w, h));
                    wNew++;
                }
                hNew++;
                wNew = 0;
            }
            wNew = 0; hNew = 0;
            for(int h = 0; h < inImg.getHeight(); h += 2) {
                for(int w = 1; w < inImg.getWidth(); w += 2) {
                    outImgs[2].setRGB(wNew, hNew, inImg.getRGB(w, h));
                    wNew++;
                }
                hNew++;
                wNew = 0;
            }
            wNew = 0; hNew = 0;
            for(int h = 0; h < inImg.getHeight(); h += 2) {
                for(int w = 0; w < inImg.getWidth(); w += 2) {
                    // System.out.println("W - " + w + " | H - " + h);
                    outImgs[0].setRGB(wNew, hNew, inImg.getRGB(w, h));
                    wNew++;
                }
                hNew++;
                wNew = 0;
            }

            imgCount += 4;

            for(int i = 0; i < outImgs.length; i++) {
                ImageIO.write(outImgs[i], "JPG", new File("./frames_out/" + String.valueOf(imgCount - 3 + i) + ".jpg"));
            }

        }
    }
}
