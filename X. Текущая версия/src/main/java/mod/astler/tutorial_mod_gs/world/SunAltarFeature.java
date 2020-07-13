package mod.astler.tutorial_mod_gs.world;

import com.mojang.datafixers.Dynamic;
import mod.astler.tutorial_mod_gs.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import java.util.Random;
import java.util.function.Function;

public class SunAltarFeature extends Feature<NoFeatureConfig> {
    private final BlockState sun_bricks = ModBlocks.SUN_BRICKS.getDefaultState();
    private final BlockState sun_bricks_stairs_1 = ModBlocks.SUN_BRICKS_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
    private final BlockState sun_bricks_stairs_2 = ModBlocks.SUN_BRICKS_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
    private final BlockState sun_bricks_stairs_3 = ModBlocks.SUN_BRICKS_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
    private final BlockState sun_bricks_stairs_4 = ModBlocks.SUN_BRICKS_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
    private final BlockState table_block = ModBlocks.TABLE_BLOCK.getDefaultState();
    private final BlockState sun_dust_glass = ModBlocks.SUN_DUST_GLASS.getDefaultState();
    private final BlockState cobblestone = Blocks.COBBLESTONE.getDefaultState();
    private final BlockState sun_lamp = ModBlocks.SUN_LAMP.getDefaultState();

    public SunAltarFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        for (pos = pos.up(); worldIn.isAirBlock(pos) && pos.getY() > 2; pos = pos.down()) {
        }

     //   System.out.println("placed sun_altar: x = " + pos.getX() + " y = " + pos.getY() + " z = " + pos.getZ());

        if (worldIn.getBlockState(pos).isSolid() || worldIn.getBlockState(pos).getMaterial().isLiquid()) {
            int maxX = 2;
            int maxZ = 2;
            int minX = -2;
            int minZ = -2;


            for (int l1 = minX; l1 <= maxX; ++l1) {
                for (int k = minZ; k <= maxZ; ++k) {
                    worldIn.setBlockState(pos.add(l1, 0, k), this.sun_bricks, 2);
                }
            }

            for (int l1 = minX + 1; l1 <= maxX - 1; ++l1) {
                for (int k = minZ + 1; k <= maxZ - 1; ++k) {
                    worldIn.setBlockState(pos.add(l1, 1, k), this.sun_bricks, 2);
                }
            }

            for (int l1 = minX; l1 <= maxX; ++l1) {
                for (int k = minZ; k <= maxZ; ++k) {
                    boolean b = Math.abs(l1) == Math.abs(maxX) && Math.abs(k) == Math.abs(maxZ);

                    if (!b)
                        worldIn.setBlockState(pos.add(l1, 5, k), this.sun_dust_glass, 2);
                }
            }

            for (int l1 = minX + 1; l1 <= maxX - 1; ++l1) {
                for (int k = minZ + 1; k <= maxZ - 1; ++k) {
                    worldIn.setBlockState(pos.add(l1, 6, k), this.sun_dust_glass, 2);
                }
            }

            for (int iy = 1; iy <= 4; iy++)
                for (int l1 = minX; l1 <= maxX; l1 += 4) {
                    for (int k = minZ; k <= maxZ; k += 4) {
                        worldIn.setBlockState(pos.add(l1, iy, k), this.sun_dust_glass, 2);
                    }
                }

            worldIn.setBlockState(pos.add(-1, 1, -2), this.sun_bricks_stairs_4, 2);
            worldIn.setBlockState(pos.add(0, 1, -2), this.sun_bricks_stairs_4, 2);
            worldIn.setBlockState(pos.add(1, 1, -2), this.sun_bricks_stairs_4, 2);


            worldIn.setBlockState(pos.add(-1, 1, 2), this.sun_bricks_stairs_1, 2);
            worldIn.setBlockState(pos.add(0, 1, 2), this.sun_bricks_stairs_1, 2);
            worldIn.setBlockState(pos.add(1, 1, 2), this.sun_bricks_stairs_1, 2);

            worldIn.setBlockState(pos.add(2, 1, -1), this.sun_bricks_stairs_3, 2);
            worldIn.setBlockState(pos.add(2, 1, 0), this.sun_bricks_stairs_3, 2);
            worldIn.setBlockState(pos.add(2, 1, 1), this.sun_bricks_stairs_3, 2);

            worldIn.setBlockState(pos.add(-2, 1, -1), this.sun_bricks_stairs_2, 2);
            worldIn.setBlockState(pos.add(-2, 1, 0), this.sun_bricks_stairs_2, 2);
            worldIn.setBlockState(pos.add(-2, 1, 1), this.sun_bricks_stairs_2, 2);

            worldIn.setBlockState(pos.add(0, 2, 0), this.table_block, 2);
            worldIn.setBlockState(pos.add(0, 5, 0), this.sun_lamp, 2);

            worldIn.setBlockState(pos.add(-1, 5, 0), this.sun_dust_glass, 2);
            worldIn.setBlockState(pos.add(1, 5, 0), this.sun_dust_glass, 2);
            worldIn.setBlockState(pos.add(0, 5, -1), this.sun_dust_glass, 2);
            worldIn.setBlockState(pos.add(0, 5, 1), this.sun_dust_glass, 2);

            for (int ii = minX; ii <= maxX; ++ii) {
                for (int ij = minZ; ij <= maxZ; ++ij) {

                    worldIn.setBlockState(pos.add(ii, -1, ij), cobblestone, 3);

                    boolean b = Math.abs(ii) == Math.abs(maxX) && Math.abs(ij) == Math.abs(maxZ);

                    if (b) {
                        int innerY = -2;

                        while ((worldIn.isAirBlock(pos.add(ii, innerY, ij)) || worldIn.getBlockState(pos.add(ii, innerY, ij)).getMaterial().isLiquid()) && pos.add(ii, innerY, ij).getY() > 1) {
                            worldIn.setBlockState(pos.add(ii, innerY, ij), Blocks.COBBLESTONE.getDefaultState(), 2);
                            --innerY;
                        }
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }
}