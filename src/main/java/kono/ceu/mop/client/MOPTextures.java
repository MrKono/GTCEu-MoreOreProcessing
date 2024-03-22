package kono.ceu.mop.client;

import gregtech.api.gui.resources.TextureArea;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

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
    public static SimpleOverlayRenderer BRONZE_FIREBRICK;

    public static void textures() {
        BRONZE_FIREBRICK = new SimpleOverlayRenderer("blocks/primitive/bronze_firebrick");
    }
}
