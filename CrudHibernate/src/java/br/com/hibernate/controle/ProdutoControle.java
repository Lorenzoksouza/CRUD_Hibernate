/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.controle;

import br.com.hibernate.dao.HibernateUtil;
import br.com.hibernate.dao.ProdutoDao;
import br.com.hibernate.dao.ProdutoDaoImpl;
import br.com.hibernate.entidade.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "ProdutoControle", urlPatterns = {"/ProdutoControle"})
public class ProdutoControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Session sessao;
    private Produto produto;
    private ProdutoDao produtoDao;
    private RequestDispatcher redireciona;

    protected void processarRequisicao() throws ServletException, IOException {
        String comando = request.getParameter("cmd");
        switch (comando) {
            case "salvar":
                salvar();
                break;
            case "pesquisar":
                pesquisarPorNome();
                break;
            case "excluir":
                excluir();
                break;
        }
        redireciona.forward(request, response);
    }

    private void salvar() {
        produto = new Produto();
        produto.setNome(request.getParameter("nome"));
        produto.setEstoque(Integer.valueOf(request.getParameter("estoque")));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        produto.setDescricao(request.getParameter("descricao"));
        produtoDao = new ProdutoDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        try {
            produtoDao.salvarOuAlterar(produto, sessao);
            request.setAttribute("msg_sucesso", "Salvo com sucesso!");
        } catch (HibernateException e) {
            request.setAttribute("msg_erro", "Erro ao Salvar!");
            System.out.println("Erro ao salvar na servlet " + e.getMessage());
        } finally {
            redireciona = request.getRequestDispatcher("novoProduto.jsp");
            sessao.close();
        }
    }

    public void pesquisarPorNome() {
        String nome = request.getParameter("nome");
        produtoDao = new ProdutoDaoImpl();
        try {
            sessao = HibernateUtil.abrirSessao();
            List<Produto> produtos = produtoDao.pesquisarPorNome(nome, sessao);
            redireciona = request.getRequestDispatcher("pesquisarProduto.jsp");
            request.setAttribute("produtos", produtos);

        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar por nome " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    private void excluir() {
        produto = new Produto();
        produto.setId(Long.parseLong(request.getParameter("id")));
        produtoDao = new ProdutoDaoImpl();
        try {
            sessao = HibernateUtil.abrirSessao();
            produto = produtoDao.pesquisarPorId(produto.getId(), sessao);
            produtoDao.excluir(produto, sessao);
            request.setAttribute("msg_sucesso", "Excluído com sucesso!");
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir " + e.getMessage());
        } finally {
            redireciona = request.getRequestDispatcher("pesquisarProduto.jsp");
            sessao.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processarRequisicao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processarRequisicao();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
