package mod.astler.tutorial_mod_gs.item;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialGSMod.MODID);

    public static final RegistryObject<Item> STONE_STICK = ITEMS.register("stone_stick", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
}

