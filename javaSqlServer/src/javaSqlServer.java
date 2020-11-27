import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class javaSqlServer extends JFrame implements ActionListener {
	 
	String consultaSql= "SELECT matricula,nombres,paterno,materno FROM DatosPersonales";
	
	//Apuntar a la conexion
	Connection conn = null;
	//Para ejecutar la operacion
	Statement estSQL1;
	//Para guardar los resultados de la operacon SELECT
	ResultSet rs;
	
	conexionSql con = new conexionSql();
	
	javaSqlServer(String titulo, int ancho, int alto){
		super(titulo);
		this.setSize(ancho,alto);
		setLocationRelativeTo(null);
		JButton btnConnectar = new JButton("Conectar");
		btnConnectar.addActionListener(this);
		
		add(Box.createVerticalStrut(300));
		add(btnConnectar);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//Escuchador
	public void actionPerformed(ActionEvent evento){
		try{
			if(ConectarServidor()){
				System.out.println("Matricula\t\t\t Nombres Completo");
				rs = estSQL1.executeQuery(consultaSql);
				while(rs.next())
					System.out.println(rs.getString("matricula").trim()+
						"\t\t\t\t" + rs.getString("nombres").trim()+
						"\t\t\t\t" + rs.getString("materno").trim());
					
					//Cerrar  los objetos de manejo bd
					conexionSql.cerrar(rs);
					conexionSql.cerrar(estSQL1);
					conexionSql.cerrar(conn);
					//CrearConexion.cerrar(rs);
					//CrearConexion.cerrar(estSQL1);
					//CrearConexion.cerrar(conn);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error al ejecutar");
			e.printStackTrace();
		}
	}
	
	//Devuelve true en caso de que si se pudo conectar
	public boolean ConectarServidor(){
		try{
			conn = con.getConnection();
			
			//Comprobar si hay una si hay una referencia valida
			if(conn != null){
				//
				JOptionPane.showMessageDialog(null,"ConexionEstablecida");
				estSQL1 = conn.createStatement();
				return true;
			}
			else
				return false;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Error en la conexion");
			e.printStackTrace();
			return false;
		}
	}
	
    public static void main(String[] args) throws Exception{
    	javaSqlServer ejemplo = new javaSqlServer("Ejemplo de conexion con sql Server",400,300);
    }
}
