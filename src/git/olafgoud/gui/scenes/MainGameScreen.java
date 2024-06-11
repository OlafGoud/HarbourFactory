package git.olafgoud.gui.scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONTokener;

import git.olafgoud.HarbourFactoryMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainGameScreen {
	private Stage stage;
	private GridPane gridPane;
	private Pane pane;
	private Label moneyLabel;
	private VBox box;
	private Integer money;
	private int key;
	private int state;
	private String path;
	private ArrayList<String> emptyTiles;
	private ArrayList<String> objects;
	private int tileGrote = 30;
	private JSONObject jsonSaveFile;
	private Label currentObject;
	
	

	
	public void loadSave(String save) throws FileNotFoundException {
		JSONTokener saveFileToken = new JSONTokener(new FileInputStream(new File(this.path + "\\saves\\" + save)));
		JSONObject saveFile = new JSONObject(saveFileToken);
		gridPane = new GridPane(-1, -1);
		gridPane.setAlignment(Pos.TOP_CENTER);

		this.money = saveFile.getInt("money");
		this.moneyLabel.setText("$" + money);
		this.jsonSaveFile = saveFile;
		saveFile = saveFile.getJSONObject("map");
		JSONObject saveFileTiles = saveFile.getJSONObject("tiles");
		HarbourFactoryMain.map =  saveFileTiles;
		System.out.println(HarbourFactoryMain.map);
		Iterator<?> it = saveFileTiles.keys();
		while(it.hasNext()) {
			String key = (String) it.next();
			JSONObject tile = saveFileTiles.getJSONObject(key);
			Integer row = tile.getInt("posY");
			Integer col = tile.getInt("posX");
			
			String material = tile.getString("material");
			
			Image image = new Image(new FileInputStream(path + "\\emptytiles\\" + material + ".png"));
			ImageView imageView = new ImageView(image); 
			imageView.setFitHeight(tileGrote); 
		    imageView.setFitWidth(tileGrote); 
		    gridPane.add(imageView, col, row);
		    
		    if(!tile.getString("building").equals("-1")) {
		    	image = new Image(new FileInputStream(path + "\\objects\\" + tile.getString("building") + ".png"));
				imageView = new ImageView(image); 
				imageView.setFitHeight(tileGrote); 
			    imageView.setFitWidth(tileGrote); 
			    gridPane.add(imageView, col, row);
		    }
			
		}

	}
	
	
	
	public MainGameScreen(Stage stage, HarbourFactoryMain main) {
		this.stage = stage;
		this.money = 0;
		stage.setScene(new Scene(new Pane(), 800, 600));
		stage.setResizable(false);
		stage.show();
		this.path = "C:\\Users\\Olaf\\Downloads\\pictures";
		this.emptyTiles = new ArrayList<>();
		this.objects = new ArrayList<>();
		File filePath = new File(this.path + "\\emptytiles");
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".png");
			}
		};
		
		File[] filelist = filePath.listFiles(filter);
		for (File tempFile : filelist) {
			emptyTiles.add(tempFile.getName().replace(".png", ""));
		}
		filePath = new File(this.path + "\\objects");
		File[] filelistobjects = filePath.listFiles(filter);
		for (File tempFile : filelistobjects) {
			objects.add(tempFile.getName().replace(".png", ""));
		}
	}
	
	
	public void setGameScreen() {
		try {

			setupPopUpBox();
			pane = new Pane();
			moneyLabel = new Label();
			moneyLabel.setText("$0");
			moneyLabel.setMouseTransparent(true);
			
			
			currentObject = new Label();
			currentObject.setText("");
			currentObject.setMouseTransparent(true);
			
			
			
			
			
			this.stage.getScene().setRoot(pane);
			setEventHandlers();
			if(new File(path + "\\saves\\" + "Save.json").exists()) {
				loadSave("Save.json");
			} else {makeSaveFile();}
			VBox vbox = new VBox(10);
			vbox.getChildren().addAll(moneyLabel, currentObject);
			pane.getChildren().addAll(gridPane, box, vbox);
			HarbourFactoryMain.startGame();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setEventHandlers() {

		this.stage.getScene().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				if(box.isVisible() == true) {
					box.setVisible(false);

				} else {
					box.setVisible(true);
				}
				
			}
			else if (e.getCode() == KeyCode.DIGIT1) {
				if (key == 1) {
					key = -1;
				} else {
					key = 1;
					state = 0;

				}
				
			}
			else if (e.getCode() == KeyCode.DIGIT2) {
				if (key == 2) {
					key = -1;
				} else {
					key = 2;
					state = 0;

				}
				
			}
			else if (e.getCode() == KeyCode.DIGIT3) {
				if (key == 3) {
					key = -1;
				} else {
					key = 3;
					state = 0;
					currentObject.setText(objects.get(state));
				}
				
			}
			else if (e.getCode() == KeyCode.DIGIT9) {
				if (key == 9) {
					key = -1;
				} else {
					key = 9;
					state = 0;
					currentObject.setText(emptyTiles.get(state));
				}
				
			}
			else if (e.getCode() == KeyCode.T) {
				if (key == 9) {
					if (state < emptyTiles.size() - 1) {
						state++;
					} else {
						state = 0;
					}
					currentObject.setText(emptyTiles.get(state));
					
				}else if (key == 3) {
					if (state < objects.size() - 1) {
						state++;
						
						currentObject.setText("");

					} else {
						state = 0;
					}
					currentObject.setText(objects.get(state));
				}
				
			}
			else if (e.getCode() == KeyCode.R) {
				if (key == 2) {
					key = -1;
					currentObject.setText("");
				} else {
					key = 2;
					currentObject.setText(objects.get(state));

				}
				
			}
			else if (e.getCode() == KeyCode.I) {
				if (key == 13) {
					key = -1;
					currentObject.setText("");
				} else {
					key = 13;
					currentObject.setText("inspector");

				}
				
			}
		});
		
		
		
		this.stage.getScene().setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				Node node = (Node) e.getTarget();
				int row = GridPane.getRowIndex(node);
				int col = GridPane.getColumnIndex(node);
				

				if (key == 1) {
					System.out.println(1);
				} else if (key == 2) {
					System.out.println(2);
				} else if (key == 3) {
					Image image;
					try {
						JSONObject tile = HarbourFactoryMain.map.getJSONObject(col + "-" + row);
						
						if (tile.get("building").equals("-1")) {
							image = new Image(new FileInputStream(path + "\\objects\\" + objects.get(state) + ".png"));
							ImageView imageView = new ImageView(image); 
							imageView.setFitHeight(tileGrote); 
						    imageView.setFitWidth(tileGrote); 
						    gridPane.add(imageView, col, row);
						    
						    tile.put("building", objects.get(state));
						    HarbourFactoryMain.map.put(col + "-" + row, tile);
						    
						} else {
							System.out.println("There is a object");
						}
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else if (key == 4) {
					
				} else if (key == 13) {
					System.out.println("X:" + row + ", Y:" + col);
					
				} else if (key == 9) {
					
					try {
						Image image = new Image(new FileInputStream(path + "\\emptytiles\\" + emptyTiles.get(state) + ".png"));
						ImageView imageView = new ImageView(image); 
						imageView.setFitHeight(tileGrote); 
					    imageView.setFitWidth(tileGrote); 
						gridPane.getChildren().removeIf( node1 -> GridPane.getColumnIndex(node1) == col && GridPane.getRowIndex(node1) == row);
					    gridPane.add(imageView, col, row);
					    JSONObject tile = HarbourFactoryMain.map.getJSONObject(col + "-" + row);
					    tile.put("material", emptyTiles.get(state));
					    HarbourFactoryMain.map.put(col + "-" + row, tile);
					    if(!tile.getString("building").equals("-1")) {
					    	image = new Image(new FileInputStream(path + "\\objects\\" + tile.getString("building") + ".png"));
							imageView = new ImageView(image); 
							imageView.setFitHeight(tileGrote); 
						    imageView.setFitWidth(tileGrote); 
						    gridPane.add(imageView, col, row);
					    }
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
				}
			} else if (e.getButton().equals(MouseButton.SECONDARY)) {
				try {
					Node node = (Node) e.getTarget();
					int row = GridPane.getRowIndex(node);
					int col = GridPane.getColumnIndex(node);
					
					JSONObject tile = HarbourFactoryMain.map.getJSONObject(col + "-" + row);
					
					if (tile.get("building").equals("-1")) {
						
					} else {
						Image image;
						
						image = new Image(new FileInputStream(path + "\\emptytiles\\" + tile.getString("material") + ".png"));
						ImageView imageView = new ImageView(image); 
						imageView.setFitHeight(tileGrote); 
					    imageView.setFitWidth(tileGrote); 
						gridPane.getChildren().removeIf(node1 -> GridPane.getColumnIndex(node1) == col && GridPane.getRowIndex(node1) == row);
					    gridPane.add(imageView, col, row);
					    tile.put("building", "-1");
					    HarbourFactoryMain.map.put(col + "-" + row, tile);

						
					}
				} catch (Exception e1) {
					System.out.println("er ging iets fout in mousebuttonclicksecondary");
				}
			}
		});
		
		
		
	}


	private void makeSaveFile() throws FileNotFoundException {
		gridPane = new GridPane(-1, -1);
		gridPane.setAlignment(Pos.TOP_CENTER);
		JSONObject map = new JSONObject();
		Image imagegrass = new Image(new FileInputStream("C:\\Users\\Olaf\\Downloads\\pictures\\emptytiles\\grass.png"));
		Image imagewater = new Image(new FileInputStream("C:\\Users\\Olaf\\Downloads\\pictures\\emptytiles\\water.png"));
		Image imagepavement = new Image(new FileInputStream("C:\\Users\\Olaf\\Downloads\\pictures\\emptytiles\\pavement.png"));
		for (int i = 0; i < (800 / tileGrote) + 2; i++) {
			for (int n = 0; n < (600 / tileGrote) + 2; n++) {
				JSONObject obj = new JSONObject();
				obj.put("posX", i);
				obj.put("posY", n);
				obj.put("building", "-1");
				if(n > 8) {
					obj.put("material", "grass");
					
					ImageView imageView = new ImageView(imagegrass); 
					imageView.setFitHeight(tileGrote); 
				    imageView.setFitWidth(tileGrote); 
				    gridPane.add(imageView, i, n);
				} else if (n < 5) {
					obj.put("material", "water");

					ImageView imageView = new ImageView(imagewater); 
					imageView.setFitHeight(tileGrote); 
				    imageView.setFitWidth(tileGrote); 
				    gridPane.add(imageView, i, n);
				} else {
					obj.put("material", "pavement");

					ImageView imageView = new ImageView(imagepavement); 
					imageView.setFitHeight(tileGrote); 
				    imageView.setFitWidth(tileGrote); 
				    gridPane.add(imageView, i, n);
				 
				}
				map.put(i + "-" + n, obj);
			}
		}
		JSONObject save = new JSONObject();
		save.put("money", 1000);
		save.put("map", new JSONObject().put("tiles", map));
		
		File file = new File(path + "\\saves\\" + "Save.json");
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(save.toString());
			writer.flush();
			writer.close();
			
			HarbourFactoryMain.map = map;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void setupPopUpBox() {
		box = new VBox(10);
		Button continueBtn = new Button("continue");
		continueBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				box.setVisible(false);
			}
			
		});
		Button homeButton = new Button("home");
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg) {
				setMainScreen();
				
			}
			
		});
		
		Button saveButton = new Button("save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg) {
				JSONObject save = new JSONObject();
				save.put("money", money);
				save.put("map", new JSONObject().put("tiles", HarbourFactoryMain.map));
				
				File file = new File(path + "\\saves\\" + "Save.json");
				
				try {
					FileWriter writer = new FileWriter(file);
					
					writer.write(save.toString());
					writer.flush();
					writer.close();
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		box.getChildren().addAll(continueBtn, homeButton, saveButton);
		box.layoutXProperty().set(345);
		box.layoutYProperty().set(200);
		box.setCenterShape(true);
		box.setAlignment(Pos.CENTER);
		box.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY) ));
		box.setPadding(new Insets(30, 10, 30, 10));
		box.setVisible(false);
	}
	
	
	public void addMoney(Integer amount) {
		money += amount;
		moneyLabel.setText("$" + money.toString());
	}
	
	
	public void setCreditsScreen() {
		FlowPane root = new FlowPane(10, 10);
		root.setPadding(new Insets(10));
		Button btn2 = new Button("back");
		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg) {
				setMainScreen();
			}
			
		});
		Label contributers = new Label("Olaf, AKA OlafGoud");
		
		
		VBox hbox = new VBox(10);
		hbox.getChildren().addAll(btn2, contributers );
		root.getChildren().add(hbox);
		
		stage.getScene().setRoot(root);
		
		

	}
	
	
	public void setMainScreen() {
		FlowPane root = new FlowPane(10, 10);
		root.setPadding(new Insets(10));
		
		
		
		Button playButton = new Button("open");
		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg) {
				
				setGameScreen();
				
				
			}
			
		});
		Button creditsButton = new Button("credits");
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg) {
				
				setCreditsScreen();
			}
			
		});
		
		
		
		
		VBox buttons = new VBox(30);
		buttons.getChildren().addAll(playButton, creditsButton);
		buttons.setAlignment(Pos.CENTER);
		root.getChildren().add(buttons);
		root.setAlignment(Pos.CENTER);
		this.stage.getScene().setRoot(root);
	}



	public JSONObject getJsonSaveFile() {
		return jsonSaveFile;
	}
	
	
	public void setVrachtwagen(int row, int col) {
		Image imagepavement;
		try {
			imagepavement = new Image(new FileInputStream("C:\\Users\\Olaf\\Downloads\\pictures\\vehicles\\truck.png"));
			ImageView imageView = new ImageView(imagepavement); 
			imageView.setFitHeight(tileGrote); 
		    imageView.setFitWidth(tileGrote); 
		    gridPane.add(imageView, col, row);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public void removeTruck(int row1, int col1) {
		try {
			JSONObject tile = HarbourFactoryMain.map.getJSONObject(col1+ "-" + row1);			
			String material = tile.getString("material");
			
			Image image;
			
				image = new Image(new FileInputStream(path + "\\emptytiles\\" + material + ".png"));
			
			ImageView imageView = new ImageView(image); 
			imageView.setFitHeight(tileGrote); 
		    imageView.setFitWidth(tileGrote); 
		    gridPane.add(imageView, col1, row1);
		    
		    if(!tile.getString("building").equals("-1")) {
		    	image = new Image(new FileInputStream(path + "\\objects\\" + tile.getString("building") + ".png"));
				imageView = new ImageView(image); 
				imageView.setFitHeight(tileGrote); 
			    imageView.setFitWidth(tileGrote); 
			    gridPane.add(imageView, col1, row1);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
