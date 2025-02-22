package kono.ceu.mop.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.api.unification.material.event.MaterialEvent;

import kono.ceu.mop.Tags;
import kono.ceu.mop.api.unification.material.MOPMaterialFlagAddition;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class MOPEventHandler {

    public MOPEventHandler() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterialsHigh(MaterialEvent event) {
        MOPMaterialFlagAddition.init();
    }
}
