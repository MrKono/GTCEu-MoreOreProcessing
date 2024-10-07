package kono.ceu.mop.recipes;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS;
import static kono.ceu.mop.common.blocks.Casing.MOPBlockPrimitiveCasing.CasingType.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import kono.ceu.mop.common.blocks.MOPMetaBlocks;

public class MOPBlockRecipe {

    public static void init() {
        // Bronze Plated Firebricks
        if (ConfigHolder.recipes.harderBrickRecipes) {
            ModHandler.addShapedRecipe("bronze_plated_firebricks",
                    MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING.getItemVariant(BRONZE_PLATED_FIREBRICKS),
                    "BPB", "BbB", "PBP",
                    'B', MetaItems.FIRECLAY_BRICK.getStackForm(),
                    'P', new UnificationEntry(plateDouble, Materials.Bronze),
                    'b', new UnificationEntry(block, Materials.Bronze));
        } else {
            ModHandler.addShapedRecipe("bronze_plated_firebricks",
                    MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING.getItemVariant(BRONZE_PLATED_FIREBRICKS),
                    " P ", "PBP", " P ",
                    'P', new UnificationEntry(plateDouble, Materials.Bronze),
                    'B', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS));
        }
    }
}
