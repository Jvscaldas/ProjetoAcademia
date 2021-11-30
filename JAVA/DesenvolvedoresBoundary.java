package academia.teste;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DesenvolvedoresBoundary {

    public Pane render() {

        BorderPane panPrincipal = new BorderPane();
        VBox panDesenvolvedores = new VBox();

        panPrincipal.setCenter(panDesenvolvedores);

        panDesenvolvedores.getChildren().addAll(
                new Label("Criadores do Projeto"),
                new Label("Wellington G de Lima - Aluno 4 sem"),
                new Label("João Vitor Perez - Aluno 4 sem"),
                new Label("João Vitor Santana Caldas - Aluno 4 sem")
        );

        return panPrincipal;
    }

}