package gregtechmod.common.render;

import net.minecraft.client.renderer.RenderEngine;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GT_LiquidFX extends FMLTextureFX
{
    /** red RGB value for water texture */
    protected float[] red = new float[256];

    /** green RGB value for water texture */
    protected float[] green = new float[256];

    /** blue RGB value for water texture */
    protected float[] blue = new float[256];

    /** alpha RGB value for water texture */
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
        setup();
        rmin = Math.max(  0, aRmin);
        rmax = Math.min(255, aRmax);
        gmin = Math.max(  0, aGmin);
        gmax = Math.min(255, aGmax);
        bmin = Math.max(  0, aBmin);
        bmax = Math.min(255, aBmax);
        TextureFXManager.instance().addAnimation(this);
    }
    
    @Override
    public void setup() {
        super.setup();
        
        red = new float[tileSizeSquare];
        green = new float[tileSizeSquare];
        blue = new float[tileSizeSquare];
        alpha = new float[tileSizeSquare];
        tickCounter = 0;
    }
    
    @Override
    public void bindImage(RenderEngine par1RenderEngine) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, par1RenderEngine.getTexture("/gregtechmod/textures/metaitems/liquids.png"));
    }

    @Override
    public void onTick() {
        int var1;
        int var2;
        float var3;
        int var4;
        int var5;

        for (var1 = 0; var1 < this.tileSizeBase; ++var1)
        {
            for (var2 = 0; var2 < this.tileSizeBase; ++var2)
            {
                var3 = 0.0F;

                for (int var6 = var1 - 1; var6 <= var1 + 1; ++var6)
                {
                    var4 = var6 & this.tileSizeMask;
                    var5 = var2 & this.tileSizeMask;
                    var3 += this.red[var4 + var5 * this.tileSizeBase];
                }

                this.green[var1 + var2 * this.tileSizeBase] = var3 / 3.3F + this.blue[var1 + var2 * this.tileSizeBase] * 0.8F;
            }
        }

        for (var1 = 0; var1 < this.tileSizeBase; ++var1)
        {
            for (var2 = 0; var2 < this.tileSizeBase; ++var2)
            {
                this.blue[var1 + var2 * this.tileSizeBase] += this.alpha[var1 + var2 * this.tileSizeBase] * 0.05F;

                if (this.blue[var1 + var2 * this.tileSizeBase] < 0.0F)
                {
                    this.blue[var1 + var2 * this.tileSizeBase] = 0.0F;
                }

                this.alpha[var1 + var2 * this.tileSizeBase] -= 0.1F;

                if (Math.random() < 0.05D)
                {
                    this.alpha[var1 + var2 * this.tileSizeBase] = 0.5F;
                }
            }
        }

        float[] var13 = this.green;
        this.green = this.red;
        this.red = var13;

        for (var2 = 0; var2 < this.tileSizeSquare; ++var2)
        {
            var3 = this.red[var2];

            if (var3 > 1.0F)
            {
                var3 = 1.0F;
            }

            if (var3 < 0.0F)
            {
                var3 = 0.0F;
            }

            float var7 = var3 * var3;
            var4 = (int)((float)this.rmin + var7 * (float)(this.rmax - this.rmin));
            var5 = (int)((float)this.gmin + var7 * (float)(this.gmax - this.gmin));
            int var8 = (int)((float)this.bmin + var7 * (float)(this.bmax - this.bmin));
            int var9 = (int)(146.0F + var7 * 50.0F);

            if (this.anaglyphEnabled)
            {
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
