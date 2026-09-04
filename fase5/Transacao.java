import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private Long idTransacao;
    private Long idEmpresa;
    private Long idAtivo;
    private String tipoDeTransacao; 
    private BigDecimal quantidade;
    private BigDecimal valorUnitarioNoMomento; 
    private LocalDateTime dataHora;

    public Transacao() {}

    public Transacao(Long idTransacao, Long idEmpresa, Long idAtivo, String tipoDeTransacao, BigDecimal quantidade, BigDecimal valorUnitarioNoMomento) {
        this.idTransacao = idTransacao;
        this.idEmpresa = idEmpresa;
        this.idAtivo = idAtivo;
        this.tipoDeTransacao = tipoDeTransacao;
        this.quantidade = quantidade;
        this.valorUnitarioNoMomento = valorUnitarioNoMomento;
        this.dataHora = LocalDateTime.now();
    }

    public void executarTransacao() {
        /* Este método deve executar a transação de acordo com o tipo (Compra ou venda) desta transação. */
    }

    // --- Getters e Setters ---
    public Long getIdTransacao() { return idTransacao; }
    public void setIdTransacao(Long idTransacao) { this.idTransacao = idTransacao; }

    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }

    public Long getIdAtivo() { return idAtivo; }
    public void setIdAtivo(Long idAtivo) { this.idAtivo = idAtivo; }

    public String getTipoDeTransacao() { return tipoDeTransacao; }
    public void setTipoDeTransacao(String tipoDeTransacao) { this.tipoDeTransacao = tipoDeTransacao; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorUnitarioNoMomento() { return valorUnitarioNoMomento; }
    public void setValorUnitarioNoMomento(BigDecimal valorUnitarioNoMomento) { this.valorUnitarioNoMomento = valorUnitarioNoMomento; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}