package kono.ceu.mop.api.recipes;

import static gregtech.api.GTValues.*;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.mop.recipe.MOPRecipeMaps")
@ZenRegister
public class MOPRecipeMaps {

    @ZenProperty
    public static final RecipeMap<PrimitiveRecipeBuilder> BRONZE_REINFORCED_BLAST_FURNACE_RECIPES = new RecipeMap<>(
            "bronze_reinforced_blast_furnace", 3, 6, 0, 0, new PrimitiveRecipeBuilder(), false)
                    .setSound(GTSoundEvents.FIRE);

    public static final RecipeMap<SimpleRecipeBuilder> ORE_FACTORY_RECIPES = new RecipeMap<>(
            "ore_factory", 2, 12, 2, 0, new SimpleRecipeBuilder().EUt(VA[LV]), false)
                    .setSlotOverlay(false, false, GuiTextures.IN_SLOT_OVERLAY)
                    .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_MULTI_ENERGY_YELLOW, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.MACERATOR);
}
