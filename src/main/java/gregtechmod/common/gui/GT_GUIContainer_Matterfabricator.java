/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.common.containers.GT_Container_Matterfabricator;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_Matterfabricator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_Matterfabricator
extends GT_GUIContainerMetaID_Machine {
    public GT_GUIContainer_Matterfabricator(InventoryPlayer aInventoryPlayer, GT_TileEntity_Matterfabricator aTileEntity, int aID) {
        super(new GT_Container_Matterfabricator(aInventoryPlayer, aTileEntity, aID), (GT_TileEntityMetaID_Machine)aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString("Matterfabricator", 7, 4, 0x404040);
        this.fontRenderer.drawString(((GT_Container_Matterfabricator)this.mContainer).mPercentualProgress + "%", 128, 40, 0xFAFAFF);
        this.fontRenderer.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 96 + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/Matterfabricator.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            int tScale = this.mContainer.mEnergy / Math.max(1, this.mContainer.mStorage / 160);
            this.drawTexturedModalRect(x + 8, y + 60, 0, 251, tScale, 5);
        }
    }
}

