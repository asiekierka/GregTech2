/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.containers.GT_Container_Item_Destructopack;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_Destructopack
extends GuiContainer {
    public GT_GUIContainer_Destructopack(InventoryPlayer aInventoryPlayer, ItemStack aItem) {
        super((Container)new GT_Container_Item_Destructopack(aInventoryPlayer, aItem));
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(GT_LanguageManager.mNameListItem[33], 8, 6, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/Destructopack.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}

