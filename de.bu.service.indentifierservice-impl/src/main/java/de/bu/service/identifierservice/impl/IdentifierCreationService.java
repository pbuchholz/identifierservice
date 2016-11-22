package de.bu.service.identifierservice.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import de.bu.service.identifierservice.api.IdentifierBlock;
import de.bu.service.identifierservice.api.IdentifierCreationException;

/**
 * Service which creates identifiers.
 * 
 * @author Philipp Buchholz
 */
@WebService()
public interface IdentifierCreationService<I> {

	/**
	 * Creates and returns a single Identifier of the supported type.
	 * 
	 * @param owner
	 *            The owner for which the identifiers should be created.
	 * @return Next available identifier.
	 * @throws IdentifierCreationException
	 */
	@WebMethod
	// @WebFault?
	I createSingleIdentifier(String owner) throws IdentifierCreationException;

	/**
	 * Creates and returns a {@link IdentifierBlock} with the specified size.
	 * 
	 * @param owner
	 *            The owner for which the {@link IdentifierBlock} should be
	 *            created.
	 * @param size
	 *            The size of the {@link IdentifierBlock}.
	 * @return
	 * @throws IdentifierCreationException
	 */
	@WebMethod
	IdentifierBlock<I> createIdentifierBlock(String owner, int size) throws IdentifierCreationException;

}
