package download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class download extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.setContentType("text/html;charset=utf-8");
		
		//filepath
		String filePath = "D:\\我.jpg";
		
		try(
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
				BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
				
			) {
			
			long fileLength = new File(filePath).length();
			resp.setHeader("Content-disposition", "attachment; filename=" + new String(new File(filePath).getName().getBytes("utf-8"), "ISO8859-1"));
			resp.setHeader("Content-Length", String.valueOf(fileLength));
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
