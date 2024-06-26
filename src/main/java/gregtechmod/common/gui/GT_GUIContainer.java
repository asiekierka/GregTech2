/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 */
package gregtechmod.common.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class GT_GUIContainer
extends GuiContainer {
    public boolean mCrashed = false;

    public GT_GUIContainer(Container aContainer) {
        super(aContainer);
    }

    public String toNumber(int aNumber) {
        String tString = "";
        boolean temp = true;
        boolean negative = false;
        if (aNumber < 0) {
            aNumber *= -1;
            negative = true;
        }
        for (int i = 1000000000; i > 0; i /= 10) {
            int tDigit = aNumber / i % 10;
            if (temp && tDigit != 0) {
                temp = false;
            }
            if (temp) continue;
            tString = tString + tDigit;
            if (i == 1) continue;
            for (int j = i; j > 0; j /= 1000) {
                if (j != 1) continue;
                tString = tString + ",";
            }
        }
        if (tString.equals("")) {
            tString = "0";
        }
        return negative ? "-" + tString : tString;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    }

    public void drawScreen(int par1, int par2, float par3) {
        try {
            super.drawScreen(par1, par2, par3);
        }
        catch (Throwable e) {
            Tessellator.instance.isDrawing = false;
        }
    }

    protected void drawSlotInventory(Slot par1Slot) {
        block2: {
            try {
                super.drawSlotInventory(par1Slot);
            }
            catch (Throwable e) {
                Tessellator.instance.isDrawing = false;
                if (this.mCrashed) break block2;
                System.out.println("Clientside Slot drawing Crash prevented. Seems one Itemstack causes Problems with negative Damage Values. This is absolutely NOT a Bug of the GregTech-Addon, so don't even think about reporting it to me, it's a Bug of the Mod, which belongs to the almost-crash-causing Item, so bug that Mods Author and not me! Did you hear it? NOT ME!!!");
                e.printStackTrace();
                this.mCrashed = true;
            }
        }
    }
}

