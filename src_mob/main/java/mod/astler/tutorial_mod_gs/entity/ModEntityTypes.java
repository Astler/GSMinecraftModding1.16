package mod.astler.tutorial_mod_gs.entity;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import mod.astler.tutorial_mod_gs.entity.villager.GSVillagerEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            TutorialGSMod.MODID);

    public static final RegistryObject<EntityType<GSVillagerEntity>> GS_VILLAGER = ENTITY_TYPES
            .register("gs_villager",
                    () -> EntityType.Builder.create(GSVillagerEntity::new, EntityClassification.CREATURE)
                            .size(0.6F, 1.8F)
                            .build(new ResourceLocation(TutorialGSMod.MODID, "gs_villager").toString()));

}
