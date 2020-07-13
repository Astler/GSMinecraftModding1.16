package mod.astler.tutorial_mod_gs.world;

import mod.astler.tutorial_mod_gs.TutorialGSMod;
import mod.astler.tutorial_mod_gs.world.gen.fecture.structure.KillerHousePieces;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class ModStructurePieceType
{
    public static final IStructurePieceType KILLER_HOUSE = register(KillerHousePieces.Piece::new, TutorialGSMod.MODID + ":survival_camp");

    public static void init() {}

    private static IStructurePieceType register(IStructurePieceType type, String key)
    {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(key), type);
    }
}