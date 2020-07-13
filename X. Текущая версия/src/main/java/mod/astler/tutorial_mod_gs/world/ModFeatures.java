package mod.astler.tutorial_mod_gs.world;

        import mod.astler.tutorial_mod_gs.TutorialGSMod;
        import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.KillerHouseStructure;
        import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config.KillerHouseConfig;
        import net.minecraft.world.gen.feature.Feature;
        import net.minecraft.world.gen.feature.NoFeatureConfig;
        import net.minecraftforge.fml.RegistryObject;
        import net.minecraftforge.registries.DeferredRegister;
        import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = new DeferredRegister<>(ForgeRegistries.FEATURES, TutorialGSMod.MODID);

    public static final RegistryObject<KillerHouseStructure> KILLER_HOUSE = REGISTER.register("killer_house", () -> new KillerHouseStructure(KillerHouseConfig::deserialize));
    public static final RegistryObject<SunAltarFeature> SUN_ALTAR = REGISTER.register("sun_altar", () -> new SunAltarFeature(NoFeatureConfig::deserialize));
}