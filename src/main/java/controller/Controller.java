package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Model;
import model.ModelDAO;
import model.szavak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 * @author Birki László
 */
public class Controller implements Initializable {
    /**
     * Slf4j logger.
     */
    public final static Logger logger = LoggerFactory.getLogger(Controller.class);
    /**
     * szavak adatbázistáblából kiolvasott összes szót tartalmazó lista.
     */
    private List<szavak> beolvasottSzavak = new ArrayList<>();
    /**
     * ModelDAO osztály példányosítása.
     */
    private ModelDAO md;
    /**
     * Modell osztály példányosítása.
     */
    private Model m = new Model();
    /**
     * A helyes megoldás pozíciójának tárolása.
     */
    private int correctNumber;
    /**
     * A választott rádiógomb sorszámának tárolása.
     */
    private int answerNumber;
    /**
     * Kezdő képernyő.
      */
    @FXML
    private Pane basePane;
    /**
     * A kérdés.
     */
    @FXML
    private Label questionLabel;
    /**
     * Első lehetséges válasz a tesztnél.
     */
    @FXML
    private RadioButton answer1Radio;
    /**
     * Második lehetséges válasz a tesztnél.
     */
    @FXML
    private RadioButton answer2Radio;
    /**
     * Harmadik lehetséges válasz a tesztnél.
     */
    @FXML
    private RadioButton answer3Radio;
    /**
     * Negyedik lehetséges válasz a tesztnél.
     */
    @FXML
    private RadioButton answer4Radio;
    /**
     * Rádiógombok csoportja.
     */
    @FXML
    private ToggleGroup group1;
    /**
     * Folyamat indítása gomb.
     */
    @FXML
    private Button goButton;
    /**
     * Kilépés gomb.
     */
    @FXML
    private Button exitButton;
    /**
     * Helyes volt-e a válasz, grafikus visszajelzés.
     */
    @FXML
    private ImageView isCorrectImageView;
    /**
     * PopUp ablak.
     */
    @FXML
    private Pane alertPane;
    /**
     * PopUp ablak üzenete.
      */
    @FXML
    private Label alertLabel;
    /**
     * Kilépés.
     * @param event a megnyomott gomb eseménykezelője.
     */
    @FXML
    void exitButtonAction(ActionEvent event) {
        logger.info("Sikeres kilépés");
        System.exit(0);
    }

    /**
     * Funkciót elindító gomb.
     * @param event a megnyomott gomb eseménykezelője.
     */
    @FXML
    void goButtonAction(ActionEvent event) {
        if(goButton.getText().equals("Mehet")){
            if(answer1Radio.isSelected() == false && answer2Radio.isSelected() == false &&
               answer3Radio.isSelected() == false && answer4Radio.isSelected() == false){
                setAlertPaneVisible("Nem jelölél be egy lehetőséget se");
                logger.warn("A felhasználó nem jelölt be választ.");
                return;
            }else{
                setPaintRadioButton();
                isAnswerCorrect();
                logger.info("Érkezett válasz a feltett kérdésre és a GUI-n a visszajelzés megtörtént.");
                return;
            }
        }
        if(goButton.getText().equals("Következő kérdés")){
            setDefaultScreen();
            setRadioText();
            logger.info("Új kérdés fel lett téve.");
            return;
        }
    }

    /**
     * Aktív popUp ablak OK gombja, popUp ablak eltütetése és a kezdő felület újra aktiválása.
     * @param event a megnyomott gomb eseménykezelője.
     */
    @FXML
    void backButtonAction(ActionEvent event) {
        alertPane.setVisible(false);
        alertLabel.setText("");
        basePane.setOpacity(1);
        basePane.setDisable(false);
        logger.info("A hibát jelző popUp ablakról a visszatérés sikeres.");
    }

    /**
     * Teszt felületen lévő válaszok szövegeinek beállítása.
     */
    public void setRadioText(){
        List<Integer> randomNumber = m.generateRandomNumbers(1, beolvasottSzavak.size(), 4);
        correctNumber = m.generateCorrentAnswerPlace(1, 4);

        questionLabel.setText(beolvasottSzavak.get(randomNumber.get(0)).getAngol());
        switch(correctNumber){
            case 1:
                answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                break;
            case 2:
                answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                break;
            case 3:
                answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                break;
            case 4:
                answer4Radio.setText(beolvasottSzavak.get(randomNumber.get(0)).getMagyar());
                answer1Radio.setText(beolvasottSzavak.get(randomNumber.get(1)).getMagyar());
                answer2Radio.setText(beolvasottSzavak.get(randomNumber.get(2)).getMagyar());
                answer3Radio.setText(beolvasottSzavak.get(randomNumber.get(3)).getMagyar());
                break;
        }
        logger.info("A kérdés és a válaszok a GUI-n kiírásra kerültek.");
    }

    /**
     * A teszt felületen adott válasz alapján a lehetséges válaszok kinézeték módosítása.
     * Zölddel jelezve a helyes választ és pirossal a helytelen választ.
     */
    public void setPaintRadioButton(){
        answer1Radio.setDisable(true);
        answer2Radio.setDisable(true);
        answer3Radio.setDisable(true);
        answer4Radio.setDisable(true);
        switch(correctNumber){
            case 1: answer1Radio.setTextFill((Color.web("#009900"))); break;
            case 2: answer2Radio.setTextFill((Color.web("#009900"))); break;
            case 3: answer3Radio.setTextFill((Color.web("#009900"))); break;
            case 4: answer4Radio.setTextFill((Color.web("#009900"))); break;
        }
        logger.info("A jó és rossz válaszok színeinek beállítása a GUI-n megtörétént");
        goButton.setText("Következő kérdés");
    }

    /**
     * Kapott válasz helyességének ellenőrzése.
     * @return Az adott válasz sorszáma.
     */
    private int setAnswerNumber(){
        logger.info("A felhasználó által választott lehetőség alapján a segédváltozó beállítva.");
        if(answer1Radio.isSelected())
            return 1;
        if(answer2Radio.isSelected())
            return 2;
        if(answer3Radio.isSelected())
            return 3;
        if(answer4Radio.isSelected())
            return 4;

        return 0;
    }

    /**
     * Az adott válasz helyességétől függően a megfelelő image beállíátsa és megjelenítése.
     */
    private void isAnswerCorrect(){
        if(correctNumber == setAnswerNumber()){
            logger.info("A felhasználó helyes választ adott.");
            Image image1 = new Image("/pics/correct.png");
            isCorrectImageView.setImage(image1);
            isCorrectImageView.setVisible(true);
        }else{
            logger.info("A felhasználó helytelen választ adott.");
            Image image2 = new Image("/pics/incorrect.png");
            isCorrectImageView.setImage(image2);
            isCorrectImageView.setVisible(true);
        }
    }
    /**
     * Képernyők alaphelyzetbe állítása.
     */
    private void setDefaultScreen(){
        answer1Radio.setSelected(false);
        answer2Radio.setSelected(false);
        answer3Radio.setSelected(false);
        answer4Radio.setSelected(false);
        answer1Radio.setDisable(false);
        answer2Radio.setDisable(false);
        answer3Radio.setDisable(false);
        answer4Radio.setDisable(false);
        answer1Radio.setTextFill((Color.web("#000000")));
        answer2Radio.setTextFill((Color.web("#000000")));
        answer3Radio.setTextFill((Color.web("#000000")));
        answer4Radio.setTextFill((Color.web("#000000")));
        isCorrectImageView.setVisible(false);
        goButton.setText("Mehet");
        logger.info("Képernyő alaphelyzetre állítása megtörtént.");
    }

    /**
     * PopUp ablak aktiválása.
     * @param alertText hiba szövege.
     */
    private void setAlertPaneVisible(String alertText){
        basePane.setOpacity(0.3);
        basePane.setDisable(true);
        alertPane.setVisible(true);
        alertLabel.setText(alertText);
        logger.info("PopUp ablak beállítása megtörtént.");
    }

    /**
     * Felület inicializálása.
     * @param location Location url.
     * @param resources Resources.
     */
    public void initialize(URL location, ResourceBundle resources) {
        md = new ModelDAO();
        beolvasottSzavak = md.osszesSzo();
        setRadioText();
        logger.info("GUI alaphelyzetbeállítása megtörtént.");
    }
}