import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.*;


public class Main {
    public static void main(String[] args){

        PwdGen pwdGen = new PwdGen();

        Pattern p_prefix = Pattern.compile("-p\\s(\\w+)");
        Pattern r_prefix = Pattern.compile("-r\\s(\\d+)");
        Pattern o_prefix = Pattern.compile("-o\\s(\\w+)");


        try {

            if (args.length > 0) {
                StringBuilder args_builder = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    args_builder.append(args[i]+" ");
                }
                String arguments = args_builder.toString();

                Matcher m;

                m = p_prefix.matcher(arguments);
                if (m.find()) {
                    pwdGen.prefix = m.group(1);
                }
                m = r_prefix.matcher(arguments);
                if (m.find()) {
                    pwdGen.r = Integer.parseInt(m.group(1));
                }
                m = o_prefix.matcher(arguments);
                if (m.find()) {
                    pwdGen.out_file_name = m.group(1);
                }
            } else {
                //Interactive version

                System.out.println("" +
                        "    ____               ________         \n" +
                        "   / __ \\_      ______/ / ____/__  ____ \n" +
                        "  / /_/ / | /| / / __  / / __/ _ \\/ __ \\\n" +
                        " / ____/| |/ |/ / /_/ / /_/ /  __/ / / /\n" +
                        "/_/     |__/|__/\\__,_/\\____/\\___/_/ /_/ \n");

                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the prefix: ");
                pwdGen.prefix = scan.nextLine().trim();

                System.out.println("Please enter the number of passwords you need");
                pwdGen.r = scan.nextInt();
                scan.nextLine();

                boolean file_exists = true;

                while(file_exists) {
                    System.out.println("Please enter the output file name");
                    System.out.println("The file will be saved in CSV format");
                    pwdGen.out_file_name = scan.nextLine().trim();

                    if(pwdGen.check_file_exists(pwdGen.out_file_name)){
                        System.out.println("File name specified already exists,\nPlease choose a different file name");
                    }else{
                        file_exists = false;
                    }
                }
            }


            if (pwdGen.check_file_exists(pwdGen.out_file_name) && !pwdGen.out_file_name.isEmpty()) {
                System.out.println("File name specified already exists");
                System.exit(0);
            }

            String pwd = "";
            StringBuilder pwd_list = new StringBuilder();

            for(int i = 0; i<pwdGen.r; i++) {

                if (pwdGen.prefix.length() > 0) {
                    pwd = pwdGen.create_pwd(pwdGen.prefix);
                } else {
                    pwd = pwdGen.create_rand_string(10);
                }

                pwd_list.append(pwd+"\n");

            }

            if(!pwdGen.out_file_name.isEmpty()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(pwdGen.out_file_name + ".csv"));
                writer.write(pwd_list.toString());
                writer.close();
            }else {
                System.out.println(pwd_list.toString());
            }

        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("Arguments haven't been rightfully specified");
            System.out.println("Run either the program without arguments to get interactive pwd creation");
            System.out.println("or run it with the following arguments");
            System.out.println("-r <repetitions> -p <prefix> [-o <output_file_name>]");
        }
    }
}
