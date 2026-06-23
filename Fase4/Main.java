import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    //ARRAYLISTS
    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    static ArrayList<AtivoCripto> listaAtivos = new ArrayList<>();

    //HASHMAPS
    static HashMap<Long, Empresa> mapaEmpresa = new HashMap<>();
    static HashMap<Long, Transacao> mapaTransacao = new HashMap<>();

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.println("=== SISTEMA VOLTZ - MISSÃO TIO PATINHAS ===");
        System.out.println("Deseja inserir dados manualmente? (S/N)");
        String opcao = leitor.nextLine();

        if (opcao.equalsIgnoreCase("s") || opcao.equalsIgnoreCase("sim")) {
            executarComInputs(leitor);
        } else {
            executarTesteAutomatico();
        }

        // Demonstração de ArrayList, HashMap e arquivo (roda sempre)
        demonstrarArrayList();
        demonstrarHashMap();
        salvarEmArquivo();
        lerEAtualizarArquivo();

        System.out.println("\nSistema VOLTZ finalizado com sucesso.");
        leitor.close(); // Fecha o scanner apenas no fim de tudo
    }

    private static void executarComInputs(Scanner leitor) {
        // --- TESTE DE USUÁRIO ---
        try {
            System.out.println("\n--- Cadastro de Usuário ---");
            System.out.print("ID: ");
            Long id = Long.parseLong(leitor.nextLine());
            System.out.print("Nome: ");
            String nome = leitor.nextLine();
            System.out.print("Email: ");
            String email = leitor.nextLine();
            System.out.print("Senha: ");
            String senha = leitor.nextLine();
            System.out.print("Ativar 2FA? (true/false): ");
            boolean mfa = Boolean.parseBoolean(leitor.nextLine());

            Usuario usuario = new Usuario(id, nome, email, senha, mfa);
            System.out.println("Usuário criado: " + usuario.getNome() + " | Status: " + usuario.statusDaConta());

            // Adiciona o usuario criado manualmente ao ArrayList
            listaUsuarios.add(usuario);

        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: Dados inválidos. Usando padrão.");
        }

        // --- TESTE DE EMPRESA E POLIMORFISMO ESTÁTICO ---
        try {
            System.out.println("\n--- Cadastro de Empresa ---");
            System.out.print("Nome da Empresa: ");
            String nomeEmp = leitor.nextLine();
            System.out.print("Saldo Inicial: ");
            BigDecimal saldo = new BigDecimal(leitor.nextLine());

            Empresa empresa = new Empresa(1L, nomeEmp, "Razao Social Ltda", "00.000.000/0001-00", saldo);

            // Testando Polimorfismo Estático (Overload)
            System.out.print("Valor para depósito (BigDecimal): ");
            empresa.depositarFiduciario(new BigDecimal(leitor.nextLine()));

            System.out.print("Valor para depósito (int): ");
            empresa.depositarFiduciario(Integer.parseInt(leitor.nextLine()));

            System.out.println("Saldo final da " + empresa.getNomeEmpresa() + ": R$ " + empresa.getSaldoFiduciario());

            // Adiciona a empresa criada manualmente ao HashMap
            mapaEmpresa.put(empresa.getIdEmpresa(), empresa);

        } catch (Exception e) {
            System.err.println("Erro na operação da Empresa: " + e.getMessage());
        }

        // --- TESTE DE ATIVO E TRANSAÇÃO ---
        try {
            System.out.println("\n--- Registro de Transação ---");
            AtivoCripto btc = new AtivoCripto(1L, "Bitcoin", "BTC", new BigDecimal("350000.00"));

            System.out.print("Quantidade para compra: ");
            BigDecimal qtd = new BigDecimal(leitor.nextLine());

            Transacao t = new Transacao(100L, 1L, 1L, "COMPRA", qtd, btc.getPrecoAtual());
            System.out.println("Transação realizada em: " + t.getDataHora());

            // Adiciona o ativo e a transação criados manualmente às coleções
            listaAtivos.add(btc);
            mapaTransacao.put(t.getIdTransacao(), t);

        } catch (Exception e) {
            System.err.println("Erro ao processar transação.");
        }
    }

    private static void executarTesteAutomatico() {
        System.out.println("\n--- Executando Testes Automatizados ---");

        try {
            // Polimorfismo Dinâmico (Override)
            Usuario uNormal = new Usuario(1L, "João", "joao@email.com", "123", false);
            UsuarioVip uVip = new UsuarioVip(2L, "Maria VIP", "maria@vip.com", "456", true, true);

            System.out.println("User 1: " + uNormal.statusDaConta());
            System.out.println("User 2: " + uVip.statusDaConta());

            // Instanciando outros objetos obrigatórios
            Dashboard dash = new Dashboard(100L, 1L, new BigDecimal("10000.00"), new BigDecimal("500.00"));
            Carteira cart = new Carteira(1L, 1L, 1L, new BigDecimal("0.5"), new BigDecimal("175000.00"));

            System.out.println("Teste de Dashboard e Carteira concluído.");

            // Popula ArrayList e HashMap com os objetos dos testes automáticos
            listaUsuarios.add(uNormal);
            listaUsuarios.add(uVip);

            listaAtivos.add(new AtivoCripto(1L, "Bitcoin", "BTC", new BigDecimal("350000.00")));
            listaAtivos.add(new AtivoCripto(2L, "Ethereum", "ETH", new BigDecimal("18000.00")));
            listaAtivos.add(new AtivoCripto(3L, "Solana", "SOL", new BigDecimal("800.00")));

            mapaEmpresa.put(1L, new Empresa(1L, "Voltz Capital", "Voltz Capital Ltda", "11.111.111/0001-11", new BigDecimal("500000.00")));
            mapaEmpresa.put(2L, new Empresa(2L, "CriptoFund S.A.", "CriptoFund S.A. EIRELI", "22.222.222/0001-22", new BigDecimal("1200000.00")));

            mapaTransacao.put(100L, new Transacao(100L, 1L, 1L, "COMPRA", new BigDecimal("0.5"), new BigDecimal("350000.00")));
            mapaTransacao.put(101L, new Transacao(101L, 1L, 2L, "COMPRA", new BigDecimal("3.0"), new BigDecimal("18000.00")));
            mapaTransacao.put(102L, new Transacao(102L, 2L, 1L, "VENDA", new BigDecimal("0.2"), new BigDecimal("352000.00")));

        } catch (Exception e) {
            System.err.println("Erro nos testes automáticos.");
        }
    }

    //DEMONSTRAÇÃO - ARRAYLIST
    private static void demonstrarArrayList() {
        System.out.println("\n--- ArrayList ---");

        // Iterando usuários — demonstra polimorfismo dinâmico (statusDaConta())
        System.out.println("\n[ArrayList<Usuario>]");
        for (Usuario u : listaUsuarios) {
            System.out.println("  ID " + u.getIdUsuario() + " | " + u.getNome() + " -> " + u.statusDaConta());
        }

        // Iterando ativos cripto
        System.out.println("\n[ArrayList<AtivoCripto>]");
        for (AtivoCripto a : listaAtivos) {
            System.out.println("  " + a.getSimbolo() + " | " + a.getNomeMoeda() + " -> R$ " + a.getPrecoAtual());
        }
    }

    //DEMONSTRAÇÃO - HASHMAP
    private static void demonstrarHashMap() {
        System.out.println("\n--- HashMap ---");

        // Consultando empresa por chave e atualizando valor
        System.out.println("\n[HashMap<Long, Empresa>]");
        if (mapaEmpresa.containsKey(1L)) {
            Empresa emp = mapaEmpresa.get(1L);
            System.out.println("  Empresa ID 1: " + emp.getNomeEmpresa() + " | Saldo: R$ " + emp.getSaldoFiduciario());

            // Polimorfismo Estático (Overload) — depositarFiduciario com BigDecimal
            emp.depositarFiduciario(new BigDecimal("50000.00"));
            mapaEmpresa.put(1L, emp); // Atualiza o objeto no mapa
            System.out.println("  Após depósito R$50.000 -> Saldo: R$ " + mapaEmpresa.get(1L).getSaldoFiduciario());
        }

        // Iterando todas as transações registradas
        System.out.println("\n[HashMap<Long, Transacao>]");
        for (Map.Entry<Long, Transacao> entry : mapaTransacao.entrySet()) {
            Transacao t = entry.getValue();
            System.out.println(" TX " + t.getIdTransacao()
                    + " | Empresa " + t.getIdEmpresa()
                    + " | Ativo " + t.getIdAtivo()
                    + " | Tipo: " + t.getTipoDeTransacao()
                    + " | Qtd: " + t.getQuantidade());
        }
    }

    // CRIAÇÃO DE ARQUIVO DE TEXTO
    // Escreve o conteúdo dos objetos do ArrayList e HashMap no arquivo
    private static void salvarEmArquivo() {
        System.out.println("\n--- Criando arquivo de texto com dados ---");

        String nomeArquivo = "voltz_dados.txt";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {

            writer.write("=== SISTEMA VOLTZ - EXPORTAÇÃO DE DADOS ===");
            writer.newLine();
            writer.write("Gerado em: " + LocalDateTime.now().format(fmt));
            writer.newLine();
            writer.write("==================================================");
            writer.newLine();
            writer.newLine();

            // ---- Seção: Usuários (ArrayList) ----
            writer.write(">> USUARIOS (ArrayList) <<");
            writer.newLine();
            for (Usuario u : listaUsuarios) {
                writer.write("ID=" + u.getIdUsuario()
                        + "|Nome=" + u.getNome()
                        + "|Email=" + u.getEmail()
                        + "|2FA=" + u.isIs2FAAtivo()
                        + "|Tipo=" + (u instanceof UsuarioVip ? "VIP" : "PADRAO")
                        + "|Status=" + u.statusDaConta());
                writer.newLine();
            }
            writer.newLine();

            // ---- Seção: Ativos Cripto (ArrayList) ----
            writer.write(">> ATIVOS CRIPTO (ArrayList) <<");
            writer.newLine();
            for (AtivoCripto a : listaAtivos) {
                writer.write("ID=" + a.getIdAtivo()
                        + "|Nome=" + a.getNomeMoeda()
                        + "|Simbolo=" + a.getSimbolo()
                        + "|Preco=" + a.getPrecoAtual());
                writer.newLine();
            }
            writer.newLine();

            // ---- Seção: Empresas (HashMap) ----
            writer.write(">> EMPRESAS (HashMap) <<");
            writer.newLine();
            for (Map.Entry<Long, Empresa> entry : mapaEmpresa.entrySet()) {
                Empresa e = entry.getValue();
                writer.write("ID=" + e.getIdEmpresa()
                        + "|Nome=" + e.getNomeEmpresa()
                        + "|CNPJ=" + e.getCnpj()
                        + "|Saldo=" + e.getSaldoFiduciario());
                writer.newLine();
            }
            writer.newLine();

            // ---- Seção: Transações (HashMap) ----
            writer.write(">> TRANSACOES (HashMap) <<");
            writer.newLine();
            for (Map.Entry<Long, Transacao> entry : mapaTransacao.entrySet()) {
                Transacao t = entry.getValue();
                writer.write("ID=" + t.getIdTransacao()
                        + "|Empresa=" + t.getIdEmpresa()
                        + "|Ativo=" + t.getIdAtivo()
                        + "|Tipo=" + t.getTipoDeTransacao()
                        + "|Qtd=" + t.getQuantidade()
                        + "|ValorUnit=" + t.getValorUnitarioNoMomento()
                        + "|DataHora=" + t.getDataHora().format(fmt));
                writer.newLine();
            }

            System.out.println("  Arquivo '" + nomeArquivo + "' criado com sucesso.");

        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }


    // LEITURA E ATUALIZAÇÃO DO ARQUIVO DE TEXTO

    private static void lerEAtualizarArquivo() {
        System.out.println("\n--- Lendo e atualizando arquivo de texto ---");

        String nomeArquivo = "voltz_dados.txt";

        // --- LEITURA: exibe as linhas da seção de usuários ---
        System.out.println("\n  [Lendo seção USUARIOS do arquivo]");
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            boolean dentroSecaoUsuarios = false;

            while ((linha = reader.readLine()) != null) {
                if (linha.contains(">> USUARIOS")) {
                    dentroSecaoUsuarios = true;
                    continue;
                }
                if (dentroSecaoUsuarios && linha.startsWith(">>")) break; // chegou na próxima seção
                if (dentroSecaoUsuarios && !linha.isBlank()) {
                    System.out.println("  Lido: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }

        // --- ATUALIZAÇÃO: adiciona nova transação e regrava o arquivo ---
        System.out.println("\n  [Atualizando arquivo: adicionando nova transação ID=200]");
        Transacao novaTransacao = new Transacao(200L, 2L, 3L, "COMPRA",
                new BigDecimal("10.0"), new BigDecimal("800.00"));
        mapaTransacao.put(200L, novaTransacao); // Atualiza o HashMap

        salvarEmArquivo(); // Regrava o arquivo com o HashMap atualizado
        System.out.println("  Arquivo atualizado com sucesso.");
    }

}