package mod.astler.tutorial_mod_gs.entity.villager;

import jdk.nashorn.internal.ir.Block;
import mod.astler.tutorial_mod_gs.entity.villager.professions.Profession;
import mod.astler.tutorial_mod_gs.utils.VillagerUtils;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class GSVillagerData {

    public static final DataParameter<BlockPos> HOME_POS = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.BLOCK_POS);
    private static final DataParameter<String> TEXTURE = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.STRING);
    private static final DataParameter<Integer> AGE = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> GENDER = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.VARINT);
    private static final DataParameter<String> NAME = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.STRING);
    private static final DataParameter<String> FAMILY_NAME = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.STRING);
    public static final DataParameter<BlockPos> BED_POS = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.BLOCK_POS);
    public static final DataParameter<String> PROFESSION = EntityDataManager.createKey(GSVillagerEntity.class, DataSerializers.STRING);

    private List<PrioritizedGoal> additionalTasks;

    public ArrayList<ItemStack> itemsToCraft = new ArrayList<>();

    private EntityDataManager dataManager;
    private Random random;

    private String emotion = "NOEMOTION";
    private BlockPos homePos = BlockPos.ZERO;
    private BlockPos bedPos = BlockPos.ZERO;
    private int foodLevel = 0;
    private int xp = 0;

    public boolean talking = false;

    public GSVillagerData(EntityDataManager dataManager_, Random rand)
    {
        this.dataManager = dataManager_;
        this.random = rand;

        switch (random.nextInt(4)+1)
        {
            case 1: emotion = "happy"; break;
            case 2: emotion = "angry"; break;
            case 3: emotion = "sad"; break;
            case 4: emotion = "normal"; break;
        }
    }

    public GSVillagerData addAI(PrioritizedGoal taskIn) {
        this.additionalTasks.add(taskIn);
        return this;
    }

    public int getXp() {
        return xp;
    }

    public boolean canHeal()
    {
        return foodLevel > 50;
    }

    public boolean veryHungry()
    {
        return foodLevel < 20;
    }

    public void addHunger(GSVillagerEntity villagerEntity) {
        this.foodLevel++;

        if(this.canHeal())
        {
            villagerEntity.setHealth(villagerEntity.getMaxHealth()+1);
        }
    }

    public void useFood()
    {
        this.foodLevel = 0;
    }

    public int getFoodLevel()
    {
        return this.foodLevel;
    }

    public String getEmotion()
    {
        return this.emotion;
    }

    public String getVillFullName()
    {
        return this.getName() + " " + this.getFamilyName();
    }

    public void writeDataToNBT(CompoundNBT compound) {
        compound.putString("name", this.getName());
        compound.putString("family_name", this.getFamilyName());
        compound.putString("emotion", this.emotion);
        compound.putInt("foodLevel", this.getFoodLevel());
        compound.putString("texture", this.getTexture());
        compound.putString("profession", this.getProfession());
        compound.putInt("xp", this.getXp());
    }

    public void readDataFromNBT(CompoundNBT compound) {
        this
                .setEmotion(compound.getString("emotion"))
                .setXp(compound.getInt("xp"))
                .setFoodLevel(compound.getInt("foodLevel"))
                .setName(compound.getString("name"))
                .setTexture(compound.getString("texture"))
                .setFamilyName(compound.getString("family_name"))
                .setProfession(compound.getString("profession"));
    }

    private GSVillagerData setName(String name) {
        this.dataManager.set(NAME, name);
        return this;
    }

    private GSVillagerData setFamilyName(String familyName) {
        this.dataManager.set(FAMILY_NAME, familyName);
        return this;
    }


    private GSVillagerData setProfession(String pProfession) {
        this.dataManager.set(PROFESSION, pProfession);
        return this;
    }

    private GSVillagerData setXp(Integer xp) {
        this.xp = xp;
        return this;
    }

    private String getName() {
        return this.dataManager.get(NAME);
    }

    private String getFamilyName() {
        return this.dataManager.get(FAMILY_NAME);
    }

    private GSVillagerData setEmotion(String emotion) {
        this.emotion = emotion;
        return this;
    }

    private void setGender(int gender) {
        this.dataManager.set(GENDER, gender);
    }

    private GSVillagerData setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
        return this;
    }

    public void registerData(EntityDataManager dataManager) {
        dataManager.register(AGE, 0);

        if(random.nextBoolean()) {
            int gender = 0;
            dataManager.register(GENDER, gender);
            dataManager.register(TEXTURE, VillagerUtils.getRandomPlayerSkin(gender));
            dataManager.register(NAME, VillagerUtils.getRandomFemaleName());
        }
        else {
            int gender = 1;
            dataManager.register(GENDER, gender);
            dataManager.register(TEXTURE, VillagerUtils.getRandomPlayerSkin(gender));
            dataManager.register(NAME, VillagerUtils.getRandomMaleName());
        }

        dataManager.register(FAMILY_NAME, VillagerUtils.getRandomFamilyName());
        dataManager.register(HOME_POS, homePos);
        dataManager.register(BED_POS, bedPos);

        dataManager.register(PROFESSION, getRandomProfession());
    }

    private String getRandomProfession() {
        return "sheepman";
    }

    public boolean isChild() {
        return this.dataManager.get(AGE) > 0;
    }

    public void setChild() {
        this.dataManager.set(AGE, 1);
    }

    public String getTexture() {
        return this.dataManager.get(TEXTURE);
    }

    public GSVillagerData setTexture(String pTexture) {
        this.dataManager.set(TEXTURE, pTexture);
        return this;
    }

    public int getGender() {
        return this.dataManager.get(GENDER);
    }

    public String getProfession() {
        return this.dataManager.get(PROFESSION);
    }

    public String getVillagerName() {
        return this.dataManager.get(NAME);
    }

}
