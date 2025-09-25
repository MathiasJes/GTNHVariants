package com.gtnh.gtnhvariants;

import net.minecraftforge.event.world.WorldEvent.CreateSpawnPosition;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GTNHVariantsEventHandler {

    @SubscribeEvent
    public void onCreateWorldSpawn(CreateSpawnPosition event) {
        if (GTNHWorldConfig.instance()
            .IsNetherOnly()) {
            event.setCanceled(true);
        }
    }
}
