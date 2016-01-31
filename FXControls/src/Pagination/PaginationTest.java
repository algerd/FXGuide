
package Pagination;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
Pagination is used to display a large single content by dividing sections of it into smaller chunks called
pages, for example, the results of a search. A Pagination control has a page count, 
which is the number of pages in it. If the number of pages is not known, the page count
may be indeterminate. Each page has an index, which starts at 0.

A Pagination control is divided into two areas:
    • Content area
    • Navigation area
The content area displays the content of the current page. The navigation area contains parts to allow
the user to navigate from one page to another. You can navigate between pages sequentially or randomly.

The previous and next page arrow buttons let the user navigate to the previous and next pages,
respectively. The previous page button is disabled when you are on the first page. The next page button is
disabled when you are on the last page. Page indicators also let you navigate to a specific page by showing
all of the page numbers. By default, page indicators use a tool tip to show the page number, which you have
the option to disable using a CSS property. The selected page indicator shows the current page. The selected
page label shows the current page selection details.

The Pagination class provides several constructors. They configure the control differently. The default
constructor creates a control with an indeterminate page count and zero as the index for the selected page

The Pagination class declares an INDETERMINATE constant that can be used to specify an indeterminate
page count.

The Pagination class contains the following properties:
    • currentPageIndex
    • maxPageIndicatorCount
    • pageCount
    • pageFactory

The currentPageIndex is an integer property. Its value is the page index of the page to display.
The default value is zero. You can specify its value using one of the constructors or using the
setCurrentPageIndex() method. If you set its value to less than zero, the first page index, which is zero, is set
as its value. If you set its value to greater than the page count minus 1, its value is set to page count minus 1. If
you want to know when a new page is displayed, add a ChangeListener to the currentPageIndex property.

The maxPageIndicatorCount is an integer property. It sets the maximum number of page indicators to
display. It defaults to 10. Its value remains unchanged if it is set beyond the page count range. If its value is
set too high, the value is reduced so that the number of page indicators fits the control. You can set its value
using the setMaxPageIndicatorCount() method.

The pageCount is an integer property. It is the number of pages in the Pagination control. Its value must
be greater than or equal to 1. It defaults to indeterminate. Its value can be set in the constructors or using the
setPageCount() method.

The pageFactory is the most important property. It is an object property of the Callback<Integer,
Node> type. It is used to generate pages. When a page needs to be displayed, the control calls the call()
method of the Callback object passing the page index. The call() method returns a node that is the content
of the page.

Tip T he call() method of the page factory should return null if a page index does not exist. The current
page does not change when the call() method returns null.

The page indicators may be numeric buttons or bullet buttons. Numeric buttons are used by default.
The Pagination class contains a String constant named STYLE_CLASS_BULLET, which is the style class for
the control if you want to use bullet buttons.
    // Use bullet page indicators
    pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
*/
public class PaginationTest extends Application {
	private static final int PAGE_COUNT = 5;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Pagination pagination = new Pagination(PAGE_COUNT);
		
		// Set the page factory
		pagination.setPageFactory(this::getPage);
        
        // Use bullet page indicators
        //pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

		VBox root = new VBox(pagination);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Pagination Controls");
		stage.show();
	}
	
	public Label getPage(int pageIndex) {
		Label content = null;
		
		if (pageIndex >= 0 && pageIndex < PAGE_COUNT) {
			content = new Label("Content for page " + (pageIndex + 1));		
		}
		return content;
	}
}
