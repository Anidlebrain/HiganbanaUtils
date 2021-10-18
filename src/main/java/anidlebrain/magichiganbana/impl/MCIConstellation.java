package anidlebrain.magichiganbana.impl;

import anidlebrain.magichiganbana.api.mods.astralsorcery.IConstellation;
import hellfirepvp.astralsorcery.common.constellation.ConstellationBase;
import hellfirepvp.astralsorcery.common.constellation.ConstellationRegistry;
import hellfirepvp.astralsorcery.common.constellation.star.StarLocation;

import java.awt.Color;

public class MCIConstellation implements IConstellation {
    private final ConstellationBase m_starbase;
    private final StarLocation[] m_starList = new StarLocation[10];
    private int m_starNum = 0;

    public MCIConstellation(String name, int level, int color)
    {
        if (level == 1) {
            m_starbase = new ConstellationBase.Major(name, new Color(color));
        }
        else if (level == 2) {
            m_starbase = new ConstellationBase.Weak(name, new Color(color));
        }
        else if (level == 3) {
            m_starbase = new ConstellationBase.Minor(name, new Color(color));
        }
        else {
            m_starbase = null;
        }
        m_starNum = 0;
    }

    @Override
    public int addStar(int x, int y) {
        if (m_starNum > 9 || m_starbase == null) {
            return -1;
        }
        m_starList[m_starNum] = m_starbase.addStar(x, y);
        ++m_starNum;
        return m_starNum;
    }

    @Override
    public void addConnection(int x, int y) {
        if (m_starbase != null && x > 0 && y > 0 && x <= m_starNum && y <= m_starNum) {
            m_starbase.addConnection(m_starList[x - 1], m_starList[y - 1]);
        }
    }

    @Override
    public void register(){
        if (m_starbase != null) {
            ConstellationRegistry.registerConstellation(m_starbase);
        }
    }

}
