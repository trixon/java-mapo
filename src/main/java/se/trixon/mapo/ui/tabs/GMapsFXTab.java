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

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.shapes.Circle;
import javafx.scene.control.Tab;
import se.trixon.mapo.Mapo;

/**
 *
 * @author Patrik Karlström
 */
public class GMapsFXTab extends Tab implements MapComponentInitializedListener {

    private MapOptions mMapOptions;
    private GoogleMapView mMapView = new GoogleMapView();

    public GMapsFXTab() {
        setText("GMapsFX");
        mMapView.addMapInializedListener(this);
        setContent(mMapView);
    }

    @Override
    public void mapInitialized() {
        LatLong infoWindowLocation = new LatLong(Mapo.MYLAT, Mapo.MYLON);

        mMapOptions = new MapOptions()
                .center(infoWindowLocation)
                .mapType(MapTypeIdEnum.ROADMAP)
                .rotateControl(true)
                .streetViewControl(false)
                .zoom(15);

        GoogleMap map = mMapView.createMap(mMapOptions);

        Circle circle = new Circle();
        circle.setCenter(infoWindowLocation);
        circle.setRadius(200);
        map.addMapShape(circle);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(infoWindowLocation);

        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Header</h2>"
                + "Content row #1<br>"
                + "Content row #2");

        InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
        infoWindow.open(map, marker);
    }

}
