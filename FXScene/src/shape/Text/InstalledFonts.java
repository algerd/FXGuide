
package shape.Text;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
You can get the list of installed fonts on your machine. You can get the list of font family names, full font
names, and full font names for a specified family name for all installed fonts. The following static methods in
the Font class provide these lists.
    • List<String> getFamilies()
    • List<String> getFontNames()
    • List<String> getFontNames(String family)
*/
public class InstalledFonts extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
 
	@Override
	public void start(Stage stage) {
		/*
        The following snippet of code prints the family names of all installed fonts on a machine. The output
        was generated on Windows. A partial output is shown:
        */        
        // Print the family names of all installed fonts
        for(String familyName: Font.getFamilies()) {
            System.out.println(familyName);
        }
		
        /*
        The following snippet of code prints the full names of all installed fonts on a machine. The output was
        generated on Windows. A partial output is shown:
        */        
        // Print the full names of all installed fonts
        for(String fullName: Font.getFontNames()) {
            System.out.println(fullName);
        }
        
        /*
        The following snippet of code prints the full names of all installed fonts for the “Times New Roman”
        family:
        */
        // Print the full names of “Times New Roman” family
        for(String fullName: Font.getFontNames("Times New Roman")) {
            System.out.println(fullName);
        }
            		
	}
}
