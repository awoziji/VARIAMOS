package com.variamos.configurator.io;

import com.variamos.solver.model.SolverSolution;
import com.variamos.solver.model.ConfigurationOptionsDTO;
@Deprecated
public class ConfigurationDTO { 
	private ConfigurationOptionsDTO options;
	private SolverSolution values;
	
	public ConfigurationDTO() {
		options = new ConfigurationOptionsDTO();
		values = new SolverSolution();
	}
	
	public ConfigurationDTO(ConfigurationOptionsDTO options, SolverSolution values) {
		super();
		this.options = options;
		this.values = values;
	}


	public ConfigurationOptionsDTO getOptions() {
		return options;
	}
	public void setOptions(ConfigurationOptionsDTO options) {
		this.options = options;
	}
	public SolverSolution getValues() {
		return values;
	}
	public void setValues(SolverSolution values) {
		this.values = values;
	}
	
	
}
