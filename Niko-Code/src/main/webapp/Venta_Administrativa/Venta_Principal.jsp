<%-- 
    Document   : Venta_Principal
    Created on : 22/02/2023, 22:56:48
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                        <h1 class="title"> Create User</h1>
                        <form action="" class="form"> 
                            <label for="tex" class="label">Name:</label>
                            <input type="text" class="input" placeholder="GerardoTax">
                            <label for="tex" class="label">Username:</label>
                            <input type="text" class="input" placeholder="Gtax419holis">
                            <label for="password" class="label" >Password:</label>
                            <input type="password" class="input" placeholder="*******">
                            <button class="button">Create</button>
                        </form>

                    </div>
                </div>
                <div class="container-list">
                    <table>
                        <thead>
                          <tr>
                            <th>User Name </th>
                            <th>Password</th>
                            <th>Area</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                            <th scope="row"> <button class="button-secundary">Delete </button></th>
                          </tr>
                          <tr>

                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fadsfsdfsdfdsfsdfsdft</td>
                            <th scope="row"> <button class="button-secundary">Delete </button></th>
                          </tr>
                        </tbody>
                    </table>     
                </div>

            </div>
        </body>
    </html>