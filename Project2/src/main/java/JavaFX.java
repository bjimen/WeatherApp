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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import weather.Period;
import weather.WeatherAPI;
import java.util.ArrayList;

public class JavaFX extends Application {
	Scene mainPage, threeDayForecast;
	BorderPane bpMainPage, bpForecast;
	GridPane gpMainPage, gpForecast;
	StackPane spDayBlock1, spDayBlock2, spDayBlock3;
	VBox vbDayBlock1, vbDayBlock2, vbDayBlock3;
	Text shortDesc, temperature, day1, day2, day3, dayTemp1, nightTemp1, dayTemp2, nightTemp2, dayTemp3, nightTemp3, dayWindSpeed1, nightWindSpeed1, dayWindSpeed2, nightWindSpeed2, dayWindSpeed3, nightWindSpeed3, dayWindDir1, nightWindDir1, dayWindDir2, nightWindDir2, dayWindDir3, nightWindDir3;
	Button btForecast, btBack;
	Image imWeatherIcon, imDayBlock;
	ImageView ivWeatherIcon, ivDayBlock1, ivDayBlock2, ivDayBlock3;

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
		btForecast.setOnAction(e->{primaryStage.setScene(threeDayForecast);});

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

		// 3-Day Forecast
		day1 = new Text();
		day2 = new Text();
		day3 = new Text();
		dayTemp1 = new Text();
		nightTemp1 = new Text();
		dayTemp2 = new Text();
		nightTemp2 = new Text();
		dayTemp3 = new Text();
		nightTemp3 = new Text();
		dayWindSpeed1 = new Text();
		nightWindSpeed1 = new Text();
		dayWindSpeed2 = new Text();
		nightWindSpeed2 = new Text();
		dayWindSpeed3 = new Text();
		nightWindSpeed3 = new Text();
		dayWindDir1 = new Text();
		nightWindDir1 = new Text();
		dayWindDir2 = new Text();
		nightWindDir2 = new Text();
		dayWindDir3 = new Text();
		nightWindDir3 = new Text();

		day1.setText(forecast.get(0).name);
		day2.setText(forecast.get(2).name);
		day3.setText(forecast.get(4).name);
		dayTemp1.setText(String.valueOf(forecast.get(0).temperature) + "°F");
		nightTemp1.setText(String.valueOf(forecast.get(1).temperature) + "°F");
		dayTemp2.setText(String.valueOf(forecast.get(2).temperature) + "°F");
		nightTemp2.setText(String.valueOf(forecast.get(3).temperature) + "°F");
		dayTemp3.setText(String.valueOf(forecast.get(4).temperature) + "°F");
		nightTemp3.setText(String.valueOf(forecast.get(5).temperature) + "°F");
		dayWindSpeed1.setText(forecast.get(0).windSpeed);
		nightWindSpeed1.setText(forecast.get(1).windSpeed);
		dayWindSpeed2.setText(forecast.get(2).windSpeed);
		nightWindSpeed2.setText(forecast.get(3).windSpeed);
		dayWindSpeed3.setText(forecast.get(4).windSpeed);
		nightWindSpeed3.setText(forecast.get(5).windSpeed);
		dayWindDir1.setText(forecast.get(0).windDirection);
		nightWindDir1.setText(forecast.get(1).windDirection);
		dayWindDir2.setText(forecast.get(2).windDirection);
		nightWindDir2.setText(forecast.get(3).windDirection);
		dayWindDir3.setText(forecast.get(4).windDirection);
		nightWindDir3.setText(forecast.get(5).windDirection);

		day1.setStyle("-fx-font-size: 15");
		day2.setStyle("-fx-font-size: 15");
		day3.setStyle("-fx-font-size: 15");

		imDayBlock = new Image("C:\\Users\\bjime\\OneDrive\\Desktop\\UIC\\CS 342\\Project 2\\Project 2\\Project2\\src\\main\\java\\assets\\tempDayBlock.png");
		ivDayBlock1 = new ImageView(imDayBlock);
		ivDayBlock2 = new ImageView(imDayBlock);
		ivDayBlock3 = new ImageView(imDayBlock);

		btBack = new Button("Back");
		btBack.setOnAction(e->{primaryStage.setScene(mainPage);});

		vbDayBlock1 = new VBox(15, dayTemp1, dayWindSpeed1, dayWindDir1, nightTemp1, nightWindSpeed1, nightWindDir1);
		vbDayBlock2 = new VBox(15, dayTemp2, dayWindSpeed2, dayWindDir2, nightTemp2, nightWindSpeed2, nightWindDir2);
		vbDayBlock3 = new VBox(15, dayTemp3, dayWindSpeed3, dayWindDir3, nightTemp3, nightWindSpeed3, nightWindDir3);

		spDayBlock1 = new StackPane();
		spDayBlock2 = new StackPane();
		spDayBlock3 = new StackPane();
		spDayBlock1.getChildren().addAll(ivDayBlock1, vbDayBlock1);
		spDayBlock2.getChildren().addAll(ivDayBlock2, vbDayBlock2);
		spDayBlock3.getChildren().addAll(ivDayBlock3, vbDayBlock3);

		gpForecast = new GridPane();
		gpForecast.add(day1, 0, 0);
		gpForecast.setHalignment(day1, HPos.CENTER);
		gpForecast.add(day2, 1, 0);
		gpForecast.setHalignment(day2, HPos.CENTER);
		gpForecast.add(day3, 2, 0);
		gpForecast.setHalignment(day3, HPos.CENTER);
		gpForecast.add(spDayBlock1, 0, 1);
		gpForecast.setHalignment(spDayBlock1, HPos.CENTER);
		gpForecast.add(spDayBlock2, 1, 1);
		gpForecast.setHalignment(spDayBlock2, HPos.CENTER);
		gpForecast.add(spDayBlock3, 2, 1);
		gpForecast.setHalignment(spDayBlock3, HPos.CENTER);
		gpForecast.add(btBack, 2, 2);
		gpForecast.setHalignment(btBack, HPos.RIGHT);
		gpForecast.setVgap(15);
		gpForecast.setHgap(15);

		bpForecast = new BorderPane();
		bpForecast.setPadding(new Insets(37.5));
		bpForecast.setCenter(gpForecast);

		threeDayForecast = new Scene(bpForecast, 800, 450);


		mainPage = new Scene(bpMainPage, 800, 450);
		primaryStage.setScene(mainPage);
		primaryStage.show();
	}
}
