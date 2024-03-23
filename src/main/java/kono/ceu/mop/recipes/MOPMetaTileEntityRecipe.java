package kono.ceu.mop.recipes;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.metatileentities.MetaTileEntities;
import kono.ceu.mop.common.metatileentities.MOPMetaTileEntities;

public class MOPMetaTileEntityRecipe {

    public static void init() {
        registerMulti();
    }

    public static void registerMulti() {
        ModHandler.addShapedRecipe(true, "bronze_plated_blast_furnace",
                MOPMetaTileEntities.BRONZE_PLATED_BLASE_FURNACE.getStackForm(), "dPh", "PFP", "SPS",
                'P', new UnificationEntry(OrePrefix.plate, Materials.Bronze),
                'F', MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm(),
                'S', new UnificationEntry(OrePrefix.screw, Materials.Bronze));
    }
}
