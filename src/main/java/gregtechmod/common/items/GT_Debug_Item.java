package gregtechmod.common.items;

import gregtechmod.api.IDebugableBlock;
import gregtechmod.api.IIgnoreRightclickOnMachine;
import gregtechmod.api.IPlayerTickingItem;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Debug_Item extends GT_Generic_Item implements IIgnoreRightclickOnMachine, IElectricItem, IPlayerTickingItem {

    public GT_Debug_Item(int aID) {
		super(aID);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
	}
    
    public boolean getShareTag() {
        return true;
    }
    
	@Override
	public boolean onTick(EntityPlayer aPlayer, ItemStack aStack, int aTimer, boolean aIsArmor) {
		NBTTagCompound tNBT;
		tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = new NBTTagCompound();
		tNBT.setInteger("charge", 1000000000);
        aStack.setTagCompound(tNBT);
		return true;
	}
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        ElectricItem.charge(tCharged, 1000000000, Integer.MAX_VALUE, true, false);
        var3.add(tCharged);
    }
    
	@Override
	public boolean canProvideEnergy() {
		return true;
	}

	@Override
	public int getChargedItemId() {
		return shiftedIndex;
	}

	@Override
	public int getEmptyItemId() {
		return shiftedIndex;
	}

	@Override
	public int getMaxCharge() {
		return 2000000000;
	}

	@Override
	public int getTier() {
		return 1;
	}

	@Override
	public int getTransferLimit() {
		return 2000000000;
	}

	@Override
	public boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float var8, float var9, float var10) {
	    if (!aWorld.isRemote) {
			TileEntity tTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
		    
		    ArrayList<String> tList = new ArrayList<String>();
		    
		    Block tBlock = Block.blocksList[aWorld.getBlockId(aX, aY, aZ)];
		    
		    if (tTileEntity != null && tTileEntity instanceof IInventory)
		    	tList.add("Name: " + ((IInventory)tTileEntity).getInvName());
		    else
		    	tList.add("Name: " + tBlock.getBlockName());
		    
		    if (tBlock instanceof IDebugableBlock) {
		        ArrayList<String> temp = ((IDebugableBlock)tBlock).getDebugInfo(aPlayer, aX, aY, aZ, 3);
		        if (temp != null) tList.addAll(temp);
		    }
	    
		    if (aPlayer instanceof EntityPlayerMP) {
		    	for (int i = 0; i < tList.size(); i++)
		        	((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(tList.get(i)));
		    }
	    }
        return true;
    }
}
