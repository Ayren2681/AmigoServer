package code;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;


import javax.swing.JButton;
import javax.swing.JTextArea;


import code.Setting;

public class pathalgo extends Thread implements MonitorProtocol{
	boolean fin=false;
	public  DataOutputStream  out=null;
	Socket socket=null;
	double tang=0;
	vec tnow=null;
	vec tnext=null;
	static double carx=0;
	static double cary=0;
	public static int traposno=-1;
	public static int traposne=-1;
	int[] path=null;
	pathalgo(int[] _path){
		path=_path;
	}
	@Override
	public void run(){
//		int[] a={2,3,8,7,6,5,0,1,2};
//		int[] a1={2,1,0,5,6,7,8,3,2};
//		int[] b={2,1,0,5};
//		int[] c={0,1,2,3,8};
		if(Info.secpo==-1){
			inipos();
			
		}
		else{
			pathgo(path);
		  Info.secpo=path[path.length-1];
		}
	}
	public void inipos(){
		//�斗銝��鲳��函洵撟曉�  visual
		playmusico(1);
		qtvisual2 ql2=new qtvisual2();
		ql2.tochep=path[0];
		new Thread(ql2).start();
		visfin=false;
		int st=0;
		while(ql2.qtfin==false){
			try {
				Thread.sleep(1000);
				st++;
				if(st>6)break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		visfin=true;
		playmusicc(2);
		if(ql2.tang!=0&&ql2.tang!=360){
			System.out.println("start point: "+path[0]);
			pathgo(path);
			Info.secpo=path[path.length-1];
		}else{
			System.out.println("start point: not "+path[0]);
		}
	
	}
	 
	 static boolean visfin=true;
//	public void checkpath(int ang) throws InterruptedException, IOException{
//		int case2=0;
//		if(ang<15)ang+=360;
//		Thread.sleep(2000);
//		int cd=Info.comdeg;
//		
//			if(cd>=ang+15){
//				relrot(-15);
//				relrot(0);
//			}else if(cd<=ang-15){
//				relrot(15);
//				relrot(0);
//			}
//			playmusico(1);
//		qtvisual ql=new qtvisual();
//		new Thread(ql).start();
//		visfin=false;
//		try {int xxx=0;
//			while(ql.fin==false&&ql!uang==360){
//				if(xxx%10==0)System.out.println(ql.uang);
//				Thread.sleep(1000);xxx++;
//				if(xxx==370){ql.uang=0;break;}
//			}
//			visfin=true;
//			if(ql.uang<=-6&&ql.uang>-10){
//				ql.uang=-10;
//			}
//			if(ql.uang<10&&ql.uang>=6){
//				ql.uang=10;
//			}
//			if(ql.uang<-3&&ql.uang>=-5){
//				ql.uang=-6;
//			}
//			if(ql.uang<6&&ql.uang>=3){
//				ql.uang=6;
//			}
//			playmusicc(1);
//			relrot(ql.uang);
//			relrot(0);
//		} catch (NumberFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	return;
//	
//	}
	public void checkpath() throws InterruptedException, IOException{
		playmusico(1);
		qtvisual ql=new qtvisual();
		new Thread(ql).start();
		
		visfin=false;
		try {int xxx=0;
			while(ql.fin==false||(ql.uang==360||ql.uang==370)){
				System.out.println(ql.uang);
				Thread.sleep(1000);xxx++;
				if(xxx>5)break;
			}
			if(xxx==360||ql.uang==370)ql.uang=0;
			visfin=true;
//			if(ql.uang<=-6&&ql.uang>-10){
//				ql.uang=-10;
//			}
//			if(ql.uang<10&&ql.uang>=6){
//				ql.uang=10;
//			}
			if(ql.uang<-3&&ql.uang>=-5){
				ql.uang=-6;
			}
			if(ql.uang<6&&ql.uang>=3){
				ql.uang=6;
			}
			
			relrot(ql.uang);
			relrot(0);
			playmusicc(1);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return;
	
	}
	public void avoidcheckpath() throws InterruptedException{
		
		qtvisual2 ql=new qtvisual2();
	
		new Thread(ql).start();
		visfin=false;
		try {int xxx=0;
			while(ql.uang==360||ql.uang==370){
				if(xxx%10==0)System.out.println(ql.uang);
				Thread.sleep(100);xxx++;
				if(xxx==370){ql.uang=0;break;}
			}
			visfin=true;
			if(ql.uang<=-6&&ql.uang>-10){
				ql.uang=-10;
			}
			if(ql.uang<10&&ql.uang>=6){
				ql.uang=10;
			}
			if(ql.uang<-3&&ql.uang>=-5){
				ql.uang=-6;
			}
			if(ql.uang<6&&ql.uang>=3){
				ql.uang=6;
			}
			relrot(ql.uang);
			relrot(0);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return;
	
	}
	public void go(vec now,vec next) throws IOException, InterruptedException{
		if(fin==true){
			Thread.currentThread().interrupt();
			return;  }
		tnow=now;
		tnext=next;
	if(now.no==0&&next.no==1){
		carx=now.x;
	    cary=now.y;
		setrotang(0);
		checkpath();
		if(po==true){
		    reset0();
		}
		setgodis(300,250);
		
		
   }
	
	if(now.no==1&&next.no==0){
		carx=now.x;
	    cary=now.y;
			setrotang(180);	
			checkpath();
			setgodis(300,250);			
	}
	if(now.no==1&&next.no==2){
		carx=now.x;
	    cary=now.y;
		setrotang(0);
		
		checkpath();
		setgodis(300,300);
		reset0();
	}
	if(now.no==1&&next.no==4){
		carx=now.x;
	    cary=now.y;
		setrotang(90);
		checkpath();
		setgodis(300,300);
	}
	
	if(now.no==2&&next.no==1){
		carx=now.x;
	    cary=now.y;
		setrotang(180);
		checkpath();
		setgodis(300,200);
		
	}
	if(now.no==2&&next.no==3){
		System.out.println("2-3");
		carx=now.x;
	    cary=now.y;
		setrotang(90);
		checkpath();
		setgodis(360,200);

	}
	if(now.no==2&&next.no==4){
		
		setrotang(135);
		checkpath();
		setgodis(423,200);

	}
	if(now.no==3&&next.no==2){
		
		setrotang(270);
		checkpath();
		setgodis(360,200);
		
	}
	if(now.no==3&&next.no==8){//90
		System.out.println("3-8");
		carx=now.x;
	    cary=now.y;
		setrotang(90);
		
		checkpath();
		setgodis(360,200);

	}
	if(now.no==8&&next.no==3){
		
		setrotang(270);
		checkpath();
		setgodis(360,200);
		
	}
	if(now.no==4&&next.no==2){
		
		setrotang(315);
		checkpath();
		setgodis(423,200);
		
	}
	
	if(now.no==4&&next.no==1){
		
		setrotang(270);
		checkpath();
		setgodis(300,200);
	}
//	if(now.no==4&&next.no==7){
//		
//		setrotang(90);
//	
//		setgodis(540,200);
//	}
//	if(now.no==7&&next.no==4){
//		
//		setrotang(270);
//		checkpath();
//		setgodis(480,200);
//	}
	
	if(now.no==4&&next.no==5){
		
//		setrotang(168);
//		checkpath();
//		setgodis(305,200);
		setrotang(180);
		checkpath();
		setgodis(300,200);

	}
	if(now.no==5&&next.no==4){
		
//		setrotang(348);
//		checkpath();
//		setgodis(305,200);
		setrotang(0);
		checkpath();
		setgodis(300,200);

	}
	
	
	if(now.no==0&&next.no==5){
		carx=now.x;
	    cary=now.y;
		setrotang(90);
		checkpath();
		setgodis(300,200);
	}
	if(now.no==5&&next.no==0){
		carx=now.x;
	    cary=now.y;
		setrotang(270);
		
		checkpath();
		setgodis(300,200);
		
		
	}
	if(now.no==5&&next.no==6){
		
		setrotang(90);
		checkpath();
		setgodis(420,200);
		
	}
	if(now.no==6&&next.no==5){
		System.out.println("6-5");
		carx=now.x;
	    cary=now.y;
		setrotang(270);
		
		checkpath();
		setgodis(420,200);
		
	}
	if(now.no==8&&next.no==7){
		
		System.out.println("8-7");
		carx=now.x;
	    cary=now.y;
		setrotang(169);//15
		
		checkpath();
		setgodis(306,200);

		
	}
	if(now.no==7&&next.no==8){
		carx=now.x;
	    cary=now.y;
		setrotang(349);
		checkpath();
		setgodis(306,200);

	}
	if(now.no==7&&next.no==6){
		
		System.out.println("7-6");
		carx=now.x;
	    cary=now.y;
		setrotang(191);//5,11,12
		checkpath();
		setgodis(306,200);

		
	}
	if(now.no==6&&next.no==7){
		
		setrotang(11);
		checkpath();
		setgodis(306,100);
		
	}
  }
	
	public void pathgo(int[] vi){
		
		vec now =null;
		vec next=null;
		for(int i=0;i<vi.length-1;i++){
			now=new vec(vi[i]);
			next=new vec(vi[i+1]);
			
			try {
				
				if(fin==true){
					break;  
					}
				
				go(now,next);
				traposno=now.no;
				traposne=next.no;
				System.out.println( "now: "+now.no+"next: "+next.no);
				Thread.sleep(1000);
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.out.println( "travel bye!-go(now,next)");
				break;
			}
			
		}
		traposno=-1;
		traposne=-1;
		
	}
	public class qtvisual implements Runnable{
//		public String path="";
		int uang=360;
		boolean fin=false;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			byte[] buffer = new byte[100];
			  
			try {      
				 DatagramSocket ds=new DatagramSocket();
				InetAddress IPAddress = InetAddress.getByName("127.0.0.1");       
				byte[] sendData = new byte[1024];       
				byte[] receiveData = new byte[1024];       
				  
				boolean avoid=false;
				String sentence =  ""+tnow.no;   				
				sendData = sentence.getBytes();       
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1023);       
				ds.send(sendPacket); 
				
				sentence = Monitor.getpicname;    				
				sendData = sentence.getBytes();       
				 sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1023);       
				ds.send(sendPacket); 
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
				System.out.println("debug1");
				ds.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());      
				System.out.println("FROM SERVER:" + modifiedSentence);
				
				ds.receive(receivePacket);
				 modifiedSentence = new String(receivePacket.getData());      
				System.out.println("FROM SERVER:" + modifiedSentence);
				int xx=modifiedSentence.indexOf("ang");
				int zero=modifiedSentence.indexOf("ang0.");
				int nann=modifiedSentence.indexOf("angnan");
				int dan=0;
				if(zero>1||modifiedSentence.indexOf("ang0,")>1||nann>1){
					uang=0;
				}else{
					
					double tan=Double.parseDouble(modifiedSentence.substring(xx+3, xx+7));
					
					if((tan*10)%10>5){
						if(tan<0)dan=(int) (tan-1);
						else dan=(int) (tan+1);
					}	
					else dan=(int)(tan);
					System.out.println(xx+" :" + modifiedSentence.substring(xx+3, xx+7)+" dan "+dan);
					uang=dan;
					fin=true;
				}
				ds.close();
					
	
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public class qtvisual2 implements Runnable{
		int uang=360;
		double tang=-1;
		int tochep=-1;
		boolean qtfin=false;
		@Override
		public void run() {
			// TODO Auto-generated method stub
		
				// TODO Auto-generated method stub
				byte[] buffer = new byte[100];
				  
				try {      
					 DatagramSocket ds=new DatagramSocket();
					InetAddress IPAddress = InetAddress.getByName("127.0.0.1");       
					byte[] sendData = new byte[1024];       
					byte[] receiveData = new byte[1024];       
					  
					boolean avoid=true;
					String sentence =  ""+tochep;
					if(tochep==0){sentence="00";}
					sendData = sentence.getBytes();       
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1023);       
					ds.send(sendPacket); 
					
					
					
					sentence =""+Monitor.getpicname;   				
					sendData = sentence.getBytes();       
					 sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1023);       
					ds.send(sendPacket); 
					
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
					System.out.println("debug1");
					ds.receive(receivePacket);
					String modifiedSentence = new String(receivePacket.getData());      
					System.out.println("FROM SERVER:" + modifiedSentence);
					
					ds.receive(receivePacket);
					 modifiedSentence = new String(receivePacket.getData());      
					System.out.println("FROM SERVER:" + modifiedSentence);
					
					
					int xx=modifiedSentence.indexOf("ang");
					int zero=modifiedSentence.indexOf("ang0.");
					int nann=modifiedSentence.indexOf("angnan");
					int dan=0;
					if(zero>1||modifiedSentence.indexOf("ang0,")>1||nann>0){
						uang=0;
					}else{
						
						double tan=Double.parseDouble(modifiedSentence.substring(xx+3, xx+7));
						tang=tan;
						if((tan*10)%10>5){
							if(tan<0)dan=(int) (tan-1);
							else dan=(int) (tan+1);
						}	
						else dan=(int)(tan);
//						System.out.println(xx+" :" + modifiedSentence.substring(xx+3, xx+7)+" dan "+dan);
						uang=dan;
						
					}
					qtfin=true;
					ds.close();
					
		
				}catch (Exception e) {
					// TODO: handle exception
				}
			
		}
		
	}
	boolean avoflag=false;
	double si41=Math.sin(Math.PI/180*41);
	double co75=Math.cos(Math.PI/180*75);
	double co15=Math.cos(Math.PI/180*15);
	public int avoidbum2() throws IOException, InterruptedException {
		rotc=0;
		
		try {
			int x=0;
			if(Info.sensor[2]*co15<330){
				forward(0);
				playmusico(2);
				relrot(-75);
				Thread.sleep(100);
				
				while((Info.sensor[0]<350||Info.sensor[1]<300||x<1)&&fin==false){
					setgodis(20,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(75);
				playmusicc(2);
				return 1;
			}
			if(Info.sensor[3]*co15<300){
				forward(0);
				playmusico(2);
				relrot(75);
				x=0;
			
				while((Info.sensor[5]<350||Info.sensor[4]<300||x<1)&&fin==false){
					setgodis(20,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(-75);
				playmusicc(2);
				return 1;
			}
			
			if(Info.sensor[1]*si41<180||Info.sensor[2]*co75<150){
				
				forward(0);
				playmusico(2);
				relrot(-49);
				x=0;
			
				while((Info.sensor[0]<350||Info.sensor[1]<300||x<1)&&fin==false){
					setgodis(20,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(49);
				playmusicc(2);
				return 1;
			}
			if(Info.sensor[4]*si41<190||Info.sensor[3]*co75<170){
				forward(0);
				playmusico(2);
				relrot(49);
				x=0;
				while((Info.sensor[5]<350||Info.sensor[4]<300||x<1)&&fin==false){
					setgodis(20,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				Thread.sleep(1000);
				}
				forward(0);
				relrot(-49);
				playmusicc(2);
				return 1;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	public int avoidbum38() throws IOException, InterruptedException {
		try {
			int x=0;
			if(Info.sensor[2]<330){
				forward(0);
				playmusico(2);
				relrot(-75);
				x=0;	
				
				while((Info.sensor[0]<350||Info.sensor[1]<300||x<1)&&fin==false){
					System.out.println( "sensor[2]");
					setgodis(20,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				}
				forward(0);
				relrot(75);
				playmusicc(2);
				return 1;
			}
			if(Info.sensor[3]<200){
				forward(0);
				playmusico(2);
				relrot(75);
				x=0;
				
				while((Info.sensor[5]<350||Info.sensor[4]<300||x<1)&&fin==false){
					System.out.println( "sensor[3]");
					setgodis(20,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				
				}
				forward(0);
				relrot(-75);
				playmusicc(2);
				return 1;
			}
			
			if(Info.sensor[1]*si41<190||Info.sensor[2]*co75<160){
				forward(0);
				playmusico(2);
				relrot(-49);
				x=0;		
				
				while((Info.sensor[0]<350||Info.sensor[1]<300||x<1)&&fin==false){
					System.out.println( "sensor[1],sensor[2]sc");
					setgodis(25,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
			
				}
				forward(0);
				relrot(49);
				playmusicc(2);
				return 1;
			}
			if(Info.sensor[4]*si41<160||Info.sensor[3]*co75<150){
				forward(0);
				playmusico(2);
				relrot(49);			
				x=0;
				
				while((Info.sensor[5]<350||Info.sensor[4]<300||x<1)&&fin==false){
					System.out.println( "sensor[4],sensor[3]sc");
					setgodis(20,150);
					if(calbumdis(Info.sensor[2],Info.sensor[3])<250){
						forward(0);
						backprepoint();
						return -1;
					}
				x++;
				
				}
				forward(0);
				relrot(-49);
				playmusicc(2);
				return 1;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	
	public double calbumdis(int l1,int l2){
		double dis=1000;
		if(l1>=l2)dis=l2*Math.cos(Math.PI/180*15);
		else dis=l1*Math.cos(Math.PI/180*15);
		return dis;
	}
	public double calbumdis2(int l1,int l2){
		double dis=1000;
		if(l1>=l2)dis=l2*Math.sin(Math.PI/180*60);
		else dis=l1*Math.sin(Math.PI/180*60);
		return dis;
	}
	
	public void  backprepoint(){
//		(tang + 180)%360;
		
		try {
			int bang=(int)(Math.atan2(tnow.y-cary, tnow.x-carx)*180/Math.PI);
			System.out.println( "backprepoint: x"+ carx+" y "+ cary+" bang "+bang);
			setrotang((360+bang)%360);
			double tdis=Math.sqrt(Math.pow(tnow.y-cary, 2)+Math.pow(tnow.x-carx,2) );
			setgodis(tdis, 150);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	public void avotonext(double tdis){
//		int nang=(int)(Math.atan2(tnext.y-cary, tnext.x-carx)*180/Math.PI);
		System.out.println( "avotonext: next"+tnext.no);
//		if(Math.abs(tnext.y-cary)<10&&Math.abs(tnext.x-carx)<10)return;
		try {
//			setrotang((360+nang)%360);
			//avoidcheckpath();
			qtvisual2 qv2=new qtvisual2();
			qv2.tochep=tnow.no*10+0;
			playmusico(1);
			new Thread(qv2).start();
			int st=0;
			while(qv2.qtfin==false||qv2.uang==360){
				try {
					Thread.sleep(1000);
					st++;
					if(st>6)break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(qv2.uang==360)qv2.uang=0;
			
			if(qv2.uang<-3&&qv2.uang>=-5){
				qv2.uang=-6;
			}
			if(qv2.uang<6&&qv2.uang>=3){
				qv2.uang=6;
			}
			relrot(qv2.uang);
			relrot(0);
			playmusicc(1);
//			double tdis=Math.sqrt(Math.pow(tnext.y-cary, 2)+Math.pow(tnext.x-carx,2) );
			setgodis(tdis, 150);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void forward(int speed) throws IOException{
		System.out.println( "forward ");
		if(socket!=null)socket.close();
		socket=null;
		socket=Setting.server.accept();
		out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(Trans);
		out.flush();
		out.writeInt(speed);
		out.flush();
		socket.close();
		
	}
	public void relrot(int ang) throws IOException{
		System.out.println( "relrot: "+ang);
		socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 double xt=0;
		out.writeInt(Rotate);
		out.flush();
		if(ang==0)out.writeInt(0);
		if(ang>0)out.writeInt(10);
		else if(ang<0)out.writeInt(-10);
		
		out.flush();
		double tang=Math.abs(ang)/10;
		while(xt<tang&&fin==false){
			xt+=0.1;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		 out.close();
		 socket.close();
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(Rotate);
		 out.flush();
		 out.writeInt(0);
		 out.flush();
		 out.close();
		 socket.close();
		
	}
	public  void playmusico(int m){
	
		System.out.println( "playmusico");
		if(m==1){//視覺校正
		 try {
			 socket=null;
		     socket=Setting.server.accept();
			 DataOutputStream out2 = new DataOutputStream(socket.getOutputStream());
			 out2.writeInt(PlayMusic1);
			 out2.flush();
			 out2.writeInt(Open);
			 out2.flush();
			 socket.close();
			 socket=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else{
		try {//避障
			 socket=null;
		socket=Setting.server.accept();
	    DataOutputStream out2 = new DataOutputStream(socket.getOutputStream());
		 out2.writeInt(PlayMusic2);
		 out2.flush();
		 out2.writeInt(Open);
		 out2.flush();
		 socket.close();
		 socket=null;
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
		
   }
	public   void playmusicc(int m){
	
		 
		if(m==1){
			try {
			 socket=null;
			 socket=Setting.server.accept();
			 DataOutputStream out2 = new DataOutputStream(socket.getOutputStream());
			 out2.writeInt(PlayMusic1);
			 out2.flush();
			 out2.writeInt(Close);
			 out2.flush();
			 out2.close();
			 socket=null;
		
	    }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	   else{
		   try {
			   socket=null;
				socket=Setting.server.accept();
				DataOutputStream out2 = new DataOutputStream(socket.getOutputStream());
				 out2.writeInt(PlayMusic2);
				 out2.flush();
				 out2.writeInt(Close);
				 out2.flush();
				 out2.close();
				 socket=null;
			 
	  }
	  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
   }
	static int rotc=0;
	static boolean po=false;
	static int ta=0;
	static int status=0;
	public  void setrotang(int ang) throws IOException, InterruptedException{
		rotc++;
		System.out.println( "setrotang"+ang);
		
		int ttx=(int) (Math.abs(ang-Info.carang)/9);
		if(Info.carang<90&&ang>270){ttx=(int) (ang-Info.carang)/9;}
		if(ang==0){ttx=(int) (Math.abs(360-Info.carang)/9);
		if(Info.carang<10){ttx=1;}
		}
		Socket socket=null;
		 socket=Setting.server.accept();
		out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(MaxRotV);
		 out.flush();
		 out.writeInt(10);
		 out.flush();
		 out.close();
		 socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(AbsoluteHeading);
		 out.flush();
		 if(ta==0){
			 if(ang<=180){
					po=true;
					ta++;
				}else{
					po=false;
					ta++;
				}
		 }
		 double fixang=0;
		 if(po==true){
			 fixang=ang-ang/30;
			 status=0;
//			 if(Info.carang>=180&&ang>180){fixang=ang-ang/15;
//			 status=1;
			 if(Info.carang>180&&ang==270){
				 fixang=ang-ang/30-10;
					status=2;
					
				}
			 if(Info.carang>180&&ang==0){
					fixang=360-360/30-15;  
					status=2;
					
				}
		}
		
		 else{
			 fixang=ang+(360-ang)/30;
			 if(Info.carang<180&&ang==90){
				 fixang=ang+(360-ang)/30+10;
					
				}
			 if(Info.carang<180&&ang==0){
					fixang=27;  
					
				}
		 }
		
		 
		 out.writeInt((int) fixang);
		 out.flush();
		 
		 Thread.sleep(ttx*1000+1000);
		 
		 socket=null;
		 socket=Setting.server.accept();
		 out = new DataOutputStream(socket.getOutputStream());
		 out.writeInt(Rotate);
		 out.flush();
		 out.writeInt(0);
		 out.flush();
		
		 out.close();
		 socket.close();
		 int x=0;
		 Thread.sleep(250);
		 
//		if(Info.carang>350&&Math.abs(Info.carang-360)>2&&ang==0&&rotc==1){
//			
//			
//			 relrot(45); 
//			 relrot(0); 
//			 setrotang(ang);
//		}
//		 if((Math.abs(Info.carang-ang)>2)&&rotc==1){
//			 System.out.println( "Math.abs(Info.carang-ang)");
//		
//		     relrot(45); 
//		     relrot(0); 
//		     setrotang(ang); 	
//		 }
		 rotc=0;
		
	}
	public void reset0(){
		 socket=null;
		 try {
			socket=Setting.server.accept();
			out = new DataOutputStream(socket.getOutputStream());
			 out.writeInt(ResetPotition);
			 out.flush();
			 out.close();
			 socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		 
		
	}
	public void setgodis(double dis,int speed) throws IOException, InterruptedException{
		System.out.println( "setgodis");
		
		double dtime=dis/(speed/10);
		System.out.println( "dis: "+dis+" time: "+dtime);
		double xt=0;
		
		forward(speed);
		double ttdis=0;
		double safedistime=60/(speed/10);
		while(xt<dtime&&fin==false){
		   if(dtime-xt>safedistime){
			if(tnow.no==3&&tnext.no==8){
				if((avoidbum38()==1||avoidbum38()==-1)){
					
					double tdis=(dtime-xt)*(speed/10);
					System.out.println( "remaindis: "+tdis);
					avotonext(tdis-25);
					break;
				}
			}
			else if(avoidbum2()==1){
				
				double tdis=(dtime-xt)*(speed/10);
				System.out.println( "remaindis: "+tdis);
				avotonext(tdis-15);
				break;				
			}
		   }
			Thread.sleep(100);
//			carx=carx+(0.1*speed/10)*Math.cos(Math.PI/180*Info.carang);
//			cary=cary+(0.1*speed/10)*Math.sin(Math.PI/180*Info.carang);
			xt+=0.1;
			
		}
//		Thread.sleep((long) dtime);
		forward(0);
		
		
	
	
	}
	public  void setstop(boolean t) {
		this.fin = t;
		Thread.currentThread().interrupt();
		while(Thread.currentThread().isInterrupted()==false){}
		this.fin = t;
		try {
			forward(0);
			relrot(0);
			out=null;
			socket=null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	
	}
	
	public class vec{
		int x=0;
		int y=0;
		int no=0;
		vec(){}
		vec(int no){
			this.no=no;
			switch(no){
			case 0:
				x=0;
				y=0;
				break;
			case 1: 
				x=300;
				y=0;
				break;
			case 2:
				x=600;
				y=0;
				break;
			case 3:
				x=600;
				y=360;
				break;
			case 4:
				x=300;
				y=300;
				break;
			case 5:
				x=0;
				y=360;
				break;
			case 6:
				x=0;
				y=720;
				break;
			case 7:
				x=300;
				y=780;
				break;
			case 8:
				x=600;
				y=720;
				break;
			
				
			default: 
                System.out.println(""+no);
			}
		}
		public void set(int x,int y,int no){
			this.x=x;
			this.y=y;
			this.no=no;
		} 
		
	} 

}
