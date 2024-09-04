public class ContaBancaria {
    private String nomeTitular;
    private int numeroConta;
    private double saldo;

    // Construtor
    public ContaBancaria(String nomeTitular, int numeroConta, double saldo) {
        this.nomeTitular = nomeTitular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    // Getters
    public String getNomeTitular() {
        return nomeTitular;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    // Métodos para modificar o saldo
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor do depósito deve ser positivo.");
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
            return false;
        }
    }

    public boolean solicitarEmprestimo(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        } else {
            System.out.println("O valor do empréstimo deve ser superior a 0 reais.");
            return false;
        }
    }

    public boolean transferir(ContaBancaria destinatario, double valor) {
        if (sacar(valor)) {
            destinatario.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Nome do Titular: " + nomeTitular + ", Número da Conta: " + numeroConta + ", Saldo: R$ " + saldo;
    }
}
