package yt190409;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import javax.script.*;
public class byyl3 {
	private static BufferedReader buffer;
	static int ii=0;
	static int jj=0;
	static int kk=0;
	private static Scanner sc;
    static char getch(String buffer){
	char c;
	c=buffer.charAt(ii);
	ii++;
	return c;
    }
    static boolean isnumber(char c) {
    	if ('0' <= c&&c <= '9')
    		return true;
    	else return false;
       }
    static boolean isoperator(String c) {
    	if (c.equals("+")|| c.equals("-")|| c.equals("*")|| c.equals("/")||c.equals("(")||c.equals(")")||c.equals("#"))
    		return true;
    	else return false;
    }
    static void computer(String calculate) throws IOException, ScriptException{
    	String str=calculate.toString();
    	ScriptEngineManager manager=new ScriptEngineManager();
    	ScriptEngine engine=manager.getEngineByName("js");
    	Object result=engine.eval(str);
    	System.out.println("运算结果："+result);
    }
	public static void main(String[] args) throws IOException, Exception{
		// TODO Auto-generated method stub
		 FileReader fr=new FileReader("D:\\360MoveData\\Users\\叶谈\\Desktop\\byyl\\demo2.txt");	  
		     buffer = new BufferedReader(fr);	   
		     String[] yt=new String[193];
		     for(int i=0;i<193;i++)
		     {
		    	 yt[i]=buffer.readLine();
				// System.out.print(yt[i]); 
		     }
		     sc = new Scanner(System.in);
		     System.out.print("请输入一个字符串：");
	         String ch1=sc.next();
	         System.out.println(ch1);
	         String[] ch2 =new String[20];  
	         String[] ch3 =new String[30];
	         String calculate="";
	         int count=ch1.length();
	      //   System.out.println(count);
	         int len=0;
	      while(ii<count){
	        	 char ch=getch(ch1);
	        			if (isnumber(ch)) 
	        		{
	        			String num="";
	        			do{
	        				num = num + String.valueOf(ch);
	        				if(ii<count)
	        				{ch = getch(ch1);}
	        			} while (isnumber(ch));
	        			ch2[jj]=num.toString();
	        		//	System.out.println(ch2[jj]);
	        			jj++;
	        			ii--;
	        			continue;			
	        		}
	        		else{
	        			ch2[jj]=String.valueOf(ch);
	        			//System.out.println(ch2[jj]);
	        			jj++;
        			    continue;}
	        		}
	         for(int i=0;i<ch2.length;i++)
	         {   if(ch2[i]!=null) 
	             {
	        	// System.out.println(ch2[i]);
	        	 len++;
	             }     else break;
	         }
	        // System.out.println(len);
	         int x=0;
             int y=0;
	         for(int i=0;i<len-1;i++)
	         {    
	              if(ch2[i].equals("(")){x++;}
	              if(ch2[i].equals(")")){y++;}
	        	 if(len-1!=1&&i+1<len-1&&i>=1&&isoperator(ch2[i])&&(ch2[i-1].equals("(")||ch2[i+1].equals(")")))
	        	// System.out.println(ch2[i]);
	        	 {System.out.println("-----------字符串输入有误！1--------------");
	        	 break;
	             }   
	        	 else if(i==len-2&&x!=y){
	        		 System.out.println(x+" "+y);
	        		 System.out.println("-----------字符串输入有误！2--------------");
		        	 break;
	        	 }
	        	// else {continue;}
	         }
	         Stack<String> signstack=new Stack<String>();
	         signstack.push("#");
	         System.out.println("符号栈:"+signstack);
	         Stack<String> inputstack=new Stack<String>();
	         for(int i=len-1;i>=0;i--){
	        	 inputstack.push(ch2[i]);
	         }
	         System.out.println("待输入串:"+inputstack);  
	         System.out.println("----------------开始计算逆波兰式----------------");  
	   letter: while(!inputstack.isEmpty())
	   {
	        	 for(int i=1;i<yt.length;i=i+3)
	       {
	        	 if(isoperator(inputstack.peek())==false)
	        	 {
	        		 System.out.println("-------待输入串栈顶是操作数，操作数出栈-------");
	        		 ch3[kk]=inputstack.peek();
	        		 inputstack.pop();
	        		 System.out.println("待输入串:"+inputstack);  
	        		 System.out.println("符号栈:"+signstack);
	        		 kk++;
	        		 break;
	        	 }
	        	 else if(isoperator(inputstack.peek())==true||signstack.peek().equals("#")==true){
	        			 if(yt[i].equals(signstack.peek())&&yt[i+1].equals(inputstack.peek())&&yt[i+2].equals("<"))
	        			    { System.out.println("-------符号栈栈顶<待输入串栈顶，进符号栈-------");
	        				 signstack.push(inputstack.peek());
	        				 inputstack.pop();
	        				 System.out.println("待输入串:"+inputstack);  
	    	        		 System.out.println("符号栈:"+signstack);
	        				 break;
	        			     }
	        			 else if(yt[i].equals(signstack.peek())&&yt[i+1].equals(inputstack.peek())&&yt[i+2].equals(">")&&inputstack.peek().equals(")")==false)
	        			     { System.out.println("-------符号栈栈顶>待输入串栈顶，出符号栈-------");
	        				 ch3[kk]=signstack.peek();
	        				 signstack.pop();
	        				 System.out.println("待输入串:"+inputstack);  
	    	        		 System.out.println("符号栈:"+signstack);
	        				 kk++;
	        				 break;
	        			     }
	        			 else if(yt[i].equals(signstack.peek())&&yt[i+1].equals(inputstack.peek())&&inputstack.peek().equals(")"))
	        			     { System.out.println("-------待输入串栈顶是“)”,“)”出栈,符号栈出栈直到“(”-------");
	        				 while(signstack.peek().equals("(")==false)
	        				 {    ch3[kk]=signstack.peek();
	        					 signstack.pop();
	        					 System.out.println("待输入串:"+inputstack);  
	        	        		 System.out.println("符号栈:"+signstack);
	        					 kk++;
	        				 }
	        				 signstack.pop();//左括号出栈
	        				 inputstack.pop();//右括号出栈
	        				 System.out.println("待输入串:"+inputstack);  
	    	        		 System.out.println("符号栈:"+signstack);
	        				 break;
	        			     }
	        			 else if(yt[i].equals(signstack.peek())&&yt[i+1].equals(inputstack.peek())&&yt[i+2].equals("=")&&inputstack.peek().equals("#")){
	        				 System.out.println("-------待输入串栈顶是“#”,“#”出栈,符号栈出栈直到“#”-------");
	        				 inputstack.pop();
	        				 if(signstack.peek().equals("#")==false){
	        					 ch3[kk]=signstack.peek();
	        				     signstack.pop();
	        				     kk++;
	        				 }
	        				 signstack.pop();//#出栈
	        				 if(signstack.isEmpty()&&inputstack.isEmpty())
	        				 {
	        					 System.out.println("----------------逆波兰式分析完成----------------");
	        					 System.out.println("此时待输入串:"+inputstack);  
		    	        		 System.out.println("此时符号栈:"+signstack);
		    	        		 int len1=0;
		    	    			 for(int j=0;j<ch3.length;j++)
		    	    	         {   if(ch3[j]!=null) 
		    	    	             {
		    	    	        	// System.out.println(ch3[j]);
		    	    	        	 len1++;
		    	    	             }     else break;
		    	    	         }
		    	    			 System.out.print("逆波兰式：");
		    	    			 for(int j=0;j<len1;j++){
		    	    				 System.out.print(ch3[j]+" ");
		    	    			 }
		    	    			 System.out.println();
		    	    			 for(int j=0;j<ch2.length;j++)
		    	    	         {   if(ch2[j].equals("#")==false&&ch2[j]!=null) 
		    	    	             {
		    	    	        	 calculate=calculate+ch2[j];
		    	    	        	// System.out.println(ch2[j]);
		    	    	             }     else break;
		    	    	         }
		    	    			 computer(calculate);
	        				 }
	        				 break letter;
	        			 }
	        			 else if(yt[i].equals(signstack.peek())&&yt[i+1].equals(inputstack.peek())&&yt[i+2].equals("NULL"))
	        			    {
	        				 System.out.println("分析逆波兰式出现错误！");
	        				 System.out.println("此时待输入串:"+inputstack);  
	    	        		 System.out.println("此时符号栈:"+signstack);
	    	        		 int len1=0;
	    	        		 for(int j=0;j<ch3.length;j++)
	    	    	         {   if(ch3[j]!=null) 
	    	    	             {
	    	    	        	// System.out.println(ch3[j]);
	    	    	        	 len1++;
	    	    	             }     else break;
	    	    	         }
	    	        		 System.out.print("逆波兰式：");
	    	    			 for(int j=0;j<len1;j++){
	    	    				 System.out.print(ch3[j]+" ");
	    	    			 }
	        				 break letter;
	        			    }
	        			 else{
	        				 //System.out.println("-------7-------");
	        				 continue;
	                         }      			 
	        		 }
	        	 }
	        	 }
	         }
	}
