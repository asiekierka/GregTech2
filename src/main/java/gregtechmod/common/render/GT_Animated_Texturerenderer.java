/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.FMLTextureFX
 *  cpw.mods.fml.client.TextureFXManager
 *  net.minecraft.client.renderer.RenderEngine
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.render;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.client.TextureFXManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.renderer.RenderEngine;
import org.lwjgl.opengl.GL11;

public class GT_Animated_Texturerenderer
extends FMLTextureFX {
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
        this.targetTex = target;
        this.tileSize = size;
        this.tileImage = re.getTexture(target);
        this.mLength = aLength;
        this.tickRate = tickCount;
        this.ticks = tickCount;
        try {
            this.imgData = TextureFXManager.instance().loadImageFromTexturePack(FMLClientHandler.instance().getClient().renderEngine, target);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bindImage(RenderEngine renderengine) {
        GL11.glBindTexture((int)3553, (int)renderengine.getTexture(this.targetTex));
    }

    public void setup() {
        super.setup();
        int sWidth = this.imgData.getWidth();
        int sHeight = this.imgData.getHeight();
        int tWidth = this.tileSizeBase;
        int tHeight = this.tileSizeBase;
        int frames = (int)Math.floor(sHeight / sWidth);
        if (frames < 1) {
            throw new IllegalArgumentException(String.format("Attempted to create a TextureAnimation with no complete frames: %dx%d", sWidth, sHeight));
        }
        this.images = new byte[frames][];
        BufferedImage image = this.imgData;
        if (sWidth != tWidth) {
            BufferedImage b = new BufferedImage(tWidth, tHeight * frames, 6);
            Graphics2D g = b.createGraphics();
            g.drawImage(this.imgData, 0, 0, tWidth, tHeight * frames, 0, 0, sWidth, sHeight, null);
            g.dispose();
            image = b;
        }
        for (int frame = 0; frame < frames; ++frame) {
            int[] pixels = new int[this.tileSizeSquare];
            image.getRGB(0, tHeight * frame, tWidth, tHeight, pixels, 0, tWidth);
            this.images[frame] = new byte[this.tileSizeSquare << 2];
            for (int i = 0; i < pixels.length; ++i) {
                int i4 = i * 4;
                this.images[frame][i4 + 0] = (byte)(pixels[i] >> 16 & 0xFF);
                this.images[frame][i4 + 1] = (byte)(pixels[i] >> 8 & 0xFF);
                this.images[frame][i4 + 2] = (byte)(pixels[i] >> 0 & 0xFF);
                this.images[frame][i4 + 3] = (byte)(pixels[i] >> 24 & 0xFF);
            }
        }
    }

    public void func_783_a() {
        if (++this.ticks >= this.tickRate) {
            if (++this.index >= Math.min(this.mLength, this.images.length)) {
                this.index = 0;
            }
            this.imageData = this.images[this.index];
            this.ticks = 0;
        }
    }
}

