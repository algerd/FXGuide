
package Menu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
You can use the following types of menu items ("A class diagram for the MenuItem class and its subclasses.png"):
    • A MenuItem for an actionable option
    • A RadioMenuItem for a group of mutually exclusive options
    • A CheckMenuItem for a toggle option
    • A Menu, when used as a menu item and acts as a submenu that holds a list of menu items
    • A CustomMenuItem for an arbitrary node to be used as an menu item
    • A SeparatorMenuItem, which is a CustomMenuItem, to display a separator as a menu item

A MenuItem represents an actionable option. When it is clicked, the registered ActionEvent handlers are
called.
A MenuItem is added to a menu. A menu stores the reference of its MenuItems in an
ObservableList<MenuItem> whose reference can be obtained using the getItems() method:
    Menu fileMenu = new Menu("File");
    fileMenu.getItems().add(exitItem);

The MenuItem class contains the following properties that apply to all types of menu items:
    • text
    • graphic
    • disable
    • visible
    • accelerator
    • mnemonicParsing
    • onAction
    • onMenuValidation
    • parentMenu
    • parentPopup
    • style
    • id
The text and graphic properties are the text and graphics for the menu item, respectively, which
are of String and Node types. The disable and visible properties are boolean properties. They specify
whether the menu item is disabled and visible. The accelerator property is an object property of the
KeyCombination type that specifies a key combination that can be used to execute the action associated with
the menu item in one keystroke. 

The mnemonicParsing property is a boolean property. It enables or disables text parsing to detect a
mnemonic character. By default, it is set to true for menu items. If it is set to true, the text for the menu
item is parsed for an underscore character. The character following the first underscore is added as the
mnemonic for the menu item. Pressing the Alt key on Windows highlights mnemonics for all menu items.
Typically, mnemonic characters are shown in underlined font style. Pressing the key for the mnemonic
character activates the menu item.
    RadioMenuItem rectItem = new RadioMenuItem("_Rectangle");

The onAction property is an ActionEvent handler that is called when the menu item is activated, for
example, by clicking it with a mouse or pressing its accelerator key.

The onMenuValidation property is an event handler that is called when a MenuItem is accessed using its
accelerator or when the onShowing event handler for its menu (the parent) is called. For a menu, this handler
is called when its menu items are shown.

The parentMenu property is a read-only object property of the Menu type. It is the reference of the Menu,
which contains the menu item. Using this property and the items list returned by the getItems() method of
the Menu class, you can navigate the menu tree from top to bottom and vice versa.
The parentPopup property is a read-only object property of the ContextMenu type. It is the reference of
the ContextMenu in which the menu item appears. It is null for a menu item appearing in a normal menu.

CustomMenuItem is a simple yet powerful menu item type. It opens the door for all kinds of creativity for
designing menu items. It lets you use any node. For example, you can use a Slider, a TextField, or an HBox
as a menu item. The CustomMenuItem class contains two properties:
    • content
    • hideOnClick
The content property is an object property of Node type. Its value is the node that you want to use as the menu item.
When you click a menu item, all visible menus are hidden and only top-level menus in the menu bar
stay visible. When you use a custom menu item that has controls, you do not want to hide menus when the
user clicks it because the user needs to interact with the menu item, for example, to enter or select some
data. The hideOnClick property is a boolean property that lets you control this behavior. By default, it is set
to true, which means clicking a custom menu hides all showing menus.

The program creates a shape drawing application using menus. It uses all types of menu items. 
The program displays a window with a BorderPane as the root of its scene. The top region contains a
menu and the center region contains a canvas on which shapes are drawn.
Run the application and use the File menu to draw different types of shapes; clicking the Clear menu
item clears the canvas. Clicking the Exit menu item closes the application.
Use the Options menu to draw or not to draw the strokes and set the stroke width. Notice that a slider
is used as a custom menu item under the Options menu. When you adjust the slider value, the stroke width
of the drawn shape is adjusted accordingly. The Draw Stroke menu item is a CheckMenuItem. When it is
unselected, the slider menu item is disabled and the shape does not use a stroke.
*/
public class MenuItemTest extends Application {
	// A canvas to draw shapes
	Canvas canvas = new Canvas(200, 200);
	
	// Create three RadioMenuItems for shapes
	RadioMenuItem rectItem = new RadioMenuItem("_Rectangle");
	RadioMenuItem circleItem = new RadioMenuItem("_Circle");
	RadioMenuItem ellipseItem = new RadioMenuItem("_Ellipse");

	// A menu item to draw stroke
	CheckMenuItem strokeItem = new CheckMenuItem("Draw _Stroke");

	// To adjust the stroke width
	Slider strokeWidthSlider = new Slider(1, 10, 1);
	CustomMenuItem strokeWidthItem = new CustomMenuItem(strokeWidthSlider, false);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        /*
        Menu class is inherited from the MenuItem class. This makes it possible to use a Menu in place
        of a MenuItem. Use a Menu as a menu item to create a submenu. When the mouse hovers over a submenu,
        its list of options is displayed.
        */
		Menu fileMenu = getFileMenu();
		Menu optionsMenu = getOptionsMenu();

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, optionsMenu);

		// Draw the default shape, which is a Rectangle
		this.draw();
		
		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setCenter(canvas);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Different Types of Menu Items");
		stage.show(); 
	}
	
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 200, 200); // First clear the canvas
		
		// Set drawing parameters
		gc.setFill(Color.TAN);
		gc.setStroke(Color.RED);
		gc.setLineWidth(strokeWidthSlider.getValue());

		String shapeType = getSelectedShape();
		switch(shapeType) {
			case "Rectangle":
				gc.fillRect(0, 0, 200, 200);
				if (strokeItem.isSelected()) {
					gc.strokeRect(0, 0, 200, 200);
				}
				break;
			case "Circle":
				gc.fillOval(10, 10, 180, 180);
				if (strokeItem.isSelected()) {
					gc.strokeOval(10, 10, 180, 180);
				}
				break;
			case "Ellipse":
				gc.fillOval(10, 10, 180, 150);
				if (strokeItem.isSelected()) {
					gc.strokeOval(10, 10, 180, 150);
				}
				break;
			default:
				clear(); // Do not know the shape type
		}
	}
	
	public void clear() {
		canvas.getGraphicsContext2D().clearRect(0, 0, 200, 200); 
		this.rectItem.setSelected(false);
		this.circleItem.setSelected(false);
		this.ellipseItem.setSelected(false);
	}	
	
	public Menu getFileMenu() {
		Menu fileMenu = new Menu("_File");

		// Make Rectangle the default option
		rectItem.setSelected(true);

		// Set Key Combinations for shapes
		KeyCombination kr = new KeyCodeCombination(KeyCode.R, KeyCombination.ALT_DOWN);
		KeyCombination kc = new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN);
		KeyCombination ke = new KeyCodeCombination(KeyCode.E, KeyCombination.ALT_DOWN);
		rectItem.setAccelerator(kr);
		circleItem.setAccelerator(kc);
		ellipseItem.setAccelerator(ke);

		// Add ActionEvent handler to all shape radio menu items
		rectItem.setOnAction(e -> draw());
		circleItem.setOnAction(e -> draw());
		ellipseItem.setOnAction(e -> draw());

		// Add RadioMenuItems to a ToggleGroup to make them mutually exclusive
		ToggleGroup shapeGroup = new ToggleGroup();
		shapeGroup.getToggles().addAll(rectItem, circleItem, ellipseItem);

		MenuItem clearItem = new MenuItem("Cle_ar");
		clearItem.setOnAction(e -> clear());

		MenuItem exitItem = new MenuItem("E_xit");
		exitItem.setOnAction(e -> Platform.exit());

		// Add menu items to the File menu
		fileMenu.getItems().addAll(
            rectItem, 
            circleItem, ellipseItem, 
            new SeparatorMenuItem(),
            clearItem,
            new SeparatorMenuItem(),
            exitItem);
		return fileMenu;
	}
	
	public Menu getOptionsMenu() {
		// Draw stroke by default		
		strokeItem.setSelected(true);

		// Redraw the shape when draw stroke option toggles
		strokeItem.setOnAction(e -> syncStroke());
		
		// Configure the slider
		strokeWidthSlider.setShowTickLabels(true);
		strokeWidthSlider.setShowTickMarks(true);
		strokeWidthSlider.setMajorTickUnit(2);	    
		strokeWidthSlider.setSnapToPixel(true);
		strokeWidthSlider.valueProperty().addListener(this::strokeWidthChanged);

		Menu optionsMenu = new Menu("_Options"); 
		optionsMenu.getItems().addAll(strokeItem, this.strokeWidthItem);
		
		return optionsMenu;
	}
	
	public void strokeWidthChanged (ObservableValue<? extends Number> prop, 
	                                Number oldValue, 
	                                Number newValue) {
		draw();
	}
	
	public String getSelectedShape() {
		if (rectItem.isSelected()) {
			return "Rectangle";
		}
		else if (circleItem.isSelected()) {
			return "Circle";
		}
		else if (ellipseItem.isSelected()) {
			return "Ellipse";
		}
		else {
			return "";
		}
	}
	
	public void syncStroke() {
		// Enable/disable the slider
		strokeWidthSlider.setDisable(!strokeItem.isSelected());
		draw();
	}
}
