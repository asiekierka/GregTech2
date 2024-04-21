/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_BasicMachine;
import gregtechmod.common.gui.GT_GUIContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_BasicMachine
extends GT_GUIContainerMetaTile_Machine {
    String mTextureFile = "";
    String mName = "";

    public GT_GUIContainer_BasicMachine(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID, String aName, String aTextureFile) {
        super(new GT_Container_BasicMachine(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
        this.mTextureFile = aTextureFile;
        this.mName = aName;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(this.mName, 8, 4, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture(this.mTextureFile);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            if (((GT_Container_BasicMachine)this.mContainer).mOutput) {
                this.drawTexturedModalRect(x + 7, y + 62, 176, 18, 18, 18);
            }
            if (((GT_Container_BasicMachine)this.mContainer).mItemTransfer) {
                this.drawTexturedModalRect(x + 25, y + 62, 176, 36, 18, 18);
            }
            if (((GT_Container_BasicMachine)this.mContainer).mSeperatedInputs) {
                this.drawTexturedModalRect(x + 43, y + 62, 176, 54, 18, 18);
            }
            this.drawTexturedModalRect(x + 78, y + 24, 176, 0, ((GT_Container_BasicMachine)this.mContainer).mProgressBar, 18);
        }
    }
}

