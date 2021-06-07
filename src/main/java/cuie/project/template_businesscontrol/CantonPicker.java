package cuie.project.template_businesscontrol;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.*;
import java.util.regex.Pattern;

import javafx.beans.property.*;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import static java.lang.Math.abs;

public class CantonPicker extends Control {
    private static Locale CH = new Locale("de", "CH");
    private static final PseudoClass MANDATORY_CLASS = PseudoClass.getPseudoClass("mandatory");
    private static final PseudoClass INVALID_CLASS   = PseudoClass.getPseudoClass("invalid");
    private final String CSV_RESOURCE = "/files/cantons.csv";

    private static final String REGEX_ABBR_1 = "(^[abfgjlnostuvz][grilseuwhzod]|[ABFGJLNOSTUVZ][GRILSEUWHZOD]$)";
    private static final String REGEX_ABBR_2 = "(^[fjlou]|[FJLOU]$)";
    private static final String REGEX_NAME_1 = "(\\b[^\\d]+\\b)";

    private static final Pattern PATTERN_ABBR_1 = Pattern.compile(REGEX_ABBR_1);
    private static final Pattern PATTERN_ABBR_2 = Pattern.compile(REGEX_ABBR_2);
    private static final Pattern PATTERN_NAME_1 = Pattern.compile(REGEX_NAME_1);

    private final Map<String, Canton> cantonMap = new LinkedHashMap<>();

    private  ObjectProperty<Canton> cantonValue = new SimpleObjectProperty<Canton>();
    private final StringProperty cantonAbbrAsText = new SimpleStringProperty() {
        @Override
        public void set(String newValue) {
            super.set(newValue.toUpperCase());
        }
    };
    private final StringProperty cantonNameAsText = new SimpleStringProperty();

    private final BooleanProperty mandatory = new SimpleBooleanProperty() {
        @Override
        protected void invalidated() {
            pseudoClassStateChanged(MANDATORY_CLASS, get());
        }
    };

    private final BooleanProperty invalid = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            pseudoClassStateChanged(INVALID_CLASS, get());
        }
    };

    private final BooleanProperty readOnly     = new SimpleBooleanProperty();
    private final StringProperty  label        = new SimpleStringProperty();
    private final StringProperty  errorMessage = new SimpleStringProperty();


    public CantonPicker() {
        initializeSelf();
        initCantonList();
        addValueChangeListener();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new LegacySkin(this);
    }

    private void initializeSelf() {
         getStyleClass().add("business-control");
    }


    private void initCantonList() {
        try {
            File csvFile = new File((CantonPicker.class.getResource(CSV_RESOURCE).toURI()));
            BufferedReader br = new BufferedReader(new FileReader(csvFile, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    Canton c = new Canton(values[0], values[1], values[2]);
                    cantonMap.put(c.getUrlName(), c);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addValueChangeListener() {
        cantonAbbrAsText.addListener((observable, oldValue, userInput) -> {


            if (isMandatory() && (userInput == null || userInput.isEmpty())) {
                setInvalid(true);
                setErrorMessage("Mandatory Field");
                return;
            }

            if (PATTERN_ABBR_1.matcher(userInput).matches() || PATTERN_ABBR_2.matcher(userInput).matches()) {
                setInvalid(false);
                setErrorMessage(null);
                formatAbbreviation();
            } else {
                setInvalid(true);
                setErrorMessage("incorrect canton abbreviation");
            }
        });

        cantonNameAsText.addListener((observable, oldValue, userInput) -> {
            if (isMandatory() && (userInput == null || userInput.isEmpty())) {
                setInvalid(true);
                setErrorMessage("Mandatory Field");
                return;
            }

            if (PATTERN_NAME_1.matcher(userInput).matches() && validCanton()) {
                setInvalid(false);
                setErrorMessage(null);
            } else {
                setInvalid(true);
                setErrorMessage("incorrect canton name");
            }
        });

        cantonValueProperty().addListener((observable, oldValue, newValue) -> {
            setCantonAbbrAsText(newValue.getAbbreviation());
            setCantonNameAsText(newValue.getName());
        });
    }

    public void formatAbbreviation() {
        if (!getInvalid()) {

            Optional<Map.Entry<String, Canton>> c = cantonMap.entrySet()
                        .stream()
                        .filter(e -> {
                            String s = e.getValue().getAbbreviation();

                            if (getCantonAbbrAsText().length() == 1) {
                                s = s.substring(0, 1);
                            }
                            return s.toLowerCase().equals(getCantonAbbrAsText().toLowerCase());
                        })
                        .findFirst();

                c.ifPresent(stringCantonEntry -> setCantonValue(stringCantonEntry.getValue()));
        }
    }

    public void formatName() {
        if (!getInvalid()) {
            Optional<Map.Entry<String, Canton>> c = cantonMap.entrySet().stream()
                    .filter(e -> (e.getValue().getName().toLowerCase().contains(getCantonNameAsText().toLowerCase())
                                || e.getValue().getUrlName().toLowerCase().contains(getCantonNameAsText().toLowerCase()))
                    ).findFirst();

            c.ifPresent(stringCantonEntry -> setCantonValue(stringCantonEntry.getValue()));
        }
    }

    boolean validCanton() {
        String cantonName = getCantonNameAsText().toLowerCase();

        return cantonMap.entrySet()
                .stream()
                .anyMatch(e -> (
                        e.getValue().getName().toLowerCase().contains(cantonName)
                                || e.getValue().getUrlName().contains(cantonName))
                );
    }

    public void loadFonts(String... font){
        for(String f : font){
            Font.loadFont(getClass().getResourceAsStream(f), 0);
        }
    }

    public void addStylesheetFiles(String... stylesheetFile){
        for(String file : stylesheetFile){
            String stylesheet = getClass().getResource(file).toExternalForm();
            getStylesheets().add(stylesheet);
        }
    }

    void reset() {
        setCantonAbbrAsText(getCantonValue().getAbbreviation());
    }

    void decrease() {
        Iterator<Map.Entry<String, Canton>> it = cantonMap.entrySet().iterator();

        if (getCantonValue() == null) {
            setCantonValue(cantonMap.get("zug"));
        } else {
            while (it.hasNext()) {
                if (getCantonValue() == it.next().getValue()) {
                    if (it.hasNext()) setCantonValue(it.next().getValue());
                    break;
                }
            }
        }
    }

    void increase() {
        List<String> allKeys = new ArrayList<String>(cantonMap.keySet());
        Collections.reverse(allKeys);
        Iterator<String> it = allKeys.iterator();

        if (getCantonValue() == null) {
            setCantonValue(cantonMap.get("aargau"));
        } else {
            while (it.hasNext()) {
                if (getCantonValue() == cantonMap.get(it.next())) {
                    if (it.hasNext()) setCantonValue(cantonMap.get(it.next()));
                    break;
                }
            }
        }
    }

    // alle  Getter und Setter


    public Canton getCantonValue() {
        return cantonValue.getValue();
    }

    public ObjectProperty<Canton> cantonValueProperty() {
        return cantonValue;
    }

    public void setCantonValue(Canton canton) {
        this.cantonValue.setValue(cantonMap.get(canton.getUrlName()));
    }

    public void setCantonValueByUrlName(String urlName) {
        this.cantonValue.setValue(cantonMap.get(urlName));
    }

    public void setCantonValueByAbbreviation(String abbreviation) {
        String key = null;
        for (Map.Entry<String, Canton> e : cantonMap.entrySet()) {
            if (e.getValue().getAbbreviation().equals(abbreviation.toLowerCase())) key = e.getKey();
        }
        this.cantonValue.setValue(cantonMap.get(key));
    }

    public void setCantonValueByName(String name) {
        String key = null;
        for (Map.Entry<String, Canton> e : cantonMap.entrySet()) {
            if (e.getValue().getName().toLowerCase().equals(name.toLowerCase())) key = e.getKey();
        }
        this.cantonValue.setValue(cantonMap.get(key));
    }

    public String getCantonAbbrAsText() {
        return cantonAbbrAsText.get();
    }

    public StringProperty cantonAbbrAsTextProperty() {
        return cantonAbbrAsText;
    }

    public void setCantonAbbrAsText(String cantonAbbrAsText) {
        this.cantonAbbrAsText.set(cantonAbbrAsText);
    }

    public String getCantonNameAsText() {
        return cantonNameAsText.get();
    }

    StringProperty cantonNameAsTextProperty() {
        return cantonNameAsText;
    }

    void setCantonNameAsText(String cantonNameAsText) {
        this.cantonNameAsText.set(cantonNameAsText);
    }

    public boolean isReadOnly() {
        return readOnly.get();
    }

    public BooleanProperty readOnlyProperty() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly.set(readOnly);
    }

    public boolean isMandatory() {
        return mandatory.get();
    }

    public BooleanProperty mandatoryProperty() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory.set(mandatory);
    }

    public String getLabel() {
        return label.get();
    }

    public StringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public boolean getInvalid() {
        return invalid.get();
    }

    public BooleanProperty invalidProperty() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid.set(invalid);
    }

    public String getErrorMessage() {
        return errorMessage.get();
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.set(errorMessage);
    }

    public boolean isInvalid() {
        return invalid.get();
    }


}
