/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class GT_MetaItem_Renderer
implements IItemRenderer {
    public boolean handleRenderType(ItemStack aStack, IItemRenderer.ItemRenderType aType) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType aType, ItemStack aStack, IItemRenderer.ItemRendererHelper aHelper) {
        return aHelper.equals((Object)IItemRenderer.ItemRendererHelper.ENTITY_BOBBING) || aHelper.equals((Object)IItemRenderer.ItemRendererHelper.ENTITY_ROTATION);
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack aStack, Object ... data) {
        Item tItem = aStack.getItem();
        GL11.glPushMatrix();
        GL11.glBindTexture((int)3553, (int)Minecraft.getMinecraft().renderEngine.getTexture(aStack.getItem().getTextureFile()));
        int var6 = tItem.getIconIndex(aStack);
        float var7 = ((float)(var6 % 16 * 16) + 0.0f) / 256.0f;
        float var8 = ((float)(var6 % 16 * 16) + 15.99f) / 256.0f;
        float var9 = ((float)(var6 / 16 * 16) + 0.0f) / 256.0f;
        float var10 = ((float)(var6 / 16 * 16) + 15.99f) / 256.0f;
        float var11 = 0.0f;
        float var12 = 0.3f;
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)(-var11), (float)(-var12), (float)0.0f);
        float var13 = 1.5f;
        GL11.glScalef((float)var13, (float)var13, (float)var13);
        GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
        ItemRenderer.renderItemIn2D((Tessellator)Tessellator.instance, (float)var8, (float)var9, (float)var7, (float)var10, (float)0.0625f);
        if (aStack != null && aStack.hasEffect()) {
            GL11.glDepthFunc((int)514);
            GL11.glDisable((int)2896);
            Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getTexture("%blur%/misc/glint.png"));
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)768, (int)1);
            float var14 = 0.76f;
            GL11.glColor4f((float)(0.5f * var14), (float)(0.25f * var14), (float)(0.8f * var14), (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            float var15 = 0.125f;
            GL11.glScalef((float)var15, (float)var15, (float)var15);
            float var16 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0f * 8.0f;
            GL11.glTranslatef((float)var16, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.renderItemIn2D((Tessellator)Tessellator.instance, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)var15, (float)var15, (float)var15);
            var16 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0f * 8.0f;
            GL11.glTranslatef((float)(-var16), (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.renderItemIn2D((Tessellator)Tessellator.instance, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glDepthFunc((int)515);
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

