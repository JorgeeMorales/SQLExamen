package com.example.sqlexamen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerCliente {
    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnMostrar;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrimerApellido;

    @FXML
    private TextField txtSegundoApellido;

    public void guardar() throws SQLException {
        lblResultado.setText("Los datos se estan guardando o hubo un error");
        String sql="INSERT INTO cliente (idCliente, nombre, primerApellido, segundoApellido, correo, domicilio) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        ConexionSQL connMySQL=new ConexionSQL();
        Connection conn=connMySQL.open();
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(txtId.getText()));
        pstm.setString(2, txtNombre.getText());
        pstm.setString(3, txtPrimerApellido.getText());
        pstm.setString(4, txtSegundoApellido.getText());
        pstm.setString(5, txtCorreo.getText());
        pstm.setString(6, txtDomicilio.getText());


        pstm.executeUpdate();
        pstm.close();
        connMySQL.close();
        lblResultado.setText("Se guardo con exito.");
        clean();
    }

    public void actualizar() throws SQLException{
        lblResultado.setText("Los datos se estan actualizando o hubo un error.");
        String sql="UPDATE cliente" +
                " SET nombre=?, primerApellido=?, segundoApellido=?, correo=?, domicilio=?" +
                " WHERE idCliente=?;";
        ConexionSQL connMySQL=new ConexionSQL();
        Connection conn= connMySQL.open();
        PreparedStatement pstm= conn.prepareStatement(sql);
        pstm.setString(1, txtNombre.getText());
        pstm.setString(2, txtPrimerApellido.getText());
        pstm.setString(3, txtSegundoApellido.getText());
        pstm.setString(4, txtCorreo.getText());
        pstm.setString(5,txtDomicilio.getText());
        pstm.setInt(6, Integer.parseInt(txtId.getText()));
        pstm.executeUpdate();
        pstm.close();
        connMySQL.close();
        lblResultado.setText("Se actualizaron con exito");
        clean();
    }

    public void mostrar() throws SQLException{
        lblResultado.setText("Los datos se estan consultando o hubo un error.");
        String sql="SELECT * FROM cliente;";
        ConexionSQL connMySQL=new ConexionSQL();
        Connection conn= connMySQL.open();
        PreparedStatement pstm= conn.prepareStatement(sql);
        ResultSet rs= pstm.executeQuery();
        Cliente cliente=new Cliente();
        while(rs.next()){
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setPrimerApellido(rs.getString("primerApellido"));
            cliente.setSegundoApellido(rs.getString("segundoApellido"));
            cliente.setCorreo(rs.getString("correo"));
            cliente.setDomicilio(rs.getString("domicilio"));

            System.out.println("idCliente: "+cliente.getIdCliente());
            System.out.println("nombre: "+cliente.getNombre());
            System.out.println("primerApellido: "+cliente.getPrimerApellido());
            System.out.println("segundoApellido: "+cliente.getSegundoApellido());
            System.out.println("correo: "+cliente.getCorreo());
            System.out.println("domicilio: "+cliente.getDomicilio());
        }
        rs.close();
        pstm.close();
        connMySQL.close();
        lblResultado.setText("Fueron mostrados en la consola.");
        clean();
    }

    public void eliminar() throws SQLException{
        lblResultado.setText("El registro se esta eliminando o hubo un error");
        String sql="DELETE FROM cliente WHERE idCliente=?;";
        ConexionSQL connMySQL=new ConexionSQL();
        Connection conn=connMySQL.open();
        PreparedStatement pstm= conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(txtId.getText()));
        pstm.executeUpdate();
        pstm.close();
        connMySQL.close();
        lblResultado.setText("Se elimino con exito.");
        clean();
    }

    public void clean(){
        txtId.setText("");
        txtDomicilio.setText("");
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtCorreo.setText("");
    }

}
