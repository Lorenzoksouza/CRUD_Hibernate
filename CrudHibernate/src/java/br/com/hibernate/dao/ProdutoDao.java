/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernate.dao;

import br.com.hibernate.entidade.Produto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public interface ProdutoDao extends BaseDao<Produto, Long>{
   
    List<Produto> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
}
