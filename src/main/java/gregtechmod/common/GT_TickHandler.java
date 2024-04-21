package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.IPlayerTickingItem;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class GT_TickHandler implements ITickHandler {
	
	public static long mTickCounter = 0;
	
    @Override
    public void tickStart(EnumSet<TickType> aType, Object... aData) {

    }

    @Override
    public void tickEnd(EnumSet<TickType> aType, Object... aData) {
        if (aType.contains(TickType.SERVER)) {
        	mTickCounter++;
        }
        if (aType.contains(TickType.CLIENT)) {
        	mTickCounter++;
        }
        if (aType.contains(TickType.WORLD)) {
            World tWorld = (World)aData[0];
            if (tWorld != null) {
            	if (GT_Mod.instance.mUniverse == null)
            		GT_Mod.instance.mUniverse = tWorld;
            	
	            if (GT_TileEntity_IDSU.sEnergyList == null)
	            	GT_Mod.instance.readIDSUData();
	            
	            if (mTickCounter%10000==0)
	            	GT_Mod.instance.writeIDSUData();
            }
        }
        if (aType.contains(TickType.PLAYER)) {
        	EntityPlayer tPlayer = (EntityPlayer)aData[0];
        	if (tPlayer != null && !tPlayer.isDead) {
        		for (int i = 0; i < 36; i++)
            		if (tPlayer.inventory.getStackInSlot(i) != null && tPlayer.inventory.getStackInSlot(i).getItem() instanceof IPlayerTickingItem)
            			((IPlayerTickingItem)tPlayer.inventory.getStackInSlot(i).getItem()).onTick(tPlayer, tPlayer.inventory.getStackInSlot(i), (int)mTickCounter, false);
        		
            	for (int i = 0; i < 4; i++)
            		if (tPlayer.inventory.armorInventory[i] != null && tPlayer.inventory.armorInventory[i].getItem() instanceof IPlayerTickingItem)
            			((IPlayerTickingItem)tPlayer.inventory.armorInventory[i].getItem()).onTick(tPlayer, tPlayer.inventory.armorInventory[i], (int)mTickCounter, true);
        	}
        }
    }
    
    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.RENDER, TickType.WORLD, TickType.PLAYER, TickType.SERVER, TickType.CLIENT);
    }

    @Override
    public String getLabel() { return "GT_TickHandler"; }
}