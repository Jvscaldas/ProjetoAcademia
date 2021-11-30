package academia.teste;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoControl {
    LongProperty IDaluno = new SimpleLongProperty(0);
    StringProperty nome = new SimpleStringProperty("");
    IntegerProperty cpf = new SimpleIntegerProperty(0);
    IntegerProperty telefone = new SimpleIntegerProperty(0);
    IntegerProperty idade = new SimpleIntegerProperty(0);
    DoubleProperty peso = new SimpleDoubleProperty(0);
    ObjectProperty dtnasc = new SimpleObjectProperty(LocalDate.now());
    private AlunoDAO alunoDAO = new AlunoDAOImple();
    private List<Aluno> alunoGeral = new ArrayList<>();
    private ObservableList<Aluno> Alunos = FXCollections.observableArrayList();
    public void adicionar() {

        Aluno a = toEntity();
        alunoDAO.adicionar(a);
        fromEntity(a);
    }


    public void pesquisar() {
        Alunos.clear();
        List<Aluno> achados = alunoDAO.pesquisarPorNome(nome.get());
        Alunos.addAll(achados);
        if (!Alunos.isEmpty()) {
            fromEntity(Alunos.get(0));
        }
    }


    public void atualizar() {
        Aluno aluno = toEntity();
        alunoDAO.atualizar(IDaluno.get(), aluno);
    }




    public void remover(long IDaluno) {
        alunoDAO.remover(IDaluno);
    }

    public Aluno toEntity() {
        Aluno a = new Aluno();
        a.setIDaluno(IDaluno.get());
        a.setNome(nome.get());
        a.setIdade(idade.get());
        a.setPeso(peso.get());
        a.setDtnasc((LocalDate) dtnasc.get());
        a.setCpf(cpf.get());
        a.setTelefone(telefone.get());
        return a;
    }

    public void fromEntity(Aluno a) {

        IDaluno.set(a.getIDaluno());
        nome.set(a.getNome());
        idade.set(a.getIdade());
        peso.set(a.getPeso());
        dtnasc.set(a.getDtnasc());
        cpf.set(a.getCpf());
        telefone.set(a.getTelefone());
    }



    public ObservableList <Aluno> getAlunos(){

        return Alunos;
    }
}
