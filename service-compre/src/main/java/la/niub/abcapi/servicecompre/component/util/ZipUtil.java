package la.niub.abcapi.servicecompre.component.util;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    /**
     * 把文件打成压缩包并输出到客户端浏览器中
     * @param request
     * @param response
     * @param srcFiles
     * @param srcFilesName
     * @param downloadZipFileName
     */
    public void zip(HttpServletRequest request, HttpServletResponse response,
                           List<String> srcFiles,  List<String> srcFilesName, String downloadZipFileName){

        try {
            // Create the ZIP file
            // --设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
            ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
            // Compress the files
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                downloadZipFileName = new String(downloadZipFileName.getBytes(),"ISO-8859-1");
            } else {
                // 对文件名进行编码处理中文问题
                downloadZipFileName = URLEncoder.encode(downloadZipFileName, "UTF-8");// 处理中文文件名的问题
                downloadZipFileName = new String(downloadZipFileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
            }
            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8"); // 重点突出
            response.setContentType("application/octet-stream");// 不同类型的文件对应不同的MIME类型 // 重点突出
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment;filename="+downloadZipFileName);

            for (int i = 0; i < srcFiles.size(); i++) {
                // File file = new File(srcFiles.get(i)); 从路径中获取
                // FileInputStream in = new FileInputStream(file);
                // Add ZIP entry to output stream.
                // String fileName = file.getName();
                URL url = new URL(srcFiles.get(i));  // 从URL中获取
                // 图片名称
                String fileName = srcFilesName.get(i);
                out.putNextEntry(new ZipEntry(fileName+".png"));
                InputStream in = url.openConnection().getInputStream();
                byte[] buffer = new byte[1024];
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                in.close();
            }
            // Complete the ZIP file
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
