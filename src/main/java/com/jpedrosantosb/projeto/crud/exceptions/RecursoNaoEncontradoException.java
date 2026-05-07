package com.jpedrosantosb.projeto.crud.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6355496264585872569L;

	public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
