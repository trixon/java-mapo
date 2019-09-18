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

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.Tab;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import se.trixon.mapo.Mapo;

/**
 *
 * @author Patrik Karlström
 */
public class JXMapViewer2 extends Tab {

    public JXMapViewer2() {
        setText("JXMapViewer");
        final SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);
        setContent(swingNode);
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            final JXMapKit jXMapKit = new JXMapKit();
            TileFactoryInfo info = new OSMTileFactoryInfo();
            DefaultTileFactory tileFactory = new DefaultTileFactory(info);
            jXMapKit.setTileFactory(tileFactory);

            final GeoPosition gp = new GeoPosition(Mapo.MYLAT, Mapo.MYLON);

            final JToolTip tooltip = new JToolTip();
            tooltip.setTipText("Mölndal");
            tooltip.setComponent(jXMapKit.getMainMap());
            jXMapKit.getMainMap().add(tooltip);

            jXMapKit.setZoom(5);
            jXMapKit.setAddressLocation(gp);

            jXMapKit.getMainMap().addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    // ignore
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    JXMapViewer map = jXMapKit.getMainMap();

                    // convert to world bitmap
                    Point2D worldPos = map.getTileFactory().geoToPixel(gp, map.getZoom());

                    // convert to screen
                    Rectangle rect = map.getViewportBounds();
                    int sx = (int) worldPos.getX() - rect.x;
                    int sy = (int) worldPos.getY() - rect.y;
                    Point screenPos = new Point(sx, sy);

                    // check if near the mouse
                    if (screenPos.distance(e.getPoint()) < 20) {
                        screenPos.x -= tooltip.getWidth() / 2;

                        tooltip.setLocation(screenPos);
                        tooltip.setVisible(true);
                    } else {
                        tooltip.setVisible(false);
                    }
                }
            });

            swingNode.setContent(jXMapKit);
        });
    }
}
