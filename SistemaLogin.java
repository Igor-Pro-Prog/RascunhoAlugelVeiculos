
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SistemaLogin {
    private static String o;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo ao sistema de aluguel de veículos!");
            System.out.println("Por favor, informe seu nome:");
            String nome = scanner.nextLine();
            System.out.println("Por favor, informe seu CPF:");
            String cpf = scanner.nextLine();
            System.out.println("Por favor, informe seu endereço:");
            String endereco = scanner.nextLine();
            System.out.println("Por favor, informe seu telefone:");
            String telefone = scanner.nextLine();
            System.out.println("Por favor, informe seu email:");
            String email = scanner.nextLine();
            System.out.println("Por favor, informe sua data de nascimento:");
            String dataNascimento = scanner.nextLine();
            // cria um csv com os dados do usuário
            try {
                FileWriter writer = new FileWriter("DadosPessoais.csv", true);
                writer.append(nome);
                writer.append(",");
                writer.append(cpf);
                writer.append(",");
                writer.append(endereco);
                writer.append(",");
                writer.append(telefone);
                writer.append(",");
                writer.append(email);
                writer.append(",");
                writer.append(dataNascimento);
                writer.append(",");
                int saldo = 0;
                writer.append(String.valueOf(saldo));
                writer.append("\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
            }
            System.out.println("Por favor, informe seu tipo de usuário (gerente/cliente):");
            String tipoUsuario = scanner.nextLine();
            int saldo;
            if (tipoUsuario.equalsIgnoreCase("gerente")) {
                System.out.println("Digite o login do gerente:");
                String login = scanner.nextLine();
                System.out.println("Digite a senha do gerente:");
                String senha = scanner.nextLine();

                if (login.equalsIgnoreCase("admin") && senha.equals("admin")) {
                    System.out.println("Login de gerente bem-sucedido!");
                    System.out.println("Seja bem-vindo " + nome + "!");
                    saldo = 0;
                    System.out.println("Seu saldo é de: " + saldo);
                    System.out.println("Oque deseja fazer:");
                    int opcao;
                    do {
                        System.out.println("O que deseja fazer:");
                        System.out.println("0 - Para sair da conta");
                        System.out.println("1 - Adicionar veículo");
                        System.out.println("2 - Cadastrar aluguel de veículo");
                        System.out.println("3 - Listar veículos disponíveis");
                        System.out.println("4 - Adicionar dinheiro à conta");
                        System.out.println("5 - Remover carro");
                        opcao = scanner.nextInt();
                switch (opcao) {
                            case 0:
                                System.out.println("Obrigado por utilizar nosso sistema! Volte sempre!");
                                break;
                            case 1:
                                System.out.println("Digite a marca do veículo:");
                                String marca = scanner.nextLine();
                                System.out.println("Digite o modelo do veículo:");
                                String modelo = scanner.nextLine();
                                System.out.println("Digite o ano do veículo:");
                                String ano = scanner.nextLine();
                                System.out.println("Digite o valor da diária do veículo:");
                                String valor = scanner.nextLine();
                                // cria um csv com os dados do veículo
                                try {
                                    FileWriter writer = new FileWriter("dados.csv", true);
                                    writer.append(marca);
                                    writer.append(",");
                                    writer.append(modelo);
                                    writer.append(",");
                                    writer.append(ano);
                                    writer.append(",");
                                    writer.append(valor);
                                    writer.append("\n");
                                    writer.flush();
                                    writer.close();
                                } catch (IOException e) {
                                }
                                break;
                            case 2:
                                System.out.println("Digite o CPF do cliente:");
                                String cpfCliente = scanner.nextLine();
                                System.out.println("Digite o carro que deseja alugar:");
                                String carroAlugado = scanner.nextLine();
                                // muda o status do veículo de disponivel para false
                                try {
                                    File inputFile = new File("dados.csv");
                                    File tempFile = new File("dadosTemp.csv");
                                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                                    String lineToRemove = carroAlugado;
                                    String currentLine;
                                    while ((currentLine = reader.readLine()) != null) {
                                        String trimmedLine = currentLine.trim();
                                        if (trimmedLine.contains(lineToRemove)) continue;
                                        writer.write(currentLine + System.getProperty("line.separator"));
                                    }
                                    writer.close();
                                    reader.close();
                                    boolean successful = tempFile.renameTo(inputFile);
                                } catch (IOException e) {
                                }
                                break;
                            case 3:
                                String csvFile = "dados.csv"; // Nome do arquivo CSV
                                String line;
                                String csvSplitBy = ","; // Delimitador usado no arquivo CSV
                                System.out.println("Digite o veículo a ser buscado:");
                                String veiculoBuscado = new Scanner(System.in).nextLine();

                                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                                    boolean veiculoEncontrado = false;

                                    while
                                        ((line = br.readLine()) != null) {
                                        String[] dat = line.split(csvSplitBy);
                                        String marcaa = dat[0];
                                        String modeloo = dat[1];
                                        String anoo = dat[2];
                                        String valorDiaria = dat[3];

                                        if (marcaa.equalsIgnoreCase(veiculoBuscado) || modeloo.equalsIgnoreCase(veiculoBuscado)) {
                                            System.out.println("Veículo encontrado!");
                                            System.out.println("Marca: " + marcaa + " Modelo: " + modeloo + " Ano: " + anoo + " Valor da diária: " + valorDiaria);
                                            veiculoEncontrado = true;
                                        }
                                    }
                                    if (!veiculoEncontrado) {
                                        System.out.println("Veículo não encontrado!");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.println("Digite o valor a ser adicionado à conta:");
                                int saldoConta = scanner.nextInt();
                                saldo = saldo + saldoConta;
                                System.out.println("Seu saldo é de: " + saldo);
                                break;  
                            case 5:
                                System.out.println("Digite o veículo a ser removido:");
                                String veiculoRemovido = new Scanner(System.in).nextLine();
                                String csvFile2 = "dados.csv"; // Nome do arquivo CSV
                                String line2;
                                String csvSplitBy2 = ","; // Delimitador usado no arquivo CSV
                                try (BufferedReader br = new BufferedReader(new FileReader(csvFile2))) {
                                    boolean veiculoEncontrado = false;
                                    while ((line2 = br.readLine()) != null) {
                                        String[] dat = line2.split(csvSplitBy2);
                                        String marcaa = dat[0];
                                        String modeloo = dat[1];
                                        String anoo = dat[2];
                                        String valorDiaria = dat[3];
                                        if (marcaa.equalsIgnoreCase(veiculoRemovido) || o.equalsIgnoreCase(veiculoRemovido)) {
                                            System.out.println("Veículo encontrado!");
                                            System.out.println("Marca: " + marcaa + " Modelo: " + modeloo + " Ano: " + anoo + " Valor da diária: " + valorDiaria);
                                            veiculoEncontrado = true;
                                        }
                                        // apaga a linha do arquivo csv correspondente ao veículo encontrado
                                        if (veiculoEncontrado) {
                                            try {
                                                File inputFile = new File("dados.csv");
                                                File tempFile = new File("myTempFile.csv");
                                                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                                                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                                                String lineToRemove = line2;
                                                String currentLine;
                                                while ((currentLine = reader.readLine()) != null) {
                                                    // trim newline when comparing with lineToRemove
                                                    String trimmedLine = currentLine.trim();
                                                    if (trimmedLine.equals(lineToRemove)) continue;
                                                    writer.write(currentLine + System.getProperty("line.separator"));
                                                }
                                                writer.close();
                                                reader.close();
                                                boolean successful = tempFile.renameTo(inputFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    if (!veiculoEncontrado) {
                                        System.out.println("Veículo não encontrado!");
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (opcao != 0);


                             } else {
                    System.out.println("Login de gerente falhou!");
                }
        }else
            if (tipoUsuario.equalsIgnoreCase("cliente")) {
                System.out.println("Digite o login do cliente:");
                String log = scanner.nextLine();
                System.out.println("Digite a senha do cliente:");
                String sen = scanner.nextLine();
                if (log.equalsIgnoreCase("sorvete") && sen.equals("sorvete")) {
                    System.out.println("Login de cliente bem-sucedido!");
                    System.out.println("seja bem-vindo " + nome + "!");
                    saldo = 0;
                    System.out.println("Seu saldo é de: " + saldo);

                    int opcao;
                    do {
                        System.out.println("O que deseja fazer:");
                        System.out.println("0 - Para sair da conta");
                        System.out.println("1 - Agendar aluguel de um veículo");
                        System.out.println("2 - Agendar devolução de um veículo");
                        System.out.println("3 - Listar veículos disponíveis");
                        System.out.println("4 - Adicionar dinheiro à conta");
                        opcao = scanner.nextInt();

                        switch (opcao) {
                            case 0:
                                System.out.println("Obrigado por utilizar nosso sistema! Volte sempre!");
                                break;
                            case 1:
                                // pede ao cliente para digitar uma data de retirada
                                System.out.println("Digite a data que pretende retirar (formato yyyyMMdd):");
                                int dataRetirada = scanner.nextInt();
                                // formata a data de retirada para o formato dd/MM/yyyy
                                String dataRet = String.format("%02d/%02d/%04d", dataRetirada % 100, (dataRetirada / 100) % 100, dataRetirada / 10000);
                                System.out.println("Data de retirada: " + dataRet);
                                System.out.println("Dirija-se ao balcão para finalizar o processo.");
                                break;
                            
                           case 2:
                                System.out.println("Digite a data que pretende devolver (formato yyyyMMdd):");
                                int dataDevolucao2 = scanner.nextInt();
                                // formata a data de retirada para o formato dd/MM/yyyy
                                String datadev = String.format("%02d/%02d/%04d", dataDevolucao2 % 100, (dataDevolucao2 / 100) % 100, dataDevolucao2 / 10000);
                                System.out.println("Data de devolução: " + datadev);
                                System.out.println("Dirija-se à locadora mais próxima na data para finalizar o processo.");
                                break;

                            case 3:
                                String csvFile = "dados.csv"; // Nome do arquivo CSV
                                String line;
                                String csvSplitBy = ","; // Delimitador usado no arquivo CSV
                                System.out.println("Digite o veículo a ser buscado:");
                                String veiculoBuscado = new Scanner(System.in).nextLine();

                                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                                    boolean veiculoEncontrado = false;

                                    while
                                        ((line = br.readLine()) != null) {
                                        String[] dat = line.split(csvSplitBy);
                                        String marca = dat[0];
                                        String modelo = dat[1];
                                        String ano = dat[2];
                                        String valorDiaria = dat[3];

                                        if (marca.equalsIgnoreCase(veiculoBuscado) || modelo.equalsIgnoreCase(veiculoBuscado)) {
                                            System.out.println("Veículo disponível:");
                                            System.out.println("Marca: " + marca);
                                            System.out.println("Modelo: " + modelo);
                                            System.out.println("Ano: " + ano);
                                            System.out.println("Valor Diária: " + valorDiaria);
                                            System.out.println("----------------------");
                                            veiculoEncontrado = true;
                                        }
                                    }
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.println("Digite o valor a ser adicionado à conta:");
                                int valor = scanner.nextInt();
                                saldo += valor;
                                System.out.println("Valor adicionado com sucesso!");
                                System.out.println("Seu saldo é de: " + saldo);
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    while (opcao != 0);
                }
                else {
                    System.out.println("Login ou Senha invalidos!");
                }
            }
        }
    }
}



