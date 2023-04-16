import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    static Date date = new Date();
    static SimpleDateFormat simplDate = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        String url = "E:\\Games";
        List<String> nameDir = Arrays.asList(
                "src",
                "res",
                "saveGames",
                "tmp"
        );
        dirictoryCreate(url, nameDir);
        dirictoryCreate(url + "\\src", Arrays.asList("main", "test"));
        dirictoryCreate(url + "\\res", Arrays.asList("drawables", "vectors", "icons"));

        List<String> filesName = new ArrayList<>();
        filesName.add("Main.java");
        filesName.add("Utils.java");
        fielsMaker(url + "\\src\\main", filesName);
        filesName.clear();
        filesName.add("tmp.txt");
        fielsMaker(url + "\\tmp", filesName);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url + "\\tmp\\tmp.txt", true))) {
            writer.write(sb.toString());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void dirictoryCreate(String path, List<String> list) {
        list.forEach(d -> {
            File dirs = new File(path, d);
            if (!dirs.exists()) {
                if (dirs.mkdir()) {
                    sb.append(dirs.getName() + " Создан " + simplDate.format(date));
                    sb.append("\n");
                    System.out.println("Папка  " + dirs.getName() + " создана в " + dirs.getAbsolutePath());
                } else {
                    sb.append(dirs.getName() + "Ошибка инцилизации");
                }
            } else {
                sb.append("Каталог уже был создан");
                sb.append("\n");
            }

        });
    }


    public static void fielsMaker(String path, List<String> list) {
        for (String str : list) {
            File files = new File(path, str);
            try {
                if (!files.exists()) {
                    if (files.createNewFile()) {
                        sb.append(files.getName() + " Создан " + simplDate.format(date));
                        sb.append("\n");
                        System.out.println("Файл  " + files.getName() + " создана в " + files.getAbsolutePath());
                    } else {
                        sb.append(files.getName() + "Ошибка инцилизации");
                    }
                } else {
                    sb.append("Файл уже был создан");
                    sb.append("\n");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}