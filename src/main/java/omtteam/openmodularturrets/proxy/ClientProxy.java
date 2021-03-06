package omtteam.openmodularturrets.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import omtteam.omlib.render.CamoBlockColor;
import omtteam.openmodularturrets.blocks.LeverBlock;
import omtteam.openmodularturrets.blocks.turretheads.BlockAbstractTurretHead;
import omtteam.openmodularturrets.client.render.models.TurretBaseBakedModel;
import omtteam.openmodularturrets.client.render.renderers.blockitem.TileEntityRenderers;
import omtteam.openmodularturrets.client.render.renderers.projectiles.ProjectileRenderers;
import omtteam.openmodularturrets.compatibility.IGWHandler;
import omtteam.openmodularturrets.compatibility.ModCompatibility;
import omtteam.openmodularturrets.init.ModBlocks;
import omtteam.openmodularturrets.init.ModItems;
import omtteam.openmodularturrets.init.ModTESRItems;
import omtteam.openmodularturrets.items.*;
import omtteam.openmodularturrets.reference.OMTNames;
import omtteam.openmodularturrets.reference.Reference;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private void registerItemModel(final Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName().toString().toLowerCase()));
    }

    private void registerItemModel(final Item item, int meta, final String variantName) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(item.getRegistryName().toString().toLowerCase()), variantName));
    }

    @SuppressWarnings("SameParameterValue")
    private void registerItemModel(final Item item, int meta, final String customName, boolean useCustomName) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + customName.toLowerCase()));
    }

    @SuppressWarnings("ConstantConditions")
    private void registerBlockModelAsItem(final Block block, int meta, final String blockName) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + blockName, "inventory"));
    }

    @SuppressWarnings("ConstantConditions")
    private void registerBlockModelAsItem(final Block block, int meta, final String blockName, String variantName) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + blockName, variantName));
    }

    @Override
    public void preInit() {
        super.preInit();

        StateMap ignoreRotation = new StateMap.Builder().ignore(LeverBlock.ROTATION).build();
        StateMap ignoreConcealed = new StateMap.Builder().ignore(BlockAbstractTurretHead.CONCEALED).build();
        ModelLoader.setCustomStateMapper(ModBlocks.leverBlock, ignoreRotation);
        ModelLoader.setCustomStateMapper(ModBlocks.disposableItemTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.grenadeLauncherTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.teleporterTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.incendiaryTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.laserTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.railGunTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.relativisticTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.rocketTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.machineGunTurret, ignoreConcealed);
        ModelLoader.setCustomStateMapper(ModBlocks.potatoCannonTurret, ignoreConcealed);
        ModelLoaderRegistry.registerLoader(new TurretBaseBakedModel.ModelLoader());
        ModelLoader.setCustomStateMapper(ModBlocks.turretBase, new TurretBaseBakedModel.Statemapper());

        for (int i = 0; i < 5; i++) {
            registerBlockModelAsItem(ModBlocks.turretBase, i, OMTNames.Blocks.turretBase + "_normal", "tier=" + (i + 1));
        }
        for (int i = 0; i < 10; i++) {
            registerBlockModelAsItem(ModBlocks.expander, i, OMTNames.Blocks.expander, "facing=north,meta=" + i);
        }
        for (int i = 0; i < 15; i++) {
            registerItemModel(ModItems.intermediateProductTiered, i, IntermediateProductTiered.subNames[i], true);
        }
        for (int i = 0; i < 8; i++) {
            registerItemModel(ModItems.addonMetaItem, i, AddonMetaItem.subNames[i], true);
        }
        for (int i = 0; i < 5; i++) {
            registerItemModel(ModItems.upgradeMetaItem, i, UpgradeMetaItem.subNames[i], true);
        }
        for (int i = 0; i < 1; i++) {
            registerItemModel(ModItems.intermediateProductRegular, i, IntermediateProductRegular.subNames[i], true);
        }
        for (int i = 0; i < 5; i++) {
            registerItemModel(ModItems.ammoMetaItem, i, AmmoMetaItem.subNames[i], true);
        }
        for (int i = 0; i < 3; i++) {
            registerItemModel(ModItems.usableMetaItem, i, UsableMetaItem.subNames[i], true);
        }
    }

    @Override
    public void init() {
        super.init();
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new CamoBlockColor(), ModBlocks.turretBase);
    }

    @Override
    protected void initTileRenderers() {
        super.initTileRenderers();
        TileEntityRenderers.init();
        ModTESRItems.init();
    }

    @Override
    protected void initEntityRenderers() {
        super.initEntityRenderers();
        ProjectileRenderers.init();
    }

    @Override
    public void initHandlers() {
        super.initHandlers();
        if (ModCompatibility.IGWModLoaded) {
            ModCompatibility.igwHandler = IGWHandler.getInstance();
        }
    }
}