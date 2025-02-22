package kono.ceu.mop.client;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import gregtech.api.gui.resources.TextureArea;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import kono.ceu.mop.api.MOPValues;

@Mod.EventBusSubscriber(modid = MOPValues.MODID, value = Side.CLIENT)
public class MOPTextures {

    // GUI
    public static final TextureArea PRIMITIVE_SLOT_BRONZE = TextureArea
            .fullImage("textures/gui/primitive/bronze/bronze_primitive_slot.png");
    public static final TextureArea PRIMITIVE_DUST_OVERLAY_BRONZE = TextureArea
            .fullImage("textures/gui/primitive/bronze/overlay_bronze_primitive_dust.png");
    public static final TextureArea PRIMITIVE_INGOT_OVERLAY_BRONZE = TextureArea
            .fullImage("textures/gui/primitive/bronze/overlay_bronze_primitive_ingot.png");
    public static final TextureArea PRIMITIVE_FURNACE_OVERLAY_BRONZE = TextureArea
            .fullImage("textures/gui/primitive/bronze/overlay_bronze_primitive_furnace.png");
    public static final TextureArea PRIMITIVE_BACKGROUND_BRONZE = TextureArea
            .fullImage("textures/gui/primitive/bronze/primitive_bronze_background.png");
    public static final TextureArea BRONZE_PRIMITIVE_BLAST_FURNACE_PROGRESS_BAR = TextureArea
            .fullImage("textures/gui/primitive/bronze/progress_bar_bronze_primitive_blast_furnace.png");

    // Blocks
    public static SimpleOverlayRenderer BRONZE_FIREBRICKS;
    public static SimpleOverlayRenderer IRIDIUM_PLATED;
    public static SimpleOverlayRenderer AMERICIUM_PLATED;
    public static SimpleOverlayRenderer IRIDIUM_PIPE;
    public static SimpleOverlayRenderer AMERICIUM_PIPE;
    public static SimpleOverlayRenderer IRIDIUM_GEARBOX;
    public static SimpleOverlayRenderer AMERICIUM_GEARBOX;

    private MOPTextures() {}

    public static void preInit() {
        BRONZE_FIREBRICKS = new SimpleOverlayRenderer("primitive/bronze_plated_firebricks");
        IRIDIUM_PLATED = new SimpleOverlayRenderer("metal/iridium_plated");
        AMERICIUM_PLATED = new SimpleOverlayRenderer("metal/americium_plated");
        IRIDIUM_PIPE = new SimpleOverlayRenderer("pipe/pipe_iridium");
        AMERICIUM_PIPE = new SimpleOverlayRenderer("pipe/pipe_americium");
        IRIDIUM_GEARBOX = new SimpleOverlayRenderer("gearbox/gearbox_iridium");
        AMERICIUM_GEARBOX = new SimpleOverlayRenderer("gearbox/gearbox_americium");
    }
}
