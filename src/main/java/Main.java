
import algoritmit.Djikstra;
import algoritmit.Fringe;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import logiikka.Ruudukko;
import logiikka.Tyyppi;

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

                        if (tyyppi == Tyyppi.DIJKSTRA) {
                            piirturi.setFill(Color.YELLOW);
                        }
                        if (tyyppi == Tyyppi.FRINGE) {
                            piirturi.setFill(Color.CYAN);
                        }

                        piirturi.fillRect(x, y, 1, 1);
                    }
                }
                this.edellinen = nykyhetki;
            }
        }.start();


        Label dijkstraAika = new Label("Djikstra");
        Label fringeAika = new Label("Fringe");


        //Algoritmit
        Djikstra djikstra = new Djikstra(ruudukko);
        Fringe fringe = new Fringe(ruudukko);

        //Etsireittiä painamalla tapahtuu reitin muodostaminen
        etsiReitti.setOnAction((event -> {
            if (ruudukko.getAloitus() == null && ruudukko.getMaali() == null) {
                new Alert(Alert.AlertType.ERROR, "Aseta aloituspiste ja maali!").show();
            } else if(ruudukko.getAloitus() == null)  {
                new Alert(Alert.AlertType.ERROR, "Aseta aloituspiste!").show();
            } else if (ruudukko.getMaali() == null) {
                new Alert(Alert.AlertType.ERROR, "Aseta maali!").show();
            } else {
                ruudukko.resetPolku();
                ruudukko.muodostavieruslista();
                ruudukko.nollaaSolmujenVanhemmat();
                long alku = System.nanoTime();
                fringe.algo();
                long loppu = System.nanoTime();
                ruudukko.muodostavieruslista();
                long alku2 = System.nanoTime();
                djikstra.algo();
                long loppu2 = System.nanoTime();

                fringeAika.setText("Fringe: "+((loppu-alku)/1e9)+" s");
                dijkstraAika.setText("Djikstra: "+((loppu2-alku2)/1e9)+" s");
            }
        }));




        resetNappi.setOnAction((event -> {
            ruudukko.nollaaTaulukko();
        }));


        Rectangle dijkstraVari = new Rectangle(20, 20);
        Rectangle fridgeVari = new Rectangle(20, 20);

        dijkstraVari.setFill(Color.YELLOW);
        fridgeVari.setFill(Color.CYAN);
        BorderPane borderPane = new BorderPane();
        HBox buttonMenu = new HBox();
        buttonMenu.getChildren().addAll(seina, tyhja, aloitus, maali, resetNappi, etsiReitti);

        HBox infoPalkki = new HBox();
        infoPalkki.setSpacing(15);
        infoPalkki.getChildren().addAll(dijkstraVari, dijkstraAika, fridgeVari, fringeAika);
        borderPane.setBottom(infoPalkki);
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
