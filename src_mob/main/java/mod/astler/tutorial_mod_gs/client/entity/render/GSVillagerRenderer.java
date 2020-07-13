package mod.astler.tutorial_mod_gs.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.astler.tutorial_mod_gs.TutorialGSMod;
import mod.astler.tutorial_mod_gs.client.renderer.entity.model.GSVillagerModel;
import mod.astler.tutorial_mod_gs.entity.villager.GSVillagerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GSVillagerRenderer extends MobRenderer<GSVillagerEntity, GSVillagerModel> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(TutorialGSMod.MODID, "textures/entity/villagers/hunter_1.png");

    public GSVillagerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GSVillagerModel(0.0F, false), 0.5F);
    }

    public ResourceLocation getEntityTexture(GSVillagerEntity entity) {
        ResourceLocation texture = new ResourceLocation(TutorialGSMod.MODID, "textures/entity/" + entity.getGsVillagerData().getTexture() +".png");

        return texture;
    }

    protected void preRenderCallback(VillagerEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.9375F;
        if (entitylivingbaseIn.isChild()) {
            f = (float)((double)f * 0.5D);
            this.shadowSize = 0.25F;
        } else {
            this.shadowSize = 0.5F;
        }

        matrixStackIn.scale(f, f, f);
    }
}