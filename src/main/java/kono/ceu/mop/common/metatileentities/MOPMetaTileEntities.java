package kono.ceu.mop.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static kono.ceu.mop.api.MOPValues.mopId;

import kono.ceu.mop.common.metatileentities.multi.MetaTileEntityBronzePlatedBlastFurnace;

public class MOPMetaTileEntities {

    // Multi Machine
    public static MetaTileEntityBronzePlatedBlastFurnace BRONZE_PLATED_BLASE_FURNACE;

    public static void init() {
        // Multi Machine
        BRONZE_PLATED_BLASE_FURNACE = registerMetaTileEntity(24000,
                new MetaTileEntityBronzePlatedBlastFurnace(mopId("bronze_plated_blast_furnace")));
    }
}
