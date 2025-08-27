package com.gtnh.gtnhvariants.mixins.early;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gtnh.gtnhvariants.GTNHWorldConfig;

import net.minecraft.block.BlockPortal;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

@Mixin(BlockPortal.class)
public abstract class BlockPortalMixin {
    @Inject(
        method = "func_150000_e",
        at = @At("HEAD"),
        cancellable = true
    )
    private void gtnhvariants$preventNetherPortal(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (GTNHWorldConfig.instance().IsPortalsDisabled()) {

            for (Object playerObj : world.playerEntities) {
                EntityPlayer player = (EntityPlayer) playerObj;
                double distance = player.getDistance(x + 0.5, y + 0.5, z + 0.5);
                if (distance <= 10.0) {
                    player.addChatMessage(new net.minecraft.util.ChatComponentText("Nether portals are disabled due to the GTNH world configuration."));
                }
            }
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}