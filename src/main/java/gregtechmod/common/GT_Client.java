package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.common.render.GT_Renderer;

import java.net.URL;
import java.util.Scanner;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class GT_Client extends GT_Proxy {
	
	public static int renderID;

	public void initialize() {
		super.initialize();
		addCapeLists();
		TickRegistry.registerTickHandler(new GT_TickHandler(), Side.CLIENT);
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/void.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/items.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/metaitems/materials.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/metaitems/dusts.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/metaitems/cells.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/metaitems/components.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/metaitems/liquids.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/metaitems/gasses.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/metaitems/plasma.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/terrain.png");
		MinecraftForgeClient.preloadTexture("/gregtechmod/textures/blocks.png");
		//RenderingRegistry.registerBlockHandler(new GT_Block_Renderer());
		renderID = 1;
		new GT_Renderer();
		//TextureFXManager.instance().addAnimation(new GT_Animated_Texturerenderer(25, 16, "/gregtechmod/textures/terrain.png", 1, 4));
		//TextureFXManager.instance().addAnimation(new GT_Animated_Texturerenderer(41, 16, "/gregtechmod/textures/terrain.png", 1, 4));
	}

	public int addArmor(String aPrefix) {
		return RenderingRegistry.addNewArmourRendererPrefix(aPrefix);
	}
	
	public void serverStart() {
		
	}
	
    public void doSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
    	float tFloat = 1.0F;
    	String tString = "note.harp";
    	
    	if (aStack == null) return;
    		
	    for (int i = 0; i < GT_Mod.instance.mSoundItems.size(); i++) {
	    	if (GT_Mod.instance.mSoundItems.get(i).isItemEqual(aStack)) {
	    		tString = GT_Mod.instance.mSoundNames.get(i);
	    		break;
	    	}
	    }
    	
    	if (tString.startsWith("random.explode")) {
    		if (aStack.stackSize==3) {
    			tString = "random.fuse";
    		} else if (aStack.stackSize==2) {
    			tString = "random.old_explode";
    		}
    	}

    	if (tString.startsWith("streaming.")) {
    		switch (aStack.stackSize) {
    		case  1:
    			tString += "13";
    			break;
    		case  2:
    			tString += "cat";
    			break;
    		case  3:
    			tString += "blocks";
    			break;
    		case  4:
    			tString += "chirp";
    			break;
    		case  5:
    			tString += "far";
    			break;
    		case  6:
    			tString += "mall";
    			break;
    		case  7:
    			tString += "mellohi";
    			break;
    		case  8:
    			tString += "stal";
    			break;
    		case  9:
    			tString += "strad";
    			break;
    		case 10:
    			tString += "ward";
    			break;
    		case 11:
    			tString += "11";
    			break;
    		case 12:
    			tString += "wait";
    			break;
    		default:
    			tString += "wherearewenow";
    			break;
    		}
    	}
    	
    	if (tString.startsWith("note.")) {
    		tFloat = (float)Math.pow(2.0D, (double)(aStack.stackSize - 13) / 12.0D);
    	}
    	
    	if (tString.startsWith("streaming.")) {
    		aWorld.playRecord(tString.substring(10, tString.length()), (int)aX, (int)aY, (int)aZ);
    	} else {
    		aWorld.playSoundEffect(aX, aY, aZ, tString, 3.0F, tFloat);
    	}
    }
    
    private void addCapeLists() {
        try {
            Scanner tScanner = new Scanner(new URL("https://dl.dropbox.com/u/88825306/CapeList.txt").openStream());
            while (tScanner.hasNextLine()) {
            	String tName = tScanner.nextLine();
            	if (!GT_Mod.mGregTechCapeList.contains(tName))
            		GT_Mod.mGregTechCapeList.add(tName);
            }
        } catch(Throwable e) {}
    }
}
