/*
 * Copyright 2016 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.dynamicCities.world;

import org.terasology.core.world.generator.facetProviders.BiomeProvider;
import org.terasology.core.world.generator.facetProviders.DefaultFloraProvider;
import org.terasology.core.world.generator.facetProviders.PerlinBaseSurfaceProvider;
import org.terasology.core.world.generator.facetProviders.PerlinHillsAndMountainsProvider;
import org.terasology.core.world.generator.facetProviders.PerlinHumidityProvider;
import org.terasology.core.world.generator.facetProviders.PerlinOceanProvider;
import org.terasology.core.world.generator.facetProviders.PerlinRiverProvider;
import org.terasology.core.world.generator.facetProviders.PerlinSurfaceTemperatureProvider;
import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.core.world.generator.facetProviders.SurfaceToDensityProvider;
import org.terasology.core.world.generator.rasterizers.FloraRasterizer;
import org.terasology.dynamicCities.region.RegionEntityProvider;
import org.terasology.dynamicCities.region.ResourceProvider;
import org.terasology.dynamicCities.region.RoughnessProvider;
import org.terasology.dynamicCities.settlements.SettlementFacetProvider;
import org.terasology.dynamicCities.sites.SiteFacetProvider;
import org.terasology.dynamicCities.world.trees.DefaultTreeProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.spawner.FixedSpawner;
import org.terasology.math.geom.ImmutableVector2i;
import org.terasology.math.geom.Vector3f;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

/**
 */
@RegisterWorldGenerator(id = "DynamicCitiesPerlin", displayName = "DynCitiesPerlin", description = "Faceted world generator")
public class PerlinFacetedWorldGenerator extends BaseFacetedWorldGenerator {

    private final FixedSpawner spawner = new FixedSpawner(0, 0);

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    public PerlinFacetedWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @Override
    public Vector3f getSpawnPosition(EntityRef entity) {
        return spawner.getSpawnPosition(getWorld(), entity);
    }

    @Override
    protected WorldBuilder createWorld() {
        int seaLevel = 32;
        ImmutableVector2i spawnPos = new ImmutableVector2i(0, 0); // as used by the spawner

        return new WorldBuilder(worldGeneratorPluginLibrary)
                .setSeaLevel(seaLevel)
                .addProvider(new SeaLevelProvider(seaLevel))
                .addProvider(new PerlinHumidityProvider())
                .addProvider(new PerlinSurfaceTemperatureProvider())
                .addProvider(new PerlinBaseSurfaceProvider())
                .addProvider(new PerlinRiverProvider())
                .addProvider(new PerlinOceanProvider())
                .addProvider(new PerlinHillsAndMountainsProvider())
                .addProvider(new BiomeProvider())
                .addProvider(new SurfaceToDensityProvider())
                .addProvider(new RoughnessProvider())
                .addProvider(new SiteFacetProvider())
                .addProvider(new SettlementFacetProvider())
                .addProvider(new DefaultFloraProvider())
                .addProvider(new DefaultTreeProvider())
                //.addProvider(new PlateauProvider(spawnPos, seaLevel + 4, 10, 30))
                .addProvider(new ResourceProvider())
                .addEntities(new RegionEntityProvider())
                .addRasterizer(new SolidRasterizer())
                .addPlugins()
                .addRasterizer(new FloraRasterizer())
                .addRasterizer(new TreeRasterizer());
    }
}
