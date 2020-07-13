package mod.astler.tutorial_mod_gs;

import mod.astler.tutorial_mod_gs.client.ClientEventBusSubscriber;
import mod.astler.tutorial_mod_gs.entity.ModEntityTypes;
import mod.astler.tutorial_mod_gs.entity.ai.brain.schedule.ModSchedule;
import mod.astler.tutorial_mod_gs.entity.ai.brain.task.ModActivity;
import mod.astler.tutorial_mod_gs.world.ModFeatures;
import mod.astler.tutorial_mod_gs.world.ModStructurePieceType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TutorialGSMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TutorialGSMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tutorial_mod_gs";

    public TutorialGSMod() {
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        MinecraftForge.EVENT_BUS.register(ClientEventBusSubscriber.class);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntityTypes.ENTITY_TYPES.register(bus);
        ModActivity.ACTIVITIES.register(bus);
        ModSchedule.SCHEDULES.register(bus);
        ModStructurePieceType.init();
        ModFeatures.REGISTER_FEATURES.register(bus);
        ModFeatures.REGISTER_STRUCTURES.register(bus);
    }

}