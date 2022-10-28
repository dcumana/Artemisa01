package com.mateo.artemisa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mateo.artemisa.app.fachadaimp.Productoimp;
import com.mateo.artemisa.app.fachadaimp.Usuarioimp;
import com.mateo.artemisa.app.modelo.Productos;
import com.mateo.artemisa.app.modelo.Usuarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import java.util.Random;

/**
 * Servlet implementation class Reportes
 */
@WebServlet("/Reportes")
public class Reportes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reportes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");

		// creating a row object
		XSSFRow row;

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		
		Productoimp producto = new Productoimp();
		List<Productos> productos = producto.obtenerTodos();

		data.put("1", new Object[] { "ID", "COD", "Nombre", "Stock"});
		
		Productos p;
		for (int i = 0; i < productos.size(); i++) {
			p = productos.get(i);
			
			data.put(String.valueOf(i + 2), new Object[] { p.getIdProducto(), p.getCodProducto(), p.getNombreProducto(), p.getStockProducto() });
		}

		Set<String> keyid = data.keySet();

		int rowid = 0;

		// writing the data into the sheets...

		for (String key : keyid) {

			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = data.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue(obj.toString());
			}
		}

		final Random aleatorio = new Random();

		String archivo = "reporte-" + 10000 + aleatorio.nextInt(99999) + ".xlsx";
		final String nombreArchivo = System.getProperty("user.home") + File.separator + archivo; 
		File archivoNuevo = new File(nombreArchivo);
		FileOutputStream out = new FileOutputStream(archivoNuevo);

		workbook.write(out);
		workbook.close();
		out.close();
		
		downloadFile(archivoNuevo, response);
		
	}	

	private void downloadFile(File file, HttpServletResponse response) {

		try {
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("content-disposition", "attachment; filename=reporte.xlsx");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "no-store");
			response.addHeader("Cache-Control", "max-age=0");
			FileInputStream fin = null;
			try {
				System.out.println(file.exists());
				fin = new FileInputStream(file);
				System.out.println(file.getTotalSpace());
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			}
			
			final int size = 1024;
			try {
				response.setContentLength(fin.available());
				final byte[] buffer = new byte[size];
				ServletOutputStream outputStream = null;

				outputStream = response.getOutputStream();
				int length = 0;
				while ((length = fin.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
					System.out.println("--- data --- " + length);
				}
				
				fin.close();
				
				outputStream.flush();
				outputStream.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}

	}

}
