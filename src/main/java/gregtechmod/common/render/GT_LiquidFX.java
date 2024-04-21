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
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.render;

import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.texturefx.TextureFX;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GT_LiquidFX
extends FMLTextureFX {
    protected float[] red = new float[256];
    protected float[] green = new float[256];
    protected float[] blue = new float[256];
    protected float[] alpha = new float[256];
    private int tickCounter = 0;
    private int rmin;
    private int rmax;
    private int gmin;
    private int gmax;
    private int bmin;
    private int bmax;

    public GT_LiquidFX(int aIndex, int aRmin, int aRmax, int aGmin, int aGmax, int aBmin, int aBmax) {
        super(aIndex);
        this.setup();
        this.rmin = Math.max(0, aRmin);
        this.rmax = Math.min(255, aRmax);
        this.gmin = Math.max(0, aGmin);
        this.gmax = Math.min(255, aGmax);
        this.bmin = Math.max(0, aBmin);
        this.bmax = Math.min(255, aBmax);
        TextureFXManager.instance().addAnimation((TextureFX)this);
    }

    public void setup() {
        super.setup();
        this.red = new float[this.tileSizeSquare];
        this.green = new float[this.tileSizeSquare];
        this.blue = new float[this.tileSizeSquare];
        this.alpha = new float[this.tileSizeSquare];
        this.tickCounter = 0;
    }

    public void bindImage(RenderEngine par1RenderEngine) {
        GL11.glBindTexture((int)3553, (int)par1RenderEngine.getTexture("/gregtechmod/textures/metaitems/liquids.png"));
    }

    public void onTick() {
        int var5;
        int var4;
        float var3;
        int var2;
        int var1;
        for (var1 = 0; var1 < this.tileSizeBase; ++var1) {
            for (var2 = 0; var2 < this.tileSizeBase; ++var2) {
                var3 = 0.0f;
                for (int var6 = var1 - 1; var6 <= var1 + 1; ++var6) {
                    var4 = var6 & this.tileSizeMask;
                    var5 = var2 & this.tileSizeMask;
                    var3 += this.red[var4 + var5 * this.tileSizeBase];
                }
                this.green[var1 + var2 * this.tileSizeBase] = var3 / 3.3f + this.blue[var1 + var2 * this.tileSizeBase] * 0.8f;
            }
        }
        for (var1 = 0; var1 < this.tileSizeBase; ++var1) {
            for (var2 = 0; var2 < this.tileSizeBase; ++var2) {
                int n = var1 + var2 * this.tileSizeBase;
                this.blue[n] = this.blue[n] + this.alpha[var1 + var2 * this.tileSizeBase] * 0.05f;
                if (this.blue[var1 + var2 * this.tileSizeBase] < 0.0f) {
                    this.blue[var1 + var2 * this.tileSizeBase] = 0.0f;
                }
                int n2 = var1 + var2 * this.tileSizeBase;
                this.alpha[n2] = this.alpha[n2] - 0.1f;
                if (!(Math.random() < 0.05)) continue;
                this.alpha[var1 + var2 * this.tileSizeBase] = 0.5f;
            }
        }
        float[] var13 = this.green;
        this.green = this.red;
        this.red = var13;
        for (var2 = 0; var2 < this.tileSizeSquare; ++var2) {
            var3 = this.red[var2];
            if (var3 > 1.0f) {
                var3 = 1.0f;
            }
            if (var3 < 0.0f) {
                var3 = 0.0f;
            }
            float var7 = var3 * var3;
            var4 = (int)((float)this.rmin + var7 * (float)(this.rmax - this.rmin));
            var5 = (int)((float)this.gmin + var7 * (float)(this.gmax - this.gmin));
            int var8 = (int)((float)this.bmin + var7 * (float)(this.bmax - this.bmin));
            int var9 = (int)(146.0f + var7 * 50.0f);
            if (this.anaglyphEnabled) {
                int var10 = (var4 * 30 + var5 * 59 + var8 * 11) / 100;
                int var11 = (var4 * 30 + var5 * 70) / 100;
                int var12 = (var4 * 30 + var8 * 70) / 100;
                var4 = var10;
                var5 = var11;
                var8 = var12;
            }
            this.imageData[var2 * 4 + 0] = (byte)var4;
            this.imageData[var2 * 4 + 1] = (byte)var5;
            this.imageData[var2 * 4 + 2] = (byte)var8;
            this.imageData[var2 * 4 + 3] = (byte)var9;
        }
    }
}

