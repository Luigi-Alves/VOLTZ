public class UsuarioVip extends Usuario {
    private boolean status;

    public UsuarioVip() {
        super();
    }

    public UsuarioVip(Long idUsuario, String nome, String email, String senhaCriptografada, boolean is2FAAtivo, boolean status) {
        super(idUsuario, nome, email, senhaCriptografada, is2FAAtivo);
        this.status = status;
    }

    // Polimorfismo Dinamico com a classe Usuario
    @Override
    public String statusDaConta() {
        return "esta com o status VIP";
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
