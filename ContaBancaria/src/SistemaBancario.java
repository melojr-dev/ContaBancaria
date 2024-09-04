import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaBancario {
    private Map<Integer, ContaBancaria> contas;
    private Scanner scanner;

    public SistemaBancario() {
        contas = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Registrar conta");
            System.out.println("2. Visualizar saldo");
            System.out.println("3. Transferir dinheiro");
            System.out.println("4. Sacar");
            System.out.println("5. Solicitar Empréstimo");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o newline

            switch (opcao) {
                case 1:
                    registrarConta();
                    break;
                case 2:
                    visualizarSaldo();
                    break;
                case 3:
                    transferirDinheiro();
                    break;
                case 4:
                    sacarDinheiro();
                    break;
                case 5:
                    solicitarEmprestimo();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registrarConta() {
        System.out.print("Digite o nome do titular: ");
        String nomeTitular = scanner.nextLine();
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Digite o saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine(); // Consome o newline

        if (contas.containsKey(numeroConta)) {
            System.out.println("Número da conta já existe.");
        } else {
            contas.put(numeroConta, new ContaBancaria(nomeTitular, numeroConta, saldo));
            System.out.println("Conta registrada com sucesso.");
        }
    }

    private void visualizarSaldo() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine(); // Consome o newline

        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null) {
            System.out.println(conta);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void transferirDinheiro() {
        System.out.print("Digite o número da conta remetente: ");
        int remetenteNumero = scanner.nextInt();
        System.out.print("Digite o número da conta destinatário: ");
        int destinatarioNumero = scanner.nextInt();
        System.out.print("Digite o valor a ser transferido: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consome o newline

        ContaBancaria remetente = contas.get(remetenteNumero);
        ContaBancaria destinatario = contas.get(destinatarioNumero);

        if (remetente == null) {
            System.out.println("Conta remetente não encontrada.");
        } else if (destinatario == null) {
            System.out.println("Conta destinatário não encontrada.");
        } else if (remetente.transferir(destinatario, valor)) {
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para a transferência.");
        }
    }

    private void sacarDinheiro() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Digite o valor a ser sacado: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consome o newline

        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null) {
            if (conta.sacar(valor)) {
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para o saque.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private void solicitarEmprestimo() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        System.out.print("Digite o valor do empréstimo: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consome o newline

        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null) {
            if (conta.solicitarEmprestimo(valor)) {
                System.out.println("Empréstimo solicitado com sucesso.");
            } else {
                System.out.println("Não foi possível solicitar o empréstimo.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}
