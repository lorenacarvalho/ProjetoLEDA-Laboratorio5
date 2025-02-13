package repositories;

import java.util.Collection;
import java.util.HashMap;

import entities.Dica;
import validators.ValidadorDica;

/**
 * Classe responsável por gerenciar e armazenar as dicas em um repositório.
 * Esta classe permite adicionar e listar dicas.
 */

public class DicaRepository {
	
	private HashMap<Integer, Dica> dicas;
	private int dicasID = 1;
	
	/**
     * Cria um novo repositório de dicas.
     */
	public DicaRepository() {
		this.dicas = new HashMap<>();
	}
	
	/**
     * Adiciona uma dica ao repositório.
     *
     * @param dica a dica a ser adicionada
     * @return o número total de dicas no repositório após a adição ou 0 se a dica não foi adicionada
     * @throws NullPointerException se a dica for nula
     */
	public int adicionaDica(Dica dica) {
		ValidadorDica.validaDica(dica);
		this.dicas.put(dicasID, dica);
		if (dicas.size()>0) {
			dicasID++;
			return dicas.size();
		} else
			return 0;
	}
	
	/**
     * Retorna uma lista das dicas como strings.
     *
     * @return um array de strings representando as dicas
    */
	public String[] listaDicas() {
		if (this.dicas.size() == 0) {
			return new String[0];
		}
		return converteParaArrayDeString(this.dicas.values());
	}
	
	/**
     * Retorna uma lista detalhada das dicas.
     *
     * @return um array de strings com os detalhes das dicas
     */
	public String[] listaDicasDetalhes() {
		if (this.dicas.size() == 0) {
			return new String[0];
		}
		String[] lista = new String[this.dicas.size()];
		int i = 0;
		for (Dica dica : dicas.values()) {
			lista[i++] = dica.exibeDetalhes();
		}
		return lista;
	}
	
	/**
	 * Retorna a representação em string da dica na posição especificada.
	 *
	 * @param posicao a posição da dica a ser retornada (1-based)
	 * @return a representação em string da dica
	 * @throws IllegalArgumentException se posicao for inválida
	 */
	public String listaDica(int posicao) {
		return buscaDica(posicao).toString();
	}
	
	/**
     * Retorna os detalhes da dica na posição especificada.
     *
     * @param posicao a posição da dica a ser retornada (1-based)
     * @return os detalhes da dica
     * @throws IllegalArgumentException se posicao for inválida
     */
	public String listaDicaDetalhes(int posicao) {
		return buscaDica(posicao).exibeDetalhes();
	}
	
	/**
     * Busca e retorna a dica na posição especificada.
     *
     * @param posicao a posição da dica a ser buscada (1-based)
     * @return a dica na posição especificada
     * @throws IllegalArgumentException se posicao for inválida
     */
	public Dica buscaDica(int posicao) {
		ValidadorDica.validaPosicao(posicao, this.dicas.size());
		return this.dicas.get(posicao);
	}

	private <T> String[] converteParaArrayDeString(Collection<T> colecao) {
		String[] lista = new String[colecao.size()];
		int i = 0;
		for (T elemento : colecao) {
			lista[i++] = elemento.toString();
		}
		return lista;
	}

}
