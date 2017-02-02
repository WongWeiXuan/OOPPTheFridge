package theFridge.model;

import java.io.UnsupportedEncodingException;

public class Encryption {
    private String line;
    private String binary;

    public Encryption(String line){
        this.line = line;
    }
    
    public Encryption(String binary, boolean anything){
    	this.binary = binary;
    }

    public String getBinary() throws UnsupportedEncodingException{
        byte[] bytes = line.getBytes("UTF-8");
        String finalBytes = "";
        for (byte b : bytes) {
            int i = Integer.toBinaryString(b).length();
            while(i < 8){
                finalBytes += "0";
                i++;
            }
            finalBytes += Integer.toBinaryString(b);
        }
        this.binary = finalBytes;
        return finalBytes;
    }

    public String encryptLine() throws UnsupportedEncodingException{
        String input = binary;
        String encryptedBinary = "";
        for(int i = 0; i<input.length(); i++){
            char c = input.charAt(i);
            if(c == '0'){
                c = '1';
            }
            else if(c == '1'){
                c = '0';
            }
            encryptedBinary += c;
        }
       // this.input = encryptedBinary;
        this.binary = encryptedBinary;
        return encryptedBinary;
    }

    public String getString(){
        String input = binary;
        String output = "";
        for(int i = 0; i <= input.length() - 8; i+=8)
        {
            int k = Integer.parseInt(input.substring(i, i+8), 2);
            output += (char) k;
        } 
        return output;
    }



    public static void main(String[] args) throws UnsupportedEncodingException{
    	/*
        Encryption ec = new Encryption("Hello");
        System.out.println(ec.getBinary());
        System.out.println(ec.encryptLine());
        System.out.println(ec.getString());
        System.out.println(ec.encryptLine());
        System.out.println(ec.getString());
        */
    }
    
}

