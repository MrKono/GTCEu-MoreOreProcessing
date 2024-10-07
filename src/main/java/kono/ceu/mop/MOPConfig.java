package kono.ceu.mop;

import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MODID)
public class MOPConfig {

    @Config.Name("Difficulty Option")
    @Config.Comment("Options for difficulty")
    public static DifficultyOptions difficulty = new DifficultyOptions();

    public static class DifficultyOptions {

        @Config.Comment({ "Allows HotIngot to be cooled by throwing it into water.", "Default: false" })
        public boolean easyCooling = false;

        @Config.Comment({ "In cauldron, allow only 3 items to be cleaned in 1B.", "Default: true" })
        public boolean hardCleaning = true;

        @Config.Comment({ "Allows cleaning by water instead of a cauldron.", "Default: false" })
        public boolean easyCleaning = false;
    }
}
