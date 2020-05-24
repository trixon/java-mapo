/*
 * Copyright 2018 Patrik Karlström.
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

import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.control.Tab;
import se.trixon.mapo.Mapo;

/**
 *
 * @author Patrik Karlström
 */
public class GluonMapsTab extends Tab {

    public GluonMapsTab() {
        setText("Gluon Maps");
        MapView mapView = new MapView();
        setContent(mapView);

        MapPoint mapPoint = new MapPoint(Mapo.MYLAT, Mapo.MYLON);
//        PoiLayer poiLayer = new PoiLayer();
//        poiLayer.addPoint(mapPoint, new Circle(8, Color.RED));
//        mapView.addLayer(poiLayer);
        mapView.setZoom(3);

        mapView.flyTo(1., mapPoint, 2.);
    }

}
