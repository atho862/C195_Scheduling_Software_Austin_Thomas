package Domain.Helpers;

import Contracts.Statics.CustomerStatics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerHelper {

    public static ObservableList<String> getIsActiveItems(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(CustomerStatics.getIsActiveYes(), CustomerStatics.getGetIsActiveNo());

        return items;
    }
}
