package la.niub.abcapi.servicecompre.component.util;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import la.niub.abcapi.servicecompre.model.request.TableDataDownXlsRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {

    /**
     * @Description: 导出Excel
     * @param workbook
     * @param sheetNum (sheet的位置，0表示第一个表格中的第一个sheet)
     * @param sheetTitle  （sheet的名称）
     * @param headers    （表格的列标题）
     * @param result   （表格的数据）
     * @param out  （输出流）
     * @throws Exception
     */
    public void exportExcel(HSSFWorkbook workbook,
                            int sheetNum,
                            String sheetTitle,
                            List<String> headers,
                            List<List<String>> result,
                            OutputStream out) throws Exception {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            HSSFCell cell = row.createCell((short) i);

            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
            cell.setCellValue(text.toString());
        }
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 1;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    HSSFCell cell = row.createCell((short) cellIndex);
                    cell.setCellValue(str);
                    cellIndex++;
                }
                index++;
            }
        }
    }

    /**
     * 导出数据表
     * @param workbook
     * @param sheetNum
     * @param sheetTitle
     * @param data
     * @throws Exception
     */
    public void exportExcelByTableDate(HSSFWorkbook workbook,
                            int sheetNum,
                            String sheetTitle,
                             List<TableDataDownXlsRequest> data) throws Exception {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        try {
            workbook.setSheetName(sheetNum, sheetTitle);
        } catch (IllegalArgumentException e){
        }
        // 设置表格默认列宽度为40个字节
        sheet.setDefaultColumnWidth((short) 30);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中

        int rowIndex;
        int columnIndex;
        int index = 0;
        int mergeRow;
        int mergeCol;
        HSSFRow row = sheet.createRow(index);
        for (TableDataDownXlsRequest item : data) {
            rowIndex = item.getRow();
            columnIndex = item.getColumn();
            mergeRow = item.getRowSpan();
            mergeCol = item.getColSpan();
//            if (item.getRowSpan() != null && item.getRowSpan()>1) {
//                sheet.addMergedRegion(new CellRangeAddress(
//                        rowIndex, (rowIndex + item.getRowSpan()-1), columnIndex, columnIndex
//                ));
//            }
//            if (item.getColSpan() != null && item.getColSpan()>1) {
//                sheet.addMergedRegion(new CellRangeAddress(
//                        rowIndex, rowIndex, columnIndex, (columnIndex + item.getColSpan()-1)
//                ));
//            }
            try{
                if (mergeRow > 1 || mergeCol > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(
                            rowIndex, (rowIndex+mergeRow-1), columnIndex, (columnIndex + mergeCol-1)
                    ));
                }
            } catch (IllegalStateException e) {

            }

            if(index != rowIndex){
                index = rowIndex;
                row = sheet.createRow(index);
            }
            HSSFCell cell = row.createCell((short) columnIndex);
            cell.setCellValue(item.getText());
            cell.setCellStyle(style);
        }
    }

}