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
package se.trixon.mapo.ui;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.trixon.mapo.ui.tabs.GMapsFXTab;
import se.trixon.mapo.ui.tabs.GluonMapsTab;
import se.trixon.mapo.ui.tabs.JXMapViewer2;
import se.trixon.mapo.ui.tabs.MapJfx;

/**
 *
 * @author Patrik Karlström
 */
public class MainApp extends Application {

    public static final String APP_TITLE = "Mapo";
    private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());
    private BorderPane mRoot;
    private Stage mStage;
    private final TabPane mTabPane = new TabPane();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mStage = primaryStage;
        primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("majincline-globe-1024px.png")));

        createUI();
        initAccelerators();
        mStage.setTitle(APP_TITLE + " - a collection of map renderers for Java desktop applications");
        mStage.show();
    }

    private void createUI() {
        mRoot = new BorderPane();
        Scene scene = new Scene(mRoot, 600, 600);
        mRoot.setCenter(mTabPane);
        mStage.setScene(scene);

        mTabPane.getTabs().addAll(
                new MapJfx(),
                new GluonMapsTab(),
                new GMapsFXTab(),
                new JXMapViewer2()
        );
    }

    private void initAccelerators() {
        final ObservableMap<KeyCombination, Runnable> accelerators = mStage.getScene().getAccelerators();

        accelerators.put(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN), (Runnable) () -> {
            mStage.fireEvent(new WindowEvent(mStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });

        accelerators.put(new KeyCodeCombination(KeyCode.ESCAPE), (Runnable) () -> {
            mStage.fireEvent(new WindowEvent(mStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
    }
}
