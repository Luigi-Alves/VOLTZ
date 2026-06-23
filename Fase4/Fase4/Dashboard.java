import java.math.BigDecimal;

public class Dashboard {
    // Adicionado o idDashboard para bater com a PK do seu diagrama
    private Long idDashboard;
    private Long idUsuario;
    private BigDecimal saldoTotalGeral;
    private BigDecimal lucroPrejuizoTotal;

    public Dashboard() {}

    // Construtor atualizado com o idDashboard
    public Dashboard(Long idDashboard, Long idUsuario, BigDecimal saldoTotalGeral, BigDecimal lucroPrejuizoTotal) {
        this.idDashboard = idDashboard;
        this.idUsuario = idUsuario;
        this.saldoTotalGeral = saldoTotalGeral;
        this.lucroPrejuizoTotal = lucroPrejuizoTotal;
    }

    public BigDecimal calcularTotalConsolidado() {
        return saldoTotalGeral;
    }

    public void gerarGraficoDesempenho() {
        /* Lógica para o gráfico */
    }

    // --- Getters e Setters ---

    // Novo Getter e Setter para o ID do Dashboard
    public Long getIdDashboard() { return idDashboard; }
    public void setIdDashboard(Long idDashboard) { this.idDashboard = idDashboard; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public BigDecimal getSaldoTotalGeral() { return saldoTotalGeral; }
    public void setSaldoTotalGeral(BigDecimal saldoTotalGeral) { this.saldoTotalGeral = saldoTotalGeral; }

    public BigDecimal getLucroPrejuizoTotal() { return lucroPrejuizoTotal; }
    public void setLucroPrejuizoTotal(BigDecimal lucroPrejuizoTotal) { this.lucroPrejuizoTotal = lucroPrejuizoTotal; }
}