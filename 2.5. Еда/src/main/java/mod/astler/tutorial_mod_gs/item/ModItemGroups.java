package mod.astler.tutorial_mod_gs.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {
    public static final ItemGroup MOD_ITEMS_ITEM_GROUP = new ModItemGroup("tutorial_mod_gs_items", () -> new ItemStack(ModItems.ENCHANTED_SAUSAGE.get()));
}
