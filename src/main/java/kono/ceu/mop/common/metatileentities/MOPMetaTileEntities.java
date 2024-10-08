package kono.ceu.mop.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static kono.ceu.mop.api.MOPValues.mopId;

import kono.ceu.mop.MOPConfig;
import kono.ceu.mop.common.metatileentities.multi.MetaTileEntityBronzePlatedBlastFurnace;
import kono.ceu.mop.common.metatileentities.multi.MetaTileEntityBronzeReinforcedBlastFurnace;

public class MOPMetaTileEntities {

    // Multi Machine
    public static MetaTileEntityBronzePlatedBlastFurnace BRONZE_PLATED_BLASE_FURNACE;
    public static MetaTileEntityBronzeReinforcedBlastFurnace BRONZE_REINFORCED_BLAST_FURNACE;

    public static void init() {
        // Multi Machine
        int id = MOPConfig.id.startMulti;
        BRONZE_PLATED_BLASE_FURNACE = registerMetaTileEntity(id,
                new MetaTileEntityBronzePlatedBlastFurnace(mopId("bronze_plated_blast_furnace")));
        BRONZE_REINFORCED_BLAST_FURNACE = registerMetaTileEntity(++id,
                new MetaTileEntityBronzeReinforcedBlastFurnace(mopId("bronze_reinforced_blast_furnace")));
    }
}
