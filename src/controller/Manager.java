package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Manager {

    public boolean isLineEmpty(String line) {
        if (line.length() == 0) {
            return true;
        }
        return false;
    }

    public void countLine() {
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)));
            String line;
            //write until end file
            while ((line = br.readLine()) != null) {
                //check line empty
                if (isLineEmpty(line)) {
                    continue;
                }             
                line = formatOneSpace(line);
                line = afterDotUpperCase(line);
                line = firstUpperCase(line);
                line = lastAddDot(line);
                pw.print(line);
                pw.print(System.getProperty("line.separator"));
                
            }
            br.close();
            pw.close();
            System.out.println("Normalize successful.");
            

        } catch (FileNotFoundException ex) {
            System.err.println("Can't found.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }

    //Them dau cham cuoi cau
    public String lastAddDot(String line) {
        if (line.endsWith(".")) {
            return line;
        }
        return line + ".";
    }

    //Chu hoa dau dong
    public String firstUpperCase(String line) {
        line = line.trim();
        StringBuffer strbBuffer = new StringBuffer(line);
        if (Character.isLetter(line.charAt(0))) {
            strbBuffer.setCharAt(0, Character.toUpperCase(line.charAt(0)));
        }
        return strbBuffer.toString();
}

    //Viet hoa chu cai dau sau dau cham
    public String afterDotUpperCase(String line){
        line = line.trim();
        StringBuffer strBuffer = new StringBuffer(line);
        for (int i=0; i<strBuffer.length()-2; i++){
            if (strBuffer.charAt(i)=='.'){
                strBuffer.setCharAt(i+2,Character.toUpperCase(strBuffer.charAt(i+2)));
            }
        }
        return strBuffer.toString();
    }
    
    //khoang cach sau ki tu dac biet
    public String formatSpecial(String line, String ch){
        line = line.trim();
        StringBuffer str= new StringBuffer();
        String[] string = line.split("\\s*\\"+ ch + "\\s*");
        for (String oneWord : string) {
            str.append(oneWord+ "" + ch);
            str.append(" ");
        }
        return str.toString().substring(0, str.length()-3);
    }
    
    //
    public String formatOneSpace(String line){
        line = line.trim().toLowerCase();
        line = line.replace("\\s+"," ");
        line = formatSpecial(line, ".");
        line = formatSpecial(line, ",");
        line = formatSpecial(line, ":");
        line = formatSpecial(line, "\"");
        return line;
    }
}
