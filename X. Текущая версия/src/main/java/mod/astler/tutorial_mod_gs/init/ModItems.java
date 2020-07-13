package mod.astler.tutorial_mod_gs.init;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TutorialGSMod.MODID)
public class ModItems {
    @ObjectHolder("sausage")
    public static Item SAUSAGE;
    @ObjectHolder("sun_dust_glass")
    public static Item SUN_DUST_GLASS;
    @ObjectHolder("moon_dust_glass")
    public static Item MOON_DUST_GLASS;
}