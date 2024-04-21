/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IConnectionHandler
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.Player
 *  net.minecraft.network.INetworkManager
 *  net.minecraft.network.NetLoginHandler
 *  net.minecraft.network.packet.NetHandler
 *  net.minecraft.network.packet.Packet1Login
 *  net.minecraft.server.MinecraftServer
 */
package gregtechmod.common;

import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;
import gregtechmod.GT_Mod;
import gregtechmod.common.GT_GUIHandler;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;

public class GT_ConnectionHandler
implements IConnectionHandler {
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
    }

    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
        return null;
    }

    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
    }

    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
    }

    public void connectionClosed(INetworkManager manager) {
    }

    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
        NetworkRegistry.instance().registerGuiHandler((Object)GT_Mod.instance, (IGuiHandler)new GT_GUIHandler());
        if (!GT_Mod.instance.mAlreadyPlayed) {
            clientHandler.getPlayer().addChatMessage("The GregTech-Addon is known for changing many Recipes.");
            clientHandler.getPlayer().addChatMessage("Please make sure to look them up (best via NEI), before");
            clientHandler.getPlayer().addChatMessage("complaining about missing Recipes of ANY kind or Mod, ");
            clientHandler.getPlayer().addChatMessage("especially the ones from IC2. I needed to nerf/remove some");
            clientHandler.getPlayer().addChatMessage("Recipes to prevent any exploits. Including Forestry Bronze");
            clientHandler.getPlayer().addChatMessage("now giving only 2 Ingots instead of 4, and our beloved Tin");
            clientHandler.getPlayer().addChatMessage("Buckets. And do NOT suggest to make it possible, to turn");
            clientHandler.getPlayer().addChatMessage("exploits back ON. I won't do that. ~ Gregorius Techneticies");
            try {
                GT_Mod.mLogFile.createNewFile();
            }
            catch (Throwable e) {
                // empty catch block
            }
        }
    }
}

