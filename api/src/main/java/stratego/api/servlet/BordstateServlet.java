// package stratego.api.servlet;

// import java.io.IOException;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @jakarta.servlet.annotation.WebServlet("/api/bordstate")
// public class BordstateServlet extends jakarta.servlet.http.HttpServlet {
//     private BordstateRepository BordstateRepository = new BordstateRepository();

//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         // Handle GET requests here
//         // You can call your service layer and interact with the database
//         // Send a response back to the client
//     }

//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         // Handle POST requests here
//         // You can call your service layer and interact with the database
//         // Send a response back to the client
//     }
// }

// public class GetDataServlet extends HttpServlet {
//     private DataDao dataDao = new DataDao();

//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         // Handle the GET request
//         String data = dataDao.getData();
//         // Send the retrieved data to the client
//         response.getWriter().write(data);
//     }
// }