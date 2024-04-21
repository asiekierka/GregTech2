package gregtechmod.common.gui;

import gregtechmod.api.BaseMetaTileEntity;
import gregtechmod.common.containers.GT_Container_ElectricBufferSmall;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_ElectricBufferSmall extends GT_GUIContainerMetaTile_Machine {
	
    public GT_GUIContainer_ElectricBufferSmall(InventoryPlayer aInventoryPlayer, BaseMetaTileEntity aTileEntity, int aID) {
        super(new GT_Container_ElectricBufferSmall(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        //draw your Gui here, only thing you need to change is the path
        int texture = mc.renderEngine.getTexture("/gregtechmod/textures/gui/ElectricBufferSmall.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}