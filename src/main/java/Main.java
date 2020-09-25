
import algoritmit.Djikstra;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import logiikka.Ruudukko;
import logiikka.Tyyppi;

import algoritmit.Bfs;

public class Main extends Application {

    /**
     * Aloittaa graafisen käyttöliittymän käyttäjää varten.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        //Ikkunan koko

        int korkeus = 350;
        int leveys = 350;

        final ToggleGroup ryhma = new ToggleGroup();

        RadioButton seina = new RadioButton("Seinä");
        seina.setToggleGroup(ryhma);
        seina.setSelected(true);

        RadioButton tyhja = new RadioButton("Kumi");
        tyhja.setToggleGroup(ryhma);

        RadioButton aloitus = new RadioButton("Aloitus");
        aloitus.setToggleGroup(ryhma);

        RadioButton maali = new RadioButton("Maali");
        maali.setToggleGroup(ryhma);

        Ruudukko ruudukko = new Ruudukko(leveys, korkeus);

        Button resetNappi = new Button("Reset");

        Button etsiReitti = new Button("Etsi reitti");




        Canvas screen = new Canvas(leveys, korkeus);
        GraphicsContext piirturi = screen.getGraphicsContext2D();

        screen.setOnMouseClicked((event) -> {
            double xSpot = event.getX();
            double ySpot = event.getY();

            int x = (int) xSpot;
            int y = (int) ySpot;

            if (ryhma.getSelectedToggle() == aloitus) {
                ruudukko.lisaaTyyppi(x, y, Tyyppi.ALOITUS);
            }

            if (ryhma.getSelectedToggle() == maali) {
                ruudukko.lisaaTyyppi(x, y, Tyyppi.MAALI);
            }
        });

        screen.setOnMouseDragged((event) -> {
            double xSpot = event.getX();
            double ySpot = event.getY();

            for (int x = (int) xSpot - 2; x < (int) xSpot + 2; x++) {
                for (int y = (int) ySpot - 2; y < (int) ySpot + 2; y++) {

                    if (ryhma.getSelectedToggle() == seina) {
                        ruudukko.lisaaTyyppi(x, y, Tyyppi.SEINA);
                    }

                    if (ryhma.getSelectedToggle() == tyhja) {
                        ruudukko.lisaaTyyppi(x, y, Tyyppi.TYHJA);
                    }

                    if (ryhma.getSelectedToggle() == aloitus) {
                        ruudukko.lisaaTyyppi(x, y, Tyyppi.ALOITUS);
                    }

                    if (ryhma.getSelectedToggle() == maali) {
                        ruudukko.lisaaTyyppi(x, y, Tyyppi.MAALI);
                    }
                }
            }
        });



        //Etsi naapurit, kun hiiri päästetään irti
        screen.setOnMouseReleased((event) -> {


             /*
            ruudukko.lisaaTyyppi(24,26, Tyyppi.ALOITUS);
            ruudukko.lisaaTyyppi(27,24, Tyyppi.MAALI);
            ruudukko.muodostavieruslista();
            ruudukko.algo();
            */

        });

        new AnimationTimer() {
            long edellinen = 0;

            @Override
            public void handle(long nykyhetki) {
                if (nykyhetki - edellinen < 100000000) {
                    return;
                }

                piirturi.setFill(Color.BLACK);
                piirturi.fillRect(0, 0, leveys, korkeus);

                for (int x = 0; x < leveys; x++) {
                    for (int y = 0; y < korkeus; y++) {
                        Tyyppi tyyppi = ruudukko.getTyyppi(x, y);

                        if (tyyppi == Tyyppi.TYHJA || tyyppi == null) {
                            continue;
                        }

                        if (tyyppi == Tyyppi.SEINA) {
                            piirturi.setFill(Color.GRAY);
                        }

                        if (tyyppi == Tyyppi.ALOITUS) {
                            piirturi.setFill(Color.RED);
                        }

                        if (tyyppi == Tyyppi.MAALI) {
                            piirturi.setFill(Color.GREEN);
                        }

                        if (tyyppi == Tyyppi.REITTI) {
                            piirturi.setFill(Color.YELLOW);
                        }
                        piirturi.fillRect(x, y, 1, 1);
                    }
                }
                this.edellinen = nykyhetki;
            }
        }.start();


        Label reitinPituus = new Label();
        reitinPituus.setTextFill(Color.GREEN);
        reitinPituus.setAlignment(Pos.CENTER);
        etsiReitti.setOnAction((event -> {
            ruudukko.resetPolku();
            ruudukko.muodostavieruslista();
            Djikstra.algo(ruudukko);
            reitinPituus.setText("" + ruudukko.getReitinPituus());
        }));

        resetNappi.setOnAction((event -> {
            reitinPituus.setText("");
            ruudukko.nollaaTaulukko();
        }));


        BorderPane borderPane = new BorderPane();
        HBox buttonMenu = new HBox();
        buttonMenu.getChildren().addAll(seina, tyhja, aloitus, maali, resetNappi, etsiReitti, reitinPituus);

        borderPane.setTop(buttonMenu);
        borderPane.setCenter(screen);
        Scene scene = new Scene(borderPane);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
