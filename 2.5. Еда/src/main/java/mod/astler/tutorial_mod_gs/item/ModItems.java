package mod.astler.tutorial_mod_gs.item;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialGSMod.MODID);

    public static final RegistryObject<Item> SAUSAGE = ITEMS.register("sausage", () -> new Item(new Item.Properties().food(ModFoods.SAUSAGE).group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));

    public static final RegistryObject<Item> ENCHANTED_SAUSAGE = ITEMS.register("enchanted_sausage", () -> new Item(new Item.Properties().food(ModFoods.ENCHANTED_SAUSAGE).group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)) {
        @Override
        public boolean hasEffect(ItemStack stack) {
            return true;
        }
    });
}

