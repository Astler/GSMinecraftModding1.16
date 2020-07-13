package mod.astler.tutorial_mod_gs.init;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MyModItemGroups {
    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(TutorialGSMod.MODID, () -> new ItemStack(ModItems.SAUSAGE));
}
