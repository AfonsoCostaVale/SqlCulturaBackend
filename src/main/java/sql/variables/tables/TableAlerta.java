package sql.variables.tables;

import sql.CulturaSP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;

public class TableAlerta {

	public static final String TABLE_ALERTA_NAME = "alerta";
	/**
	 * <p>TABLE_ALERTA_COLLUMS</p>
	 * <ul>
	 *     <li>[0]IdAlerta              </li>
	 *     <li>[1]IdZona                </li>
	 *     <li>[2]IdSensor              </li>
	 *     <li>[3]Hora                  </li>
	 *     <li>[4]Leitura               </li>
	 *     <li>[5]TipoAlerta            </li>
	 *     <li>[6]Cultura               </li>
	 *     <li>[7]IdUtilizador          </li>
	 *     <li>[8]HoraEscrita           </li>
	 *     <li>[9]IdParametroCultura   </li>
	 *     <li>[10]NivelAlerta          </li>
	 * </ul>
	 */
	public static final String[] TABLE_ALERTA_COLLUMS = {
	          "IdAlerta"                //IdAlerta
	        , "IdZona"                  //IdZona
	        , "IdSensor"                //IdSensor
	        , "Hora"                    //Hora
	        , "Leitura"                 //Leitura
	        , "TipoAlerta"              //TipoAlerta
	        , "Cultura"                 //Cultura
	        , "IdUtilizador"            //IdUtilizador
	        , "HoraEscrita"             //HoraEscrita
			, "IdParametroCultura"      //IdParametroCultura
			, "NivelAlerta"             //NivelAlerta
	};
	/**
	 * <p>TABLE_ALERTA_DATATYPES</p>
	 * <ul>
	 *     <li>[0]  INTEGER         - IdAlerta              </li>
	 *     <li>[1]  INTEGER         - IdZona                </li>
	 *     <li>[2]  INTEGER         - IdSensor              </li>
	 *     <li>[3]  TIMESTAMP       - Hora                  </li>
	 *     <li>[4]  NUMERIC (5,2)   - Leitura              </li>
	 *     <li>[5]  VARCHAR(1)      - TipoAlerta            </li>
	 *     <li>[6]  VARCHAR(100)    - Cultura               </li>
	 *     <li>[7]  INTEGER         - IdUtilizador          </li>
	 *     <li>[8]  TIMESTAMP       - HoraEscrita           </li>
	 *     <li>[9] 	INTEGER         - IdParametroCultura    </li>
	 *     <li>[10] VARCHAR(100)   	- NivelAlerta           </li>
	 * </ul>
	 */
	public static final String[] TABLE_ALERTA_DATATYPES = {
	        "INTEGER"               ,//IdAlerta
	        "INTEGER"               ,//IdZona
	        "INTEGER"               ,//IdSensor
	        "TIMESTAMP"             ,//Hora
			"NUMERIC (5,2)"         ,//Leitura
	        "VARCHAR(1)"            ,//TipoAlerta
	        "VARCHAR(100)"          ,//Cultura
	        "INTEGER"               ,//IdUtilizador
	        "TIMESTAMP"             ,//HoraEscrita
			"INTEGER"               ,//IdParametroCultura
			"VARCHAR(100)"          ,//NivelAlerta
	};
	/**
	 * <p>TABLE_ALERTA_PARAMS</p>
	 * <ul>
	 *     <li>[0]  NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE      - IdAlerta              </li>
	 *     <li>[1]  NOT NULL                                        - IdZona                </li>
	 *     <li>[2]  NOT NULL                                        - IdSensor              </li>
	 *     <li>[3]  NOT NULL                                        - Hora                  </li>
	 *     <li>[4]  NOT NULL                                        - Leitura               </li>
	 *     <li>[5]  NOT NULL                                        - TipoAlerta            </li>
	 *     <li>[6]  NOT NULL                                        - Cultura               </li>
	 *     <li>[7]  NULL DEFAULT NULL                               - IdUtilizador          </li>
	 *     <li>[8]  NOT NULL UNIQUE                                 - HoraEscrita           </li>
	 *     <li>[9] 	NOT NULL                                        - IdParametroCultura    </li>
	 *     <li>[10] NOT NULL                                      	- NivelAlerta           </li>
	 * </ul>
	 */
	public static final String[] TABLE_ALERTA_PARAMS = {
	        "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"    ,//IdAlerta
	        "NOT NULL"                                      ,//IdZona
	        "NOT NULL"                                      ,//IdSensor
	        "NOT NULL"                                      ,//Hora
	        "NOT NULL"                                      ,//Leitura
	        "NOT NULL"                                      ,//TipoAlerta
	        "NOT NULL"                                      ,//Cultura
			"NULL DEFAULT NULL"                             ,//IdUtilizador
	        "DEFAULT CURRENT_TIMESTAMP NOT NULL"     		,//HoraEscrita
			"NOT NULL"                                      ,//IdParametroCultura
			"NOT NULL"                                      ,//NivelAlerta
	};
	public static final String[] TABLE_ALERTA = {
	        TABLE_ALERTA_COLLUMS[0]  + " " + TABLE_ALERTA_DATATYPES[0]  + " " + TABLE_ALERTA_PARAMS[0],     //IdAlerta
	        TABLE_ALERTA_COLLUMS[1]  + " " + TABLE_ALERTA_DATATYPES[1]  + " " + TABLE_ALERTA_PARAMS[1],     //IdZona
	        TABLE_ALERTA_COLLUMS[2]  + " " + TABLE_ALERTA_DATATYPES[2]  + " " + TABLE_ALERTA_PARAMS[2],     //IdSensor
	        TABLE_ALERTA_COLLUMS[3]  + " " + TABLE_ALERTA_DATATYPES[3]  + " " + TABLE_ALERTA_PARAMS[3],     //Hora
	        TABLE_ALERTA_COLLUMS[4]  + " " + TABLE_ALERTA_DATATYPES[4]  + " " + TABLE_ALERTA_PARAMS[4],     //Leitura
	        TABLE_ALERTA_COLLUMS[5]  + " " + TABLE_ALERTA_DATATYPES[5]  + " " + TABLE_ALERTA_PARAMS[5],     //TipoAlerta
	        TABLE_ALERTA_COLLUMS[6]  + " " + TABLE_ALERTA_DATATYPES[6]  + " " + TABLE_ALERTA_PARAMS[6],     //Cultura
	        TABLE_ALERTA_COLLUMS[7]  + " " + TABLE_ALERTA_DATATYPES[7]  + " " + TABLE_ALERTA_PARAMS[7],     //IdUtilizador
	        TABLE_ALERTA_COLLUMS[8]  + " " + TABLE_ALERTA_DATATYPES[8]  + " " + TABLE_ALERTA_PARAMS[8],     //HoraEscrita
	        TABLE_ALERTA_COLLUMS[9]  + " " + TABLE_ALERTA_DATATYPES[9]  + " " + TABLE_ALERTA_PARAMS[9],     //IdParametroCultura
	        TABLE_ALERTA_COLLUMS[10] + " " + TABLE_ALERTA_DATATYPES[10] + " " + TABLE_ALERTA_PARAMS[10],    //NivelAlerta
	        "CONSTRAINT FK2_"+TABLE_ALERTA_COLLUMS[1]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[1] + ") REFERENCES " + TableZona.TABLE_ZONA_NAME     +"(" + TABLE_ALERTA_COLLUMS[1] + ")",
	        "CONSTRAINT FK_" +TABLE_ALERTA_COLLUMS[2]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[2] + ") REFERENCES " + TableSensor.TABLE_SENSOR_NAME       +"(" + TABLE_ALERTA_COLLUMS[2] + ")",
	        "CONSTRAINT FK2_"+TABLE_ALERTA_COLLUMS[7]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[7] + " ) REFERENCES " + TableUtilizador.TABLE_UTILIZADOR_NAME +"(" + TABLE_ALERTA_COLLUMS[7] + ")",
	        "CONSTRAINT FK_" +TABLE_ALERTA_COLLUMS[9]+" FOREIGN KEY (" + TABLE_ALERTA_COLLUMS[9] + " ) REFERENCES " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME +"(" + TABLE_ALERTA_COLLUMS[9] + ")",
	};

	public static final String SP_INSERIR_ALERTA_NAME               = "Inserir_Alerta";
	public static final String SP_INSERIR_ALERTA_PREDICTED_NAME     = "Inserir_Alerta_Predicted";
	public static final String SP_ALTERAR_ALERTA_NAME               = "Alterar_Alerta";
	public static final String SP_ELIMINAR_ALERTA_NAME              = "Eliminar_Alerta";
	public static final String SP_SELECT_ALERTA_NAME                = "Selecionar_Alerta";

	public static void createSPInserir_Alerta(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,1, TABLE_ALERTA_COLLUMS.length-1 ),
	            Arrays.copyOfRange(TABLE_ALERTA_DATATYPES,1, TABLE_ALERTA_DATATYPES.length-1   )
	    );

		String Variable_LimiteInferior_name = "alerta_Min";
		String Variable_LimiteDangerZoneInferior_name = "alerta_Danger_Min";
		String Variable_LimiteSuperior_name = "alerta_Max";
		String Variable_LimiteDangerZoneSuperior_name = "alerta_Danger_Max";

		String lastAlertaIf_name = "last_alertaIf";

		String lastAlertaDangerDate_name = "last_Dangerdate";
		String lastAlertaDeathDate_name = "last_Deathdate";

		//String compareAlertaTimes = "select time_to_sec(timediff(sp_" + TABLE_ALERTA_COLLUMS[8] + ", " + lastAlertaDate_name + " )) / 3600 > ;";

		String finalStatements = argsForAlertas();

		String insertDanger = CulturaSP.generateINSERTForAlerta("DangerZone");
		String insertDeath = CulturaSP.generateINSERTForAlerta("DeathZone");
		String insertNormal = CulturaSP.generateINSERTForAlerta("Healthy");

		finalStatements += "\nIF ((select time_to_sec(timediff(sp_" + TABLE_ALERTA_COLLUMS[8] + ", " + lastAlertaDangerDate_name + ")) / 3600 > 0.16) OR " + lastAlertaIf_name + " <> 'DangerZone')" +
				" AND ((sp_" + TABLE_ALERTA_COLLUMS[4] + " < " + Variable_LimiteDangerZoneInferior_name + " AND sp_" + TABLE_ALERTA_COLLUMS[4] + " > " + Variable_LimiteInferior_name  + ") OR (sp_" + TABLE_ALERTA_COLLUMS[4] + " > " + Variable_LimiteDangerZoneSuperior_name + " AND sp_" + TABLE_ALERTA_COLLUMS[4] + " < " + Variable_LimiteSuperior_name + "))" +
				" AND (sp_" + TABLE_ALERTA_COLLUMS[7] + " <> 'null') THEN\n" + insertDanger + " ;END IF;";
		finalStatements += "\nIF ((select time_to_sec(timediff(sp_" + TABLE_ALERTA_COLLUMS[8] + ", " + lastAlertaDeathDate_name + ")) / 3600 > 0.08) OR " + lastAlertaIf_name + " <> 'DeathZone')" +
				" AND (sp_" + TABLE_ALERTA_COLLUMS[4] + " < " + Variable_LimiteInferior_name + " OR sp_" + TABLE_ALERTA_COLLUMS[4] + " > " + Variable_LimiteSuperior_name + ")" +
				" AND (sp_" + TABLE_ALERTA_COLLUMS[7] + " <> 'null') THEN\n" + insertDeath + " ;END IF;";
		finalStatements += "\nIF " + lastAlertaIf_name + " <> 'Healthy' AND (sp_" + TABLE_ALERTA_COLLUMS[4] + " >= " + Variable_LimiteDangerZoneInferior_name + " AND sp_" + TABLE_ALERTA_COLLUMS[4] + " <= " + Variable_LimiteDangerZoneSuperior_name + ")" +
				"AND (sp_" + TABLE_ALERTA_COLLUMS[7] + " <> 'null') THEN\n" + insertNormal + " ;END IF";

	    createStoredProcedure(connection, SP_INSERIR_ALERTA_NAME, finalStatements, args);

	}

	public static void createSPInserir_Alerta_Predicted(Connection connection) throws SQLException {
		String args = CulturaSP.generateARGUMENTSForAlertaPredicted(
				Arrays.copyOfRange(TABLE_ALERTA_COLLUMS,1, TABLE_ALERTA_COLLUMS.length-1 ),
				Arrays.copyOfRange(TABLE_ALERTA_DATATYPES,1, TABLE_ALERTA_DATATYPES.length-1   )
		);

		String Variable_LimiteInferior_name = "alerta_Min";
		String Variable_LimiteDangerZoneInferior_name = "alerta_Danger_Min";
		String Variable_LimiteSuperior_name = "alerta_Max";
		String Variable_LimiteDangerZoneSuperior_name = "alerta_Danger_Max";

		String lastAlertaIf_name = "last_alertaIf";

		String lastAlertaAproachIf_name = "last_alerta_aproachIf";

		String finalStatements = argsForAlertas();

		String insertDanger = CulturaSP.generateINSERTForAlerta("Aproaching DangerZone...");
		String insertDeath = CulturaSP.generateINSERTForAlerta("Aproaching DeathZone...");
		String insertNormal = CulturaSP.generateINSERTForAlerta("Aproaching Healthy...");

		finalStatements += "\nIF (sp_PredictedValue <> sp_" + TABLE_ALERTA_COLLUMS[4] + " AND (" + lastAlertaAproachIf_name + " <> 'Aproaching DangerZone...' AND " + lastAlertaIf_name + " <> 'DangerZone') AND ((sp_PredictedValue < " + Variable_LimiteDangerZoneInferior_name + " AND sp_PredictedValue > " + Variable_LimiteInferior_name  + ") OR (sp_PredictedValue > " + Variable_LimiteDangerZoneSuperior_name + " AND sp_PredictedValue < " + Variable_LimiteSuperior_name + ")))" +
				"AND (sp_" + TABLE_ALERTA_COLLUMS[7] + " <> 'null') THEN\n" + insertDanger + " ;END IF;";
		finalStatements += "\nIF (sp_PredictedValue <> sp_" + TABLE_ALERTA_COLLUMS[4] + " AND (" + lastAlertaAproachIf_name + " <> 'Aproaching DeathZone...' AND " + lastAlertaIf_name + " <> 'DeathZone') AND (sp_PredictedValue < " + Variable_LimiteInferior_name + " OR sp_PredictedValue > " + Variable_LimiteSuperior_name + "))" +
				"AND (sp_" + TABLE_ALERTA_COLLUMS[7] + " <> 'null') THEN\n" + insertDeath + " ;END IF;";
		finalStatements += "\nIF (sp_PredictedValue <> sp_" + TABLE_ALERTA_COLLUMS[4] + " AND (" + lastAlertaAproachIf_name + " <> 'Aproaching Healthy...' AND " + lastAlertaIf_name + " <> 'Healthy') AND (sp_PredictedValue >= " + Variable_LimiteDangerZoneInferior_name + " AND sp_PredictedValue <= " + Variable_LimiteDangerZoneSuperior_name + "))" +
				"AND (sp_" + TABLE_ALERTA_COLLUMS[7] + " <> 'null') THEN\n" + insertNormal + " ;END IF";

		createStoredProcedure(connection, SP_INSERIR_ALERTA_PREDICTED_NAME, finalStatements, args);
	}

	public static void createSPAlterar_Alerta(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_ALERTA_COLLUMS, TABLE_ALERTA_DATATYPES);


	    String statements = "UPDATE " + TABLE_ALERTA_NAME + " SET " + TABLE_ALERTA_COLLUMS[1] + " = sp_" + TABLE_ALERTA_COLLUMS[1] +
	            " ," + TABLE_ALERTA_COLLUMS[2] + " = sp_" + TABLE_ALERTA_COLLUMS[2] +
	            " ," + TABLE_ALERTA_COLLUMS[3] + " = sp_" + TABLE_ALERTA_COLLUMS[3] +
	            " ," + TABLE_ALERTA_COLLUMS[4] + " = sp_" + TABLE_ALERTA_COLLUMS[4] +
	            " ," + TABLE_ALERTA_COLLUMS[5] + " = sp_" + TABLE_ALERTA_COLLUMS[5] +
	            " ," + TABLE_ALERTA_COLLUMS[6] + " = sp_" + TABLE_ALERTA_COLLUMS[6] +
	            " ," + TABLE_ALERTA_COLLUMS[7] + " = sp_" + TABLE_ALERTA_COLLUMS[7] +
	            " ," + TABLE_ALERTA_COLLUMS[8] + " = sp_" + TABLE_ALERTA_COLLUMS[8] +
	            " ," + TABLE_ALERTA_COLLUMS[9] + " = sp_" + TABLE_ALERTA_COLLUMS[9] +
	            " ," + TABLE_ALERTA_COLLUMS[10] + " = sp_" + TABLE_ALERTA_COLLUMS[10] +
	            " WHERE " + TABLE_ALERTA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_ALERTA_NAME, statements, args);

	}

	public static void createSPEliminar_Alerta(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_ALERTA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_ALERTA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_ALERTA_NAME, statements, args);
	}

	public static void createSPSelect_Alerta(Connection connection) throws SQLException {

		String args ="IN sp_"+ TableUtilizador.TABLE_UTILIZADOR_COLLUMS[1] + " " + TableUtilizador.TABLE_UTILIZADOR_DATATYPES[1] + " ,IN sp_"+TABLE_ALERTA_COLLUMS[3] + " " + TABLE_ALERTA_DATATYPES[3];
		String statements = "SELECT "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[1] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[2] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[3] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[4] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[5] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[6] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[7] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[8] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[9] + ", "
				+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[10] +" "
				+" FROM " + TABLE_ALERTA_NAME + ", "+TableUtilizador.TABLE_UTILIZADOR_NAME
				//where Utilizador.IDUtilizador = Alerta.IDUtilizador
				+" WHERE "+ TableUtilizador.TABLE_UTILIZADOR_NAME+"."+TableUtilizador.TABLE_UTILIZADOR_COLLUMS[0] +" = " +TABLE_ALERTA_NAME+"."+TABLE_ALERTA_COLLUMS[7]
				//AND Utilizador.username = sp_username
				+" AND " +TableUtilizador.TABLE_UTILIZADOR_NAME + "." + TableUtilizador.TABLE_UTILIZADOR_COLLUMS[1] + " = sp_"+TableUtilizador.TABLE_UTILIZADOR_COLLUMS[1]
				+" AND DATE("+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[3] + " ) = sp_"+TABLE_ALERTA_COLLUMS[3]
				+" AND "+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[8]+ " >= now() - interval 1 second"
				+" ORDER BY "+TABLE_ALERTA_NAME + "." +TABLE_ALERTA_COLLUMS[3] + " DESC"
				;
		createStoredProcedure(connection, SP_SELECT_ALERTA_NAME, statements, args);
	}

	public static ResultSet callSPSelect_Alerta(Connection connection, int IdUtilizador) throws SQLException {
		CallableStatement cst = connection.prepareCall("{call " + SP_SELECT_ALERTA_NAME +"(?)}");
		cst.setInt(1,IdUtilizador);


		return cst.executeQuery();
	}

	private static String argsForAlertas() {
		String Variable_LimiteInferior_name = "alerta_Min";
		String Variable_LimiteDangerZoneInferior_name = "alerta_Danger_Min";
		String Variable_LimiteSuperior_name = "alerta_Max";
		String Variable_LimiteDangerZoneSuperior_name = "alerta_Danger_Max";

		String Variable_LimiteInferior = "DECLARE " + Variable_LimiteInferior_name + " " + TableParametroCultura.TABLE_PARAMETROCULTURA_DATATYPES[2] +";";
		String Variable_LimiteDangerZoneInferior = "DECLARE " + Variable_LimiteDangerZoneInferior_name + " " + TableParametroCultura.TABLE_PARAMETROCULTURA_DATATYPES[8] +";";
		String Variable_LimiteSuperior = "DECLARE " + Variable_LimiteSuperior_name + " " + TableParametroCultura.TABLE_PARAMETROCULTURA_DATATYPES[3] +";";
		String Variable_LimiteDangerZoneSuperior = "DECLARE " + Variable_LimiteDangerZoneSuperior_name + " " + TableParametroCultura.TABLE_PARAMETROCULTURA_DATATYPES[9] +";";

		String lastAlerta_name = "last_alerta";
		String lastAlertaAproach_name = "last_alerta_aproach";
		String lastAlertaIf_name = "last_alertaIf";
		String lastAlertaAproachIf_name = "last_alerta_aproachIf";
		String lastAlerta = "DECLARE " + lastAlerta_name + " " + TABLE_ALERTA_DATATYPES[10] +";";
		String lastAlertaAproach = "DECLARE " + lastAlertaAproach_name + " " + TABLE_ALERTA_DATATYPES[10] +";";
		String lastAlertaIf = "DECLARE " + lastAlertaIf_name + " " + TABLE_ALERTA_DATATYPES[10] +";";
		String lastAlertaAproachIf = "DECLARE " + lastAlertaAproachIf_name + " " + TABLE_ALERTA_DATATYPES[10] +";";

		String lastAlertaDangerDate_name = "last_Dangerdate";
		String lastAlertaDeathDate_name = "last_Deathdate";
		String lastAlertaDangerDate = "DECLARE " + lastAlertaDangerDate_name + " " + TABLE_ALERTA_DATATYPES[8] +";";
		String lastAlertaDeathDate = "DECLARE " + lastAlertaDeathDate_name + " " + TABLE_ALERTA_DATATYPES[8] +";";

		String sensorType = "sp_" + TABLE_ALERTA_COLLUMS[5];
		String sensorID = "sp_" + TABLE_ALERTA_COLLUMS[2];

		String statementsHumidade = "IF " + sensorType + " = 'H' THEN\n" + "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[2] + " INTO " + Variable_LimiteInferior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[3] + " INTO " + Variable_LimiteSuperior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[8] + " INTO " + Variable_LimiteDangerZoneInferior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[9] + " INTO " + Variable_LimiteDangerZoneSuperior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + "; END IF;";

		String statementsTemperatura = "IF " + sensorType + " = 'T' THEN\n" + "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[4] + " INTO " + Variable_LimiteInferior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[5] + " INTO " + Variable_LimiteSuperior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[10] + " INTO " + Variable_LimiteDangerZoneInferior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[11] + " INTO " + Variable_LimiteDangerZoneSuperior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + "; END IF;";

		String statementsLuz = "IF " + sensorType + " = 'L' THEN\n" + "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[6] + " INTO " + Variable_LimiteInferior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[7] + " INTO " + Variable_LimiteSuperior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[12] + " INTO " + Variable_LimiteDangerZoneInferior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + ";\n"
				+ "SELECT " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[13] + " INTO " + Variable_LimiteDangerZoneSuperior_name + " FROM " + TableParametroCultura.TABLE_PARAMETROCULTURA_NAME + " WHERE " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + "; END IF;";

		String statementsLastAlerta = "SELECT " + TABLE_ALERTA_COLLUMS[10] + " INTO " + lastAlerta_name + " FROM " + TABLE_ALERTA_NAME
				+ " WHERE " + TABLE_ALERTA_COLLUMS[2] + " = " + sensorID + " AND " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + " AND " + TABLE_ALERTA_COLLUMS[10] + " <> 'Aproaching DangerZone...' AND " +
				TABLE_ALERTA_COLLUMS[10] + " <> 'Aproaching DeathZone...' AND " + TABLE_ALERTA_COLLUMS[10] + " <> 'Aproaching Healthy...'" +
				" ORDER BY " + TABLE_ALERTA_COLLUMS[0] + " DESC LIMIT 1;";

		statementsLastAlerta += "SELECT " + TABLE_ALERTA_COLLUMS[10] + " INTO " + lastAlertaAproach_name + " FROM " + TABLE_ALERTA_NAME
				+ " WHERE " + TABLE_ALERTA_COLLUMS[2] + " = " + sensorID + " AND " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + " AND " + TABLE_ALERTA_COLLUMS[10] + " <> 'DangerZone' AND " +
				TABLE_ALERTA_COLLUMS[10] + " <> 'DeathZone' AND " + TABLE_ALERTA_COLLUMS[10] + " <> 'Healthy'" +
				" ORDER BY " + TABLE_ALERTA_COLLUMS[0] + " DESC LIMIT 1;";

		statementsLastAlerta += "SELECT " + TABLE_ALERTA_COLLUMS[8] + " INTO " + lastAlertaDangerDate_name + " FROM " + TABLE_ALERTA_NAME
				+ " WHERE " + TABLE_ALERTA_COLLUMS[2] + " = " + sensorID + " AND " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + " AND " + TABLE_ALERTA_COLLUMS[10] + " = 'DangerZone' ORDER BY " + TABLE_ALERTA_COLLUMS[0] + " DESC LIMIT 1;";

		statementsLastAlerta += "SELECT " + TABLE_ALERTA_COLLUMS[8] + " INTO " + lastAlertaDeathDate_name + " FROM " + TABLE_ALERTA_NAME
				+ " WHERE " + TABLE_ALERTA_COLLUMS[2] + " = " + sensorID + " AND " + TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS[0] + " = sp_" + TABLE_ALERTA_COLLUMS[9] + " AND " + TABLE_ALERTA_COLLUMS[10] + " = 'DeathZone' ORDER BY " + TABLE_ALERTA_COLLUMS[0] + " DESC LIMIT 1;";

		statementsLastAlerta += "\nSET " + lastAlertaIf_name + " = IFNULL(" + lastAlerta_name + ",'Healthy');";

		statementsLastAlerta += "\nSET " + lastAlertaAproachIf_name + " = IFNULL(" + lastAlertaAproach_name + ",'Aproaching Healthy...');";

		return "\n" + Variable_LimiteInferior
				+"\n" + Variable_LimiteSuperior
				+"\n" + Variable_LimiteDangerZoneInferior
				+"\n" + Variable_LimiteDangerZoneSuperior
				+"\n" + lastAlerta
				+"\n" + lastAlertaAproach
				+"\n" + lastAlertaIf
				+"\n" + lastAlertaAproachIf
				+"\n" + lastAlertaDangerDate
				+"\n" + lastAlertaDeathDate
				+"\n" + statementsHumidade
				+"\n" + statementsTemperatura
				+"\n" + statementsLuz
				+"\n" + statementsLastAlerta;

	}
}
