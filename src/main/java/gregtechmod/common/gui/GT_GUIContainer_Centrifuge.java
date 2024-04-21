package gregtechmod.common.gui;

import gregtechmod.common.containers.GT_Container_Centrifuge;
import gregtechmod.common.tileentities.GT_TileEntity_Centrifuge;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_Centrifuge extends GT_GUIContainerMetaID_Machine {
	
    public GT_GUIContainer_Centrifuge(InventoryPlayer aInventoryPlayer, GT_TileEntity_Centrifuge aTileEntity, int aID) {
        super(new GT_Container_Centrifuge(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 4210752);
        fontRenderer.drawString("Industrial", 110,  4, 4210752);
        fontRenderer.drawString("Centrifuge", 110, 12, 4210752);
        if ((((GT_Container_Centrifuge)mContainer).mDisplayErrorCode & 1) != 0)
        	fontRenderer.drawString("Insufficient Energy Line!", 8, ySize - 94, 4210752);
        else
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        //draw your Gui here, only thing you need to change is the path
        int texture = mc.renderEngine.getTexture("/gregtechmod/textures/gui/Centrifuge.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (mContainer != null) {
        	int tScale = ((GT_Container_Centrifuge)mContainer).mProgressScale;
        	this.drawTexturedModalRect(x + 83, y + 33 - tScale, 193, 33 - tScale, 10, tScale);
        	this.drawTexturedModalRect(x + 78 - tScale, y + 38, 188 - tScale, 38 , tScale, 10);
        	this.drawTexturedModalRect(x + 83, y + 53, 193, 53, 10, tScale);
        	this.drawTexturedModalRect(x + 98, y + 38, 208, 38, tScale, 10);
        }
    }
}
