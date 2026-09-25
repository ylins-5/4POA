package br.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.crud.conexao.Conexao;
import br.crud.model.Embarcacao;

public class EmbarcacaoDAO {

    // 1. CREATE (Salvar nova embarcação)
    public void salvar(String nome, String tipo) {
        String sql = "INSERT INTO embarcacoes (nome, tipo, ativa) VALUES (?, ?, true)";

        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            stmt.executeUpdate();
            System.out.println("-> Embarcação '" + nome + "' adicionada com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao salvar embarcação: " + e.getMessage());
        }
    }

    // 2. READ (Listar todas as embarcações)
    public List<Embarcacao> listarTodas() {
        List<Embarcacao> embarcacoes = new ArrayList<>();
        String sql = "SELECT * FROM embarcacoes";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Embarcacao e = new Embarcacao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getBoolean("ativa")
                );
                embarcacoes.add(e);
            }

        } catch (Exception e) {
            System.err.println("Erro ao listar embarcações: " + e.getMessage());
        }
        return embarcacoes;
    }

    // 3. UPDATE (Atualizar status de atividade)
    public void atualizarStatus(int id, boolean ativa) {
        String sql = "UPDATE embarcacoes SET ativa = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, ativa);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("-> Embarcação ID " + id + " atualizada!");

        } catch (Exception e) {
            System.err.println("Erro ao atualizar embarcação: " + e.getMessage());
        }
    }

    // 4. DELETE (Remover uma embarcação)
    public void deletar(int id) {
        String sql = "DELETE FROM embarcacoes WHERE id = ?";

        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("-> Embarcação ID " + id + " removida!");

        } catch (Exception e) {
            System.err.println("Erro ao deletar embarcação: " + e.getMessage());
        }
    }
}