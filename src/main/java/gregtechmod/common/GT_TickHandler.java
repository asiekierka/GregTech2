/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ITickHandler
 *  cpw.mods.fml.common.TickType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package gregtechmod.common;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import gregtechmod.GT_Mod;
import gregtechmod.api.IPlayerTickingItem;
import gregtechmod.common.tileentities.GT_TileEntity_IDSU;
import java.util.EnumSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GT_TickHandler
implements ITickHandler {
    public static long mTickCounter = 0L;

    public void tickStart(EnumSet aType, Object ... aData) {
    }

    public void tickEnd(EnumSet aType, Object ... aData) {
        EntityPlayer tPlayer;
        World tWorld;
        if (aType.contains(TickType.SERVER)) {
            ++mTickCounter;
        }
        if (aType.contains(TickType.CLIENT)) {
            ++mTickCounter;
        }
        if (aType.contains(TickType.WORLD) && (tWorld = (World)aData[0]) != null) {
            if (GT_Mod.mUniverse == null) {
                GT_Mod.mUniverse = tWorld;
            }
            if (GT_TileEntity_IDSU.sEnergyList == null) {
                GT_Mod.instance.readIDSUData();
            }
            if (mTickCounter % 10000L == 0L) {
                GT_Mod.instance.writeIDSUData();
            }
        }
        if (aType.contains(TickType.PLAYER) && (tPlayer = (EntityPlayer)aData[0]) != null && !tPlayer.isDead) {
            int i;
            for (i = 0; i < 36; ++i) {
                if (tPlayer.inventory.getStackInSlot(i) == null || !(tPlayer.inventory.getStackInSlot(i).getItem() instanceof IPlayerTickingItem)) continue;
                ((IPlayerTickingItem)tPlayer.inventory.getStackInSlot(i).getItem()).onTick(tPlayer, tPlayer.inventory.getStackInSlot(i), (int)mTickCounter, false);
            }
            for (i = 0; i < 4; ++i) {
                if (tPlayer.inventory.armorInventory[i] == null || !(tPlayer.inventory.armorInventory[i].getItem() instanceof IPlayerTickingItem)) continue;
                ((IPlayerTickingItem)tPlayer.inventory.armorInventory[i].getItem()).onTick(tPlayer, tPlayer.inventory.armorInventory[i], (int)mTickCounter, true);
            }
        }
    }

    public EnumSet ticks() {
        return EnumSet.of(TickType.RENDER, TickType.WORLD, TickType.PLAYER, TickType.SERVER, TickType.CLIENT);
    }

    public String getLabel() {
        return "GT_TickHandler";
    }
}

