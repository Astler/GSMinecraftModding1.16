package mod.astler.tutorial_mod_gs.item;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {
    public static Food SAUSAGE = (new Food.Builder()).hunger(5).saturation(0.5F).meat().build();
    public static Food ENCHANTED_SAUSAGE = (new Food.Builder()).hunger(8).saturation(0.7F).meat().setAlwaysEdible().fastToEat().effect(new EffectInstance(Effects.HEALTH_BOOST, 600, 0), 1F).effect(new EffectInstance(Effects.INSTANT_HEALTH, 600, 0), 1F).build();
}
