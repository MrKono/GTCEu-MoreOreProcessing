package kono.ceu.mop.api;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import kono.ceu.mop.Tags;

public class MOPValues {

    public static final String MODNAME = Tags.MODNAME;
    public static final String MODID = Tags.MODID;

    public static @NotNull ResourceLocation mopId(String path) {
        return new ResourceLocation(MODID, path);
    }
}
