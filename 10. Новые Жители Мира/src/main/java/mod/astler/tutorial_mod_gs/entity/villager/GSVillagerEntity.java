package mod.astler.tutorial_mod_gs.entity.villager;

import mod.astler.tutorial_mod_gs.entity.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Random;

public class GSVillagerEntity extends AgeableEntity {

    private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.STRING);
    private static final DataParameter<Integer> AGE = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.VARINT);
    private static final DataParameter<String> NAME = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.STRING);
    public static final DataParameter<BlockPos> BED_POS = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.BLOCK_POS);

    private VillagerType type;
    private byte foodLevel;
    private int xp;
    public boolean isPlayerInteracting = false;
    public boolean isVillagerSleeping = false;

    private final Inventory inventory = new Inventory(16);

    public GSVillagerEntity(EntityType<? extends GSVillagerEntity> type, World worldIn) {
        super(type, worldIn);
        ((GroundPathNavigator) this.getNavigator()).setBreakDoors(true);
        this.getNavigator().setCanSwim(true);
        this.setCanPickUpLoot(true);
        this.setCustomNameVisible(true);
    }

    public BlockPos getPosition()
    {
        return new BlockPos(getPosX(), getPosY(), getPosZ());
    }

    public Random getRandom()
    {
        return rand;
    }

    @Override
    public boolean isActiveItemStackBlocking() {
        return getActiveItemStack().getItem() instanceof ShieldItem;
    }

    protected void updateEquipmentIfNeeded(ItemEntity itemEntity) {
        ItemStack itemstack = itemEntity.getItem();
        if (itemstack.getItem() instanceof BannerItem) {
            super.updateEquipmentIfNeeded(itemEntity);
        } else {
            Item item = itemstack.getItem();

            System.out.println("item = " + item.getName());

            this.func_233630_a_(itemEntity);
            ItemStack itemstack1 = this.inventory.addItem(itemstack);
            if (itemstack1.isEmpty()) {
                itemEntity.remove();
            } else {
                itemstack.setCount(itemstack1.getCount());
            }
        }

    }

    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
        if (super.replaceItemInInventory(inventorySlot, itemStackIn)) {
            return true;
        } else {
            int i = inventorySlot - 300;
            if (i >= 0 && i < this.inventory.getSizeInventory()) {
                this.inventory.setInventorySlotContents(i, itemStackIn);
                return true;
            } else {
                return false;
            }
        }
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, GSVillagerEntity.class, 6.0F));
    }

    public static AttributeModifierMap.MutableAttribute func_234188_eI_() {
        return MobEntity
                .func_233666_p_().func_233815_a_(Attributes.field_233818_a_, 20.0D)
                .func_233815_a_(Attributes.field_233821_d_, 0.5F);
    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_COW_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    public ActionResultType func_230254_b_(PlayerEntity pPlayer, Hand hand) {
        ItemStack itemstack = pPlayer.getHeldItem(hand);
        if (itemstack.getItem() == Items.DEBUG_STICK) {
            //   pPlayer.sendMessage(new StringTextComponent("[TUTORMOD]\nHAS MILK: " + hasMilk() + "\nHUNGRY: " + isHungry() + "\nTIME UNTIL MILK: " + timeUntilMilk));
            return ActionResultType.func_233537_a_(this.world.isRemote);
        } else {
            return super.func_230254_b_(pPlayer, hand);
        }
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {

        if (!this.world.isRemote) {
            if (type == null) return super.applyPlayerInteraction(player, vec, hand);

            if (type.hireCost > 0) {
                this.isPlayerInteracting = true;
                //playerIn.openGui(Millenaire.instance, 5, playerIn.worldObj, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
                return ActionResultType.func_233537_a_(false);
            }
            if (type.isChief) {
                this.isPlayerInteracting = true;
                //playerIn.openGui(Millenaire.instance, 4, playerIn.worldObj, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
                return ActionResultType.func_233537_a_(false);
            }
        }

        System.out.println("BAM BOM");

        return super.applyPlayerInteraction(player, vec, hand);
    }

    public GSVillagerEntity createChild(AgeableEntity ageableEntity) {
        return ModEntityTypes.GS_VILLAGER.get().create(this.world);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TEXTURE, "texture");
        this.dataManager.register(AGE, 0);
        this.dataManager.register(GENDER, new Random().nextInt(1));
        this.dataManager.register(NAME, "name");
    }

    public GSVillagerEntity setTypeAndGender(VillagerType typeIn, int genderIn) {
        this.type = typeIn;
        this.dataManager.set(GENDER, genderIn);
        this.dataManager.set(TEXTURE, type.getTexture());
        return this;
    }

    public void setChild() {
        this.dataManager.set(AGE, 1);
    }

    public String getTexture() {
        return this.dataManager.get(TEXTURE);
    }

    public int getGender() {
        return this.dataManager.get(GENDER);
    }

    public String getVillagerName() {
        return this.dataManager.get(NAME);
    }

    public VillagerType getVillagerType() {
        return type;
    }

    @Override
    public boolean isChild() {
        return (this.dataManager.get(AGE) > 0);
    }

    @Override
    public void onDeath(DamageSource cause) {
        InventoryHelper.dropInventoryItems(this.world, this.getOnPosition(), this.inventory);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);

        compound.putByte("FoodLevel", this.foodLevel);
        compound.putInt("Xp", this.xp);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);

        if (compound.contains("FoodLevel", 1)) {
            this.foodLevel = compound.getByte("FoodLevel");
        }

        if (compound.contains("Xp", 3)) {
            this.xp = compound.getInt("Xp");
        }
    }

    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 0.81F : 1.62F;
    }

    @Override
    protected boolean isMovementBlocked() {
        return this.getHealth() <= 0 || this.isVillagerSleeping || this.isPlayerInteracting;
    }

}