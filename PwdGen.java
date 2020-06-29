
import java.io.*;
import java.lang.Math;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

class PwdGen{

	public static int max = 126;
	public static int min = 33;
	public static int offset = max-min;

	String prefix;
	String suffix;
	String out_file_name = "";
	String pwd;
	int r = 1;
	int p = 0;
	boolean o = false;
	StringBuilder pwd_list = new StringBuilder();

	public PwdGen(){
//		this.prefix = prefix;
		this.r = 1;
//		this.out_file_name = out_file_name;
	}

	public String create_rand_string(int length){
		StringBuilder prefixBuilder = new StringBuilder(length);
		for(int i = 0; i<length; i++){
			char next_char = (char) ((Math.random()*offset)+min);
			prefixBuilder.append(next_char);
		}
		return prefixBuilder.toString();

	}

	public String[] create_rand_string_arr(int length, int r){
		String[] pwd_arr = new String[r];
		for(int i = 0; i<r; i++){
			pwd_arr[i] = create_rand_string(length);
		}

		return pwd_arr;
	}

	public String create_pwd(String prefix){
		suffix = create_rand_string(5);
		int total_length = prefix.length() + suffix.length();
		StringBuilder pwd = new StringBuilder(total_length);
		pwd.append(prefix);
		pwd.append(suffix);
		return pwd.toString();
	}

	public boolean check_file_exists(String file_name) {
		File f = new File(file_name+".csv");
		if(!f.exists() && !f.isDirectory()){
			return false;
		}else{
			return true;
		}

	}

}







