package mod.astler.tutorial_mod_gs.entity.ai.brain.task;

import com.google.common.collect.ImmutableMap;
import mod.astler.tutorial_mod_gs.entity.villager.GSVillagerEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.world.server.ServerWorld;

public class RunAwayFromHurtHurtTask extends Task<GSVillagerEntity> {
    public RunAwayFromHurtHurtTask() {
        super(ImmutableMap.of());
    }

    protected void startExecuting(ServerWorld worldIn, GSVillagerEntity entityIn, long gameTimeIn) {
        boolean flag = AlarmAndPanicTask.hasBeenHurt(entityIn) || AlarmAndPanicTask.hostileNearby(entityIn) || distanceToEnemy(entityIn);
        if (!flag) {
            entityIn.getBrain().removeMemory(MemoryModuleType.HURT_BY);
            entityIn.getBrain().removeMemory(MemoryModuleType.HURT_BY_ENTITY);
            entityIn.getBrain().updateActivity(worldIn.getDayTime(), worldIn.getGameTime());
        }
    }

    private static boolean distanceToEnemy(GSVillagerEntity gsVillagerEntity) {
        return gsVillagerEntity.getBrain().getMemory(MemoryModuleType.HURT_BY_ENTITY)
                .filter((entity) -> entity.getDistanceSq(gsVillagerEntity) <= 36.0D).isPresent();
    }
}