package mod.astler.tutorial_mod_gs.world.gen.fecture.structure;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IglooPieces;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;
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

            System.out.println("Load Template?");

        }

        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        public boolean func_230383_a_(ISeedReader iSeedReader, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
            int posY = iSeedReader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.templatePosition.getX(), this.templatePosition.getZ()) - 1;

            this.templatePosition = new BlockPos(this.templatePosition.getX(), posY, this.templatePosition.getZ());

            System.out.println("killer HUT and " + this.templatePosition.getX() + " and " + this.templatePosition.getY() + " and " + this.templatePosition.getZ());

            return super.func_230383_a_(iSeedReader, p_230383_2_, p_230383_3_, p_230383_4_, p_230383_5_, p_230383_6_, p_230383_7_);
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