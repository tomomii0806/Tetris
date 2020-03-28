package application;
	
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class Tetris extends Application {
	
	Controller cntl = new Controller();
	
	//public static final int MOVE = 25; 		//height of rectangle when it falls down
	public static final int SIZE = 25;		//size of one rectangle
	public static int XMAX = SIZE * 12;		//width of the pane (12 is the number of the rows
	public static int YMAX = SIZE * 24;		//height of the pane (24 is the number of the columns
	public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];		// (XMAX / SIZE) is 12, and (YMAX / SIZE) is 24
	private Pane root = new Pane();
	public Scene scene;
	private Form object;
	private Form nextObject = cntl.makeRectangle();
	private boolean gameOver = false;
	private int score = 0;
	private Label scores;
	private Label times;
	private int exit = 0;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			for (int[] a: MESH) {
				Arrays.fill(a, 0);
			}
			
			
			scene = new Scene(root, XMAX + 150, YMAX);
			Rectangle rec1 = new Rectangle();
			rec1.setWidth(150);
			rec1.setHeight(YMAX);
			rec1.setLayoutX(XMAX);
			rec1.setLayoutY(0);
			rec1.setFill(Color.DARKGRAY);
			FadeTransition fade = new FadeTransition();
		    fade.setFromValue(.65);
		    fade.setNode(rec1);
		    fade.play();
			//Line line = new Line(XMAX, 0, XMAX, YMAX);
			scores = new Label("YOUR SCORE");
			times = new Label("TIME");		
			scores.setLayoutX(XMAX + 20);
			scores.setLayoutY(5 * SIZE);
			scores.setTextFill(Color.CADETBLUE);
			times.setLayoutX(XMAX + 20);
			times.setLayoutY(8 * SIZE);
			Form f = nextObject;
			nextObject = cntl.makeRectangle();
			root.getChildren().addAll(rec1, scores, times, f.a, f.b, f.c, f.d);
			//Set KeyEvent here 
			controllByKey(f);
			object = f;
			long start = System.currentTimeMillis();
			times.setText("TIME : " + (start/1000)/60 + ":" + (start/1000)%60);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Platform.runLater(new Runnable() {
						public void run() {
							long end = System.currentTimeMillis();
							if (!gameOver) {
								moveDown(object);
								times.setText("TIME : " + ((end - start)/1000)/60 + ":" + ((end - start)/1000)% 60);
								scores.setText("YOUR SCORE : " + score);
							}
							else
							{		
								if (exit == 0)
								{
									times.setText("TIME : " + ((end - start)/1000)/60 + ":" + ((end - start)/1000)% 60);
									scores.setText("YOUR SCORE : " + score);
									Label gameOverLabel = new Label("GAME OVER");
									gameOverLabel.setTextFill(Color.DARKKHAKI);
									gameOverLabel.setFont(new Font("Arial", 50));
									gameOverLabel.setLayoutX(50);
									gameOverLabel.setLayoutY(YMAX/2 - 30);
									root.getChildren().add(gameOverLabel);
									exit++;
								}
								
								exit++;
								
							}
					
							if (exit == 20)
							{
								System.exit(0);
							}
				
						}
					});
				}
				
			}; timer.schedule(task, 0, 200);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void removeRows() {
		// TODO Auto-generated method stub
		ArrayList<Node> rects = new ArrayList<>();
		ArrayList<Node> copies = new ArrayList<>();
		int numOfRows = 0;
		for (int i = YMAX / SIZE -1 ; i >= 0; i--) // YMAX/SIZE = 24, i : number of columns
		{
			int num = 0;
			for (int j = 0; j < XMAX / SIZE; j++)	// XMAX/SIZE = 12, j : number of rows
			{
				if (MESH[j][i] == 1) {
					num++;
				}
			}
			
			if (num == 12)
			{
				numOfRows++;
				//remove all cells on the row
				for (Node node: root.getChildren())
				{							
					if (node instanceof Rectangle)	//Return true if node is rectangle
					{
						Rectangle rec = (Rectangle) node;
						if (rec.getY() <= i*SIZE && rec.getWidth() != 150)
						{
							copies.add(node);					
							if (rec.getY() == i*SIZE)
							{
								rects.add(node);	//Store rectangles to delete 
							}
						}	
					}						
				}
				//set MESH of removed rectangle '0'
				for (int k = 0; k < 12; k++) {
					MESH[k][i] = 0;
				}
				//Delete rectangles
				for (Node node: rects) {
					root.getChildren().remove(node);
					copies.remove(node);
				}	
				
				//Move MESH one down
				for (int k = i; k > 0; k--)
				{
					for (int j = 0; j < 12; j++) 	// j : rows
					{	
						if (MESH[j][k - 1] == 1) {
							MESH[j][k] = 1;
						}else {
							MESH[j][k] = 0;
						}
					}
				}
				
			}		
		}
		
		if (numOfRows >= 4) {
			score += 800;
			score += (numOfRows - 4)*100;
		}else {
			score += 100*numOfRows;
		}
		
		//System.out.println(copies.size());
		//Move rectangle SIZE down 
		for (Node node: copies)
		{
			Rectangle rec = (Rectangle) node;
			rec.setY(rec.getY() + SIZE*numOfRows);
		}
	}

	
	public void moveDown(Form form) {
		// TODO Auto-generated method stub
		//GO down if the X+SIZE and Y+SIZE are inside of the pane, and also there is no rectangle on that space, which means MESH[][] is not 1
		if (form.a.getY() + SIZE < YMAX && form.b.getY() + SIZE < YMAX && form.c.getY() + SIZE < YMAX && form.d.getY() + SIZE < YMAX
				&& MESH[(int) (form.a.getX() / SIZE)][(int) form.a.getY() / SIZE + 1] != 1 
				&& MESH[(int) (form.b.getX() / SIZE)][(int) form.b.getY() / SIZE + 1] != 1
				&& MESH[(int) (form.c.getX() / SIZE)][(int) form.c.getY() / SIZE + 1] != 1
				&& MESH[(int) (form.d.getX() / SIZE)][(int) form.d.getY() / SIZE + 1] != 1)
		{
			form.a.setY(form.a.getY()+SIZE);
			form.b.setY(form.b.getY()+SIZE);
			form.c.setY(form.c.getY()+SIZE);
			form.d.setY(form.d.getY()+SIZE);	
			
			//removeRows();
		}
		else	// (form.a.getY() + SIZE >= YMAX && form.b.getY() + SIZE >= YMAX && form.c.getY() + SIZE >= YMAX && form.d.getY() + SIZE >= YMAX)
		{
			if (form.a.getY() >= 0 && form.b.getY() >= 0 && form.c.getY() >= 0 && form.d.getY() >= 0)
			{
				MESH[(int) (form.a.getX() / SIZE)][(int) form.a.getY() / SIZE] = 1;
				MESH[(int) (form.b.getX() / SIZE)][(int) form.b.getY() / SIZE] = 1;
				MESH[(int) (form.c.getX() / SIZE)][(int) form.c.getY() / SIZE] = 1;
				MESH[(int) (form.d.getX() / SIZE)][(int) form.d.getY() / SIZE] = 1;
				
				removeRows();
				
				Form a = nextObject;
				nextObject = cntl.makeRectangle();
				object = a;
				root.getChildren().addAll(a.a, a.b, a.c, a.d);
				controllByKey(a);
			}
			else 
			{
				gameOver = true;
			}		
		}
		
		
		
	}
	private void controllByKey(Form form) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case RIGHT:
					cntl.moveRight(form);;
					break;
				case DOWN:
					cntl.moveFaster(form);
					break;
				case LEFT:
					cntl.moveLeft(form);;
					break;
				case UP:
					cntl.turnRect(form);;
					break;
				}
			}
		});
	}
	
	
}
