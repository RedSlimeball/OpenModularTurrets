package omtteam.openmodularturrets.init;

import net.minecraft.util.ResourceLocation;
import omtteam.omlib.util.compat.EntityTools;
import omtteam.openmodularturrets.OpenModularTurrets;
import omtteam.openmodularturrets.entity.projectiles.*;
import omtteam.openmodularturrets.reference.Reference;

public class ModEntities {
    public static void registerProjectiles(OpenModularTurrets mod) {
        EntityTools.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":rocketProjectile"), RocketProjectile.class, Reference.MOD_ID + ":rocketProjectile", 1, mod, 64, 64, true);
        EntityTools.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":grenadeProjectile"), GrenadeProjectile.class, Reference.MOD_ID + ":grenadeProjectile", 2, mod, 64, 64, true);
        EntityTools.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":bulletProjectile"), BulletProjectile.class, Reference.MOD_ID + ":bulletProjectile", 3, mod, 64, 64, true);
        EntityTools.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":laserProjectile"), LaserProjectile.class, Reference.MOD_ID + ":laserProjectile", 4, mod, 64, 64, true);
        EntityTools.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":blazingClayProjectile"), BlazingClayProjectile.class, Reference.MOD_ID + ":blazingClayProjectile", 5, mod, 64, 64, true);
    }
}
