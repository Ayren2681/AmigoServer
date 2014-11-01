package code;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import ch.randelshofer.media.avi.AVIOutputStream;

public class AviCreator extends Thread{
	public int Header, Pictures;
	public AviCreator( int _Header, int _pics ){
		Header=_Header;
		Pictures=_pics;
	}
	public void run(){
		try {
			int i;
			SimpleDateFormat avisdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm");
			Date avidate=new Date();
			String avitime=avisdf.format(avidate);
			AVIOutputStream aviout = null;
			aviout = new AVIOutputStream( new File("D:/CamTest/avi/"+avitime+".avi"), 
					AVIOutputStream.VideoFormat.JPG );
			aviout.setVideoCompressionQuality( 1f );
	        aviout.setTimeScale(1);
	        aviout.setFrameRate(28);
	        
	        for( i=1; i<=Pictures; i++ ){
	        	aviout.writeFrame( ImageIO.read(new File( "D:/CamTest/"+Header+"_"+i+".jpeg" ) ) );
	        	File file=new File("C:/CamTest/"+Header+"_"+i+".jpeg");
	        	file.delete();
	        }
	        aviout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
