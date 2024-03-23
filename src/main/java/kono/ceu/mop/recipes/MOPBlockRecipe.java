package kono.ceu.mop.recipes;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import kono.ceu.mop.common.blocks.Casing.MOPBlockPrimitiveCasing;
import kono.ceu.mop.common.blocks.MOPMetaBlocks;

import static gregtech.api.recipes.RecipeMaps.*;

public class MOPBlockRecipe {

    public static void init() {
        registerPrimitiveCasing();
    }

    public static void registerPrimitiveCasing() {
        // Bronze Plated Firebricks
        ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS))
                .input(OrePrefix.plate, Materials.Bronze, 4)
                .outputs(MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING.getItemVariant(MOPBlockPrimitiveCasing.CasingType.BRONZE_PLATED_FIREBRICKS))
                .EUt(16).duration(300).buildAndRegister();
    }
}
