package com.shaolei;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Stage;
import javafx.util.Duration;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Game extends Application{
	
	 private final String low="低级(8*8,10个雷)";
	 private final String center="中级(12*12,20个雷)";
	 private final String high="高级(16*16,40个雷)";
	 private int mines=0;//雷数
    // private int minecount=0;//ͳ��������
    // private int findmines=0;
     private int mark=0;//标记数
     private int click=0;//ͳ�Ƶ����
     private int  btnrow=0;//���ù�ģ
     private int btncol=0;
     //private int leftclicknumber = 0;//�һ�����
    // ����һ��ͳ��ʱ�������
    //int time = 0;
    // ����һ����ʱ��
     //Timer timer = null;
  
     private Button[][] btns=null;   //��ť
	 private String []Mines=null;    //���ը��
	 private String [][]mine=null;     //��ά������ը��
	 private String [][]m=null;           //��ֹ������ѭ�������ڱ���Զ������Ѿ��߹���·
	 private Timeline timeline,timeline1,timeline2;
	 private DoubleProperty timeSeconds = new SimpleDoubleProperty();
	 private Duration time = Duration.ZERO;

	
	private String[] Titles={low,center,high};
	//private ChangePane changePane=new ChangePane();
	BorderPane pane=new BorderPane(); 
	TextField resultField = new TextField();
	TextField resultField1= new TextField();
	private ComboBox<String> emailComboBox = new ComboBox<>(); 
		@Override
		
		
		
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			//BorderPane pane=new BorderPane(); 
			Scene scene = new Scene(pane,450,450);
			pane.setTop(getHBox());
			pane.setLeft(getHBox1());
			pane.setRight(getHBox1());
			pane.setBottom(getHBox2());
			setDisplay(0);   //���ó�ʼ��ģΪ��������ʾ��
			ObservableList<String> items=
					FXCollections.observableArrayList(Titles);
			emailComboBox.getItems().addAll(items);
			
			emailComboBox.setOnAction(e -> setDisplay(items.indexOf(emailComboBox.getValue())));
			//begin();
			primaryStage.setTitle("扫雷");
			primaryStage.setScene(scene);
			primaryStage.show();
			//System.out.println(items.indexOf(emailComboBox.getValue()));
		}
		
		
		private void setDisplay(int indexOf) {
			// TODO Auto-generated method stub
			System.out.println(indexOf);
			//return Titles[indexOf];
			
			GridPane pane1=new GridPane();
			pane1.setPadding(new Insets(10,15,15,15));
			pane1.setStyle("-fx-background-color:gray");
			pane1.setAlignment( Pos.CENTER );
		if(Titles[indexOf].equals(low)){
			resultField1.setText("0");
			time = Duration.ZERO;
			resultField.textProperty().bind(timeSeconds.asString());
			//resultField.setText("0");
			 btns=new Button[8][8];
			 click=0;
			 mark=0;
			btnrow=8;
			btncol=8;
			mines=10;
			this.Mines = new String[mines];
			this.mine=new String[btnrow][btncol];
			this.m=new String[btnrow][btncol];
			creatMines(mines);
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					btns[i][j]=new Button("  ");
					//btns[i][j].setValue(i + "," + j);
					pane1.add(btns[i][j], i, j);
					//mine[i][j]="*";
					final int il=i,jl=j;
					//checked();
					btns[i][j].setOnAction( new EventHandler<ActionEvent>(){
						public void handle(ActionEvent e){
							 //String MINE=il+","+jl;
							checked();
							System.out.print("mark="+(mark+click));
							click++;
							String c=String.valueOf(click);
							resultField1.setText(c);
							if(click==1)
							{
								//���漸���Ǽ�ʱ��
								timeline = new Timeline(
								        new KeyFrame(Duration.millis(100),
								        new EventHandler<ActionEvent>(){
								       public void handle(ActionEvent t) {
								        Duration duration = ((KeyFrame)t.getSource()).getTime();
								        time = time.add(duration);
								        timeSeconds.set(time.toSeconds());
								       }
								        })
								        );
								timeline.setCycleCount(Timeline.INDEFINITE);
								timeline.play();
							}
							if(mark==btnrow*btncol-mines-click)
							{
								gameover();
							}
							else{
							if(mine[il][jl].equals("#"))
							{
								
								//btns[il][jl].setStyle(" -fx-color: Silver green");
						         //btns[il][jl].setText("ը");
								btns[il][jl].setStyle(" -fx-color: red");
								m[il][jl]="#";
								//mark++;
								showallmines();
								gameover();
								//System.out.println("");
								//btns[il][jl].setGraphic();
								timeline.stop();
							}
							else{
								//System.out.print("cg");
								//m[il][jl]="#";
								isHave(il,jl);
								/*String b=String.valueOf(a);
							//btns[il][jl].setStyle(" -fx-color: Silver black");
								btns[il][jl].setText(b);*/
							}}
			    		}
					});
				}
			}
			System.out.println("create mines:"+mines);
	       //creatMines(mines);
		}	
		if(Titles[indexOf].equals(center)){
			click=0;
			btnrow=12;
			btncol=12;
			mines=20;
			this.Mines = new String[mines];
			this.mine=new String[btnrow][btncol];
			this.m=new String[btnrow][btncol];
			btns=new Button[12][12];
			resultField1.setText("0");
			time = Duration.ZERO;
			resultField.textProperty().bind(timeSeconds.asString());
			//resultField.setText("0");
			for(int i=0;i<12;i++)
			{
				for(int j=0;j<12;j++)
				{
					btns[i][j]=new Button("  ");
					pane1.add(btns[i][j], i, j);
					final int il=i,jl=j;
					btns[i][j].setOnAction( new EventHandler<ActionEvent>(){
						public void handle(ActionEvent e){
							//int click=0;
							click++;
							String c=String.valueOf(click);
							resultField1.setText(c);
							if(click==1)
							{
								timeline1 = new Timeline(
								        new KeyFrame(Duration.millis(100),
								        new EventHandler<ActionEvent>(){
								       public void handle(ActionEvent t) {
								        Duration duration = ((KeyFrame)t.getSource()).getTime();
								        time = time.add(duration);
								        timeSeconds.set(time.toSeconds());
								       }
								        })
								        );
								timeline1.setCycleCount(Timeline.INDEFINITE);
								timeline1.play();
							}
							
							if(mine[il][jl].equals("#"))
							{
								System.out.print("sb");
								//btns[il][jl].setStyle(" -fx-color: Silver green");
								//btns[il][jl].setText("ը");
								btns[il][jl].setStyle(" -fx-color: red");
								m[il][jl]="#";
								mark++;
								showallmines();
                                timeline1.stop();
                                gameover();
								//btns[il][jl].setGraphic();
							}
							else{
								//System.out.print("cg");
								//m[il][jl]="#";
								isHave(il,jl);
								/*String b=String.valueOf(a);
							//btns[il][jl].setStyle(" -fx-color: Silver black");
								btns[il][jl].setText(b);*/
							}
			    		}
					});
				}
			}
			
			creatMines(mines);
		}
		if(Titles[indexOf].equals(high)){
			click=0;
			btnrow=16;
			btncol=16;
			mines=40;
			this.Mines = new String[mines];
			this.mine=new String[btnrow][btncol];
			this.m=new String[btnrow][btncol];
			btns=new Button[16][16];
			resultField1.setText("0");
			time = Duration.ZERO;
			resultField.textProperty().bind(timeSeconds.asString());
			//resultField.setText("0");
			for(int i=0;i<16;i++)
			{
				for(int j=0;j<16;j++)
				{
					btns[i][j]=new Button("  ");
					pane1.add(btns[i][j], i, j);
					final int il=i,jl=j;
					btns[i][j].setOnAction( new EventHandler<ActionEvent>(){
						public void handle(ActionEvent e){
						
							click++;
							String c=String.valueOf(click);
							resultField1.setText(c);
							if(click==1)
							{
								timeline2 = new Timeline(
								        new KeyFrame(Duration.millis(100),
								        new EventHandler<ActionEvent>(){
								       public void handle(ActionEvent t) {
								        Duration duration = ((KeyFrame)t.getSource()).getTime();
								        time = time.add(duration);
								        timeSeconds.set(time.toSeconds());
								       }
								        })
								        );
								timeline2.setCycleCount(Timeline.INDEFINITE);
								timeline2.play();
							}
							if(mine[il][jl].equals("#"))
							{
								System.out.print("sb");
								//btns[il][jl].setStyle(" -fx-color: Silver green");
								//btns[il][jl].setText("ը");
								btns[il][jl].setStyle(" -fx-color: red");
								m[il][jl]="#";
								mark++;
								showallmines();
								timeline2.stop();
							gameover();
								//btns[il][jl].setGraphic();
							}
							else{
								
								isHave(il,jl);
								
							}
			    		}
					});
			
				}
			}
			creatMines(mines);
		}
		pane.setCenter(pane1);
		}
		
		
		public void creatMines(int count)//生成地雷
	     {
	    	int a=0;
	    	//Random rd=new Random();
	    	while(a<count)
	    	{
	    		int row=(int)(Math.random()*btnrow);
	    		int col=(int)(Math.random()*btncol);
	    		String loc=row+","+col;
	    		if (!ismines(loc)) {
	                Mines[a] = loc;
	                mine[row][col]="#";//
	                a++;
	            }
	    	}
	    	for(int i=0;i<btnrow;i++)
	    		for(int j=0;j<btncol;j++)
	    		{
	    			m[i][j]="*";
	    			if(mine[i][j]!="#")
	    				mine[i][j]="*";
	    		}
	    	//System.out.println("cg");
	     }
		private void checked()  //检测是否有雷
		{
			mark=1;
			for(int i=0;i<btnrow;i++)
				for(int j=0;j<btncol;j++)
			{
				if(m[i][j]=="#")
					mark++;
			}
		}

	     private boolean ismines(String a)//是不是雷
	     {  
	    	 boolean b1 = false;
	    	 for(int i=0;i<Mines.length;i++)
	    	 {
	    		 if(Mines[i]!=null)
	    			 if(Mines[i].equals(a))
	    				 return true;
	    	 }
	    	 return b1;
	     } 
	     public void showallmines()//显示出所有雷
	     {
	    	 for(int i=0;i<btnrow;i++)
	    		 for(int j=0;j<btncol;j++)
	    	 {
	    		if(mine[i][j]=="#") 
	    		{
	    			//btns[i][j].setText("ը");
	    			btns[i][j].setStyle(" -fx-color: red");
	    		}
	    	 }
	     }
	    public void gameover()    //游戏结束
	    {
	    	HBox pane=new HBox(10);
	    	pane.setAlignment(Pos.CENTER);
	    	//Button OK=new Button("��Ϸ����");
	    	if(mark!=btnrow*btncol-mines-click){
	    	Text OK=new Text(20,20,"游戏结束,你输了");
	    	pane.getChildren().add(OK);
	    	}
	    	else{
	    		Text OK=new Text(20,20,"恭喜你,通关用时"+time+"");
	    		pane.getChildren().add(OK);
	    	}
	    	
	    	
	    	
	    	Stage second=new Stage();
			Scene scene=new Scene(pane,300,200);
			second.setTitle("游戏结束");
			second.setScene(scene);
			second.show();
	    }
	     public void isHave(int row,int col)//核心算法，检测周围是否有雷，有几颗雷
	     {
	    	 int num=0;
	    	if(row>=1&&row<btnrow-1&&col>=1&&col<btncol-1)
	    	{
	    	 for(int i=-1;i<=1;i++)
	    		 for(int j=-1;j<=1;j++)
	    		 {
	    			 if(i==0&&j==0)
	    			 {
	    				 continue;
	    			 }
	    			 else if(mine[row+i][col+j].equals("#"))
	    			 {
	    				 //String loc=(row+i)+","+(col+j);
	    				 //System.out.println((row+i)+","+(col+j)); 
	    				  num++;
	  
	    			 }
	    		 }
	    	    String b=String.valueOf(num);
				//btns[il][jl].setStyle(" -fx-color: Silver black");
					btns[row][col].setText(b);
	    	     if(num==0&&m[row][col]!="#")
	    	     {   m[row][col]="#";
	    	     mark++;
	    	    	 isHave(row-1,col);
	    	    	 isHave(row,col-1);
	    	    	 isHave(row+1,col);
	    	    	 isHave(row,col+1);
	    	    	 isHave(row-1,col+1);
	    	    	 isHave(row+1,col+1);
	    	    	 isHave(row-1,col-1);
	    	    	 isHave(row+1,col-1);
	    	     }
	    	     
	    	}
	    	 else if(row==0&&col>0&&col<btncol-1)
	    	 {
	    		 for(int i=0;i<=1;i++)
		    		 for(int j=-1;j<=1;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				 //System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
			    	     mark++;
		    	    	 isHave(row,col-1);
		    	    	 isHave(row+1,col);
		    	    	 isHave(row,col+1);
		    	    	
		    	    	 isHave(row+1,col+1);
		    	    	
		    	    	 isHave(row+1,col-1);
		    	     }
	    	 }
	    	 else if(row==0&&col==0)
	    	 {
	    		 for(int i=0;i<=1;i++)
		    		 for(int j=0;j<=1;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				 //System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
			    	     mark++;
		    	    	
		    	    	 isHave(row+1,col);
		    	    	 isHave(row,col+1);
		    	    	
		    	    	 isHave(row+1,col+1);
		    	    	
		  
		    	     }
	    	 }
	    	 else if(col==0&&row>0&&row<btnrow-1)
	    	 {
	    		 for(int i=-1;i<=1;i++)
		    		 for(int j=0;j<=1;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				 //System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
			    	     mark++;
		    	    	 isHave(row-1,col);
		    	    	 
		    	    	 isHave(row+1,col);
		    	    	 isHave(row,col+1);
		    	    	 isHave(row-1,col+1);
		    	    	 isHave(row+1,col+1);
		    	    
		    	    	
		    	     }
	    	 }
	    	 else if(col==btncol-1&&row>0&&row<btnrow-1)
	    	 {
	    		 for(int i=-1;i<=1;i++)
		    		 for(int j=-1;j<=0;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				 //System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
			    	     mark++;
		    	    	 isHave(row-1,col);
		    	    	 
		    	    	 isHave(row+1,col);
		    	    	 isHave(row,col-1);
		    	    	 isHave(row-1,col-1);
		    	    	 isHave(row+1,col-1);
		    	    
		    	    	
		    	     }
	    	 }
	    	 else if(row==btnrow-1&&col>0&&col<btncol-1)
	    	 {
	    		 for(int i=-1;i<=0;i++)
		    		 for(int j=-1;j<=1;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				// System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
			    	     mark++;
		    	    	 isHave(row,col-1);
		    	    	 isHave(row-1,col);
		    	    	 isHave(row,col+1);
		    	    	
		    	    	 isHave(row-1,col+1);
		    	    	
		    	    	 isHave(row-1,col-1);
		    	     }
	    	 }
	    	 else if(row==btnrow-1&&col==0)
	    	 {
	    		 for(int i=-1;i<=0;i++)
		    		 for(int j=0;j<=1;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				// System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
			    	     mark++;
		    	    	
		    	    	 isHave(row-1,col);
		    	    	 isHave(row,col+1);
		    	    	
		    	    	 isHave(row-1,col+1);
		    	    	
		  
		    	     }
	    	 }
	    	 else if(row==0&&col==btncol-1)
	    	 {
	    		 for(int i=0;i<=1;i++)
		    		 for(int j=-1;j<=0;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				 //System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
			    	     mark++;
		    	    	
		    	    	 isHave(row,col-1);
		    	    	 isHave(row+1,col);
		    	    	
		    	    	 isHave(row+1,col-1);
		    	    	
		  
		    	     }
	    	 }
	    	 else if(row==btnrow-1&&col==btncol-1)
	    	 {
	    		 for(int i=-1;i<=0;i++)
		    		 for(int j=-1;j<=0;j++)
		    		 {
		    			 if(i==0&&j==0)
		    			 {
		    				 continue;
		    			 }
		    			 else if(mine[row+i][col+j].equals("#"))
		    			 {
		    				 //String loc=(row+i)+","+(col+j);
		    				 //System.out.println((row+i)+","+(col+j)); 
		    				  num++;
		  
		    			 }
		    		 }
	    		 String b=String.valueOf(num);
					//btns[il][jl].setStyle(" -fx-color: Silver black");
						btns[row][col].setText(b);
						 if(num==0&&m[row][col]!="#")
			    	     {   m[row][col]="#";
		    	    	 
			    	     mark++;
		    	    	 isHave(row,col-1);
		    	    	 isHave(row-1,col);
		    	    	
		    	    	 isHave(row-1,col-1);
		    	    	
		  
		    	     }
	    	 }
	    	// return num;
	     }
	    	
	     
		//�����ǲ����ĺ���
		private GridPane getHBox() {
			//TODO Auto-generated method stub
			GridPane pane=new GridPane();
			pane.setPadding(new Insets(10,0,10,0));
	        pane.setStyle("-fx-background-color:Silver gray");
	        pane.add(new Label("难度  : "),0,0);
	        pane.add(emailComboBox,1,0);
	        pane.add(new Label("时间  :"),2,0);
	       //TextField resultField = new TextField();
	        //resultField.setText("0");
	        resultField.textProperty().bind(timeSeconds.asString());
	        resultField.setFont(Font.font("Verdana", FontWeight.BOLD,11));
	        resultField.setPrefColumnCount(4);
	        pane.add(resultField,3,0);
	        pane.add(new Label("步数  :"),4,0);
	        //TextField resultField1= new TextField();
	        resultField1.setFont(Font.font("Verdana", FontWeight.BOLD,11));
	        resultField1.setPrefColumnCount(4);
	        pane.add(resultField1,5,0);
	        emailComboBox.setValue(low);
			return pane;
		}
		private HBox getHBox1() {
			// TODO Auto-generated method stub
			HBox hBox=new HBox(0);
			hBox.setPadding(new Insets(15,15,15,0));
			hBox.setStyle("-fx-background-color:Silver");
			return hBox;
		}
		private HBox getHBox2() {
			//TODO Auto-generated method stub
			HBox hBox=new HBox(0);
			hBox.setPadding(new Insets(10,0,10,0));
			hBox.setStyle("-fx-background-color:Silver gray");
			return hBox;
		}
		
	     
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Application.launch(args);
			//Game game=new Game();
		}
}