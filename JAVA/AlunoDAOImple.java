package academia.teste;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImple implements AlunoDAO {

    private static final String URIDB = "jdbc:mariadb://localhost:3306/alunodb?allowMultiQueries=true";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";

    public AlunoDAOImple() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URIDB, USUARIO, PASSWORD);

    }

    @Override
    public void adicionar(Aluno aluno) {

        try {
            Connection con = getConnection();
            String sql = "INSERT INTO aluno (IDaluno, nome, idade, peso, dtnasc, cpf, telefone) " +
                    "Values(?, ?, ?, ?, ?, ?, ?)";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, aluno.getIDaluno());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getIdade());
            stmt.setDouble(4, aluno.getPeso());
            stmt.setDate(5, java.sql.Date.valueOf(aluno.getDtnasc()));
            stmt.setInt(6, aluno.getCpf());
            stmt.setInt(7, aluno.getTelefone());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Aluno> pesquisarPorNome(String Nome) {
        List<Aluno> lista = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM aluno WHERE nome like ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + Nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setIDaluno(rs.getInt("IDaluno"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setPeso(rs.getDouble("peso"));
                a.setDtnasc(rs.getDate("dtnasc").toLocalDate());
                a.setCpf(rs.getInt("cpf"));
                a.setTelefone(rs.getInt("telefone"));
                lista.add(a);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    @Override
    public void remover(long IDaluno) {

        try {
            Connection con = getConnection();
            String sql = "DELETE FROM aluno  WHERE IDaluno = ? ";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, IDaluno);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(long IDaluno, Aluno aluno) {

        try {
            Connection con = getConnection();
            String sql = "UPDATE aluno SET  nome = ?, idade = ?, peso = ?,  dtnasc = ?,  cpf = ?,  telefone = ? WHERE IDaluno = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setDouble(3, aluno.getPeso());
            stmt.setDate(4, java.sql.Date.valueOf(aluno.getDtnasc()));
            stmt.setInt(5, aluno.getCpf());
            stmt.setInt(6, aluno.getTelefone());
            stmt.setLong(7, aluno.getIDaluno());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
