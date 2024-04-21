package gregtechmod.common.render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import net.minecraft.client.renderer.RenderEngine;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.client.TextureFXManager;

/**
 * Old unused Class as It ended in extreme Lag
 */
public class GT_Animated_Texturerenderer extends FMLTextureFX {
    private final int tickRate;
    private byte[][] images;
    private int index = 0;
    private int ticks = 0;
    private int mLength = 1;
    
    private String targetTex = null;
    private BufferedImage imgData = null;
	
	public GT_Animated_Texturerenderer(int icon, int size, String target, int tickCount, int aLength) {
        super(icon);
        RenderEngine re = FMLClientHandler.instance().getClient().renderEngine;
        
        targetTex = target;
        tileSize = size;
        tileImage = re.getTexture(target);
        mLength = aLength;
        tickRate = tickCount;
        ticks = tickCount;
        try {
			imgData = TextureFXManager.instance().loadImageFromTexturePack(FMLClientHandler.instance().getClient().renderEngine, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void bindImage(RenderEngine renderengine) {
    	//Binds texture with GL11 to use specific icon index.
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, renderengine.getTexture(targetTex));
    }
	
    @Override
    public void setup() {
        super.setup();
        
        int sWidth  = imgData.getWidth();
        int sHeight = imgData.getHeight();
        int tWidth  = tileSizeBase;
        int tHeight = tileSizeBase;
        
        
        int frames = (int)Math.floor((double)(sHeight / sWidth));

        if (frames < 1)
        {
            throw new IllegalArgumentException(String.format("Attempted to create a TextureAnimation with no complete frames: %dx%d", sWidth, sHeight));
        }
        else
        {
            images = new byte[frames][];
            BufferedImage image = imgData;
            
            if (sWidth != tWidth)
            {
                BufferedImage b = new BufferedImage(tWidth, tHeight * frames, 6);
                Graphics2D g = b.createGraphics();
                g.drawImage(imgData, 0, 0, tWidth, tHeight * frames, 0, 0, sWidth, sHeight, (ImageObserver)null);
                g.dispose();
                image = b;
            }

            for (int frame = 0; frame < frames; frame++)
            {
                int[] pixels = new int[tileSizeSquare];
                image.getRGB(0, tHeight * frame, tWidth, tHeight, pixels, 0, tWidth);
                images[frame] = new byte[tileSizeSquare << 2];

                for (int i = 0; i < pixels.length; i++)
                {
                    int i4 = i * 4;
                    images[frame][i4 + 0] = (byte)(pixels[i] >> 16 & 255);
                    images[frame][i4 + 1] = (byte)(pixels[i] >> 8  & 255);
                    images[frame][i4 + 2] = (byte)(pixels[i] >> 0  & 255);
                    images[frame][i4 + 3] = (byte)(pixels[i] >> 24 & 255);
                }
            }
        }
    }

    public void func_783_a() {
        if (++ticks >= tickRate) {
            if (++index >= Math.min(mLength, images.length)) {
                index = 0;
            }

            imageData = images[index];
            ticks = 0;
        }
    }
}