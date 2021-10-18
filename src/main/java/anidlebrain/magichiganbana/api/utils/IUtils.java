package anidlebrain.magichiganbana.api.utils;
import anidlebrain.magichiganbana.impl.INumber;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
/**
 * @author Andileabrain
 */
@ZenRegister
@ZenClass("mods.magichiganbana.IUtils")
@SuppressWarnings("unused")
public interface IUtils {

    @ZenMethod
    static boolean bitwiseJudgment(int num, int bit)
    {
            return (num & (1 << bit)) == 1;
    }

    @ZenMethod
    static int binaryCreation(String binary)
    {
        return INumber.binaryCreation(binary);
    }

    @ZenMethod
    static int findNextNum(int num)
    {
        return INumber.findNextNum(num);
    }

    @ZenMethod
    static int judgeLevel(int num)
    {
        return INumber.judgeLevel(num);
    }
}
