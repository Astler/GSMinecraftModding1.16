package mod.astler.tutorial_mod_gs.world.gen.fecture.structure;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.storage.loot.LootTables;
import java.util.Random;

import static mod.astler.tutorial_mod_gs.world.ModStructurePieceType.KILLER_HOUSE;

public class KillerHousePieces {
    private static final ResourceLocation model = new ResourceLocation("tutorial_mod_gs:killer_house_1");

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation resourceLocation;
        private final Rotation rotation;

        public Piece(TemplateManager templateManager, BlockPos p_i49313_3_, Rotation p_i49313_4_) {
            super(KILLER_HOUSE, 0);
            this.resourceLocation = model;
            this.templatePosition = p_i49313_3_;
            this.rotation = p_i49313_4_;
            this.loadTemplate(templateManager);
        }

        public Piece(TemplateManager templateManager, CompoundNBT p_i50566_2_) {
            super(KILLER_HOUSE, p_i50566_2_);
            this.resourceLocation = new ResourceLocation(p_i50566_2_.getString("Template"));
            this.rotation = Rotation.valueOf(p_i50566_2_.getString("Rot"));
            this.loadTemplate(templateManager);
        }

        private void loadTemplate(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK).addProcessor(JigsawReplacementStructureProcessor.INSTANCE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        public boolean func_225577_a_(IWorld world, ChunkGenerator<?> chunkGenerator, Random nRandom, MutableBoundingBox nMutableBoundingBox, ChunkPos nChunkPos) {
            int posY = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.templatePosition.getX(), this.templatePosition.getZ()) - 1;

            this.templatePosition = new BlockPos(this.templatePosition.getX(), posY, this.templatePosition.getZ());

            System.out.println("killer HUT and " + this.templatePosition.getX() + " and " + this.templatePosition.getY() + " and " + this.templatePosition.getZ());

            return super.func_225577_a_(world, chunkGenerator, nRandom, nMutableBoundingBox, nChunkPos);
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
            if ("dead_man_chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                TileEntity lvt_6_1_ = worldIn.getTileEntity(pos.up());
                if (lvt_6_1_ instanceof ChestTileEntity) {
                    ((ChestTileEntity)lvt_6_1_).setLootTable(LootTables.CHESTS_IGLOO_CHEST, rand.nextLong());
                }
            }
            else  if ("killer_chest".equals(function)) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                TileEntity lvt_6_1_ = worldIn.getTileEntity(pos.up());
                if (lvt_6_1_ instanceof ChestTileEntity) {
                    ((ChestTileEntity)lvt_6_1_).setLootTable(LootTables.CHESTS_ABANDONED_MINESHAFT, rand.nextLong());
                }
            }
        }
    }
}