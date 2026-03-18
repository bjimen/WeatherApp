import javafx.application.Application;
import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import weather.Period;
import weather.WeatherAPI;
import java.util.ArrayList;

public class JavaFX extends Application {
	Scene mainPage, threeDayForecast;
	BorderPane bpMainPage;
	GridPane gpMainPage;
	VBox vbMainPage;
	HBox hbMainPage;
	Text shortDesc, temperature;
	Button btForecast;
	Image imWeatherIcon;
	ImageView ivWeatherIcon;

	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("I'm a professional Weather App!");
		ArrayList<Period> forecast = WeatherAPI.getForecast("LOT",77,70);
		if (forecast == null){
			throw new RuntimeException("Forecast did not load");
		}

		// Main Page
		shortDesc = new Text();
		temperature = new Text();
		shortDesc.setText(forecast.get(0).shortForecast);
		temperature.setText(String.valueOf(forecast.get(0).temperature) + "°F");
		shortDesc.setStyle("-fx-font-size: 18");
		temperature.setStyle("-fx-font-size: 30");

		imWeatherIcon = new Image("C:\\Users\\bjime\\OneDrive\\Desktop\\UIC\\CS 342\\Project 2\\Project 2\\Project2\\src\\main\\java\\assets\\tempIcon.jpg");
		ivWeatherIcon = new ImageView(imWeatherIcon);

		btForecast = new Button("3-Day Forecast");

		gpMainPage = new GridPane();
		gpMainPage.add(shortDesc, 1, 0);
		gpMainPage.setHalignment(shortDesc, HPos.CENTER);
		gpMainPage.add(ivWeatherIcon, 1, 1);
		gpMainPage.setHalignment(ivWeatherIcon, HPos.CENTER);
		gpMainPage.add(temperature, 1, 2);
		gpMainPage.setHalignment(temperature, HPos.CENTER);
		gpMainPage.add(btForecast, 2, 3);
		gpMainPage.setHalignment(btForecast, HPos.RIGHT);
		gpMainPage.setValignment(btForecast, VPos.BOTTOM);
		gpMainPage.setVgap(15);
		gpMainPage.setHgap(100);
		gpMainPage.setAlignment(Pos.CENTER);

		bpMainPage = new BorderPane();
		bpMainPage.setPadding(new Insets(50));
		bpMainPage.setCenter(gpMainPage);

		mainPage = new Scene(bpMainPage, 800, 450);
		primaryStage.setScene(mainPage);
		primaryStage.show();
	}
}
