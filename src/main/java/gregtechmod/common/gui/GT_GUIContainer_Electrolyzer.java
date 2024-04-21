package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_Electrolyzer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_Electrolyzer extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_Electrolyzer(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_Electrolyzer(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Industrial Electrolyzer", 8,  4, 4210752);
        if (((mContainer).mDisplayErrorCode & 1) != 0)
        	fontRenderer.drawString("Insufficient Energy Line!", 8, ySize - 94, 4210752);
        else
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        //draw your Gui here, only thing you need to change is the path
        int texture = mc.renderEngine.getTexture("/gregtechmod/textures/gui/Electrolyzer.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        if (mContainer != null) {
        	int tScale = ((GT_Container_Electrolyzer)mContainer).mProgressScale;
        	this.drawTexturedModalRect(x + 73, y + 44 - tScale, 183, 44 - tScale, 30, tScale);
        }
    }
}
