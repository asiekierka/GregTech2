package gregtechmod.common.gui;

import gregtechmod.common.GT_LanguageManager;
import gregtechmod.common.containers.GT_Container_Fusionreactor;
import gregtechmod.common.tileentities.GT_TileEntity_Fusionreactor;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

public class GT_GUIContainer_Fusionreactor extends GT_GUIContainerMetaID_Machine {
	
    public GT_GUIContainer_Fusionreactor(InventoryPlayer aInventoryPlayer, GT_TileEntity_Fusionreactor aTileEntity, int aID) {
        super(new GT_Container_Fusionreactor(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRenderer.drawString(GT_LanguageManager.mNameList1[1], 87, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
        if (mContainer != null) {
        	fontRenderer.drawString("EU: " + toNumber(mContainer.mEnergy), 11, 8, 16448255);
        	fontRenderer.drawString("Coils: "+(((GT_Container_Fusionreactor)mContainer).mCoils!=0?"Yes":"No"), 11, 16, 16448255);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        //draw your Gui here, only thing you need to change is the path
        int texture = mc.renderEngine.getTexture("/gregtechmod/textures/gui/Fusionreactor.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        if (mContainer != null) {
        	if (((GT_Container_Fusionreactor)mContainer).mActive!=0) {
        		this.drawTexturedModalRect(x +  88, y + 36, 176, 0, 14, 14);
        		this.drawTexturedModalRect(x + 111, y + 34, 176, 14, ((GT_Container_Fusionreactor)mContainer).mProgressScale, 16);
        	}
        }
    }
}
