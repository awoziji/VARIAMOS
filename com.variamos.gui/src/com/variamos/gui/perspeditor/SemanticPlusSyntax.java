package com.variamos.gui.perspeditor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.AbstractSemanticVertex;
import com.variamos.perspsupport.semanticsupport.SemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticContextGroup;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.semanticsupport.SemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.semanticsupport.SoftSemanticConcept;
import com.variamos.perspsupport.semanticsupport.SoftSemanticConceptSatisficing;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaView;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.perspsupport.syntaxsupport.SimulationConfigAttribute;
import com.variamos.perspsupport.syntaxsupport.SimulationStateAttribute;

/**
 * A class to create semantic and syntax instances for vertex and edges of the
 * models. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Mu�oz Fern�ndez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-20
 */
public class SemanticPlusSyntax {
	private Map<String, IntSemanticElement> semanticConcepts = new HashMap<String, IntSemanticElement>();
	private List<MetaView> metaViews = new ArrayList<MetaView>();
	private Map<String, MetaElement> syntaxElements = new HashMap<String, MetaElement>();

	public Map<String, IntSemanticElement> getSemanticConcepts() {
		return semanticConcepts;
	}

	public SemanticPlusSyntax() {

		// Definition of variability concept and relations

		System.out.println("Loading semantic model...");

		SemanticConcept semGeneralElement = new SemanticConcept();
		semanticConcepts.put("GE", semGeneralElement);

		// Design attributes

		semGeneralElement.putSemanticAttribute("Description",
				new SemanticAttribute("Description", "String", false,
						"Description", ""));
		semGeneralElement.addPropEditableAttribute("04#" + "Description");
		semGeneralElement.addPropVisibleAttribute("04#" + "Description");

		// Configuration attributes

		semGeneralElement.putSemanticAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		semGeneralElement.putSemanticAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		semGeneralElement.putSemanticAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		semGeneralElement.putSemanticAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", true,
						"Is Allowed", true));
		semGeneralElement.putSemanticAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain or
														// Enum
														// Level

		semGeneralElement.putSemanticAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		semGeneralElement.putSemanticAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		semGeneralElement.addPropEditableAttribute("01#" + "Active");
		// semGeneralElement.addDisPropEditableAttribute("02#" + "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		semGeneralElement.addPropEditableAttribute("10#" + "ForcedSatisfied"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");
		semGeneralElement.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		semGeneralElement.addPropVisibleAttribute("01#" + "Active");
		semGeneralElement.addPropVisibleAttribute("02#" + "Visibility");
		semGeneralElement.addPropVisibleAttribute("03#" + "Allowed");
		semGeneralElement.addPropVisibleAttribute("04#" + "Required");
		semGeneralElement.addPropVisibleAttribute("05#" + "RequiredLevel" + "#"
				+ "Required" + "#==#" + "true");
		semGeneralElement.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		semGeneralElement.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		semGeneralElement.putSemanticAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		semGeneralElement.putSemanticAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		semGeneralElement
				.putSemanticAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		semGeneralElement.putSemanticAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		semGeneralElement.putSemanticAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		semGeneralElement.putSemanticAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		semGeneralElement.putSemanticAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		semGeneralElement.putSemanticAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		semGeneralElement.putSemanticAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		semGeneralElement.putSemanticAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		semGeneralElement.putSemanticAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Not Preferred Selected", false));
		semGeneralElement.putSemanticAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		semGeneralElement.putSemanticAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		semGeneralElement.putSemanticAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		semGeneralElement.putSemanticAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		semGeneralElement.addPropVisibleAttribute("01#" + "SimRequired");
		semGeneralElement.addPropVisibleAttribute("03#" + "SimRequiredLevel");
		semGeneralElement.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		semGeneralElement.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");

		semGeneralElement.addPropVisibleAttribute("09#" + "Selected");
		semGeneralElement.addPropVisibleAttribute("11#" + "NotPrefSelected");
		semGeneralElement.addPropVisibleAttribute("13#" + "ValidationSelected");
		semGeneralElement.addPropVisibleAttribute("15#" + "SolverSelected");

		semGeneralElement.addPropVisibleAttribute("02#" + "Satisfied");
		semGeneralElement.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		semGeneralElement
				.addPropVisibleAttribute("06#" + "ValidationSatisfied");
		semGeneralElement.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		semGeneralElement.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		semGeneralElement.addPropVisibleAttribute("12#" + "SimAllowed");

		semGeneralElement.addPropVisibleAttribute("14#" + "Optional");

		// Definition of variability concept and relations
		SemanticConcept semHardConcept = new SemanticConcept(semGeneralElement,
				"semHardConcept");
		semanticConcepts.put("HC", semHardConcept);

		// Direct Relations of the semanticAssumption
		/*
		 * dirRelation = new DirectRelation(semVariabilityElement,
		 * DirectRelationType.preferred);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.required);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.conflict);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement,
		 * DirectRelationType.alternative);
		 * semVariabilityElement.addDirectRelation(dirRelation); dirRelation =
		 * new DirectRelation(semVariabilityElement, DirectRelationType.mutex);
		 * semVariabilityElement.addDirectRelation(dirRelation);
		 */

		// Feature concepts

		SemanticConcept semFeature = new SemanticConcept(semGeneralElement,
				"Feature");
		semanticConcepts.put("F", semFeature);

		SemanticContextGroup semFeatureGroup = new SemanticContextGroup(
				"ContextGroup");
		semanticConcepts.put("FCG", semFeatureGroup);

		// definition of other concepts

		SemanticConcept semAssumption = new SemanticConcept(semHardConcept,
				"Assumption");
		semanticConcepts.put("Asset", semAssumption);

		SemanticConcept semGoal = new SemanticConcept(semHardConcept, "Goal");
		semGoal.addPanelVisibleAttribute("01#" + "satisfactionType");
		semGoal.addPanelSpacersAttribute("<#" + "satisfactionType" + "#>\n");
		semanticConcepts.put("G", semGoal);

		SemanticConcept semOperationalization = new SemanticConcept(
				semHardConcept, "Operationalization");
		semanticConcepts.put("OPER", semOperationalization);

		SoftSemanticConcept semSoftgoal = new SoftSemanticConcept(
				semGeneralElement, "SoftGoal");
		semanticConcepts.put("SG", semSoftgoal);

		SemanticVariable semVariable = new SemanticVariable("Variable");
		semanticConcepts.put("VAR", semVariable);

		SemanticContextGroup semContextGroup = new SemanticContextGroup(
				"ContextGroup");
		semanticConcepts.put("CG", semContextGroup);

		SemanticConcept semAsset = new SemanticConcept(semGeneralElement,
				"Asset");
		semanticConcepts.put("Asset", semAsset);

		SoftSemanticConceptSatisficing semClaim = new SoftSemanticConceptSatisficing(
				semGeneralElement, "Claim", true, null);
		semanticConcepts.put("CL", semClaim);
		semClaim.putSemanticAttribute(
				"Operationalizations",
				new SemanticAttribute("Operationalizations", "MClass", false,
						"Operationalizations", InstConcept.class
								.getCanonicalName(), "OPER", "", ""));
		semClaim.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semClaim.putSemanticAttribute("CompExp", new SimulationConfigAttribute(
				"CompExp", "Boolean", false, "Boolean Comp. Expression", true));
		semClaim.putSemanticAttribute("ClaimSelected",
				new SimulationConfigAttribute("ClaimSelected", "Boolean",
						false, "Claim Selected", false));

		semClaim.addPanelVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPanelVisibleAttribute("03#" + "ConditionalExpression"); // TODO
																			// move
																			// to
																			// semantic
																			// attributes

		semClaim.addPropEditableAttribute("01#" + "Operationalizations");
		semClaim.addPropEditableAttribute("03#" + "ConditionalExpression");

		semClaim.addPropVisibleAttribute("01#" + "Operationalizations");
		semClaim.addPropVisibleAttribute("03#" + "ConditionalExpression");

		semClaim.addPropEditableAttribute("01#" + "CompExp");
		semClaim.addPropVisibleAttribute("01#" + "CompExp");

		semClaim.addPropVisibleAttribute("02#" + "ClaimSelected");

		semClaim.addPanelSpacersAttribute("#" + "Operationalizations" + "#\n#");

		SoftSemanticConceptSatisficing semSoftDependency = new SoftSemanticConceptSatisficing(
				semGeneralElement, "SoftDependency", false, null);
		semanticConcepts.put("SD", semSoftDependency);

		semSoftDependency.putSemanticAttribute("CompExp",
				new SimulationConfigAttribute("CompExp", "Boolean", false,
						"Boolean Comp. Expression", true));
		semSoftDependency.putSemanticAttribute("SDSelected",
				new SimulationConfigAttribute("SDSelected", "Boolean", false,
						"SD Selected", false));

		semSoftDependency.putSemanticAttribute("ConditionalExpression",
				new SemanticAttribute("ConditionalExpression", "String", false,
						"Conditional Expression", ""));
		semSoftDependency.addPanelVisibleAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropEditableAttribute("03#"
				+ "ConditionalExpression");
		semSoftDependency.addPropVisibleAttribute("03#"
				+ "ConditionalExpression");

		semSoftDependency.addPropEditableAttribute("01#" + "CompExp");
		semSoftDependency.addPropVisibleAttribute("01#" + "CompExp");

		semSoftDependency.addPropVisibleAttribute("02#" + "SDSelected");

		// Elements Lists
		List<AbstractSemanticVertex> semAssumptionElements = new ArrayList<AbstractSemanticVertex>();
		semAssumptionElements.add(semAssumption);

		List<AbstractSemanticVertex> SemGoalOperElements = new ArrayList<AbstractSemanticVertex>();
		SemGoalOperElements.add(semGoal);
		SemGoalOperElements.add(semOperationalization);

		List<AbstractSemanticVertex> semGoalElements = new ArrayList<AbstractSemanticVertex>();
		semGoalElements.add(semGoal);

		List<AbstractSemanticVertex> semOperationalizationElements = new ArrayList<AbstractSemanticVertex>();
		semOperationalizationElements.add(semOperationalization);

		List<AbstractSemanticVertex> semSoftgoalElements = new ArrayList<AbstractSemanticVertex>();
		semSoftgoalElements.add(semSoftgoal);

		List<AbstractSemanticVertex> semClaimsElements = new ArrayList<AbstractSemanticVertex>();
		semClaimsElements.add(semClaim);

		List<AbstractSemanticVertex> semSDElements = new ArrayList<AbstractSemanticVertex>();
		semSDElements.add(semSoftDependency);

		// Relations

		SemanticOverTwoRelation semanticHardHardGroupRelation = new SemanticOverTwoRelation(
				"HardHardOverTwoRel", false, null);
		semanticConcepts.put("HardHardOverTwoRel",
				semanticHardHardGroupRelation);

		List<AbstractSemanticVertex> semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semHardConcept);

		List<IntSemanticRelationType> hardSemPairwiseRelList = new ArrayList<IntSemanticRelationType>();
		hardSemPairwiseRelList.add(new SemanticRelationType("MeansEnds",
				"Means Ends", "means-ends", true, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("Conflict",
				"Conflict", "conflict", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("Alternative",
				"Alternative", "altern.", false, true, true, 1, -1, 1, 1));
		hardSemPairwiseRelList.add(new SemanticRelationType("Excludes",
				"Excludes", "excludes", false, true, true, 1, -1, 1, 1));

		SemanticPairwiseRelation directHardHardSemanticEdge = new SemanticPairwiseRelation(
				"HardHardDirectEdge", false, hardSemPairwiseRelList);
		semanticConcepts.put("HardHardDirectEdge", directHardHardSemanticEdge);

		// Feature to Feature

		SemanticOverTwoRelation semanticFeatureFeatureGroupRelation = new SemanticOverTwoRelation(
				"FeatureFeatureGroupRel", false, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semFeature);

		SemanticPairwiseRelation directFeatureFeatureSemanticEdge = new SemanticPairwiseRelation(
				"FeatureFeatureDirectEdge", false, hardSemPairwiseRelList);
		semanticConcepts.put("FeauteFeateuGroupRel",
				semanticFeatureFeatureGroupRelation);
		semanticConcepts.put("FeauteFeatureDirectEdge",
				directFeatureFeatureSemanticEdge);

		// Goal to Goal

		SemanticOverTwoRelation semanticGoalGoalGroupRelation = new SemanticOverTwoRelation(
				"GoalGoalOverTwoRel", false, null);
		semanticVertexs.add(semGoal);

		SemanticPairwiseRelation directGoalGoalSemanticEdge = new SemanticPairwiseRelation(
				"GoalGoalDirectEdge", false, hardSemPairwiseRelList);
		semanticConcepts.put("GoalGoalOverTwoRel",
				semanticGoalGoalGroupRelation);
		semanticConcepts.put("GoalGoalDirectEdge", directGoalGoalSemanticEdge);

		// Oper to Goal and Oper
		SemanticOverTwoRelation semanticOperGoalGroupRelation = new SemanticOverTwoRelation(
				"OperGoalOverTwoRel", false, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);
		semanticVertexs.add(semGoal);

		SemanticPairwiseRelation directOperGoalSemanticEdge = new SemanticPairwiseRelation(
				"OperGoalDirectEdge", false, hardSemPairwiseRelList);

		semanticConcepts.put("OperGoalOverTwoRel",
				semanticOperGoalGroupRelation);
		semanticConcepts.put("OperGoalDirectEdge", directOperGoalSemanticEdge);

		// Oper to Oper
		SemanticOverTwoRelation semanticOperOperGroupRelation = new SemanticOverTwoRelation(
				"OperOperOverTwoRel", false, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		SemanticPairwiseRelation directOperOperSemanticEdge = new SemanticPairwiseRelation(
				"OperOperDirectEdge", false, hardSemPairwiseRelList);
		semanticConcepts.put("OperOperOverTwoRel",
				semanticOperOperGroupRelation);
		semanticConcepts.put("OperOperDirectEdge", directOperOperSemanticEdge);

		// SG to SG
		SemanticOverTwoRelation semanticSGSGGroupRelation = new SemanticOverTwoRelation(
				"SGSGGroupRel", false, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		SemanticPairwiseRelation directSGSGSemEdge = new SemanticPairwiseRelation(
				"SGSGDirectEdge", true, hardSemPairwiseRelList);
		/*
		 * directSGSGSemEdge.putSemanticAttribute(AbstractSemanticEdge.VAR_LEVEL,
		 * new SemanticAttribute(AbstractSemanticEdge.VAR_LEVEL, "Enumeration",
		 * false, AbstractSemanticEdge.VAR_LEVELNAME,
		 * AbstractSemanticEdge.VAR_LEVELCLASS, "plus plus", ""));
		 * directSGSGSemEdge.addPropEditableAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directSGSGSemEdge.addPropVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 * directSGSGSemEdge.addPanelVisibleAttribute("08#" +
		 * AbstractSemanticEdge.VAR_LEVEL);
		 */
		semanticConcepts.put("SGSGGroupRel", semanticSGSGGroupRelation);
		semanticConcepts.put("SGSGDirectEdge", directSGSGSemEdge);

		// CV to CG
		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semContextGroup);

		SemanticPairwiseRelation directCVCGSemanticEdge = new SemanticPairwiseRelation(
				"CVCGDirectRel", false, hardSemPairwiseRelList);
		semanticConcepts.put("CVCGDirectRel", directCVCGSemanticEdge);

		// Oper to Claim
		SemanticOverTwoRelation semanticOperClaimGroupRelation = new SemanticOverTwoRelation(
				"OperClaimGroupRel", true, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semClaim);

		SemanticPairwiseRelation directOperClaimSemanticEdge = new SemanticPairwiseRelation(
				"OperClaimDirectEdge", true, hardSemPairwiseRelList);

		semanticConcepts.put("OperClaimGroupRel",
				semanticOperClaimGroupRelation);
		semanticConcepts
				.put("OperClaimDirectEdge", directOperClaimSemanticEdge);

		// Claim to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		SemanticPairwiseRelation directClaimSGSemanticEdge = new SemanticPairwiseRelation(
				"ClaimSGDirectEdge", true, hardSemPairwiseRelList);

		semanticConcepts.put("ClaimSGDirectEdge", directClaimSGSemanticEdge);

		// SD to SG

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semSoftgoal);

		SemanticPairwiseRelation directSDSGSemanticEdge = new SemanticPairwiseRelation(
				"SDSGDirectEdge", true, hardSemPairwiseRelList);

		semanticConcepts.put("SDSGDirectEdge", directSDSGSemanticEdge);

		// Asset to Oper
		SemanticOverTwoRelation semanticAssetOperGroupRelation = new SemanticOverTwoRelation(
				"AssetOperGroupRel", false, null);

		semanticVertexs = new ArrayList<AbstractSemanticVertex>();
		semanticVertexs.add(semOperationalization);

		SemanticPairwiseRelation directAssetOperSemanticEdge = new SemanticPairwiseRelation(
				"AssetOperDirectEdge", false, hardSemPairwiseRelList);
		semanticConcepts.put("AssetOperGroupRel",
				semanticAssetOperGroupRelation);
		semanticConcepts
				.put("AssetOperDirectEdge", directAssetOperSemanticEdge);

		// TODO: structural and functional dependency relations

		System.out.println("Semantic model loaded.");
		// *************************---------------****************************
		// Our MetaModel objects definition

		System.out.println("Loading syntax meta model...");

		MetaView syntaxMetaView = null;

		// *************************---------------****************************
		// Goals and avariability model

		syntaxMetaView = new MetaView("GoalsAndVaribilityModel",
				"Goals and Variability Model", "Goals and Variability Palette",
				1);

		MetaConcept syntaxFeature = new MetaConcept("F", true, "Feature",
				"plnode", "Defines a feature", 100, 40,
				"/com/variamos/gui/pl/editor/images/plnode.png", true,
				Color.BLUE.toString(), 3, true, semFeature);
		syntaxFeature.addModelingAttribute("name", "String", false, "Name", "");

		syntaxElements.put("F", syntaxFeature);

		syntaxFeature.addPanelVisibleAttribute("03#" + "name");

		syntaxFeature.addPropEditableAttribute("03#" + "name");

		syntaxFeature.addPropVisibleAttribute("03#" + "name");

		// Feature direct relations

		List<IntSemanticPairwiseRelation> directFeatureSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directFeatureSemanticEdges.add(directFeatureFeatureSemanticEdge);

		MetaPairwiseRelation metaFeatureEdge = new MetaPairwiseRelation(
				"Feature Relation", false, "Feature Relation", "plnode",
				"Direct relation between two"
						+ " feature concepts. Defines different types of"
						+ " relations", 50, 50,
				"/com/variamos/gui/pl/editor/images/plnode.png", 1,
				directFeatureFeatureSemanticEdge);
		syntaxElements.put("Feature Relation", metaFeatureEdge);

		// Group Feature Relations

		MetaOverTwoRelation syntaxFeatureGroupDep = new MetaOverTwoRelation(
				"FeatGroupDep", true, "FeatGroupDep", "plgroup",
				"Group relation between"
						+ " Feature concepts. Defines different types of"
						+ " cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticFeatureFeatureGroupRelation);

		syntaxElements.put("FeatGroupDep", syntaxFeatureGroupDep);

		syntaxFeatureGroupDep.addModelingAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		syntaxFeatureGroupDep.addModelingAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxFeatureGroupDep.addModelingAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxFeatureGroupDep.addModelingAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxFeatureGroupDep.addModelingAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain or
														// Enum
														// Level

		syntaxFeatureGroupDep.addModelingAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		syntaxFeatureGroupDep.addModelingAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		syntaxFeatureGroupDep.addPropEditableAttribute("01#" + "Active");
		// syntaxFeatureGroupDep.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		syntaxFeatureGroupDep.addPropEditableAttribute("10#"
				+ "ForcedSatisfied" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");
		syntaxFeatureGroupDep.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		syntaxFeatureGroupDep.addPropVisibleAttribute("01#" + "Active");
		syntaxFeatureGroupDep.addPropVisibleAttribute("02#" + "Visibility");
		syntaxFeatureGroupDep.addPropVisibleAttribute("03#" + "Allowed");
		syntaxFeatureGroupDep.addPropVisibleAttribute("04#" + "Required");
		syntaxFeatureGroupDep.addPropVisibleAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true");
		syntaxFeatureGroupDep
				.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		syntaxFeatureGroupDep.addModelingAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		syntaxFeatureGroupDep
				.addModelingAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"Required", false));

		syntaxFeatureGroupDep.addModelingAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"Satisfied", false));
		syntaxFeatureGroupDep.addModelingAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		syntaxFeatureGroupDep.addModelingAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		syntaxFeatureGroupDep.addModelingAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"Selected", false));
		syntaxFeatureGroupDep.addModelingAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Select by Preferred", false));
		syntaxFeatureGroupDep.addModelingAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		syntaxFeatureGroupDep.addModelingAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		syntaxFeatureGroupDep.addModelingAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"Is Optional", false));

		syntaxFeatureGroupDep.addModelingAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		syntaxFeatureGroupDep.addPropVisibleAttribute("01#" + "SimRequired");
		syntaxFeatureGroupDep.addPropVisibleAttribute("03#"
				+ "SimRequiredLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("09#" + "Selected");
		syntaxFeatureGroupDep
				.addPropVisibleAttribute("11#" + "NotPrefSelected");
		syntaxFeatureGroupDep.addPropVisibleAttribute("13#"
				+ "ValidationSelected");
		syntaxFeatureGroupDep.addPropVisibleAttribute("15#" + "SolverSelected");

		syntaxFeatureGroupDep.addPropVisibleAttribute("02#" + "Satisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		syntaxFeatureGroupDep.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		syntaxFeatureGroupDep.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		syntaxFeatureGroupDep.addPropVisibleAttribute("12#" + "SimAllowed");

		syntaxFeatureGroupDep.addPropVisibleAttribute("14#" + "Optional");

		MetaConcept syntaxVariabilityArtifact = new MetaConcept("VA", false,
				"VariabilityArtifact", null, "", 0, 0, null, true, null, 3,
				true, semHardConcept);
		syntaxVariabilityArtifact.addModelingAttribute("name", "String", false,
				"Name", "");

		syntaxVariabilityArtifact.addPanelVisibleAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropEditableAttribute("03#" + "name");

		syntaxVariabilityArtifact.addPropVisibleAttribute("03#" + "name");

		syntaxElements.put("VA", syntaxVariabilityArtifact);

		metaViews.add(syntaxMetaView);

		MetaConcept syntaxTopGoal = new MetaConcept("TopGoal", true,
				"Top Goal", "refasgoal", "Defines a top goal of the system"
						+ " from the stakeholder perspective that can be"
						+ " satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 3, true, semGoal);
		syntaxTopGoal.setParent(syntaxVariabilityArtifact);

		syntaxElements.put("TopGoal", syntaxTopGoal);

		MetaConcept syntaxGeneralGoal = new MetaConcept("GeneralGoal", true,
				"General Goal", "refasgoal", "Defines a general goal of the"
						+ " system from the stakeholder perspective that can"
						+ " be satisfied with a clear cut condition", 100, 40,
				"/com/variamos/gui/perspeditor/images/goal.png", true,
				Color.BLUE.toString(), 2, true, semGoal);
		syntaxGeneralGoal.setParent(syntaxVariabilityArtifact);

		syntaxElements.put("GeneralGoal", syntaxGeneralGoal);

		MetaConcept sOperationalization = new MetaConcept("OPER", true,
				"Operationalization", "refasoper",
				"An operationalization allows"
						+ " the partial or complete satisfaction of a goal or"
						+ " another operationalization. If"
						+ " the operationalizations defined is satisfied,"
						+ " according to the defined relation, the goal"
						+ " associated will be also satisfied", 100, 40,
				"/com/variamos/gui/perspeditor/images/operational.png", true,
				Color.BLUE.toString(), 2, true, semOperationalization);
		sOperationalization.setParent(syntaxVariabilityArtifact);

		syntaxElements.put("OPER", sOperationalization);

		MetaConcept syntaxAssumption = new MetaConcept("Assumption", true,
				"semanticAssumption", "refassassump", "An assumption is a"
						+ " condition that should me truth for the goal or"
						+ " operationalization to be satisfied", 100, 40,
				"/com/variamos/gui/perspeditor/images/assump.png", true,
				Color.WHITE.toString(), 1, true, semAssumption);
		syntaxAssumption.setParent(syntaxVariabilityArtifact);

		syntaxElements.put("Assumption", syntaxAssumption);

		// Direct Hard Relations

		MetaPairwiseRelation metaHardEdge = new MetaPairwiseRelation(
				"HardRelation", true, "HardRelation", "ploptional",
				"Direct relation between two"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directHardHardSemanticEdge);
		syntaxElements.put("HardRelation", metaHardEdge);

		// Group Hard Relations

		MetaOverTwoRelation syntaxGroupDependency = new MetaOverTwoRelation(
				"HardOverTwoRel", true, "HardOverTwoRel", "plgroup",
				"Group relation between"
						+ " hard concepts. Defines different types of"
						+ " relations and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticHardHardGroupRelation);

		syntaxElements.put("HardOverTwoRel", syntaxGroupDependency);

		syntaxGroupDependency.addModelingAttribute("Active",
				new SimulationConfigAttribute("Active", "Boolean", true,
						"Is Active", true));
		syntaxGroupDependency.addModelingAttribute("Visibility",
				new SimulationConfigAttribute("Visibility", "Boolean", false,
						"Is Visible", true));
		syntaxGroupDependency.addModelingAttribute("Required",
				new SimulationConfigAttribute("Required", "Boolean", true,
						"Is Required", false));
		syntaxGroupDependency.addModelingAttribute("Allowed",
				new SimulationConfigAttribute("Allowed", "Boolean", false,
						"Is Allowed", true));
		syntaxGroupDependency.addModelingAttribute("RequiredLevel",
				new SimulationConfigAttribute("RequiredLevel", "Integer",
						false, "Required Level", 0)); // TODO define domain or
														// Enum
														// Level
		syntaxGroupDependency.addModelingAttribute("ForcedSatisfied",
				new SimulationConfigAttribute("ForcedSatisfied", "Boolean",
						false, "Force Satisfaction", false));
		syntaxGroupDependency.addModelingAttribute("ForcedSelected",
				new SimulationConfigAttribute("ForcedSelected", "Boolean",
						false, "Force Selection", false));

		syntaxGroupDependency.addPropEditableAttribute("01#" + "Active");
		// syntaxGroupDependency.addDisPropEditableAttribute("02#" +
		// "Visibility"
		// + "#" + "Active" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addPropEditableAttribute("03#" + "Allowed" + "#"
				+ "Active" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addPropEditableAttribute("04#" + "Required" + "#"
				+ "Allowed" + "#==#" + "true" + "#" + "false");
		syntaxGroupDependency.addPropEditableAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		syntaxGroupDependency.addPropEditableAttribute("10#"
				+ "ForcedSatisfied" + "#" + "Allowed" + "#==#" + "true" + "#"
				+ "false");
		syntaxGroupDependency.addPropEditableAttribute("15#" + "ForcedSelected"
				+ "#" + "Allowed" + "#==#" + "true" + "#" + "false");

		syntaxGroupDependency.addPropVisibleAttribute("01#" + "Active");
		syntaxGroupDependency.addPropVisibleAttribute("02#" + "Visibility");
		syntaxGroupDependency.addPropVisibleAttribute("03#" + "Allowed");
		syntaxGroupDependency.addPropVisibleAttribute("04#" + "Required");
		syntaxGroupDependency.addPropVisibleAttribute("05#" + "RequiredLevel"
				+ "#" + "Required" + "#==#" + "true");
		syntaxGroupDependency
				.addPropVisibleAttribute("10#" + "ForcedSatisfied");
		syntaxGroupDependency.addPropVisibleAttribute("15#" + "ForcedSelected");

		// Simulation attributes

		syntaxGroupDependency.addModelingAttribute("InitialRequiredLevel",
				new SimulationStateAttribute("InitialRequiredLevel", "Integer",
						false, "Initial Required Level", false));
		syntaxGroupDependency.addModelingAttribute("SimRequiredLevel",
				new SimulationStateAttribute("SimRequiredLevel", "Integer",
						false, "Required Level", false));
		syntaxGroupDependency
				.addModelingAttribute("ValidationRequiredLevel",
						new SimulationStateAttribute("ValidationRequiredLevel",
								"Integer", false,
								"Required Level by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SimRequired",
				new SimulationStateAttribute("SimRequired", "Boolean", false,
						"***Required***", false));

		syntaxGroupDependency.addModelingAttribute("Satisfied",
				new SimulationStateAttribute("Satisfied", "Boolean", false,
						"***Satisfied***", false));
		syntaxGroupDependency.addModelingAttribute("AlternativeSatisfied",
				new SimulationStateAttribute("AlternativeSatisfied", "Boolean",
						false, "Satisfied by Alternatives", false));
		syntaxGroupDependency.addModelingAttribute("ValidationSatisfied",
				new SimulationStateAttribute("ValidationSatisfied", "Boolean",
						false, "Satisfied by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SatisfiedLevel",
				new SimulationStateAttribute("SatisfiedLevel", "Integer",
						false, "Satisficing Level", false));
		syntaxGroupDependency.addModelingAttribute("SatisfactionConflict",
				new SimulationStateAttribute("SatisfactionConflict", "Boolean",
						false, "Satisfaction Conflict", false));

		syntaxGroupDependency.addModelingAttribute("Selected",
				new SimulationStateAttribute("Selected", "Boolean", false,
						"***Selected***", false));
		syntaxGroupDependency.addModelingAttribute("NotPrefSelected",
				new SimulationStateAttribute("NotPrefSelected", "Boolean",
						false, "Not Preferred Selected", false));
		syntaxGroupDependency.addModelingAttribute("ValidationSelected",
				new SimulationStateAttribute("ValidationSelected", "Boolean",
						false, "Selected by Validation", false));
		syntaxGroupDependency.addModelingAttribute("SolverSelected",
				new SimulationStateAttribute("SolverSelected", "Boolean",
						false, "Selected by Solver", false));

		syntaxGroupDependency.addModelingAttribute("Optional",
				new SimulationStateAttribute("Optional", "Boolean", false,
						"*Is Optional*", false));

		syntaxGroupDependency.addModelingAttribute("SimAllowed",
				new SimulationStateAttribute("SimAllowed", "Boolean", false,
						"Is Allowed", true));

		syntaxGroupDependency.addPropVisibleAttribute("01#" + "SimRequired");
		syntaxGroupDependency.addPropVisibleAttribute("03#"
				+ "SimRequiredLevel");
		syntaxGroupDependency.addPropVisibleAttribute("05#"
				+ "InitialRequiredLevel");
		syntaxGroupDependency.addPropVisibleAttribute("07#"
				+ "ValidationRequiredLevel");
		syntaxGroupDependency.addPropVisibleAttribute("09#" + "Selected");
		syntaxGroupDependency
				.addPropVisibleAttribute("11#" + "NotPrefSelected");
		syntaxGroupDependency.addPropVisibleAttribute("13#"
				+ "ValidationSelected");
		syntaxGroupDependency.addPropVisibleAttribute("15#" + "SolverSelected");

		syntaxGroupDependency.addPropVisibleAttribute("02#" + "Satisfied");
		syntaxGroupDependency.addPropVisibleAttribute("04#"
				+ "AlternativeSatisfied");
		syntaxGroupDependency.addPropVisibleAttribute("06#"
				+ "ValidationSatisfied");
		syntaxGroupDependency.addPropVisibleAttribute("08#" + "SatisfiedLevel");
		syntaxGroupDependency.addPropVisibleAttribute("10#"
				+ "SatisfactionConflict");

		syntaxGroupDependency.addPropVisibleAttribute("12#" + "SimAllowed");

		syntaxGroupDependency.addPropVisibleAttribute("14#" + "Optional");

		// *************************---------------****************************
		// Softgoals model

		syntaxMetaView = new MetaView("SoftGoals", "Soft Goals Model",
				"Soft Goals Palette", 2);
		metaViews.add(syntaxMetaView);

		MetaConcept syntaxAbsSoftGoal = new MetaConcept("Softgoal", false,
				"Softgoal", "", null, 0, 0, null, true, null, 3, true,
				semSoftgoal);

		syntaxAbsSoftGoal.addModelingAttribute("name", "String", false, "Name",
				"");
		syntaxAbsSoftGoal.addPanelVisibleAttribute("03#" + "name");

		syntaxAbsSoftGoal.addPropEditableAttribute("03#" + "name");
		syntaxAbsSoftGoal.addPropVisibleAttribute("03#" + "name");

		syntaxElements.put("Softgoal", syntaxAbsSoftGoal);

		MetaConcept syntaxTopSoftGoal = new MetaConcept(
				"TopSoftgoal",
				true,
				"Top Softgoal",
				"refassoftgoal",
				"Defines a top goal of the"
						+ " system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 40,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 3, true, semSoftgoal);

		syntaxTopSoftGoal.setParent(syntaxAbsSoftGoal);

		syntaxElements.put("TopSoftgoal", syntaxTopSoftGoal);

		MetaConcept syntaxGeneralSoftGoal = new MetaConcept(
				"GeneralSSoftgoal",
				true,
				"General Softgoal",
				"refassoftgoal",
				"Defines a general"
						+ " softgoal of the system from the stakeholder"
						+ " perspective that can at most be satisficed without"
						+ " a clear cut verification. Given the satisficing problem,"
						+ " it includes an scale of levels of satisfaction/denial."
						+ " The SG satisficing level can be measured globally or"
						+ " locally, for the system or for each user, depending"
						+ " on the SG", 100, 40,
				"/com/variamos/gui/perspeditor/images/softgoal.png", true,
				Color.WHITE.toString(), 1, true, semSoftgoal);

		syntaxGeneralSoftGoal.setParent(syntaxAbsSoftGoal);
		syntaxElements.put("GeneralSSoftgoal", syntaxGeneralSoftGoal);

		// Direct Soft relation

		MetaPairwiseRelation metaSoftEdge = new MetaPairwiseRelation(
				"Soft Relation", true, "Soft Relation", "ploptional",
				"Direct relation between two soft concepts. Defines"
						+ " different types of relations and cartinalities",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSGSGSemEdge);
		syntaxElements.put("Soft Relation", metaSoftEdge);

		// Group soft relation

		syntaxGroupDependency = new MetaOverTwoRelation("SoftgoalGroupDep",
				true, "SoftgoalGroupDep", "plgroup",
				"Direct relation between soft"
						+ " concepts. Defines different types of relations"
						+ " and cartinalities", 20, 20,
				"/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticSGSGGroupRelation);

		syntaxElements.put("SoftgoalGroupDep", syntaxGroupDependency);

		// *************************---------------****************************
		// Context model

		syntaxMetaView = new MetaView("Context", "Context Model",
				"Context Palette", 3);
		metaViews.add(syntaxMetaView);
		// syntaxMetaView.addConcept(syntaxVariable);

		MetaConcept syntaxContextGroup = new MetaConcept("CG", true,
				"Context Group", "refascontextgrp", " A context group"
						+ " is defined to associate variables with common"
						+ " characteristics. The type defines if variables"
						+ " are sensed or profiled, in the first case they"
						+ " are modifie by the system or environment; in the"
						+ " second case they are defined by the administrator"
						+ " or the user. The scope can be local or global,"
						+ " the first means the value is independently for"
						+ " each user while global variables are shared"
						+ " between all the users.", 150, 40,
				"/com/variamos/gui/perspeditor/images/contextgrp.png", true,
				Color.BLUE.toString(), 1, true, semContextGroup);

		@SuppressWarnings("unused")
		MetaView syntaxMetaChildView = new MetaView("Context",
				"Context with Enumerations", "Context Palette", 0);
		syntaxElements.put("CG", syntaxContextGroup);

		MetaConcept syntaxAbsVariable = new MetaConcept("Variable", false,
				"Variable", "", null, 0, 0, null, true, null, 1, true,
				semVariable);

		syntaxElements.put("Variable", syntaxAbsVariable);

		MetaConcept syntaxGlobalVariable = new MetaConcept(
				"GlobalVariable",
				true,
				"Global Variable",
				"refasglobcnxt",
				"A global variable"
						+ " is an abstraction of a variable or component of the"
						+ " system or the environment that are relevant the system."
						+ " The relevance applies to the system in general."
						+ " The variable values should be"
						+ " simplified as much as possible considering the modeling"
						+ " requirements", 150, 40,
				"/com/variamos/gui/perspeditor/images/globCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxGlobalVariable.setParent(syntaxAbsVariable);

		syntaxElements.put("GlobalVariable", syntaxGlobalVariable);

		MetaConcept syntaxLocalVariable = new MetaConcept(
				"ContextVariable",
				true,
				"Context Variable",
				"refaslocalcnxt",
				" A local variable"
						+ " represents an instance of a component or a variable"
						+ " with local scope. This variables may have different"
						+ " values for each user of the system. Local variables"
						+ " are used mainly for SG with local satisfaction"
						+ " evaluation", 100, 40,
				"/com/variamos/gui/perspeditor/images/localCnxtVar.png", true,
				Color.BLUE.toString(), 1, true, semVariable);

		syntaxLocalVariable.setParent(syntaxAbsVariable);

		syntaxElements.put("ContextVariable", syntaxLocalVariable);

		MetaEnumeration metaEnumeration = new MetaEnumeration("ME", true,
				"MetaEnumeration", "refasenumeration", "Allows the"
						+ " creation of user defined enumeration for"
						+ " variables", 100, 150,
				"/com/variamos/gui/perspeditor/images/assump.png", true, "", 1,
				true);
		syntaxElements.put("ME", metaEnumeration);

		syntaxMetaChildView = new MetaView("ContandModelEnum",
				"Context without Enumerations", "Context Palette", 1);
		// syntaxMetaChildView.addConcept(metaEnumeration);

		// Direct variable relations

		MetaPairwiseRelation metaVariableEdge = new MetaPairwiseRelation(
				"Variable To Context Relation", true,
				"Variable To Context Relation", "ploptional",
				"Associates a Variable" + " with the Context Group", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directCVCGSemanticEdge);
		syntaxElements.put("Variable To Context Relation", metaVariableEdge);

		syntaxElements.put("Context To Context Relation", metaVariableEdge);

		// *************************---------------****************************
		// SG Satisficing Model

		syntaxMetaView = new MetaView("SoftGoalsSatisficing",
				"SG Satisficing Model", "Soft Goals Satisficing Palette", 4);
		metaViews.add(syntaxMetaView);
		MetaConcept syntaxClaim = new MetaConcept("CL", true, "Claim",
				"refasclaim", "A claim includes a group of"
						+ " operationalizations and a logical condition"
						+ " to evaluate the claim satisfaction."
						+ " The claim is activated only when all the"
						+ " operationalizations are selected and the"
						+ " conditional expression is true. The claim"
						+ " includes a relation with a softgoal (SG)", 100, 40,
				"/com/variamos/gui/perspeditor/images/claim.png", true,
				Color.BLUE.toString(), 1, true, semClaim);
		syntaxElements.put("CL", syntaxClaim);

		MetaConcept syntaxSoftDependency = new MetaConcept(
				"SD",
				true,
				"Soft Dependency",
				"refassoftdep",
				"A Soft Dependency"
						+ " (SD express a logical condition useful to express"
						+ " an expected level of"
						+ " satisfaction of a softgoal. The soft dependency is"
						+ " activated only when the conditional expression is true."
						+ " The SD includes a relation with a softgoal (SG)",
				100, 40, "/com/variamos/gui/perspeditor/images/softdep.png",
				true, Color.BLUE.toString(), 1, true, semSoftDependency);

		syntaxElements.put("SD", syntaxSoftDependency);

		syntaxGroupDependency = new MetaOverTwoRelation(
				"OperClaimGD",
				true,
				"OperClaimGD",
				"plgroup",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " expected on the softgoal in case the Claim is satisfied",
				20, 20, "/com/variamos/gui/pl/editor/images/plgroup.png",
				false, "white", 1, false, semanticOperClaimGroupRelation);
		syntaxElements.put("OperClaimGD", syntaxGroupDependency);

		List<IntSemanticPairwiseRelation> directSDSGSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directSDSGSemanticEdges.add(directSDSGSemanticEdge);

		MetaPairwiseRelation metaSDSGEdge = new MetaPairwiseRelation(
				"SDSGRelation",
				true,
				"SDSGRelation",
				"ploptional",
				"Express the relation between"
						+ " the SD and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directSDSGSemanticEdge);

		syntaxElements.put("SDSGRelation", metaSDSGEdge);

		List<IntSemanticPairwiseRelation> directClaimSGSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directClaimSGSemanticEdges.add(directClaimSGSemanticEdge);

		MetaPairwiseRelation metaClaimSGEdge = new MetaPairwiseRelation(
				"Claim-Softgoal Relation",
				true,
				"Claim-Softgoal Relation",
				"ploptional",
				"Express the relation between"
						+ " the Claim and the SG. Represent the level of satisficing"
						+ " required on the softgoal in case the SD is satisfied",
				50, 50, "/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directClaimSGSemanticEdge);

		syntaxElements.put("Claim-Softgoal Relation", metaClaimSGEdge);

		// *************************---------------****************************
		// Assets model

		syntaxMetaView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 5);
		metaViews.add(syntaxMetaView);
		MetaConcept syntaxAsset = new MetaConcept("Asset", true, "Asset",
				"refasasset", "Represents a asset of the system. The most"
						+ " important assets to represent are those than"
						+ " can implement operationalizations", 100, 40,
				"/com/variamos/gui/perspeditor/images/component.png", true,
				Color.WHITE.toString(), 1, true, semAsset);
		syntaxAsset.addModelingAttribute("name", "String", false, "Name", ""); // TODO
																				// move
																				// to
		// semantic
		// attributes
		syntaxMetaChildView = new MetaView("Assets", "Assets General Model",
				"Assets Palette", 0);
		syntaxAsset.addPanelVisibleAttribute("03#" + "name");
		syntaxAsset.addPropEditableAttribute("03#" + "name");
		syntaxAsset.addPropVisibleAttribute("03#" + "name");

		syntaxElements.put("Asset", syntaxAsset);

		syntaxMetaChildView = new MetaView("FunctionalAssets",
				"Functionl Assets Relations", "Assets Palette", 1);

		syntaxMetaChildView = new MetaView("StructuralAssets",
				"Structural Assets Relations", "Assets Palette", 2);

		syntaxGroupDependency = new MetaOverTwoRelation("AssetOperGroupDep",
				true, "AssetOperGroupDep", "plgroup",
				"Represents the implementation "
						+ "of an operationalization by a group of assets", 20,
				20, "/com/variamos/gui/pl/editor/images/plgroup.png", false,
				"white", 1, false, semanticAssetOperGroupRelation);

		syntaxElements.put("Asset-Oper GroupDep", syntaxGroupDependency);

		List<IntSemanticPairwiseRelation> directAssetOperSemanticEdges = new ArrayList<IntSemanticPairwiseRelation>();
		directAssetOperSemanticEdges.add(directAssetOperSemanticEdge);

		MetaPairwiseRelation metaOperEdge = new MetaPairwiseRelation(
				"Asset To Oper Relation", true, "Asset To Oper Relation",
				"ploptional", "Represents the "
						+ "implementation of an operationzalization by an"
						+ " asset", 50, 50,
				"/com/variamos/gui/pl/editor/images/ploptional.png", 1,
				directAssetOperSemanticEdge);

		// syntaxMetaView.addConcept(metaOperEdge);
		syntaxElements.put("Asset To Oper Relation", metaOperEdge);

		System.out.println("Syntax meta model loaded.");

		System.out.println("Loading gui elements...");

	}
}