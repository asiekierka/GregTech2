/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_DistillationTower;
import gregtechmod.common.gui.GT_GUIContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_DistillationTower
extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_DistillationTower(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_DistillationTower(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString("Distillation", 116, 4, 0x404040);
        this.fontRenderer.drawString("Tower", 116, 12, 0x404040);
        if (!((GT_Container_DistillationTower)this.mContainer).mMachine) {
            this.fontRenderer.drawString("Incomplete", 116, 20, 0x404040);
            this.fontRenderer.drawString("Machine", 116, 28, 0x404040);
            this.fontRenderer.drawString("Casing!", 116, 36, 0x404040);
        }
        if ((this.mContainer.mDisplayErrorCode & 1) != 0) {
            this.fontRenderer.drawString("Insufficient", 116, 44, 0x404040);
            this.fontRenderer.drawString("Energy", 116, 52, 0x404040);
            this.fontRenderer.drawString("Line!", 116, 60, 0x404040);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/DistillationTower.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            int tScale = ((GT_Container_DistillationTower)this.mContainer).mProgressScale;
            this.drawTexturedModalRect(x + 80, y + 76 - tScale, 176, 76 - tScale, 16, tScale);
        }
    }
}

