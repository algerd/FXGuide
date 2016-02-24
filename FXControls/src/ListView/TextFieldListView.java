
package ListView;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Мой пример(незаконченный) выпадения поискового списка при вводе букв в текстовом поле.
Вводим напр. букву "j" и выпадает список с John Jacobs.
Список появляется точно под полем и имеет его ширину.
С высотой списка не разбирался. 
*/
public class TextFieldListView extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    private TextField name = new TextField();
    private ListView<Person> listView = new ListView<>();
    private List<Person> personList = new ArrayList<>();
     
    @Override
    public void init() {
        personList.add(new Person("John", "Jacobs", null));
        personList.add(new Person("Donna", "Duncan", null));    
        personList.add(new Person("Layne", "Estes", null));
        personList.add(new Person("Mason", "Boyd", null));
    }
       
	@Override
	public void start(Stage stage) {
        
        initListView();
        listView.setVisible(false);
        listView.prefWidthProperty().bind(name.widthProperty());
        //listView.maxHeight(100); //???
              
        HBox hb = new HBox();
        hb.getChildren().addAll(new Label("First Name:"), name);
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(hb, listView);
        Scene scene = new Scene(root);
        
        name.textProperty().addListener(this::changedInput);
        stage.setScene(scene);
		stage.setTitle("Text Field + List View");
		stage.show();
        
	}
    
    private void initListView() {
        listView.setCellFactory((ListView<Person> listView) -> new ListCell<Person>() {
            @Override
            public void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    this.setText(null);
                    this.setGraphic(null);
                } else {
                    this.setText(item.getFirstName());
                }
            }
        });
    }
    
    private void changedInput(ObservableValue<? extends Object> observable, String oldValue, String newValue) {      
        listView.getItems().clear();
        for (Person person : personList) {
            if (person.getFirstName().toLowerCase().contains(newValue.toLowerCase().trim())) {
                listView.getItems().add(person);
            }
        }          
        placeListView(name);
        listView.setVisible(true);
    }
    
    public void placeListView(Node newNode) {
        // It gets the (x, y) coordinates of the top left corner of the
        // bounding box of the node in focus in the local coordinate space:
		double nodeMinX = newNode.getLayoutBounds().getMinX();
		double nodeMaxY = newNode.getLayoutBounds().getMaxY();
        
        // It transforms the coordinates of the top left corner of the node from the local coordinate space to the
        //coordinate space of the scene:
		Point2D nodeInScene = newNode.localToScene(nodeMinX, nodeMaxY);
        
        // Now the coordinates of the top left corner of the node are transformed from the coordinate space of the
        // scene to the coordinate space of the searchList:
		Point2D nodeInListViewLocal = listView.sceneToLocal(nodeInScene);
        
        // Finally, the coordinate of the top left corner of the node is transformed to the coordinate space of the
        // parent of the searchList:
		Point2D nodeInListViewParent = listView.localToParent(nodeInListViewLocal);
        
        // Position the circle approperiately
		listView.relocate(
            nodeInListViewParent.getX() + listView.getLayoutBounds().getMinX(),
			nodeInListViewParent.getY() + listView.getLayoutBounds().getMinY()
        );     
    }
       
}
