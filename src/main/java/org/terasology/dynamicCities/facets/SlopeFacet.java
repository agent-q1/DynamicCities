package org.terasology.dynamicCities.facets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.math.Region3i;
import org.terasology.math.geom.Vector2i;
import org.terasology.rendering.nui.widgets.UITabBox;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.facets.base.BaseFieldFacet2D;


public class SlopeFacet extends Grid2DFloatFacet {

    private static final Logger logger = LoggerFactory.getLogger(SlopeFacet.class);

    public SlopeFacet(Region3i targetRegion, Border3D border, int gridSize) {
        super(targetRegion, border, gridSize);
    }


    /**
     * Calculates and sets the slope value for a point in the terrain
     *
     * @param gridpoint   The point for which slope is to be calculated
     * @param granularity The level of precision you want to measure the terrain's slope with.
     * @param facet The SurfaceHeightFacet that gives information about the height of the terrain at different points.
     */

    public void calcSlope(Vector2i gridpoint, BaseFieldFacet2D facet, int granularity) {

        float slopex = (facet.getWorld(gridpoint.x() + granularity, gridpoint.y()) - facet.getWorld(gridpoint.x(), gridpoint.y())) / (float) granularity;
        float slopey = (facet.getWorld(gridpoint.x(), gridpoint.y() + granularity) - facet.getWorld(gridpoint.x(), gridpoint.y())) / (float) granularity;
        float slope = (float) Math.sqrt(slopex * slopex + slopey * slopey);

        logger.debug("Slope is {}",slope);



        setWorld(gridpoint, slope);

    }



}
