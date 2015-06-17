package mb;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

import model.Usuario;


public class ImageMB  {
	
	private String filename;
	
	public String oncapture(ServletContext context, CaptureEvent captureEvent){
		byte[] data = captureEvent.getData();
		
		filename= String.valueOf(System.nanoTime());
		
		
		
		
		
//		filename= getRandomImageName();
		String nomeArquivo = "C:"+File.separator+"syscon"+File.separator + filename+".jpeg";
//		String nomeArquivo = "/sysconImages" +File.separator + filename+".jpeg";
//		user.setImagem(nomeArquivo);
		System.out.println("nome do arquivo");
		System.out.println(nomeArquivo);
		
		System.out.println(context.getRealPath("/protected/images"));
		
		FileImageOutputStream imageOutput;
		try {
			imageOutput= new FileImageOutputStream(new File(nomeArquivo));
			System.out.println("convertendo imagens");
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			return filename;
			
		} catch (IOException e) {
			throw new FacesException("Erro ao capturar imagem", e);
			
		}
		
		
		
	}
	
	 public String getFilename() {
	        return filename;
	 }
	 
	 public String getRandomImageName() {
	        long i = (long) (Math.random() * 1000000000000000000l);
	         
	        return String.valueOf(i);
	    }


}
