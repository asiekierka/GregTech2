/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.InventoryPlayer
 *  org.lwjgl.opengl.GL11
 */
package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_AdvancedTranslocator;
import gregtechmod.common.gui.GT_GUIContainerMetaTile_Machine;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_AdvancedTranslocator
extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_AdvancedTranslocator(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_AdvancedTranslocator(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String tINString = "";
        String tOUTString = "";
        switch (((GT_Container_AdvancedTranslocator)this.mContainer).mInputSide) {
            case 0: {
                tINString = "DOWN";
                break;
            }
            case 1: {
                tINString = "UP";
                break;
            }
            case 2: {
                tINString = "NORTH";
                break;
            }
            case 3: {
                tINString = "SOUTH";
                break;
            }
            case 4: {
                tINString = "WEST";
                break;
            }
            case 5: {
                tINString = "EAST";
                break;
            }
            default: {
                tINString = "FAIL";
            }
        }
        switch (((GT_Container_AdvancedTranslocator)this.mContainer).mOutputSide) {
            case 0: {
                tOUTString = "DOWN";
                break;
            }
            case 1: {
                tOUTString = "UP";
                break;
            }
            case 2: {
                tOUTString = "NORTH";
                break;
            }
            case 3: {
                tOUTString = "SOUTH";
                break;
            }
            case 4: {
                tOUTString = "WEST";
                break;
            }
            case 5: {
                tOUTString = "EAST";
                break;
            }
            default: {
                tOUTString = "FAIL";
            }
        }
        this.fontRenderer.drawString(tINString, 7, 7, 0xFAFAFF);
        this.fontRenderer.drawString(tOUTString, 138, 7, 0xFAFAFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int texture = this.mc.renderEngine.getTexture("/gregtechmod/textures/gui/AdvancedTranslocator.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}

