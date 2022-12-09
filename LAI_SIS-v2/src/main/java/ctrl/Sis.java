package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentBean;
import model.SisModel;

/**
 * Servlet implementation class Sis
 */
@WebServlet({"/Sis", "/Sis/*"})
public class Sis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sis() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	
    }
    
    public void init(ServletConfig config) throws ServletException { 
    	 super.init(config); 
//    	 ServletContext context = getServletContext(); 
//    	 
//    	 SisModel model = SisModel.getInstance();
//    	 context.setAttribute("sisModel", model); 
    	 try { 
    		 SisModel model = SisModel.getInstance(); 
    		 this.getServletContext().setAttribute("sisModel", model);
    		 
    	 } catch (ClassNotFoundException e) { 
    		 throw new ServletException("Class Not Found" + e); 
    	 } 
    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 try { 
//             StudentDAO sd=new StudentDAO(); 
//
//
//             sd.readAndPrintTableToConsole();
////			 EnrollmentDAO ed = new EnrollmentDAO();
////			 ed.retrieve();
//			 
//		 }catch (Exception e) { 
//			 e.printStackTrace(); 
//		 }
		Writer resOut = response.getWriter();
		ServletContext context = request.getServletContext();
		SisModel sisModel = (SisModel) context.getAttribute("sisModel");
		String surname = request.getParameter("surname");
		String minimumCreditTaken = request.getParameter("minimumCreditTaken");
		
		String error = "";
		Map<String, StudentBean> students = null;
		try {
			students = sisModel.retriveStudent(surname, minimumCreditTaken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		request.getSession().setAttribute("students", students);
		request.getSession().setAttribute("surname", surname);
		request.getSession().setAttribute("minimumCreditTaken", minimumCreditTaken);
		
		if (request.getPathInfo() != null && request.getPathInfo().indexOf("Ajax") >= 0) {
			response.setContentType("application/json");
			System.out.println("Ajax call");//this is for testing at server side...
			 //here we return json data note the escape characters \”
//			resOut.append("{\"sur\":");
//			resOut.append(surname);
//			resOut.append(",\"min\":");
//			resOut.append(minimumCreditTaken);
//			for (int i = 0; i < students.size(); i++) {
//				resOut.append(",\"stu\":");
//				resOut.append(students.get(surname).getName());
//				resOut.append(",\"taken\":");
//				resOut.append(Integer.toString(students.get(surname).getCredit_taken()));
//				resOut.append(",\"grad\":");
//				resOut.append(Integer.toString(students.get(surname).getCredit_graduate()));
//			}
			resOut.append("[");
			int count = 1;
			for (Map.Entry<String, StudentBean> mp: students.entrySet()) {
				resOut.append("{\"Student Name\":");
				resOut.append("\"" + students.get(mp.getKey()).getName() + "\"");
				resOut.append(",\"Credit Taken\":");
				resOut.append(Integer.toString(students.get(mp.getKey()).getCredit_taken()));
				resOut.append(",\"Credits to Graduate\":");
				resOut.append(Integer.toString(students.get(mp.getKey()).getCredit_graduate()));
				resOut.append("}");
				if (count != students.size()) {
					resOut.append(",");
				}
				count++;
			}
			resOut.append("]");
			resOut.flush();
			}
//		else {
//			if (request.getParameter("out") != null 
//					&& request.getParameter("out").equals("text")) { 
//				//return the text, as in Lab 1 ( here I have a private method, textView, that creates the response)
//				response.setContentType("text/plain");
//				if (error != null) {
//					resOut.write(error + "\n");
//				}
//				else {
//					resOut.write("Surname: " + surname + "\n");
//					resOut.write("Minimum Credit Taken: " + minimumCreditTaken + "\n");
//					for (Map.Entry<String, StudentBean> mp: students.entrySet()) {
//						resOut.write("Student Name: ");
//						resOut.write(students.get(mp.getKey()).getName() + "\n");
//						resOut.write("Credit Taken: ");
//						resOut.write(Integer.toString(students.get(mp.getKey()).getCredit_taken()) + "\n");
//						resOut.write("Credits to Graduate: ");
//						resOut.write(Integer.toString(students.get(mp.getKey()).getCredit_graduate()) + "\n");
//					}
//				}
//			}
//			if (request.getParameter("out") == null)  { 
//				//return the text, as in Lab 1 ( here I have a private method, textView, that creates the response)
//				response.setContentType("text/plain");
//				if (error != null) {
//					resOut.write(error + "\n");
//				}
//				else {
//					resOut.write("Surname: " + surname + "\n");
//					resOut.write("Minimum Credit Taken: " + minimumCreditTaken + "\n");
//					for (Map.Entry<String, StudentBean> mp: students.entrySet()) {
//						resOut.write("Student Name: ");
//						resOut.write(students.get(mp.getKey()).getName() + "\n");
//						resOut.write("Credit Taken: ");
//						resOut.write(Integer.toString(students.get(mp.getKey()).getCredit_taken()) + "\n");
//						resOut.write("Credits to Graduate: ");
//						resOut.write(Integer.toString(students.get(mp.getKey()).getCredit_graduate()) + "\n");
//					}
//				}
//			} 
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
