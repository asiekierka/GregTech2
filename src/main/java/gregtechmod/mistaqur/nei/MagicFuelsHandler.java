/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.forge.GuiContainerManager
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRectHandler
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.liquids.LiquidContainerData
 *  net.minecraftforge.liquids.LiquidContainerRegistry
 *  net.minecraftforge.liquids.LiquidStack
 */
package gregtechmod.mistaqur.nei;

import codechicken.nei.PositionedStack;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtechmod.api.GT_Recipe;
import gregtechmod.common.gui.GT_GUIContainer_MagicEnergyConverter;
import gregtechmod.mistaqur.nei.GT_RecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class MagicFuelsHandler
extends GT_RecipeHandler {
    @Override
    public void loadTransferRects() {
        try {
            this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(94, 30, 18, 18), this.getRecipeId(), new Object[0]));
            ArrayList<Class<GT_GUIContainer_MagicEnergyConverter>> guis = new ArrayList<Class<GT_GUIContainer_MagicEnergyConverter>>();
            ArrayList<TemplateRecipeHandler.RecipeTransferRect> transferRects2 = new ArrayList<TemplateRecipeHandler.RecipeTransferRect>();
            guis.add(GT_GUIContainer_MagicEnergyConverter.class);
            transferRects2.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 18, 18), this.getRecipeId(), new Object[0]));
            TemplateRecipeHandler.RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Override
    public String getRecipeName() {
        return "Magic Energy Converter";
    }

    @Override
    public String getRecipeId() {
        return "gregtech.MagicFuels";
    }

    @Override
    public String getGuiTexture() {
        return "/gregtechmod/textures/gui/NEIFuel.png";
    }

    @Override
    public String getOverlayIdentifier() {
        return "gregtech.MagicFuels";
    }

    @Override
    public ArrayList getRecipeList() {
        return GT_Recipe.sMagicFuels;
    }

    @Override
    public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
        return new CachedMagicFuels(irecipe);
    }

    public void drawExtras(GuiContainerManager gui, int recipe) {
        CachedMagicFuels t = (CachedMagicFuels)((Object)this.arecipes.get(recipe));
        gui.drawText(30, 80, "EU: " + this.toNumber(t.mStartEU * 1000) + "EU", -16777216, false);
    }

    public class CachedMagicFuels
    extends GT_RecipeHandler.CachedGT_Recipe {
        public int mStartEU;

        public CachedMagicFuels(GT_Recipe aRecipe) {
            int xoffset = 4;
            int yoffset = 4;
            this.resources = new ArrayList();
            if (aRecipe.mInput1 != null) {
                LiquidStack tLiquid = LiquidContainerRegistry.getLiquidForFilledItem((ItemStack)aRecipe.mInput1);
                if (null != tLiquid) {
                    this.resources.add(new PositionedStack((Object)tLiquid.asItemStack(), 80 - xoffset, 35 - yoffset));
                    ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
                    for (LiquidContainerData tData : LiquidContainerRegistry.getRegisteredLiquidContainerData()) {
                        if (!tData.stillLiquid.isLiquidEqual(tLiquid)) continue;
                        tList.add(tData.filled);
                    }
                    if (tList.isEmpty()) {
                        this.resources.add(new PositionedStack((Object)aRecipe.mInput1, 61 - xoffset, 35 - yoffset));
                    } else {
                        this.resources.add(new PositionedStack(tList, 61 - xoffset, 35 - yoffset));
                    }
                } else {
                    this.resources.add(new PositionedStack((Object)aRecipe.mInput1, 61 - xoffset, 35 - yoffset));
                }
            }
            this.products = new ArrayList();
            this.mStartEU = aRecipe.mStartEU;
        }
    }
}

