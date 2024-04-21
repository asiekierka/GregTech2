/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLTextureFX
 *  cpw.mods.fml.client.TextureFXManager
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.RenderEngine
 *  net.minecraft.client.renderer.texturefx.TextureFX
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.render;

import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.texturefx.TextureFX;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GT_GasFX
extends FMLTextureFX {
    protected float[] red = new float[256];
    protected float[] green = new float[256];
    protected float[] blue = new float[256];
    protected float[] alpha = new float[256];

    public GT_GasFX(int aIndex) {
        super(aIndex);
        this.setup();
        TextureFXManager.instance().addAnimation((TextureFX)this);
    }

    public void bindImage(RenderEngine par1RenderEngine) {
        GL11.glBindTexture((int)3553, (int)par1RenderEngine.getTexture("/gregtechmod/textures/metaitems/gasses.png"));
    }

    public void setup() {
        super.setup();
        this.red = new float[this.tileSizeSquare];
        this.green = new float[this.tileSizeSquare];
        this.blue = new float[this.tileSizeSquare];
        this.alpha = new float[this.tileSizeSquare];
    }

    public void onTick() {
        int var9;
        int var8;
        int var7;
        int var6;
        int var5;
        float var3;
        int var2;
        for (int var1 = 0; var1 < this.tileSizeBase; ++var1) {
            for (var2 = 0; var2 < this.tileSizeBase; ++var2) {
                var3 = 0.0f;
                int var4 = (int)(MathHelper.sin((float)((float)var2 * (float)Math.PI * 2.0f / 16.0f)) * 1.2f);
                var5 = (int)(MathHelper.sin((float)((float)var1 * (float)Math.PI * 2.0f / 16.0f)) * 1.2f);
                for (var6 = var1 - 1; var6 <= var1 + 1; ++var6) {
                    for (var7 = var2 - 1; var7 <= var2 + 1; ++var7) {
                        var8 = var6 + var4 & this.tileSizeMask;
                        var9 = var7 + var5 & this.tileSizeMask;
                        var3 += this.red[var8 + var9 * this.tileSizeBase];
                    }
                }
                this.green[var1 + var2 * this.tileSizeBase] = var3 / 10.0f + (this.blue[(var1 + 0 & this.tileSizeMask) + (var2 + 0 & this.tileSizeMask) * this.tileSizeBase] + this.blue[(var1 + 1 & this.tileSizeMask) + (var2 + 0 & this.tileSizeMask) * this.tileSizeBase] + this.blue[(var1 + 1 & this.tileSizeMask) + (var2 + 1 & this.tileSizeMask) * this.tileSizeBase] + this.blue[(var1 + 0 & this.tileSizeMask) + (var2 + 1 & this.tileSizeMask) * this.tileSizeBase]) / 4.0f * 0.8f;
                int n = var1 + var2 * this.tileSizeBase;
                this.blue[n] = this.blue[n] + this.alpha[var1 + var2 * this.tileSizeBase] * 0.01f;
                if (this.blue[var1 + var2 * this.tileSizeBase] < 0.0f) {
                    this.blue[var1 + var2 * this.tileSizeBase] = 0.0f;
                }
                int n2 = var1 + var2 * this.tileSizeBase;
                this.alpha[n2] = this.alpha[n2] - 0.06f;
                if (!(Math.random() < 0.005)) continue;
                this.alpha[var1 + var2 * this.tileSizeBase] = 1.5f;
            }
        }
        float[] var11 = this.green;
        this.green = this.red;
        this.red = var11;
        for (var2 = 0; var2 < this.tileSizeSquare; ++var2) {
            var3 = this.red[var2] * 2.0f;
            if (var3 > 1.0f) {
                var3 = 1.0f;
            }
            if (var3 < 0.0f) {
                var3 = 0.0f;
            }
            var5 = (int)(var3 * 100.0f + 155.0f);
            var6 = (int)(var3 * var3 * 255.0f);
            var7 = (int)(var3 * var3 * var3 * var3 * 128.0f);
            if (this.anaglyphEnabled) {
                var8 = (var5 * 30 + var6 * 59 + var7 * 11) / 100;
                var9 = (var5 * 30 + var6 * 70) / 100;
                int var10 = (var5 * 30 + var7 * 70) / 100;
                var5 = var8;
                var6 = var9;
                var7 = var10;
            }
            this.imageData[var2 * 4 + 0] = (byte)var5;
            this.imageData[var2 * 4 + 1] = (byte)var6;
            this.imageData[var2 * 4 + 2] = (byte)var7;
            this.imageData[var2 * 4 + 3] = -1;
        }
    }
}

