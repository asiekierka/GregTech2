package gregtechmod.common;

import gregtechmod.GT_Mod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class GT_Worldgenerator implements IWorldGenerator {
	
	public static boolean sAsteroids = true;
	public static int sIridiumProbability = 20;
	public static boolean sGeneratedOres[] = new boolean[] {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
	
	public GT_Worldgenerator() {
		GT_Mod.mRubyList.add(BiomeGenBase.desert.biomeName);
		GT_Mod.mRubyList.add(BiomeGenBase.desertHills.biomeName);
		GT_Mod.mRubyList.add("Mountainous Desert");
		GT_Mod.mRubyList.add("Mountain Ridge");
		GT_Mod.mRubyList.add("Savanna");
		GT_Mod.mRubyList.add("Shrubland");
		GT_Mod.mRubyList.add("Wasteland");
		GT_Mod.mRubyList.add("Fire Swamp");
		GT_Mod.mRubyList.add("Highlands");
		GT_Mod.mSapphireList.add(BiomeGenBase.ocean.biomeName);
		GT_Mod.mSapphireList.add(BiomeGenBase.beach.biomeName);
		GT_Mod.mSapphireList.add("Twilight Lake");
		GT_Mod.mSapphireList.add("Twilight Stream");
		GT_Mod.mSapphireList.add("Lake Border");
		GT_Mod.mSapphireList.add("Glacier");
		GT_Mod.mBauxiteList.add(BiomeGenBase.plains.biomeName);
		GT_Mod.mBauxiteList.add(BiomeGenBase.forest.biomeName);
		GT_Mod.mBauxiteList.add(BiomeGenBase.forestHills.biomeName);
		GT_Mod.mBauxiteList.add("Rainforest");
		GT_Mod.mBauxiteList.add("Autumn Woods");
		GT_Mod.mBauxiteList.add("Birch Forest");
		GT_Mod.mBauxiteList.add("Forested Hills");
		GT_Mod.mBauxiteList.add("Forested Island");
		GT_Mod.mBauxiteList.add("Green Hills");
		GT_Mod.mBauxiteList.add("Meadow");
		GT_Mod.mBauxiteList.add("Redwood Forest");
		GT_Mod.mBauxiteList.add("Redwood Lush");
		GT_Mod.mBauxiteList.add("Woodlands");
		GT_Mod.mBauxiteList.add("Twilight Forest");
		GT_Mod.mBauxiteList.add("Dense Twilight Forest");
		GT_Mod.mBauxiteList.add("Dark Forest");
		GT_Mod.mBauxiteList.add("Enchanted Forest");
	}
	
    public void generate(Random aRandom, int aX, int aZ, World aWorld, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
    	String tBiome = aWorld.getBiomeGenForCoords(aX+8, aZ+8).biomeName;
    	if (tBiome.equals(BiomeGenBase.hell.biomeName)) {
    		generateNether(aWorld, aRandom, aX*16, aZ*16, aChunkGenerator, aChunkProvider);
    	} else if (tBiome.equals(BiomeGenBase.sky.biomeName)) {
    		generateEnd(aWorld, aRandom, aX*16, aZ*16, aChunkGenerator, aChunkProvider);
    	} else {
    		generateSurface(aWorld, aRandom, aX*16, aZ*16, aChunkGenerator, aChunkProvider);
    	}
    }
    
    private void generateSurface(World aWorld, Random aRandom, int aX, int aZ, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
    	String tBiome = aWorld.getBiomeGenForCoords(aX+8, aZ+8).biomeName;
    	
    	if (sGeneratedOres[1])
    		for (int i = 0; i < 1; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 1, 8, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(32), aZ + aRandom.nextInt(16));
    	
    	if (sGeneratedOres[2]) if (aRandom.nextInt(100)<sIridiumProbability)
    		for (int i = 0; i < 1 + sIridiumProbability/100; i++)
    			placeSingleOreBlock(aWorld, GT_Mod.instance.mBlocks[2].blockID, 2, aX + aRandom.nextInt(16), aRandom.nextInt(99), aZ + aRandom.nextInt(16));
    	
    	if (sGeneratedOres[3]) if (GT_Mod.mRubyList.contains(tBiome))
    		for (int i = 0; i < 8; i++)
    			placeSingleOreBlock(aWorld, GT_Mod.instance.mBlocks[2].blockID, 3, aX + aRandom.nextInt(16), aRandom.nextInt(32), aZ + aRandom.nextInt(16));
    	
    	if (sGeneratedOres[4]) if (GT_Mod.mSapphireList.contains(tBiome))
    		for (int i = 0; i < 8; i++)
    			placeSingleOreBlock(aWorld, GT_Mod.instance.mBlocks[2].blockID, 4, aX + aRandom.nextInt(16), aRandom.nextInt(32), aZ + aRandom.nextInt(16));
    	
    	if (sGeneratedOres[5]) if (GT_Mod.mBauxiteList.contains(tBiome))
    		for (int i = 0; i < 2; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 5, 16, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(48), aZ + aRandom.nextInt(16));
    }
    
    private void generateNether(World aWorld, Random aRandom, int aX, int aZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    	int tQuantity;
    	tQuantity = (1+aRandom.nextInt(2))*(1+aRandom.nextInt(2))*(1+aRandom.nextInt(2))*(1+aRandom.nextInt(2));
    	if (sGeneratedOres[6])
    		for (int i = 0; i < tQuantity; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 6, tQuantity*2, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(64), aZ + aRandom.nextInt(16));
    	tQuantity = (1+aRandom.nextInt(2))*(1+aRandom.nextInt(4));
    	if (sGeneratedOres[7])
    		for (int i = 0; i < tQuantity; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 7, tQuantity*2, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), 64+aRandom.nextInt(64), aZ + aRandom.nextInt(16));
    	tQuantity = (1+aRandom.nextInt(2))*(1+aRandom.nextInt(2))*(1+aRandom.nextInt(4));
    	if (sGeneratedOres[8])
    		for (int i = 0; i < tQuantity; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 8, tQuantity*2, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), 32+aRandom.nextInt(64), aZ + aRandom.nextInt(16));
    }

    private void generateEnd(World aWorld, Random aRandom, int aX, int aZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    	if (sAsteroids && aRandom.nextInt(100) == 0) {
    		int tPosY = 10 + aRandom.nextInt(237);
    		if (aRandom.nextInt(25) == 0) {
    			new GT_MinableOreGenerator(Block.whiteStone.blockID, 0, 100+aRandom.nextInt(101), true).generate(aWorld, aRandom, aX + aRandom.nextInt(16), tPosY, aZ + aRandom.nextInt(16));
    			if (sGeneratedOres[9])
            		for (int i = 0; i < 5; i++)
            			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 9,16, true).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
            	if (sGeneratedOres[10])
            		for (int i = 0; i < 2; i++)
            			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,10, 4, true).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
             	if (sGeneratedOres[11])
            		for (int i = 0; i < 7; i++)
            			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,11, 8, true).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
             	if (sGeneratedOres[12])
            		for (int i = 0; i <12; i++)
            			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,12,16, true).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
    		}
    		for (int i = 0; i < 5; i++)
    			new GT_MinableOreGenerator(Block.whiteStone.blockID, 0, 30+aRandom.nextInt(31), true).generate(aWorld, aRandom, aX + aRandom.nextInt(16), tPosY+aRandom.nextInt(51)-25, aZ + aRandom.nextInt(16));
    		if (sGeneratedOres[9])
        		for (int i = 0; i < 5; i++)
        			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 9,12, true).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41)-20, aZ - 8 + aRandom.nextInt(24));
        	if (sGeneratedOres[10])
        		for (int i = 0; i < 1; i++)
        			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,10, 4, true).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41)-20, aZ - 8 + aRandom.nextInt(24));
         	if (sGeneratedOres[11])
        		for (int i = 0; i < 3; i++)
        			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,11, 8, true).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41)-20, aZ - 8 + aRandom.nextInt(24));
    	}
    	if (sGeneratedOres[9])
    		for (int i = 0; i < 4; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID, 9,16, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    	if (sGeneratedOres[10])
    		for (int i = 0; i < 1; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,10, 4, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    	if (sGeneratedOres[11])
    		for (int i = 0; i < 5; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,11, 8, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    	if (sGeneratedOres[12])
    		for (int i = 0; i < 8; i++)
    			new GT_MinableOreGenerator(GT_Mod.instance.mBlocks[2].blockID,12,16, false).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    }
    
    private void placeSingleOreBlock(World aWorld, int aID, int aMeta, int aX, int aY, int aZ) {
        Block block = Block.blocksList[aWorld.getBlockId(aX, aY, aZ)];
        if (block != null && (block.blockID == Block.netherrack.blockID || block.blockID == Block.whiteStone.blockID || block.isGenMineableReplaceable(aWorld, aX, aY, aZ)))
            aWorld.setBlockAndMetadata(aX, aY, aZ, aID, aMeta);
    }
    
}