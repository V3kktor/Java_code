package Java_code.Lab3;
import java.io.File;
import java.util.Scanner;
public class Read_file {
    private String _file_path="";
    private String _file_content="";
    public Read_file(){

    }
    public String Get_filePath(){
        return _file_path;
    }
    public void set_filePath(String path)
    {
        _file_path=path;
    }
    public String get_file_content(){
        try{
            Scanner fsc=new Scanner(new File(_file_path));
            _file_content= fsc.nextLine();
            return _file_content;
        }
       catch (Exception e){
            return "";
       }
    }


}
