package kono.ceu.mop;

import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MODID)
public class MOPConfig {

    @Config.Name("Difficulty Option")
    @Config.Comment("Options for difficulty")
    @Config.RequiresMcRestart
    public static DifficultyOptions difficulty = new DifficultyOptions();

    @Config.Name("ID Setting")
    @Config.Comment({ "Setting for MetaTileEntityID", "Use in case of duplicates",
            "WARNING: Changing the value may cause the machine to disappear " })
    @Config.RequiresMcRestart
    public static IdSetting id = new IdSetting();

    public static class DifficultyOptions {

        @Config.Comment({ "Allows HotIngot to be cooled by throwing it into water.", "Default: false" })
        public boolean easyCooling = false;

        @Config.Comment({ "In cauldron, allow only 3 items to be cleaned in 1B.", "Default: true" })
        public boolean hardCleaning = true;

        @Config.Comment({ "Allows cleaning by water instead of a cauldron.", "Default: false" })
        public boolean easyCleaning = false;
    }

    public static class IdSetting {

        @Config.Comment({ "Start ID for Single Block Machine", "Default: 23000" })
        public int startSingle = 23000;

        @Config.Comment({ "Start ID for Multiblock Machine", "Dafault: 24000" })
        public int startMulti = 24000;
    }
}
