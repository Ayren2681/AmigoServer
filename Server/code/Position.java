package code;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.CvMat;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_legacy.CvEM;
import org.bytedeco.javacpp.opencv_legacy.CvEMParams;
import org.bytedeco.javacpp.helper.opencv_core.AbstractCvMat;



public class Position extends Thread{
	private JButton zone0, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8;
	private JTextArea textArea;
	static Boolean flag=true;
	static int start=-1;
	static CvEMParams params=new CvEMParams();
	
	public Position( JButton _zone0, JButton _zone1, JButton _zone2, JButton _zone3, JButton _zone4, 
			JButton _zone5, JButton _zone6, JButton _zone7, JButton _zone8, JTextArea _textArea ){
		zone0=_zone0;
		zone1=_zone1;
		zone2=_zone2;
		zone3=_zone3;
		zone4=_zone4;
		zone5=_zone5;
		zone6=_zone6;
		zone7=_zone7;
		zone8=_zone8;
		textArea=_textArea;
	}
	@Override
	public void run(){

		int i, j, cnt=0, avg=0;
		
		CvEM [] gmm_model=new CvEM[9];
		CvMat[] trainData=new CvMat[9];
		
		for( i=0; i<9; i++ ) trainData[i]=AbstractCvMat.create(248, 6, opencv_core.CV_32FC1);
		
		try{
			while( cnt<9 ){
				FileReader fr = new FileReader("C://CamTest//"+(cnt+1)+".txt");
				BufferedReader br = new BufferedReader(fr);
				String ln;
				for( i=0; i<248; i++ ){
					ln=br.readLine();
					String[] strdblevel = ln.split("  ");
					int[] intdblevel=new int[strdblevel.length];
					for( j=0; j<strdblevel.length; j++ )
						intdblevel[j]=Integer.parseInt(strdblevel[j]);
					for( j=0; j<6; j++ ) 
						trainData[cnt].put(i, j, intdblevel[j]);
				}
				
				fr.close();
				br.close();
				cnt++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		params.covs();
		params.means();
		params.weights();
		params.probs();
		params.nclusters(3);
		params.cov_mat_type(CvEM.COV_MAT_SPHERICAL);
		params.start_step(CvEM.START_AUTO_STEP);
		params.term_crit().max_iter(300);    // ����N�̰�����
		params.term_crit().epsilon(0.001);
		params.term_crit().type(opencv_core.CV_TERMCRIT_ITER|opencv_core.CV_TERMCRIT_EPS);
		
		for( i=0; i<9; i++ ) gmm_model[i]=new CvEM();
		
		for( i=0; i<9; i++ ) gmm_model[i].train(trainData[i], null, params, null);
		while(true){
			while(flag){
				try{
					CvMat matTestFeature=AbstractCvMat.create(1, 6, opencv_core.CV_32FC1);
					
					int[] level=new int[]{0, 0, 0, 0, 0, 0};
					while(avg<10){
						int[] _level=new int[]{-100, -100, -100, -100, -100, -100};
								
						FileReader posfr = new FileReader("C://CamTest//Positioning.txt");
						BufferedReader posbr = new BufferedReader(posfr);
						synchronized(posbr){
							String _str=posbr.readLine();
							if( _str.indexOf("SSID")>-1 ){
								String[] wifi = _str.split("BSSID: ");
								
								FileReader macfr = new FileReader("C://CamTest//Wifimac.txt");
								BufferedReader macbr = new BufferedReader(macfr);
								String str=macbr.readLine();
								String[] mac = str.split("__");
								
								macbr.close();
								posbr.close();
								
								for( i=1; i<wifi.length; i++){
									for( j=0; j<mac.length; j++ ){
										if( wifi[i].indexOf(mac[j])!=-1 )
											_level[j]=Integer.parseInt(wifi[i].substring(
													wifi[i].indexOf("level: -")+7, wifi[i].indexOf("level: -")+10));
									}
								}
								for( i=0; i<mac.length; i++ ) level[i]+=_level[i];
								Thread.sleep(50);
							}
						}
						avg++;
					}
					avg=0;
					for( i=0; i<6; i++ ){ 
						level[i]/=10;
						matTestFeature.put(0, i, level[i]);
					}
					
					FileReader fr = new FileReader("C://CamTest//Wifimac.txt");
					BufferedReader br = new BufferedReader(fr);
					String str=br.readLine();
					String[] mac = str.split("__");
					String info="";
					for( i=0; i<mac.length; i++ ) info+=mac[i]+": "+level[i]+"\n";
					textArea.setText(info);
					br.close();
					
					Mat TestFeature=new Mat(matTestFeature);
					
					double result=gmm_model[0].calcLikelihood(TestFeature);
					int zone=0;
					for( i=1; i<gmm_model.length; i++){
						if( gmm_model[i].calcLikelihood(TestFeature)>result ){
							result = gmm_model[i].calcLikelihood(TestFeature);
							zone=i;
						}
					}
					
					switch(zone){
					case 0:
						start=2;
						zone2.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 1: 
						start=3;
						zone3.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 2: 
						start=8;
						zone8.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone2.setBackground(Color.white);
						break;
					case 3: 
						start=1;
						zone1.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 4: 
						start=4;
						zone4.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 5: 
						start=7;
						zone7.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 6: 
						start=0;
						zone0.setBackground(Color.orange);
						zone2.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 7: 
						start=5;
						zone5.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone6.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					case 8: 
						start=6;
						zone6.setBackground(Color.orange);
						zone0.setBackground(Color.white);
						zone1.setBackground(Color.white);
						zone3.setBackground(Color.white);
						zone4.setBackground(Color.white);
						zone5.setBackground(Color.white);
						zone2.setBackground(Color.white);
						zone7.setBackground(Color.white);
						zone8.setBackground(Color.white);
						break;
					}
					Thread.sleep(50);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}