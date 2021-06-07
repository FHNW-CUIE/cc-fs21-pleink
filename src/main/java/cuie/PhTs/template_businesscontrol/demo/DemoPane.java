package cuie.PhTs.template_businesscontrol.demo;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import cuie.PhTs.template_businesscontrol.CantonPicker;

class DemoPane extends BorderPane {
    private CantonPicker cantonPicker;

    private TextField abbrTextField;

    private CheckBox  readOnlyBox;
    private CheckBox  mandatoryBox;
    private TextField labelField;

    private PresentationModel model;

    DemoPane(PresentationModel model) {
        this.model = model;

        initializeControls();
        layoutControls();
        setupEventHandlers();
        setupBindings();
    }

    private void initializeControls() {
        setPadding(new Insets(10));

        cantonPicker = new CantonPicker();

        abbrTextField = new TextField();

        readOnlyBox = new CheckBox();
        readOnlyBox.setSelected(false);

        mandatoryBox = new CheckBox();
        mandatoryBox.setSelected(true);

        labelField = new TextField();
    }

    private void layoutControls() {
        setCenter(cantonPicker);
        VBox box = new VBox(10,
                            new Label("Business Control Properties"),
                            new Label("Canton")      , abbrTextField,
                            new Label("readOnly") , readOnlyBox,
                            new Label("mandatory"), mandatoryBox,
                            new Label("Label")    , labelField);
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        setRight(box);
    }

    private void setupEventHandlers() {
        abbrTextField.setOnAction(event -> cantonPicker.formatAbbreviation());
    }

    private void setupBindings() {
        abbrTextField.textProperty()   .bindBidirectional(model.cantonAbbrProperty());
        labelField.textProperty()      .bindBidirectional(model.age_LabelProperty());
        readOnlyBox.selectedProperty() .bindBidirectional(model.age_readOnlyProperty());
        mandatoryBox.selectedProperty().bindBidirectional(model.age_mandatoryProperty());

        cantonPicker.cantonAbbrAsTextProperty().bindBidirectional(model.cantonAbbrProperty());
        cantonPicker.labelProperty()    .bind(model.age_LabelProperty());
        cantonPicker.readOnlyProperty() .bind(model.age_readOnlyProperty());
        cantonPicker.mandatoryProperty().bind(model.age_mandatoryProperty());

    }

}
