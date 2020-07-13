package mod.astler.tutorial_mod_gs.entity.ai.brain.schedule;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.ai.brain.schedule.ScheduleBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSchedule {

    public static final DeferredRegister<Schedule> SCHEDULES = DeferredRegister.create(ForgeRegistries.SCHEDULES,
            TutorialGSMod.MODID);

    public static final  RegistryObject<Schedule> GS_VILLAGER_DEFAULT = SCHEDULES.register("gs_villager_default", () -> new ScheduleBuilder(new Schedule()).add(10,Activity.IDLE).add(2000,Activity.WORK).add(9000,Activity.MEET).add(11000,Activity.IDLE).add(12000,Activity.REST).build());
}
