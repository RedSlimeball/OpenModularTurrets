package omtteam.openmodularturrets.entity.projectiles.damagesources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import omtteam.openmodularturrets.tileentity.TurretBase;
import omtteam.openmodularturrets.util.OMTFakePlayer;

import javax.annotation.Nullable;

/**
 * Created by Keridos on 02/06/17.
 * This Class
 */
public abstract class AbstractOMTDamageSource extends DamageSource {
    private FakePlayer player;
    private final TurretBase base;

    public AbstractOMTDamageSource(String damageTypeIn, int fakeDrops, TurretBase base, WorldServer worldServer) {
        super(damageTypeIn);
        if (fakeDrops > -1) {
            player = OMTFakePlayer.getFakePlayer(worldServer);
            player.getEntityAttribute(SharedMonsterAttributes.LUCK).setBaseValue(fakeDrops);
        }
        this.base = base;
    }

    @Nullable
    @Override
    public Entity getEntity() {
        return player;
    }


    public TurretBase getBase() {
        return base;
    }
}
