/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.dao;

import br.com.hibernate.entidade.Cliente;
import br.com.hibernate.util.Gerador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class ClienteDaoImplTest {

    private Cliente cliente;
    private Session sessao;
    private ClienteDao clienteDao;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

//    @Test
    public void testeSalvar() {
        System.out.println("teste salvar...");
        cliente = new Cliente(null, "teste nome5",
                Gerador.gerarCpf(),
                "teste email" + Gerador.gerarNumero(5),
                "65326", "3030-3030");
        sessao = HibernateUtil.abrirSessao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        sessao.close();
        assertNotNull(cliente.getId());
    }

    @Test
    public void testeAlterar() {
        System.out.println("teste alterar...");
        pesquisarUltimoClienteBD();
        cliente.setNome(Gerador.gerarPalavra(10));
        
        sessao = HibernateUtil.abrirSessao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        
        Cliente cliAlt = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();        
        assertEquals(cliente.getNome(), cliAlt.getNome());
    }

    @Test
    public void testExcluir() {
        System.out.println("excluir cliente...");
        pesquisarUltimoClienteBD();
        sessao = HibernateUtil.abrirSessao();
        clienteDao.excluir(cliente, sessao);
        Cliente clienteExc = 
                clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();
        assertNull(clienteExc);        
    }

    @Test
    public void testListarTodo() {
        System.out.println("listarTodo");
        pesquisarUltimoClienteBD();
        sessao = HibernateUtil.abrirSessao();
        List<Cliente> clientes = clienteDao.listarTodo(sessao);
        sessao.close();
        assertTrue(!clientes.isEmpty());
        
    }

    public Cliente pesquisarUltimoClienteBD() {
        sessao = HibernateUtil.abrirSessao();
        Query consulta = sessao.createQuery("Select max(id) from Cliente");
        Long id = (Long) consulta.uniqueResult();
        sessao.close();
        if (id == null) {
            testeSalvar();
        } else {
            sessao = HibernateUtil.abrirSessao();
            cliente = clienteDao.pesquisarPorId(id, sessao);
            sessao.close();
        }
        return cliente;
    }


}
