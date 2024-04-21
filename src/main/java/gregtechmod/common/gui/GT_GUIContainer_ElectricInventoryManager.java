/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricInventoryManager;
import gregtechmod.common.gui.GT_GUIContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_ElectricInventoryManager
extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_ElectricInventoryManager(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_ElectricInventoryManager(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/InventoryManager.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            this.drawTexturedModalRect(x + 4, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[0] * 18, 202, 18, 54);
            this.drawTexturedModalRect(x + 60, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[1] * 18, 202, 18, 54);
            this.drawTexturedModalRect(x + 79, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[2] * 18, 202, 18, 54);
            this.drawTexturedModalRect(x + 135, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[3] * 18, 202, 18, 54);
            this.drawTexturedModalRect(x + 23, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[0] * 18 + 126, 166, 18, 18);
            this.drawTexturedModalRect(x + 41, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[1] * 18 + 126, 166, 18, 18);
            this.drawTexturedModalRect(x + 98, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[2] * 18 + 126, 166, 18, 18);
            this.drawTexturedModalRect(x + 116, y + 59, ((GT_Container_ElectricInventoryManager)this.mContainer).mRangeDirections[3] * 18 + 126, 166, 18, 18);
            this.drawTexturedModalRect(x + 4, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 1) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 60, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 2) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 79, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 4) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 135, y + 59, 108, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetEnergy & 8) != 0 ? 184 : 166, 18, 18);
            int i = -1;
            this.drawTexturedModalRect(x + 23, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 23, y + 22, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 23, y + 40, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 41, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 41, y + 22, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 41, y + 40, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 98, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 98, y + 22, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 98, y + 40, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 116, y + 4, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 116, y + 22, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
            this.drawTexturedModalRect(x + 116, y + 40, ((GT_Container_ElectricInventoryManager)this.mContainer).mTargetDirections[++i] * 18, (((GT_Container_ElectricInventoryManager)this.mContainer).mTargetInOut & 1 << i) != 0 ? 184 : 166, 18, 18);
        }
    }
}

