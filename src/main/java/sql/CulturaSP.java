package sql;

import sql.variables.tables.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.executeSQL;

public class CulturaSP {

	//---------------------------------- SPs ----------------------------------
	//<editor-fold desc="SP">
	public static void createAllSP(Connection connection) throws SQLException {
	    TableZona.createSPInserir_Zona                           (connection);
	    TableZona.createSPAlterar_Zona                           (connection);
	    TableZona.createSPEliminar_Zona                          (connection);

	    TableMedicao.createSPInserir_Medicao                     (connection);
	    TableMedicao.createSPAlterar_Medicao                     (connection);
	    TableMedicao.createSPEliminar_Medicao                    (connection);

	    TableSensor.createSPInserir_Sensor                       (connection);
	    TableSensor.createSPAlterar_Sensor                       (connection);
	    TableSensor.createSPEliminar_Sensor                      (connection);

	    TableUtilizador.createSPInserir_User_Investigador        (connection);
	    TableUtilizador.createSPInserir_User_Tecnico             (connection);
	    TableUtilizador.createSPInserir_User_Admin       		  (connection);
	    TableUtilizador.createSPInserir_User_MqttReader          (connection);
	    TableUtilizador.createSPAlterar_User                     (connection);
	    TableUtilizador.createSPEliminar_User                    (connection);

	    TableCultura.createSPInserir_Cultura                     (connection);
	    TableCultura.createSPAlterar_Cultura                     (connection);
	    TableCultura.createSPAlterar_Investigador_Cultura 		 (connection);
	    TableCultura.createSPEliminar_Investigador_Cultura 		 (connection);
	    TableCultura.createSPEliminar_Cultura                    (connection);
	    TableCultura.createSPSelect_Cultura                      (connection);

	  //  TableParametroCultura.createSPInserir_ParametroCultura   (connection);
	    TableParametroCultura.createSPAlterar_ParametroCultura   (connection);
	  //  TableParametroCultura.createSPEliminar_ParametroCultura  (connection);

	    TableAlerta.createSPInserir_Alerta                       (connection);
	    TableAlerta.createSPInserir_Alerta_Predicted             (connection);
	    TableAlerta.createSPAlterar_Alerta                       (connection);
	    TableAlerta.createSPEliminar_Alerta                      (connection);
		TableAlerta.createSPSelect_Alerta                        (connection);
	}

	public static String generateARGUMENTS(String[] tableCollums, String[] tableDatatypes) {
	    String args ="";
	    if(tableCollums.length == tableDatatypes.length){
	        for(int i =0 ; i<tableCollums.length && i<tableDatatypes.length ;i++){
	            args += "IN sp_" + tableCollums[i] + " " + tableDatatypes[i] +",";
	        }
	        args = args.substring(0,args.length()-1);
	        return args;

	    }else{
	        System.out.println("Error generating IN String");
	        System.out.println("Tables dont have the same length!!");
	        System.out.println("Check sqlVariables.java to fix");
	        return "";
	    }

	}

	public static String generateARGUMENTSForAlertaPredicted(String[] tableCollums, String[] tableDatatypes) {
		String args ="";
		if(tableCollums.length == tableDatatypes.length){
			for(int i =0 ; i<tableCollums.length && i<tableDatatypes.length ;i++){
				args += "IN sp_" + tableCollums[i] + " " + tableDatatypes[i] +",";
			}
			args += "IN sp_PredictedValue NUMERIC (5,2)";
			return args;

		}else{
			System.out.println("Error generating IN String");
			System.out.println("Tables dont have the same length!!");
			System.out.println("Check sqlVariables.java to fix");
			return "";
		}

	}

	public static String generateINSERT(String tableName, String[] tableCollums) {
	    String insertString = "INSERT INTO " + tableName + " (";
	    for (String value :
	            tableCollums) {
	        insertString += " " + value + ",";
	    }
	    insertString = insertString.substring(0,insertString.length() - 1);
	    insertString += ") VALUES ( ";
	    for (String value :
	            tableCollums) {
	        insertString += " sp_" + value + ",";
	    }
	    insertString = insertString.substring(0,insertString.length() - 1);
	    insertString += ")";

	    return insertString;
	}

	public static String generateINSERTForUser(String role) {
		String insertString = "INSERT INTO " + TableUtilizador.TABLE_UTILIZADOR_NAME + " (";
		for (String value :
				Arrays.copyOfRange(TableUtilizador.TABLE_UTILIZADOR_COLLUMS,
						1, TableUtilizador.TABLE_UTILIZADOR_COLLUMS.length)) {
			insertString += " " + value + ",";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ") VALUES ( ";
		for (String value :
				Arrays.copyOfRange(TableUtilizador.TABLE_UTILIZADOR_COLLUMS,
						1, TableUtilizador.TABLE_UTILIZADOR_COLLUMS.length)) {
			if(!value.equals(TableUtilizador.TABLE_UTILIZADOR_COLLUMS[3]))
				insertString += " sp_" + value + ",";
			else
				insertString += " '" + role + "',";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ");\n";

		return insertString;
	}

	public static String generateINSERTForAlerta(String alertaType) {
		String insertString = "INSERT INTO " + TableAlerta.TABLE_ALERTA_NAME + " (";
		for (String value :
				Arrays.copyOfRange(TableAlerta.TABLE_ALERTA_COLLUMS,
						1, TableAlerta.TABLE_ALERTA_COLLUMS.length)) {
			insertString += " " + value + ",";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ") VALUES ( ";
		for (String value :
				Arrays.copyOfRange(TableAlerta.TABLE_ALERTA_COLLUMS,
						1, TableAlerta.TABLE_ALERTA_COLLUMS.length)) {
			if(!value.equals(TableAlerta.TABLE_ALERTA_COLLUMS[10]))
				insertString += " sp_" + value + ",";
			else
				insertString += " '" + alertaType + "',";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ")";

		return insertString;
	}

	//ONLY FOR ALERTAS
	public static void callStoredProcedureWithNull(Connection connection,String spName, String[]parameters, boolean isPredicted) throws SQLException {
		int size=9;
		if(isPredicted) size++;
		String call = "CALL " + spName + "(";
		for (int i=0;i<size;i++) {
			if(parameters[i] == null && i==6) {
				call += "null,";
			} else {
				call += "'" + parameters[i] + "',";
			}
		}
		call = call.substring(0,call.length()-1);
		call+=");";

		System.out.println(call);
		executeSQL(connection,call);
	}

	public static String generateINSERTForParametroCultura(String[] values, String culturaID, double percentage) {
		String insertString = "INSERT INTO " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " (";
		for (String value :
				Arrays.copyOfRange(TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS,1,
						TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS.length)) {
			insertString += " " + value + ",";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ") VALUES ( " + culturaID + ", ";
		//Min & Max
		for (String value : values) {
				insertString += " sp_" + value + ",";
		}
		//DangerZone
		for(int i=0;i<values.length;i++) {
			if (values[i].contains("Min"))
				insertString += " sp_" + values[i] + " + " + percentage/100 + " * (sp_" + values[i+1] +
						" - sp_" + values[i] + "),";
			else
				insertString += " sp_" + values[i] + " - " + percentage/100 + " * (sp_" + values[i] +
						" - sp_" + values[i-1] + "),";
		}
		insertString = insertString.substring(0,insertString.length() - 1);
		insertString += ")";

		return insertString;
	}


}
