package mod.astler.tutorial_mod_gs.entity.villager;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Dynamic;
import mod.astler.tutorial_mod_gs.entity.ModEntityTypes;
import mod.astler.tutorial_mod_gs.entity.ai.brain.schedule.ModSchedule;
import mod.astler.tutorial_mod_gs.entity.ai.brain.task.ModActivity;
import mod.astler.tutorial_mod_gs.entity.villager.professions.Profession;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.VillagerTasks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.IReputationType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class GSVillagerEntity extends AgeableEntity {

    private static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.HOME, MemoryModuleType.JOB_SITE, MemoryModuleType.field_234101_d_, MemoryModuleType.MEETING_POINT, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.VISIBLE_VILLAGER_BABIES, MemoryModuleType.NEAREST_PLAYERS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.field_234102_l_, MemoryModuleType.field_234076_J_, MemoryModuleType.WALK_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.BREED_TARGET, MemoryModuleType.PATH, MemoryModuleType.INTERACTABLE_DOORS, MemoryModuleType.field_225462_q, MemoryModuleType.NEAREST_BED, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.NEAREST_HOSTILE, MemoryModuleType.SECONDARY_JOB_SITE, MemoryModuleType.HIDING_PLACE, MemoryModuleType.HEARD_BELL_TIME, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.LAST_SLEPT, MemoryModuleType.field_226332_A_, MemoryModuleType.LAST_WORKED_AT_POI, MemoryModuleType.GOLEM_LAST_SEEN_TIME);
    private static final ImmutableList<SensorType<? extends Sensor<? super GSVillagerEntity>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.field_234129_b_, SensorType.INTERACTABLE_DOORS, SensorType.NEAREST_BED, SensorType.HURT_BY, SensorType.VILLAGER_HOSTILES, SensorType.VILLAGER_BABIES, SensorType.GOLEM_LAST_SEEN);

    private boolean isStructureVillager;
    public boolean isPlayerInteracting = false;
    public boolean isVillagerSleeping = false;
    private GSVillagerData gsVillagerData = null;

    private final Inventory inventory = new Inventory(16);

    public GSVillagerEntity(EntityType<? extends GSVillagerEntity> type, World worldIn) {
        super(type, worldIn);
        ((GroundPathNavigator) this.getNavigator()).setBreakDoors(true);
        this.getNavigator().setCanSwim(true);
        this.setCanPickUpLoot(true);
        this.setCustomNameVisible(true);
    }

    public Brain<GSVillagerEntity> getBrain() {
        return (Brain<GSVillagerEntity>)super.getBrain();
    }

    protected Brain.BrainCodec<GSVillagerEntity> func_230289_cH_() {
        return Brain.func_233705_a_(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected Brain<?> createBrain(Dynamic<?> dynamicIn) {
        Brain<GSVillagerEntity> brain = this.func_230289_cH_().func_233748_a_(dynamicIn);
        this.initBrain(brain);
        return brain;
    }

    private void initBrain(Brain<GSVillagerEntity> villagerBrain) {
        if (isStructureVillager) {

            if (getGsVillagerData().getProfession() == Profession.SHEEPMAN.getProfessionName()) {
                
            }


            if (this.isChild()) {
//            villagerBrain.setSchedule(ModSchedule.VILLAGER_BABY);
//            villagerBrain.registerActivity(Activity.PLAY, VillagerTasks.play(0.5F));
            } else {
            }

            villagerBrain.setSchedule(ModSchedule.GS_VILLAGER_DEFAULT.get());
            villagerBrain.registerActivity(Activity.WORK, GSVillagerTasks.work(0.5F));

            villagerBrain.registerActivity(Activity.CORE, GSVillagerTasks.core(0.5F));
//        villagerBrain.func_233700_a_(Activity.MEET, VillagerTasks.meet(villagerprofession, 0.5F), ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryModuleStatus.VALUE_PRESENT)));
//        villagerBrain.registerActivity(Activity.REST, VillagerTasks.rest(villagerprofession, 0.5F));
            villagerBrain.registerActivity(Activity.IDLE, GSVillagerTasks.idle(0.5F));
            villagerBrain.registerActivity(ModActivity.PANIC_AND_ALARM.get(), GSVillagerTasks.panic(0.5F));
//        villagerBrain.registerActivity(Activity.PRE_RAID, VillagerTasks.preRaid(villagerprofession, 0.5F));
//        villagerBrain.registerActivity(Activity.RAID, VillagerTasks.raid(villagerprofession, 0.5F));
//        villagerBrain.registerActivity(Activity.HIDE, VillagerTasks.hide(villagerprofession, 0.5F));
            villagerBrain.setDefaultActivities(ImmutableSet.of(Activity.CORE));
            villagerBrain.setFallbackActivity(Activity.IDLE);
            villagerBrain.switchTo(Activity.IDLE);
            villagerBrain.updateActivity(this.world.getDayTime(), this.world.getGameTime());
        }
        else {
            villagerBrain.setSchedule(ModSchedule.GS_VILLAGER_DEFAULT.get());
            villagerBrain.registerActivity(Activity.WORK, GSVillagerTasks.work(0.5F));

            villagerBrain.registerActivity(Activity.CORE, GSVillagerTasks.core(0.5F));
//        villagerBrain.func_233700_a_(Activity.MEET, VillagerTasks.meet(villagerprofession, 0.5F), ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryModuleStatus.VALUE_PRESENT)));
//        villagerBrain.registerActivity(Activity.REST, VillagerTasks.rest(villagerprofession, 0.5F));
            villagerBrain.registerActivity(Activity.IDLE, GSVillagerTasks.idle(0.5F));
            villagerBrain.registerActivity(ModActivity.PANIC_AND_ALARM.get(), GSVillagerTasks.panic(0.5F));
            villagerBrain.setDefaultActivities(ImmutableSet.of(Activity.CORE));
            villagerBrain.setFallbackActivity(Activity.IDLE);
            villagerBrain.switchTo(Activity.IDLE);
            villagerBrain.updateActivity(this.world.getDayTime(), this.world.getGameTime());
        }
    }

    public void resetBrain(ServerWorld serverWorldIn) {
        Brain<GSVillagerEntity> brain = this.getBrain();
        brain.stopAllTasks(serverWorldIn, this);
        this.brain = brain.copy();
        this.initBrain(this.getBrain());
    }

    public BlockPos getPosition()
    {
        return new BlockPos(getPosX(), getPosY(), getPosZ());
    }

    @Override
    public boolean isActiveItemStackBlocking() {
        return getActiveItemStack().getItem() instanceof ShieldItem;
    }

    protected void updateAITasks() {
        this.world.getProfiler().startSection("gsVillagerBrain");
        this.getBrain().tick((ServerWorld)this.world, this);
        this.world.getProfiler().endSection();

//        if (!this.hasCustomer() && this.timeUntilReset > 0) {
//            --this.timeUntilReset;
//            if (this.timeUntilReset <= 0) {
//                if (this.leveledUp) {
//                    this.levelUp();
//                    this.leveledUp = false;
//                }
//
//                this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 0));
//            }
//        }

//        if (this.previousCustomer != null && this.world instanceof ServerWorld) {
//            ((ServerWorld)this.world).updateReputation(IReputationType.TRADE, this.previousCustomer, this);
//            this.world.setEntityState(this, (byte)14);
//            this.previousCustomer = null;
//        }

        if (!this.isAIDisabled() && this.rand.nextInt(100) == 0) {
            this.world.setEntityState(this, (byte)42);
        }

//        if (this.getVillagerData().getProfession() == VillagerProfession.NONE && this.hasCustomer()) {
//            this.resetCustomer();
//        }

        super.updateAITasks();
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (reason == SpawnReason.BREEDING) {
           // this.setVillagerData(this.getVillagerData().withProfession(VillagerProfession.NONE));
        }

        if (reason == SpawnReason.COMMAND || reason == SpawnReason.SPAWN_EGG || reason == SpawnReason.SPAWNER || reason == SpawnReason.DISPENSER) {
           // this.setVillagerData(this.getVillagerData().withType(IVillagerType.byBiome(worldIn.getBiome(this.func_233580_cy_()))));
        }

        if (reason == SpawnReason.STRUCTURE) {
            this.isStructureVillager = true;
        }

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
//            if (type == null) return super.applyPlayerInteraction(player, vec, hand);
//
//            if (type.hireCost > 0) {
//                this.isPlayerInteracting = true;
//                //playerIn.openGui(Millenaire.instance, 5, playerIn.worldObj, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
//                return ActionResultType.func_233537_a_(false);
//            }
//            if (type.isChief) {
//                this.isPlayerInteracting = true;
//                //playerIn.openGui(Millenaire.instance, 4, playerIn.worldObj, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
//                return ActionResultType.func_233537_a_(false);
//            }
        }

        System.out.println("BAM BOM");

        return super.applyPlayerInteraction(player, vec, hand);
    }

    public GSVillagerEntity createChild(AgeableEntity ageableEntity) {
        return ModEntityTypes.GS_VILLAGER.get().create(this.world);
    }

    protected void registerData() {
        super.registerData();

        getGsVillagerData().registerData(this.dataManager);
    }

    @Override
    public boolean isChild() {
        return getGsVillagerData().isChild();
    }

    @Override
    public ITextComponent getName() {
        return new StringTextComponent(getGsVillagerData().getVillFullName());
    }

    @Override
    public void onDeath(DamageSource cause) {
        InventoryHelper.dropInventoryItems(this.world, this.getOnPosition(), this.inventory);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);

        getGsVillagerData().writeDataToNBT(compound);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);

        getGsVillagerData().readDataFromNBT(compound);
    }

    public GSVillagerData getGsVillagerData() {
        if (gsVillagerData != null)
            return gsVillagerData;

        gsVillagerData = new GSVillagerData(this.dataManager, rand);

        return gsVillagerData;
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

    public void setVillagerData(GSVillagerData villagerData) {
        this.gsVillagerData = villagerData;
    }
}