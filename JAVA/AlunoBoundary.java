package academia.teste;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlunoBoundary {
    private TextField txtnome = new TextField();
    private TextField txtIDaluno = new TextField();
    private TextField txtidade = new TextField();
    private TextField txtdtnasc = new TextField();
    private TextField txtpeso = new TextField();
    private TextField txttelefone = new TextField();
    private TextField txtcpf = new TextField();
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnAtualizar = new Button("Atualizar");
    private Button btnPesquisar = new Button("Pesquisar");
    private AlunoControl control = new AlunoControl();
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private TableView<Aluno> table = new TableView<>();

    private void CriarTabela() {
        TableColumn<Aluno, Long> col1 = new TableColumn<>("IDaluno");
        col1.setCellValueFactory(
                new PropertyValueFactory<Aluno, Long>("idaluno"));
        TableColumn<Aluno, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(
                new PropertyValueFactory<Aluno, String>("nome"));
        TableColumn<Aluno, Integer> col3 = new TableColumn<Aluno, Integer>("Idade");
        col3.setCellValueFactory(
                new PropertyValueFactory<Aluno, Integer>("idade"));
        TableColumn<Aluno, Double> col4 = new TableColumn<Aluno, Double>("Peso");
        col4.setCellValueFactory(
                new PropertyValueFactory<Aluno, Double>("peso"));
        TableColumn<Aluno, Integer> col5 = new TableColumn<Aluno, Integer>("Cpf");
        col5.setCellValueFactory(
                new PropertyValueFactory<Aluno, Integer>("cpf"));
        TableColumn<Aluno, Integer> col6 = new TableColumn<Aluno, Integer>("Telefone");
        col6.setCellValueFactory(
                new PropertyValueFactory<Aluno, Integer>("telefone")
        );
        TableColumn<Aluno, String> col7 = new TableColumn<>("Nascimento");
        col7.setCellValueFactory((item) -> {
                    LocalDate d = item.getValue().getDtnasc();
                    return new ReadOnlyStringWrapper(d.format(fmt));
                }
        );
        TableColumn<Aluno, String> col8 = new TableColumn<>("Ações");
        col8.setCellFactory((tbcol) -> {

                    Button btnRemover = new Button("Remover");
                    TableCell<Aluno, String> tcell = new TableCell<Aluno, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnRemover.setOnAction((e) -> {
                                    Aluno a = getTableView().getItems().get(getIndex());
                                    control.remover(a.getIDaluno());
                                });
                                setGraphic(btnRemover);
                                setText(null);
                            }
                        }
                    };
                    return tcell;
                }
        );
        table.getSelectionModel().selectedItemProperty().addListener((obs, old, novo) -> {
                    control.fromEntity(novo);
                }

        );
        table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
        table.setItems(control.getAlunos());
    }


    public Pane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();
        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        CriarTabela();
        //       txtIDaluno.setEditable(false);
        Bindings.bindBidirectional(txtIDaluno.textProperty(), control.IDaluno, new NumberStringConverter());
        Bindings.bindBidirectional(txtnome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtidade.textProperty(), control.idade, new NumberStringConverter());
        Bindings.bindBidirectional(txtpeso.textProperty(), control.peso, new NumberStringConverter());
        Bindings.bindBidirectional(txtdtnasc.textProperty(), control.dtnasc, new LocalDateStringConverter());
        Bindings.bindBidirectional(txtcpf.textProperty(), control.cpf, new NumberStringConverter());
        Bindings.bindBidirectional(txttelefone.textProperty(), control.telefone, new NumberStringConverter());
        panCampos.add(new Label("IDaluno"), 0, 0);
        panCampos.add(txtIDaluno, 1, 0);
        panCampos.add(new Label("nome"), 0, 1);
        panCampos.add(txtnome, 1, 1);
        panCampos.add(new Label("cpf"), 0, 2);
        panCampos.add(txtcpf, 1, 2);
        panCampos.add(new Label("idade"), 0, 4);
        panCampos.add(txtidade, 1, 4);
        panCampos.add(new Label("dtnasc"), 10, 0);
        panCampos.add(txtdtnasc, 11, 0);
        panCampos.add(new Label("peso"), 10, 1);
        panCampos.add(txtpeso, 11, 1);
        panCampos.add(new Label("numero"), 10, 2);
        panCampos.add(txttelefone, 11, 2);
        panCampos.add(btnAdicionar, 1, 14);
        panCampos.add(btnPesquisar, 11, 14);
        panCampos.add(btnAtualizar, 6, 14);
        btnAdicionar.setOnAction((e) -> {

            control.adicionar();
        });
        btnPesquisar.setOnAction((e) -> {

            control.pesquisar();
        });
        btnAtualizar.setOnAction((e) -> {

            control.atualizar();
        });
        return panPrincipal;
    }
}
