/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  shedar.mods.ic2.nuclearcontrol.api.CardState
 *  shedar.mods.ic2.nuclearcontrol.api.ICardWrapper
 *  shedar.mods.ic2.nuclearcontrol.api.IPanelDataSource
 *  shedar.mods.ic2.nuclearcontrol.api.IRemoteSensor
 *  shedar.mods.ic2.nuclearcontrol.api.PanelSetting
 *  shedar.mods.ic2.nuclearcontrol.api.PanelString
 *  shedar.mods.ic2.nuclearcontrol.panel.CardWrapperImpl
 */
package gregtechmod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.GT_Mod;
import gregtechmod.api.IGregTechDeviceInformation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import shedar.mods.ic2.nuclearcontrol.api.CardState;
import shedar.mods.ic2.nuclearcontrol.api.ICardWrapper;
import shedar.mods.ic2.nuclearcontrol.api.IPanelDataSource;
import shedar.mods.ic2.nuclearcontrol.api.IRemoteSensor;
import shedar.mods.ic2.nuclearcontrol.api.PanelSetting;
import shedar.mods.ic2.nuclearcontrol.api.PanelString;
import shedar.mods.ic2.nuclearcontrol.panel.CardWrapperImpl;

public class GT_SensorCard_Item
extends Item
implements IRemoteSensor,
IPanelDataSource {
    private static final String HINT_TEMPLATE = "x: %d, y: %d, z: %d";
    public static final int DISPLAY_MAIN = 1;
    public static final int DISPLAY_SECOND = 2;
    public static final int DISPLAY_TERTIARY = 4;
    public static final UUID CARD_TYPE = new UUID(0L, 41L);

    public GT_SensorCard_Item(int aID) {
        super(aID);
        this.setMaxStackSize(1);
        this.setCreativeTab(GT_Mod.tabGregTech);
    }

    public String getTextureFile() {
        return "/gregtechmod/textures/items.png";
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
        aList.add("Insert into Display Panel");
        CardWrapperImpl helper = new CardWrapperImpl(aStack);
        ChunkCoordinates target = helper.getTarget();
        if (target != null) {
            String title = helper.getTitle();
            if (title != null && !title.isEmpty()) {
                aList.add(title);
            }
            String hint = String.format(HINT_TEMPLATE, target.posX, target.posY, target.posZ);
            aList.add(hint);
        }
    }

    public CardState update(TileEntity panel, ICardWrapper card, int maxRange) {
        ChunkCoordinates target = card.getTarget();
        TileEntity tTileEntity = panel.worldObj.getBlockTileEntity(target.posX, target.posY, target.posZ);
        if (tTileEntity != null && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
            card.setString("mString1", ((IGregTechDeviceInformation)tTileEntity).getMainInfo());
            card.setString("mString2", ((IGregTechDeviceInformation)tTileEntity).getSecondaryInfo());
            card.setString("mString3", ((IGregTechDeviceInformation)tTileEntity).getTertiaryInfo());
            return CardState.OK;
        }
        return CardState.NO_TARGET;
    }

    public List getStringData(int displaySettings, ICardWrapper card, boolean showLabels) {
        PanelString line;
        LinkedList<PanelString> result = new LinkedList<PanelString>();
        if ((displaySettings & 1) != 0) {
            line = new PanelString();
            line.textLeft = card.getString("mString1");
            result.add(line);
        }
        if ((displaySettings & 2) != 0) {
            line = new PanelString();
            line.textLeft = card.getString("mString2");
            result.add(line);
        }
        if ((displaySettings & 4) != 0) {
            line = new PanelString();
            line.textLeft = card.getString("mString3");
            result.add(line);
        }
        return result;
    }

    public List getSettingsList() {
        ArrayList<PanelSetting> result = new ArrayList<PanelSetting>(3);
        result.add(new PanelSetting("Primary", 1, CARD_TYPE));
        result.add(new PanelSetting("Secondary", 2, CARD_TYPE));
        result.add(new PanelSetting("Tertiary", 4, CARD_TYPE));
        return result;
    }

    public UUID getCardType() {
        return CARD_TYPE;
    }

    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
    }
}

