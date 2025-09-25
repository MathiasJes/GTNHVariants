package com.gtnh.gtnhvariants.mixins.early;

import java.util.Random;

import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gtnh.gtnhvariants.GTNHVariants;
import com.gtnh.gtnhvariants.GTNHWorldConfig;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin {

    private static int OVERWORLD_DIM_ID = 0;
    private static int NETHER_DIM_ID = -1;
    private static int LOWEST_Y_SPAWN_COORD = 48;
    private static int HIGHEST_Y_SPAWN_COORD = 128 - LOWEST_Y_SPAWN_COORD;

    @Inject(
        method = "loadAllWorlds",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/management/ServerConfigurationManager;setPlayerManager([Lnet/minecraft/world/WorldServer;)V"))
    public void loadAllWorlds(String p_71247_1_, String p_71247_2_, long p_71247_3_, WorldType p_71247_5_,
        String p_71247_6_, CallbackInfo ci) {
        if (GTNHWorldConfig.instance()
            .IsNetherOnly()) {

            WorldInfo worldInfo = DimensionManager.getWorld(OVERWORLD_DIM_ID)
                .getWorldInfo();

            if (isSpawnUnitialized(worldInfo)) {
                GTNHVariants.LOG.info("Creating Nether Spawn Point");
                createNetherSpawnPoint(worldInfo, DimensionManager.getProvider(NETHER_DIM_ID));
            }
        }
    }

    private boolean isSpawnUnitialized(WorldInfo worldInfo) {
        return worldInfo.getSpawnX() == 0 && worldInfo.getSpawnY() == 0 && worldInfo.getSpawnZ() == 0;
    }

    private void createNetherSpawnPoint(WorldInfo worldInfo, WorldProvider provider) {
        Random random = new Random(worldInfo.getSeed());

        int x = 0;
        int y = -1;
        int z = 0;

        for (int i = 0; i < 10000; ++i) {
            x += random.nextInt(64) - random.nextInt(64);
            z += random.nextInt(64) - random.nextInt(64);

            y = canCoordinateBeSpawn(provider, x, z);
            if (y >= 0) break;
        }

        if (y < 0) {
            y = provider.getAverageGroundLevel();
        }

        GTNHVariants.LOG.info("Setting Nether Spawn Point to " + x + "," + y + "," + z);

        worldInfo.setSpawnPosition(x, y, z);
    }

    private int canCoordinateBeSpawn(WorldProvider provider, int x, int z) {
        int airBlocksNeeded = 3;

        boolean isAirCurrent = true;
        boolean isAirBelow = true;
        boolean isAirTwoBelow = true;
        boolean isAirThreeBelow = true;

        int y_search_limit = HIGHEST_Y_SPAWN_COORD + airBlocksNeeded;

        for (int y = LOWEST_Y_SPAWN_COORD; y <= y_search_limit; ++y) {
            isAirCurrent = provider.worldObj.isAirBlock(x, y, z);
            if (isAirCurrent && isAirBelow && isAirTwoBelow && !isAirThreeBelow) return y - airBlocksNeeded + 1;

            isAirThreeBelow = isAirTwoBelow;
            isAirTwoBelow = isAirBelow;
            isAirBelow = isAirCurrent;
        }

        return -1;
    }
}
