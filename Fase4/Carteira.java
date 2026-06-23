import java.math.BigDecimal;

public class Carteira {
    private Long idCarteira;
    private Long idEmpresa;
    private Long idAtivo;
    private BigDecimal quantidade; 
    private BigDecimal saldoTotalAtualizado;

    public Carteira() {}

    public Carteira(Long idCarteira, Long idEmpresa, Long idAtivo, BigDecimal quantidade, BigDecimal saldoTotalAtualizado) {
        this.idCarteira = idCarteira;
        this.idEmpresa = idEmpresa;
        this.idAtivo = idAtivo;
        this.quantidade = quantidade;
        this.saldoTotalAtualizado = saldoTotalAtualizado;
    }

    public BigDecimal calcularValorTotal() {
        return saldoTotalAtualizado;
    }

    public void atualizarQuantidade(BigDecimal novaQuantidade) {
        this.quantidade = novaQuantidade;
    }

    // --- Getters e Setters ---
    public Long getIdCarteira() { return idCarteira; }
    public void setIdCarteira(Long idCarteira) { this.idCarteira = idCarteira; }

    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }

    public Long getIdAtivo() { return idAtivo; }
    public void setIdAtivo(Long idAtivo) { this.idAtivo = idAtivo; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public BigDecimal getSaldoTotalAtualizado() { return saldoTotalAtualizado; }
    public void setSaldoTotalAtualizado(BigDecimal saldoTotalAtualizado) { this.saldoTotalAtualizado = saldoTotalAtualizado; }
}