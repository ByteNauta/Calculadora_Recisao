import java.util.Scanner;
import java.text.DecimalFormat;

public class CalculadoraRescisaoCLT {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Configuração
        System.out.println("\n=== CALCULADORA DE RESCISÃO CLT ===\n");

        // Entrada de dados
        System.out.print("Salário mensal (R$): ");
        double salario = validarNumero(scanner);

        System.out.print("Meses trabalhados (0-12): ");
        int mesesTrabalhados = (int) validarNumero(scanner, 0, 12);

        System.out.print("Horas extras trabalhadas: ");
        double horasExtras = validarNumero(scanner);

        System.out.print("Valor da hora extra (R$): ");
        double valorHoraExtra = validarNumero(scanner);

        System.out.print("Saldo do FGTS (R$): ");
        double saldoFGTS = validarNumero(scanner);

        System.out.print("Tem direito a aviso prévio? (S/N): ");
        boolean avisoPrevio = validarSN(scanner);

        // Cálculos
        double salarioProporcional = calcularProporcional(salario, mesesTrabalhados);
        double valorHorasExtras = horasExtras * valorHoraExtra;
        double decimoTerceiro = calcularProporcional(salario, mesesTrabalhados);
        double ferias = calcularProporcional(salario, mesesTrabalhados);
        double tercoConstitucional = ferias / 3;
        double avisoPrevioIndenizado = avisoPrevio ? salario : 0;
        double multaFGTS = saldoFGTS * 0.4;

        // Total
        double total = salarioProporcional + valorHorasExtras + decimoTerceiro + 
                      ferias + tercoConstitucional + avisoPrevioIndenizado + multaFGTS;

        // Resultado
        System.out.println("\n=== RESUMO DA RESCISÃO ===");
        System.out.println("1. Salário proporcional (" + mesesTrabalhados + " meses): R$ " + df.format(salarioProporcional));
        System.out.println("2. Horas extras: R$ " + df.format(valorHorasExtras));
        System.out.println("3. 13º salário: R$ " + df.format(decimoTerceiro));
        System.out.println("4. Férias proporcionais: R$ " + df.format(ferias));
        System.out.println("5. 1/3 constitucional: R$ " + df.format(tercoConstitucional));
        System.out.println("6. Aviso prévio: R$ " + df.format(avisoPrevioIndenizado));
        System.out.println("7. Multa FGTS (40%): R$ " + df.format(multaFGTS));
        System.out.println("----------------------------------");
        System.out.println("TOTAL: R$ " + df.format(total));

        scanner.close();
    }

    // Métodos auxiliares
    private static double validarNumero(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.next().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite novamente: ");
            }
        }
    }

    private static double validarNumero(Scanner scanner, double min, double max) {
        while (true) {
            double valor = validarNumero(scanner);
            if (valor >= min && valor <= max) return valor;
            System.out.print("Valor deve estar entre " + min + " e " + max + ": ");
        }
    }

    private static boolean validarSN(Scanner scanner) {
        while (true) {
            String resposta = scanner.next().toUpperCase();
            if (resposta.equals("S")) return true;
            if (resposta.equals("N")) return false;
            System.out.print("Digite S ou N: ");
        }
    }

    private static double calcularProporcional(double salario, int meses) {
        return (salario / 12) * meses;
    }
}