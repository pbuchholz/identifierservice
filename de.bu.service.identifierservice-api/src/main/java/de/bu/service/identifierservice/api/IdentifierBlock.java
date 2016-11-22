package de.bu.service.identifierservice.api;

import java.io.Serializable;

/**
 * Represents a block of identifiers which has been reserved.
 * 
 * @author Philipp Buchholz
 *
 * @param <T>
 */
public class IdentifierBlock<T> implements Serializable {

    private static final long serialVersionUID = -1723983589533379711L;

    private String owner;
    private T startValue;
    private T endValue;

    public String getOwner() {
	return owner;
    }

    public void setOwner(String owner) {
	this.owner = owner;
    }

    public T getStartValue() {
	return startValue;
    }

    public void setStartValue(T startValue) {
	this.startValue = startValue;
    }

    public T getEndValue() {
	return endValue;
    }

    public void setEndValue(T endValue) {
	this.endValue = endValue;
    }

}