package kono.ceu.mop.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;
import static kono.ceu.mop.common.blocks.Casing.MOPBlockPrimitiveCasing.CasingType.BRONZE_PLATED_FIREBRICKS;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.metatileentities.MetaTileEntities;

import kono.ceu.mop.common.blocks.MOPMetaBlocks;
import kono.ceu.mop.common.metatileentities.MOPMetaTileEntities;

public class MOPMetaTileEntityRecipe {

    public static void init() {
        registerMulti();
    }

    public static void registerMulti() {
        // Bronze Plated Blast Furnace
        ModHandler.addShapedRecipe(true, "bronze_plated_blast_furnace",
                MOPMetaTileEntities.BRONZE_PLATED_BLASE_FURNACE.getStackForm(), "dPh", "PFP", "SPS",
                'P', new UnificationEntry(plate, Materials.Bronze),
                'F', MOPMetaBlocks.MOP_BLOCK_PRIMITIVE_CASING.getItemVariant(BRONZE_PLATED_FIREBRICKS),
                'S', new UnificationEntry(screw, Materials.Bronze));

        // Bronze Reinforced Blast Furnace
        ModHandler.addShapedRecipe(true, "bronze_reinforced_from_pbf",
                MOPMetaTileEntities.BRONZE_REINFORCED_BLAST_FURNACE.getStackForm(),
                "PwP", "PFP", "PhP",
                'P', new UnificationEntry(plateDouble, Materials.Bronze),
                'F', MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm());
        ModHandler.addShapedRecipe(true, "bronze_reinforced_from_bpf",
                MOPMetaTileEntities.BRONZE_REINFORCED_BLAST_FURNACE.getStackForm(),
                "PwP", "PFP", "PhP",
                'P', new UnificationEntry(plate, Materials.Bronze),
                'F', MOPMetaTileEntities.BRONZE_PLATED_BLASE_FURNACE.getStackForm());
    }
}
