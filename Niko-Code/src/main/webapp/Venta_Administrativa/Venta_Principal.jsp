<%-- 
    Document   : Venta_Principal
    Created on : 22/02/2023, 22:56:48
    Author     : dell
--%>

<%@page import="clases.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BaseDatos.EditarDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="MenuAdmin.jsp" %>

<!DOCTYPE html>
    <html lang="es" class="translated-ltr">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="css/Ventana.css">
             <link rel="stylesheet" href="../css/Ventana.css">
            <title>Create and delete user</title>
        </head>
        <body>
            <div class="container-createDelete">
                <div class="container">
                    <div class="logo">
                        <img src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=1400&q=60" alt="woman">
                    </div>
                    <div class="container-form">
                        <h1 class="title"> Crear usuario administrador</h1>
                        <form method="POST" action="ServletCreate" class="form">
                            <label for="tex" class="label">Nombre:</label>
                            <input type="text" name="name" class="input" placeholder="GerardoTax" required>
                            <label for="tex" class="label">Nombre de usuario:</label>
                            <input type="text"  value="wi" name="user_name" class="input" placeholder="Gtax419holis" required>
                            <label for="password" class="label" >Contraseña:</label>
                            <input type="password" name="password" class="input" placeholder="*******" required>
                            <c:forEach items="${msj}" var="mensaje">
                                <h5> <c:out value="${mensaje}" ></c:out></h5>
                            </c:forEach>
                            <button class="button" value="admin" name="button" >Guardar</button>
                        </form>
                    </div>
                </div>
                <div class="container-list">
                    <table>
                        <thead>
                          <tr>
                            <th>Nombre</th>
                            <th>Nombre de usuario</th>
                            <th>contraseña</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>       
                            <c:forEach items="${lista}" var="usuario">
                                <tr>
                                    <td> <c:out value="${usuario.nombre}" ></c:out></td>
                                    <td> <c:out value="${usuario.nombreUsuario}" ></c:out></td>     
                                <form action="EditarUsuarios" method="POST">
                                    <th scope="row"> <button value="Editar"  class="button-secundary">Editar </button></th>
                                    <th scope="row"> <button   value="Desactivar" name="button"  class="button-secundary">Desactivar </button></th>
                                     <th scope="row"> <button   value="Activar" name="button"  class="button-secundary">Activar </button></th>
                                    <input value="<c:out value="${usuario.codigo}" ></c:out>" name="IdUsuario" type="hidden">
                                </form>
                        </c:forEach>
                        </tr>
                        </tbody>
                    </table>     
                </div>
            </div>
        </body>
    </html>
