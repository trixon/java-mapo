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

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLJPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.Tab;
import javax.swing.SwingUtilities;

/**
 *
 * @author Patrik Karlström
 */
public class WorldWind extends Tab {

    public WorldWind() {
        setText("WorldWind");
        final SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);
        setContent(swingNode);
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            WorldWindowGLJPanel panel = new WorldWindowGLJPanel();
            Model m = (Model) gov.nasa.worldwind.WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
            panel.setModel(m);

            swingNode.setContent(panel);
        });
    }
}
