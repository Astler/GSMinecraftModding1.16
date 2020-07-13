package mod.astler.tutorial_mod_gs.init;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TutorialGSMod.MODID)
public class ModBlocks {
    @ObjectHolder("sun_lamp")
    public static Block SUN_LAMP;
    @ObjectHolder("sun_bricks")
    public static Block SUN_BRICKS;
    @ObjectHolder("sun_bricks_stairs")
    public static Block SUN_BRICKS_STAIRS;
    @ObjectHolder("sun_bricks_slab")
    public static Block SUN_BRICKS_SLAB;
    @ObjectHolder("sun_bricks_wall")
    public static Block SUN_BRICKS_WALL;
    @ObjectHolder("sun_dust_glass")
    public static Block SUN_DUST_GLASS;
    @ObjectHolder("moon_dust_glass")
    public static Block MOON_DUST_GLASS;
    @ObjectHolder("table_block")
    public static Block TABLE_BLOCK;
    @ObjectHolder("sun_ore")
    public static Block SUN_ORE;
}