import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

class conexionSql {
	static  String usuario = "programador";
	static  String password = "05641204262000";
	static  String baseDeDatos = "ClaseSql";
	static  String host = "LAPTOP-OBEL3V1L";
	
	public conexionSql(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException e){
			System.out.println("Error al cargar la clase Del Driver\n");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()throws  SQLException{
		String url = "jdbc:sqlserver://"+host+":1433;databaseName="+baseDeDatos+";";
		System.out.println(url);
		return DriverManager.getConnection(url,usuario,password); 
	}
	
	public static void cerrar(ResultSet rs){
		try{
			rs.close();
		}catch(Exception ex){
			
		}
	}
	
	public static void cerrar(Statement st){
		try{
			st.close();
		}catch(Exception ex){
		}
	}
	public static void  cerrar(Connection con){
		try{
			con.close();
		}catch(Exception ex){
		}
	}
	
}
