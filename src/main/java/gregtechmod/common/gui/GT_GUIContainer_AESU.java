/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.common.containers.GT_Container_AESU;
import gregtechmod.common.gui.GT_GUIContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntity_AESU;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_AESU
extends GT_GUIContainerMetaID_Machine {
    public GT_GUIContainer_AESU(InventoryPlayer aInventoryPlayer, GT_TileEntity_AESU aTileEntity, int aID) {
        super(new GT_Container_AESU(aInventoryPlayer, aTileEntity, aID), (GT_TileEntityMetaID_Machine)aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString("A.E.S.U.", 11, 8, 0xFAFAFF);
        if (this.mContainer != null) {
            this.fontRenderer.drawString("EU: " + this.toNumber(this.mContainer.mEnergy), 11, 16, 0xFAFAFF);
            this.fontRenderer.drawString("MAX: " + this.toNumber(this.mContainer.mStorage), 11, 24, 0xFAFAFF);
            this.fontRenderer.drawString("MAX EU/t IN: " + this.toNumber(this.mContainer.mInput), 11, 32, 0xFAFAFF);
            this.fontRenderer.drawString("EU/t OUT: " + this.toNumber(this.mContainer.mOutput), 11, 40, 0xFAFAFF);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/AESU.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        if (this.mContainer != null) {
            int tScale = this.mContainer.mEnergy / Math.max(1, this.mContainer.mStorage / 97);
            this.drawTexturedModalRect(x + 8, y + 73, 0, 251, tScale, 5);
        }
    }
}

