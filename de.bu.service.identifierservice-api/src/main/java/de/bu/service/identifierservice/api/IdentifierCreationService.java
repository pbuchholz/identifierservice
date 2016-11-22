package de.bu.service.identifierservice.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Service which creates identifiers.
 * 
 * @author Philipp Buchholz
 * 
 * @param <I>
 */
@WebService()
public interface IdentifierCreationService<I> {

	/**
	 * Creates and returns a single Identifier of the supported type.
	 * 
	 * @param owner
	 * @return
	 * @throws IdentifierCreatioTnException
	 */
	@WebMethod
	I createSingleIdentifier(String owner) throws IdentifierCreationException;

	/**
	 * Creates and returns a {@link IdentifierBlock} container the ammount of
	 * identifiers specified in the size argument.
	 * 
	 * @param owner
	 * @param size
	 * @return
	 * @throws IdentifierCreationException
	 */
	@WebMethod
	IdentifierBlock<I> createIdentifierBlock(String owner, int size) throws IdentifierCreationException;

}
