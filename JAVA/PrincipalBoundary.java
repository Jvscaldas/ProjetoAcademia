package academia.teste;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application {
    private AlunoBoundary alunoBoundary = new AlunoBoundary();
    private DesenvolvedoresBoundary desenvolvedoresBoundary = new DesenvolvedoresBoundary();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane bp = new BorderPane();
        Scene scn = new Scene(bp, 800, 600);

        bp.setCenter(alunoBoundary.render());

        MenuBar menBar = new MenuBar();

        Menu mnuapresentacao = new Menu("Apresentacao");
        Menu mnuSair = new Menu("Sair");
        Menu mnucadastro = new Menu("Cadastro");
        Menu mnuHelp = new Menu("Help");
        Menu mnuServicos = new Menu("Serviço");

        menBar.getMenus().addAll(mnuapresentacao, mnucadastro, mnuHelp, mnuServicos, mnuSair);

        MenuItem mnuItemSair = new MenuItem("Sair");
        mnuItemSair.setOnAction((e) -> {
            Platform.exit();
            System.exit(0);
        });

        MenuItem mnuItemAlunos = new MenuItem("Alunos");
        mnuItemAlunos.setOnAction((e) -> {

            bp.setCenter(alunoBoundary.render());
        });

        MenuItem mnuItemDesenvolvedores = new MenuItem("Desenvolvedores");

        mnuItemDesenvolvedores.setOnAction((e) -> {

            bp.setCenter(desenvolvedoresBoundary.render());

        });

        MenuItem mnuItemAtividades = new MenuItem("Atividades");
        MenuItem mnuItemApresentacao = new MenuItem("Apresentação");

        mnuSair.getItems().addAll(mnuItemSair);
        mnucadastro.getItems().addAll(mnuItemAlunos);
        mnuHelp.getItems().addAll(mnuItemDesenvolvedores);
        mnuServicos.getItems().addAll(mnuItemAtividades);

        bp.setTop(menBar);

        stage.setScene(scn);
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
}
