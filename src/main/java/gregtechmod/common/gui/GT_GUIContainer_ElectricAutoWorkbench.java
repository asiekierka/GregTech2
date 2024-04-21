/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricAutoWorkbench;
import gregtechmod.common.gui.GT_GUIContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_ElectricAutoWorkbench
extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_ElectricAutoWorkbench(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_ElectricAutoWorkbench(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/ElectricAutoWorkbench.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            int tMode = ((GT_Container_ElectricAutoWorkbench)this.mContainer).mMode;
            if (tMode != 0) {
                this.drawTexturedModalRect(x + 120, y + 40, tMode * 18, 166, 18, 18);
            }
            tMode = ((GT_Container_ElectricAutoWorkbench)this.mContainer).mThroughPut;
            this.drawTexturedModalRect(x + 120, y + 4, tMode * 18, 184, 18, 18);
        }
    }
}

