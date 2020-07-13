package mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class KillerHouseConfig implements IFeatureConfig
{
    public final int chance;

    public KillerHouseConfig(int chance)
    {
        this.chance = chance;
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops)
    {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("chance"), ops.createInt(this.chance))));
    }

    public static KillerHouseConfig deserialize(Dynamic<?> dynamic)
    {
        int chance = dynamic.get("chance").asInt(0);
        return new KillerHouseConfig(chance);
    }
}