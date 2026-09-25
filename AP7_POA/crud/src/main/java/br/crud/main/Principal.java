package br.crud.main;

import br.crud.dao.EmbarcacaoDAO;

public class Principal {
    public static void main(String[] args) {
        EmbarcacaoDAO dao = new EmbarcacaoDAO();

        System.out.println("=== 1. TESTANDO O 'CREATE' ===");
        dao.salvar("Bram Buck", "PSV");
        dao.salvar("Island Enforcer", "MPSV");
        dao.salvar("Bram Atlas", "AHTS");

        System.out.println("\n=== 2. TESTANDO O 'READ' ===");
        // ESTA LINHA EXIBE A LISTA NO CONSOLE:
        dao.listarTodas().forEach(System.out::println);

        System.out.println("\n=== 3. TESTANDO O 'UPDATE' ===");
        dao.atualizarStatus(1, false);
        // IMPRIME NOVAMENTE PARA VER A MUDANÇA:
        dao.listarTodas().forEach(System.out::println);

        System.out.println("\n=== 4. TESTANDO O 'DELETE' ===");
        dao.deletar(3);

        System.out.println("\n=== RESULTADO FINAL NO BANCO ===");
        // IMPRIME O RESULTADO FINAL:
        dao.listarTodas().forEach(System.out::println);
    }
}