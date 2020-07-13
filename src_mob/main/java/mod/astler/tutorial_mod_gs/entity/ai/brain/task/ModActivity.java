package mod.astler.tutorial_mod_gs.entity.ai.brain.task;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModActivity {

    public static final DeferredRegister<Activity> ACTIVITIES = DeferredRegister.create(ForgeRegistries.ACTIVITIES,
            TutorialGSMod.MODID);

    public static final  RegistryObject<Activity> PANIC_AND_ALARM = ACTIVITIES.register("panic_and_alarm", () -> new Activity("panic_and_alarm"));
}
