package kono.ceu.mop.api.unification.material;

import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.unification.material.Materials;

public class MOPMaterialFlagAddition {

    public static void init() {
        Materials.Americium.addFlags(GENERATE_GEAR, GENERATE_FRAME);
    }
}
