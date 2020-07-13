package mod.astler.tutorial_mod_gs.world.gen.fecture.structure;

import com.mojang.serialization.Codec;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config.SimpleHouseConfig;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class KillerHouseStructure extends Structure<SimpleHouseConfig> {
    public KillerHouseStructure(Codec<SimpleHouseConfig> p_i231965_1_) {
        super(p_i231965_1_);
    }

    public Structure.IStartFactory<SimpleHouseConfig> getStartFactory() {
        return KillerHouseStructure.Start::new;
    }

//    @Override
//    protected boolean func_230363_a_(ChunkGenerator p_230363_1_, BiomeProvider p_230363_2_, long p_230363_3_, SharedSeedRandom seedRandom, int p_230363_6_, int p_230363_7_, Biome p_230363_8_, ChunkPos p_230363_9_, SimpleHouseConfig config) {
//        int random = seedRandom.nextInt(config.chance);
//
//        System.out.println("okay, chance is = " + config.chance + " and random value is = " + random);
//
//        return random == 0;
//    }

    public static class Start extends StructureStart<SimpleHouseConfig> {
        public Start(Structure<SimpleHouseConfig> p_i225806_1_, int p_i225806_2_, int p_i225806_3_, MutableBoundingBox p_i225806_4_, int p_i225806_5_, long p_i225806_6_) {
            super(p_i225806_1_, p_i225806_2_, p_i225806_3_, p_i225806_4_, p_i225806_5_, p_i225806_6_);
        }

        public void func_230364_a_(ChunkGenerator p_230364_1_, TemplateManager templateManagerIn, int p_230364_3_, int p_230364_4_, Biome p_230364_5_, SimpleHouseConfig p_230364_6_) {

            int i = p_230364_3_ * 16;
            int j = p_230364_4_ * 16;
            BlockPos blockpos = new BlockPos(i, 90, j);
            Rotation rotation = Rotation.randomRotation(this.rand);
            this.components.add(new KillerHousePieces.Piece(templateManagerIn, blockpos, rotation));
            this.recalculateStructureSize();
        }
    }
}