import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Escolhi a AtivoCripto para devenvolver com o banco de dados
public class AtivoCriptoDAO {

    public void inserir(AtivoCripto ativo) {
        String sql = "INSERT INTO ATIVO_CRIPTO (idAtivo, nomeMoeda, simbolo, precoAtual) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, ativo.getIdAtivo());
            ps.setString(2, ativo.getNomeMoeda());
            ps.setString(3, ativo.getSimbolo());
            ps.setBigDecimal(4, ativo.getPrecoAtual());
            ps.executeUpdate();
            System.out.println("Ativo inserido: " + ativo.getIdAtivo() + " - " + ativo.getNomeMoeda());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir ativo: " + e.getMessage());
        }
    }

    public void atualizarPreco(AtivoCripto ativo, BigDecimal novoPreco) {
        ativo.atualizarPreco(novoPreco);

        String sql = "UPDATE ATIVO_CRIPTO SET precoAtual = ? WHERE idAtivo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, ativo.getPrecoAtual());
            ps.setLong(2, ativo.getIdAtivo());
            int linhas = ps.executeUpdate();
            System.out.println(linhas + " registro(s) atualizado(s).");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar ativo: " + e.getMessage());
        }
    }

    public void excluir(Long idAtivo) {
        String sql = "DELETE FROM ATIVO_CRIPTO WHERE idAtivo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idAtivo);
            int linhas = ps.executeUpdate();
            System.out.println(linhas + " registro(s) excluido(s).");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir ativo (verifique se ha TRANSACAO/CARTEIRA vinculadas): " + e.getMessage());
        }
    }

    public AtivoCripto buscarPorId(Long idAtivo) {
        String sql = "SELECT idAtivo, nomeMoeda, simbolo, precoAtual FROM ATIVO_CRIPTO WHERE idAtivo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idAtivo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ativo: " + e.getMessage());
        }
        return null;
    }

    public List<AtivoCripto> listarTodos() {
        List<AtivoCripto> lista = new ArrayList<>();
        String sql = "SELECT idAtivo, nomeMoeda, simbolo, precoAtual FROM ATIVO_CRIPTO ORDER BY idAtivo";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar ativos: " + e.getMessage());
        }
        return lista;
    }

    private AtivoCripto mapear(ResultSet rs) throws SQLException {
        return new AtivoCripto(
                rs.getLong("idAtivo"),
                rs.getString("nomeMoeda"),
                rs.getString("simbolo"),
                rs.getBigDecimal("precoAtual")
        );
    }
}