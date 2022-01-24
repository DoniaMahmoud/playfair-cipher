import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
	static String alphabets="ABCDEFGHIKLMNOPQRSTUVWXYZ";
	static ArrayList<String> encrypted = new ArrayList<>();
	static ArrayList<String> decrypted = new ArrayList<>();
	static StringBuilder preDecrypt = new StringBuilder();
	static char[][] mat=new char[5][5];
	static boolean checkX=false;
	static boolean flag=false;
	static boolean replaced=false;
	
	public static String[] split(String string) {
		String[] splitted = new String[string.length()/ 2];
		int counter=0;
		for (int i=0; i<(string.length())/2; i++) {
			splitted[i] = string.substring(counter, counter += 2);
		}
		return splitted;
	}
	public static String deleteSpaces(String plainText) {
	   String plainText1=plainText.replaceAll("\\s", "");
		return plainText1;
	}
	public static void prepareMat(String key) {
	     for(int j=0; j<key.length(); j++) {
	       if (alphabets.indexOf(key.charAt(j))>=0){
				alphabets = alphabets.replace(Character. toString(key.charAt(j)), "");	
			}
	       }
		  String matrix = key + alphabets;
		  for (int i = 0; i<5; i++){
		    	for (int j=0; j<5; j++){ 
		    		mat[i][j] = matrix.charAt(i*5+j);
		        }
		   }
		  
	}

	public static String cancelRepeated(String key) {
		String string = "";
		for (int i=0; i <key.length(); i++) {
			if (key.indexOf(key.charAt(i))==i){
				string += key.charAt(i);
			 } 
	    }
		return string;
	}

	public static String replace(String userInput) {
		 if(userInput.contains("J") || userInput.contains("j")) {
		  replaced=true; 
		  }
    	  userInput = userInput.toUpperCase().replace("J", "I");
	      return userInput;
	}

	public static String doubles(String str) {
		//MAKING LENGTH OF STRING EVEN NUMBER
		if (!(str.length() % 2 == 0)) {
			str += 'X';
		     }
		for (int i = 0; i<str.length(); i+=2) {
			if (str.charAt(i)==str.charAt(i + 1) ) { 
				str= str.substring(0,i+1)+ 'X' +str.substring(i + 1,(str.length()-1) ); 
			}
	     }
		return str;
	}
	public static void printMat(char[][] mat) {
		for (int i=0; i<5; i++){
		   for (int j=0; j<5; j++){ 
	         System.out.print(mat[i][j] + " ");
		   }
		    System.out.println();
		}
	}
	public static ArrayList<Integer> charIndex(char c) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i=0; i<5; i++ ) {
			for(int j=0; j<5; j++){
				if(c== mat[i][j]) {
					index.add(i);
					index.add(j);
				}
			}
		}
		return index;
	}
	public static void encryption(String str) {
		ArrayList<Integer> index1  = new ArrayList<Integer>();
		ArrayList<Integer> index2  = new ArrayList<Integer>();
	    index1=charIndex(str.charAt(0));
	    index2=charIndex(str.charAt(1));
	  
	    if(str.contains("X")) {
			 checkX=true;
		 }else {
			 checkX=false;
		 }
	    
	    //CASE : SAME COLUMN
	    if(index1.get(1)==index2.get(1)) {
	        StringBuilder stringBuilder1 = new StringBuilder();
	    	String str1="";
	    	String str2="";
	    	String finalStr="";
	    	str1 = str.replace(str.charAt(0),mat[(index1.get(0)+1)%5][index1.get(1)]);
	    	str2 = str.replace(str.charAt(1),mat[(index2.get(0)+1)%5][index2.get(1)]);
	    	stringBuilder1.append(finalStr).append(str1.charAt(0)).append(str2.charAt(1));
	    	String finalString = stringBuilder1.toString();
	    	encrypted.add(finalString);

	    }
	    //CASE : SAME ROW
	    else if(index1.get(0)==index2.get(0)) {
	    	StringBuilder stringBuilder1 = new StringBuilder();
	    	String str1="";
	    	String str2="";
	    	String finalStr="";
	        str1 = str.replace(str.charAt(0),mat[index1.get(0)][(index1.get(1)+1)%5]);
	    	str2 = str.replace(str.charAt(1),mat[index2.get(0)][(index2.get(1)+1)%5]);
	    	stringBuilder1.append(finalStr).append(str1.charAt(0)).append(str2.charAt(1));
	    	String finalString = stringBuilder1.toString();
	    	encrypted.add(finalString);
	    	
	    }
	    //CASE : NOT SAME COLUMN OR ROW
	    else {
	    	StringBuilder stringBuilder1 = new StringBuilder();
	    	String str1="";
	    	String str2="";
	    	String finalStr="";
	    	str1 = str.replace(str.charAt(0),mat[index1.get(0)][(index2.get(1))]);
	    	str2 = str.replace(str.charAt(1),mat[index2.get(0)][(index1.get(1))]);
	    	stringBuilder1.append(finalStr).append(str1.charAt(0)).append(str2.charAt(1));
	    	String finalString = stringBuilder1.toString();
	    	encrypted.add(finalString);
      }
	}
		
	public static void preDecryption(String str) {
		preDecrypt.append(str);
	}
	
	public static void decryption1() {
		String toDecrypt=preDecrypt.toString();
		String[] splitted= split(toDecrypt);
		 for (int i = 0; i< splitted.length; i++) {
			  decryption2(splitted[i],i,splitted.length); 
		  }
		
	}
	public static void decryption2(String str, int i, int length) {
		ArrayList<Integer> index1  = new ArrayList<Integer>();
		ArrayList<Integer> index2  = new ArrayList<Integer>();
	    index1=charIndex(str.charAt(0));
	    index2=charIndex(str.charAt(1));
		 
	    //IF THERE IS X IN THE LAST CHAR IN STRING	    		
	    if(checkX==true && i==(length-1)) { 
		   flag=true; 
	     }
		    //CASE : SAME COLUMN
		    if(index1.get(1)==index2.get(1)) {    
		        StringBuilder stringBuilder1 = new StringBuilder();
		    	String str1="";
		    	String str2="";
		    	String finalStr="";
		    	int in1 = (index1.get(0)-1);
		    	int in2=(index2.get(0)-1);
		    	int in3;
		    	int in4;
		    	//IF INDEX IS NEGATIVE
		    	if (in1<0) {
		    		in1=(in1-7)+in1;
		    		in3=Math.abs(in1);
		    		in3=(in3)%5;
		    		str1 = str.replace(str.charAt(0),mat[in3][index1.get(1)]);
		    	}else {
		    	str1 = str.replace(str.charAt(0),mat[(index1.get(0)-1)%5][index1.get(1)]);
		    	}
		    	if (in2<0) {
		    		in2=(in2-7)+in2;
		    		in4=Math.abs(in2);
		    		in4=(in4)%5;
		    		str2 = str.replace(str.charAt(1),mat[in4][index2.get(1)]);
		    	}else {
		    	str2 = str.replace(str.charAt(1),mat[(index2.get(0)-1)%5][index2.get(1)]);
		    	}
		    	stringBuilder1.append(finalStr).append(str1.charAt(0)).append(str2.charAt(1));
		    	String finalString = stringBuilder1.toString();
		    	StringBuffer sb= new StringBuffer(finalString);      				
				  if(flag==true) { 
					  sb.deleteCharAt(sb.length()-1);
					  }
                String finalString2=sb.toString();
                if(finalString2.contains("X"))
                	finalString2=finalString2.replace("X","");
				
                //IF I WAS REPLACED , RETURN IT TO J
				 if(replaced==true && finalString2.contains("I")) {
				  finalString2=finalString2.replace("I", "J");
				  }
		    	  decrypted.add(finalString2); 	
		    }
		    //CASE : SAME ROW
		    else if(index1.get(0)==index2.get(0)) {
		    	StringBuilder stringBuilder1 = new StringBuilder();
		    	String str1="";
		    	String str2="";
		    	String finalStr="";
		    	int in1 = (index1.get(1)-1);
		    	int in2=(index2.get(1)-1);
		    	int in3;
		    	int in4;
		    	//IF INDEX IS NEGATIVE
		    	if (in1<0) { 
		    		in1=(in1-7)+in1;
		    		in3=Math.abs(in1);
		    		in3=(in3)%5;
		    	   str1 = str.replace(str.charAt(0),mat[index1.get(0)][in3]);
		    	}else {
		    		  str1 = str.replace(str.charAt(0),mat[index1.get(0)][(index1.get(1)-1)%5]);}
		    	if (in2<0) {
		    		in2=(in2-7)+in2;
		    		in4=Math.abs(in2);		
		    		in4=(in4)%5;
		    		str2 = str.replace(str.charAt(1),mat[index2.get(0)][in4]);
		    	}else {
		    		str2 = str.replace(str.charAt(1),mat[index2.get(0)][(index2.get(1)-1)%5]);}
		    	stringBuilder1.append(finalStr).append(str1.charAt(0)).append(str2.charAt(1));
		    	String finalString = stringBuilder1.toString();
				StringBuffer sb= new StringBuffer(finalString);  
				
				  if(flag==true) { 
					  sb.deleteCharAt(sb.length()-1); 
					  }	 
	            String finalString2=sb.toString();
	            if(finalString2.contains("X"))
                	finalString2=finalString2.replace("X","");
	            
				//IF I WAS REPLACED , RETURN TO J
				 if(replaced==true && finalString2.contains("I")) {
				  finalString2=finalString2.replace("I", "J"); }
		    	decrypted.add(finalString2);
		    	
		    }
		    //CASE : NOT SAME ROW OR COLUMN
		    else {		    	
		    	StringBuilder stringBuilder1 = new StringBuilder();
		    	String str1="";
		    	String str2="";
		    	String finalStr="";
		    	str1 = str.replace(str.charAt(0),mat[index1.get(0)][(index2.get(1))]);
		    	str2 = str.replace(str.charAt(1),mat[index2.get(0)][(index1.get(1))]);
		    	stringBuilder1.append(finalStr).append(str1.charAt(0)).append(str2.charAt(1));
		    	String finalString = stringBuilder1.toString();
		    	StringBuffer sb= new StringBuffer(finalString);  
			
				  if(flag==true ) { 
					  sb.deleteCharAt(sb.length()-1); 
					  }
				 
                String finalString2=sb.toString();
                if(finalString2.contains("X"))
                	finalString2=finalString2.replace("X","");
                
             //IF I WAS REPLACED < RETURN TO J			
				if(replaced==true && finalString2.contains("I")) {
				 finalString2=finalString2.replace("I", "J"); }
		    	decrypted.add(finalString2);				
	        }
		
	}
	public static void postDecryption() {
		decrypted.replaceAll(String::toLowerCase);
	}
	
	public static void main(String[] args) {
		 String key = "Monchary";
		 Scanner input =  new Scanner(System.in);
		 
		//CANCEL REPEATED IN KEY
		String updatedKey = cancelRepeated(key);
		
		//REPLACE ANY J WITH I
		String updatedKey2=replace(updatedKey);
		
        //PREPARING MATRIX
		prepareMat(updatedKey2);
		System.out.println("The Matrix: ");
		printMat(mat);
		
		System.out.println();
	    System.out.println("Enter the plain text:");
		String plainText =input.nextLine();
		 
	    //DELETE SPACES IN PLAIN TEXT
		String plainText1 = deleteSpaces(plainText);
		 
		//REPLACE I WITH J
		String plainText2 = replace(plainText1);
		 
		//SUBSTITUTE REPEATED CHARS WITH X
		String plainText3=doubles(plainText2); 
	
		 
		//SPLIT INTO PAIRS
		System.out.println();
		System.out.println("Splitting into pairs:");
		String[] plainText4 = split(plainText3);
		for (int i = 0; i< plainText4.length; i++) {
			  System.out.println(plainText4[i]); 
		 }
		for (int i = 0; i< plainText4.length; i++) {
			  encryption(plainText4[i]); 
		  }
		 System.out.println();
		 System.out.println("After Encryption:");
		 for (int i = 0; i< encrypted.size(); i++) {
			 System.out.println(encrypted.get(i)); 
		  } 
		 for (int i = 0; i< encrypted.size(); i++) {
			 preDecryption(encrypted.get(i)); 
		  } 
		  decryption1();
		  postDecryption();
		  System.out.println();
		  System.out.println("After Decryption:");
		  for (int i = 0; i< decrypted.size(); i++) {
				 System.out.print(decrypted.get(i)); 
			  }

}
	}
