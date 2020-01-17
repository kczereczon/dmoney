package app.core;

import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public abstract class Controller {
    @Getter @Setter
    private Stage stage;
}
