package mod.astler.tutorial_mod_gs.entity.villager.professions;

import mod.astler.tutorial_mod_gs.entity.ai.brain.ModPOI;
import net.minecraft.village.PointOfInterestType;

public class Profession  {

    public static Profession SHEEPMAN = new Profession("sheepman");

    String professionName;
    private final PointOfInterestType mCraftingTable;

    public Profession (String professionName_) {
        professionName = professionName_;
        mCraftingTable = ModPOI.WORKBENCH_POINT.get();
    }

    public String getProfessionName() {
        return professionName;
    }

}
