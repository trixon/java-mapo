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
package se.trixon.mapo.ui;

import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.trixon.almond.util.SystemHelper;
import se.trixon.almond.util.fx.AlmondFx;

/**
 *
 * @author Patrik Karlström
 */
public class MainApp extends Application {

    public static final String APP_TITLE = "Mapo";
    private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());
    private final AlmondFx mAlmondFX = AlmondFx.getInstance();
    private final ResourceBundle mBundle = SystemHelper.getBundle(MainApp.class, "Bundle");
    private BorderPane mRoot;
    private Stage mStage;

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

        mAlmondFX.addStageWatcher(primaryStage, MainApp.class);
        createUI();
        postInit();
        initAccelerators();
        initListeners();
        mStage.setTitle(APP_TITLE);
        mStage.show();
    }

    private void createUI() {
        mRoot = new BorderPane();
        Scene scene = new Scene(mRoot);

        mStage.setScene(scene);
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

    private void initListeners() {
    }

    private void postInit() {
    }
}
