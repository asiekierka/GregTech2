/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.Direction
 *  ic2.api.ElectricItem
 *  ic2.api.IElectricItem
 *  ic2.api.IEnergyStorage
 *  ic2.api.IWrenchable
 *  ic2.api.energy.EnergyNet
 *  ic2.api.energy.tile.IEnergySink
 *  ic2.api.energy.tile.IEnergySource
 *  ic2.api.network.INetworkDataProvider
 *  ic2.api.network.INetworkUpdateListener
 *  ic2.api.network.NetworkHelper
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeDirection
 *  net.minecraftforge.common.ISidedInventory
 */
package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.IGregTechDeviceInformation;
import gregtechmod.common.GT_ModHandler;
import ic2.api.Direction;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import ic2.api.IEnergyStorage;
import ic2.api.IWrenchable;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkUpdateListener;
import ic2.api.network.NetworkHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class GT_TileEntityMetaID_Machine
extends TileEntity
implements ISidedInventory,
IEnergySink,
IEnergySource,
IWrenchable,
IEnergyStorage,
IGregTechDeviceInformation,
INetworkDataProvider,
INetworkUpdateListener {
    public ItemStack[] mInventory = new ItemStack[this.getInventorySlotCount()];
    public boolean mIsAddedToEnet = false;
    public boolean mFirstTick = true;
    public boolean mNeedsUpdate = true;
    public boolean mActive;
    public boolean oActive;
    public boolean mRedstone;
    public boolean oRedstone;
    public boolean mReleaseEnergy = false;
    private int mStoredEnergy = 0;
    private int oOutput;
    private int oX = 0;
    private int oY = 0;
    private int oZ = 0;
    private short mFacing = (short)-1;
    private short oFacing;
    public String mOwnerName = "";
    public long mTickTimer = 0L;

    public boolean isEnetOutput() {
        return false;
    }

    public boolean isEnetInput() {
        return false;
    }

    public int maxEUStore() {
        return 0;
    }

    public int maxEUInput() {
        return 0;
    }

    public int maxEUOutput() {
        return 0;
    }

    public boolean isOutputFacing(short aDirection) {
        return false;
    }

    public boolean isInputFacing(short aDirection) {
        return false;
    }

    public boolean isFacingValid(int aFacing) {
        return false;
    }

    public void onRemoval() {
    }

    public int getInventorySlotCount() {
        return 0;
    }

    public void storeAdditionalData(NBTTagCompound aNBT) {
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    }

    public void onPreTickUpdate() {
    }

    public void onPostTickUpdate() {
    }

    public void onFirstTickUpdate() {
    }

    public boolean isAccessible(EntityPlayer aPlayer) {
        return false;
    }

    public boolean isValidSlot(int aIndex) {
        return true;
    }

    public void setEnergyVar(int aEU) {
        this.mStoredEnergy = aEU;
    }

    public int getEnergyVar() {
        return this.mStoredEnergy;
    }

    public int getTier() {
        return this.getTier(Math.max(this.maxEUOutput(), this.maxEUInput()));
    }

    public int getTier(int aValue) {
        int tMaxEU = aValue;
        return tMaxEU <= 32 ? 1 : (tMaxEU <= 128 ? 2 : (tMaxEU <= 512 ? 3 : (tMaxEU <= 2048 ? 4 : (tMaxEU <= 8192 ? 5 : 6))));
    }

    public int getChargeTier() {
        return this.getTier();
    }

    public int rechargerSlotStartIndex() {
        return 0;
    }

    public int rechargerSlotCount() {
        return 0;
    }

    public int dechargerSlotStartIndex() {
        return 0;
    }

    public int dechargerSlotCount() {
        return 0;
    }

    public boolean ownerControl() {
        return false;
    }

    public boolean hasAnimation() {
        return false;
    }

    public ArrayList getSpecialDebugInfo(EntityPlayer aPlayer, int aLogLevel, ArrayList aList) {
        return aList;
    }

    public void onLeftclick(EntityPlayer aPlayer) {
    }

    @Override
    public String getMainInfo() {
        return "";
    }

    @Override
    public String getSecondaryInfo() {
        return "";
    }

    @Override
    public String getTertiaryInfo() {
        return "";
    }

    @Override
    public boolean isGivingInformation() {
        return false;
    }

    public int getStartInventorySide(ForgeDirection aSide) {
        return 0;
    }

    public int getSizeInventorySide(ForgeDirection aSide) {
        return this.getInventorySlotCount();
    }

    public String getInvName() {
        return "Defaultmachine";
    }

    public void openChest() {
    }

    public void closeChest() {
    }

    public void updateEntity() {
        if (this.isInvalid()) {
            return;
        }
        try {
            ++this.mTickTimer;
            if (this.mFirstTick) {
                if (this.mFacing == -1) {
                    this.mFacing = 0;
                }
                if (this.mTickTimer > 19L) {
                    this.mFirstTick = false;
                    this.onFirstTickUpdate();
                    try {
                        NetworkHelper.updateTileEntityField((TileEntity)this, (String)"mOwnerName");
                    }
                    catch (Throwable e) {
                        // empty catch block
                    }
                    this.mNeedsUpdate = true;
                } else {
                    return;
                }
            }
            if (this.mTickTimer % 6000L == 0L) {
                this.mNeedsUpdate = true;
            }
            this.onPreTickUpdate();
            if (this.worldObj.isRemote) {
                if ((this.mNeedsUpdate || this.hasAnimation()) && (GT_Mod.instance.mAnimations || this.mTickTimer < 250L)) {
                    this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
                    this.mNeedsUpdate = false;
                }
            } else {
                if (this.mNeedsUpdate) {
                    this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 0, (int)this.mFacing);
                    this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 1, this.mActive ? 1 : 0);
                    this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, GT_Mod.instance.mBlocks[1].blockID, 2, this.mRedstone ? 1 : 0);
                    this.mNeedsUpdate = false;
                }
                if (this.mActive != this.oActive) {
                    this.oActive = this.mActive;
                    this.mNeedsUpdate = true;
                }
                if (this.mRedstone != this.oRedstone) {
                    this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord));
                    this.oRedstone = this.mRedstone;
                    this.mNeedsUpdate = true;
                }
                if (this.mTickTimer > 30L && (this.isEnetOutput() || this.isEnetInput()) && !this.mIsAddedToEnet) {
                    this.mIsAddedToEnet = GT_ModHandler.addTileToEnet(this);
                }
                if (this.xCoord != this.oX || this.yCoord != this.oY || this.zCoord != this.oZ) {
                    this.oX = this.xCoord;
                    this.oY = this.yCoord;
                    this.oZ = this.zCoord;
                    if (this.mIsAddedToEnet) {
                        GT_ModHandler.removeTileFromEnet(this);
                    }
                    this.mIsAddedToEnet = false;
                    this.mNeedsUpdate = true;
                }
                if (this.mFacing != this.oFacing) {
                    this.oFacing = this.mFacing;
                    if (this.mIsAddedToEnet) {
                        GT_ModHandler.removeTileFromEnet(this);
                    }
                    this.mIsAddedToEnet = false;
                }
                if (this.getOutput() != this.oOutput) {
                    this.oOutput = this.getOutput();
                    if (this.mIsAddedToEnet) {
                        GT_ModHandler.removeTileFromEnet(this);
                    }
                    this.mIsAddedToEnet = false;
                    this.mNeedsUpdate = true;
                }
                if (this.mIsAddedToEnet && GT_Mod.instance.mMachineFireExplosions && this.worldObj.rand.nextInt(1000) == 0) {
                    switch (this.worldObj.rand.nextInt(6)) {
                        case 0: {
                            if (this.worldObj.getBlockId(this.xCoord + 1, this.yCoord, this.zCoord) != Block.fire.blockID) break;
                            this.doEnergyExplosion();
                            break;
                        }
                        case 1: {
                            if (this.worldObj.getBlockId(this.xCoord - 1, this.yCoord, this.zCoord) != Block.fire.blockID) break;
                            this.doEnergyExplosion();
                            break;
                        }
                        case 2: {
                            if (this.worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord) != Block.fire.blockID) break;
                            this.doEnergyExplosion();
                            break;
                        }
                        case 3: {
                            if (this.worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord) != Block.fire.blockID) break;
                            this.doEnergyExplosion();
                            break;
                        }
                        case 4: {
                            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord + 1) != Block.fire.blockID) break;
                            this.doEnergyExplosion();
                            break;
                        }
                        case 5: {
                            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord - 1) != Block.fire.blockID) break;
                            this.doEnergyExplosion();
                        }
                    }
                }
                if (this.mIsAddedToEnet && this.isEnetOutput() && this.getEnergyVar() >= this.maxEUOutput() && this.maxEUOutput() > 0) {
                    try {
                        EnergyNet tEnergyNet = EnergyNet.getForWorld((World)this.worldObj);
                        if (tEnergyNet != null) {
                            this.setStoredEnergy(this.getEnergyVar() + tEnergyNet.emitEnergyFrom((IEnergySource)this, this.maxEUOutput()) - this.maxEUOutput());
                        }
                    }
                    catch (Exception e) {
                        // empty catch block
                    }
                }
                for (int j = 0; j < this.getChargeTier(); ++j) {
                    int i;
                    for (i = this.dechargerSlotStartIndex(); i < this.dechargerSlotCount() + this.dechargerSlotStartIndex(); ++i) {
                        if (this.mInventory[i] == null || this.demandsEnergy() <= 0 || !(this.mInventory[i].getItem() instanceof IElectricItem) || !((IElectricItem)this.mInventory[i].getItem()).canProvideEnergy()) continue;
                        this.increaseStoredEnergy(ElectricItem.discharge((ItemStack)this.mInventory[i], (int)(this.maxEUStore() - this.getEnergyVar()), (int)this.getChargeTier(), (boolean)false, (boolean)false));
                    }
                    for (i = this.rechargerSlotStartIndex(); i < this.rechargerSlotCount() + this.rechargerSlotStartIndex(); ++i) {
                        if (this.getEnergyVar() <= 0 || this.mInventory[i] == null || !(this.mInventory[i].getItem() instanceof IElectricItem)) continue;
                        this.decreaseStoredEnergy(ElectricItem.charge((ItemStack)this.mInventory[i], (int)this.getEnergyVar(), (int)this.getChargeTier(), (boolean)false, (boolean)false), true);
                    }
                }
            }
            this.onPostTickUpdate();
            this.onInventoryChanged();
        }
        catch (Throwable e) {
            System.err.println("Encountered Exception while ticking TileEntity, the Game should've crashed by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            e.printStackTrace();
        }
    }

    public void receiveClientEvent(int aEventID, int aValue) {
        super.receiveClientEvent(aEventID, aValue);
        if (this.worldObj.isRemote) {
            switch (aEventID) {
                case 0: {
                    this.mFacing = (short)aValue;
                    this.mNeedsUpdate = true;
                    break;
                }
                case 1: {
                    this.mActive = aValue != 0;
                    this.mNeedsUpdate = true;
                    break;
                }
                case 2: {
                    this.mRedstone = aValue != 0;
                    this.mNeedsUpdate = true;
                }
            }
        }
    }

    public void readFromNBT(NBTTagCompound aNBT) {
        super.readFromNBT(aNBT);
        this.mStoredEnergy = aNBT.getInteger("mStoredEnergy");
        this.mFacing = aNBT.getShort("mFacing");
        this.mOwnerName = aNBT.getString("mOwnerName");
        this.mActive = aNBT.getBoolean("mActive");
        this.mRedstone = aNBT.getBoolean("mRedstone");
        this.getAdditionalData(aNBT);
        NBTTagList tagList = aNBT.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tag = (NBTTagCompound)tagList.tagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot < 0 || slot >= this.mInventory.length) continue;
            this.mInventory[slot] = ItemStack.loadItemStackFromNBT((NBTTagCompound)tag);
        }
    }

    public void writeToNBT(NBTTagCompound aNBT) {
        super.writeToNBT(aNBT);
        aNBT.setInteger("mStoredEnergy", this.mStoredEnergy);
        aNBT.setShort("mFacing", this.mFacing);
        aNBT.setString("mOwnerName", this.mOwnerName);
        aNBT.setBoolean("mActive", this.mActive);
        aNBT.setBoolean("mRedstone", this.mRedstone);
        this.storeAdditionalData(aNBT);
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.mInventory.length; ++i) {
            ItemStack stack = this.mInventory[i];
            if (stack == null) continue;
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte)i);
            stack.writeToNBT(tag);
            itemList.appendTag((NBTBase)tag);
        }
        aNBT.setTag("Inventory", (NBTBase)itemList);
    }

    public void invalidate() {
        this.onRemoval();
        if (this.mIsAddedToEnet) {
            this.mIsAddedToEnet = !GT_ModHandler.removeTileFromEnet(this);
        }
        super.invalidate();
    }

    public void validate() {
        super.validate();
        this.mNeedsUpdate = true;
        this.mTickTimer = 0L;
    }

    public boolean isAddedToEnergyNet() {
        return this.mIsAddedToEnet;
    }

    public boolean acceptsEnergyFrom(TileEntity aReceiver, Direction aDirection) {
        if (this.isInvalid() || this.mReleaseEnergy) {
            return false;
        }
        return this.isInputFacing(GT_ModHandler.convertIC2DirectionToShort(aDirection));
    }

    public boolean emitsEnergyTo(TileEntity aReceiver, Direction aDirection) {
        if (this.isInvalid() || this.mReleaseEnergy) {
            return this.mReleaseEnergy;
        }
        return this.isOutputFacing(GT_ModHandler.convertIC2DirectionToShort(aDirection));
    }

    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int aFacing) {
        return aFacing != this.mFacing && this.isFacingValid(aFacing);
    }

    public short getFacing() {
        return this.mFacing;
    }

    public void setFacing(short aFacing) {
        this.mNeedsUpdate = true;
        if (this.isFacingValid(aFacing)) {
            this.mFacing = aFacing;
        }
    }

    public boolean wrenchCanRemove(EntityPlayer aPlayer) {
        return this.playerOwnsThis(aPlayer);
    }

    public float getWrenchDropRate() {
        return 0.8f;
    }

    public int getSizeInventory() {
        return this.mInventory.length;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.mInventory[slot];
    }

    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.mInventory[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = this.getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                this.setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    this.setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = this.getStackInSlot(slot);
        if (stack != null) {
            this.setInventorySlotContents(slot, null);
        }
        return stack;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean playerOwnsThis(EntityPlayer aPlayer) {
        if (this.ownerControl()) {
            if (this.mOwnerName.equals("") && !this.worldObj.isRemote) {
                this.mOwnerName = aPlayer.username;
            } else if (!(aPlayer.username.equals("Player") || this.mOwnerName.equals("Player") || this.mOwnerName.equals(aPlayer.username))) {
                return false;
            }
        }
        return true;
    }

    public boolean isUseableByPlayer(EntityPlayer aPlayer) {
        this.mNeedsUpdate = true;
        return this.playerOwnsThis(aPlayer) && !this.mFirstTick && this.mTickTimer > 20L && this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && aPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) < 64.0 && this.isAccessible(aPlayer);
    }

    public int getStored() {
        return Math.min(this.getEnergyVar(), this.maxEUStore());
    }

    public boolean setStoredEnergy(int aEnergy) {
        if (aEnergy < 0) {
            aEnergy = 0;
        }
        this.setEnergyVar(aEnergy);
        return true;
    }

    public boolean decreaseStoredEnergy(int aEnergy, boolean aIgnoreTooLessEnergy) {
        if (this.getEnergyVar() - aEnergy >= 0 || aIgnoreTooLessEnergy) {
            this.setEnergyVar(this.getEnergyVar() - aEnergy);
            if (this.getEnergyVar() < 0) {
                this.setStoredEnergy(0);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean increaseStoredEnergy(int aEnergy) {
        if (this.getEnergyVar() < this.maxEUStore()) {
            this.setStoredEnergy(this.getEnergyVar() + aEnergy);
            return true;
        }
        return false;
    }

    public void doExplosion(int aAmount) {
        if (this.isAddedToEnergyNet() && GT_Mod.instance.mMachineWireFire) {
            try {
                this.mReleaseEnergy = true;
                GT_ModHandler.removeTileFromEnet(this);
                GT_ModHandler.addTileToEnet(this);
                EnergyNet tEnergyNet = EnergyNet.getForWorld((World)this.worldObj);
                if (tEnergyNet != null) {
                    tEnergyNet.emitEnergyFrom((IEnergySource)this, 32);
                    tEnergyNet.emitEnergyFrom((IEnergySource)this, 128);
                    tEnergyNet.emitEnergyFrom((IEnergySource)this, 512);
                    tEnergyNet.emitEnergyFrom((IEnergySource)this, 2048);
                    tEnergyNet.emitEnergyFrom((IEnergySource)this, 8192);
                }
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        this.mReleaseEnergy = false;
        float tStrength = aAmount < 10 ? 1.0f : (aAmount < 32 ? 2.0f : (aAmount < 128 ? 3.0f : (aAmount < 512 ? 4.0f : (aAmount < 2048 ? 5.0f : (aAmount < 4096 ? 6.0f : (aAmount < 8192 ? 7.0f : 8.0f))))));
        int tX = this.xCoord;
        int tY = this.yCoord;
        int tZ = this.zCoord;
        this.worldObj.setBlock(tX, tY, tZ, 0);
        this.worldObj.createExplosion(null, (double)tX + 0.5, (double)tY + 0.5, (double)tZ + 0.5, tStrength, true);
    }

    public int getMaxEnergyOutput() {
        if (this.mReleaseEnergy) {
            return Integer.MAX_VALUE;
        }
        return this.maxEUOutput();
    }

    public int demandsEnergy() {
        if (this.mReleaseEnergy) {
            return 0;
        }
        return this.maxEUStore() - this.getEnergyVar();
    }

    public int injectEnergy(Direction directionFrom, int aAmount) {
        if (aAmount > this.maxEUInput()) {
            this.doExplosion(aAmount);
            return 0;
        }
        this.setStoredEnergy(this.getEnergyVar() + aAmount);
        return 0;
    }

    public int getTexture(int aSide, int aMeta) {
        return 0;
    }

    public int getCapacity() {
        return this.maxEUStore();
    }

    public int getOutput() {
        return this.maxEUOutput();
    }

    public boolean isActive() {
        return this.mActive;
    }

    public ArrayList getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
        ArrayList<String> tList = new ArrayList<String>();
        if (aLogLevel > 2) {
            // empty if block
        }
        if (aLogLevel > 1) {
            tList.add("Is" + (this.isAccessible(aPlayer) ? " " : " not ") + "accessible for you");
        }
        if (aLogLevel > 0) {
            tList.add("Machine is " + (this.mActive ? "active" : "inactive"));
        }
        return this.getSpecialDebugInfo(aPlayer, aLogLevel, tList);
    }

    public void setStored(int aEU) {
        this.setEnergyVar(aEU);
    }

    public int addEnergy(int aEnergy) {
        if (aEnergy > 0) {
            this.increaseStoredEnergy(aEnergy);
        } else {
            this.decreaseStoredEnergy(-aEnergy, true);
        }
        return this.getStored();
    }

    public boolean isTeleporterCompatible(Direction side) {
        return false;
    }

    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(GT_Mod.instance.mBlocks[1], 1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
    }

    public void onNetworkUpdate(String field) {
    }

    public void doEnergyExplosion() {
        if (this.getStored() >= this.getCapacity() / 5) {
            this.doExplosion(this.getOutput() * (this.getStored() >= this.getCapacity() ? 4 : (this.getStored() >= this.getCapacity() / 2 ? 2 : 1)));
        }
    }

    public List getNetworkedFields() {
        ArrayList<String> rList = new ArrayList<String>();
        rList.add("mOwnerName");
        return rList;
    }

    public int getMaxSafeInput() {
        return this.maxEUInput();
    }
}

