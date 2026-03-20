import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import weather.Period;
import weather.WeatherAPI;
import java.util.ArrayList;

public class JavaFX extends Application {
	Scene mainPage, threeDayForecast;
	BorderPane bpMainPage, bpForecast;
	GridPane gpForecast;
	StackPane spDayBlock1, spDayBlock2, spDayBlock3;
	VBox vbMainPage, vbDayBlock1, vbDayBlock2, vbDayBlock3;
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

		String time;
		int periodIdx;
		if (forecast.get(0).isDaytime) {
			time = "day";
			periodIdx = 2;
		} else {
			time = "night";
			periodIdx = 1;
		}

		/// Main Page
		shortDesc = new Text();
		temperature = new Text();
		shortDesc.setText(forecast.get(0).shortForecast);
		temperature.setText(String.valueOf(forecast.get(0).temperature) + "°F");
		shortDesc.setStyle("-fx-font-size: 18");
		temperature.setStyle("-fx-font-size: 30");

		String icon = parseIcon(forecast.get(0).icon);
		try {
			imWeatherIcon = new Image(icon + ".png");
		} catch (RuntimeException e) {
			imWeatherIcon = new Image(forecast.get(0).icon);
		}
		ivWeatherIcon = new ImageView(imWeatherIcon);

		btForecast = new Button("3-Day Forecast");
		btForecast.setOnAction(e->{primaryStage.setScene(threeDayForecast);});

		vbMainPage = new VBox(5, shortDesc, ivWeatherIcon, temperature);
		vbMainPage.setAlignment(Pos.CENTER);

		bpMainPage = new BorderPane();
		bpMainPage.setPadding(new Insets(37.5));
		bpMainPage.setCenter(vbMainPage);
		bpMainPage.setBottom(btForecast);
		bpMainPage.setAlignment(btForecast, Pos.BOTTOM_RIGHT);
		bpMainPage.setBackground(new Background(new BackgroundImage(new Image(time + "/bg.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		/// 3-Day Forecast
		day1 = new Text(forecast.get(periodIdx).name);
		day2 = new Text(forecast.get(periodIdx+2).name);
		day3 = new Text(forecast.get(periodIdx+4).name);
		dayTemp1 = new Text(String.valueOf(forecast.get(periodIdx).temperature) + "°F");
		nightTemp1 = new Text(String.valueOf(forecast.get(periodIdx+1).temperature) + "°F");
		dayTemp2 = new Text(String.valueOf(forecast.get(periodIdx+2).temperature) + "°F");
		nightTemp2 = new Text(String.valueOf(forecast.get(periodIdx+3).temperature) + "°F");
		dayTemp3 = new Text(String.valueOf(forecast.get(periodIdx+4).temperature) + "°F");
		nightTemp3 = new Text(String.valueOf(forecast.get(periodIdx+5).temperature) + "°F");
		dayWindSpeed1 = new Text("Wind speed: " + forecast.get(periodIdx).windSpeed);
		nightWindSpeed1 = new Text("Wind speed: " + forecast.get(periodIdx+1).windSpeed);
		dayWindSpeed2 = new Text("Wind speed: " + forecast.get(periodIdx+2).windSpeed);
		nightWindSpeed2 = new Text("Wind speed: " + forecast.get(periodIdx+3).windSpeed);
		dayWindSpeed3 = new Text("Wind speed: " + forecast.get(periodIdx+4).windSpeed);
		nightWindSpeed3 = new Text("Wind speed: " + forecast.get(periodIdx+5).windSpeed);
		dayWindDir1 = new Text("Wind direction: " + forecast.get(periodIdx).windDirection);
		nightWindDir1 = new Text("Wind direction: " + forecast.get(periodIdx+1).windDirection);
		dayWindDir2 = new Text("Wind direction: " + forecast.get(periodIdx+2).windDirection);
		nightWindDir2 = new Text("Wind direction: " + forecast.get(periodIdx+3).windDirection);
		dayWindDir3 = new Text("Wind direction: " + forecast.get(periodIdx+4).windDirection);
		nightWindDir3 = new Text("Wind direction: " + forecast.get(periodIdx+5).windDirection);

		day1.setStyle("-fx-font-size: 15");
		day2.setStyle("-fx-font-size: 15");
		day3.setStyle("-fx-font-size: 15");

		imDayBlock = new Image("day_block.png");
		ivDayBlock1 = new ImageView(imDayBlock);
		ivDayBlock2 = new ImageView(imDayBlock);
		ivDayBlock3 = new ImageView(imDayBlock);

		btBack = new Button("Back");
		btBack.setOnAction(e->{primaryStage.setScene(mainPage);});

		vbDayBlock1 = new VBox(15, dayTemp1, dayWindSpeed1, dayWindDir1, nightTemp1, nightWindSpeed1, nightWindDir1);
		vbDayBlock2 = new VBox(15, dayTemp2, dayWindSpeed2, dayWindDir2, nightTemp2, nightWindSpeed2, nightWindDir2);
		vbDayBlock3 = new VBox(15, dayTemp3, dayWindSpeed3, dayWindDir3, nightTemp3, nightWindSpeed3, nightWindDir3);
		vbDayBlock1.setPadding(new Insets(10));
		vbDayBlock2.setPadding(new Insets(10));
		vbDayBlock3.setPadding(new Insets(10));

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
		gpForecast.add(spDayBlock2, 1, 1);
		gpForecast.add(spDayBlock3, 2, 1);
		gpForecast.setAlignment(Pos.CENTER);
		gpForecast.setVgap(10);
		gpForecast.setHgap(15);

		bpForecast = new BorderPane();
		bpForecast.setPadding(new Insets(37.5));
		bpForecast.setCenter(gpForecast);
		bpForecast.setBottom(btBack);
		bpForecast.setAlignment(btBack, Pos.BOTTOM_RIGHT);
		bpForecast.setBackground(new Background(new BackgroundImage(new Image(time + "/bg.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		threeDayForecast = new Scene(bpForecast, 800, 450);


		mainPage = new Scene(bpMainPage, 800, 450);
		primaryStage.setScene(mainPage);
		primaryStage.show();
	}

	private String parseIcon(String iconLink) {
		String icon = "";
		int startIdx = iconLink.indexOf("land/") + 5;
		for (int i = startIdx; i < iconLink.length(); i++) {
			if (iconLink.charAt(i) == '?' || iconLink.charAt(i) == ',') {
				break;
			}
			icon += iconLink.charAt(i);
		}
		return icon;
	}
}

