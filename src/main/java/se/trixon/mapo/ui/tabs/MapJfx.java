/*
 * Copyright 2019 Patrik Karlström.
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
package se.trixon.mapo.ui.tabs;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapType;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.scene.control.Tab;
import se.trixon.mapo.Mapo;

/**
 *
 * @author Patrik Karlström
 */
public class MapJfx extends Tab {

    private final Coordinate mCoordinate = new Coordinate(Mapo.MYLAT, Mapo.MYLON);
    private MapView mMapView;

    public MapJfx() {
        setText("MapJfx");
        mMapView = new MapView();
        mMapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterMapIsInitialized();
            }
        });
        //mMapView.setCustomMapviewCssURL(getClass().getResource("/custom_mapview.css"));
        setupEventHandlers();

        mMapView.initialize();
        setContent(mMapView);
    }

    private void afterMapIsInitialized() {
        mMapView.setMapType(MapType.OSM);
        mMapView.setZoom(12);
        mMapView.setCenter(mCoordinate);
    }

    private void setupEventHandlers() {
        mMapView.addEventHandler(MapViewEvent.MAP_EXTENT, event -> {
            event.consume();
            mMapView.setExtent(event.getExtent());
        });
    }
}
