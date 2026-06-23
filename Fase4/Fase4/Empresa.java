import java.math.BigDecimal;

public class Empresa {
    private Long idEmpresa;
    private String nomeEmpresa;
    private String razaoSocial;
    private String cnpj;
    private BigDecimal saldoFiduciario;

    public Empresa() {}

    public Empresa(Long idEmpresa, String nomeEmpresa, String razaoSocial, String cnpj, BigDecimal saldoFiduciario) {
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.saldoFiduciario = saldoFiduciario;
    }
    // Polimorfismo estaticos no método "depositarFiduciario"
    public void depositarFiduciario(BigDecimal valor) {
        this.saldoFiduciario = this.saldoFiduciario.add(valor);
    }

    public void depositarFiduciario(int valor) {
        this.saldoFiduciario = this.saldoFiduciario.add(BigDecimal.valueOf(valor));
    }
    // Fim do polimorfismo estatico no método
    public BigDecimal consultarSaldo() {
        return saldoFiduciario;
    }

    // --- Getters e Setters ---
    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public BigDecimal getSaldoFiduciario() { return saldoFiduciario; }
    public void setSaldoFiduciario(BigDecimal saldoFiduciario) { this.saldoFiduciario = saldoFiduciario; }
}