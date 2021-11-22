package anidlebrain.magichiganbana.api.mods.jeresources;

import anidlebrain.magichiganbana.MagicHiganbana;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntityLivingBase;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.render.TextModifier;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import net.minecraft.entity.EntityLivingBase;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.*;

/**
 * @author Andileabrain
 */

@ZenRegister
@ZenClass("mods.magichiganbana.jeresources.JEIRMobEntry")
@SuppressWarnings("unused")
public class JEIRMobEntry {
    private final EntityLivingBase entity;
    private final Set<LootDrop> drops;
    private LightLevel lightLevel;
    private final List<String> biomes;
    private int minExp, maxExp;

    private JEIRMobEntry(EntityLivingBase entity)
    {
        this.entity = entity;
        this.biomes = new ArrayList<>();
        this.biomes.add("jer.any");
        this.drops = new TreeSet<>();
        this.minExp = 0;
        this.maxExp = 0;
        this.lightLevel = LightLevel.any;
    }

    @ZenMethod
    public static JEIRMobEntry addMob(IEntityLivingBase entity) {
        return new JEIRMobEntry(CraftTweakerMC.getEntityLivingBase(entity));
    }

    @ZenMethod
    public static void removeAll() {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                MobRegistry.getInstance().clear();
            }

            @Override
            public String describe() {
                return "removeAll Mob";
            }
        });

    }

    @ZenMethod
    public static void removeEntry(IEntityLivingBase entity) {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                MobRegistry.getInstance().getMobs().removeIf(mobEntry -> mobEntry.getEntity().getName().equals(CraftTweakerMC.getEntityLivingBase(entity).getName()));
            }

            @Override
            public String describe() {
                return "remove Mob " + entity.getDisplayName();
            }
        });

    }

    @ZenMethod
    public JEIRMobEntry setExp(int xp) {
        minExp = xp;
        maxExp = xp;
        return this;
    }

    @ZenMethod
    public JEIRMobEntry setMinExp(int xp) {
        minExp = xp;
        return this;
    }

    @ZenMethod
    public JEIRMobEntry setMaxExp(int xp) {
        maxExp = xp;
        return this;
    }

    @ZenMethod
    public JEIRMobEntry setLightLevel(int level) {
        if (level == -1) {
           lightLevel = LightLevel.any;
        }
        else if (level == 4) {
            lightLevel = LightLevel.bat;
        }
        else if (level == 8) {
            lightLevel = LightLevel.hostile;
        }
        else if (level == 12) {
            lightLevel = LightLevel.blaze;
        }
        else {
            CraftTweakerAPI.logError("level must be -1/4/8/12 !");
        }
        return this;
    }

    @ZenMethod
    public JEIRMobEntry setBiomes(String[] biomes) {
        this.biomes.clear();
        this.biomes.addAll(Arrays.asList(biomes));
        return this;
    }

    @ZenMethod
    public JEIRMobEntry addBiome(String biome) {
        biomes.add(biome);
        return this;
    }

    @ZenMethod
    public JEIRMobEntry addDrop(IItemStack itemStack, int minDrop, int maxDrop, float chance, int fortuneLevel, String[] condition) {
        Conditional[] conditional = Arrays.stream(condition).map(s -> new Conditional(s, TextModifier.black)).toArray(Conditional[]::new);
        LootDrop lootDrop = new LootDrop(CraftTweakerMC.getItemStack(itemStack), minDrop, maxDrop, chance, fortuneLevel,  conditional);
        drops.add(lootDrop);
        return this;
    }

    @ZenMethod
    public JEIRMobEntry addDrop(IItemStack itemStack, int minDrop, int maxDrop, float chance, int fortuneLevel, String condition) {
        return addDrop(itemStack, minDrop, maxDrop, chance, fortuneLevel, new String[]{condition});
    }

    @ZenMethod
    public JEIRMobEntry addDrop(IItemStack itemStack, int minDrop, int maxDrop, float chance, String condition) {
        return addDrop(itemStack, minDrop, maxDrop, chance, 0, new String[]{condition});
    }

    @ZenMethod
    public JEIRMobEntry addDrop(IItemStack itemStack, int minDrop, int maxDrop, String condition) {
        return addDrop(itemStack, minDrop, maxDrop, 1F, 0, new String[]{condition});
    }

    @ZenMethod
    public JEIRMobEntry addDrop(IItemStack itemStack, float chance, String condition) {
        return addDrop(itemStack, 0, 1, chance, condition);
    }

    @ZenMethod
    public void register() {
        MagicHiganbana.queuePostInitAction(new IAction() {
            @Override
            public void apply() {
                MobRegistry.getInstance().registerMob(new MobEntry(entity, lightLevel, minExp, maxExp,
                        (String [])biomes.toArray(), (LootDrop[]) drops.toArray()));
            }

            @Override
            public String describe() {
                return "register Mob " + entity.getDisplayName();
            }
        });

    }
}
