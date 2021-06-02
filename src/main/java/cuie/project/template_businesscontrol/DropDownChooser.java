package cuie.project.template_businesscontrol;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        aargau.setOnMouseClicked(event -> {
            System.out.println("aargau clicked");
        });
        jura.setOnMouseClicked(event -> {
            System.out.println("jura clicked");
        });

    }

}
