package de.bu.service.identifierservice.impl;

import de.bu.service.identifierservice.api.IdentifierBlock;
import de.bu.service.identifierservice.api.IdentifierCreationException;

/**
 * Responsible for the transactional reservation of {@link IdentifierBlock}s.
 * 
 * A {@link IdentifierCreationService} delegates the actual reservation to an
 * implementation of {@link IdentifierReservationTransactionScript}.
 * 
 * @author Philipp Buchholz
 */
public interface IdentifierReservationTransactionScript<I> {

    IdentifierBlock<I> createSingleIdentifierBlock(String owner) throws IdentifierCreationException;

    IdentifierBlock<I> createIdentifierBlock(String owner, int size) throws IdentifierCreationException;

}