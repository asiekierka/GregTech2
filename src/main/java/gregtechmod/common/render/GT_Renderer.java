/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.render;

import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Utility;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class GT_Renderer
extends RenderPlayer {
    public GT_Renderer() {
        RenderManager.instance.entityRenderMap.remove(EntityPlayer.class);
        RenderManager.instance.entityRenderMap.put(EntityPlayer.class, this);
        this.setRenderManager(RenderManager.instance);
    }

    public void renderPlayer(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, float par8, float par9) {
        if (GT_Utility.getFullInvisibility(par1EntityPlayer)) {
            return;
        }
        super.renderPlayer(par1EntityPlayer, par2, par4, par6, par8, par9);
    }

    public void renderSpecials(EntityPlayer par1EntityPlayer, float par2) {
        if (GT_Utility.getFullInvisibility(par1EntityPlayer)) {
            return;
        }
        if (GT_Utility.getPotion((EntityLiving)par1EntityPlayer, Potion.invisibility.id)) {
            return;
        }
        super.renderSpecials(par1EntityPlayer, par2);
        boolean tLoaded = false;
        try {
            if (GT_Mod.instance.mShowCapes) {
                if (GT_Mod.sAdminNames.contains(par1EntityPlayer.username)) {
                    tLoaded = this.loadDownloadableImageTexture("", "/gregtechmodcapes/AdminCape.png");
                }
                if (GT_Mod.sPremiumNames.contains(par1EntityPlayer.username)) {
                    tLoaded = this.loadDownloadableImageTexture("", "/gregtechmodcapes/PremiumCape.png");
                }
                if (GT_Mod.mBrainTechCapeList.contains(par1EntityPlayer.username)) {
                    tLoaded = this.loadDownloadableImageTexture("https://dl.dropbox.com/u/88825306/BrainTechCape.png", "/gregtechmod/textures/BrainTechCape.png");
                }
                if (GT_Mod.mGregTechCapeList.contains(par1EntityPlayer.username)) {
                    tLoaded = this.loadDownloadableImageTexture("https://dl.dropbox.com/u/88825306/GregTechCape.png", "/gregtechmod/textures/GregTechCape.png");
                }
            }
            if (par1EntityPlayer.username.equals("Mr_Brain")) {
                tLoaded = this.loadDownloadableImageTexture("https://dl.dropbox.com/u/88825306/MrBrainCape.png", "/gregtechmod/textures/MrBrainCape.png");
            }
            if (par1EntityPlayer.username.equals("GregoriusT")) {
                tLoaded = this.loadDownloadableImageTexture("https://dl.dropbox.com/u/88825306/GregoriusCape.png", "/gregtechmod/textures/GregoriusCape.png");
            }
        }
        catch (Throwable e) {
            // empty catch block
        }
        if (tLoaded) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.125f);
            double var22 = par1EntityPlayer.field_71091_bM + (par1EntityPlayer.field_71094_bP - par1EntityPlayer.field_71091_bM) * (double)par2 - (par1EntityPlayer.prevPosX + (par1EntityPlayer.posX - par1EntityPlayer.prevPosX) * (double)par2);
            double var23 = par1EntityPlayer.field_71096_bN + (par1EntityPlayer.field_71095_bQ - par1EntityPlayer.field_71096_bN) * (double)par2 - (par1EntityPlayer.prevPosY + (par1EntityPlayer.posY - par1EntityPlayer.prevPosY) * (double)par2);
            double var8 = par1EntityPlayer.field_71097_bO + (par1EntityPlayer.field_71085_bR - par1EntityPlayer.field_71097_bO) * (double)par2 - (par1EntityPlayer.prevPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.prevPosZ) * (double)par2);
            float var10 = par1EntityPlayer.prevRenderYawOffset + (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset) * par2;
            double var11 = MathHelper.sin((float)(var10 * (float)Math.PI / 180.0f));
            double var13 = -MathHelper.cos((float)(var10 * (float)Math.PI / 180.0f));
            float var15 = (float)var23 * 10.0f;
            if (var15 < -6.0f) {
                var15 = -6.0f;
            }
            if (var15 > 32.0f) {
                var15 = 32.0f;
            }
            float var16 = (float)(var22 * var11 + var8 * var13) * 100.0f;
            float var17 = (float)(var22 * var13 - var8 * var11) * 100.0f;
            if (var16 < 0.0f) {
                var16 = 0.0f;
            }
            float var18 = par1EntityPlayer.prevCameraYaw + (par1EntityPlayer.cameraYaw - par1EntityPlayer.prevCameraYaw) * par2;
            var15 += MathHelper.sin((float)((par1EntityPlayer.prevDistanceWalkedModified + (par1EntityPlayer.distanceWalkedModified - par1EntityPlayer.prevDistanceWalkedModified) * par2) * 6.0f)) * 32.0f * var18;
            if (par1EntityPlayer.isSneaking()) {
                var15 += 25.0f;
            }
            GL11.glRotatef((float)(6.0f + var16 / 2.0f + var15), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(var17 / 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(-var17 / 2.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            ((ModelBiped)this.mainModel).renderCloak(0.0625f);
            GL11.glPopMatrix();
        }
    }
}

