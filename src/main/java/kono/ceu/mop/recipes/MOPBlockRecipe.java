package kono.ceu.mop.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN;
import static kono.ceu.mop.common.blocks.Casing.MOPBlockPrimitiveCasing.CasingType.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;

import kono.ceu.mop.common.blocks.Casing.MOPGearBoxCasing;
import kono.ceu.mop.common.blocks.Casing.MOPMetalCasing;
import kono.ceu.mop.common.blocks.Casing.MOPPipeCasing;
import kono.ceu.mop.common.blocks.MOPMetaBlocks;

public class MOPBlockRecipe {

    public static void init() {
        int amount = ConfigHolder.recipes.casingsPerCraft;

        // Bronze Plated Firebricks
        if (ConfigHolder.recipes.harderBrickRecipes) {
            ModHandler.addShapedRecipe("bronze_plated_firebricks",
                    MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING.getItemVariant(BRONZE_PLATED_FIREBRICKS),
                    "PbP", "PBP", "PbP",
                    'B', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS),
                    'P', new UnificationEntry(plateDouble, Materials.Bronze),
                    'b', new UnificationEntry(block, Materials.Bronze));
        } else {
            ModHandler.addShapedRecipe("bronze_plated_firebricks",
                    MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING.getItemVariant(BRONZE_PLATED_FIREBRICKS),
                    " P ", "PBP", " P ",
                    'P', new UnificationEntry(plateDouble, Materials.Bronze),
                    'B', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS));
        }

        // Factory Casing
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(STAINLESS_CLEAN))
                .fluidInputs(Materials.Iridium.getFluid(144))
                .outputs(MOPMetaBlocks.MOP_METAL_CASING.getItemVariant(MOPMetalCasing.CasingType.IRIDIUM))
                .duration(5 * 20).EUt(VA[LV]).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MOPMetaBlocks.MOP_METAL_CASING.getItemVariant(MOPMetalCasing.CasingType.IRIDIUM))
                .fluidInputs(Materials.Americium.getFluid(144))
                .outputs(MOPMetaBlocks.MOP_METAL_CASING.getItemVariant(MOPMetalCasing.CasingType.AMERICIUM))
                .duration(5 * 20).EUt(VA[LV]).buildAndRegister();

        // Gearbox Casing
        ModHandler.addShapedRecipe("gearbox_iridium",
                MOPMetaBlocks.MOP_GEAR_BOX_CASING.getItemVariant(MOPGearBoxCasing.CasingType.IRIDIUM, amount),
                "PhP", "GFG", "PwP",
                'P', new UnificationEntry(plate, Materials.Iridium),
                'G', new UnificationEntry(gear, Materials.Iridium),
                'F', new UnificationEntry(frameGt, Materials.Iridium));
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Iridium, 6)
                .input(gear, Materials.Iridium, 2)
                .input(frameGt, Materials.Iridium)
                .circuitMeta(4)
                .outputs(MOPMetaBlocks.MOP_GEAR_BOX_CASING.getItemVariant(MOPGearBoxCasing.CasingType.IRIDIUM, amount))
                .duration(5 * 20).EUt(VA[LV]).buildAndRegister();
        ModHandler.addShapedRecipe("gearbox_americium",
                MOPMetaBlocks.MOP_GEAR_BOX_CASING.getItemVariant(MOPGearBoxCasing.CasingType.AMERICIUM, amount),
                "PhP", "GFG", "PwP",
                'P', new UnificationEntry(plate, Materials.Americium),
                'G', new UnificationEntry(gear, Materials.Americium),
                'F', new UnificationEntry(frameGt, Materials.Americium));
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Americium, 6)
                .input(gear, Materials.Americium, 2)
                .input(frameGt, Materials.Americium)
                .circuitMeta(4)
                .outputs(
                        MOPMetaBlocks.MOP_GEAR_BOX_CASING.getItemVariant(MOPGearBoxCasing.CasingType.AMERICIUM, amount))
                .duration(5 * 20).EUt(VA[LV]).buildAndRegister();

        // Pipe Casing
        ModHandler.addShapedRecipe("pipe_casing_iridium",
                MOPMetaBlocks.MOP_PIPE_CASING.getItemVariant(MOPPipeCasing.CasingType.IRIDIUM, amount),
                "PNP", "NFN", "PNP",
                'P', new UnificationEntry(plate, Materials.Iridium),
                'N', new UnificationEntry(pipeNormalFluid, Materials.Iridium),
                'F', new UnificationEntry(frameGt, Materials.Iridium));
        ModHandler.addShapedRecipe("pipe_casing_americium",
                MOPMetaBlocks.MOP_PIPE_CASING.getItemVariant(MOPPipeCasing.CasingType.AMERICIUM, amount),
                "PNP", "NFN", "PNP",
                'P', new UnificationEntry(plate, Materials.Americium),
                'N', new UnificationEntry(pipeNormalItem, Materials.Americium),
                'F', new UnificationEntry(frameGt, Materials.Americium));
    }
}
