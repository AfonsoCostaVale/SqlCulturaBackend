package sql.variables.tables;

import sql.CulturaSP;
import sql.SqlController;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.*;

public class TableCultura {
	public static final String TABLE_CULTURA_NAME = "cultura";
	/**
	 * <p>TABLE_CULTURA_COLLUMS</p>
	 * <ul>
	 *     <li>[0]IdCultura     </li>
	 *     <li>[1]NomeCultura   </li>
	 *     <li>[2]IdUtilizador  </li>
	 *     <li>[3]Estado        </li>
	 *     <li>[4]IdZona        </li>
	 * </ul>
	 */
	public static final String[] TABLE_CULTURA_COLLUMS = {
	          "IdCultura"
	        , "NomeCultura"
	        , "IdUtilizador"
	        , "Estado"
			, "IdZona"
	};
	/**
	 * <p>TABLE_UTILIZADOR_DATATYPES</p>
	 * <ul>
	 *     <li>[0]INTEGER       - IdCultura       </li>
	 *     <li>[1]VARCHAR(50)   - NomeCultura     </li>
	 *     <li>[2]INTEGER       - IdUtilizador    </li>
	 *     <li>[3]INTEGER       - Estado          </li>
	 *     <li>[4]INTEGER       - IdZona          </li>
	 * </ul>
	 */
	public static final String[] TABLE_CULTURA_DATATYPES = {
	          "INTEGER"         //IdCultura
	        , "VARCHAR(50)"     //NomeCultura
	        , "INTEGER"         //IdUtilizador
	        , "INTEGER"         //Estado
	        , "INTEGER"         //IdZona
	};
	/**
	 * <p>TABLE_UTILIZADOR_PARAMS</p>
	 * <ul>
	 *     <li>[0]NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE    - IdCultura     </li>
	 *     <li>[1]NOT NULL UNIQUE                               - NomeCultura   </li>
	 *     <li>[2]                                              - IdUtilizador  </li>
	 *     <li>[3]NOT NULL                                      - Estado        </li>
	 *     <li>[4]NOT NULL                                      - IdZona        </li>
	 * </ul>
	 */
	public static final String[] TABLE_CULTURA_PARAMS = {
	          "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"  //IdCultura
	        , "NOT NULL UNIQUE"                             //NomeCultura
	        , ""                                            //IdUtilizador
	        , "NOT NULL"                                    //Estado
	        , "NOT NULL"                                    //IdZona
	};
	public static final String[] TABLE_CULTURA = {
	        TABLE_CULTURA_COLLUMS[0] + " " + TABLE_CULTURA_DATATYPES[0] + " " + TABLE_CULTURA_PARAMS[0] ,
	        TABLE_CULTURA_COLLUMS[1] + " " + TABLE_CULTURA_DATATYPES[1] + " " + TABLE_CULTURA_PARAMS[1] ,
	        TABLE_CULTURA_COLLUMS[2] + " " + TABLE_CULTURA_DATATYPES[2] + " " + TABLE_CULTURA_PARAMS[2] ,
	        TABLE_CULTURA_COLLUMS[3] + " " + TABLE_CULTURA_DATATYPES[3] + " " + TABLE_CULTURA_PARAMS[3]	,
	        TABLE_CULTURA_COLLUMS[4] + " " + TABLE_CULTURA_DATATYPES[4] + " " + TABLE_CULTURA_PARAMS[4]	,
	        "CONSTRAINT FK_" + TABLE_CULTURA_COLLUMS[2] + " FOREIGN KEY (" + TABLE_CULTURA_COLLUMS[2] + ") REFERENCES " + TableUtilizador.TABLE_UTILIZADOR_NAME + "(" + TABLE_CULTURA_COLLUMS[2] + ")",
	        "CONSTRAINT FK4_" + TABLE_CULTURA_COLLUMS[4] + " FOREIGN KEY (" + TABLE_CULTURA_COLLUMS[4] + ") REFERENCES " + TableZona.TABLE_ZONA_NAME + "(" + TABLE_CULTURA_COLLUMS[4] + ")"
	};
	public static final String SP_INSERIR_CULTURA_NAME              = "Inserir_Cultura";
	public static final String SP_ALTERAR_CULTURA_NAME              = "Alterar_Cultura";
	public static final String SP_ELIMINAR_CULTURA_NAME             = "Eliminar_Cultura";
	public static final String SP_SELECT_CULTURA_NAME               = "Selecionar_Cultura";

	public static void createSPInserir_Cultura(Connection connection) throws SQLException {

		String[]argsCultura = Arrays.copyOfRange(TABLE_CULTURA_COLLUMS,1, TABLE_CULTURA_COLLUMS.length);
		String[]argsParametros = Arrays.copyOfRange(TableParametroCultura.TABLE_PARAMETROCULTURA_COLLUMS, 2, 8);

		String[]argsForBoth = new String[argsCultura.length + argsParametros.length];

		System.arraycopy(argsCultura,0,argsForBoth,0,argsCultura.length);
		System.arraycopy(argsParametros,0,argsForBoth,argsCultura.length,argsParametros.length);

		String[]argsCulturaType = Arrays.copyOfRange(TABLE_CULTURA_DATATYPES,1, TABLE_CULTURA_DATATYPES.length);
		String[]argsParametrosType = Arrays.copyOfRange(TableParametroCultura.TABLE_PARAMETROCULTURA_DATATYPES, 2, 8);

		String[]argsForBothType = new String[argsCulturaType.length + argsParametrosType.length];

		System.arraycopy(argsCulturaType,0,argsForBothType,0,argsCulturaType.length);
		System.arraycopy(argsParametrosType,0,argsForBothType,argsCulturaType.length,argsParametrosType.length);

		String args = CulturaSP.generateARGUMENTS(argsForBoth, argsForBothType);

	    String statements = CulturaSP.generateINSERT(TABLE_CULTURA_NAME, argsCultura)+";";

	   String culturaID_name = "idForCultura";
	   String culturaID = "DECLARE " + culturaID_name + " " + TABLE_CULTURA_DATATYPES[0] + ";";

	   String statementsCultura = "SELECT " + TABLE_CULTURA_COLLUMS[0] + " INTO " + culturaID_name + " FROM " + TABLE_CULTURA_NAME
			   + " ORDER BY " + TABLE_CULTURA_COLLUMS[0] + " DESC LIMIT 1;";

	   String finalStatements = "\n" + culturaID + "\n" + statements + "\n" + statementsCultura;

	   finalStatements += "\n" + CulturaSP.generateINSERTForParametroCultura(argsParametros,culturaID_name,15);

	   createStoredProcedure(connection, SP_INSERIR_CULTURA_NAME, finalStatements, args);

	}

	public static void createSPAlterar_Cultura(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_CULTURA_COLLUMS, TABLE_CULTURA_DATATYPES);

	    String statements = "UPDATE " + TABLE_CULTURA_NAME + " SET " + TABLE_CULTURA_COLLUMS[1] + " = sp_" + TABLE_CULTURA_COLLUMS[1] +
	            " ," + TABLE_CULTURA_COLLUMS[2] + " = sp_" + TABLE_CULTURA_COLLUMS[2] +
	            " ," + TABLE_CULTURA_COLLUMS[3] + " = sp_" + TABLE_CULTURA_COLLUMS[3] +
	            " ," + TABLE_CULTURA_COLLUMS[4] + " = sp_" + TABLE_CULTURA_COLLUMS[4] +
	            " WHERE " + TABLE_CULTURA_COLLUMS[0] + " = sp_" + TABLE_CULTURA_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_CULTURA_NAME, statements, args);

	}

	public static void createSPEliminar_Cultura(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue " + TABLE_CULTURA_DATATYPES[0];
	    String statements = "DELETE FROM " + TABLE_CULTURA_NAME + " WHERE 'sp_Param' = sp_ParamValue";

	    createStoredProcedure(connection, SP_ELIMINAR_CULTURA_NAME, statements, args);
	}

	public static void createSPSelect_Cultura(Connection connection) throws SQLException {
		String args ="";// "IN sp_"+ TABLE_ALERTA_COLLUMS[7] + " " + TABLE_ALERTA_DATATYPES[7];
		String statements = "SELECT * FROM " + TABLE_CULTURA_NAME;// + " WHERE sp_" + TABLE_ALERTA_COLLUMS[7] + " = " + TABLE_ALERTA_NAME +"."+ TABLE_ALERTA_COLLUMS[7];
		createStoredProcedure(connection, SP_SELECT_CULTURA_NAME, statements, args);
	}

	public static ResultSet callSPSelect_Cultura(Connection connection, int IdUtilizador) throws SQLException {
		CallableStatement cst = connection.prepareCall("{call Selecionar_Cultura(?)}");
		cst.setInt(1, IdUtilizador);
		return cst.executeQuery();
	}
}
