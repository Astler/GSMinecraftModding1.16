package mod.astler.tutorial_mod_gs;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TutorialGSMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TutorialGSMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tutorial_mod_gs";

    public TutorialGSMod() { }
}