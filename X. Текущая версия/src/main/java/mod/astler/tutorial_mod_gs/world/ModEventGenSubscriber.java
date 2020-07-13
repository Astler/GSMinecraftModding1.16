package mod.astler.tutorial_mod_gs.world;

import mod.astler.tutorial_mod_gs.Config;
import mod.astler.tutorial_mod_gs.init.ModBlocks;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config.KillerHouseConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEventGenSubscriber {
    public static int veinSize = 20;
    public static int veinCount = 15;
    public static int heightMin = 0;
    public static int heightBase = 0;
    public static int heightMax = 120;

    @SubscribeEvent
    public static void onInitBiomesGen(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(
                    GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SUN_ORE.getDefaultState(), veinSize)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(veinCount, heightMin, heightBase, heightMax)))
            );

            biome.func_226711_a_(ModFeatures.KILLER_HOUSE.get().func_225566_b_(new KillerHouseConfig(Config.COMMON.killerHouseGenerateChance.get())));
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModFeatures.KILLER_HOUSE.get().func_225566_b_(new KillerHouseConfig(Config.COMMON.killerHouseGenerateChance.get())).func_227228_a_(Placement.NOPE.func_227446_a_(IPlacementConfig.NO_PLACEMENT_CONFIG)));

            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModFeatures.SUN_ALTAR.get().func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.CHANCE_HEIGHTMAP.func_227446_a_(new ChanceConfig(Config.COMMON.sunAltarGenerateChance.get()))));
        }
    }
}
