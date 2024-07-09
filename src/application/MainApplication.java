package application;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MainApplication extends Application{
	private static final int HEIGHT =750;
	private static final int WIDTH = 1000;
	public static List<Base> objects = new ArrayList<>();
	public static int Xspeed = 1;
	
	public static void main(String[] args){
		launch(args);
	}

	
	@Override
	public void start(Stage stage){
		stage.setTitle("Simple shooter game");

		AnchorPane pane = new AnchorPane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		canvas.setFocusTraversable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Button btnSpeed = new Button("X1");
		btnSpeed.setOnAction((e) -> {
			Xspeed+=1;
			if(Xspeed==4){
				Xspeed=1;
			}
			btnSpeed.setText("X"+String.valueOf(Xspeed));

		});

		Button btnReset = new Button("RESET");
		btnReset.setOnAction((e)->{
			objects.clear();
			for(int i=0;i<50;i++){
				objects.add(new Base("Rock"));
				objects.add(new Base("Paper"));
				objects.add(new Base("Scissor"));
			}
		});
		btnReset.setTranslateX(30);

		pane.getChildren().add(canvas);
		pane.getChildren().add(btnReset);
		pane.getChildren().add(btnSpeed);


		for(int i=0;i<50;i++){
			objects.add(new Base("Rock"));
			objects.add(new Base("Paper"));
			objects.add(new Base("Scissor"));
		}
		
		Timeline loop = new Timeline(new KeyFrame(Duration.millis(1000.0/40), e -> update(gc)));
		loop.setCycleCount(Animation.INDEFINITE);
		loop.play();


		
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	
	private void update(GraphicsContext gc){
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < objects.size(); i++){
			objects.get(i).render(gc);
			objects.get(i).move();
		}

		for(int i=0;i< objects.size()-1;i++){
			for(int j=i+1;j<objects.size();j++){
				if(objects.get(i).isCollide(objects.get(j))){
					if(objects.get(i).isWin(objects.get(j))){
						objects.get(j).setClassName(objects.get(i).getClassName());
					}
					else{
						objects.get(i).setClassName(objects.get(j).getClassName());
					}
				}
			}
		}




	}
}
