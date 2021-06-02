package cuie.project.template_businesscontrol;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


class DropDownChooser extends VBox {
    private static final String STYLE_CSS = "dropDownChooser.css";

    private final BusinessControl businessControl;

    private VBox mainVBox;
    private StackPane mapStackPane;
    private HBox infoHBox;
    private TextField shortName;
    private TextField longName;
    Region spacer;
    private Button okButton;


    private final ImageView aargau =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/aargau.png").toExternalForm()));
    private final ImageView appenzellAusserrhoden = new ImageView(
        new Image(DropDownChooser.class.getResource("/images/appenzellAusserrhoden.png").toExternalForm()));
    private final ImageView appenzellInnerrhoden = new ImageView(
        new Image(DropDownChooser.class.getResource("/images/appenzellInnerrhoden.png").toExternalForm()));
    private final ImageView base =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/base.png").toExternalForm()));
    private final ImageView baselLand =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/baselLand.png").toExternalForm()));
    private final ImageView baselStadt =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/baselStadt.png").toExternalForm()));
    private final ImageView bern =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/bern.png").toExternalForm()));
    private final ImageView fribourg =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/fribourg.png").toExternalForm()));
    private final ImageView genf =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/genf.png").toExternalForm()));
    private final ImageView glarus =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/glarus.png").toExternalForm()));
    private final ImageView graubuenden =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/graubuenden.png").toExternalForm()));
    private final ImageView jura =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/jura.png").toExternalForm()));
    private final ImageView luzern =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/luzern.png").toExternalForm()));
    private final ImageView neuenburg =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/neuenburg.png").toExternalForm()));
    private final ImageView nidwalden =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/nidwalden.png").toExternalForm()));
    private final ImageView obwalden =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/obwalden.png").toExternalForm()));
    private final ImageView schaffhausen =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/schaffhausen.png").toExternalForm()));
    private final ImageView schwyz =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/schwyz.png").toExternalForm()));
    private final ImageView solothurn =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/solothurn.png").toExternalForm()));
    private final ImageView stgallen =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/stgallen.png").toExternalForm()));
    private final ImageView tessin =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/tessin.png").toExternalForm()));
    private final ImageView thurgau =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/thurgau.png").toExternalForm()));
    private final ImageView uri =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/uri.png").toExternalForm()));
    private final ImageView waadt =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/waadt.png").toExternalForm()));
    private final ImageView wallis =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/wallis.png").toExternalForm()));
    private final ImageView zuerich =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/zuerich.png").toExternalForm()));
    private final ImageView zug =
        new ImageView(new Image(DropDownChooser.class.getResource("/images/zug.png").toExternalForm()));


    DropDownChooser(BusinessControl businessControl) {
        this.businessControl = businessControl;
        initializeSelf();
        initializeParts();
        layoutParts();
        setupBindings();
        setupEventHandlers();
    }

    private void initializeSelf() {
        getStyleClass().add("drop-down-chooser");

        String stylesheet = getClass().getResource(STYLE_CSS).toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeParts() {
        shortName = new TextField("BE");
        longName = new TextField("Bern");

        shortName.setMaxWidth(64);
        longName.setMaxWidth(196);
        shortName.getStyleClass().add("custom-text-field");
        longName.getStyleClass().add("custom-text-field");

        okButton = new Button("OK");
        okButton.getStyleClass().add("custom-button");

        spacer = new Region();

        mainVBox = new VBox(8);
        infoHBox = new HBox(8);

        mapStackPane =
            new StackPane(base, aargau, appenzellAusserrhoden, appenzellInnerrhoden, baselLand, baselStadt, bern,
                fribourg, genf, glarus, graubuenden, jura, luzern, neuenburg, nidwalden, obwalden, schaffhausen, schwyz,
                solothurn, stgallen, tessin, thurgau, uri, waadt, wallis, zuerich, zug);
    }

    private void layoutParts() {

        HBox.setHgrow(spacer, Priority.ALWAYS);


        infoHBox.getChildren().addAll(shortName, longName, spacer, okButton);
        mainVBox.getChildren().addAll(mapStackPane, infoHBox);
        getChildren().addAll(mainVBox);

    }

    private void setupBindings() {
    }

    private void setupEventHandlers() {

        okButton.setOnAction(actionEvent -> {
            //close popup
        });

        aargau.setOnMouseClicked(event -> { colorizeImage(aargau); });
        appenzellAusserrhoden.setOnMouseClicked(event -> { colorizeImage(appenzellAusserrhoden); });
        appenzellInnerrhoden.setOnMouseClicked(event -> { colorizeImage(appenzellInnerrhoden); });
        baselLand.setOnMouseClicked(event -> { colorizeImage(baselLand); });
        baselStadt.setOnMouseClicked(event -> { colorizeImage(baselStadt); });
        bern.setOnMouseClicked(event -> { colorizeImage(bern); });
        fribourg.setOnMouseClicked(event -> { colorizeImage(fribourg); });
        genf.setOnMouseClicked(event -> { colorizeImage(genf); });
        glarus.setOnMouseClicked(event -> { colorizeImage(glarus); });
        graubuenden.setOnMouseClicked(event -> { colorizeImage(graubuenden); });
        jura.setOnMouseClicked(event -> { colorizeImage(jura); });
        luzern.setOnMouseClicked(event -> { colorizeImage(luzern); });
        neuenburg.setOnMouseClicked(event -> { colorizeImage(neuenburg); });
        nidwalden.setOnMouseClicked(event -> { colorizeImage(nidwalden); });
        obwalden.setOnMouseClicked(event -> { colorizeImage(obwalden); });
        schaffhausen.setOnMouseClicked(event -> { colorizeImage(schaffhausen); });
        schwyz.setOnMouseClicked(event -> { colorizeImage(schwyz); });
        solothurn.setOnMouseClicked(event -> { colorizeImage(solothurn); });
        stgallen.setOnMouseClicked(event -> { colorizeImage(stgallen); });
        tessin.setOnMouseClicked(event -> { colorizeImage(tessin); });
        thurgau.setOnMouseClicked(event -> { colorizeImage(thurgau); });
        uri.setOnMouseClicked(event -> { colorizeImage(uri); });
        waadt.setOnMouseClicked(event -> { colorizeImage(waadt); });
        wallis.setOnMouseClicked(event -> { colorizeImage(wallis); });
        zuerich.setOnMouseClicked(event -> { colorizeImage(zuerich); });
        zug.setOnMouseClicked(event -> { colorizeImage(zug); });


    }


    private void colorizeImage(ImageView canton) {
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1);

        aargau.setEffect(monochrome);
        appenzellAusserrhoden.setEffect(monochrome);
        appenzellInnerrhoden.setEffect(monochrome);
        baselLand.setEffect(monochrome);
        baselStadt.setEffect(monochrome);
        bern.setEffect(monochrome);
        fribourg.setEffect(monochrome);
        genf.setEffect(monochrome);
        glarus.setEffect(monochrome);
        graubuenden.setEffect(monochrome);
        jura.setEffect(monochrome);
        luzern.setEffect(monochrome);
        neuenburg.setEffect(monochrome);
        nidwalden.setEffect(monochrome);
        obwalden.setEffect(monochrome);
        schaffhausen.setEffect(monochrome);
        schwyz.setEffect(monochrome);
        solothurn.setEffect(monochrome);
        stgallen.setEffect(monochrome);
        tessin.setEffect(monochrome);
        thurgau.setEffect(monochrome);
        uri.setEffect(monochrome);
        waadt.setEffect(monochrome);
        wallis.setEffect(monochrome);
        zuerich.setEffect(monochrome);
        zug.setEffect(monochrome);

        canton.setEffect(null);


    }

}
