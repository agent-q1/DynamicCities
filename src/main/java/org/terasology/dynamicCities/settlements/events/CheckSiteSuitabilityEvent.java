/*
 * Copyright 2017 MovingBlocks
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
package org.terasology.dynamicCities.settlements.events;

import org.terasology.entitySystem.event.ConsumableEvent;

/**
 * Issued when the settlement manager wants to know if a site is suitable for creating a settlement.
 */
public class CheckSiteSuitabilityEvent implements ConsumableEvent {
    private SettlementFilterResult result = SettlementFilterResult.UNKNOWN;
    private boolean consumed;

    @Override
    public boolean isConsumed() {
        return consumed;
    }

    @Override
    public void consume() {
        this.consumed = true;
    }

    public SettlementFilterResult getResult() {
        return result;
    }

    public void setResult(SettlementFilterResult result) {
        this.result = result;
    }
}
