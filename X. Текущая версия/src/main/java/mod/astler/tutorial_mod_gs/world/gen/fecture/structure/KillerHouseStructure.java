package mod.astler.tutorial_mod_gs.world.gen.fecture.structure;

import com.mojang.datafixers.Dynamic;
import mod.astler.tutorial_mod_gs.world.ModFeatures;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.config.KillerHouseConfig;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class KillerHouseStructure extends Structure<KillerHouseConfig> {

    public KillerHouseStructure(Function<Dynamic<?>, ? extends KillerHouseConfig> configFactory)
    {
        super(configFactory);
    }

    public String getStructureName() {
        return "Killer_House";
    }

    @Override
    public int getSize() {
        return 1;
    }

    public Structure.IStartFactory getStartFactory() {
        return KillerHouseStructure.Start::new;
    }

    @Override
    public boolean func_225558_a_(BiomeManager biomeManager, ChunkGenerator<?> chunkGenerator, Random random, int chunkX, int chunkZ, Biome biome) {
        if(chunkGenerator.hasStructure(biome, this))
        {
            ((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), chunkX, chunkZ, 0xF00D);
            KillerHouseConfig config = chunkGenerator.getStructureConfig(biome, this);
            return config != null && random.nextInt(config.chance) == 0;
        }
        return false;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> structure, int p_i225806_2_, int p_i225806_3_, MutableBoundingBox p_i225806_4_, int p_i225806_5_, long p_i225806_6_) {
            super(structure, p_i225806_2_, p_i225806_3_, p_i225806_4_, p_i225806_5_, p_i225806_6_);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            int posX = chunkX << 4;
            int posZ = chunkZ << 4;
            int height1 = generator.func_222532_b(posX + 3, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG);
            int height2 = generator.func_222532_b(posX + 13, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG);
            int height3 = generator.func_222532_b(posX + 3, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG);
            int height4 = generator.func_222532_b(posX + 13, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG);
            if(height1 == height2 && height1 == height3 && height1 == height4 && height1 >= generator.getSeaLevel())
            {
                BlockPos pos = new BlockPos(posX + 3, 90, posZ + 3);
                Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
                KillerHouseConfig config = generator.getStructureConfig(biomeIn, ModFeatures.KILLER_HOUSE.get());
                if(config != null)
                {
                    this.components.add(new KillerHousePieces.Piece(templateManagerIn, pos, rotation));
                    this.recalculateStructureSize();
                }
            }
        }
    }
}