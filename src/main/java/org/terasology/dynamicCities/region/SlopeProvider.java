package org.terasology.dynamicCities.region;


import org.terasology.dynamicCities.facets.RoughnessFacet;
import org.terasology.dynamicCities.facets.SlopeFacet;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2i;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Produces(SlopeFacet.class)
@Requires({@Facet(value = SurfaceHeightFacet.class)})

public class SlopeProvider implements FacetProvider {

    private final int gridSize = 5;


    @Override
    public void setSeed(long seed) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(RoughnessFacet.class);
        SlopeFacet facet = new SlopeFacet(region.getRegion(), border, gridSize);

        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
        Rect2i processRegion = facet.getGridWorldRegion();

        for (BaseVector2i pos : processRegion.contents()) {

            facet.calcSlope(new Vector2i(pos.x(), pos.y()), surfaceHeightFacet, 1);

        }

    }
}
