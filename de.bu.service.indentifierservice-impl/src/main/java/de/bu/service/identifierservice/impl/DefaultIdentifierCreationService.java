package de.bu.service.identifierservice.impl;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import de.bu.service.identifierservice.api.IdentifierBlock;
import de.bu.service.identifierservice.api.IdentifierCreationException;

@WebService(endpointInterface = "de.bu.service.identifierservice.api.IdentifierCreationService")
public class DefaultIdentifierCreationService implements IdentifierCreationService<Long> {

    @LongIdentifier
    @Database
    @Inject
    private IdentifierReservationTransactionScript<Long> longDatabaseIdentifierReseverationTransactionScript;

    @WebMethod
    @Override
    public Long createSingleIdentifier(String owner) throws IdentifierCreationException {
	IdentifierBlock<Long> identifierBlock = longDatabaseIdentifierReseverationTransactionScript
		.createSingleIdentifierBlock(owner);
	return identifierBlock.getStartValue();
    }

    @WebMethod
    @Override
    public IdentifierBlock<Long> createIdentifierBlock(String owner, int size) throws IdentifierCreationException {
	IdentifierBlock<Long> identifierBlock = longDatabaseIdentifierReseverationTransactionScript
		.createIdentifierBlock(owner, size);
	return identifierBlock;
    }

}
