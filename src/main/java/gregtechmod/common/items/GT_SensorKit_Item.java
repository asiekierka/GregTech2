package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.IGregTechDeviceInformation;
import gregtechmod.api.IIgnoreRightclickOnMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import shedar.mods.ic2.nuclearcontrol.NuclearNetworkHelper;
import shedar.mods.ic2.nuclearcontrol.utils.ItemStackUtils;

public class GT_SensorKit_Item extends Item implements IIgnoreRightclickOnMachine {

	public GT_SensorKit_Item(int aID) {
		super(aID);
		setCreativeTab(GT_Mod.tabGregTech);
		setMaxStackSize(1);
	}

    @Override
    public String getTextureFile() {
        return "/gregtechmod/textures/items.png";
    }
    
    protected ChunkCoordinates getTargetCoordinates(World world, int x, int y, int z, ItemStack stack) {
    	TileEntity tTileEntity = world.getBlockTileEntity(x, y, z);
        if (tTileEntity != null && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
            ChunkCoordinates coordinates = new ChunkCoordinates();
            coordinates.posX = x;
            coordinates.posY = y;
            coordinates.posZ = z;
            return coordinates;
        }
        return null;
    }
    
    private void setCoordinates(ItemStack itemStack, int x, int y, int z)
    {
        NBTTagCompound nbtTagCompound = ItemStackUtils.getTagCompound(itemStack);
        nbtTagCompound.setInteger("x", x);
        nbtTagCompound.setInteger("y", y);
        nbtTagCompound.setInteger("z", z);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(player == null)
            return false;
        boolean isServer = player instanceof EntityPlayerMP;
        if(!isServer)
            return false;
        ChunkCoordinates position = getTargetCoordinates(world, x, y, z, stack);
        
        if(position != null)
        {
            ItemStack sensorLocationCard = new ItemStack(GT_Mod.instance.mItems[16], 1);
            setCoordinates(sensorLocationCard, position.posX, position.posY, position.posZ);
            player.inventory.mainInventory[player.inventory.currentItem] = sensorLocationCard;
        	if(!world.isRemote)
        	{
        	    NuclearNetworkHelper.chatMessage(player, "SensorKit");
        	}
        	return true;
        }
        return false;
    }
}
