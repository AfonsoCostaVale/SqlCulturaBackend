package sql.variables.tables;

import sql.CulturaSP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static sql.SqlController.createStoredProcedure;

public class TableUtilizador {
	public static final String TABLE_UTILIZADOR_NAME = "utilizador";
	public static final String TABLE_UTILIZADOR_COLLUMS_PASSWORD = "Password";
	public static final String TABLE_UTILIZADOR_DATATYPES_PASSWORD = "VARCHAR(100)";
	public static final String TABLE_UTILIZADOR_PARAMS_PASSWORD = "NOT NULL";

	/**
	 * <p>TABLE_UTILIZADOR_COLLUMS</p>
	 * <ul>
	 *     <li>[0]IdUtilizador      </li>
	 *     <li>[1]NomeUtilizador  </li>
	 *     <li>[2]EmailUtilizador   </li>
	 *     <li>[3]TipoUtilizador    </li>
	 * </ul>
	 */
	 //     <li>[4]Password          </li>
	public static final String[] TABLE_UTILIZADOR_COLLUMS = {
			"IdUtilizador"
			, "NomeUtilizador"
			, "EmailUtilizador"
			, "TipoUtilizador"
			//, "Password"
	};
	/**
	 * <p>TABLE_UTILIZADOR_DATATYPES</p>
	 * <ul>
	 *     <li>[0]INTEGER -IdUtilizador      </li>
	 *     <li>[1]VARCHAR(100) -NomeUtilizador  </li>
	 *     <li>[2]VARCHAR(100) -EmailUtilizador   </li>
	 *     <li>[3]VARCHAR(100) -TipoUtilizador    </li>
	 * </ul>
	 */
	 //    <li>[4]VARCHAR(100) -Password    </li>
	public static final String[] TABLE_UTILIZADOR_DATATYPES = {
	          "INTEGER"         //IdUtilizador
	        , "VARCHAR(100)"    //NomeUtilizador
	        , "VARCHAR(100)"    //EmailUtilizador
	        , "VARCHAR(100)"    //TipoUtilizador
	        //, "VARCHAR(100)"    //Password
	};
	/**
	 * <p>TABLE_UTILIZADOR_PARAMS</p>
	 * <ul>
	 *     <li>[0]NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE -IdUtilizador      </li>
	 *     <li>[1]NOT NULL UNIQUE -NomeUtilizador    </li>
	 *     <li>[2]NOT NULL -EmailUtilizador     </li>
	 *     <li>[3]NOT NULL -TipoUtilizador      </li>
	 * </ul>
	 */
	 //  <li>[4]NOT NULL -Password            </li>
	public static final String[] TABLE_UTILIZADOR_PARAMS = {
	          "NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE"      //IdUtilizador
	        , "NOT NULL UNIQUE"                                 //NomeUtilizador
	        , "NOT NULL"                                        //EmailUtilizador
	        , "NOT NULL"                                        //TipoUtilizador
	        //, "NOT NULL"                                        //Password
	};
	public static final String[] TABLE_UTILIZADOR = {
	         TABLE_UTILIZADOR_COLLUMS[0] + " " + TABLE_UTILIZADOR_DATATYPES[0] + " " + TABLE_UTILIZADOR_PARAMS[0]   //IdUtilizador
	        ,TABLE_UTILIZADOR_COLLUMS[1] + " " + TABLE_UTILIZADOR_DATATYPES[1] + " " + TABLE_UTILIZADOR_PARAMS[1]   //NomeUtilizador
	        ,TABLE_UTILIZADOR_COLLUMS[2] + " " + TABLE_UTILIZADOR_DATATYPES[2] + " " + TABLE_UTILIZADOR_PARAMS[2]   //EmailUtilizador
			,TABLE_UTILIZADOR_COLLUMS[3] + " " + TABLE_UTILIZADOR_DATATYPES[3] + " " + TABLE_UTILIZADOR_PARAMS[3]   //TipoUtilizador
	        //,TABLE_UTILIZADOR_COLLUMS[4] + " " + TABLE_UTILIZADOR_DATATYPES[4] + " " + TABLE_UTILIZADOR_PARAMS[4]   //Password
	};
	public static final String SP_INSERIR_USER_INVESTIGADOR_NAME    = "Inserir_User_Investigador";
	public static final String SP_INSERIR_USER_TECNICO_NAME         = "Inserir_User_Tecnico";
	public static final String SP_INSERIR_USER_ADMIN_NAME           = "Inserir_User_Admin";
	public static final String SP_INSERIR_USER_CLONER_NAME = "Inserir_User_MqttReader";
	public static final String SP_ALTERAR_USER_NAME                 = "Alterar_User";
	public static final String SP_ELIMINAR_USER_NAME                = "Eliminar_User";
	//Roles for Application
	public static final String ROLE_INVESTIGADOR     = "Investigador";
	public static final String ROLE_TECNICO          = "Tecnico";
	public static final String ROLE_ADMIN            = "Admin";
	public static final String ROLE_CLONER 			 = "Cloner";


	private static String[] createSPInserir_User_Base(String role) {
		String args = CulturaSP.generateARGUMENTS(
	            Arrays.copyOfRange(TABLE_UTILIZADOR_COLLUMS,1, TABLE_UTILIZADOR_COLLUMS.length-1),
	            Arrays.copyOfRange(TABLE_UTILIZADOR_DATATYPES,1, TABLE_UTILIZADOR_DATATYPES.length-1)
	    );
		args += ",sp_"+TABLE_UTILIZADOR_COLLUMS_PASSWORD + " " + TABLE_UTILIZADOR_DATATYPES_PASSWORD;

		String statements = CulturaSP.generateINSERTForUser(role);

	    String create = "SET @query = CONCAT('CREATE USER \"', sp_"+ TABLE_UTILIZADOR_COLLUMS[1]+
				", '\"@\"', 'localhost', '\" IDENTIFIED BY \"', sp_"+ TABLE_UTILIZADOR_COLLUMS_PASSWORD+", '\";');\n" +
				"PREPARE stmt FROM @query;\n" +
				"EXECUTE stmt;\n" +
				"DEALLOCATE PREPARE stmt;";

	    String addRole = "SET @query = CONCAT('GRANT \"', '"+role+
				"', '\" TO \"', sp_"+ TABLE_UTILIZADOR_COLLUMS[1]+", '\"@\"', 'localhost', '\";');\n" +
				"PREPARE stmt FROM @query;\n" +
				"EXECUTE stmt;\n" +
				"DEALLOCATE PREPARE stmt;";

	    String setDefaultRole = "SET @query = CONCAT('SET DEFAULT ROLE \"', '"+role+
				"', '\" FOR \"', sp_"+ TABLE_UTILIZADOR_COLLUMS[1]+", '\"@\"', 'localhost', '\";');\n" +
				"PREPARE stmt FROM @query;\n" +
				"EXECUTE stmt;\n" +
				"DEALLOCATE PREPARE stmt";

	    statements += create + "\n" + addRole + "\n" + setDefaultRole;

		return new String[]{statements,args};
	}

	public static void createSPInserir_User_Investigador(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_INVESTIGADOR);

		createStoredProcedure(connection, SP_INSERIR_USER_INVESTIGADOR_NAME, args_statements[0], args_statements[1]);
	}

	public static void createSPInserir_User_Tecnico(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_TECNICO);

		createStoredProcedure(connection, SP_INSERIR_USER_TECNICO_NAME, args_statements[0], args_statements[1]);
	}

	public static void createSPInserir_User_Admin(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_ADMIN);

		createStoredProcedure(connection, SP_INSERIR_USER_ADMIN_NAME, args_statements[0], args_statements[1]);
	}

	public static void createSPInserir_User_Cloner(Connection connection) throws SQLException {
		String[] args_statements = createSPInserir_User_Base(ROLE_CLONER);

		createStoredProcedure(connection, SP_INSERIR_USER_CLONER_NAME, args_statements[0], args_statements[1]);
	}

	public static void createSPAlterar_User(Connection connection) throws SQLException {

		String args = CulturaSP.generateARGUMENTS(TABLE_UTILIZADOR_COLLUMS, TABLE_UTILIZADOR_DATATYPES);

	    String statements = "UPDATE " + TABLE_UTILIZADOR_NAME + " SET " + TABLE_UTILIZADOR_COLLUMS[1] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[1] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[2] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[2] +
	            " ," + TABLE_UTILIZADOR_COLLUMS[3] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[3] +
	            //" ," + TABLE_UTILIZADOR_COLLUMS[4] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[4] +
	            " WHERE " + TABLE_UTILIZADOR_COLLUMS[0] + " = sp_" + TABLE_UTILIZADOR_COLLUMS[0];

	    createStoredProcedure(connection, SP_ALTERAR_USER_NAME, statements, args);

	}

	public static void createSPEliminar_User(Connection connection) throws SQLException {

		String args = "IN sp_Param VARCHAR(100)" + ", IN sp_ParamValue VARCHAR(100)" + ", IN sp_NomeUtilizador VARCHAR(100)" ;
	    String statements = "DELETE FROM " + TABLE_UTILIZADOR_NAME + " WHERE 'sp_Param' = sp_ParamValue; DROP USER sp_NomeUtilizador";

	    createStoredProcedure(connection, SP_ELIMINAR_USER_NAME, statements, args);
	}
}
