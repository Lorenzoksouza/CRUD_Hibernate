/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.dao;

import br.com.hibernate.entidade.Produto;
import br.com.hibernate.util.Gerador;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class ProdutoDaoImplTest {    
    private Session sessao;
    private Produto produto;
    private ProdutoDao produtoDao;
    
    public ProdutoDaoImplTest() {
        produtoDao = new ProdutoDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        produto = new Produto(null, Gerador.gerarPalavra(10),
              "descrição", 20, Integer.valueOf(Gerador.gerarNumero(3)));
        sessao = HibernateUtil.abrirSessao();
        produtoDao.salvarOuAlterar(produto, sessao);
        sessao.close();        
        assertNotNull(produto.getId());
    }

    @Test
    public void testExcluir() {
        System.out.println("salvar");
        produto = new Produto();
        produto.setId(2l);
        sessao = HibernateUtil.abrirSessao();
        produtoDao.excluir(produto, sessao);
        sessao.close();        
        assertNull(produto.getId());
    }

//    @Test
    public void testAlterar() {
        System.out.println("alterar");
        pesquisarUltimoProdutoBD();
        
        sessao = HibernateUtil.abrirSessao();
        produto.setNome(Gerador.gerarPalavra(10));
        produtoDao.salvarOuAlterar(produto, sessao);
        Produto prodAlt = 
                produtoDao.pesquisarPorId(produto.getId(), sessao);
        sessao.close();
        assertEquals(produto.getNome(), prodAlt.getNome());
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
    }

//    @Test
    public void testListarTodo() {
        System.out.println("listarTodo");
    }
    
    public Produto pesquisarUltimoProdutoBD() {
        sessao = HibernateUtil.abrirSessao();
        Query consulta = sessao.createQuery("Select max(id) from Produto");
        Long id = (Long) consulta.uniqueResult();
        sessao.close();
        if (id == null) {
            testSalvar();
        } else {
            sessao = HibernateUtil.abrirSessao();
            produto = produtoDao.pesquisarPorId(id, sessao);
            sessao.close();
        }
        return produto;
    }
    
}
