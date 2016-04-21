package com.adoa.azportal.persistence.generator;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.logging.Logger;




@SessionScoped
@Named("sequenceGenerator")
public class SequenceGenerator implements Serializable {

	private static final long serialVersionUID = 7156348610147245752L;

	@Inject
	Logger logger;

	@Inject
	private EntityManager entityManager;

	public SequenceGenerator() {

	}

	/**
	 * Use this method when SequenceGenerator is managed by Seam, was injected
	 * into other component via @In, in this case it will have access to
	 * EntityManager without needing to provide a database connection
	 * 
	 * @param procedureName
	 * @return
	 */
	public Integer getSequenceValue(String procedureName) {

		Query query = entityManager.createNamedQuery(procedureName);
		return (Integer) query.getSingleResult();
	}

	// This is bad, do not use this method when use seam/jboss managed
	// transaction, the way you get connection to pass to this method it bad,
	// after method finished execution the connection will be closed
	/**
	 * Use this method when SequneceGenerator is used as POJO and not managed by
	 * Seam context, meaning was not injected via @In annotation, but was
	 * instantiated by calling constructor
	 * 
	 * @param procedureName
	 * @param connection
	 * @return
	 */
	public Integer getSequenceValue(String procedureName, Connection conn) {
		Integer resultValue = null;
		try {
			String procedureCall = "{?  = call " + procedureName + "()}";
			logger.debug("ready to make call: " + procedureCall);

			CallableStatement st = conn.prepareCall(procedureCall);
			st.registerOutParameter(1, Types.INTEGER);
			ResultSet resultSet = st.executeQuery();
			while (resultSet.next()) {
				resultValue = resultSet.getInt(1);
			}
			logger.debug("sequence: " + procedureName + " returns " + resultValue);
		} catch (SQLException e) {
			logger.debug("error getting a next sequence number from database using procedure call:" + procedureName, e);
		}
		return resultValue;
	}

}
