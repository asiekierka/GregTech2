/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.common.containers.GT_Container_IDSU;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_IDSU
extends GT_GUIContainerMetaID_Machine {
    public GT_GUIContainer_IDSU(InventoryPlayer aInventoryPlayer, GT_TileEntity_IDSU aTileEntity, int aID) {
        super(new GT_Container_IDSU(aInventoryPlayer, aTileEntity, aID), (GT_TileEntityMetaID_Machine)aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString("I.D.S.U.", 11, 8, 0xFAFAFF);
        if (this.mContainer != null) {
            this.fontRenderer.drawString("ID: " + ((GT_Container_IDSU)this.mContainer).mPlayerHash, 11, 16, 0xFAFAFF);
            this.fontRenderer.drawString("EU: " + this.toNumber(this.mContainer.mEnergy), 11, 24, 0xFAFAFF);
            this.fontRenderer.drawString("MAX: " + this.toNumber(this.mContainer.mStorage), 11, 32, 0xFAFAFF);
            this.fontRenderer.drawString("MAX EU/t IN: " + this.toNumber(this.mContainer.mInput), 11, 40, 0xFAFAFF);
            this.fontRenderer.drawString("EU/t OUT: " + this.toNumber(this.mContainer.mOutput), 11, 48, 0xFAFAFF);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/IDSU.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            int tScale = this.mContainer.mEnergy / Math.max(1, this.mContainer.mStorage / 116);
            this.drawTexturedModalRect(x + 8, y + 73, 0, 251, tScale, 5);
        }
    }
}

