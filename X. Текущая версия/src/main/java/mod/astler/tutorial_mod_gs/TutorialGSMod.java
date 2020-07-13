package mod.astler.tutorial_mod_gs;

import mod.astler.tutorial_mod_gs.init.ModBlocks;
import mod.astler.tutorial_mod_gs.init.ModItems;
import mod.astler.tutorial_mod_gs.world.ModEventGenSubscriber;
import mod.astler.tutorial_mod_gs.world.ModStructurePieceType;
import mod.astler.tutorial_mod_gs.world.ModFeatures;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TutorialGSMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TutorialGSMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tutorial_mod_gs";

    public TutorialGSMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEventGenSubscriber::onInitBiomesGen);

        //...

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);

        //...

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModFeatures.REGISTER.register(bus);
        ModStructurePieceType.init();


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.SUN_DUST_GLASS, RenderType.func_228645_f_());
        RenderTypeLookup.setRenderLayer(ModBlocks.MOON_DUST_GLASS, RenderType.func_228645_f_());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity pe = event.getPlayer();

        MinecraftServer ms = pe.world.getServer();

        if (ms != null) {

            if (Config.COMMON.showWelcomeMsg.get()) {
                if (ms.isSinglePlayer()) {
                    String name = "[TUTORMOD] ";

                    ITextComponent bA = new StringTextComponent("(*_*) ").applyTextStyle(TextFormatting.YELLOW);

                    int i = 0;

                    for (char n : name.toCharArray()) {
                        if (i >= 15)
                            i = 0;

                        ITextComponent inner = new StringTextComponent("" + n).applyTextStyle(TextFormatting.fromColorIndex(i));
                        bA.appendSibling(inner);
                        i++;
                    }

                    ITextComponent msg = new StringTextComponent(I18n.format("hello_msg"))
                            .applyTextStyle(TextFormatting.AQUA);

                    bA.appendSibling(msg);

                    pe.sendMessage(bA);
                } else {
                    ITextComponent mp = new StringTextComponent("[TUTORMOD] Hello, friends!");
                    pe.sendMessage(mp);
                }
            }
        }
    }

    @SubscribeEvent
    public void handleMissingMappings(RegistryEvent.MissingMappings evt) {
        String type = evt.getName().toString();

        switch (type) {
            case "minecraft:block":
                for (Object mapping : evt.getAllMappings()) {
                    RegistryEvent.MissingMappings.Mapping trueMapping = (RegistryEvent.MissingMappings.Mapping) mapping;

                    if (trueMapping.key.getNamespace().equals(MODID)) {
                        switch (trueMapping.key.getPath()) {
                            case "sun_glass":
                                trueMapping.remap(ModBlocks.SUN_DUST_GLASS);
                                break;
                        }
                    }
                }
                break;
            case "minecraft:item":
                for (Object mapping : evt.getAllMappings()) {
                    RegistryEvent.MissingMappings.Mapping trueMapping = (RegistryEvent.MissingMappings.Mapping) mapping;
                    if (trueMapping.key.getNamespace().equals(MODID)) {
                        switch (trueMapping.key.getPath()) {
                            case "sun_glass":
                                trueMapping.remap(ModItems.SUN_DUST_GLASS);
                                break;
                        }
                    }
                }
        }
    }

//    @SubscribeEvent
//    public void onServerStarting(FMLServerStartingEvent event) {
//        // do something when the server starts
//        LOGGER.info("HELLO from server starting ForAstler");
//    }
}

    /*
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }


    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }*/