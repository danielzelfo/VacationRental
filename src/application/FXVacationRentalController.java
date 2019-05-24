/*
 * Author: Daniel Zelfo
 * Date: 5/13/2019
 * Description: A vavation rental cost tool to estimate the cost of the rental based on the location, number of rooms, and meals
 */
package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class FXVacationRentalController {
	//declaring all the fxml elements
	@FXML private RadioButton parksideRadio;
	@FXML private RadioButton poolsideRadio;
	@FXML private RadioButton lakesideRadio;
	@FXML ChoiceBox<String> bedroomsChoice;
	@FXML private CheckBox chkMeals;
	@FXML private Button btnSumbit;
	@FXML private TextArea txtResult;
	//making a toggle group for the radiobuttons
	private final ToggleGroup location = new ToggleGroup();
	//declaring rest of variables
	private String selectedLocationSTR;
	private int cost;
	//initializing radiobuttons and combobox
	public void initialize() {
		parksideRadio.setToggleGroup(location);
		poolsideRadio.setToggleGroup(location);
		lakesideRadio.setToggleGroup(location);
		bedroomsChoice.setItems(FXCollections.observableArrayList("1", "2", "3"));
		bedroomsChoice.getSelectionModel().select(0);
	}
	//handing the sumbit button click
	@FXML protected void btnSubmitOnClick( ActionEvent event ){
		//initializing result variables and setting all result values
		Toggle selectedLocation = location.getSelectedToggle();
		int selectedBedrooms = Integer.valueOf(bedroomsChoice.getSelectionModel().getSelectedItem());
		boolean selectedMeal = chkMeals.isSelected();
		int locationCost;
		//making one of the radio buttons has been clicked and finding out which one
		if(selectedLocation != null) {
			selectedLocationSTR = ((Labeled) selectedLocation).getText();
			switch(selectedLocationSTR) {
			case "parkside":
				locationCost = 600;
				break;
			case "poolside":
				locationCost = 750;
				break;
			case "lakeside":
				locationCost = 825;
				break;
			default:
				txtResult.appendText("Invalid location selected. Selecting parkside.\n");
				selectedLocationSTR = "parkside";
				locationCost = 600;
			}
		} else {
			txtResult.appendText("No location selected. Selecting parkside.\n");
			selectedLocationSTR = "parkside";
			locationCost = 600;
		}
		//geting the additional bedroom cost
		int bedroomsCost = (selectedBedrooms-1)*75;
		//adding all the costs
		cost += locationCost + bedroomsCost;
		if(selectedMeal)
			cost += 200;
		//adding the results to the page
		txtResult.appendText("Location: "+selectedLocationSTR+" ($"+locationCost+")\n"+
							 "Number of bedrooms: " + selectedBedrooms + " ($"+bedroomsCost+")\n" +
							 "With meals? " + (selectedMeal ? "Yes ($200)" : "No") + "\n" + 
							 "Final cost: $" + cost + "\n"
				);
	}
}