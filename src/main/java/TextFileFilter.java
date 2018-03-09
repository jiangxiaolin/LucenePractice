import java.io.File;

public class TextFileFilter {
    public static boolean accept(File pathname){
        return pathname.getName().toLowerCase().endsWith(".txt");
    }

    public static void main(String[] args){
        System.out.println(accept(new File("/Users/jiangxl/GitDev/LucenePractice/src/main/resources/j.txt")));
    }
}
