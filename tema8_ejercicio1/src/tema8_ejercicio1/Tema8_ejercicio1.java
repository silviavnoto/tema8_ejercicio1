package tema8_ejercicio1;
import java.sql.*;
import java.util.Scanner;
public class Tema8_ejercicio1 {
	public static void mostrarMenu() {
			System.out.println("Seleccione opción:");
			System.out.println("1. Mostrar todo el contenido de la tabla");
			System.out.println("2. Mostrar los nombres de las personas mayores de edad");
			System.out.println("3. Mostrar la edad de una persona con un id tecleado por el usuario");
			System.out.println("4. Insertar una nueva persona, pidiéndole id, nombre y edad al usuario");
			System.out.println("5. Borrar la persona con un id tecleado por el usuario");
			System.out.println("6. Actualizar la edad de una persona con un id tecleado por el usuario");
			System.out.println("0. Salir");
			}
	public static void main(String[] args) {
		String url = "jdbc:mysql://127.0.0.1:3307/prueba";
		String user = "alumno";
		String password = "alumno";
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			
			Scanner s = new Scanner(System.in);
			int opcion;
			
			do {
				System.out.println("----------");
				mostrarMenu();
				opcion = s.nextInt();
				switch (opcion) {
				
				
				case 1:
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM persona");
					while (rs.next()) {
						int idp = rs.getInt("id");
						String nomp = rs.getString("nombre");
						int edadp = rs.getInt("edad");
						System.out.println("ID: " + idp + ", Nombre: " + nomp + " Edad: " + edadp);
				
			}
					rs.close();
					stmt.close();
					break;	
					
				case 2:
					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT nombre FROM persona WHERE edad>=18");
					while (rs2.next()) {
						
						String nomp = rs2.getString("nombre");
				
						System.out.println("Nombre: " + nomp);
			
			}
					rs2.close();
					stmt2.close();
					break;	
		
					
				case 3:
					PreparedStatement sel_pstmt = con.prepareStatement("SELECT * FROM persona WHERE id>?");
		
					Scanner s1= new Scanner(System.in);
					System.out.println("Ingrese el id de la persona: ");
					int idTeclado = s1.nextInt();
					
					sel_pstmt.setInt(1, idTeclado);
					ResultSet rs_sel = sel_pstmt.executeQuery();
					
					while (rs_sel.next()) {
						
						int idp = rs_sel.getInt("id");
						String nomp = rs_sel.getString("nombre");
						int edadp =  rs_sel.getInt("Edad");
						
						System.out.println("Nombre: " + nomp + " Edad: " + edadp);
			
			}
					rs_sel.close();
					sel_pstmt.close();
					break;	
					
				case 4:
					PreparedStatement ins_pstmt = con.prepareStatement("INSERT INTO persona (id, nombre, edad) VALUES (?, ?, ?)");
		
					Scanner s4= new Scanner(System.in);
					System.out.println("Ingrese el id de la persona: ");
					int idTeclado4 = s4.nextInt();
					System.out.println("Ingrese el nombre de la persona: ");
					String nombreTeclado4 = s4.next();
					System.out.println("Ingrese la edad de la persona: ");
					int edadTeclado4 = s4.nextInt();
					
					ins_pstmt.setInt(1, idTeclado4); //Primer “?”
					ins_pstmt.setString(2, nombreTeclado4); //Segundo “?”
					ins_pstmt.setInt(3, edadTeclado4); //Tercer “?”
					
					int rowsInserted = ins_pstmt.executeUpdate();
					ins_pstmt.close();
					
					break;	
			
					
				case 5:
					PreparedStatement dele_pstmt = con.prepareStatement("DELETE FROM persona WHERE id = ?");
					dele_pstmt.setInt(1, 1);
		
					Scanner s5= new Scanner(System.in);
					System.out.println("Ingrese el id de la persona a borrar: ");
					int idTeclado5 = s5.nextInt();
					
					dele_pstmt.setInt(1, idTeclado5);
					
					int rowsDeleted =dele_pstmt.executeUpdate();
					dele_pstmt.close();
					
					break;	
					
				case 6:
					PreparedStatement upd_pstmt = con.prepareStatement("UPDATE persona SET edad = ? WHERE id = ?");
		
					Scanner s6 = new Scanner(System.in);
				    System.out.println("Ingrese el nuevo valor de la edad: ");
				    int nuevaEdad = s6.nextInt();
					Scanner s7= new Scanner(System.in);
					System.out.println("Ingrese el id de la persona a actualizar edad: ");
					int idTeclado7 = s7.nextInt();
					
					upd_pstmt.setInt(1, nuevaEdad);
					upd_pstmt.setInt(2, idTeclado7);
										
					int rowsUpdated = upd_pstmt.executeUpdate();
					upd_pstmt.close();
					
					break;	
					
				case 0:
				System.out.println("Fin del programa");
				break;
				
				default:
				System.out.println("Opción no válida");
				}
				} while (opcion != 0);
			
				
			
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}}
