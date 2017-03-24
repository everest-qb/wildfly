package tw.taichung.file;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Upload() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String returnValue="";
		if(isMultipart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletContext servletContext = getServletConfig().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				    if (item.isFormField()) {
				        processFormField(item);
				    } else {
				        processUploadedFile(item);
				    }
				}
			} catch (FileUploadException e) {				
				e.printStackTrace();
			}
			returnValue="TRUE";
		}else{
			returnValue="FALSE";
		}
		response.getWriter().append(returnValue).flush();
	}
	
	private void processFormField(FileItem item){
		String name = item.getFieldName();
	    String value = item.getString();
	}
	
	private void processUploadedFile(FileItem item){
		String fieldName = item.getFieldName();
	    String fileName = item.getName();
	    String contentType = item.getContentType();
	    boolean isInMemory = item.isInMemory();
	    long sizeInBytes = item.getSize();
	    
	    System.out.println("---------------");
	    System.out.println(fieldName);
	    System.out.println(fileName);
	    System.out.println(contentType);
	    System.out.println(isInMemory);
	    System.out.println(sizeInBytes);
	    System.out.println(item.getString());
	    //item.getHeaders().
	    //File uploadedFile = new File("");
	    //item.write(uploadedFile);
	}

}
