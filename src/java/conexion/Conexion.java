package conexion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class Conexion {
	//Definicion de variables
    private DataSource dataSource;
    private ResultSet resultado;
    private Statement enunciado;
    private Connection conexion;
    
    //******************************************************************
    // Definicion de metodo singleton
    //******************************************************************
    private static Conexion instancia;
	public static Conexion getInstancia() {
		if(instancia==null) {
			instancia = new Conexion();
		}
		return instancia;
	}
	
    //******************************************************************
    // Obtiene propiedades de conexion a base de datos
    //******************************************************************
    public Conexion() {
    	Properties propiedades = new Properties();
		try {
			System.out.println(getClass().getResource("cntPropiedades.properties").getFile());
			propiedades.load(new FileInputStream("C:\\Users\\Estudiante\\Documents\\NetBeansProjects\\Sistema-Educacion-Especial-JSF\\src\\java\\conexion\\cntPropiedades.properties"));
                        //propiedades.load(new FileInputStream(getClass().getResource("cntPropiedades.properties").getFile()));
			dataSource = BasicDataSourceFactory.createDataSource(propiedades);
		}catch(FileNotFoundException error) {
			error.printStackTrace();
			System.out.println("ERROR.- No se encontro el archivo...");
		}catch(IOException error) {
			error.printStackTrace();
			System.out.println("ERROR.- Archivo de propiedades no es correcto...");
		}catch(Exception error) {
			error.printStackTrace();
			System.out.println("ERROR.- Ocurrio un error, comuniquese con el proveedor...");
		}
    }
	
    //******************************************************************
    // Ejecuta sentencia a nivel de operacion transaccional
    //******************************************************************
    public boolean ejecutarSentencia(String sentencia) {
        conexion = null;
        try {
            conexion = dataSource.getConnection();
        	enunciado = conexion.createStatement();
            enunciado.execute(sentencia);
        }catch (SQLException e) {
            System.out.println("Error al ejecutar la sentencia: " + sentencia + "\n" + e.getStackTrace());
            System.out.println("Message:"+e.getMessage()+" Causa:"+e.getCause()+" SQLSlate:"+e.getSQLState());
            return false;
        }finally{
            liberarConexion();
        }
        return true;
    }
    
    //******************************************************************
    // Realiza consultas a la base de datos 
    //******************************************************************
	public ResultSet hacerConsulta(String consulta) {
		resultado = null;
		conexion = null;
		try {
			conexion = dataSource.getConnection();
			enunciado = conexion.createStatement();
			resultado = enunciado.executeQuery(consulta);
		}catch(SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + consulta);
			System.out.println("Message:"+e.getMessage()+" Causa:"+e.getCause()+" SQLSlate:"+e.getSQLState());
		}
		return resultado;
	}
	
	//******************************************************************
    // Realiza consultas a la base de datos 
    //******************************************************************
	public int ejecutarSentenciaConRetorno(String consulta){
		int id = 0;
		try {
			conexion = dataSource.getConnection();
			enunciado = conexion.createStatement();
			enunciado.executeUpdate(consulta,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = enunciado.getGeneratedKeys();
			while(rs!=null && rs.next()){
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al ejecutar la consulta: " + consulta);
			System.out.println("Message:"+e.getMessage()+" Causa:"+e.getCause()+" SQLSlate:"+e.getSQLState());
		}finally{
                    liberarConexion();
                }
		return id;
	}
	
    //******************************************************************
    // Obtiene pool de conexion
    //******************************************************************
	public Connection obtenerPool(){
		return conexion;
	}
	
    //******************************************************************
    // Libera pool de conexion para nuevos usos
    //******************************************************************
    public void liberaConexion(Connection conexion) {
        try {
            if (null != conexion) {
                conexion.close();
            }
        } catch (SQLException e) {
        	System.out.println("ERROR. - Al liberar la conexion\nMas informacion:\n");
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
    }
    
        //******************************************************************
    // Libera pool de conexion para nuevos usos
    //******************************************************************
    public void liberarConexion() {
        try {
            if (null != conexion) {
                resultado.close();
                enunciado.close();
                conexion.close();
            }
        } catch (SQLException e) {
        	System.out.println("ERROR. - Al liberar la conexion\nMas informacion:\n");
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
    }
    
    //**********************************************************************************************
    // Ejecuta operaciones transaccionales en modo procedimiento almacenado precompilado permanente
    //**********************************************************************************************
    public CallableStatement getCallableStatement(String rcvCallable) {
    	Connection conDB = null;
    	CallableStatement callStatementReturn = null;
    	try {
    		conDB = dataSource.getConnection();
    		callStatementReturn = conDB.prepareCall(rcvCallable);
    	} catch (SQLException e){
    		e.printStackTrace();
    	}
    	return callStatementReturn;
    }
    
    //***********************************************************************************
    // Ejecuta operaciones transaccionales en modo procedimiento almacenado precompilado
    //***********************************************************************************
	public PreparedStatement getConsultaPreparada(String consulta) {
		Connection temp = null;
		try {
			temp = dataSource.getConnection();
			return temp.prepareStatement(consulta);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			if(temp!=null){
				try {
					temp.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
}