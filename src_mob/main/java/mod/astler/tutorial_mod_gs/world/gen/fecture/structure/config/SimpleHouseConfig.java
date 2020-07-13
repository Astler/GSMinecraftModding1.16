package mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class SimpleHouseConfig implements IFeatureConfig
{
    public static final Codec<SimpleHouseConfig> simpleHouseConfig =
            RecordCodecBuilder.create((p_236530_0_) ->
                    p_236530_0_.group(Codec.INT.fieldOf("chance").withDefault(400).forGetter((p_236529_0_) ->
                            p_236529_0_.chance)).apply(p_236530_0_, SimpleHouseConfig::new));

    public final int chance;

    public SimpleHouseConfig(int chance)
    {
        this.chance = chance;
    }
}