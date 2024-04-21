package gregtechmod.common.gui;

import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GT_GUIContainerMetaID_Machine extends GuiContainer {

	public GT_TileEntityMetaID_Machine mTileEntity;
	public GT_ContainerMetaID_Machine  mContainer;
	public int mID;
	
	public GT_GUIContainerMetaID_Machine(GT_ContainerMetaID_Machine aContainer, GT_TileEntityMetaID_Machine aTileEntity, int aID) {
		super(aContainer);
		mID = aID;
        mTileEntity = aTileEntity;
        mContainer  = aContainer;
	}
	
    public GT_GUIContainerMetaID_Machine(InventoryPlayer aInventoryPlayer, GT_TileEntityMetaID_Machine aTileEntity, int aID) {
        this(new GT_ContainerMetaID_Machine(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }
    
    public String toNumber(int aNumber) {
    	String tString = "";
    	boolean temp = true, negative = false;
    	
    	if (aNumber<0) {
    		aNumber *= -1;
    		negative = true;
    	}
    	
    	for (int i = 1000000000; i > 0; i /= 10) {
    		int tDigit = (aNumber/i)%10;
			if ( temp && tDigit != 0) temp = false;
			if (!temp) {
				tString += tDigit;
				if (i != 1) for (int j = i; j > 0; j /= 1000) if (j == 1) tString += ",";
			}
    	}
    	
    	if (tString.equals("")) tString = "0";
    	
    	return negative?"-"+tString:tString;
    }
    
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRenderer.drawString("GUI_Container", 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        //draw your Gui here, only thing you need to change is the path
        int texture = mc.renderEngine.getTexture("gregtechmod/textures/gui/Fusionreactor.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
