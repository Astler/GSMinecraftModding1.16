package mod.astler.tutorial_mod_gs.client;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import mod.astler.tutorial_mod_gs.client.entity.render.GSVillagerRenderer;
import mod.astler.tutorial_mod_gs.entity.villager.GSVillagerEntity;
import mod.astler.tutorial_mod_gs.entity.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
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
    }

    @SubscribeEvent
    public static void onInitBiomesGen(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModEntityTypes.GS_VILLAGER.get(), 50, 3, 8));
        }
    }
}