package mod.astler.tutorial_mod_gs.entity.ai.brain;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.ai.brain.schedule.ScheduleBuilder;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.village.PointOfInterestType.getAllStates;

public class ModPOI {

    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES,
            TutorialGSMod.MODID);

    public static final  RegistryObject<PointOfInterestType> WORKBENCH_POINT = POI.register("workbench", () -> new PointOfInterestType("workbench", getAllStates(Blocks.CRAFTING_TABLE), 1, 1));
}
