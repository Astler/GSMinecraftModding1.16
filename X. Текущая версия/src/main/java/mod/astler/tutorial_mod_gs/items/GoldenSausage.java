package mod.astler.tutorial_mod_gs.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GoldenSausage extends Item {

    public GoldenSausage(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
