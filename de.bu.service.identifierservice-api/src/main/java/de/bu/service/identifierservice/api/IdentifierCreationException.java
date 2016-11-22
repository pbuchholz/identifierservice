package de.bu.service.identifierservice.api;

/**
 * {@link Exception} which is thrown if an identifier could not be created.
 * 
 * @author Philipp Buchholz
 */
public class IdentifierCreationException extends Exception {

	private static final long serialVersionUID = 8977076424171913529L;

	public IdentifierCreationException(Exception e) {
		super(e);
	}

}