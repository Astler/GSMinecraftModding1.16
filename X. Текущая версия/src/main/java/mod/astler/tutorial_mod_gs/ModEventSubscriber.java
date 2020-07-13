package mod.astler.tutorial_mod_gs;

import mod.astler.tutorial_mod_gs.blocks.ExpOreBlock;
import mod.astler.tutorial_mod_gs.init.ModBlocks;
import mod.astler.tutorial_mod_gs.init.MyModItemGroups;
import mod.astler.tutorial_mod_gs.items.GoldenSausage;
import mod.astler.tutorial_mod_gs.items.MyFoods;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = TutorialGSMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.SAUSAGE)), "sausage"),
                setup(new GoldenSausage(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.GOLDEN_SAUSAGE)), "golden_sausage"),

                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.SPEED_SAUSAGE)), "speed_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.SLOWNESS_SAUSAGE)), "slowness_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.HASTE_SAUSAGE)), "haste_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.MINING_FATIGUE_SAUSAGE)), "mining_fatigue_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.STRENGTH_SAUSAGE)), "strength_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.INSTANT_HEALTH_SAUSAGE)), "instant_health_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.INSTANT_DAMAGE_SAUSAGE)), "instant_damage_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.JUMP_BOOST_SAUSAGE)), "jump_boost_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.NAUSEA_SAUSAGE)), "nausea_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.REGENERATION_SAUSAGE)), "regeneration_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.RESISTANCE_SAUSAGE)), "resistance_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.FIRE_RESISTANCE_SAUSAGE)), "fire_resistance_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.WATER_BREATHING_SAUSAGE)), "water_breathing_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.INVISIBILITY_SAUSAGE)), "invisibility_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.BLINDNESS_SAUSAGE)), "blindness_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.NIGHT_VISION_SAUSAGE)), "night_vision_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.HUNGER_SAUSAGE)), "hunger_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.WEAKNESS_SAUSAGE)), "weakness_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.POISON_SAUSAGE)), "poison_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.WITHER_SAUSAGE)), "wither_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.HEALTH_BOOST_SAUSAGE)), "health_boost_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.ABSORPTION_SAUSAGE)), "absorption_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.SATURATION_SAUSAGE)), "saturation_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.GLOWING_SAUSAGE)), "glowing_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.LEVITATION_SAUSAGE)), "levitation_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.LUCK_SAUSAGE)), "luck_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.UNLUCK_SAUSAGE)), "unluck_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.SLOW_FALLING_SAUSAGE)), "slow_falling_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.CONDUIT_POWER_SAUSAGE)), "conduit_power_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.DOLPHINS_GRACE_SAUSAGE)), "dolphins_grace_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.BAD_OMEN_SAUSAGE)), "bad_omen_sausage"),
                setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP).food(MyFoods.HERO_OF_THE_VILLAGE_SAUSAGE)), "hero_of_the_village_sausage"),
                setup(new BlockItem(ModBlocks.SUN_BRICKS, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), "sun_bricks"),
                setup(new BlockItem(ModBlocks.SUN_LAMP, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), "sun_lamp"),
                setup(new BlockItem(ModBlocks.SUN_BRICKS_STAIRS, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), "sun_bricks_stairs"),
                setup(new BlockItem(ModBlocks.SUN_BRICKS_SLAB, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), "sun_bricks_slab"),
                setup(new BlockItem(ModBlocks.SUN_BRICKS_WALL, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), "sun_bricks_wall"),
                setup(new BlockItem(ModBlocks.SUN_DUST_GLASS, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), "sun_dust_glass"),
                setup(new BlockItem(ModBlocks.MOON_DUST_GLASS, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), "moon_dust_glass"),
                simpleBlockItem(ModBlocks.TABLE_BLOCK, "table_block"),
                simpleBlockItem(ModBlocks.SUN_ORE, "sun_ore"),
                simpleItem("sun_element")
        );
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)), "sun_bricks"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).lightValue(15)), "sun_lamp"),
                setup(new StairsBlock(() -> ModBlocks.SUN_BRICKS.getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "sun_bricks_stairs"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "sun_bricks_slab"),
                setup(new WallBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "sun_bricks_wall"),
                setup(new GlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).func_226896_b_()), "sun_dust_glass"),
                setup(new GlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).func_226896_b_()), "moon_dust_glass"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).func_226896_b_()), "table_block"),
                setup(new ExpOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F), 4, 20), "sun_ore")
        );
    }

    private Block simpleBlock(String name) {
        return setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.0F)), name);
    }

    private static Item simpleBlockItem(Block block, String name) {
        return setup(new BlockItem(block, new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), name);
    }

    private static Item simpleItem(String name) {
        return setup(new Item(new Item.Properties().group(MyModItemGroups.MOD_ITEM_GROUP)), name);
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(TutorialGSMod.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

    }

}
