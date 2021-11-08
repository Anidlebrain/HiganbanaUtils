package anidlebrain.magichiganbana.impl;

/**
 * @author Andileabrain
 */
public class INumber {
    private static final boolean[] m_primeFlag = new boolean[101];
    INumber() {

    }

    public static void init() {
        m_primeFlag[0] = false;
        m_primeFlag[1] = false;
        m_primeFlag[2] = false;

        for(int i = 3; i < 100; ++i) {
            m_primeFlag[i] = (i & 1) != 1;
        }

        for (int i = 3; i < 100; i = i + 2) {
            if(m_primeFlag[i])continue;
            m_primeFlag[i] = false;
            for (int j = i << 1; j < 100; j += i)
            {
                m_primeFlag[j] = true;
            }
        }
    }

    public static int binaryCreation(String binary) {
        if (binary == null || binary.length() == 0) {
            return 0;
        }
        int res = 0;
        for (int i = binary.length(); i >=0 ; --i) {
            res = (res << 1) | (binary.indexOf(i) - '0');
        }
        return res;
    }

    public static int findNextNum(int num) {
        int i;
        for(i = num; i < 100 && m_primeFlag[i]; ++i) {

        }
        return i;
    }

    public static int judgeLevel(int num) {
        if ((num & 1) == 0) {
            return -1;
        }
        for (int i = 3; i * i <= num; i +=2) {
            if (num % i == 0)
            {
                return -1;
            }
        }
        int res = 0;
        for (int i = 0; i < num && i < 100; ++i) {
            if (!m_primeFlag[i]) {
                ++res;
            }
        }
        if (num > 100) {
            res += num;
        }
        return res;
    }

}
