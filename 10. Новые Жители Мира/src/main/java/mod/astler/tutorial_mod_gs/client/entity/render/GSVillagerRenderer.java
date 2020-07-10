package mod.astler.tutorial_mod_gs.client.entity.render;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import mod.astler.tutorial_mod_gs.entity.villager.GSVillagerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GSVillagerRenderer extends MobRenderer<GSVillagerEntity, BipedModel<GSVillagerEntity>> {
    private static final ResourceLocation COW_TEXTURES = new ResourceLocation(TutorialGSMod.MODID, "textures/entity/villagers/hunter_1.png");

    public GSVillagerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BipedModel<>(0.5F), 0.5F);
    }

    public ResourceLocation getEntityTexture(GSVillagerEntity entity) {
        return COW_TEXTURES;
    }
}