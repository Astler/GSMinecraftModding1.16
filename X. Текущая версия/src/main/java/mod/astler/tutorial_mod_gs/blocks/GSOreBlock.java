package mod.astler.tutorial_mod_gs.blocks;

import mod.astler.tutorial_mod_gs.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import java.util.Random;

public class GSOreBlock extends Block {

    public static int veinSize = 20;
    public static int veinCount = 15;
    public static int heightMin = 5;
    public static int heightBase = 30;
    public static int heightMax = 60;

    public GSOreBlock(Block.Properties properties) {
        super(properties);
    }

    protected int getExperience(Random p_220281_1_) {
         return this == ModBlocks.SUN_ORE ? MathHelper.nextInt(p_220281_1_, 4, 10) : 0;
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
