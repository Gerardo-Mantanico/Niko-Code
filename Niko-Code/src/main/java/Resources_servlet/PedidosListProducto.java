/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Resources_servlet;

import BaseDatos.EditarDB;
import BaseDatos.GuardarDatosEntrada;
import clases.Pedido;
import clases.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resources.ConexionBase;
import resources.Estado;

/**
 *
 * @author HP
 */
@WebServlet(name = "PedidosListProducto", urlPatterns = {"/PedidosListProducto"})
public class PedidosListProducto extends HttpServlet {
    
    ConexionBase con=new ConexionBase();
    Statement stamente;
    ResultSet r;
    ArrayList list= new ArrayList();
    Pedido pedido=new Pedido();
    float total=0;
    GuardarDatosEntrada base=new GuardarDatosEntrada();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PedidosListProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PedidosListProducto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu=request.getParameter("button");
        switch (menu) {
            case  "agregar":
                pedido.setTienda( Integer.valueOf(request.getParameter("tienda")) );
                pedido.setCodigoUsuario( Integer.parseInt(request.getParameter("id")) );
                pedido.setFecha(Date.valueOf(request.getParameter("fecha")));
                pedido.setEstado(Estado.SOLICITADO.name());
                Producto pro=new Producto();
                String p=request.getParameter("producto");
                String c= request.getParameter("cantidad");
                System.out.println( "cantiad.  " +c  +"  id:"+p);
                pro=buscarproducto(Integer.valueOf(p));
                pro.setExistencia(Integer.valueOf(c));
                pro.setCosto(pro.getPrecio()* pro.getExistencia());
                this.sumartotal((float) pro.getCosto());
                list.add(pro);
                list=productoRepedios(list);
                request.setAttribute("fecha", String.valueOf(pedido.getFecha()));
                request.setAttribute("codigoUsuario", String.valueOf(pedido.getCodigoUsuario()));
                request.setAttribute("tienda", String.valueOf(pedido.getTienda()));
                request.setAttribute("produ", list);
                request.setAttribute("total", String.valueOf(total));
                request.setAttribute("lista", this.lista("catalogue"));
                request.getRequestDispatcher("Ventana_Tienda/Tienda.jsp").forward(request, response);
            break;
           
            case "pedido":
                pedido.setTotal((double) total );
                base.pedido(pedido);
                for(Object objeto: list){
                    Producto producto=new Producto();
                    producto= (Producto)objeto;
                    base.listapedido(producto, base.IdMax());
                }
                total=0;
                list.clear();
                request.setAttribute("fecha", String.valueOf(pedido.getFecha()));
                request.setAttribute("codigoUsuario", String.valueOf(pedido.getCodigoUsuario()));
                request.setAttribute("tienda", String.valueOf(pedido.getTienda()));
                request.setAttribute("produ", list);
                //request.setAttribute("total", String.valueOf(total));
                request.setAttribute("lista", this.lista("catalogue"));
                request.getRequestDispatcher("Ventana_Tienda/Tienda.jsp").forward(request, response);
                
            break;
            
            
            default:
                
        }
        
        
         
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
   
    public Producto buscarproducto(int codigo){
        Producto producto = new Producto();
        try {
            String query="select* from catalogue where _code="+codigo;
            stamente = con.conexion().createStatement();
            r = stamente.executeQuery(query);
            while (r.next()) {
                producto.setCodigo(r.getInt(1));
                producto.setNombre(r.getString(2));
                producto.setCosto(r.getDouble(4));
                producto.setPrecio(r.getDouble(3));
                producto.setExistencia(r.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosListProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidosListProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }
     public ArrayList  lista(String query){
            EditarDB db=new EditarDB();
            ArrayList list = new ArrayList();
            list=db.listUsuarioTienda(query);
        return list;
     }
     public void sumartotal( float subtotal){
         this.total=total+subtotal;
     }
     
    public ArrayList productoRepedios(List<Producto> list){
        // Crear un Map para almacenar los objetos y sus sumas
        Map<Integer, Producto> mapProductos = new HashMap<>();
        for (Producto producto : list) {
            Integer id = producto.getCodigo();
            if (mapProductos.containsKey(id)) {
                // Si ya existe el objeto, sumar su valor
                Producto p = mapProductos.get(id);
                p.setExistencia(p.getExistencia()+producto.getExistencia());
                p.setCosto(p.getCosto()+producto.getCosto());
            } else {
                // Si no existe el objeto, a??adirlo al Map
                mapProductos.put(id, producto);
            }
        }
        // Crear un nuevo ArrayList a partir de los valores del Map
        List<Producto> productosSumados = new ArrayList<>(mapProductos.values());
        return (ArrayList) productosSumados;
    }
}
