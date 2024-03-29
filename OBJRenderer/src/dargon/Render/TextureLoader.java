package dargon.Render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class TextureLoader {
	
	private static HashMap<String, int[]> loadedTextures = new HashMap<String, int[]>();

	public static final int[] load(String filename, int textureMode) 
	{
		try 
		{
			if (loadedTextures.containsKey(filename)) 
			{
	
				return loadedTextures.get(filename);
			}
			IntBuffer buffer = (IntBuffer) BufferUtils.createIntBuffer(1).clear();
			glGenTextures(buffer);

			int id = buffer.get(0);
			File f = new File(filename);
			BufferedImage img = ImageIO.read(f);
			int width = img.getWidth();
			int height = img.getHeight();
			loadedTextures.put(filename, new int[]{id,width,height});
			glBindTexture(GL_TEXTURE_2D, id);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, textureMode);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, textureMode);

			
			ByteBuffer pixelsByteBuffer = BufferUtils.createByteBuffer(width * height << 2);
			int[] intPixels = new int[width * height];
			byte[] bytePixels = new byte[width * height << 2];

			img.getRGB(0, 0, width, height, intPixels, 0, width);
			for (int i = 0; i < intPixels.length; i++) 
			{
				int a = intPixels[i] >>> 24;
				int r = intPixels[i] >> 16 & 0xff;
				int g = intPixels[i] >> 8 & 0xff;
				int b = intPixels[i] & 0xff;
				bytePixels[(i << 2) + 0] = (byte) r;
				bytePixels[(i << 2) + 1] = (byte) g;
				bytePixels[(i << 2) + 2] = (byte) b;
				bytePixels[(i << 2) + 3] = (byte) a;
			}

			pixelsByteBuffer.put(bytePixels);
			pixelsByteBuffer.position(0).limit(bytePixels.length);
			gluBuild2DMipmaps(GL_TEXTURE_2D, GL_RGBA, width, height, GL_RGBA, GL_UNSIGNED_BYTE, pixelsByteBuffer);
			return new int[]{id,width,height};
			
		} 
		catch (Exception e) {e.printStackTrace();}
		
		throw new RuntimeException("No Found Imgage "+filename);
	}
}