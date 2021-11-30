package academia.teste;

import java.util.List;

public interface AlunoDAO {

    void adicionar(Aluno a);

    List<Aluno> pesquisarPorNome(String Nome);

    void remover(long IDaluno);

    void atualizar(long IDaluno, Aluno a);
}
