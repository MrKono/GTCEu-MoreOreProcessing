package kono.ceu.mop;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import gregtech.GTInternalTags;

import kono.ceu.mop.api.MOPValues;
import kono.ceu.mop.common.CommonProxy;

@Mod(modid = MOPValues.MODID,
     name = MOPValues.MODNAME,
     acceptedMinecraftVersions = "[1.12, 1.12.2]",
     version = Tags.VERSION,
     dependencies = GTInternalTags.DEP_VERSION_STRING + "required-after:mixinbooter;")
@Mod.EventBusSubscriber(modid = MOPValues.MODID)
public class MoreOreProcessing {

    @SidedProxy(modId = MOPValues.MODID,
                clientSide = "kono.ceu.mop.client.ClientProxy",
                serverSide = "kono.ceu.mop.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static MoreOreProcessing instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
