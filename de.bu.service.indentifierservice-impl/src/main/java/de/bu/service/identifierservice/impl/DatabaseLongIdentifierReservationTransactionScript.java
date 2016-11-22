package de.bu.service.identifierservice.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import de.bu.service.identifierservice.api.IdentifierBlock;
import de.bu.service.identifierservice.api.IdentifierCreationException;

@LongIdentifier
@Database
@ApplicationScoped
public class DatabaseLongIdentifierReservationTransactionScript
	implements IdentifierReservationTransactionScript<Long> {

    public enum IdentifierFields {
	OWNER(1),
	BLOCKSTART(2),
	BLOCKEND(3);

	private int index;

	public int index() {
	    return index;
	}

	private IdentifierFields(int index) {
	    this.index = index;
	}
    }

    public enum IdentifierStatements {

	RESERVE_IDENTIFIER_BLOCK("INSERT INTO identifier_block (owner, blockstart, blockend) VALUES (?,?,?)"),
	CURRENT_HIGH("SELECT MAX(blockend) as currentHigh FROM identifier_block");

	private String identifierStatement;

	public String identifierStatement() {
	    return this.identifierStatement;
	}

	private IdentifierStatements(String identifierStatement) {
	    this.identifierStatement = identifierStatement;
	}

    }

    @Resource(name = "jdbc/IdentifierDataSource")
    private DataSource identifierDataSource;

    @Lock(LockType.WRITE)
    @Override
    public IdentifierBlock<Long> createSingleIdentifierBlock(String owner) throws IdentifierCreationException {
	return this.createIdentifierBlock(owner, 1);
    }

    @Transactional(TxType.REQUIRES_NEW)
    @Lock(LockType.WRITE)
    @Override
    public IdentifierBlock<Long> createIdentifierBlock(String owner, int size) throws IdentifierCreationException {
	try (Connection connection = identifierDataSource.getConnection()) {

	    /* Read and update current high. */
	    long currentHigh = this.queryCurrentHigh(connection);
	    long blockstart = currentHigh + 1;
	    long blockend = currentHigh + size;
	    this.insertIdentifierBlock(connection, owner, blockstart, blockend);

	    /* Create and return IdentifierBlock. */
	    IdentifierBlock<Long> identifierBlock = new IdentifierBlock<>();
	    identifierBlock.setOwner(owner);
	    identifierBlock.setStartValue(blockstart);
	    identifierBlock.setEndValue(blockend);
	    return identifierBlock;
	} catch (SQLException e) {
	    throw new IdentifierCreationException(e);
	}
    }

    @Transactional(TxType.REQUIRED)
    private void insertIdentifierBlock(Connection connection, String owner, long blockstart, long blockend)
	    throws SQLException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement(IdentifierStatements.RESERVE_IDENTIFIER_BLOCK.identifierStatement())) {
	    preparedStatement.setString(IdentifierFields.OWNER.index(), owner);
	    preparedStatement.setLong(IdentifierFields.BLOCKSTART.index(), blockstart);
	    preparedStatement.setLong(IdentifierFields.BLOCKEND.index(), blockend);
	    preparedStatement.execute();
	}
    }

    @Transactional(TxType.REQUIRED)
    private long queryCurrentHigh(Connection connection) throws SQLException {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		IdentifierStatements.CURRENT_HIGH.identifierStatement(), ResultSet.TYPE_SCROLL_INSENSITIVE,
		ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
	    ResultSet resultSet = preparedStatement.executeQuery();
	    resultSet.first();
	    return resultSet.getLong("currentHigh");
	}
    }

}