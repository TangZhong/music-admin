package com.music.admin.util;

import java.io.*;

/**
 * Created by donald.tang on 2019/7/17.
 */
public class FileUtil {

    public static final String FILE_TYPE_AUDIO = "audio";
    public static final String FILE_TYPE_LRC = "lrc";
    public static final String FILE_TYPE_IMG = "image";

    public static void saveFile(InputStream in,String targetPath){
        FileOutputStream out = null;
        try {
            File outFile = new File(targetPath);

            if(!outFile.getParentFile().exists())
                outFile.getParentFile().mkdirs();

            out = new FileOutputStream(targetPath);

            byte[] bf = new byte[1024];
            int len = 0;
            while ((len = in.read(bf)) != -1){
                out.write(bf,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
