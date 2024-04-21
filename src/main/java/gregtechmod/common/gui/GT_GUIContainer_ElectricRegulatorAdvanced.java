/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricRegulatorAdvanced;
import gregtechmod.common.gui.GT_GUIContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_ElectricRegulatorAdvanced
extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_ElectricRegulatorAdvanced(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_ElectricRegulatorAdvanced(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[0], 120, 9, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[1], 137, 9, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[2], 155, 9, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[3], 120, 26, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[4], 137, 26, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[5], 155, 26, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[6], 120, 43, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[7], 137, 43, 0xFAFAFF);
        this.fontRenderer.drawString("" + ((GT_Container_ElectricRegulatorAdvanced)this.mContainer).mTargetSlots[8], 155, 43, 0xFAFAFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/ElectricRegulatorAdvanced.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}

