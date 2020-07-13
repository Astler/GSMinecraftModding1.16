package mod.astler.tutorial_mod_gs.item;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialGSMod.MODID);

    public static final RegistryObject<Item> STONE_STICK = ITEMS.register("stone_stick", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));

    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(ModItemTier.STEEL, 6.0F, -3.1F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ShovelItem(ModItemTier.STEEL, 1.5F, -3.0F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new SwordItem(ModItemTier.STEEL, 3, -2.4F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new PickaxeItem(ModItemTier.STEEL, 1, -2.8F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new HoeItem(ModItemTier.STEEL, -2, -1F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));

    public static final RegistryObject<Item> MOON_INGOT = ITEMS.register("moon_ingot", () -> new Item(new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));

    public static final RegistryObject<Item> MOON_AXE = ITEMS.register("moon_axe", () -> new AxeItem(ModItemTier.MOON_STONE, 6.0F, -3.1F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> MOON_SHOVEL = ITEMS.register("moon_shovel", () -> new ShovelItem(ModItemTier.MOON_STONE, 1.5F, -3.0F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> MOON_SWORD = ITEMS.register("moon_sword", () -> new SwordItem(ModItemTier.MOON_STONE, 3, -2.4F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> MOON_PICKAXE = ITEMS.register("moon_pickaxe", () -> new PickaxeItem(ModItemTier.MOON_STONE, 1, -2.8F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));
    public static final RegistryObject<Item> MOON_HOE = ITEMS.register("moon_hoe", () -> new HoeItem(ModItemTier.MOON_STONE, -2, -1F, new Item.Properties().group(ModItemGroups.MOD_ITEMS_ITEM_GROUP)));

}