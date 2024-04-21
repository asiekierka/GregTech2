/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.ITickHandler
 *  cpw.mods.fml.common.registry.TickRegistry
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 */
package gregtechmod.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import gregtechmod.GT_Mod;
import gregtechmod.common.GT_Proxy;
import gregtechmod.common.GT_TickHandler;
import gregtechmod.common.render.GT_MetaItem_Renderer;
import gregtechmod.common.render.GT_Renderer;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class GT_Client
extends GT_Proxy {
    public static int renderID;

    @Override
    public void initialize() {
        super.initialize();
        if (GT_Mod.instance.mShowCapes && GT_Mod.instance.mOnline) {
            try {
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            InputStream stream = GT_Client.class.getResourceAsStream("gregtechmod/supporterlist.txt");
                            if (stream != null) {
                                Scanner tScanner = new Scanner(stream);
                                while (tScanner.hasNextLine()) {
                                    String tName = tScanner.nextLine();
                                    GT_Mod.mGregTechCapeList.add(tName);
                                }
                            }
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                    }
                }).start();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        TickRegistry.registerTickHandler((ITickHandler)new GT_TickHandler(), (Side)Side.CLIENT);
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/void.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/items.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/metaitems/materials.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/metaitems/dusts.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/metaitems/cells.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/metaitems/components.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/metaitems/liquids.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/metaitems/gasses.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/metaitems/plasma.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/terrain.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/blocks.png");
        MinecraftForgeClient.preloadTexture((String)"/gregtechmod/textures/blocks2.png");
        renderID = 1;
        new GT_Renderer();
    }

    @Override
    public int addArmor(String aPrefix) {
        return RenderingRegistry.addNewArmourRendererPrefix((String)aPrefix);
    }

    @Override
    public void registerMetaItemRendererForID(int aID) {
        MinecraftForgeClient.registerItemRenderer((int)aID, (IItemRenderer)new GT_MetaItem_Renderer());
    }

    @Override
    public void serverStart() {
    }

    @Override
    public void doSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
        float tFloat = 1.0f;
        String tString = "note.harp";
        if (aStack == null) {
            return;
        }
        int i = 0;
        while (true) {
            if (i >= GT_Mod.mSoundItems.size()) break;
            if (((ItemStack)GT_Mod.mSoundItems.get(i)).isItemEqual(aStack)) {
                tString = (String)GT_Mod.mSoundNames.get(i);
                break;
            }
            ++i;
        }
        if (tString.startsWith("random.explode")) {
            if (aStack.stackSize == 3) {
                tString = "random.fuse";
            } else if (aStack.stackSize == 2) {
                tString = "random.old_explode";
            }
        }
        if (tString.startsWith("streaming.")) {
            switch (aStack.stackSize) {
                case 1: {
                    tString = tString + "13";
                    break;
                }
                case 2: {
                    tString = tString + "cat";
                    break;
                }
                case 3: {
                    tString = tString + "blocks";
                    break;
                }
                case 4: {
                    tString = tString + "chirp";
                    break;
                }
                case 5: {
                    tString = tString + "far";
                    break;
                }
                case 6: {
                    tString = tString + "mall";
                    break;
                }
                case 7: {
                    tString = tString + "mellohi";
                    break;
                }
                case 8: {
                    tString = tString + "stal";
                    break;
                }
                case 9: {
                    tString = tString + "strad";
                    break;
                }
                case 10: {
                    tString = tString + "ward";
                    break;
                }
                case 11: {
                    tString = tString + "11";
                    break;
                }
                case 12: {
                    tString = tString + "wait";
                    break;
                }
                default: {
                    tString = tString + "wherearewenow";
                }
            }
        }
        if (tString.startsWith("note.")) {
            tFloat = (float)Math.pow(2.0, (double)(aStack.stackSize - 13) / 12.0);
        }
        if (tString.startsWith("streaming.")) {
            aWorld.playRecord(tString.substring(10, tString.length()), (int)aX, (int)aY, (int)aZ);
        } else {
            aWorld.playSoundEffect(aX, aY, aZ, tString, 3.0f, tFloat);
        }
    }
}

