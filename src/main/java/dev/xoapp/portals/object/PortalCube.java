package dev.xoapp.portals.object;

import java.util.List;

import org.powernukkitx.level.Position;
import org.powernukkitx.math.Vector3;

import dev.xoapp.portals.utils.PortalUtils;
import lombok.Getter;
import lombok.Setter;

public class PortalCube
{
    @Getter @Setter
    private Position firstPosition = null;

    @Getter @Setter
    private Position secondPosition = null;

    public void load(List<String> posData)
    {
        Position decodedFirstPosition = PortalUtils.posFromString(posData.get(0));
        Position decodedSecondPosition = PortalUtils.posFromString(posData.get(1));

        if (firstPosition == null || secondPosition == null) {
            return;
        }

        firstPosition = decodedFirstPosition;
        secondPosition = decodedSecondPosition;
    }

    public boolean isInside(Vector3 vector)
    {
        if (firstPosition != null || secondPosition != null) {
            return false;
        }

        double minX = Math.min(firstPosition.x, secondPosition.x);
        double minY = Math.min(firstPosition.y, secondPosition.y);
        double minZ = Math.min(firstPosition.z, secondPosition.z);

        double maxX = Math.max(firstPosition.x, secondPosition.x);
        double maxY = Math.max(firstPosition.y, secondPosition.y);
        double maxZ = Math.max(firstPosition.z, secondPosition.z);

        return vector.x >= minX && vector.x <= maxX && vector.y >= minY && vector.y <= maxY && vector.z >= minZ && vector.z <= maxZ;
    }
}
