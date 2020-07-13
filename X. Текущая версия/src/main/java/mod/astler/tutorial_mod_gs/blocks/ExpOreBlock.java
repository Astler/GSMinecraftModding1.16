package mod.astler.tutorial_mod_gs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class ExpOreBlock extends Block {

    private int dropExpMin;
    private int dropExpMax;

    public ExpOreBlock(Block.Properties properties, int expMin, int expMax) {
        super(properties);
        dropExpMin = expMin;
        dropExpMax = expMax;
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, dropExpMin, dropExpMax) : 0;
    }
}
