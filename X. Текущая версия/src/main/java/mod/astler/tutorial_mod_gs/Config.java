package mod.astler.tutorial_mod_gs;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config
{
    public static class Common
    {
        public final ForgeConfigSpec.IntValue sunAltarGenerateChance;
        public final ForgeConfigSpec.IntValue killerHouseGenerateChance;
        public final ForgeConfigSpec.BooleanValue showWelcomeMsg;

        Common(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Common").push("common");
            {
                builder.comment("Structures").push("structures");
                {
                    this.sunAltarGenerateChance = builder.comment("Sun altar generation chance.").defineInRange("sunAltarGenerateChance", 15, 1, Integer.MAX_VALUE);
                    this.killerHouseGenerateChance = builder.comment("Killer House generation chance.").defineInRange("killerHouseGenerateChance", 40, 1, Integer.MAX_VALUE);
                }
                builder.pop();
                builder.comment("Other").push("other");
                {
                    this.showWelcomeMsg = builder.comment("Show greetings message.").define("showWelcomeMsg", true);
                }
                builder.pop();
            }
            builder.pop();
        }
    }

    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static
    {
        final Pair<Common, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Config.Common::new);
        commonSpec = clientSpecPair.getRight();
        COMMON = clientSpecPair.getLeft();
    }
}