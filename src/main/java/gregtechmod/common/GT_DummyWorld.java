/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.world.MinecraftException
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldSettings
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.chunk.storage.IChunkLoader
 *  net.minecraft.world.storage.IPlayerFileData
 *  net.minecraft.world.storage.ISaveHandler
 *  net.minecraft.world.storage.WorldInfo
 */
package gregtechmod.common;

import gregtechmod.common.GT_IteratorRandom;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class GT_DummyWorld
extends World {
    public GT_IteratorRandom mRandom = new GT_IteratorRandom();
    public ItemStack mLastSetBlock = null;

    public GT_DummyWorld(ISaveHandler par1iSaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, Profiler par5Profiler) {
        super(par1iSaveHandler, par2Str, par4WorldSettings, par3WorldProvider, par5Profiler);
        this.rand = this.mRandom;
    }

    public GT_DummyWorld() {
        this(new ISaveHandler(){

            public void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2) {
            }

            public void saveWorldInfo(WorldInfo var1) {
            }

            public WorldInfo loadWorldInfo() {
                return null;
            }

            public IPlayerFileData getSaveHandler() {
                return null;
            }

            public String getSaveDirectoryName() {
                return null;
            }

            public File getMapFileFromName(String var1) {
                return null;
            }

            public IChunkLoader getChunkLoader(WorldProvider var1) {
                return null;
            }

            public void flush() {
            }

            public void checkSessionLock() {
            }
        }, "DUMMY_DIMENSION", new WorldProvider(){

            public String getDimensionName() {
                return "DUMMY_DIMENSION";
            }
        }, new WorldSettings(new WorldInfo(new NBTTagCompound())), new Profiler());
    }

    protected IChunkProvider createChunkProvider() {
        return null;
    }

    public Entity getEntityByID(int var1) {
        return null;
    }

    public boolean setBlockAndMetadataWithNotify(int aX, int aY, int aZ, int aID, int aMeta) {
        this.mLastSetBlock = new ItemStack(aID, 1, aMeta);
        return true;
    }

    public int getFullBlockLightValue(int aX, int aY, int aZ) {
        return 10;
    }

    public int getBlockId(int aX, int aY, int aZ) {
        if (aY == -1) {
            return Block.grass.blockID;
        }
        return 0;
    }

    public boolean canBlockSeeTheSky(int aX, int aY, int aZ) {
        return true;
    }
}

