package omtteam.openmodularturrets.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import omtteam.openmodularturrets.entity.projectiles.damagesources.AbstractOMTDamageSource;
import omtteam.openmodularturrets.util.OMTFakePlayer;
import omtteam.openmodularturrets.util.OMTUtil;

/**
 * Created by Keridos on 02/05/17.
 * This class is the listener for all the Events we need to watch & modify.
 */
public class EventHandler {
    private static EventHandler instance;

    private EventHandler() {
    }

    public static EventHandler getInstance() {
        if (instance == null) {
            instance = new EventHandler();
        }
        return instance;
    }

    @SubscribeEvent
    public void lootEvent(LivingDropsEvent event) {
        if (event.getEntityLiving().getTags().contains("openmodularturrets:turret_hit") && !ConfigHandler.doTurretsKillsDropMobLoot) {
            event.setCanceled(true);
        }
        event.getLootingLevel();
    }

    @SubscribeEvent
    public void entityHurtEvent(LivingHurtEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        int fakeDrops = OMTUtil.getFakeDropsLevel(entity);
        if (fakeDrops >= 0) {
            FakePlayer player = OMTFakePlayer.getFakePlayer((WorldServer) event.getEntityLiving().getEntityWorld());
            player.setHeldItem(EnumHand.MAIN_HAND, OMTFakePlayer.getSword(fakeDrops));
            entity.setLastAttacker(player);
        }
    }

    @SubscribeEvent
    public void entityAttackedEvent(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        int fakeDrops = OMTUtil.getFakeDropsLevel(entity);
        if (fakeDrops >= 0) {
            FakePlayer player = OMTFakePlayer.getFakePlayer((WorldServer) event.getEntityLiving().getEntityWorld());
            player.setHeldItem(EnumHand.MAIN_HAND, OMTFakePlayer.getSword(fakeDrops));
            entity.setLastAttacker(player);
        }
    }

    @SubscribeEvent
    public void entityDeathEvent(LivingDeathEvent event) {
        if (event.getSource() instanceof AbstractOMTDamageSource) {
            ((AbstractOMTDamageSource) event.getSource()).getBase().increaseKillCounter();
            if (event.getEntity() instanceof EntityPlayer) {
                ((AbstractOMTDamageSource) event.getSource()).getBase().increasePlayerKillCounter();
            }
        }
    }
}
