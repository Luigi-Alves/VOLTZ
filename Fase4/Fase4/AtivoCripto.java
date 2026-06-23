import java.math.BigDecimal;

public class AtivoCripto {
    private Long idAtivo;
    private String nomeMoeda;
    private String simbolo;
    private BigDecimal precoAtual;

    public AtivoCripto() {}

    public AtivoCripto(Long idAtivo, String nomeMoeda, String simbolo, BigDecimal precoAtual) {
        this.idAtivo = idAtivo;
        this.nomeMoeda = nomeMoeda;
        this.simbolo = simbolo;
        this.precoAtual = precoAtual;
    }

    public void atualizarPreco(BigDecimal novoPreco) {
        this.precoAtual = novoPreco;
    }

    // --- Getters e Setters ---
    public Long getIdAtivo() { return idAtivo; }
    public void setIdAtivo(Long idAtivo) { this.idAtivo = idAtivo; }

    public String getNomeMoeda() { return nomeMoeda; }
    public void setNomeMoeda(String nomeMoeda) { this.nomeMoeda = nomeMoeda; }

    public String getSimbolo() { return simbolo; }
    public void setSimbolo(String simbolo) { this.simbolo = simbolo; }

    public BigDecimal getPrecoAtual() { return precoAtual; }
    public void setPrecoAtual(BigDecimal precoAtual) { this.precoAtual = precoAtual; }
}