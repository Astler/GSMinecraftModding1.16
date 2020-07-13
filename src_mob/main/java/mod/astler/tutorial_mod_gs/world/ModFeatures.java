package mod.astler.tutorial_mod_gs.world;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.KillerHouseStructure;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.SingleHouseStructure;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config.SimpleHouseConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {
    public static final DeferredRegister<Structure<?>> REGISTER_STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, TutorialGSMod.MODID);
    public static final DeferredRegister<Feature<?>> REGISTER_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TutorialGSMod.MODID);

    public static final RegistryObject<KillerHouseStructure> KILLER_HOUSE = REGISTER_STRUCTURES.register("killer_house", () -> new KillerHouseStructure(SimpleHouseConfig.simpleHouseConfig));
    public static final RegistryObject<SingleHouseStructure> SIMPLE_HOUSE = REGISTER_STRUCTURES.register("simple_house", () -> new SingleHouseStructure(NoFeatureConfig.field_236558_a_));

}