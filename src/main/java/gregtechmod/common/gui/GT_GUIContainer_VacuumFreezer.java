/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_VacuumFreezer;
import gregtechmod.common.gui.GT_GUIContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_VacuumFreezer
extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_VacuumFreezer(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_VacuumFreezer(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        if ((this.mContainer.mDisplayErrorCode & 1) != 0) {
            this.fontRenderer.drawString("Insufficient Energy Line!", 8, this.ySize - 94, 0x404040);
        } else {
            this.fontRenderer.drawString(StatCollector.translateToLocal((String)"container.inventory"), 8, this.ySize - 94, 0x404040);
        }
        this.fontRenderer.drawString("Vacuum Freezer", 8, 4, 0x404040);
        if (!((GT_Container_VacuumFreezer)this.mContainer).mMachine) {
            this.fontRenderer.drawString("Incomplete Machine Casing!", 8, this.ySize - 103, 0x404040);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/VacuumFreezer.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            int tScale = ((GT_Container_VacuumFreezer)this.mContainer).mProgressScale;
            this.drawTexturedModalRect(x + 58, y + 28, 176, 0, tScale, 11);
        }
    }
}

