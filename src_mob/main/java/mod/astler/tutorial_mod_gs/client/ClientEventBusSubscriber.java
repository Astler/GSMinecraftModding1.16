package mod.astler.tutorial_mod_gs.client;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import mod.astler.tutorial_mod_gs.client.entity.render.GSVillagerRenderer;
import mod.astler.tutorial_mod_gs.entity.ModEntityTypes;
import mod.astler.tutorial_mod_gs.entity.villager.GSVillagerEntity;
import mod.astler.tutorial_mod_gs.world.ModFeatures;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config.SimpleHouseConfig;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TutorialGSMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GS_VILLAGER.get(), GSVillagerRenderer::new);
        GlobalEntityTypeAttributes.put(ModEntityTypes.GS_VILLAGER.get(), GSVillagerEntity.func_234188_eI_().func_233813_a_());
        Structure.field_236365_a_.put("killer_house", ModFeatures.KILLER_HOUSE.get());
    }

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinWorldEvent event) {

        if (!(event.getEntity() instanceof ZombieEntity))
            return;

        ZombieEntity ze = (ZombieEntity) event.getEntity();

        //ze.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Blocks.DIRT));

        ze.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(ze, GSVillagerEntity.class, true));
    }

    @SubscribeEvent
    public static void onInitBiomesGen(FMLCommonSetupEvent event) {

        for (Biome biome : ForgeRegistries.BIOMES) {

            StructureFeature<SimpleHouseConfig, ? extends Structure<SimpleHouseConfig>> KILLER_HOUSE_FEATURE = ModFeatures.KILLER_HOUSE.get().func_236391_a_(new SimpleHouseConfig(25));
       StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> singleHouseFeature = ModFeatures.SIMPLE_HOUSE.get().func_236391_a_(new NoFeatureConfig());


            biome.func_235063_a_(KILLER_HOUSE_FEATURE);
            biome.func_235063_a_(singleHouseFeature);
          //  biome.func_235063_a_(ModFeatures.VILLAGER_HOUSE.get().func_236391_a_(new SimpleHouseConfig(Config.COMMON.singleHousesGenerateChance.get())));

          //  biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, ModFeatures.SUN_ALTAR.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP.configure(new ChanceConfig(Config.COMMON.sunAltarGenerateChance.get()))));

            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityTypes.GS_VILLAGER.get(), 50, 3, 8));
        }
    }
}