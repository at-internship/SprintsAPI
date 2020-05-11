package com.sprints.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestingConflicts {
	final static Logger logger = LogManager.getLogger(TestingConflicts.class);
	
	public void TestingJonniAndGabriel() {
		logger.info("Metodo de Jonni");
		logger.info("Metodo de Gabriel");
	}

}
